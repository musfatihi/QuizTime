package ma.musfatihii.QuizTime.controller;


import ma.musfatihii.QuizTime.service.LevelService;
import ma.musfatihii.QuizTime.model.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/level")
public class LevelController {

    private final LevelService levelService;

    @Autowired
    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping
    public List<Level> getLevels() {
        return levelService.getLevels();

    }

    @PostMapping
    public void addNewLevel(@RequestBody Level level){
        levelService.saveNewLevel(level);
    }

}
