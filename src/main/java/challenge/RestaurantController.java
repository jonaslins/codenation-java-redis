package challenge;

import org.springframework.beans.factory.annotation.Autowired;

public class RestaurantController {

	@Autowired
	private RestaurantService service;

	public NeighborhoodRedis findInNeighborhood() {
		return service.findInNeighborhood(0, 0);
	}

}
