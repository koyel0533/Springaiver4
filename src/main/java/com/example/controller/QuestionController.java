package com.example.controller;


import com.example.model.Answer;
import com.example.model.GetCapitalRequest;
import com.example.model.Question;
import com.example.model.Topic;
import com.example.service.Impl.OpenaiServiceImpl;
import com.example.service.OpenaiService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api")
@AllArgsConstructor
public class QuestionController {
   private final OpenaiService openaiService;

    @PostMapping
    public ResponseEntity<Answer> getAnswer(@RequestBody Question question){
        return new ResponseEntity<>(openaiService.getAnswer(question), HttpStatus.CREATED);
    }

    @PostMapping("/capital")
    public Answer getCapital(@RequestBody GetCapitalRequest getCapitalRequest){
        return openaiService.getCapital(getCapitalRequest);
    }
    @PostMapping("/topic")
    public Answer getCapital(@RequestBody Topic topic){
        return openaiService.getTopic(topic);
    }


    @GetMapping("/topic-ai")
    public String showForm(Model model) {
        model.addAttribute("topic", "");
        model.addAttribute("explanation", "");
        return "topic";
    }

    @PostMapping("/topic-ai")
    public String submitForm(
            @RequestParam("topic") String topic,
            @RequestParam("explanation") String explanation,
            Model model) {

        Topic t = new Topic(topic, explanation);
        Answer aianswer = openaiService.getTopic(t);

        model.addAttribute("topic", topic);
        model.addAttribute("explanation", explanation);
        model.addAttribute("airesult", aianswer.answer());

        return "topic";}

}
