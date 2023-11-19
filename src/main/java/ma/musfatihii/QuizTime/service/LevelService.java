package ma.musfatihii.QuizTime.service;

import ma.musfatihii.QuizTime.exception.LevelInfosNotCorrectException;
import ma.musfatihii.QuizTime.exception.LevelNotCreatedException;
import ma.musfatihii.QuizTime.repository.LevelRepository;
import ma.musfatihii.QuizTime.model.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelService implements ServiceInterface<Level> {
    private final LevelRepository levelRepository;

    @Autowired
    public LevelService(LevelRepository levelRepository)
    {
        this.levelRepository = levelRepository;
    }

    @Override
    public Optional<Level> save(Level level) {
        if(!isMinMaxScoreValid(level)){throw new LevelInfosNotCorrectException();}
        try{levelRepository.save(level);
        return Optional.of(level);}
        catch (Exception ex){throw new LevelNotCreatedException();}
    }

    @Override
    public Optional<Level> update(Level updatedLevel) {
        Optional<Level> optionalLevel = findById(updatedLevel.getId());
        if(optionalLevel.isPresent())
        {
            Level level = optionalLevel.get();

            level.setDescription(updatedLevel.getDescription());
            level.setMinScore(updatedLevel.getMinScore());
            level.setMaxScore(updatedLevel.getMaxScore());

            levelRepository.save(level);
        }
        return optionalLevel;
    }

    @Override
    public List<Level> findAll() {
        return levelRepository.findAll();
    }

    @Override
    public Optional<Level> findById(Long id) {
        return levelRepository.findById(id);
    }

    @Override
    public boolean delete(Long id) {
        if(findById(id).isPresent()) {
            levelRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private Boolean isMinMaxScoreValid(Level level)
    {
        if(level.getMinScore()> level.getMaxScore()) {return false;}
        return true;
    }
}
