package ma.musfatihii.QuizTime.service;

import ma.musfatihii.QuizTime.repository.LevelRepository;
import ma.musfatihii.QuizTime.model.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelService {
    private final LevelRepository levelRepository;

    @Autowired
    public LevelService(LevelRepository levelRepository)
    {
        this.levelRepository = levelRepository;
    }

    public List<Level> getLevels()
    {
        return levelRepository.findAll();
    }

    public void saveNewLevel(Level level) {
        levelRepository.save(level);
    }
}
