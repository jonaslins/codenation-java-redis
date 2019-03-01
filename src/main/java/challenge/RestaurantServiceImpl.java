package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
	private RestaurantMongoRepository restaurantMongoRepository;

    @Autowired
    private NeighborhoodMongoRepository neighborhoodMongoRepository;

	@Override
	public NeighborhoodRedis findInNeighborhood(double x, double y) {

//        Optional<NeighborhoodMongo> neigh = neighborhoodMongoRepository.findById("55cb9c666c522cafdb053a89");
        //find the neighborhod first
        NeighborhoodMongo neigh = neighborhoodMongoRepository.findByInterserction(new GeoJsonPoint(x, y));

        //then serch the neighborhood on redis


        //if it's not on redit then find on mongo
        List<RestaurantMongo> byLocationWithin = restaurantMongoRepository.findByLocationWithin(neigh.getGeometry());

        //then save the registres on redis


        //and return the Neighborhood with all restaurants within

        return null;
	}

}
