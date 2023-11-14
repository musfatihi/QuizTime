package ma.musfatihii.QuizTime.service;

import ma.musfatihii.QuizTime.exception.LevelNotFoundException;
import ma.musfatihii.QuizTime.exception.QuestionInfosNotCorrectException;
import ma.musfatihii.QuizTime.exception.QuestionNotCreated;
import ma.musfatihii.QuizTime.model.Question;
import ma.musfatihii.QuizTime.model.QuestionType;
import ma.musfatihii.QuizTime.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;

    @Autowired
    private LevelService levelService;

    public QuestionService(QuestionRepository questionRepository)
    {
        this.questionRepository = questionRepository;
    }
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestion(Long id) {
        return questionRepository.findById(id);
    }

    public void addNewQuestion(Question question) {
        if(!isNbrResponsesValid(question) || !isRespsCorrectRespsValid(question)
        || !isQuestionTypeNbrCorrectResponsesValid(question) || !isMaxScoreValid(question))
        {
            throw new QuestionInfosNotCorrectException();
        }
        try {questionRepository.save(question);}
        catch (Exception e) {throw new QuestionNotCreated();}
    }

    private boolean isNbrResponsesValid(Question question)
    {return question.getNbrResponses()>=2;}

    private boolean isRespsCorrectRespsValid(Question question)
    {return question.getNbrResponses()>=question.getNbrCorrectResponses();}

    private boolean isQuestionTypeNbrCorrectResponsesValid(Question question)
    {
        if(question.getNbrCorrectResponses()>1 && question.getType().name().equals(QuestionType.ManyTrueResponses.name())
        || question.getNbrCorrectResponses()==1 && question.getType().name().equals(QuestionType.OneTrueResponse.name()))
        {return true;}

        return false;
    }

    private boolean isMaxScoreValid(Question question) {
        if(levelService.getLevel(question.getLevel().getId()).isPresent())
        {
            if (question.getMaxScore() >= levelService.getLevel(question.getLevel().getId()).get().getMinScore() &&
                    question.getMaxScore() <= levelService.getLevel(question.getLevel().getId()).get().getMaxScore()) {
                    return true;
            }
        }
        else
        {
            throw new LevelNotFoundException(question.getLevel().getId());
        }

        return false;
    }
}
