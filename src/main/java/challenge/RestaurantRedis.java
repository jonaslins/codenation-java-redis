package challenge;

import java.io.Serializable;

/**
 * Classe para mapear o restaurante no Redis
 */
public class RestaurantRedis implements Serializable {

    private String id;
    private String name;
    private double x;
    private double y;

    public RestaurantRedis() {
    }

    public RestaurantRedis(String id, String name, double x, double y) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
