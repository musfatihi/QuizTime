package ma.musfatihii.QuizTime.service.Implementation;

import ma.musfatihii.QuizTime.dto.quiz.QuizReq;
import ma.musfatihii.QuizTime.dto.quiz.QuizResp;
import ma.musfatihii.QuizTime.exception.InfosNotCorrectException;
import ma.musfatihii.QuizTime.exception.NotFoundException;
import ma.musfatihii.QuizTime.model.Quiz;
import ma.musfatihii.QuizTime.repository.InstructorRepository;
import ma.musfatihii.QuizTime.repository.QuizRepository;
import ma.musfatihii.QuizTime.service.Interface.ServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService implements ServiceInterface<QuizReq,Long,QuizResp> {
    private final QuizRepository quizRepository;
    private final InstructorRepository instructorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public QuizService(QuizRepository quizRepository,
                       ModelMapper modelMapper,
                       InstructorRepository instructorRepository) {
        this.quizRepository = quizRepository;
        this.modelMapper = modelMapper;
        this.instructorRepository = instructorRepository;
    }


    @Override
    public Optional<QuizResp> save(QuizReq quizReq) {
        if(!isInstructorValid(quizReq)){throw new InfosNotCorrectException("Infos Quiz incorrectes");}
        return Optional.of(
                modelMapper.map(
                        quizRepository.save(modelMapper.map(quizReq,Quiz.class))
                        ,QuizResp.class
                )
        );
    }

    @Override
    public Optional<QuizResp> update(QuizReq quizReq) {
        return Optional.empty();
    }

    @Override
    public List<QuizResp> findAll() {
        return List.of(modelMapper.map(quizRepository.findAll(),QuizResp[].class));
    }

    @Override
    public Optional<QuizResp> findById(Long id) {
        Quiz foundQuiz = quizRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Quiz introuvable"));

        return Optional.of(modelMapper.map(foundQuiz, QuizResp.class));
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    private boolean isInstructorValid(QuizReq quizReq) {
        return instructorRepository.existsById(quizReq.getInstructor().getId());
    }

}
