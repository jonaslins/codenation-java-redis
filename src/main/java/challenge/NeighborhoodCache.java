package challenge;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NeighborhoodCache {

    @Autowired
    private RestaurantMongoRepository restaurantMongoRepository;

    @Cacheable(cacheNames = "neighborhoods", key = "'neighborhood:'+#nm.id")
    public NeighborhoodRedis test(NeighborhoodMongo nm) {
        System.out.println("sasdasdasda");
        List<RestaurantMongo> byLocationWithin = restaurantMongoRepository.findByLocationWithin(nm.getGeometry());

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
