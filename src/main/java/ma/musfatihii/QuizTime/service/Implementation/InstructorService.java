package ma.musfatihii.QuizTime.service.Implementation;

import ma.musfatihii.QuizTime.DTO.instructor.InstructorResp;
import ma.musfatihii.QuizTime.exception.InstructorNotCreatedException;
import ma.musfatihii.QuizTime.model.Instructor;
import ma.musfatihii.QuizTime.repository.InstructorRepository;
import ma.musfatihii.QuizTime.service.Interface.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


import java.util.List;
import java.util.Optional;

@Service
public class InstructorService implements ServiceInterface<Instructor,Long, InstructorResp> {

    private final InstructorRepository instructorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository)
    {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public Optional<InstructorResp> save(Instructor instructor) {
        Optional<Instructor> optionalInstructor = Optional.of(instructorRepository.save(instructor));
        if (optionalInstructor.isEmpty()) throw new InstructorNotCreatedException();
        return Optional.of(modelMapper.map(optionalInstructor.get(),InstructorResp.class));
    }

    @Override
    public Optional<InstructorResp> update(Instructor instructor) {
        //return Optional.empty();
        return null;
    }

    @Override
    public List<InstructorResp> findAll() {
        return List.of(modelMapper.map(instructorRepository.findAll(),InstructorResp[].class));
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
