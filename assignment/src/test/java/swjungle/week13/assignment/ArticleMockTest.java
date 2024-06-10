package swjungle.week13.assignment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import swjungle.week13.assignment.application.dto.ArticleDTO;
import swjungle.week13.assignment.application.service.ArticleQueryService;
import swjungle.week13.assignment.global.dto.ResponseEnvelope;
import swjungle.week13.assignment.presentation.ArticleController;
import swjungle.week13.assignment.presentation.dto.request.PageReq;
import swjungle.week13.assignment.presentation.dto.response.PageRes;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ArticleMockTest {
    @InjectMocks
    private ArticleController articleController;

    @Mock
    private ArticleQueryService articleQueryService;

    @Test
    public void testFindAllArticles() {
        // given
        int page = 1;
        int size = 10;
        PageReq pageReq = new PageReq(page, size);

        List<ArticleDTO> articleDTOs = new ArrayList<>();

        Page<ArticleDTO> articles = new PageImpl<>(articleDTOs);
        given(articleQueryService.findAllArticles(page, size)).willReturn(articles);

        // when
        ResponseEnvelope<?> responseEnvelope = articleController.findAllArticles(pageReq);

        // then
        assertThat(responseEnvelope.data()).isInstanceOf(PageRes.class);
        PageRes<?> pageRes = (PageRes<?>) responseEnvelope.data();
        assertThat(pageRes.pageData()).isEqualTo(articleDTOs);
    }
}
