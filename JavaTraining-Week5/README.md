# JavaTraining-Week5

## プロジェクト概要
Spring Boot を用いてタスク管理用の REST API を作成し、H2 Database にデータを永続化するアプリケーションです。  
CRUD操作およびバリデーション・例外ハンドリングを実装しています。

## 環境構築手順

### 開発環境
- IDE：Visual Studio Code
- Java：17
- Spring Boot：3.5.10
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

## 実行・確認手順
### アプリケーション起動 
JavaTraining-Week5で以下を実行します。（PowerShell）
.\gradlew.bat bootRun

以下のログが表示されれば起動成功です。
Tomcat started on port(s): 8080 (http)
Started TaskappApplication

### API動作確認（PowerShell）
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

### DB永続化の確認(H2 Console)
起動中にブラウザで以下へアクセスします
http://localhost:8080/h2-console

接続情報：

JDBC URL：jdbc:h2:mem:tasks

User Name：sa

Password：空

以下のSQLで保存データを確認できます。
SELECT * FROM TASK;

## 例外ハンドリングの動作例
### Validationエラー（400）
titleが空の場合、400エラーを返します。
try {
  iwr "http://localhost:8080/api/tasks" -Method POST `
    -ContentType "application/json" `
    -Body '{"title":""}'
} catch {
  $_.Exception.Response.StatusCode.value__
  (New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())).ReadToEnd()
}
期待される結果：
・Status Code:400
・入力エラー内容を含むJSONが返却される

### 存在しないIDにアクセス（404）
存在しないIDにアクセスした場合、404エラーを返します。
try {
  iwr "http://localhost:8080/api/tasks/9999" -Method DELETE
} catch {
  $_.Exception.Response.StatusCode.value__
  (New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())).ReadToEnd()
}
期待される結果：
・Status Code:404
・{"error":"Task not found"} が返却される





