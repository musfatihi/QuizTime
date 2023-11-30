package ma.musfatihii.QuizTime.service.Implementation;

import ma.musfatihii.QuizTime.DTO.question.QuestionResp;
import ma.musfatihii.QuizTime.exception.LevelNotFoundException;
import ma.musfatihii.QuizTime.exception.QuestionInfosNotCorrectException;
import ma.musfatihii.QuizTime.exception.QuestionNotCreated;
import ma.musfatihii.QuizTime.exception.QuestionNotFoundException;
import ma.musfatihii.QuizTime.model.Media;
import ma.musfatihii.QuizTime.model.Question;
import ma.musfatihii.QuizTime.model.QuestionType;
import ma.musfatihii.QuizTime.repository.QuestionRepository;
import ma.musfatihii.QuizTime.service.Interface.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService implements ServiceInterface<Question,Long,QuestionResp> {

    private QuestionRepository questionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LevelService levelService;

    @Autowired
    private MediaService mediaService;

    @Autowired
    public QuestionService(QuestionRepository questionRepository)
    {
        this.questionRepository = questionRepository;
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
        if(levelService.findById(question.getLevel().getId()).isPresent())
        {
            if (question.getMaxScore() >= levelService.findById(question.getLevel().getId()).get().getMinScore() &&
                    question.getMaxScore() <= levelService.findById(question.getLevel().getId()).get().getMaxScore()) {
                    return true;
            }
        }
        else
        {
            throw new LevelNotFoundException(question.getLevel().getId());
        }

        return false;
    }

    @Override
    public Optional<QuestionResp> save(Question question) {
        if(!isNbrResponsesValid(question) || !isRespsCorrectRespsValid(question)
                || !isQuestionTypeNbrCorrectResponsesValid(question) || !isMaxScoreValid(question))
        {
            throw new QuestionInfosNotCorrectException();
        }
        try {return Optional.of(modelMapper.map(questionRepository.save(question),QuestionResp.class));}
        catch (Exception e) {throw new QuestionNotCreated();}
    }

    @Override
    public Optional<QuestionResp> update(Question question) {
        return Optional.empty();
    }

    @Override
    public List<QuestionResp> findAll() {
        return List.of(modelMapper.map(questionRepository.findAll(),QuestionResp[].class));
    }

    @Override
    public Optional<QuestionResp> findById(Long id) {
        Optional<Question> questionOptional = questionRepository.findById(id);
        if(questionOptional.isPresent()) {return Optional.of(modelMapper.map(questionOptional.get(),QuestionResp.class));}
        throw new QuestionNotFoundException(id);
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
