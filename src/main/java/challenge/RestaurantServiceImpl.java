package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private NeighborhoodMongoRepository neighborhoodMongoRepository;

    @Autowired
    private NeighborhoodCache neighborhoodCache;

    @Override
    public NeighborhoodRedis findInNeighborhood(double x, double y) {
        NeighborhoodMongo neigh = neighborhoodMongoRepository.findByInterserction(new GeoJsonPoint(x, y));
        return neighborhoodCache.test(neigh);
    }

}
