package swjungle.week13.assignment.presentation.dto.request;

import java.util.UUID;

public record ModifyArticleReq(UUID uuid, String title, String contents) {
}
