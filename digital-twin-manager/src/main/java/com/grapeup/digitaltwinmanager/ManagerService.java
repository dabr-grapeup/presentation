package com.grapeup.digitaltwinmanager;

import com.grapeup.digitaltwinmanager.digitaltwin.DigitalTwin;
import com.grapeup.digitaltwinmanager.gps.Destination;
import com.grapeup.digitaltwinmanager.gps.GPS;
import com.grapeup.digitaltwinmanager.vehicle.UpdatesHandler;
import com.grapeup.digitaltwinmanager.vehicle.VehicleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

/*
  private final DigitalTwinRepository digitalTwinRepository;
  private final GPS gps;
  private final UpdatesHandler updatesHandler;

  @Autowired
  public ManagerService(DigitalTwinRepository digitalTwinRepository, GPS gps, UpdatesHandler updatesHandler) {
    this.digitalTwinRepository = digitalTwinRepository;
    this.gps = gps;
    this.updatesHandler = updatesHandler;
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

*/
  public DigitalTwin getDigitalTwin() {
    // get digital twin from repository or create new one
    return new DigitalTwin();
  }

  public void updateGPS(Destination destination) {
    DigitalTwin digitalTwin = getDigitalTwin();
    // String gpsRoute = gps.findRoute(destination, digitalTwin.getRangeInMeters().getActual() / 1000);
    // set desired state and save in database
    // updatesHandler.sendGPSRoute(gpsRoute);
  }
}
