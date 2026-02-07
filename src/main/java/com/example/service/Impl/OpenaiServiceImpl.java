package com.example.service.Impl;

import com.example.model.Answer;
import com.example.model.GetCapitalRequest;
import com.example.model.Question;
import com.example.model.Topic;
import com.example.service.OpenaiService;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service

public class OpenaiServiceImpl implements OpenaiService {

    @Value("classpath:templates/myprompt.st")
    private Resource getCapitalPrompt;

    @Value("classpath:templates/myprompt.st")
    private Resource getTopicData;
/*
    @Value("classpath:templates/myprompt.st")
    private Resource getTopic;
*/

    private final ChatModel chatModel;

    public OpenaiServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public String getAnswer(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatModel.call(prompt);
        return response.getResult().getOutput().getText();

    }

    @Override
    public Answer getAnswer(Question question) {
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatModel.call(prompt);
        return new Answer(response.getResult().getOutput().getText());
    }

    @Override
    public Answer getCapital(GetCapitalRequest getCapitalRequest) {
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateorCountry", getCapitalRequest.stateorCountry()));
        ChatResponse response = chatModel.call(prompt);
        return new Answer(response.getResult().getOutput().getText());
    }


    @Override
    public Answer getTopic(Topic topic) {
        PromptTemplate promptTemplate = new PromptTemplate(getTopicData);
        Prompt prompt = promptTemplate.create(Map.of("topic", topic.topic(), "explanation", topic.explanation()));
        ChatResponse response = chatModel.call(prompt);
        return new Answer(response.getResult().getOutput().getText());
    }
}