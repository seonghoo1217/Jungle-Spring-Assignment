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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "article", cascade = CascadeType.PERSIST)
    private List<Comment> comments = new ArrayList<>();


    public Article(UUID uuid, ArticleEssential articleEssential, Member member) {
        this.uuid = uuid;
        this.articleEssential = articleEssential;
        this.member = member;
    }

    public void modifyArticleEssential(String title, String contents, LocalDateTime postDateTime) {
        this.articleEssential = new ArticleEssential(title, contents, postDateTime);
    }
}
