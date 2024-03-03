package board1.helloboard.repository;

import board1.helloboard.domain.Post;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    Post modify(Post post);

    void remove(Post post);
    Optional<Post> findById(Long id);
    Optional<Post> findByName(String name);

    Optional<Post> findByTitle(String title);

    Optional<Post> findByContent(String content);


//    List<Post> findAllByDate(LocalDate date);
    List<Post> findAll();



}
