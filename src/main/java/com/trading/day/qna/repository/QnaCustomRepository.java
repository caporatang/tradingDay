package com.trading.day.qna.repository;

import com.trading.day.qna.domain.QnaDslDTO;

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



}