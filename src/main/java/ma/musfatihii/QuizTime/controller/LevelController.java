package ma.musfatihii.QuizTime.controller;


import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.dto.level.LevelReq;
import ma.musfatihii.QuizTime.dto.level.LevelResp;
import ma.musfatihii.QuizTime.service.Implementation.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/levels")
@CrossOrigin
public class LevelController {

    private final LevelService levelService;

    @Autowired
    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping
    public ResponseEntity<Page<LevelResp>> getLevels(Pageable pageable) {
        return ResponseEntity.ok(levelService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LevelResp> getLevel(@PathVariable Long id) {
        return ResponseEntity.ok(levelService.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<LevelResp> addLevel(@RequestBody @Valid LevelReq levelReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(levelService.save(levelReq).get());
    }


    @PutMapping
    public ResponseEntity<LevelResp> updateLevel(@RequestBody @Valid LevelReq levelReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(levelService.update(levelReq).get());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLevel(@PathVariable Long id)
    {
        levelService.delete(id);
        return ResponseEntity.ok("Niveau ayant l'id "+id+" est supprimé avec succès");
    }

}
