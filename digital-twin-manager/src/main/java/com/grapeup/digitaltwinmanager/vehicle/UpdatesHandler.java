package com.grapeup.digitaltwinmanager.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@EnableBinding(Source.class)
@Component
public class UpdatesHandler {

  private final Source source;

  @Autowired
  public UpdatesHandler(Source source) {
    this.source = source;
  }

  public void sendGPSRoute(String route) {
    source.output().send(new GenericMessage<>(new Update("gpsRoute", route)));
  }

}
