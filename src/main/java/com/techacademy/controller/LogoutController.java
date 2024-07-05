package com.techacademy.controller;

import org.springframework.web.bind.annotation.PostMapping;

public class LogoutController {

    /** ログアウト処理を行なう */
    @PostMapping("/logout") //URL末尾に/logoutってつけるとログアウト画面が表示される
    public String postLogout() {
        // ログイン画面にリダイレクト(戻る)
        return "redirect:/login";
    }

}
