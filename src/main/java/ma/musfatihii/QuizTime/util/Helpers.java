package ma.musfatihii.QuizTime.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Helpers<Entity>{
    public Page<Entity> convertListToPage(List<Entity> entityList, int pageNumber, int pageSize) {
        int start = Math.min((pageNumber - 1) * pageSize, entityList.size());
        int end = Math.min(start + pageSize, entityList.size());
        List<Entity> sublist = entityList.subList(start, end);
        return new PageImpl<>(sublist, PageRequest.of(pageNumber - 1, pageSize), entityList.size());
    }
}
