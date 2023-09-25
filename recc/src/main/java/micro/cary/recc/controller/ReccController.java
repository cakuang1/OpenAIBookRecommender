package micro.cary.recc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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
    
    @GetMapping(value = "/grabmovies", produces = "application/json")
    public ResponseEntity<String> grabmovies(@RequestParam List<String> listofmovies) {
        String prompt = "Given I have read the books " + listofmovies +  ",give me other book reccomendations in with a list of JSON.Do not include any explanations, only provide a  RFC8259 compliant JSON response following this format without deviation. [{title: the title of the book,author:the author of the book,reason : why do you recommend this book and how it relates to the books I have read}]";
        ChatRequest request = new ChatRequest(model, prompt);        
        // call the API
        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the HTTP entity with headers and response body
        System.out.println(response.getChoices().get(0).getMessage().getContent());
        ResponseEntity<String> responseEntity = new ResponseEntity<>(response.getChoices().get(0).getMessage().getContent(), headers, HttpStatus.OK);
        return responseEntity;
    }

}