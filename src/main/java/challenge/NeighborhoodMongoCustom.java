package challenge;

import org.springframework.data.mongodb.core.geo.GeoJson;

public interface NeighborhoodMongoCustom {

    NeighborhoodMongo findByInterserction(GeoJson geoJson);

}
