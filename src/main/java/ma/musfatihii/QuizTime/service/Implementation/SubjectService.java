package ma.musfatihii.QuizTime.service.Implementation;

import ma.musfatihii.QuizTime.dto.subject.SubjectResp;
import ma.musfatihii.QuizTime.exception.SubjectNotCreatedException;
import ma.musfatihii.QuizTime.exception.SubjectNotFoundException;
import ma.musfatihii.QuizTime.model.Subject;
import ma.musfatihii.QuizTime.repository.SubjectRepository;
import ma.musfatihii.QuizTime.service.Interface.ServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService implements ServiceInterface<Subject,Long, SubjectResp> {
    private SubjectRepository subjectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository)
    {
        this.subjectRepository = subjectRepository;
    }


    @Override
    public Optional<SubjectResp> save(Subject subject) {
        if(!isParentValid(subject)){throw new SubjectNotFoundException(subject.getParent().getId());}
        try{return Optional.of(modelMapper.map(subjectRepository.save(subject),SubjectResp.class));}
        catch (Exception ex){throw new SubjectNotCreatedException();}
    }

    @Override
    public Optional<SubjectResp> update(Subject subject) {
        Optional<SubjectResp> optionalSubjectResp = findById(subject.getId());
        if(optionalSubjectResp.isEmpty()) throw new SubjectNotFoundException(subject.getId());

        if(isParentValid(subject)) {
            try{return Optional.of(modelMapper.map(subjectRepository.save(subject),SubjectResp.class));}
            catch(Exception ex){throw new SubjectNotCreatedException();}
        }

        throw new SubjectNotFoundException(subject.getParent().getId());
    }

    @Override
    public List<SubjectResp> findAll() {
        return List.of(modelMapper.map(subjectRepository.findAll(), SubjectResp[].class));
    }

    @Override
    public Optional<SubjectResp> findById(Long id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if(optionalSubject.isPresent())
        {
            return Optional.of(modelMapper.map(optionalSubject.get(),SubjectResp.class));
        }
        throw new SubjectNotFoundException(id);
    }

    @Override
    public boolean delete(Long id) {
        if(subjectRepository.findById(id).isPresent()) {
            subjectRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private boolean isParentValid(Subject subject)
    {
        if(subject.getParent()!=null && subject.getParent().getId()!=null) {
            return findById(subject.getParent().getId()).isPresent();
        }
        return true;
    }
}
