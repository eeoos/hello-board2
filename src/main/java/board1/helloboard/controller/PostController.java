package board1.helloboard.controller;

import board1.helloboard.domain.Post;
import board1.helloboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class PostController {
    private final PostService postService;
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts/new")
    public String createForm() {
        return "posts/createPostForm";
    }

    @PostMapping("/posts/new")
    public String create(PostForm form) {
        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setName(form.getName());
        post.setContent(form.getContent());

        postService.create(post);

        return "redirect:/";
    }

    @GetMapping("/posts/updatePost/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        Post post = postService.findOne(id).orElseThrow(() -> new IllegalArgumentException("해당 ID의 게시글이 존재하지 않습니다."));
        model.addAttribute("post", post);
        return "posts/updatePost";
    }

    @PostMapping("/posts/updatePost/{id}")
    public String update(@PathVariable Long id, PostForm form) {

        logger.info("Update method called with id " + id);

        Post post = postService.findOne(id).orElseThrow(() -> new IllegalArgumentException("해당 ID의 게시글이 존재하지 않습니다."));
        post.setTitle(form.getTitle());
        post.setName(form.getName());
        post.setContent(form.getContent());

        postService.update(post);
        return "redirect:/";
    }

    @GetMapping("/posts/confirmDeletePost/{id}")
    public String confirmDeleteForm(@PathVariable Long id, Model model) {
        Post post = postService.findOne(id).orElseThrow(() -> new IllegalArgumentException("해당 ID의 게시글이 존재하지 않습니다."));
        model.addAttribute("post", post);
        return "posts/confirmDeletePost";
    }

    @PostMapping("/posts/confirmDeletePost/{id}")
    public String delete(@PathVariable Long id) {
        Post post = postService.findOne(id).orElseThrow(() -> new IllegalArgumentException("해당 ID의 게시글이 존재하지 않습니다."));
        postService.delete(post);
        return "redirect:/";
    }

}
