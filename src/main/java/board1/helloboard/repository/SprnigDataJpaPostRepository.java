package board1.helloboard.repository;

import board1.helloboard.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SprnigDataJpaPostRepository extends JpaRepository<Post, Long>, PostRepository {
    @Override
    Optional<Post> findByName(String name);

    @Override
    Optional<Post> findByTitle(String title);

    @Override
    Optional<Post> findByContent(String content);

    // JpaRepository의 save 메서드를 오버라이드합니다.
    @Override
    Post save(Post post);
    @Override
    default Post modify(Post post) {
        return save(post); // JpaRepository의 save 메서드를 사용합니다.
    }

    @Override
    void delete(Post post);
}
