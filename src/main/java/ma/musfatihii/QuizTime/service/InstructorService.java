package ma.musfatihii.QuizTime.service;

import ma.musfatihii.QuizTime.model.Instructor;
import ma.musfatihii.QuizTime.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository)
    {
        this.instructorRepository = instructorRepository;
    }
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Optional<Instructor> addNewInstructor(Instructor instructor) {
        instructorRepository.save(instructor);
        return Optional.of(instructor);
    }
}
