package com.progressive.powerplant.controllers;

import com.progressive.powerplant.payloads.BatteriesInPostCodeRange;
import com.progressive.powerplant.payloads.Battery;
import com.progressive.powerplant.services.BatteryService;
import com.progressive.powerplant.utils.AppConstants;
import com.progressive.powerplant.utils.BatteryInPostCodeRangeRepresentationAssembler;
import com.progressive.powerplant.utils.BatteryRepresentationAssembler;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The BatteryController class defines a REST API controller responsible for managing battery-related operations.
 * It handles incoming HTTP requests related to batteries and delegates the business logic to the BatteryService.
 */
@RestController
@Slf4j
@RequestMapping(AppConstants.BATTERY_ENDPOINT)
public class BatteryController {

    private final BatteryService batteryService;
    private final BatteryRepresentationAssembler assembler;
    private final BatteryInPostCodeRangeRepresentationAssembler postCodeRangeRepresentationAssembler;

    /**
     * Constructor for BatteryController, injecting the BatteryService dependency.
     * @param batteryService The BatteryService responsible for handling battery operations.
     */
    public BatteryController(final BatteryService batteryService, final BatteryRepresentationAssembler assembler,
                             final BatteryInPostCodeRangeRepresentationAssembler postCodeRangeRepresentationAssembler) {
        this.batteryService = batteryService;
        this.assembler = assembler;
        this.postCodeRangeRepresentationAssembler = postCodeRangeRepresentationAssembler;
    }

    /**
     * Endpoint for creating multiple battery records.
     * @param batteries The list of Battery objects to be created.
     * @return ResponseEntity with a list of created Battery objects and a 201 (CREATED) status code.
     */
    @PostMapping
    public ResponseEntity<CollectionModel<Battery>> createBatteries(@Valid @RequestBody List<Battery> batteries) {
        CollectionModel<Battery> savedBatteries = assembler.toCollectionModel(batteryService.createBatteries(batteries));
        return new ResponseEntity<>(savedBatteries, HttpStatus.CREATED);
    }

    /**
     * Endpoint for retrieving battery records within a specified postcode range.
     * @param from The request parameters containing startPostcode.
     * @param to The request parameters containing endPostcode.
     * @return ResponseEntity with a BatteriesInPostCodeRange containing the filtered battery list and statistics, along with a 200 (OK) status code.
     */
    @GetMapping
    public ResponseEntity<BatteriesInPostCodeRange> getBatteriesInPostcodeRange(@Valid @RequestParam int from, @RequestParam int to) {
        BatteriesInPostCodeRange batteriesInPostCodeRange = postCodeRangeRepresentationAssembler
                .toModel(batteryService.getBatteriesInPostcodeRange(from, to), from, to);
        return new ResponseEntity<>(batteriesInPostCodeRange, HttpStatus.OK);
    }
}
