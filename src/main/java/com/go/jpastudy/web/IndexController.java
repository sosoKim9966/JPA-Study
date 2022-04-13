package com.go.jpastudy.web;

import com.go.jpastudy.config.auth.LoginUser;
import com.go.jpastudy.config.auth.dto.SessionUser;
import com.go.jpastudy.service.posts.PostsService;
import com.go.jpastudy.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    /**
     * @param user 기존에 httpSession.getAttribute("user")로 가져오던 세션 정보 값이 개선됨
     *             이제는 어느 컨트롤러든지 @LoginUser만 사용하면 세션 정보를 가져올 수 있게 되었음.
     */
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        /**
         * CustomOAuth2userService에서 로그인 성공시 세션에 SessionUser 저장하도록 했음
         * 로그인 성공시 httpSession.getAttribute("user")에서 값을 가져올 수 있음
         */
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");

        model.addAttribute("posts", postsService.findAllDesc());
        if(user != null) {
            model.addAttribute("name", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
