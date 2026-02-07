package com.example;

import com.example.service.OpenaiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springaiver4ApplicationTests {
    @Autowired
  private  OpenaiService openaiService;



    @Test
    void getAnswer() {
       String op= openaiService.getAnswer("what is ai?");
        System.out.println("This is response"+op);

    }

}
