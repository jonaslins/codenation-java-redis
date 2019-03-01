package challenge;

import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RestaurantMongoRepository extends MongoRepository<RestaurantMongo, String>, RestaurantMongoCustom {

    List<RestaurantMongo> findByLocationWithin(GeoJsonPolygon polygon);

}
