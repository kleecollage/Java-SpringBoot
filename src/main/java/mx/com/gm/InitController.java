package mx.com.gm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class InitController {

    @GetMapping("/")
    public String init() {
        log.info("Execute Init Controller");
        log.debug("More information about the controller");
        return "Hello World with Spring Boot!";
    }
}
