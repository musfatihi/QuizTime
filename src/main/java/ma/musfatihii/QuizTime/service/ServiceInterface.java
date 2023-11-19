package ma.musfatihii.QuizTime.service;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<A>{
    Optional<A> save(A a);
    Optional<A> update(A a);
    List<A> findAll();
    Optional<A> findById(Long id);
    boolean delete(Long id);

}
