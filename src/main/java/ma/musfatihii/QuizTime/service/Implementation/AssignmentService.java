package ma.musfatihii.QuizTime.service.Implementation;


import ma.musfatihii.QuizTime.dto.assignment.AssignmentReq;
import ma.musfatihii.QuizTime.dto.assignment.AssignmentResp;
import ma.musfatihii.QuizTime.exception.InfosNotCorrectException;
import ma.musfatihii.QuizTime.exception.NotFoundException;
import ma.musfatihii.QuizTime.model.Assignment;
import ma.musfatihii.QuizTime.model.AssignmentCompositeKey;
import ma.musfatihii.QuizTime.repository.AssignmentRepository;
import ma.musfatihii.QuizTime.repository.QuizRepository;
import ma.musfatihii.QuizTime.repository.StudentRepository;
import ma.musfatihii.QuizTime.service.Interface.ServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService implements ServiceInterface<AssignmentReq,AssignmentCompositeKey, AssignmentResp> {
    private final AssignmentRepository assignmentRepository;
    private final StudentRepository studentRepository;
    private final QuizRepository quizRepository;
    private final AnswerService answerService;
    private final ModelMapper modelMapper;


    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository,
                             StudentRepository studentRepository,
                             QuizRepository quizRepository,
                             AnswerService answerService,
                             ModelMapper modelMapper) {
        this.assignmentRepository = assignmentRepository;
        this.studentRepository = studentRepository;
        this.quizRepository = quizRepository;
        this.answerService = answerService;
        this.modelMapper = modelMapper;
    }


    private void evaluateAssignment(AssignmentCompositeKey assignmentCompositeKey)
    {
        Assignment assignment = assignmentRepository.findById(assignmentCompositeKey)
                .orElseThrow(()->new NotFoundException("Affectation introuvable"));

        double obtainedScore = answerService.calculateObtainedScore(assignmentCompositeKey);

        assignment.setObtainedScore(obtainedScore);
        assignment.setPassed(isAssignmentPassed(assignmentCompositeKey,obtainedScore));
        assignmentRepository.save(assignment);
    }

    @Override
    public Optional<AssignmentResp> save(AssignmentReq assignmentReq) {

        if(!isDatesValid(assignmentReq)
            || !isQuizValid(assignmentReq)
            || !isStudentValid(assignmentReq)
            || !isAttemptNbrValid(assignmentReq)
            )
        {throw new InfosNotCorrectException("Infos Affectation incorrectes");}

        return Optional.of(
                modelMapper.map(
                        assignmentRepository.save(modelMapper.map(assignmentReq,Assignment.class))
                        ,AssignmentResp.class
                )
        );
    }

    @Override
    public Optional<AssignmentResp> update(AssignmentReq assignmentReq) {
        return Optional.empty();
    }

    @Override
    public List<AssignmentResp> findAll() {
        return List.of(
                modelMapper.map(assignmentRepository.findAll(),AssignmentResp[].class)
        );
    }

    @Override
    public Optional<AssignmentResp> findById(AssignmentCompositeKey assignmentCompositeKey) {

        Assignment foundAssignment = assignmentRepository.findById(assignmentCompositeKey)
                .orElseThrow(()->new NotFoundException("Affectation introuvable"));

        return Optional.of(modelMapper.map(foundAssignment,AssignmentResp.class));
    }

    @Override
    public boolean delete(AssignmentCompositeKey assignmentCompositeKey) {
        return false;
    }

    private boolean isAssignmentPassed(AssignmentCompositeKey assignmentCompositeKey,double obtainedScore)
    {
        return quizRepository.findById(assignmentCompositeKey.getQuiz().getId()).get().getSuccessScore()<=obtainedScore;
    }

    private boolean isQuizValid(AssignmentReq assignmentReq)
    {
        return quizRepository.findById(assignmentReq.getAssignmentCompositeKey().getQuiz().getId()).isPresent();
    }

    private boolean isStudentValid(AssignmentReq assignmentReq)
    {
        return studentRepository.findById(assignmentReq.getAssignmentCompositeKey().getStudent().getId()).isPresent();
    }

    private boolean isAttemptNbrValid(AssignmentReq assignmentReq)
    {
        return assignmentReq.getAssignmentCompositeKey().getAttempt()<=quizRepository.findById(assignmentReq.getAssignmentCompositeKey().getQuiz().getId()).get().getMaxAttempts();
    }

    private boolean isDatesValid(AssignmentReq assignmentReq)
    {
        return (assignmentReq.getStartDate().isBefore(assignmentReq.getEndDate()) &&
                LocalDate.now().isBefore(assignmentReq.getEndDate()));
    }
}
