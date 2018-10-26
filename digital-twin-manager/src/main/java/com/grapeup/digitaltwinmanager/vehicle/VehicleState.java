package com.grapeup.digitaltwinmanager.vehicle;

import lombok.Data;

@Data
public class VehicleState {

  private long mileageInMeters;

  private long rangeInMeters;

  private String gpsRoute;

}
