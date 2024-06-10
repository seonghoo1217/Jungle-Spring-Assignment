package swjungle.week13.assignment.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import swjungle.week13.assignment.application.service.ArticleCommandService;
import swjungle.week13.assignment.application.service.MemberQueryService;

@SpringBootTest
public class ArticleCommandServiceImplTest {

    @Autowired
    ArticleCommandService articleCommandService;

    @Autowired
    MemberQueryService memberQueryService;

    /*@Test
    @DisplayName("주인이 아닌 사용자가 게시물 수정 작업 요청 시")
    public void modifyArticleNotOwner() {
        //given
        String authorization = getAuthorization();
        UUID aritcleUuid = UUID.fromString("7d8119f4-1b50-4274-9afa-c7fe3fdc8ac2");
        String title = "수정된 제목";
        String contents = "수정된 내용";
        //when, then
        assertThatThrownBy(() -> {
                    articleCommandService.modifyArticleEssential(
                            authorization,
                            aritcleUuid,
                            title,
                            contents);
                }
        ).isInstanceOf(ArticleNotOwnerException.class)
                .hasMessageContaining("작성자만 삭제/수정할 수 있습니다.");
    }

    private String getAuthorization() {
        SignupRes signupRes = memberQueryService.signin("seonghoo1", "A12345678@!");
        System.out.println(signupRes.accessToken());
        return "Bearer" + signupRes.accessToken();
    }*/
}