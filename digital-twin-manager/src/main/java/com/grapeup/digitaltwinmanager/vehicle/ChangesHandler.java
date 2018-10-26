package com.grapeup.digitaltwinmanager.vehicle;

import com.grapeup.digitaltwinmanager.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
@Slf4j
public class ChangesHandler {

  private final ManagerService managerService;

  @Autowired
  public ChangesHandler(ManagerService managerService) {
    this.managerService = managerService;
  }

  @StreamListener(target = Sink.INPUT)
  public void receiveMessage(VehicleState vehicleState) {
    log.info("Received message: {}", vehicleState);
    managerService.updateDigitalTwin(vehicleState);
  }

}
