package com.grapeup.vehiclesystem.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class VehicleState {

  @Id
  private String id;

  private long mileageInMeters;

  private long fuelInMilliliters;

  private long rangeInMeters;

  private long velocityInKmph;

  private String gpsRoute;

}
