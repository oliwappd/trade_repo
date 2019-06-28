package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index(@RequestParam String abc) {
        return "Greetings from Spring Boot! " + abc + " !!!";
    }

}