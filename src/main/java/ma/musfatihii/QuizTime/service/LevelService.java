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
public class LevelService {
    private final LevelRepository levelRepository;

    @Autowired
    public LevelService(LevelRepository levelRepository)
    {
        this.levelRepository = levelRepository;
    }

    public List<Level> getAllLevels()
    {
        return levelRepository.findAll();
    }

    public Optional<Level> getLevel(Long id)
    {
        return levelRepository.findById(id);
    }

    public void addNewLevel(Level level) {
        if(!isMinMaxScoreValid(level)){throw new LevelInfosNotCorrectException();}
        try{levelRepository.save(level);}
        catch (Exception ex){throw new LevelNotCreatedException();}
    }

    public Optional<Level> updateLevel(Level updatedLevel) {
        Optional<Level> optionalLevel = getLevel(updatedLevel.getId());
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

    public boolean deleteLevel(Long id)
    {
        if(getLevel(id).isPresent())
        {
            levelRepository.deleteById(id);
            return true;
        }
        else
        {return false;}
    }

    private Boolean isMinMaxScoreValid(Level level)
    {
        if(level.getMinScore()> level.getMaxScore()) {return false;}
        return true;
    }
}
