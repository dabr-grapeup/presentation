package com.grapeup.digitaltwinmanager.digitaltwin;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
public class DigitalTwin {

  @Id
  private String id = "main";

/*  private DigitalTwinProperty<Long> mileageInMeters = new DigitalTwinProperty<>();

  private DigitalTwinProperty<Long> rangeInMeters = new DigitalTwinProperty<>();

  private DigitalTwinProperty<String> gpsRoute = new DigitalTwinProperty<>();*/

}
