package com.grapeup.digitaltwinmanager.digitaltwin;

import com.grapeup.digitaltwinmanager.ManagerService;
import com.grapeup.digitaltwinmanager.vehicle.UpdatesHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PropagateDesiredStateJob {

  private final ManagerService managerService;
  private final UpdatesHandler updatesHandler;

  @Autowired
  public PropagateDesiredStateJob(ManagerService managerService, UpdatesHandler updatesHandler) {
    this.managerService = managerService;
    this.updatesHandler = updatesHandler;
  }

  @Scheduled(fixedRate = 1000)
  private void updateVehicle() {
    DigitalTwin digitalTwin = managerService.getDigitalTwin();
    String gpsRoute = digitalTwin.getGpsRoute().getDesired();
    if (gpsRoute != null
        && !gpsRoute.equals(digitalTwin.getGpsRoute().getActual())) {
      updatesHandler.sendGPSRoute(gpsRoute);
    }
  }

}
