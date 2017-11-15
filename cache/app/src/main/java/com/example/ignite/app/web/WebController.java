package com.example.ignite.app.web;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ignite.app.user.User;
import com.example.ignite.app.user.UserList;

@Controller
public class WebController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);
    private static final String SESSION_ATTR_USER = "user";

    private final UserList userList;

    @Autowired
    public WebController(UserList userList) {
        this.userList = userList;
    }

    @GetMapping("/login")
    public String loginGet(HttpServletRequest request, Model model) {
        LOGGER.info("GET /login");

        Object user = request.getSession().getAttribute(SESSION_ATTR_USER);
        if (user != null) {
            model.addAttribute("user", user);
            return "home";
        }

        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam("username") String username, @RequestParam("password") String password,
            HttpServletRequest request, Model model) {
        LOGGER.info("POST /login {0} {1}", username, password);
        Optional<User> findOne = userList.findOne(username);
        if (findOne.isPresent()) {
            User user = findOne.get();
            if (user.getPassword().equals(password)) {
                request.getSession().setAttribute(SESSION_ATTR_USER, user);
                model.addAttribute("user", findOne.get());
                return "home";
            }
        }
        model.addAttribute("invalidCredentials", true);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        LOGGER.info("GET /logout");
        request.getSession().removeAttribute(SESSION_ATTR_USER);
        return "redirect:/login";
    }
}
