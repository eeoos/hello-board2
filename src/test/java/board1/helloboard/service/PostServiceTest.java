package board1.helloboard.service;

import board1.helloboard.domain.Post;
import board1.helloboard.repository.MemoryPostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class PostServiceTest {
    PostService postService;
    MemoryPostRepository postRepository;

    @BeforeEach
    public void beforeEach() {
        postRepository = new MemoryPostRepository();
        postService = new PostService(postRepository);
    }

    @AfterEach
    public void afterEach() {
        postRepository.clearStore();
    }

    @Test
    public void 글_생성() {

        //given
        Post post = new Post();
        post.setTitle("tmp title");
        post.setName("tmp name");
        post.setContent("abcdefg");

        //when
        Long saveId = postService.create(post);

        //then
        Post findPost = postRepository.findById(saveId).get();

        assertThat(post.getTitle()).isEqualTo(findPost.getTitle());
        assertThat(post.getName()).isEqualTo(findPost.getName());
        assertThat(post.getContent()).isEqualTo(findPost.getContent());
    }

    @Test
    public void 중복_이름_예외() {
        //given
        Post post1 = new Post();
        post1.setTitle("title1");
        post1.setName("name1");
        post1.setContent("content1");

        Post post2 = new Post();
        post2.setTitle("title2");
        post2.setName("name1");
        post2.setContent("content2");

        //when
        postService.create(post1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> postService.create(post2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    public void 중복_제목_예외() {
        //given
        Post post1 = new Post();
        post1.setTitle("title1");
        post1.setName("name1");
        post1.setContent("content1");

        Post post2 = new Post();
        post2.setTitle("title1");
        post2.setName("name2");
        post2.setContent("content2");

        //when
        postService.create(post1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> postService.create(post2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 제목입니다.");
    }

    @Test
    public void 중복_내용_예외() {
        //given
        Post post1 = new Post();
        post1.setTitle("title1");
        post1.setName("name1");
        post1.setContent("content1");

        Post post2 = new Post();
        post2.setTitle("title2");
        post2.setName("name2");
        post2.setContent("content1");

        //when
        postService.create(post1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> postService.create(post2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 내용입니다.");
    }

    @Test
    public void 글_수정() {
        //given
        Post post = new Post();
        post.setTitle("Original Title");
        post.setName("Original Name");
        post.setContent("Original Content");

        //when
        Long savedPostId = postService.create(post);

        Post updatePost = new Post();
        updatePost.setId(savedPostId);
        updatePost.setTitle("Updated Title");
        updatePost.setName("Updated Name");
        updatePost.setContent("Updated Content");

        postService.update(updatePost);
        //then
        Post foundPost = postRepository.findById(updatePost.getId()).get();
        assertEquals(updatePost.getTitle(), foundPost.getTitle());
        assertEquals(updatePost.getName(), foundPost.getName());
        assertEquals(updatePost.getContent(), foundPost.getContent());
    }

    @Test
    public void 삭제() {
        Post post = new Post();
        post.setName("name1");
        post.setTitle("title1");
        post.setContent("content1");

        Long saveId = postService.create(post);
        Post findPost = postRepository.findById(saveId).get();

        assertThat(post.getTitle()).isEqualTo(findPost.getTitle());
        assertThat(post.getName()).isEqualTo(findPost.getName());
        assertThat(post.getContent()).isEqualTo(findPost.getContent());

        postService.remove(post);
    }


}