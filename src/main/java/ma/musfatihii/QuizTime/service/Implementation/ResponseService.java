package ma.musfatihii.QuizTime.service.Implementation;

import ma.musfatihii.QuizTime.DTO.response.ResponseResp;
import ma.musfatihii.QuizTime.exception.ResponseNotCreatedException;
import ma.musfatihii.QuizTime.model.Response;
import ma.musfatihii.QuizTime.repository.ResponseRepository;
import ma.musfatihii.QuizTime.service.Interface.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


import java.util.List;
import java.util.Optional;

@Service
public class ResponseService implements ServiceInterface<Response,Long, ResponseResp> {
    private ResponseRepository responseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ResponseService(ResponseRepository responseRepository)
    {
        this.responseRepository = responseRepository;
    }


    @Override
    public Optional<ResponseResp> save(Response response) {
        try {
            return Optional.of(modelMapper.map(responseRepository.save(response),ResponseResp.class));
        }
        catch (Exception ex)
        {
            throw new ResponseNotCreatedException();
        }
    }

    @Override
    public Optional<ResponseResp> update(Response response) {
        return Optional.empty();
    }

    @Override
    public List<ResponseResp> findAll() {
        return null;
    }


    @Override
    public Optional<ResponseResp> findById(Long id) {
        Optional<Response> optionalResponse = responseRepository.findById(id);
        if(optionalResponse.isPresent()) return Optional.of(modelMapper.map(optionalResponse.get(),ResponseResp.class));
        return Optional.empty();
    }

    @Override
    public boolean delete(Long aLong) {
        return false;
    }


}
