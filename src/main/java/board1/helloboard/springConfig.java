package board1.helloboard;

import board1.helloboard.repository.MemoryPostRepository;
import board1.helloboard.repository.PostRepository;
import board1.helloboard.service.PostService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import jakarta.persistence.EntityManager;


@Configuration
public class springConfig {


    @Bean

    public PostService postService(){
        return new PostService(postRepository());
    }

    @Bean
    public PostRepository postRepository(){
//        return new MemoryPostRepository();
        return new MemoryPostRepository();
    }
}
