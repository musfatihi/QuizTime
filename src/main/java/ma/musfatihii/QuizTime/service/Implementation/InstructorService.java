package ma.musfatihii.QuizTime.service.Implementation;

import ma.musfatihii.QuizTime.dto.instructor.InstructorReq;
import ma.musfatihii.QuizTime.dto.instructor.InstructorResp;
import ma.musfatihii.QuizTime.model.Instructor;
import ma.musfatihii.QuizTime.repository.InstructorRepository;
import ma.musfatihii.QuizTime.service.Interface.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


import java.util.List;
import java.util.Optional;

@Service
public class InstructorService implements ServiceInterface<InstructorReq,Long, InstructorResp> {

    private final InstructorRepository instructorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository,
                             ModelMapper modelMapper)
    {
        this.instructorRepository = instructorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<InstructorResp> save(InstructorReq instructorReq) {
        return Optional.of(
                modelMapper.map(
                        instructorRepository.save(
                                modelMapper.map(instructorReq,Instructor.class)
                        )
                        ,InstructorResp.class)
        );
    }

    @Override
    public Optional<InstructorResp> update(InstructorReq instructorReq) {
        return Optional.empty();
    }

    @Override
    public List<InstructorResp> findAll() {
        return List.of(
                modelMapper.map(instructorRepository.findAll(),InstructorResp[].class)
        );
    }

    @Override
    public Optional<InstructorResp> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
