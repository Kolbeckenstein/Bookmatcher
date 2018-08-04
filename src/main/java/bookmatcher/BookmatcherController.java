package bookmatcher;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import bookmatcher.*;
import bookmatcher.data.DirectApiSpikeRepository;
import bookmatcher.data.MockDataRepository;
import bookmatcher.utils.OAuth;

@RestController
public class BookmatcherController {

    MockDataRepository repo = new MockDataRepository();

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot, " + repo.getUsername() + "!";
    }

    @RequestMapping("/test1")
    public String apiSpike(){
        DirectApiSpikeRepository spikeRepo = new DirectApiSpikeRepository();
        return spikeRepo.getUsername();
    }

    @RequestMapping("/test2")
    public String oathSpike() {
        OAuth oauth1 = new OAuth();
        return oauth1.getID();
    }
}
