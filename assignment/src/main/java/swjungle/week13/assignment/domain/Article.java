package swjungle.week13.assignment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "article")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;

    private ArticleEssential articleEssential;

    @OneToMany(mappedBy = "article", cascade = CascadeType.PERSIST)
    @OrderBy("postDateTime DESC")
    private List<Comment> comments = new ArrayList<>();

    public Article(UUID uuid, ArticleEssential articleEssential) {
        this.uuid = uuid;
        this.articleEssential = articleEssential;
    }

    public void modifyArticleEssential(String title, String contents, LocalDateTime postDateTime, String author) {
        this.articleEssential = new ArticleEssential(title, contents, postDateTime, author);
    }
}
