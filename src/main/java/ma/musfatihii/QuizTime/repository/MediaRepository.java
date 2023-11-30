package ma.musfatihii.QuizTime.repository;

import ma.musfatihii.QuizTime.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media,Long> {
}
