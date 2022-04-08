package com.go.jpastudy.domain.posts;

import com.go.jpastudy.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *     Entity -> 테이블과 링크될 클래스
 *               기본값으로 클래스의 카멜케이스 이름을
 *               언더 스코어 네이밍으로 테이블 이름을 매칭
 *
 *     NoArgsConstructor -> 기본 생성자 자동 추가
 *                          public Posts(){}와 같음
 */
@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    /**
     * id -> 해당 테이블의 PK 필드
     * GeneratedValue -> PK의 생성 규칙
     *                   GenerationType.IDENTITY 옵션 추가 = auto_increment
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * column -> 테이블의 칼럼 굳이 선언하지 않아도 해당 클래스의 필드는 모두 칼럼
     *           기본 값 외에 추가로 변경이 필요한 옵션이 있으면 사용
     */
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    /**
     * Builder -> 해당 클래스의 빌더 패턴 클래스를 생성
     *            생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
     */
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
