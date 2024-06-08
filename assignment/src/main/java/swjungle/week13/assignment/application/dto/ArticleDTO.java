package swjungle.week13.assignment.application.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ArticleDTO(UUID uuid,
                         String title,
                         String contents,
                         String username,
                         LocalDateTime postDateTime,
                         List<CommentDTO> comments) {
}
