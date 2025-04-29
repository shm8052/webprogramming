package com.example.spring.post;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class PostController {
    
    @Autowired
    PostService postService;

    // 게시글 등록 (화면, GET)
    @GetMapping("/posts/create")
    public String createGet() {
        return ("/post/create");    // return create.jsp
    }

    // 게시글 등록 (처리, POST)
    @PostMapping("/posts/create")
    public String createPost(PostDto post, RedirectAttributes redirectAttributes) {
        boolean created = postService.create(post);

        if (created) {
            redirectAttributes.addFlashAttribute("successMessage", "게시글이 등록되었습니다.");
            return "redirect:/posts";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "게시글 등록에 실패했습니다.");
        return "redirect:/post/create";
    }

    // 게시글 목록 (화면, GET)
    @GetMapping("")
    public String listGet(
        @RequestParam(required = false) String searchType,
        @RequestParam(required = false) String searchKeyword,
        @RequestParam(value = "page", defaultValue = "1") int currentPage, 
        Model model
    ) {
        int listCountPerPage = 10;  // 한 페이지에서 불러올 게시글 수
        int pageCountPerPage = 5;   // 한 페이지에서 보여질 페이지 수

        // 게시글 목록 조회
        Map<String, Object> result = postService.list(currentPage, listCountPerPage, pageCountPerPage, searchType, searchKeyword);

        model.addAttribute("posts", result.get("posts"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("searchType", result.get("searchType"));
        model.addAttribute("searchKeyword", result.get("searchKeyword"));
        return "post/list";
    }

    // 게시글 상세보기 (화면, GET)
    public String readGet(@PathVariable("id") int id, Model model) {
        PostDto post = postService.read(id);
        model.addAttribute("post", post);
        return "post/read";
    }

    // 게시글 수정 (화면, GET)
    @GetMapping("/posts/{id}/update")
    public String updateGet(@PathVariable("id") int id, Model model) {
        PostDto post = postService.read(id);
        model.addAttribute("post", post);
        return "post/update";
    }

    // 게시글 수정 (처리, POST)
    @PostMapping("/posts/{id}/update")
    public String updatePost(@PathVariable("id") int id, PostDto post, RedirectAttributes redirectAttributes) {
        post.setId(id);

        if (postService.update(post)) {
            redirectAttributes.addFlashAttribute("successMessage", "게시글이 수정되었습니다.");
            return "redirect:/posts/" + id;
        }

        redirectAttributes.addFlashAttribute("errorMessage", "게시글 수정에 실패했습니다.");
        return "redirect:/posts/" + id + "/update";
    }

    // 게시글 삭제 (처리, POST)
    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable("id") int id, PostDto posts, RedirectAttributes redirectAttributes) {
        posts.setId(id);
        boolean deleted = postService.delete(posts);

        if (deleted) {
            redirectAttributes.addFlashAttribute("successMessage", "게시글이 삭제되었습니다.");
            return "redirect:/posts";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "게시글 삭제에 실패했습니다.");
        return("redirect:/posts/" + id);
    }

}
