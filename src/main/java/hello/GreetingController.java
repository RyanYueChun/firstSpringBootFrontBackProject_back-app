package hello;

import java.util.concurrent.atomic.AtomicLong;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @CrossOrigin
    @GetMapping("/greeting")
    public Greeting getGreeting(@RequestParam(value="name", required = false, defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @CrossOrigin
    @PostMapping("/greeting")
    public Greeting greetingPost(@RequestBody String receivedJson) {
        Gson gson = new Gson();
        MessageFormat receivedBody = gson.fromJson(receivedJson, MessageFormat.class);

        return new Greeting(counter.incrementAndGet(),
                String.format(template, receivedBody.getContents()));
    }
}