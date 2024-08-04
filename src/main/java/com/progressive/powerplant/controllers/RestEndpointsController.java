package com.progressive.powerplant.controllers;

import com.progressive.powerplant.utils.AppConstants;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RestEndpointsController {

    @GetMapping
    RepresentationModel<?> index() {
        RepresentationModel<?> rootModel = new RepresentationModel<>();
        rootModel.add(linkTo(methodOn(BatteryController.class).createBatteries(null)).withRel(AppConstants.CREATE_BATTERIES));
        rootModel.add(linkTo(methodOn(BatteryController.class).getBatteriesInPostcodeRange(0, 0)).withRel(AppConstants.BATTERIES_IN_RANGE));
        return rootModel;
    }
}
