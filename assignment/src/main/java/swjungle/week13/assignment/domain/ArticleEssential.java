package swjungle.week13.assignment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ArticleEssential {
    @Column(nullable = false, length = 255)
    private String title;

    @Column(length = 1000)
    private String contents;

    private LocalDate postDate;
}
