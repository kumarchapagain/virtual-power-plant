package com.progressive.powerplant.initializer;

import com.progressive.powerplant.entities.BatteryEntity;
import com.progressive.powerplant.payloads.Battery;

import java.util.List;

public class BatteryDataInitializer {

    public static List<Battery> getBatteries() {
        return List.of(
                getBattery("Duracell", 5000, 13000),
                getBattery("Arron", 6010, 5000),
                getBattery("Exide", 5040, 4500),
                getBattery("Battery", 7000, 6000)
        );
    }

    public static Battery getBattery(String name, int postCode, double wattCapacity) {
        return Battery.builder()
                .name(name)
                .postCode(postCode)
                .wattCapacity(wattCapacity)
                .build();
    }

    public static BatteryEntity getBatteryEntity(String name, int postCode, double wattCapacity) {
        return BatteryEntity.builder()
                .name(name)
                .postCode(postCode)
                .wattCapacity(wattCapacity)
                .build();
    }

    public static List<BatteryEntity> getBatteriesEntity() {
        return List.of(
                getBatteryEntity("Duracell", 5000, 13000),
                getBatteryEntity("Arron", 6010, 5000),
                getBatteryEntity("Exide", 5040, 4500),
                getBatteryEntity("Battery", 7000, 6000)
        );
    }
}
