package ma.musfatihii.QuizTime.service;


import ma.musfatihii.QuizTime.exception.AssignmentInfosNotCorrectException;
import ma.musfatihii.QuizTime.exception.AssignmentNotCreatedException;
import ma.musfatihii.QuizTime.model.Assignment;
import ma.musfatihii.QuizTime.model.QuestionQuiz;
import ma.musfatihii.QuizTime.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {
    private AssignmentRepository assignmentRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private QuizService quizService;

    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository)
    {
        this.assignmentRepository = assignmentRepository;
    }
    public void addNewAssignment(Assignment assignment) {

        if(!isQuizValid(assignment) || !isStudentValid(assignment) || !isAttemptNbrValid(assignment) || !isDatesValid(assignment))
        {
            throw new AssignmentInfosNotCorrectException();
        }

        try
        {assignmentRepository.save(assignment);}
        catch (Exception ex) {throw new AssignmentNotCreatedException();}
    }

    private boolean isQuizValid(Assignment assignment)
    {
        return quizService.getQuiz(assignment.getAssignmentCompositeKey().getQuiz().getId()).isPresent();
    }

    private boolean isStudentValid(Assignment assignment)
    {
        return studentService.getStudent(assignment.getAssignmentCompositeKey().getStudent().getId()).isPresent();
    }

    private boolean isAttemptNbrValid(Assignment assignment)
    {
        return assignment.getAssignmentCompositeKey().getAttempt()<=quizService.getQuiz(assignment.getAssignmentCompositeKey().getQuiz().getId()).get().getMaxAttempts();
    }

    private boolean isDatesValid(Assignment assignment)
    {
        return assignment.getStartDate().compareTo(assignment.getEndDate())>=0;
    }


}
