package com.grapeup.digitaltwinmanager.vehicle;

import org.springframework.stereotype.Component;

@Component
public class UpdatesHandler {

  public void sendGPSRoute(String route) {
    new Update("gpsRoute", route);
  }

}
