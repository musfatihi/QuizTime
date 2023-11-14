package ma.musfatihii.QuizTime.repository;

import ma.musfatihii.QuizTime.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ResponseRepository extends JpaRepository<Response,Long> {
}
