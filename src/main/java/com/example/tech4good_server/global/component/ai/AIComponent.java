package com.example.tech4good_server.global.component.ai;

import com.example.tech4good_server.domain.question.repository.AnswerRepository;
import com.example.tech4good_server.global.model.entity.Answer;
import com.example.tech4good_server.global.model.entity.Question;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Component
public class AIComponent {
    private final AnswerRepository answerRepository;
    private final ObjectMapper objectMapper;

    @Value("${ai-server.python.chat-url}")
    String chat_url;

    @Async
    public CompletableFuture<Void> sendQuestionToAI(Question question) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> body = new HashMap<>();
            body.put("question", question.getQuestion());

            HttpEntity<Map<String, String>> requestMessage = new HttpEntity<>(body, httpHeaders);
            ResponseEntity<String> response = restTemplate.postForEntity(chat_url, requestMessage, String.class);

            Map<String, String> responseBody = objectMapper.readValue(response.getBody(), Map.class);
            String answer = responseBody.get("answer");

            Answer answerEntity = Answer.builder()
                    .answer(answer)
                    .questionSeq(question.getQuestionSeq())
                    .userSeq(0)
                    .build();

            answerRepository.save(answerEntity);
            log.info("AI Answer Saved: " + answer);
        } catch (Exception e) {
            log.error("Failed to process AI response", e);
        }
        return CompletableFuture.completedFuture(null);
    }
}

