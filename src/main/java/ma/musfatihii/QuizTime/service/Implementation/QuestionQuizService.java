package ma.musfatihii.QuizTime.service.Implementation;

import ma.musfatihii.QuizTime.dto.questionsQuiz.QuestionQuizResp;
import ma.musfatihii.QuizTime.dto.questionsQuiz.QuestionsQuizReq;
import ma.musfatihii.QuizTime.dto.questionsQuiz.QuestionsQuizResp;
import ma.musfatihii.QuizTime.exception.InfosNotCorrectException;
import ma.musfatihii.QuizTime.model.QuestionQuiz;
import ma.musfatihii.QuizTime.model.QuestionQuizCompositeKey;
import ma.musfatihii.QuizTime.repository.QuestionQuizRepository;
import ma.musfatihii.QuizTime.repository.QuestionRepository;
import ma.musfatihii.QuizTime.repository.QuizRepository;
import ma.musfatihii.QuizTime.service.Interface.ServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionQuizService implements ServiceInterface<QuestionsQuizReq, QuestionQuizCompositeKey, QuestionsQuizResp> {
    private final QuestionQuizRepository questionQuizRepository;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public QuestionQuizService(QuestionQuizRepository questionQuizRepository,
                               QuizRepository quizRepository,
                               QuestionRepository questionRepository,
                               ModelMapper modelMapper) {
        this.questionQuizRepository = questionQuizRepository;
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<QuestionsQuizResp> save(QuestionsQuizReq questionsQuizReq) {

        if(!isAllQuestionsValid(questionsQuizReq.getQuestionsQuiz())
           || !isAllDurationsValid(questionsQuizReq.getQuestionsQuiz())
           || !isQuizValid(questionsQuizReq.getQuestionsQuiz()))
        {throw new InfosNotCorrectException("Infos Questions Quiz incorrectes");}

        QuestionsQuizResp questionsQuizResp = new QuestionsQuizResp();

        questionsQuizResp.setQuestionsQuiz(
                List.of(
                        modelMapper.map(questionQuizRepository.saveAll(questionsQuizReq.getQuestionsQuiz()),
                                        QuestionQuizResp[].class
                                        )
                        )
        );

        return Optional.of(
                questionsQuizResp
        );
    }

    @Override
    public Optional<QuestionsQuizResp> update(QuestionsQuizReq questionsQuizReq) {
        return Optional.empty();
    }

    @Override
    public List<QuestionsQuizResp> findAll() {
        return null;
    }

    @Override
    public Optional<QuestionsQuizResp> findById(QuestionQuizCompositeKey questionQuizCompositeKey) {
        return Optional.empty();
    }

    @Override
    public boolean delete(QuestionQuizCompositeKey questionQuizCompositeKey) {
        return false;
    }

    private boolean isAllQuestionsValid(List<QuestionQuiz> questionsQuiz)
    {
        for (QuestionQuiz questionQuiz : questionsQuiz) {
            if(questionRepository.findById(questionQuiz.getQuestionQuizCompositeKey().getQuestion().getId()).isEmpty()) {return false;}
        }
        return true;
    }

    private boolean isAllDurationsValid(List<QuestionQuiz> questionsQuiz)
    {
        for (QuestionQuiz questionQuiz : questionsQuiz) {
            if(questionQuiz.getTemporized()<0){return false;}
        }
        return true;
    }

    private boolean isQuizValid(List<QuestionQuiz> questionsQuiz)
    {
        return quizRepository.findById(questionsQuiz.get(0).getQuestionQuizCompositeKey().getQuiz().getId()).isPresent();
    }
}
