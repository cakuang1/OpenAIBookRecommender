package micro.cary.recc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import micro.cary.recc.domain.ChatRequest;
import micro.cary.recc.domain.ChatResponse;



@RestController
@RequestMapping("/recommendation")
public class ReccController {
    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;
    @Autowired


    @Value("${openai.model}")
    private String model;
    
    @Value("${openai.api.url}")
    private String apiUrl;
    
    @GetMapping("/grabmovies")
    public String grabmovies(@RequestParam List<String> listofmovies) {
        String prompt = "Given I have read the books " + listofmovies +  ",give me other book reccomendations in with a list of JSON with the following keys [title,author,reason]. Please ensure that only the list is returned, meaning response should be a list, not JSON.";
        ChatRequest request = new ChatRequest(model, prompt);        
        // call the API
        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }

        return response.getChoices().get(0).getMessage().getContent();
    }
}