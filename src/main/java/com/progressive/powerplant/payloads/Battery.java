package com.progressive.powerplant.payloads;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Battery extends RepresentationModel<Battery> {

  private String name;
  private int postCode;
  private double wattCapacity;

}
