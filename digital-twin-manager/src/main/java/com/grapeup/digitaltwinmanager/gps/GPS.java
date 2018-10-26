package com.grapeup.digitaltwinmanager.gps;

import org.springframework.stereotype.Service;

@Service
public class GPS {

  public String findRoute(Destination destination, long rangeInKm) {
    StringBuilder route = new StringBuilder("TRASA: ");
    if (rangeInKm <= destination.getDistanceInKilometers()) {
      route.append("jedź ").append(rangeInKm).append("km, zatankuj i ");
    }
    return route.append("jedź dalej do ").append(destination.getName()).toString();
  }

}
