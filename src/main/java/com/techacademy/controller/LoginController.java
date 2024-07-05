package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    /** ログイン画面を表示 */
    @GetMapping("/login") //URL末尾に/loginってつけるとログイン画面が表示される
    public String getLogin() {
        // Login.htmlに画面推移
        return "login";
    }

}
