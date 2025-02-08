package mx.com.gm.web;

import lombok.extern.slf4j.Slf4j;
import mx.com.gm.dao.IPersonDao;
import mx.com.gm.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
@Slf4j
public class InitController {

    @Autowired
    private IPersonDao personDao;

    @GetMapping("/")
    public String init(Model model) {
        log.info("Executing Controller Spring MVC");
        var persons = personDao.findAll();
        model.addAttribute("persons", persons);

        return "index";
    }
}
