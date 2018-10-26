package com.grapeup.digitaltwinmanager.digitaltwin;

import lombok.Data;

@Data
public class DigitalTwinProperty<T> {

  private T actual;
  private T desired;

}
