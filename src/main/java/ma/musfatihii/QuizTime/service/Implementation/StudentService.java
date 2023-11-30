package ma.musfatihii.QuizTime.service.Implementation;

import ma.musfatihii.QuizTime.DTO.student.StudentResp;
import ma.musfatihii.QuizTime.exception.QuestionNotFoundException;
import ma.musfatihii.QuizTime.exception.StudentNotCreatedException;
import ma.musfatihii.QuizTime.exception.StudentNotFoundException;
import ma.musfatihii.QuizTime.model.Student;
import ma.musfatihii.QuizTime.repository.StudentRepository;
import ma.musfatihii.QuizTime.service.Interface.ServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements ServiceInterface<Student,Long,StudentResp> {
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }


    @Override
    public Optional<StudentResp> save(Student student) {
        Student savedStudent;
        try {
            savedStudent = studentRepository.save(student);
        }catch (Exception ex)
        {
            throw new StudentNotCreatedException();
        }

        return Optional.of(modelMapper.map(savedStudent,StudentResp.class));
    }

    @Override
    public Optional<StudentResp> update(Student student) {
        return Optional.empty();
    }

    @Override
    public List<StudentResp> findAll() {
        return List.of(modelMapper.map(studentRepository.findAll(),StudentResp[].class));
    }

    @Override
    public Optional<StudentResp> findById(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isEmpty()) throw new StudentNotFoundException(id);
        return Optional.of(modelMapper.map(studentOptional.get(),StudentResp.class));
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
