package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.dto.response.CreateResponseRequest;
import ma.musfatihii.QuizTime.dto.response.ResponseResp;
import ma.musfatihii.QuizTime.exception.ResponseNotFoundException;
import ma.musfatihii.QuizTime.model.Response;
import ma.musfatihii.QuizTime.service.Implementation.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/responses")
public class ResponseController {
    private ResponseService responseService;

    @Autowired
    public ResponseController(ResponseService responseService)
    {
        this.responseService = responseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseResp> getResponse(@PathVariable Long id) {
        Optional<ResponseResp> foundResponse = responseService.findById(id);
        if (foundResponse.isPresent()) {
            return ResponseEntity.ok(foundResponse.get());
        }
        throw new ResponseNotFoundException(id);
    }

    @PostMapping
    public ResponseEntity<ResponseResp> addNewResponse(@RequestBody @Valid CreateResponseRequest createResponseRequest){
        Response response = new Response(createResponseRequest.getContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseService.save(response).get());
    }

    @GetMapping
    public List<ResponseResp> getAllResponses() {
        return responseService.findAll();
    }

}
