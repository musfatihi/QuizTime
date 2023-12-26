package ma.musfatihii.QuizTime.service.Implementation;

import ma.musfatihii.QuizTime.dto.answers.AnswersReq;
import ma.musfatihii.QuizTime.dto.answers.AnswersResp;
import ma.musfatihii.QuizTime.exception.InfosNotCorrectException;
import ma.musfatihii.QuizTime.model.*;
import ma.musfatihii.QuizTime.repository.AnswerRepository;
import ma.musfatihii.QuizTime.repository.QuestionResponseRepository;
import ma.musfatihii.QuizTime.service.Interface.ServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService implements ServiceInterface<AnswersReq,AnswerCompositeKey, AnswersResp> {
    private final AnswerRepository answerRepository;
    private final QuestionResponseRepository questionResponseRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AnswerService(AnswerRepository answerRepository,
                         QuestionResponseRepository questionResponseRepository,
                         ModelMapper modelMapper)
    {
        this.answerRepository = answerRepository;
        this.questionResponseRepository = questionResponseRepository;
        this.modelMapper = modelMapper;
    }



    public double calculateObtainedScore(AssignmentCompositeKey assignmentCompositeKey)
    {
        return   findAllAnswers().stream()
                 .filter(answer -> answer.getAnswerCompositeKey().getAssignment().getAssignmentCompositeKey().equals(assignmentCompositeKey))
                 .mapToDouble(Answer::getScore)
                 .sum();

    }

    @Override
    public Optional<AnswersResp> save(AnswersReq answersReq) {

        if(!isAnswerValid(answersReq)){throw new InfosNotCorrectException("Ce Quiz est expiré");}

        List<Answer> answers = List.of(modelMapper.map(answersReq.getAnswers(),Answer[].class));

        for(Answer answer : answers){
            answer.setScore(0);
        }

        answerRepository.saveAll(answers);

        return Optional.empty();
    }

    @Override
    public Optional<AnswersResp> update(AnswersReq answersReq) {
        return Optional.empty();
    }

    @Override
    public List<AnswersResp> findAll() {
        return null;
    }

    private List<Answer> findAllAnswers(){
        return answerRepository.findAll();
    }

    @Override
    public Optional<AnswersResp> findById(AnswerCompositeKey answerCompositeKey) {
        return Optional.empty();
    }

    @Override
    public boolean delete(AnswerCompositeKey answerCompositeKey) {
        return false;
    }

    private boolean isAnswerValid(AnswersReq answersReq){
        return LocalDate.now().isAfter(answersReq.getAnswers().get(0).getAssignment().getStartDate()) &&
               LocalDate.now().isBefore(answersReq.getAnswers().get(0).getAssignment().getEndDate());
    }
}
