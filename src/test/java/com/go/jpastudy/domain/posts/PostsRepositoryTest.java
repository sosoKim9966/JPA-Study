package com.go.jpastudy.domain.posts;

/*
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import

import java.util.List;

@ExtendWithtext.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    */
/**
     * After -> Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드
     *          배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용
     *//*

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @ExtendWith
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        */
/**
         *
         * postsRepository.save -> 테이블 posts에 insert/update 쿼리를 실행
         *                         id 값이 있다면 update가 없다면 insert 쿼리 실행
         *//*

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("sosokim9966@gmail.com")
                .build());

        */
/**
         * postsRepository.findAll -> 테이블 posts에 있는 모든 데이터를 조회해오는 메소드
         *//*

        //when
        List<Posts> postList = postsRepository.findAll();

        //then
        Posts posts = postList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
*/

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    /**
     * AfterEach -> Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드
     * 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용
     */
    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        /**
         *
         * postsRepository.save -> 테이블 posts에 insert/update 쿼리를 실행
         *                         id 값이 있다면 update가 없다면 insert 쿼리 실행
         */
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("sosokim9966@gmail.com")
                .build());

        /**
         * postsRepository.findAll -> 테이블 posts에 있는 모든 데이터를 조회해오는 메소드
         */
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    /**
     * BaseTimeEntity 등록 테스트
     */
    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2022,4, 8, 0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>>> createDate = " + posts.getCreatedTime() + ", modifiedDate = " + posts.getModifiedDate());

        assertThat(posts.getCreatedTime()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}