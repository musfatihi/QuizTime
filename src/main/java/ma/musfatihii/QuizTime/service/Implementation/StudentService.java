package ma.musfatihii.QuizTime.service.Implementation;

import ma.musfatihii.QuizTime.dto.student.StudentReq;
import ma.musfatihii.QuizTime.dto.student.StudentResp;
import ma.musfatihii.QuizTime.exception.NotFoundException;
import ma.musfatihii.QuizTime.model.Student;
import ma.musfatihii.QuizTime.repository.StudentRepository;
import ma.musfatihii.QuizTime.service.Interface.ServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements ServiceInterface<StudentReq,Long,StudentResp> {
    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository,
                          ModelMapper modelMapper)
    {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<StudentResp> save(StudentReq studentReq) {

        return Optional.of(
                modelMapper.map(
                        studentRepository.save(modelMapper.map(studentReq,Student.class))
                        ,StudentResp.class
                )
        );
    }

    @Override
    public Optional<StudentResp> update(StudentReq studentReq) {
        return Optional.empty();
    }

    @Override
    public List<StudentResp> findAll() {
        return List.of(
                modelMapper.map(studentRepository.findAll(),StudentResp[].class)
        );
    }

    @Override
    public Optional<StudentResp> findById(Long id) {
        Student foundStudent = studentRepository.findById(id)
                                .orElseThrow(()->new NotFoundException("Etudiant introuvable"));
        return Optional.of(
                modelMapper.map(foundStudent,StudentResp.class)
        );
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
