package com.example.tech4good_server.domain.question.service;

import com.example.tech4good_server.global.component.ai.AIComponent;
import com.example.tech4good_server.domain.auth.repository.UserRepository;
import com.example.tech4good_server.domain.question.mapper.QuestionRequestMapper;
import com.example.tech4good_server.domain.question.model.request.QuestionRequest;
import com.example.tech4good_server.domain.question.model.request.QuestionSearchRequest;
import com.example.tech4good_server.domain.question.model.response.QuestionDetailResponse;
import com.example.tech4good_server.domain.question.model.response.QuestionListResponse;
import com.example.tech4good_server.domain.question.model.response.QuestionResponse;
import com.example.tech4good_server.domain.question.repository.AnswerRepository;
import com.example.tech4good_server.domain.question.repository.QuestionQueryRepository;
import com.example.tech4good_server.domain.question.repository.QuestionRepository;
import com.example.tech4good_server.domain.question.repository.UserQueryRepository;
import com.example.tech4good_server.global.mapper.AnswerMapper;
import com.example.tech4good_server.global.mapper.QuestionMapper;
import com.example.tech4good_server.global.model.entity.Answer;
import com.example.tech4good_server.global.model.entity.Question;
import com.example.tech4good_server.global.model.vo.AnswerVo;
import com.example.tech4good_server.global.model.vo.QuestionVo;
import com.example.tech4good_server.global.model.vo.UserProfileVo;
import com.example.tech4good_server.global.security.LoginManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRequestMapper questionRequestMapper;
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final UserQueryRepository userQueryRepository;
    private final QuestionQueryRepository questionQueryRepository;
    private final AIComponent aiComponent;


    public QuestionResponse question(QuestionRequest request) throws JsonProcessingException {
        QuestionResponse questionResponse = new QuestionResponse();
        Question questionRequestMapperEntity = questionRequestMapper.toEntity(request);
        Question question = questionRepository.save(questionRequestMapperEntity);

        aiComponent.sendQuestionToAI(question);

        QuestionVo questionVo = questionMapper.toDto(question);
        questionVo.setName(Objects.requireNonNull(LoginManager.getUserDetails()).getName());
        questionResponse.setQuestion(questionVo);

        return questionResponse;
    }

    public QuestionListResponse getQuestionList(QuestionSearchRequest questionSearchRequest) {
        QuestionListResponse questionListResponse = new QuestionListResponse();

        List<Question> questionList = questionQueryRepository.getQuestionList(questionSearchRequest);
        questionListResponse.setQuestion(questionMapper.toDto(questionList));

        return questionListResponse;
    }

    public QuestionDetailResponse getQuestionDetail(Integer questionSeq) {
        QuestionDetailResponse questionDetailResponse = new QuestionDetailResponse();
        Question question = questionRepository.findQuestionByQuestionSeq(questionSeq);

        QuestionVo questionVo = questionMapper.toDto(question);
        questionVo.setName(userRepository.findUserInfoByUserSeq(question.getUserSeq()).getName());
        questionDetailResponse.setQuestion(questionVo);

        List<Answer> answerList = answerRepository.findAnswerByQuestionSeq(questionSeq);

        List<Integer> userSeqs = answerList.stream()
                .map(Answer::getUserSeq)
                .toList();
        List<UserProfileVo> userProfileVos = userQueryRepository.findUserNamesByUserSeqIn(userSeqs);

        List<AnswerVo> answerVoList = answerMapper.toDto(answerList);
        answerVoList.forEach(answerVo -> userProfileVos.stream().filter(userProfileVo -> answerVo.getUserSeq().equals(userProfileVo.getUserSeq()))
                .forEach(userProfileVo -> answerVo.setName(userProfileVo.getName())));
        answerVoList.stream().filter(answerVo -> answerVo.getUserSeq().equals(0))
                        .forEach(answerVo -> answerVo.setName("AI 챗봇"));

        questionDetailResponse.setAnswerList(answerVoList);

        return questionDetailResponse;
    }

}
