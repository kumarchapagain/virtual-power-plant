package com.progressive.powerplant.payloads;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BatteriesInPostCodeRange extends RepresentationModel<BatteriesInPostCodeRange> {

	public List<String> batteries;
	public double totalWattCapacity = 0.0D;
	public double averageWattCapacity = 0.0D;
}
