package ma.musfatihii.QuizTime.service.Implementation;

import ma.musfatihii.QuizTime.DTO.level.LevelResp;
import ma.musfatihii.QuizTime.exception.LevelInfosNotCorrectException;
import ma.musfatihii.QuizTime.exception.LevelNotCreatedException;
import ma.musfatihii.QuizTime.exception.LevelNotFoundException;
import ma.musfatihii.QuizTime.repository.LevelRepository;
import ma.musfatihii.QuizTime.model.Level;
import ma.musfatihii.QuizTime.service.Interface.ServiceInterface;
import ma.musfatihii.QuizTime.util.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.Optional;

@Service
public class LevelService implements ServiceInterface<Level,Long,LevelResp> {
    private final LevelRepository levelRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Helpers<LevelResp> helpers;

    @Autowired
    public LevelService(LevelRepository levelRepository)
    {
        this.levelRepository = levelRepository;
    }

    @Override
    public Optional<LevelResp> save(Level level) {
        if(!isMinMaxScoreValid(level)){throw new LevelInfosNotCorrectException();}
        try{return Optional.of(modelMapper.map(levelRepository.save(level),LevelResp.class));}
        catch (Exception ex){throw new LevelNotCreatedException();}
    }

    @Override
    public Optional<LevelResp> update(Level updatedLevel) {
        if(!isMinMaxScoreValid(updatedLevel)){throw new LevelInfosNotCorrectException();}
        Optional<LevelResp> optionalLevelResp = findById(updatedLevel.getId());
        Level level = modelMapper.map(optionalLevelResp.get(),Level.class);

        level.setDescription(updatedLevel.getDescription());
        level.setMinScore(updatedLevel.getMinScore());
        level.setMaxScore(updatedLevel.getMaxScore());

        return Optional.of(modelMapper.map(levelRepository.save(level),LevelResp.class));
    }

    @Override
    public List<LevelResp> findAll() {
        return List.of(modelMapper.map(levelRepository.findAll(), LevelResp[].class));
    }

    @Override
    public Optional<LevelResp> findById(Long id) {
        Optional<Level> optionalLevel = levelRepository.findById(id);
        if(optionalLevel.isPresent()) return  Optional.of(modelMapper.map(optionalLevel.get(),LevelResp.class));
        throw new LevelNotFoundException(id);
    }

    @Override
    public boolean delete(Long id) {
        if(findById(id).isPresent()) {
            levelRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Boolean isMinMaxScoreValid(Level level)
    {
        if(level.getMinScore()> level.getMaxScore()) {return false;}
        return true;
    }

    public Page<LevelResp> findAllLevels(int p) {
        return helpers.convertListToPage(List.of(modelMapper.map(levelRepository.findAll(), LevelResp[].class)),p,10);
    }

}
