package com.techacademy.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.entity.User;
import com.techacademy.repository.UserRepository;

@Service
public class UserService { //クラス名
    private final UserRepository userRepository; //UserRepositoryクラスを変数userRepositoryとして宣言

    public UserService(UserRepository userRepository) { //コンストラクタの作成。UserRepositoryクラスのインスタンスを引数repositoryに代入
        this.userRepository = userRepository;
    }

    /** 全件を検索して返す */
    public List<User> getUserList(){
        // リポジトリのfindAllメソッドを呼び出す
        return userRepository.findAll();
    }

    /** User を1件検索して返す */
    public User getUser(Integer Id) {
        return userRepository.findById(Id).get();
    }

    //** Userの登録を行なう */
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /** Userの削除を行なう */
    @Transactional
    public void deleteUser(Set<Integer> idck) {
        for(Integer id : idck) {
            userRepository.deleteById(id);
        }
    }

}
