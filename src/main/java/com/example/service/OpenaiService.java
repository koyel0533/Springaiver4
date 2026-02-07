package com.example.service;

import com.example.model.Answer;
import com.example.model.GetCapitalRequest;
import com.example.model.Question;
import com.example.model.Topic;

public interface OpenaiService {

    public String getAnswer(String question);
    public Answer getAnswer(Question question);
    public Answer getCapital(GetCapitalRequest getCapitalRequest);
   public Answer getTopic(Topic topic);

}
