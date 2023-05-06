package com.trading.day.qna.repository;


import com.trading.day.item.domain.ItemBoard;
import com.trading.day.qna.domain.Qna;
import com.trading.day.qna.domain.QnaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * packageName :
 * fileName : QnaRepository
 * author : taeil
 * date :
 * description : 1:1 문의에 대한 Repository class
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 *               김태일                       최초생성
 */
@Repository
public interface QnaRepository extends JpaRepository<Qna,Long>, QnaCustomRepository{

    Qna findByQnaId(Long QnaId);
    Qna findByQnaIdAndPwd(Long qnaId, String pwd);
    // id로 작성자 검색
    Page<Qna> findByWriter(String Writer, Pageable pageable);
    Page<Qna> findAll(Pageable pageable);
    // 조회
    List<Qna> findByWriter(String writer);

    @EntityGraph(attributePaths = "answers")
    @Query("select q from Qna q")
    List<Qna> findAllEntityGraph();

    @Query("select q from Qna q join fetch q.answers")
    List<Qna> findAllByFetchJoin();


    // List<Qna> result = em.createQuery("select q , a  from Qna q left join q.writer a where q.writer =:writer", Qna.class)
    @Query(value = "select q , a  from Qna q left join q.writer a where q.writer =:writer")
    List<Qna> findJpqlJoinTest(@Param("writer") String writer);

}
