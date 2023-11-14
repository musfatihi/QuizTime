package ma.musfatihii.QuizTime.service;

import ma.musfatihii.QuizTime.exception.ResponseNotCreatedException;
import ma.musfatihii.QuizTime.model.Response;
import ma.musfatihii.QuizTime.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResponseService {
    private ResponseRepository responseRepository;

    @Autowired
    public ResponseService(ResponseRepository responseRepository)
    {
        this.responseRepository = responseRepository;
    }

    public Optional<Response> getResponse(Long id) {
        return responseRepository.findById(id);
    }

    public void addNewResponse(Response response) {
        try {
            responseRepository.save(response);
        }
        catch (Exception ex)
        {
            throw new ResponseNotCreatedException();
        }

    }
}
