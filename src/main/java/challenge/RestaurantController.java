package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantService service;

	@GetMapping("/findInNeighborhood")
	public NeighborhoodRedis findInNeighborhood(Double x, Double y) {
		return service.findInNeighborhood(x, y);
	}

}
