package ma.musfatihii.QuizTime.service.Implementation;




import ma.musfatihii.QuizTime.DTO.level.LevelResp;
import ma.musfatihii.QuizTime.exception.LevelInfosNotCorrectException;
import ma.musfatihii.QuizTime.model.Level;
import ma.musfatihii.QuizTime.repository.LevelRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LevelServiceTest {


    @Mock
    private LevelRepository levelRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private LevelService levelService;

    /*
    @Mock
    static LevelService levelService;
    static Level level;

    static ModelMapper modelMapper;

    @BeforeAll
    public static void init() {
        level = new Level();
        level.setMinScore(1);
        level.setMaxScore(4);
        level.setDescription("this is a description");
        level.setQuestions(new ArrayList<>());
        modelMapper = new ModelMapper();
    }

    @Test
    void save() {
        when(levelService.save(level)).thenReturn(Optional.of(modelMapper.map(level, LevelResp.class)));
        Optional<LevelResp> levelRespOptional = levelService.save(level);
        assertTrue(levelRespOptional.isPresent());
        //assertEquals(levelRespOptional.get(), modelMapper.map(level, LevelResp.class));
    }

    */

    @Test
    void save() {

        Level level = new Level();
              level.setMinScore(4);
              level.setMaxScore(1);
              level.setDescription("this is a description");

        assertThrows(LevelInfosNotCorrectException.class,() -> levelService.save(level));

    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findAllLevels() {
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {

    }
}