package ma.musfatihii.QuizTime.service;

import ma.musfatihii.QuizTime.exception.SubjectNotCreatedException;
import ma.musfatihii.QuizTime.exception.SubjectNotFoundException;
import ma.musfatihii.QuizTime.model.Level;
import ma.musfatihii.QuizTime.model.Subject;
import ma.musfatihii.QuizTime.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository)
    {
        this.subjectRepository = subjectRepository;
    }
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubject(Long id) {
        return subjectRepository.findById(id);
    }

    public void addNewSubject(Subject subject) {
        if(subject.getParent()!=null && subject.getParent().getId()!=null)
        {
            if(!getSubject(subject.getParent().getId()).isPresent())
            {throw new SubjectNotFoundException(subject.getParent().getId());}
        }
        try{subjectRepository.save(subject);}
        catch (Exception ex){throw new SubjectNotCreatedException();}
    }

    public Optional<Subject> updateSubject(Subject updatedSubject) {

        Optional<Subject> optionalSubject = getSubject(updatedSubject.getId());

        if(optionalSubject.isPresent())
        {
            Subject subject = optionalSubject.get();
            subject.setTitle(updatedSubject.getTitle());

            return Optional.of(subjectRepository.save(subject));
        }

        return Optional.empty();
    }

    public boolean deleteSubject(Long id)
    {
        if(getSubject(id).isPresent())
        {
            subjectRepository.deleteById(id);
            return true;
        }
        else
        {return false;}
    }
}
