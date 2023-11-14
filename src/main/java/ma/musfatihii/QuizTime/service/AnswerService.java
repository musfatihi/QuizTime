package ma.musfatihii.QuizTime.service;

import ma.musfatihii.QuizTime.DTO.answers.CreateAnswerRequest;
import ma.musfatihii.QuizTime.model.Answer;
import ma.musfatihii.QuizTime.model.AnswerCompositeKey;
import ma.musfatihii.QuizTime.model.QuestionResponseCompositeKey;
import ma.musfatihii.QuizTime.repository.AnswerRepository;
import ma.musfatihii.QuizTime.repository.QuestionResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionResponseRepository questionResponseRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository)
    {
        this.answerRepository = answerRepository;
    }
    public List<Answer> addNewAnswer(List<AnswerCompositeKey> answerCompositeKeys) {
        int sumScore=0;
        List<Answer> answers = new ArrayList<>();
        answerCompositeKeys.forEach(answerCompositeKey -> {
            Answer answer = new Answer();
            answer.setAnswerCompositeKey(answerCompositeKey);
            /*
            QuestionResponseCompositeKey questionResponseCompositeKey = new QuestionResponseCompositeKey();
            questionResponseCompositeKey.setQuestion(answerCompositeKey.getQuestionResponse().getQuestionResponseCompositeKey().getQuestion());
            questionResponseCompositeKey.setResponse(answerCompositeKey.getQuestionResponse().getQuestionResponseCompositeKey().getResponse());
            answer.setScore(questionResponseRepository.findById(questionResponseCompositeKey).get().getScore());
            */
            answers.add(answer);
            answerRepository.save(answer);
        });

        return answers;
    }

}
