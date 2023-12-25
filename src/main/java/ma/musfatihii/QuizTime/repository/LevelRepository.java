package ma.musfatihii.QuizTime.repository;

import ma.musfatihii.QuizTime.model.Level;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface LevelRepository extends JpaRepository<Level,Long> {
    Page<Level> findAll(Pageable pageable);
}
