package com.trading.day.qna.domain;


import com.querydsl.core.annotations.QueryProjection;
import com.trading.day.qna.answer.domain.Answer;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * packageName :
 * fileName : QnaDTO
 * author : taeil
 * date :
 * description : 1:1 문의에 대한 DTO class
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 *               김태일                       최초생성
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class QnaDslDTO {
    private Long qnaId;
    private String title;
    private String writer;
    private String content;
    private String createdDate;
    private String modifiedDate;
    private String pwd;

    @QueryProjection
    public QnaDslDTO(Long qnaId, String title, String writer, String content) {
        this.qnaId = qnaId;
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    private List<Answer> answers;

    public Page<QnaDslDTO> toPageDTO(Page<Qna> entity) {
        Page<QnaDslDTO> qnaListPage = entity.map(m ->
                QnaDslDTO.builder()
                        .qnaId(m.getQnaId())
                        .title(m.getTitle())
                        .content(m.getContent())
                        .writer(m.getWriter())
                        .createdDate(m.getCreatedDate())
                        .modifiedDate(m.getModifiedDate())
                        .title(m.getTitle())
                        .build()
        );
        return qnaListPage;
    }

}
