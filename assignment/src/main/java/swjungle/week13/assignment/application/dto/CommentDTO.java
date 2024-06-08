package swjungle.week13.assignment.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentDTO(UUID uuid, String contents, LocalDateTime postTime, String username) {
}
