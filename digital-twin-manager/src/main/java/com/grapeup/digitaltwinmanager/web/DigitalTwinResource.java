package com.grapeup.digitaltwinmanager.web;

import com.grapeup.digitaltwinmanager.ManagerService;
import com.grapeup.digitaltwinmanager.digitaltwin.DigitalTwin;
import com.grapeup.digitaltwinmanager.gps.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DigitalTwinResource {

  private final ManagerService managerService;

  @Autowired
  public DigitalTwinResource(ManagerService managerService) {
    this.managerService = managerService;
  }

  @GetMapping("/digital-twin")
  public DigitalTwin get() {
    return managerService.getDigitalTwin();
  }

  @PostMapping("/gps")
  public void updateGPS(@RequestBody Destination destination) {
    managerService.updateGPS(destination);
  }
}
