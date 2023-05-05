package com.trading.day.qna.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.trading.day.qna.domain.QQnaDslDTO;
import com.trading.day.qna.domain.Qna;
import com.trading.day.qna.domain.QnaDslDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.sql.ResultSet;
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

    @Override
    public Page<QnaDslDTO> dslFindPagingWriter(String writer, Pageable pageable) {
        System.out.println("나는 dslFindPagingWriter paging : " + writer);

        // --> TotalCount 쿼리, content 조회용 쿼리 두번 발생
        QueryResults<QnaDslDTO> results = queryFactory
                .select(new QQnaDslDTO(
                        qna.qnaId,
                        qna.title,
                        qna.writer,
                        qna.content
                ))
                .from(qna)
                .leftJoin(qna.answers, answer)
                .where(qna.writer.eq(writer))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<QnaDslDTO> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<QnaDslDTO> dslFindPagingWriterCountQuery(String writer, Pageable pageable) {
        // 데이터가 많은 경우, 하번 조회하는데 count와 content용 쿼리가 따로 날아가는 경우 성능 저하가 될수있음.

        // 게시물 내용 조회 쿼리
        List<QnaDslDTO> content = queryFactory
                .select(new QQnaDslDTO(
                        qna.qnaId,
                        qna.title,
                        qna.writer,
                        qna.content
                ))
                .from(qna)
                .leftJoin(qna.answers, answer)
                .where(qna.writer.eq(writer))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 총 카운트가 필요하지 않는 경우엔 생략 하는 것이 좋을 수도있다..
        JPAQuery<Qna> countQuery = queryFactory
                .select(qna)
                .from(qna)
                .leftJoin(qna.answers, answer)
                .where(qna.writer.eq(writer));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }
}