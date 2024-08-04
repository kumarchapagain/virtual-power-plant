package com.progressive.powerplant.services;

import com.progressive.powerplant.initializer.BatteryDataInitializer;
import com.progressive.powerplant.payloads.Battery;
import com.progressive.powerplant.repositories.BatteryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BatteryServiceTest {

    @InjectMocks
    private BatteryServiceImpl batteryService;

    @Mock
    private BatteryRepository batteryRepository;

    @Test
    public void testCreateBatteries() {

        List<Battery> batteries = BatteryDataInitializer.getBatteries();
        batteryService.createBatteries(batteries);
        verify(batteryRepository, times(1)).saveAll(any());

    }

    @Test
    public void testGetBatteriesInPostcodeRange() {
        int from = 5000;
        int to = 6000;
        batteryService.getBatteriesInPostcodeRange(from, to);
        verify(batteryRepository, times(1)).findByPostCodeBetween(from, to);
    }
}
