package com.trading.day.qna.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trading.day.config.BaseTimeEntity;
import com.trading.day.member.domain.Member;
import com.trading.day.qna.answer.domain.Answer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName :
 * fileName : Qna
 * author : taeil
 * date :
 * description : 1:1 문의에 대한 Entity class
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 *               김태일                       최초생성
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "Qna")
public class Qna extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qna_id")
    private Long qnaId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;

    private String title;
    private String writer;
    private String content;

    private String pwd;

    @JsonIgnore
    @OneToMany(mappedBy = "qnaId", cascade = CascadeType.REMOVE)
    private List<Answer> answers = new ArrayList<>();

    public void addAnswer(Answer answer) {
        answer.setQnaId(this);
        answers.add(answer);
    }
}