プロジェクト概要
Spring Boot 3を用いた最小構成のREST API。
"/hello"で文字列を返し、"api/tasks"で簡易的なタスク管理APIを提供

開発環境
IDE: VScode
JDK: 17
フレームワーク: Spring Boot 3.5.9
ビルドツール: Gradle 

セットアップ手順
1. リポジトリをクローン
2. サーバーを起動(PowerShell)　./gradlew bootRun　(コマンドを実行する場所にgladlew.batファイルがあることを確認)
3. 動作確認(PowerShell)
Hello API: curl http://localhost:8080/hello
タスク登録: Invoke-WebRequest -Uri "http://localhost:8080/api/tasks" `
  -Method POST `
  -ContentType "application/json" `
  -Body '{"title":"new task}'
タスク一覧: curl http://localhost:8080/api/tasks

エラーが出たときの対処
最初に./gradlew bootRunを実行しようとするとエラーがでたがspring initializrがらダウンロードしたファイルにhellospringフォルダが連続して2つあったため一回多く正しい場所を開いて実行したところ解決した。
PowerShellでタスク登録をcurlコマンドで実行しようとしたところエラーがでたため代わりにInvoke-WebRequestを用いたところ正常に動作することが確認できた。