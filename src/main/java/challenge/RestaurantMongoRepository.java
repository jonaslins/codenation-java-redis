package challenge;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantMongoRepository extends MongoRepository<RestaurantMongo, String> {
}
