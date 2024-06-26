package swjungle.week13.assignment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ArticleEssential {
    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String contents;

    private LocalDateTime postDateTime;

    private String author;

    public ArticleEssential(String title, String contents, LocalDateTime postDateTime, String author) {
        this.title = title;
        this.contents = contents;
        this.postDateTime = postDateTime;
        this.author = author;
    }

    public ArticleEssential(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
