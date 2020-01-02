package elasticSearch.helloNoElastic;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @CrossOrigin
    @RequestMapping("/")
    public String index() {
        MessageFormat newMessage = new MessageFormat();
        newMessage.setContents("Greetings from Spring Boot!");

        Gson jsonObject = new Gson();
        
        return jsonObject.toJson(newMessage, MessageFormat.class);
    }



}