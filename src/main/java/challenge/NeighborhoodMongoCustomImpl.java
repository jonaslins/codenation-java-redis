package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NeighborhoodMongoCustomImpl implements NeighborhoodMongoCustom{

    @Autowired
    private MongoTemplate mongoTemplate;

}
