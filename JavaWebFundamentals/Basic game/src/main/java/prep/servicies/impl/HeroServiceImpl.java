package prep.servicies.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prep.models.entities.Hero;
import prep.models.service.HeroServiceModel;
import prep.models.view.HeroViewModel;
import prep.models.view.HomeViewHero;
import prep.repositories.HeroRepository;
import prep.servicies.HeroService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HeroServiceImpl implements HeroService {

    private final HeroRepository heroRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HeroServiceImpl(HeroRepository heroRepository, ModelMapper modelMapper) {
        this.heroRepository = heroRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public HeroServiceModel createHero(HeroServiceModel map) {
        Hero hero=this.modelMapper
                .map(map,Hero.class);
        hero.setaHeroClass(map.getHeroClass());
        return this.modelMapper
                .map(this.heroRepository.saveAndFlush(hero),
                        HeroServiceModel.class);
    }

    @Override
    public HeroViewModel findById(String id) {

        return this.heroRepository.findById(id)
                .map(hero -> {
                    HeroViewModel heroViewModel=
                        this.modelMapper.map(hero,HeroViewModel.class);
                    heroViewModel.setImgUrl(
                            String.format("/img/%s.jpg",hero.getaHeroClass().name()));
                    return  heroViewModel;
                })
                .orElse(null);
    }

    @Override
    public List<HomeViewHero> findAllHeroes() {
        return this.heroRepository
                .findAll()
                .stream()
                .map(hero -> {
                    HomeViewHero heroViewModel=
                            this.modelMapper.map(hero,HomeViewHero.class);
                    heroViewModel.setImgUrl(
                            String.format("/img/%s.jpg",hero.getaHeroClass().name()));
                    return  heroViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        this.heroRepository.deleteById(id);
    }
}
