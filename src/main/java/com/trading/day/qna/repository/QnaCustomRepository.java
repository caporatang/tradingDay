package com.trading.day.qna.repository;

import com.trading.day.qna.domain.QnaDTO;
import com.trading.day.qna.domain.QnaDslDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * packageName : com.trading.day.qna.repository
 * fileName : QnaCustomRepository
 * author : taeil
 * date : 2023/05/04
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/05/04        taeil                   최초생성
 */
public interface QnaCustomRepository {
    // 사용자 정의 레포지토리..!

    List<QnaDslDTO> dslFindByQnaId(Long qnaId);

    Page<QnaDslDTO> dslFindPagingWriter(String writer, Pageable pageable);
    Page<QnaDslDTO> dslFindPagingWriterCountQuery(String writer, Pageable pageable);
}