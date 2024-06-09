package swjungle.week13.assignment.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ModifyCommentReq(@NotNull UUID commentUuid, String contents) {
}
