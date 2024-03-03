package board1.helloboard.service;

import board1.helloboard.domain.Post;
import board1.helloboard.repository.MemoryPostRepository;
import board1.helloboard.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * 글 작성
     */
    public Long create(Post post) {

        validateDuplicateName(post); // 이 세 개의 메서드 합칠 방법 생각하기
        validateDuplicateTitle(post);
        validateDuplicateContent(post);
        postRepository.save(post);
        return post.getId();
    }

    private void validateDuplicateContent(Post post) {
        postRepository.findByContent(post.getContent())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 내용입니다.");
                });
    }

    private void validateDuplicateTitle(Post post) {
        postRepository.findByTitle(post.getTitle())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 제목입니다.");
                });
    }

    private void validateDuplicateName(Post post) {
        postRepository.findByName(post.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> findOne(Long postId) {
        return postRepository.findById(postId);
    }

    public Long update(Post post) { // 복습
        // 중복 체크를 위한 validate 메서드들 (필요에 따라)

        // 저장소에 있는 기존 Post를 수정한다.
        postRepository.modify(post);

        // 수정된 Post의 ID를 반환한다.
        return post.getId();
    }

    public void delete(Post post) { // 체크 필요
        postRepository.remove(post);
    }



}
