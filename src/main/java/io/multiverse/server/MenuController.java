package io.multiverse.server;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class MenuController {
    private MenuRepository repository;
    private RestaurantRepository restaurantsRepository;

    MenuController(MenuRepository repository, RestaurantRepository restaurantsRepository) {
        this.repository = repository;
        this.restaurantsRepository = restaurantsRepository;
    }

    @PersistenceContext
    private EntityManager em;
    
    @PostMapping("/restaurants/{restaurant_id}/menus")
    public Menu newMenu(@RequestBody Menu newMenu, @PathVariable Integer restaurant_id) {
        Restaurant restaurant = restaurantsRepository.findById(restaurant_id).get();
        newMenu.setRestaurant(restaurant);
        return repository.save(newMenu);
    }
}
