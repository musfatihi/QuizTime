package ma.musfatihii.QuizTime.service;

import ma.musfatihii.QuizTime.repository.LevelRepository;
import ma.musfatihii.QuizTime.model.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Level> getAllLevels(int page, int size)
    {
        Pageable pageable = PageRequest.of(page,size);
        return levelRepository.findAll(pageable);
    }

    public Optional<Level> getLevel(Long id)
    {
        return levelRepository.findById(id);
    }

    public Optional<Level> addNewLevel(Level level) {
        levelRepository.save(level);
        return Optional.of(level);
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
        }else{return false;}
    }
}
