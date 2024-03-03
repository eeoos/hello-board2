package board1.helloboard.controller;

import board1.helloboard.domain.Post;
import board1.helloboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    private final PostService postService;

    @Autowired
    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Post> posts = postService.findPosts();
        model.addAttribute("posts", posts);
        return "home";
    }

    @GetMapping("/posts/readPost/{id}")
    public String read(Model model, @PathVariable Long id) {
        Optional<Post> optionalPost = postService.findOne(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "posts/readPost";
        } else {
            return "redirect:/";
        }
    }

}
