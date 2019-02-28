package challenge;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NeighborhoodMongoRepository extends MongoRepository<NeighborhoodMongo, String> {

}
