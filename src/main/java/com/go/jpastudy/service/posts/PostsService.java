package com.go.jpastudy.service.posts;

import com.go.jpastudy.domain.posts.Posts;
import com.go.jpastudy.domain.posts.PostsRepository;
import com.go.jpastudy.web.dto.PostsListResponseDto;
import com.go.jpastudy.web.dto.PostsResponseDto;
import com.go.jpastudy.web.dto.PostsSaveRequestDto;
import com.go.jpastudy.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * RequiredArgsConstructor -> final이 선언된 모든 필드를 인자값으로 하는 생성자 생성
 *                            해당 클래서의 의존성 관계가 변경될 때마다 생성자 코드를 계속해서
 *                            수정하는 번거로움을 해결하기 위함함 */
@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }

    /**
     * readOnly = true -> 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선되기 때문에
     *                    등록, 수정, 삭제 기능이 전혀 없는 메소드에서 사용하는 것을 추천
     * .map(PostsListResponseDto::new) -> 레포지토리 결과로 넘오은 posts의 stream을 map 을 통해 PostslistResponseDto로 변환 -> List로 반환하는 메소드
     */
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

        /**
         * JpaRepository에서 이미 delete 메소드를 지원하고 있으니 이를 활용
         * 엔티티를 파라미터로 삭제할 수도 있고, deleteById 메소드를 이용하면 id로 삭제할 수 있음
         * 존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제
         */
        postsRepository.delete(posts);
    }
}
