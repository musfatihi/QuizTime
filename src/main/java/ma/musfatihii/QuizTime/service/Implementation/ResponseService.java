package ma.musfatihii.QuizTime.service.Implementation;

import ma.musfatihii.QuizTime.dto.response.ResponseReq;
import ma.musfatihii.QuizTime.dto.response.ResponseResp;
import ma.musfatihii.QuizTime.exception.NotFoundException;
import ma.musfatihii.QuizTime.exception.ServerErrorException;
import ma.musfatihii.QuizTime.model.Response;
import ma.musfatihii.QuizTime.repository.ResponseRepository;
import ma.musfatihii.QuizTime.service.Interface.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


import java.util.List;
import java.util.Optional;

@Service
public class ResponseService implements ServiceInterface<ResponseReq,Long, ResponseResp> {
    private final ResponseRepository responseRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ResponseService(ResponseRepository responseRepository,
                           ModelMapper modelMapper)
    {
        this.responseRepository = responseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<ResponseResp> save(ResponseReq responseReq) {
        try {
            return Optional.of(
                    modelMapper.map(
                            responseRepository.save(modelMapper.map(responseReq,Response.class))
                            ,ResponseResp.class)
            );
        } catch (Exception ex) {throw new ServerErrorException("Erreur serveur");}
    }

    @Override
    public Optional<ResponseResp> update(ResponseReq responseReq) {
        return Optional.empty();
    }

    @Override
    public List<ResponseResp> findAll() {
        return List.of(
                modelMapper.map(responseRepository.findAll(), ResponseResp[].class)
        );
    }

    @Override
    public Optional<ResponseResp> findById(Long id) {
        Response foundResponse  = responseRepository.findById(id)
                                .orElseThrow(()->new NotFoundException("Réponse introuvable"));
        return Optional.of(modelMapper.map(foundResponse,ResponseResp.class));
    }

    @Override
    public boolean delete(Long aLong) {
        return false;
    }
}
