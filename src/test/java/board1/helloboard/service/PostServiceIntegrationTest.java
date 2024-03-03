package board1.helloboard.service;

import board1.helloboard.domain.Post;
import board1.helloboard.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PostServiceIntegrationTest {

    @Autowired PostService postService;
    @Autowired PostRepository postRepository;

    @Test
    public void 글쓰기() {
        //Given
        Post post = new Post();
        post.setName("hello");
        post.setTitle("title");
        post.setContent("content");

        //when
        Long saveId = postService.create(post);

        //then
        Post findPost = postRepository.findById(saveId).get();
        assertEquals(post.getName(), findPost.getName());

    }
    @Test
    public void 이름_중복_게시글_예외() {
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
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> postService.create(post2));//예외가 발생해야 한다. assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    public void 제목_중복_게시글_예외() {
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
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> postService.create(post2));//예외가 발생해야 한다. assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
    @Test
    public void 내용_중복_게시글_예외() {
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
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> postService.create(post2));//예외가 발생해야 한다. assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    /*@Test
    public void 중복_회원_예외() throws Exception {
//Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
//When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다. assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }*/
}
