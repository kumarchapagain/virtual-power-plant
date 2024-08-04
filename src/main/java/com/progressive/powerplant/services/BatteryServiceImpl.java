package com.progressive.powerplant.services;

import com.progressive.powerplant.entities.BatteryEntity;
import com.progressive.powerplant.payloads.BatteriesInPostCodeRange;
import com.progressive.powerplant.payloads.Battery;
import com.progressive.powerplant.repositories.BatteryRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@Transactional
public class BatteryServiceImpl implements BatteryService {

    private final BatteryRepository batteryRepository;

    public BatteryServiceImpl(final BatteryRepository batteryRepository) {
        this.batteryRepository = batteryRepository;
    }

    @Override
    public List<BatteryEntity> createBatteries(List<Battery> batteries) {
        log.info("Creating Batteries");
        List<BatteryEntity> batteryList = this.toEntityList(batteries);
        return batteryRepository.saveAll(batteryList);
    }

    @Override
    public BatteriesInPostCodeRange getBatteriesInPostcodeRange(int from, int to) {
        List<BatteryEntity> batteries = batteryRepository.findByPostCodeBetween(from, to);
        return BatteriesInPostCodeRange.builder()
                .batteries(getSortedNames(batteries))
                .totalWattCapacity(calculateTotalWattCapacity(batteries))
                .averageWattCapacity(calculateAverageWattCapacity(batteries))
                .build();
    }

    private List<String> getSortedNames(final List<BatteryEntity> batteries) {
        return batteries.stream().map(BatteryEntity::getName).sorted().toList();
    }


    /**
     * Calculates the total watt capacity of a list of batteries.
     *
     * @param batteries The list of Battery objects for which to calculate the total watt capacity.
     * @return The total watt capacity of the batteries in the list.
     */
    private static double calculateTotalWattCapacity(List<BatteryEntity> batteries) {
        return batteries.stream().mapToDouble(BatteryEntity::getWattCapacity).sum();
    }

    /**
     * Calculates the average watt capacity of a list of batteries.
     *
     * @param batteries The list of Battery objects for which to calculate the average watt capacity.
     * @return The average watt capacity of the batteries in the list. If the list is empty, it returns 0.0.
     */
    private static double calculateAverageWattCapacity(List<BatteryEntity> batteries) {
        return batteries.stream().mapToDouble(BatteryEntity::getWattCapacity).average().orElse(0.0);
    }

    private BatteryEntity toEntity(Battery battery) {
        return BatteryEntity.builder()
                .name(battery.getName())
                .postCode(battery.getPostCode())
                .wattCapacity(battery.getWattCapacity())
                .build();
    }

    public List<BatteryEntity> toEntityList(List<Battery> batteries) {
        if (Objects.isNull(batteries)) {
            return List.of();
        }
        return batteries.stream().map(this::toEntity).collect(toList());
    }
}
