package com.api.parkingcontrol.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.models.UserModel;


public interface ParkingSpotService {

	ParkingSpotModel save(ParkingSpotModel parkingSpotModel);

	boolean existsByLicensePlateCar(String licensePlateCar);

	boolean existsByParkingSpotNumber(String parkingSpotNumber);

	boolean existsByApartmentAndBlock(String apartment, String block);

	// List<ParkingSpotModel> findAll()
	List<ParkingSpotModel> findAll();

	Optional<ParkingSpotModel> findById(UUID id);

	void delete(ParkingSpotModel parkingSpotModel);
	
	String addUser(UserModel userModel);
}
