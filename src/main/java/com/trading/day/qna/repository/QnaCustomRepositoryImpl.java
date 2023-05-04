package com.trading.day.qna.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.trading.day.qna.domain.QQnaDslDTO;
import com.trading.day.qna.domain.QnaDslDTO;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;

import static com.trading.day.qna.answer.domain.QAnswer.answer;
import static com.trading.day.qna.domain.QQna.qna;

/**
 * packageName : com.trading.day.qna.repository
 * fileName : QnaCustomRepositoryImpl
 * author : taeil
 * date : 2023/05/04
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/05/04        taeil                   최초생성
 */
public class QnaCustomRepositoryImpl implements QnaCustomRepository{

    private final JPAQueryFactory queryFactory;

    public QnaCustomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<QnaDslDTO> dslFindByQnaId(Long qnaId) {
        System.out.println("나는 dslFindByQnaId : 호 출 됐 음 ");
        return queryFactory
                .select(new QQnaDslDTO(
                        qna.qnaId,
                        qna.title,
                        qna.writer,
                        qna.content
                ))
                .from(qna)
                        .leftJoin(qna.answers, answer)
                        .where(qna.qnaId.eq(qnaId))
                        .fetch();
    }
}