package com.grapeup.vehiclesystem.sync;

import com.grapeup.vehiclesystem.domain.VehicleService;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
@Slf4j
public class Inbound {

  private final VehicleService vehicleService;

  @Autowired
  public Inbound(VehicleService vehicleService) {
    this.vehicleService = vehicleService;
  }

  @StreamListener(target = Sink.INPUT)
  public void receive(Change payload) {
    log.info("Received message: {}", payload);
    vehicleService.update(payload.getKey(), payload.getValue());
  }

}
