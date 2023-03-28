package prep.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import prep.models.binding.HeroAddBindingModel;
import prep.models.service.HeroServiceModel;
import prep.servicies.HeroService;

import javax.validation.Valid;

@Controller
@RequestMapping("/heroes")
public class HeroesController {

    private final HeroService heroService;
    private final ModelMapper modelMapper;

    @Autowired
    public HeroesController(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    public String create(Model model){
        if (!model.containsAttribute("heroAddBindingModel")){
            model.addAttribute("heroAddBindingModel",new HeroAddBindingModel());
        }
        return "create-hero";
    }

    @PostMapping("/create")
    public String createConfirm(@Valid @ModelAttribute("heroAddBindingModel")
                                      HeroAddBindingModel heroAddBindingModel,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes,
                                      ModelAndView modelAndView){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("heroAddBindingModel",heroAddBindingModel);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.heroAddBindingModel",bindingResult);

            return "redirect:create";
        }else {
            this.heroService.createHero(
                    this.modelMapper
                            .map(heroAddBindingModel, HeroServiceModel.class));

            return "redirect:/";
        }

    }

    @GetMapping("/details")
    public ModelAndView details(@RequestParam("id") String id,ModelAndView modelAndView){
        modelAndView.addObject("hero",heroService.findById(id));
        modelAndView.setViewName("details-hero");

        return modelAndView;
    }

    @GetMapping("/delete")
    public String  delete(@RequestParam("id") String id){
        this.heroService.delete(id);
        return "redirect:/";

    }

}
