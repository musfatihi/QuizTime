package ma.musfatihii.QuizTime.controller;


import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.DTO.level.CreateLevelRequest;
import ma.musfatihii.QuizTime.DTO.level.UpdateLevelRequest;
import ma.musfatihii.QuizTime.exception.LevelNotFoundException;
import ma.musfatihii.QuizTime.service.LevelService;
import ma.musfatihii.QuizTime.model.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/levels")
public class LevelController {

    private final LevelService levelService;

    @Autowired
    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping
    public List<Level> getAllLevels() {
        return levelService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Level> getLevel(@PathVariable Long id) {
        Optional<Level> foundLevel = levelService.findById(id);
        if (foundLevel.isPresent()) {
            return ResponseEntity.ok(foundLevel.get());
        } else {
            throw new LevelNotFoundException(id);
        }
    }

    @PostMapping
    public ResponseEntity<Level> addNewLevel(@RequestBody @Valid  CreateLevelRequest createLevelRequest){
        Level level = new Level(createLevelRequest.getDescription(), createLevelRequest.getMinScore(), createLevelRequest.getMaxScore());
        levelService.save(level);
        return ResponseEntity.status(HttpStatus.CREATED).body(level);
    }


    @PutMapping
    public ResponseEntity<Level> updateLevel(@RequestBody @Valid UpdateLevelRequest updateLevelRequest){
        Level level = new Level();
        level.setId(updateLevelRequest.getId());
        level.setDescription(updateLevelRequest.getDescription());
        level.setMinScore(updateLevelRequest.getMinScore());
        level.setMaxScore(updateLevelRequest.getMaxScore());
        if(levelService.update(level).isPresent()) {return ResponseEntity.status(HttpStatus.CREATED).body(level);}
        else {throw new LevelNotFoundException(level.getId());}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLevel(@PathVariable Long id)
    {
        if(levelService.delete(id))
        {
            return ResponseEntity.ok("Niveau supprimé avec succès");
        }else{
            throw new LevelNotFoundException(id);
        }
    }

}
