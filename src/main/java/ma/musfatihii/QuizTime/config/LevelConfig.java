package ma.musfatihii.QuizTime.config;


import ma.musfatihii.QuizTime.repository.LevelRepository;
import ma.musfatihii.QuizTime.model.Level;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LevelConfig {

    @Bean
    CommandLineRunner commandLineRunner(LevelRepository levelRepository) {
        return args -> {
            Level level1 = new Level(
                    "description 1",
                    10,
                    20
            );

            Level level2 = new Level(
                    "description 2",
                    30,
                    40
            );

            levelRepository.saveAll(
                    List.of(level1,level2)
            );

        };
    }
}
