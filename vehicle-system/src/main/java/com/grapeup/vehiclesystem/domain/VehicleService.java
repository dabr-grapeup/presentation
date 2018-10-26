package com.grapeup.vehiclesystem.domain;

import com.grapeup.vehiclesystem.data.VehicleState;
import com.grapeup.vehiclesystem.data.VehicleStateRepository;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Source.class)
public class VehicleService {

  private static final int FUEL_CONSUMPTION_IN_LITERS_PER_100_KM = 6;

  private final VehicleStateRepository vehicleStateRepository;

  private final Source syncChannel;

  private final ExecutorService executor = Executors.newSingleThreadExecutor();

  private FutureTask<Void> driveTask;

  @Autowired
  public VehicleService(VehicleStateRepository vehicleStateRepository, Source syncChannel) {
    this.vehicleStateRepository = vehicleStateRepository;
    this.syncChannel = syncChannel;
  }

  public VehicleState getState() {
    return getOrInit();
  }

  private VehicleState getOrInit() {
    return vehicleStateRepository.findById("main").orElseGet(this::reset);
  }

  public VehicleState reset() {
    return save(init());
  }

  public void drive() {
    driveTask = new FutureTask<>(() -> {
      while (true) {
        VehicleState state = vehicleStateRepository.findById("main").orElseGet(this::init);
        state.setMileageInMeters(state.getMileageInMeters() + 833);
        if (state.getFuelInMilliliters() <= 50) {
          state.setFuelInMilliliters(0);
          state.setRangeInMeters(0);
          state.setVelocityInKmph(0);
          save(state);
          return null;
        }
        state.setFuelInMilliliters(state.getFuelInMilliliters() - 50);
        state.setRangeInMeters(calculateRange(state));
        state.setVelocityInKmph(100);
        save(state);
        Thread.sleep(500);
      }
    });
    getOrInit();
    executor.execute(driveTask);
  }

  private VehicleState save(VehicleState state) {
    state = vehicleStateRepository.save(state);
    syncChannel.output().send(new GenericMessage<>(state));
    return state;
  }

  public void cancel() {
    driveTask.cancel(true);
    VehicleState state = getOrInit();
    state.setVelocityInKmph(0);
    save(state);
  }

  public void refuel() {
    VehicleState state = vehicleStateRepository.findById("main").orElseGet(this::init);
    state.setFuelInMilliliters(30000);
    state.setRangeInMeters(calculateRange(state));
    save(state);
  }


  private VehicleState init() {
    VehicleState vehicleState = new VehicleState();
    vehicleState.setId("main");
    vehicleState.setMileageInMeters(43089200);
    vehicleState.setFuelInMilliliters(20500);
    vehicleState.setRangeInMeters(calculateRange(vehicleState));
    return vehicleState;
  }

  private long calculateRange(VehicleState state) {
    return Math.round(state.getFuelInMilliliters() * 100) / FUEL_CONSUMPTION_IN_LITERS_PER_100_KM;
  }

  public void update(String key, String value) {
    if ("map".equals(key)) {
      VehicleState state = getOrInit();
      state.setGpsRoute(value);
      save(state);
    }
  }
}
