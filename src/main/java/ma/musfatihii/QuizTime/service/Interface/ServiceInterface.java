package ma.musfatihii.QuizTime.service.Interface;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<A,B,C>{
    Optional<C> save(A a);
    Optional<C> update(A a);
    List<C> findAll();
    Optional<C> findById(B b);
    boolean delete(B b);

}
