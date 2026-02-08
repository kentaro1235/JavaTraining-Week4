# JavaTraining-Week5

## プロジェクト概要
Spring Boot の MVC 構成（Controller / Service / Repository）と Thymeleaf を用いて、  
タスク管理アプリケーションに Web 画面を実装しました。
タスクの一覧表示・新規作成・編集・削除といった基本的な CRUD 機能を Web 画面から操作できるようにし、  
サーバサイドバリデーションのエラーメッセージ表示、PRG パターンによる画面遷移、  
共通レイアウト（header / footer / メッセージ表示）の適用を行いました。

## 環境構築手順

### 開発環境
- IDE：Visual Studio Code
- Java：17
- Spring Boot：3.5.9
- Build Tool：Gradle
- Database：H2 Database
- OS：Windows10

### DB設定
`src/main/resources/application.yml` に以下を設定しています。

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:tasks;DB_CLOSE_DELAY=-1
    driver-class-name: org.h2.Driver
    username: sa
    password:
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
  h2:
    console:
      enabled: true
      path: /h2-console 　
```

## 起動手順
JavaTraining-Week5で以下を実行します。（PowerShell）
.\gradlew.bat bootRun

以下のログが表示されれば起動成功です。
Tomcat started on port(s): 8080 (http)
Started TaskappApplication

起動後、ブラウザで以下にアクセスします。
http://localhost:8080/tasks

## 画面推移
画面	　　　　URL                      　　内容
一覧画面	　　/tasks                   　　タスク一覧表示
新規作成画面	/tasks/new               　　タスク新規作成フォーム
編集画面	　　/tasks/{id}/edit         　　タスク編集フォーム
登録	　　　　/tasks（POST）               タスク登録
更新	　　　　/tasks/{id}（POST）          タスク更新
削除	　　　　/tasks/{id}/delete（POST）   タスク削除
完了切替	　　/tasks/{id}/toggle（POST）   完了／未完了の切替

## バリデーション・例外ハンドリングの説明

### バリデーション
フォーム入力は TaskForm DTO で受け取り、以下のバリデーションを行っています。

タイトル：@NotBlank

最大文字数：@Size(max = 50)

バリデーションエラー発生時は、同一画面に戻り、
Thymeleaf の th:errors を用いてフィールド直下にエラーメッセージを表示します。

### 例外ハンドリング
存在しないタスク ID にアクセスした場合は TaskNotFoundException を送出し、
@ControllerAdvice により例外を捕捉して、
共通の 404 エラーページ（templates/error/404.html）を表示します。




