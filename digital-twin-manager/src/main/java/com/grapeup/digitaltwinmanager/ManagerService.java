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

  private final GPS gps;
  private final UpdatesHandler updatesHandler;

  @Autowired
  public ManagerService(GPS gps, UpdatesHandler updatesHandler) {
    this.gps = gps;
    this.updatesHandler = updatesHandler;
  }

  public void updateDigitalTwin(VehicleState vehicleState) {
    DigitalTwin digitalTwin = getDigitalTwin();

  }

  /*
  private void removeDesiredStateIfSet(DigitalTwin digitalTwin) {
    if (digitalTwin.getGpsRoute().getActual() != null
        && digitalTwin.getGpsRoute().getActual().equals(digitalTwin.getGpsRoute().getDesired())) {
      digitalTwin.getGpsRoute().setDesired(null);
    }
  }

*/
  public DigitalTwin getDigitalTwin() {
    // get digital twin from repository or create new one
    return new DigitalTwin();
  }

  public void updateGPS(Destination destination) {
    DigitalTwin digitalTwin = getDigitalTwin();
    // find route
    // set desired state and save in database
    // updatesHandler.sendGPSRoute(gpsRoute);
  }
}
