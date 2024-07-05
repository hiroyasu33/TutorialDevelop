package com.techacademy.controller;
// importは予めソースコードが書かれているクラスを呼び出している
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techacademy.entity.User;
import com.techacademy.service.UserService;

@Controller  // これはこのクラスがHTTPリクエストを受けつけるクラスであることを示す
@RequestMapping("user") //これはURLの末尾の前にuserをつけるって意味　例：localhost:8080/user/list
public class UserController { //UserControllerクラスの宣言
    private final UserService service; //サービスフォルダにある、UserService.javaクラスのコンストラクタをserviceと名付けて宣言している

    public UserController(UserService service) { //UserControllerという名前のメソッドの宣言、UserServiceクラスのserviceという引数を使用
        this.service = service; //引数のserviceと変数のserviceは同じ
    }

    /** 一覧画面を表示 */
    @GetMapping("/list") //URLの末尾にlistをつけて検索すると以下の処理が実行される
    public String getlist(Model model) { //String型のgetlistという名前のメソッドの宣言、Modelクラスのインスタンス(model)を引数として受け取る
        // 全件検索結果をModelに登録
        model.addAttribute("userlist", service.getUserList()); //modelオブジェクトにuserlist というキーとservice.getUserList()から取得した値をセットしている。
        // user/list.htmlに画面遷移
        return "user/list";
    }

    /** User登録画面を表示 */
    @GetMapping("/register") //URLの末尾にregisterをつけて検索するとgetRegisterメソッドが実行される。@GetMappingでデータを入力させるメソッド
    public String getRegister(@ModelAttribute User user) { //String型のメソッドgetRegisterを宣言する。user（フォームに入力された値）をUser.javaのインスタンスに登録する。
        // User 登録画面に遷移
        return "user/register"; //localhost:8080/user/registerが表示される
    }

    /** User登録処理 */
    @PostMapping("/register") //@PostMapping("/register")はlocalhost:8080/user/register（User登録ページ）に入力された値を送信するコード
    public String postRegister(@Validated User user, BindingResult res, Model model) { //String型のpostRegisterメソッドを宣言。@Validatedでuserの値をチェック。BindingResult res,でバリデーションエラーの結果を格納する。Modelクラスのmodel変数でビュー（画面）にデータを表示する。
        if(res.hasErrors()) { //エラーが発生するかどうか判断するコード
            // エラーあり
            return getRegister(user); //user（フォームに入力された値）を保持したままgetRegisterメソッドを実行
        }
        // User登録
        service.saveUser(user); //UserServiceクラスのsaveUserメソッドをuser（フォームに入力された値）を保持したまま実行。userが登録される。
        // 一覧画面にリダイレクト
        return "redirect:/user/list";
    }

    /** User 更新画面を表示*/
    @GetMapping("/update/{id}/") //URLの末尾にupdate/指定されたユーザーのIDをつけて検索するとgetUserメソッドが実行される。@GetMappingはリンク、URLが入力されたときに動くアノテーション。
    public String getUser(@PathVariable("id") Integer id, Model model, User user) { //getUserメソッドの宣言。URLに入力されたid（値）を引数として使用、その引数(id)をInteger型のid変数に代入。Model型のmodel変数、User型のuser変数を引数として受け取る。
        if(id != null) { //idが入力された場合
        // Modelに登録
        model.addAttribute("user", service.getUser(id)); //"user"は、update.htmlの${user}を指す。内容は、
        } else { //idが入力されなかった場合
            model.addAttribute("user", user);
        }
        // User 更新画面に遷移
        return "user/update";
    }



    /** User 更新処理 */
    @PostMapping("/update/{id}/") //submitで送られたデータを受信するコード
    public String postUser(@PathVariable("id") Integer id,@Validated User user, BindingResult res, Model model) { //postUserメソッドの宣言
        if(res.hasErrors()) { //エラーが発生するかどうか判断するコード
            return getUser(null,model, user); //getUserメソッド(idがnull)を実行して画面に表示する
        }
        // User 登録
        service.saveUser(user);
        // 一覧画面にリダイレクト
        return "redirect:/user/list";
    }

    /** User 削除処理 */
    @PostMapping(path="list", params="deleteRun")
    public String deleteRun(@RequestParam(name="idck") Set<Integer> idck, Model model) {
        // Userを一括削除
        service.deleteUser(idck);
        // 一覧画面にリダイレクト
        return "redirect:/user/list";
    }

}
