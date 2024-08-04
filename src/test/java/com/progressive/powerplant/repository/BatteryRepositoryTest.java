package com.progressive.powerplant.repository;


import com.progressive.powerplant.entities.BatteryEntity;
import com.progressive.powerplant.initializer.BatteryDataInitializer;
import com.progressive.powerplant.repositories.BatteryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class BatteryRepositoryTest {

  @Autowired
  private BatteryRepository batteryRepository;

  @Test
  void testFindByPostCodeBetween() {

    List<BatteryEntity> batteryEntities = BatteryDataInitializer.getBatteriesEntity();
    batteryRepository.saveAll(batteryEntities);

    List<BatteryEntity> rangeBatteries = batteryRepository.findByPostCodeBetween(5000, 6000);
    assertEquals(2, rangeBatteries.size());
    assertEquals("Duracell", rangeBatteries.get(0).getName());
  }

  @Test
  void testFindByPostCodeBetweenNoMatch() {

    List<BatteryEntity> batteryEntities = BatteryDataInitializer.getBatteriesEntity();
    batteryRepository.saveAll(batteryEntities);

    List<BatteryEntity> rangeBatteries = batteryRepository.findByPostCodeBetween(3000, 4000);
    assertEquals(0, rangeBatteries.size());
  }

}