package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.dto.response.ResponseReq;
import ma.musfatihii.QuizTime.dto.response.ResponseResp;
import ma.musfatihii.QuizTime.service.Implementation.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/responses")
@CrossOrigin
public class ResponseController {
    private final ResponseService responseService;

    @Autowired
    public ResponseController(ResponseService responseService)
    {
        this.responseService = responseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseResp> getResponse(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(responseService.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<ResponseResp> addResponse(@RequestBody @Valid ResponseReq responseReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(responseService.save(responseReq).get());
    }

    @GetMapping
    public ResponseEntity<List<ResponseResp>> getAllResponses() {
        return ResponseEntity.ok().body(responseService.findAll());
    }
}
