package io.multiverse.server;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {
    private RestaurantRepository repository;

    RestaurantController(RestaurantRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> all() {
        return repository.findAll();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant getRestaurant(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping("/restaurants")
    public Restaurant newRestaurant(@RequestBody Restaurant newRestaurant) {
        return repository.save(newRestaurant);
    }

    @PutMapping("/restaurants/{id}")
    public Restaurant update(@RequestBody Restaurant update, @PathVariable Integer id) {
        return repository.findById(id)
            .map(restaurant -> {
                restaurant.setName(update.getName());
                restaurant.setImageURL(update.getImageURL());
                return repository.save(restaurant);
            }).orElseThrow();
    }

    @DeleteMapping("/restaurants/{id}")
    public void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
