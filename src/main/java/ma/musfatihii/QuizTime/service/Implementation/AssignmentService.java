package ma.musfatihii.QuizTime.service.Implementation;


import ma.musfatihii.QuizTime.dto.assignment.AssignmentResp;
import ma.musfatihii.QuizTime.exception.AssignmentInfosNotCorrectException;
import ma.musfatihii.QuizTime.exception.AssignmentNotCreatedException;
import ma.musfatihii.QuizTime.exception.AssignmentNotFoundException;
import ma.musfatihii.QuizTime.model.Assignment;
import ma.musfatihii.QuizTime.model.AssignmentCompositeKey;
import ma.musfatihii.QuizTime.repository.AssignmentRepository;
import ma.musfatihii.QuizTime.service.Interface.ServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService implements ServiceInterface<Assignment,AssignmentCompositeKey, AssignmentResp> {
    private AssignmentRepository assignmentRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository)
    {
        this.assignmentRepository = assignmentRepository;

    }

    private boolean isQuizValid(Assignment assignment)
    {
        return quizService.findById(assignment.getAssignmentCompositeKey().getQuiz().getId()).isPresent();
    }

    private boolean isStudentValid(Assignment assignment)
    {
        return studentService.findById(assignment.getAssignmentCompositeKey().getStudent().getId()).isPresent();
    }

    private boolean isAttemptNbrValid(Assignment assignment)
    {
        return assignment.getAssignmentCompositeKey().getAttempt()<=quizService.findById(assignment.getAssignmentCompositeKey().getQuiz().getId()).get().getMaxAttempts();
    }

    private boolean isDatesValid(Assignment assignment)
    {
        return assignment.getStartDate().compareTo(assignment.getEndDate())<=0;
    }

    private void evaluateAssignment(AssignmentCompositeKey assignmentCompositeKey)
    {
        Assignment assignment = assignmentRepository.findById(assignmentCompositeKey).get();
        double obtainedScore = answerService.calculateObtainedScore(assignmentCompositeKey);
        assignment.setObtainedScore(obtainedScore);
        assignment.setPassed(isAssignmentPassed(assignmentCompositeKey,obtainedScore));
        assignmentRepository.save(assignment);
    }


    @Override
    public Optional<AssignmentResp> save(Assignment assignment) {
        if(!isQuizValid(assignment) || !isStudentValid(assignment) || !isAttemptNbrValid(assignment) || !isDatesValid(assignment))
        {
            throw new AssignmentInfosNotCorrectException();
        }
        Assignment savedAssignment;
        try
        {savedAssignment = assignmentRepository.save(assignment);}
        catch (Exception ex) {throw new AssignmentNotCreatedException();}
        return Optional.of(modelMapper.map(savedAssignment,AssignmentResp.class));
    }

    @Override
    public Optional<AssignmentResp> update(Assignment assignment) {
        return Optional.empty();
    }

    @Override
    public List<AssignmentResp> findAll() {
        return List.of(modelMapper.map(assignmentRepository.findAll(),AssignmentResp[].class));
    }

    @Override
    public Optional<AssignmentResp> findById(AssignmentCompositeKey assignmentCompositeKey) {
        Optional<Assignment> assignmentOptional = assignmentRepository.findById(assignmentCompositeKey);
        if(assignmentOptional.isEmpty()) throw new AssignmentNotFoundException();
        return Optional.of(modelMapper.map(assignmentOptional.get(),AssignmentResp.class));
    }

    @Override
    public boolean delete(AssignmentCompositeKey assignmentCompositeKey) {
        return false;
    }

    private boolean isAssignmentPassed(AssignmentCompositeKey assignmentCompositeKey,double obtainedScore)
    {
        return quizService.findById(assignmentCompositeKey.getQuiz().getId()).get().getSuccessScore()<=obtainedScore;
    }


}
