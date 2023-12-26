package ma.musfatihii.QuizTime.service.Implementation;

import ma.musfatihii.QuizTime.dto.subject.SubjectReq;
import ma.musfatihii.QuizTime.dto.subject.SubjectResp;
import ma.musfatihii.QuizTime.exception.NotFoundException;
import ma.musfatihii.QuizTime.exception.ServerErrorException;
import ma.musfatihii.QuizTime.model.Subject;
import ma.musfatihii.QuizTime.repository.SubjectRepository;
import ma.musfatihii.QuizTime.service.Interface.ServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService implements ServiceInterface<SubjectReq,Long, SubjectResp> {
    private final SubjectRepository subjectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository)
    {
        this.subjectRepository = subjectRepository;
    }


    @Override
    public Optional<SubjectResp> save(SubjectReq subjectReq) {
        if(!isParentValid(subjectReq)){throw new NotFoundException("Sujet Parent introuvable");}
        try{
            return Optional.of(
                    modelMapper.map(
                            subjectRepository.save(
                                    modelMapper.map(subjectReq,Subject.class)
                            ),
                            SubjectResp.class)
            );
        }
        catch (Exception ex){throw new ServerErrorException("Erreur serveur");}
    }

    @Override
    public Optional<SubjectResp> update(SubjectReq subjectReq) {

        if(!isSubjectValid(subjectReq)){throw new NotFoundException("Sujet introuvable");}
        if(!isParentValid(subjectReq)){throw new NotFoundException("Sujet Parent introuvable");}
        try{
            return Optional.of(
                    modelMapper.map(
                            subjectRepository.save(
                                    modelMapper.map(subjectReq,Subject.class)
                            ),
                            SubjectResp.class)
            );
        }
        catch (Exception ex){throw new ServerErrorException("Erreur serveur");}
    }


    @Override
    public List<SubjectResp> findAll() {
        return List.of(modelMapper.map(subjectRepository.findAll(), SubjectResp[].class));
    }

    @Override
    public Optional<SubjectResp> findById(Long id) {
        Subject foundSubject = subjectRepository.findById(id)
                            .orElseThrow(()-> new NotFoundException("Sujet introuvable"));

        return Optional.of(
                modelMapper.map(foundSubject,SubjectResp.class)
        );
    }

    @Override
    public boolean delete(Long id) {
        subjectRepository.findById(id)
                        .orElseThrow(()-> new NotFoundException("Sujet introuvable"));

        subjectRepository.deleteById(id);
        return true;
    }

    private boolean isParentValid(SubjectReq subjectReq)
    {
        if(subjectReq.getParent()!=null && subjectReq.getParent().getId()!=null) {
            return subjectRepository.existsById(subjectReq.getParent().getId());
        }
        return true;
    }

    private boolean isSubjectValid(SubjectReq subjectReq)
    {
        return subjectRepository.existsById(subjectReq.getId());
    }
}
