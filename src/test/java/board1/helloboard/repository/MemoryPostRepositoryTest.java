package board1.helloboard.repository;

import board1.helloboard.domain.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class MemoryPostRepositoryTest {

    MemoryPostRepository repository = new MemoryPostRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        //given
        Post post = new Post();
        post.setTitle("tmp title");
        post.setName("tmp name");
        post.setContent("abcdefg");

        //when
        repository.save(post);

        //then
        Post result = repository.findById(post.getId()).get();
        assertThat(result).isEqualTo(post);
    }

    @Test
    void modify() {
        //given
        Post post = new Post();
        post.setTitle("Original Title");
        post.setName("Original Name");
        post.setContent("Original Content");

        //when
        Post savedPost = repository.save(post);

        Post updatePost = new Post();
        updatePost.setId(savedPost.getId());
        updatePost.setTitle("Updated Title");
        updatePost.setName("Updated Name");
        updatePost.setContent("Updated Content");

        repository.modify(updatePost);


        //then
        Post foundPost = repository.findById(updatePost.getId()).get();
        assertThat(foundPost).isEqualTo(updatePost);

    }

    @Test
    public void remove() {
        Post post = new Post();
        post.setName("name1");
        post.setTitle("title1");
        post.setContent("content1");

        Post savedPost = repository.save(post);

        repository.remove(savedPost);
    } // 체크 필요
    @Test
    public void findByName() {
        //given
        Post post1 = new Post();
        post1.setName("name1");
        repository.save(post1);

        Post post2 = new Post();
        post2.setName("name2");
        repository.save(post2);

        //when
        Post result = repository.findByName("name1").get();

        //then
        assertThat(result).isEqualTo(post1);
    }

    @Test
    public void findByTitle() {
        //given
        Post post1 = new Post();
        post1.setTitle("title1");
        repository.save(post1);

        Post post2 = new Post();
        post2.setTitle("title2");
        repository.save(post2);

        //when
        Post result = repository.findByTitle("title1").get();

        //then
        assertThat(result).isEqualTo(post1);
    }

    @Test
    public void findByContent() {
        //given
        Post post1 = new Post();
        post1.setTitle("content1");
        repository.save(post1);

        Post post2 = new Post();
        post2.setTitle("content2");
        repository.save(post2);

        //when
        Post result = repository.findByTitle("content1").get();

        //then
        assertThat(result).isEqualTo(post1);
    }

    @Test
    public void findAll() { // 이름으로 찾겠음
        //given
        Post post1 = new Post();
        post1.setName("name1");
        repository.save(post1);

        Post post2 = new Post();
        post2.setName("name2");
        repository.save(post2);

        //when
        List<Post> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
    }
}