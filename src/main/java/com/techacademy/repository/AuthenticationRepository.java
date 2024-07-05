package com.techacademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techacademy.entity.Authentication;

/* AuthenticationRepository は、JpaRepository インターフェースを継承しています。JpaRepository は Spring Data JPA において、データベース操作を行うための基本的なメソッドを提供するインターフェイスです。
String は、Authentication エンティティの ID フィールドのデータ型を指定しています。この場合、ID は文字列（String）として扱われます。*/
public interface AuthenticationRepository extends JpaRepository<Authentication, String> {

}
