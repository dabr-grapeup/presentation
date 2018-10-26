package com.grapeup.digitaltwinmanager;

import com.grapeup.digitaltwinmanager.digitaltwin.DigitalTwin;
import com.grapeup.digitaltwinmanager.digitaltwin.DigitalTwinRepository;
import com.grapeup.digitaltwinmanager.gps.Destination;
import com.grapeup.digitaltwinmanager.gps.GPS;
import com.grapeup.digitaltwinmanager.vehicle.Update;
import com.grapeup.digitaltwinmanager.vehicle.VehicleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Source.class)
public class ManagerService {

  private final DigitalTwinRepository digitalTwinRepository;
  private final GPS gps;
  private final Source updatesChannel;

  @Autowired
  public ManagerService(DigitalTwinRepository digitalTwinRepository, GPS gps, Source updatesChannel) {
    this.digitalTwinRepository = digitalTwinRepository;
    this.gps = gps;
    this.updatesChannel = updatesChannel;
  }

  public void updateDigitalTwin(VehicleState vehicleState) {
    DigitalTwin digitalTwin = getDigitalTwin();

    digitalTwin.getMileageInMeters().setActual(vehicleState.getMileageInMeters());
    digitalTwin.getRangeInMeters().setActual(vehicleState.getRangeInMeters());
    digitalTwin.getGpsRoute().setActual(vehicleState.getGpsRoute());

    removeDesiredStateIfSet(digitalTwin);

    digitalTwinRepository.save(digitalTwin);
  }

  private void removeDesiredStateIfSet(DigitalTwin digitalTwin) {
    if (digitalTwin.getMileageInMeters().getActual() != null
        && digitalTwin.getMileageInMeters().getActual().equals(digitalTwin.getMileageInMeters().getDesired())) {
      digitalTwin.getMileageInMeters().setDesired(null);
    }

    if (digitalTwin.getGpsRoute().getActual() != null
        && digitalTwin.getGpsRoute().getActual().equals(digitalTwin.getGpsRoute().getDesired())) {
      digitalTwin.getGpsRoute().setDesired(null);
    }

    if (digitalTwin.getRangeInMeters().getActual() != null
        && digitalTwin.getRangeInMeters().getActual().equals(digitalTwin.getRangeInMeters().getDesired())) {
      digitalTwin.getRangeInMeters().setDesired(null);
    }
  }

  public DigitalTwin getDigitalTwin() {
    return digitalTwinRepository.findById("main").orElseGet(DigitalTwin::new);
  }

  public void updateGPS(Destination destination) {
    DigitalTwin digitalTwin = getDigitalTwin();
    String gpsRoute = gps.findRoute(destination, digitalTwin.getRangeInMeters().getActual() / 1000);
    digitalTwin.getGpsRoute().setDesired(gpsRoute);
    digitalTwinRepository.save(digitalTwin);
    updatesChannel.output().send(new GenericMessage<>(new Update("gpsRoute", gpsRoute)));
  }

  @Scheduled(fixedRate = 1000)
  private void updateVehicle() {
    DigitalTwin digitalTwin = getDigitalTwin();
    String gpsRoute = digitalTwin.getGpsRoute().getDesired();
    if (gpsRoute != null
        && !gpsRoute.equals(digitalTwin.getGpsRoute().getActual())) {
      updatesChannel.output().send(new GenericMessage<>(new Update("gpsRoute", gpsRoute)));
    }
  }
}
