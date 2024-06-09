package swjungle.week13.assignment.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentQueryDTO(UUID uuid, String contents, LocalDateTime postDateTime, String author, UUID articleUuid) {

}
