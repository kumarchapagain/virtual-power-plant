package com.progressive.powerplant.utils;

import com.progressive.powerplant.controllers.BatteryController;
import com.progressive.powerplant.payloads.BatteriesInPostCodeRange;

import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BatteryInPostCodeRangeRepresentationAssembler {


    public BatteriesInPostCodeRange toModel(BatteriesInPostCodeRange batteriesInPostCodeRange, int from, int to) {
        batteriesInPostCodeRange.add(
                linkTo(methodOn(BatteryController.class).getBatteriesInPostcodeRange(from, to)).withSelfRel()
        );
        return batteriesInPostCodeRange;
    }

}
