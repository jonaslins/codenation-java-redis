package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class NeighborhoodMongoCustomImpl implements NeighborhoodMongoCustom {

    @Autowired
    private MongoTemplate mongoTemplate;


    public NeighborhoodMongo findByInterserction(GeoJson geoJson) {
        Query query = new Query(where("geometry").intersects(geoJson));
        NeighborhoodMongo neigh = mongoTemplate.findOne(query, NeighborhoodMongo.class);
        return neigh;
    }

}
