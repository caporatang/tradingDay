package com.trading.day.qna.service;

import com.trading.day.qna.domain.Qna;
import com.trading.day.qna.domain.QnaDTO;
import com.trading.day.qna.domain.QnaDslDTO;
import com.trading.day.qna.repository.QnaCustomRepository;
import com.trading.day.qna.repository.QnaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class QnaServiceTest {

    @Autowired private QnaService qnaService;
    @Autowired private QnaRepository qnaRepository;


    @Test
    @DisplayName("기존 메서드 sql 테스트")
    void findAll() {
        List<Qna> result = qnaRepository.findAllEntityGraph();
        for (Qna qna : result) {
            System.out.println("qna = " + qna);
        }
    }

    @Test
    @DisplayName("fetchJoin")
    void findAllByFetchJoin() {
        List<Qna> result = qnaRepository.findAllByFetchJoin();
        List<Qna> result2 = qnaRepository.findAllEntityGraph();
        for (Qna qna : result) {
            System.out.println("qna = " + qna);
        }

        System.out.println("-------------------------------------------------------------------");

        for (Qna qna : result2) {
            System.out.println("qna2 = " + qna);
        }
    }

    @Test
    @DisplayName("QnaCustomRepository")
    void qnaCustomRepository() {
        List<QnaDslDTO> result1 = qnaRepository.dslFindByQnaId(1L);
        List<QnaDslDTO> result2 = qnaRepository.dslFindByQnaId(2L);
        List<QnaDslDTO> result3 = qnaRepository.dslFindByQnaId(3L);

        for (QnaDslDTO qnaDslDTO1 : result1) {
            System.out.println("qnaDslDTO1 = " + qnaDslDTO1);
        }
        for (QnaDslDTO qnaDslDTO2 : result2) {
            System.out.println("qnaDslDTO2 = " + qnaDslDTO2);
        }
        for (QnaDslDTO qnaDslDTO3 : result3) {
            System.out.println("qnaDslDTO3 = " + qnaDslDTO3);
        }
    }
}