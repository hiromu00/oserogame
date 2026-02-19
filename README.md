# Othello Web - Modern Full-stack Showcase

## 概要
本プロジェクトは、元々コンソールベースであったオセロゲームを、最新のフルスタック技術を用いてWebアプリケーションとして再構築したものです。
バックエンドに **Java (Spring Boot)**、フロントエンドに **Kotlin/JS (React)** を採用し、開発環境には **Docker Dev Containers** を用いた、モダンでメンテナンス性の高いアーキテクチャを実現しています。

## 技術スタック
### Backend
- **Java 21**: 最新のLTSバージョンを使用し、Recordやモダンな言語機能を活用。
- **Spring Boot 3**: RESTful APIの実装と依存関係の管理。
- **Gradle**: マルチプロジェクト構成による一元的なビルド管理。

### Frontend
- **Kotlin/JS**: 型安全なJavaScript開発。
- **React**: コンポーネント指向のUI開発。
- **Kotlin Emotion**: CSS-in-JSによるスタイリッシュなデザイン。

### DevOps / Environment
- **Docker**: 標準化された開発環境と実行環境。
- **GitHub Actions**: (オプション) CI/CDパイプラインの構築が容易な構成。

## ポートフォリオとしてのポイント
1. **言語の使い分け**: 計算ロジックや堅牢性が求められるバックエンドにJava、フロントエンドの型安全なUI開発にKotlinを採用し、適材適所の技術選定を行っています。
2. **クリーンな設計**: サービス層にゲームロジックをカプセル化し、API層と明確に分離することで、拡張性とテスト可能性を高めています。
3. **開発環境の標準化**: `Dev Containers`を導入することで、誰でもワンクリックで環境を構築でき、チーム開発を意識したセットアップを提供しています。
4. **UXの追求**: 従来のテキスト入力から、直感的なクリック操作によるWebインターフェースへと進化させ、視覚的にも洗練されたオセロ体験を実現しました。

## セットアップ
1. 本プロジェクトをVS Codeで開き、`Reopen in Container`を選択します。
2. バックエンド起動:
   ```bash
   ./gradlew :backend:bootRun
   ```
3. フロントエンド起動:
   ```bash
   ./gradlew :frontend:browserDevelopmentRun
   ```
4. ブラウザで `http://localhost:3000` にアクセスしてください。

---
