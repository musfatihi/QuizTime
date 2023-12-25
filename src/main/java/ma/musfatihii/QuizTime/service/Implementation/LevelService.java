package ma.musfatihii.QuizTime.service.Implementation;

import ma.musfatihii.QuizTime.dto.level.LevelReq;
import ma.musfatihii.QuizTime.dto.level.LevelResp;
import ma.musfatihii.QuizTime.exception.*;
import ma.musfatihii.QuizTime.repository.LevelRepository;
import ma.musfatihii.QuizTime.model.Level;
import ma.musfatihii.QuizTime.service.Interface.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LevelService implements ServiceInterface<LevelReq,Long,LevelResp> {

    private final LevelRepository levelRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public LevelService(LevelRepository levelRepository,
                        ModelMapper modelMapper)
    {
        this.levelRepository = levelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<LevelResp> findAll() {
        return List.of(modelMapper.map(levelRepository.findAll(), LevelResp[].class));
    }

    public Page<LevelResp> findAll(Pageable pageable) {

        Page<Level> levelPage = levelRepository.findAll(pageable);

        return new PageImpl<>(
                levelPage.getContent().stream()
                        .map(this::convertLevelToLevelResp)
                        .collect(Collectors.toList()),
                levelPage.getPageable(),
                levelPage.getTotalElements()
        );

    }


    @Override
    public Optional<LevelResp> save(LevelReq levelReq) {
        if(!isMinMaxScoreValid(levelReq)){throw new InfosNotCorrectException("Infos Niveau incorrectes");}
        try{
            return Optional.of(
                    modelMapper.map(levelRepository.save(modelMapper.map(levelReq,Level.class)),LevelResp.class)
            );
        }
        catch (Exception ex){throw new ServerErrorException("Erreur serveur");}
    }

    @Override
    public Optional<LevelResp> update(LevelReq levelReq) {
        if(!isMinMaxScoreValid(levelReq)){throw new InfosNotCorrectException("Infos Niveau incorrectes");}
        levelRepository.findById(levelReq.getId())
                            .orElseThrow(()->new NotFoundException("Niveau introuvable"));
        try{
            return Optional.of(
                    modelMapper.map(levelRepository.save(modelMapper.map(levelReq,Level.class)),LevelResp.class)
            );
        }
        catch (Exception ex){throw new ServerErrorException("Erreur serveur");}
    }

    @Override
    public Optional<LevelResp> findById(Long id) {
        Level foundLevel = levelRepository.findById(id)
                            .orElseThrow(()->new NotFoundException("Niveau introuvable"));
        return  Optional.of(
                modelMapper.map(foundLevel,LevelResp.class)
        );
    }

    @Override
    public boolean delete(Long id) {
        levelRepository.findById(id)
                       .orElseThrow(()->new NotFoundException("Niveau introuvable"));

        levelRepository.deleteById(id);
        return true;
    }

    public Boolean isMinMaxScoreValid(LevelReq levelReq)
    {
        return levelReq.getMaxScore()>=levelReq.getMinScore();
    }

    private LevelResp convertLevelToLevelResp(Level level) {
        return modelMapper.map(level,LevelResp.class);
    }

}
