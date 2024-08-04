package com.progressive.powerplant.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.progressive.powerplant.initializer.BatteryDataInitializer;
import com.progressive.powerplant.payloads.BatteriesInPostCodeRange;
import com.progressive.powerplant.payloads.Battery;
import com.progressive.powerplant.utils.AppConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class BatteryControllerFunctionalTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    public void testCreateBatteries() throws Exception {
        perform()
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$._embedded.batteryList.size()").value(4))
                .andExpect(jsonPath("$._embedded.batteryList[0].name").value("Duracell"))
                .andReturn();
    }

    @Test
    public void testGetBatteriesInPostcodeRange() throws Exception {
        ResultActions resultActions = perform();
        resultActions.andExpect(status().isCreated());
        String responseString = mockMvc.perform(get(AppConstants.BATTERY_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("from", String.valueOf(5000))
                .queryParam("to", String.valueOf(6000)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        BatteriesInPostCodeRange batteriesInPostCodeRange = objectMapper.readValue(responseString, BatteriesInPostCodeRange.class);
        assertEquals(2, batteriesInPostCodeRange.getBatteries().size());
        assertEquals("Duracell", batteriesInPostCodeRange.getBatteries().get(0));
        assertEquals(8750, batteriesInPostCodeRange.getAverageWattCapacity());
        assertEquals(17500, batteriesInPostCodeRange.getTotalWattCapacity());
    }

    private ResultActions perform() throws Exception {

        List<Battery> batteries = BatteryDataInitializer.getBatteries();

        // Serialize the list of batteries to JSON
        String jsonBatteries = objectMapper.writeValueAsString(batteries);
        return mockMvc.perform(post(AppConstants.BATTERY_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBatteries));
    }
}
