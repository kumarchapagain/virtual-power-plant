package com.progressive.powerplant.utils;

import com.progressive.powerplant.controllers.BatteryController;
import com.progressive.powerplant.entities.BatteryEntity;
import com.progressive.powerplant.payloads.Battery;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BatteryRepresentationAssembler extends RepresentationModelAssemblerSupport<BatteryEntity, Battery> {

    public BatteryRepresentationAssembler() {
        super(BatteryController.class, Battery.class);
    }

    @Override
    public Battery toModel(BatteryEntity entity) {
        Battery resource = instantiateModel(entity);
        BeanUtils.copyProperties(entity, resource);
        return resource;
    }

    @Override
    public CollectionModel<Battery> toCollectionModel(Iterable<? extends BatteryEntity> entities) {
        CollectionModel<Battery> batteries = super.toCollectionModel(entities);
        batteries.add(
                linkTo(methodOn(BatteryController.class).createBatteries(null)).withSelfRel(),
                linkTo(methodOn(BatteryController.class).getBatteriesInPostcodeRange(0, 0)).withRel(AppConstants.BATTERIES_IN_RANGE)
        );
        return batteries;
    }

}
