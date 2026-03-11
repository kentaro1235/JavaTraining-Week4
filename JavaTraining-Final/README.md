# Task Management App (JavaTraining-Final)

## プロジェクト概要
Spring Boot を用いて作成したタスク管理Webアプリです。  
ログイン機能を持ち、認証されたユーザのみタスクの作成・編集・削除・完了切替ができます。  
また REST API も提供しており、JSON を用いたタスク操作が可能です。


## 開発環境
- IDE：Visual Studio Code
- Java：17
- Spring Boot：3.5.9
- Build Tool：Gradle
- Database：H2 Database
- OS：Windows10

## セットアップ手順
JavaTraining-Finalで以下を実行します。（PowerShell）
.\gradlew.bat bootRun

以下のログが表示されれば起動成功です。
Tomcat started on port(s): 8080 (http)
Started TaskappApplication

起動後、ブラウザで以下にアクセスします。
http://localhost:8080/login 　

## 動作確認手順（ブラウザ）

ユーザー名：testuser
パスワード：password
を入力し、ログインします。

ログインに失敗すると/login?error に遷移するので再入力してください。

ログアウトは画面上部のログアウトボタンを押してください。

ログイン後、http://localhost:8080/tasksに画面が移りタスク一覧画面が表示されます。

ここで以下の操作が可能です。

画面	　　　　URL                      　　内容
一覧画面	　　/tasks                   　　タスク一覧表示
新規作成画面	/tasks/new               　　タスク新規作成フォーム
編集画面	　　/tasks/{id}/edit         　　タスク編集フォーム
登録	　　　　/tasks（POST）               タスク登録
更新	　　　　/tasks/{id}（POST）          タスク更新
削除	　　　　/tasks/{id}/delete（POST）   タスク削除
完了切替	　　/tasks/{id}/toggle（POST）   完了／未完了の切替

## API動作手順　(Powershell)
タスク操作はREST APIでも可能です。
PowerShellではInvoke-WebRequest(iwr)を使用しています。

タスク一覧取得（GET）
(iwr "http://localhost:8080/api/tasks" -Method GET).Content

タスク作成（POST）
(iwr "http://localhost:8080/api/tasks" -Method POST `
  -ContentType "application/json" `
  -Body '{"title":"buy milk"}').Content

タスク更新（PUT）
(iwr "http://localhost:8080/api/tasks/1" -Method PUT `
  -ContentType "application/json" `
  -Body '{"title":"read book","completed":true}').Content

タスク削除（DELETE）
iwr "http://localhost:8080/api/tasks/1" -Method DELETE | Out-Null

## アーキテクチャ図
Browser
   ↓
Spring Security
   ↓
Controller
 ├ LoginController
 ├ TaskViewController (画面)
 └ TaskRestController (REST API)
   ↓
Service
   ↓
Repository
   ↓
H2 Database

## パッケージ構成
com.example.taskapp
 ├ config
 │   ├ SecurityConfig
 │   └ DataInitalizer
 ├ controller
 │   ├ dto
 │   │  └TaskForm
 │   ├ LoginController
 │   ├ TaskViewController
 │   ├ TaskRestController
 │   └ TaskErrorControllerAdvice
 ├ entity
 │   ├ Task
 │   └ UserAccount
 ├ repository
 │   ├ TaskRepository
 │   └UserAccountRespository
 ├ service
 │   ├ TaskService
 │   └UserAccountService 
 └ exception
     ├ TaskNotFoundException
     └GlobalExceptionHandler

## 既知の制約・今後の改善点
・ユーザごとにタスクを分離する機能が未実装である。
・権限別制御（ADMIN/USER）が未実装である。
・タスクが膨大な量になる可能性を考えるとタスク検索機能の追加が望ましい。




