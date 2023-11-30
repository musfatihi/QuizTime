package ma.musfatihii.QuizTime.service.Implementation;

import ma.musfatihii.QuizTime.exception.QuestionsQuizInfosNotCorrectException;
import ma.musfatihii.QuizTime.exception.QuestionsQuizNotCreatedException;
import ma.musfatihii.QuizTime.model.QuestionQuiz;
import ma.musfatihii.QuizTime.repository.QuestionQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionQuizService {
    private QuestionQuizRepository questionQuizRepository;

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    public QuestionQuizService(QuestionQuizRepository questionQuizRepository)
    {
        this.questionQuizRepository = questionQuizRepository;
    }
    public void addNewQuestionsQuiz(List<QuestionQuiz> questionsQuiz) {
        if(!isAllQuestionsValid(questionsQuiz) || !isAllDurationsValid(questionsQuiz) || !isQuizValid(questionsQuiz)){throw new QuestionsQuizInfosNotCorrectException();}
        try{
            questionQuizRepository.saveAll(questionsQuiz);
        }catch(Exception ex){
            throw new QuestionsQuizNotCreatedException();
        }
    }

    private boolean isAllQuestionsValid(List<QuestionQuiz> questionsQuiz)
    {
        for (QuestionQuiz questionQuiz : questionsQuiz) {
            if(!questionService.findById(questionQuiz.getQuestionQuizCompositeKey().getQuestion().getId()).isPresent()) {return false;}
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
        return quizService.findById(questionsQuiz.get(0).getQuestionQuizCompositeKey().getQuiz().getId()).isPresent();
    }
}
