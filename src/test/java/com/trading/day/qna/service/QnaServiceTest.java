package com.trading.day.qna.service;

import com.trading.day.qna.domain.Qna;
import com.trading.day.qna.domain.QnaDTO;
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
        for (Qna qna : result2) {
            System.out.println("qna2 = " + qna);
        }
    }

}