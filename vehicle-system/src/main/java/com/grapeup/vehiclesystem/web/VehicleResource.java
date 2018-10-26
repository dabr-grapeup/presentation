package com.grapeup.vehiclesystem.web;

import com.grapeup.vehiclesystem.data.VehicleState;
import com.grapeup.vehiclesystem.domain.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/vehicle")
public class VehicleResource {

  private final VehicleService vehicleService;

  @Autowired
  public VehicleResource(VehicleService vehicleService) {
    this.vehicleService = vehicleService;
  }

  @GetMapping
  public VehicleState getVehicleState() {
    return vehicleService.getState();
  }

  @PostMapping("/vehicle/reset")
  public void reset() {
    vehicleService.reset();
  }

  @PostMapping("/vehicle/drive")
  public void drive() {
    vehicleService.drive();
  }


  @PostMapping("/vehicle/cancel")
  public void cancel() {
    vehicleService.cancel();
  }

  @PostMapping("/vehicle/refuel")
  public void refuel() {
    vehicleService.refuel();
  }

}
