package mx.com.gm.web;

import lombok.extern.slf4j.Slf4j;
import mx.com.gm.dao.IPersonDao;
import mx.com.gm.service.IPersonService;
import mx.com.gm.service.PersonServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
