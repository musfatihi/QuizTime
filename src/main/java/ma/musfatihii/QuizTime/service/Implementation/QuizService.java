package ma.musfatihii.QuizTime.service.Implementation;

import ma.musfatihii.QuizTime.DTO.quiz.QuizResp;
import ma.musfatihii.QuizTime.exception.QuizNotCreatedException;
import ma.musfatihii.QuizTime.exception.QuizNotFoundException;
import ma.musfatihii.QuizTime.model.Quiz;
import ma.musfatihii.QuizTime.repository.QuizRepository;
import ma.musfatihii.QuizTime.service.Interface.ServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService implements ServiceInterface<Quiz,Long,QuizResp> {
    private QuizRepository quizRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    public QuizService(QuizRepository quizRepository)
    {
        this.quizRepository = quizRepository;
    }


    @Override
    public Optional<QuizResp> save(Quiz quiz) {
        Quiz savedQuiz;
        try {
            savedQuiz = quizRepository.save(quiz);
        }catch (Exception ex){
            throw new QuizNotCreatedException();
        }
        return Optional.of(modelMapper.map(savedQuiz, QuizResp.class));
    }

    @Override
    public Optional<QuizResp> update(Quiz quiz) {
        return Optional.empty();
    }

    @Override
    public List<QuizResp> findAll() {
        return List.of(modelMapper.map(quizRepository.findAll(),QuizResp[].class));
    }

    @Override
    public Optional<QuizResp> findById(Long id) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(id);
        if(optionalQuiz.isEmpty()) throw new QuizNotFoundException(id);
        return Optional.of(modelMapper.map(optionalQuiz.get(), QuizResp.class));
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

}
