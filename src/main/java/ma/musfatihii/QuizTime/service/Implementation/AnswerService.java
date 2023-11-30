package ma.musfatihii.QuizTime.service.Implementation;

import ma.musfatihii.QuizTime.exception.AssignmentNotFoundException;
import ma.musfatihii.QuizTime.model.*;
import ma.musfatihii.QuizTime.repository.AnswerRepository;
import ma.musfatihii.QuizTime.repository.QuestionResponseRepository;
import ma.musfatihii.QuizTime.service.Interface.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService implements ServiceInterface<Answer,AnswerCompositeKey,Answer> {
    private final AnswerRepository answerRepository;
    private final QuestionResponseRepository questionResponseRepository;


    @Autowired
    public AnswerService(AnswerRepository answerRepository,QuestionResponseRepository questionResponseRepository)
    {
        this.answerRepository = answerRepository;
        this.questionResponseRepository = questionResponseRepository;
    }


    @Override
    public Optional<Answer> save(Answer answer) {
        //if(!isAssignmentValid(answer.getAnswerCompositeKey().getAssignment().getAssignmentCompositeKey())) throw new AssignmentNotFoundException();
        Optional<QuestionResponse> optionalQuestionResponse = questionResponseRepository.findById(answer.getAnswerCompositeKey().getQuestionResponse().getQuestionResponseCompositeKey());
        if(optionalQuestionResponse.isPresent())
        {
            answer.setScore(optionalQuestionResponse.get().getScore());
            answerRepository.save(answer);
        }
        return Optional.of(answer);
    }

    @Override
    public Optional<Answer> update(Answer answer) {
        return Optional.empty();
    }

    @Override
    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    @Override
    public Optional<Answer> findById(AnswerCompositeKey answerCompositeKey) {
        return Optional.empty();
    }

    @Override
    public boolean delete(AnswerCompositeKey answerCompositeKey) {
        return false;
    }

    public double calculateObtainedScore(AssignmentCompositeKey assignmentCompositeKey)
    {
        return findAll().stream()
                 .filter(answer -> answer.getAnswerCompositeKey().getAssignment().getAssignmentCompositeKey().equals(assignmentCompositeKey))
                 .mapToDouble(Answer::getScore)
                 .sum();

    }

    /*
    private boolean isAssignmentValid(AssignmentCompositeKey assignmentCompositeKey)
    {
        if(assignmentService.findById(assignmentCompositeKey).get().getStartDate().compareTo(LocalDate.now())<=0 &&
           assignmentService.findById(assignmentCompositeKey).get().getEndDate().compareTo(LocalDate.now())>=0){
            return true;
        }
        return false;
    }
     */
}
