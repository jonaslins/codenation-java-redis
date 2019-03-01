package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private NeighborhoodMongoRepository neighborhoodMongoRepository;

    @Autowired
    private RestaurantMongoRepository restaurantMongoRepository;

    @Autowired
    private NeighborhoodRedisRepository neighborhoodRedisRepository;

    @Override
    public NeighborhoodRedis findInNeighborhood(double x, double y) {

        //find the neighborhod first
        NeighborhoodMongo neigh = neighborhoodMongoRepository.findByInterserction(new GeoJsonPoint(x, y));

        //then search the neighborhood on redis
        NeighborhoodRedis cachedNeigh = neighborhoodRedisRepository
                .findById(neigh.getId())
                .orElseGet(() -> {
                    //if it's not on redis then search on mongobd
                    NeighborhoodRedis neighFromDb = loadFromDb(neigh);
                    //then save the registres on redis
                    neighborhoodRedisRepository.save(neighFromDb.getId(), neighFromDb);
                    return neighFromDb;
                });

        //and return the Neighborhood with all restaurants within
        return cachedNeigh;
    }

    public NeighborhoodRedis loadFromDb(NeighborhoodMongo nm) {
        List<RestaurantMongo> byLocationWithin = restaurantMongoRepository.findByLocationWithinOrderByNameAsc(nm.getGeometry());

        NeighborhoodRedis nr = new NeighborhoodRedis();
        List<RestaurantRedis> resRedis = byLocationWithin.stream().map(rm ->
                new RestaurantRedis(rm.getId(),
                        rm.getName(),
                        rm.getLocation().getX(),
                        rm.getLocation().getY())
        ).collect(Collectors.toList());

        nr.setId(nm.getId());
        nr.setName(nm.getName());
        nr.setRestaurants(resRedis);
        return nr;
    }

}
