package ma.musfatihii.QuizTime.controller;


import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.DTO.level.CreateLevelRequest;
import ma.musfatihii.QuizTime.DTO.level.LevelResp;
import ma.musfatihii.QuizTime.DTO.level.UpdateLevelRequest;
import ma.musfatihii.QuizTime.exception.LevelNotFoundException;
import ma.musfatihii.QuizTime.service.Implementation.LevelService;
import ma.musfatihii.QuizTime.model.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/levels")
public class LevelController {

    private final LevelService levelService;

    @Autowired
    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping("/p{p}")
    public Page<LevelResp> getAllLevels(@PathVariable int p) {
        return levelService.findAllLevels(p);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LevelResp> getLevel(@PathVariable Long id) {
        return ResponseEntity.ok(levelService.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<LevelResp> addNewLevel(@RequestBody @Valid  CreateLevelRequest createLevelRequest){
        Level level = new Level(createLevelRequest.getDescription(), createLevelRequest.getMinScore(), createLevelRequest.getMaxScore());
        return ResponseEntity.status(HttpStatus.CREATED).body(levelService.save(level).get());
    }


    @PutMapping
    public ResponseEntity<LevelResp> updateLevel(@RequestBody @Valid UpdateLevelRequest updateLevelRequest){
        Level level = new Level();
        level.setId(updateLevelRequest.getId());
        level.setDescription(updateLevelRequest.getDescription());
        level.setMinScore(updateLevelRequest.getMinScore());
        level.setMaxScore(updateLevelRequest.getMaxScore());
        return ResponseEntity.status(HttpStatus.CREATED).body(levelService.update(level).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLevel(@PathVariable Long id)
    {
        if(levelService.delete(id))
        {
            return ResponseEntity.ok("Niveau ayant l'id "+id+" est supprimé avec succès");
        }
        throw new LevelNotFoundException(id);
    }

}
