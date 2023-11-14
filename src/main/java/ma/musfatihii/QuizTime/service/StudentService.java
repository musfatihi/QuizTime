package ma.musfatihii.QuizTime.service;

import ma.musfatihii.QuizTime.model.Student;
import ma.musfatihii.QuizTime.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }
    public void addNewStudent(Student student) {
        studentRepository.save(student);
    }

    public Optional<Student> getStudent(Long id)
    {
        return studentRepository.findById(id);
    }
}
