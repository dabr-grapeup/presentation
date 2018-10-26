package com.grapeup.vehiclesystem.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleStateRepository extends MongoRepository<VehicleState, String> {

}
