package com.progressive.powerplant.repositories;

import com.progressive.powerplant.entities.BatteryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * The BatteryRepository interface extends the Spring Data JPA `JpaRepository` and provides
 * methods for performing CRUD (Create, Read, Update, Delete) operations on Battery entities.
 * It enables interaction with the underlying database for battery related data.
 */
public interface BatteryRepository extends JpaRepository<BatteryEntity, UUID> {

	List<BatteryEntity> findByPostCodeBetween(int from, int to);

}
