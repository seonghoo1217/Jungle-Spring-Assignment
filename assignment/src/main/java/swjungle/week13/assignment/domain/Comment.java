package swjungle.week13.assignment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contents;

    private LocalDateTime postDateTime;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Comment(UUID uuid, String contents, LocalDateTime postDateTime, Article article, Member member) {
        this.uuid = uuid;
        this.contents = contents;
        this.postDateTime = postDateTime;
        this.article = article;
        this.member = member;
    }

    public void modifyComment(String contents) {
        this.contents = contents;
    }
}
