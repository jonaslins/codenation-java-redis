package challenge;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

/**
 * Classe para mapear o bairro no Redis
 */
public class NeighborhoodRedis implements Serializable {

    @Id
    private String id;
    private String name;
    private List<RestaurantRedis> restaurants;

    public NeighborhoodRedis() {
    }

    public NeighborhoodRedis(String id, String name, List<RestaurantRedis> restaurants) {
        this.id = id;
        this.name = name;
        this.restaurants = restaurants;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRestaurants(List<RestaurantRedis> restaurants) {
        this.restaurants = restaurants;
    }

    public List<RestaurantRedis> getRestaurants() {
        return restaurants;
    }
}
