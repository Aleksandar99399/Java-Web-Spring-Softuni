package prep.servicies;

import prep.models.service.HeroServiceModel;
import prep.models.view.HeroViewModel;
import prep.models.view.HomeViewHero;

import java.util.List;

public interface HeroService {
    HeroServiceModel createHero(HeroServiceModel map);

    HeroViewModel findById(String id);

    List<HomeViewHero> findAllHeroes();

    void delete(String id);
}
