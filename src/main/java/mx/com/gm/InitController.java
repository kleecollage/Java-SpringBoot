package mx.com.gm;

import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
@Slf4j
public class InitController {

    @Value("${index.greetings}")
    private String greetings;

    @GetMapping("/")
    public String init(Model model) {
        var message = "Hello world with thymeleaf";

        var person = new Person();
        person.setName("John");
        person.setSurname("Doe");
        person.setEmail("john.doe@gmail.com");
        person.setPhone("01-55-78-63-44");

        var person2 = new Person();
        person2.setName("Jane");
        person2.setSurname("Smith");
        person2.setEmail("j.smith@mail.com");
        person2.setPhone("01-99-45-11-08");

        // TRADITIONAL METHOD //
        // var persons = new ArrayList<Person>();
        // persons.add(person);
        // persons.add(person2);

        // PRO METHOD //
        var persons = Arrays.asList(person, person2);

        log.info("Executing Controller Spring MVC");
        model.addAttribute("message", message);
        model.addAttribute("greetings", greetings);
        // model.addAttribute("person", person);
        model.addAttribute("persons", persons);

        return "index";
    }
}
