package swjungle.week13.assignment.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

public record CreateArticleReq(@NotNull String title, String contents) {
}
