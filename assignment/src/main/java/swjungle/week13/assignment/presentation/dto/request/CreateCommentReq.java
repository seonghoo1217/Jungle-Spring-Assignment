package swjungle.week13.assignment.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateCommentReq(@NotNull UUID articleUuid, @NotNull String contents) {
}
