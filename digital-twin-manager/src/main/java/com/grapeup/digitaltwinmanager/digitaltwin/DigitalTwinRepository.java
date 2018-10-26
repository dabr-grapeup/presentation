package com.grapeup.digitaltwinmanager.digitaltwin;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DigitalTwinRepository extends MongoRepository<DigitalTwin, String> {

}
