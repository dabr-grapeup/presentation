package com.grapeup.digitaltwinmanager.digitaltwin;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class DigitalTwin {

  @Id
  private String id = "main";

  private long mileageInMeters;

  private long rangeInMeters;

  private DigitalTwinProperty<String> gpsRoute = new DigitalTwinProperty<>();

}
