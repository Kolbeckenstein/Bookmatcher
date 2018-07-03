package bookmatcher;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import bookmatcher.*;
import bookmatcher.data.MockDataRepository;

@RestController
public class BookmatcherController {

    MockDataRepository repo = new MockDataRepository();

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot, " + repo.getUsername() + "!";
        //return "Greetings from Spring Boot!";
    }

}
