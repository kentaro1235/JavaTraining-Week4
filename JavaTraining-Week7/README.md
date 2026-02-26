# JavaTraining-Week6

## プロジェクト概要
前回の課題で作成したアプリケーションに追加でSpring Security を導入し、  
ユーザ認証機能（ログイン・ログアウト）を実装しました。

また、認証が必要なページと不要なページを適切に制御し、  
JUnit + MockMvc を用いてセキュリティ設定とAPI動作のテストを行いました。


## 環境構築手順

### 開発環境
- IDE：Visual Studio Code
- Java：17
- Spring Boot：3.5.9
- Build Tool：Gradle
- Database：H2 Database
- OS：Windows10

## 起動手順
JavaTraining-Week7で以下を実行します。（PowerShell）
.\gradlew.bat bootRun

以下のログが表示されれば起動成功です。
Tomcat started on port(s): 8080 (http)
Started TaskappApplication

起動後、ブラウザで以下にアクセスします。
http://localhost:8080/login 　

ユーザー名：testuser
パスワード:password
を入力し、ログインします。

ログインに失敗すると/login?error に遷移するので再入力してください。

ログアウトは画面上部のログアウトボタンを押してください。


## 画面推移
画面	　　　　URL                      　　内容
一覧画面	　　/tasks                   　　タスク一覧表示
新規作成画面	/tasks/new               　　タスク新規作成フォーム
編集画面	　　/tasks/{id}/edit         　　タスク編集フォーム
登録	　　　　/tasks（POST）               タスク登録
更新	　　　　/tasks/{id}（POST）          タスク更新
削除	　　　　/tasks/{id}/delete（POST）   タスク削除
完了切替	　　/tasks/{id}/toggle（POST）   完了／未完了の切替






