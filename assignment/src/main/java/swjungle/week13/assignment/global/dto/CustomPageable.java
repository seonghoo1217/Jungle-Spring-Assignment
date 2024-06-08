package swjungle.week13.assignment.global.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class CustomPageable {
    public static Pageable of(int page, int size) {
        return PageRequest.of(page - 1, size);
    }
}
