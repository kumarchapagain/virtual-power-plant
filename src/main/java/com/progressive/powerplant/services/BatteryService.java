package com.progressive.powerplant.services;


import com.progressive.powerplant.entities.BatteryEntity;
import com.progressive.powerplant.payloads.BatteriesInPostCodeRange;
import com.progressive.powerplant.payloads.Battery;

import java.util.List;

/**
 * The BatteryService interface defines for managing battery related operations.
 * Classes implementing this interface are responsible for implementing the business logic
 */
public interface BatteryService {

    List<BatteryEntity> createBatteries(List<Battery> batteries);

    BatteriesInPostCodeRange getBatteriesInPostcodeRange(int from, int to);

}
