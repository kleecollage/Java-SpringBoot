package mx.com.gm.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.dao.IPersonDao;
import mx.com.gm.domain.Person;
import mx.com.gm.service.IPersonService;
import mx.com.gm.service.PersonServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class InitController {
    // IPersonService injection
    private final IPersonService personService;
    public InitController(IPersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public String init(Model model) {
        log.info("Executing Controller Spring MVC");
        var persons = personService.listPersons();
        model.addAttribute("persons", persons);

        return "index";
    }

    @GetMapping("/add")
    public String add(Person person) {
        return "update";
    }

    @PostMapping("/save")
    public String save(@Valid Person person, Errors errors) {
        if (errors.hasErrors()) return "update";
        personService.save(person);

        return "redirect:/";
    }

    @GetMapping("/edit/{idPerson}")
    public String edit(@Valid Person person, Model model, Errors errors) {
        if (errors.hasErrors()) return "update";

        person = personService.findPerson(person);
        model.addAttribute("person", person);

        return "update";
    }

    @GetMapping("/delete")
    public String delete(Person person) {
        personService.delete(person);
        return "redirect:/";
    }
}














