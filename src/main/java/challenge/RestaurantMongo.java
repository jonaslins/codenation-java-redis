package challenge;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Classe para mapear o restaurante no MongoDB
 *
 */
@Document(collection = "restaurant")
public class RestaurantMongo {

    @Id
    private String id;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location;
    @Field
    private String name;

    public RestaurantMongo() {
    }

    public RestaurantMongo(GeoJsonPoint location, String name) {
        this.location = location;
        this.name = name;
    }

    public RestaurantMongo(String id, GeoJsonPoint location, String name) {
        this.id = id;
        this.location = location;
        this.name = name;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
