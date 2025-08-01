# 📌 このアプリの目的
Androidの主要技術を実践的に学びながら、再利用・拡張がしやすいモジュール設計を試すための技術検証用サンプルアプリです。
自分だけでなく他者にも参考にしてもらえるようなアプリとなっていれば嬉しいです。

# 🏗️ 機能一覧
このアプリは複数の技術検証を目的とした「ミニアプリ集」として構成されており、現在は以下の機能を実装：
- Retrofit + Moshi + Coilを使った画像アルバム表示機能
	- スクショ貼る予定

# ⚙️ 技術スタック
## 使用技術
- **DI**：Hilt
- **UI**：Jetpack Compose, zoomable
- **画像/ネットワーク**：Retrofit + Moshi, Coil3
- **画面遷移**：Jetpack Navigation3
- **ライセンス管理**：OSS Licenses Plugin
- **ログ**：Timber

## アーキテクチャ構成
- Todo: 図を作って載せる（Clean Architectureも意識したMVVMとモジュール構成の関係性を簡略図で示す予定）

## 開発方針
- なるべくシンプルで応用が想像しやすいようなミニマルな機能を持つミニアプリを複数内包したアプリであること
- 複数内包したアプリは個別のアプリ化が行うことが可能であること

# 🗂️ プロジェクト構成
- ディレクトリ構成の簡易図（`/ui`, `/domain`, `/data`, `/di` など）
	- /app
		- ベースとなるApplicationやActivityを配置
		- 依存モジュール
			- :core
			- :network
			- :feature:coil
	- /core
		- /ui/theme
			- アプリ全体で利用できるJetpack Compose用のThemeを配置
		- /util
			- アプリ全体で利用できる汎用的なクラスや関数を配置
		- 依存モジュール
			- なし
	- /feature
		- ミニアプリ毎にディレクトリを作成し、UIを実装
			- /coil
				- Retrofit + Moshi + Coilを使った画像アルバム表示機能
				- 依存モジュール
					- :core
					- :network
					- :feature:settings
			- /settings
				- OSSライセンスの表記を行う画面への遷移を行うための設定画面機能
				- 依存モジュール
					- :core
	- /network
		- ホスト毎にディレクトリを作成し、APIを利用するためのUseCaseを作成
			- UseCaseは本来、/feature内の該当するミニアプリのディレクトリ内部に配置するのが良いと思うが、現時点ではUseCase内の処理がミニアプリに固有の処理が含まれていないため、こちらに配置
		- 依存モジュール
			- :core

# 🧠 設計思想
## 設計意図
- コードが肥大化せず、単機能に集中できる構成を目指す
- 実務でも想定される複数画面、複数APIの実装に備えたスケーラブルな構造を採用
## 工夫している点
- マルチモジュール化により、ビルド高速化と責務分離を実施。
- 特に`feature`配下のモジュールをミニアプリ単位で独立させることで、以下の効果を期待：
	- 機能追加・削除の影響範囲を局所化
	- 特定機能のみの再利用や再ビルドが容易

# 🧪 環境構築ガイド
- Android Studioのバージョン
	- `Android Studio Narwhal | 2025.1.1 Build #AI-251.25410.109.2511.13665796, built on June 19, 2025`で動作確認済み
- JDKバージョン：17 （Android Studio Narwhal に付属のものを使用）
- `.env` や APIキーの有無（あれば記載）
	- 現状はなし
	- リリースビルドができるようにしたら、.envファイルが必要となる予定
- ビルド手順
```bash
git clone https://github.com/NaCl5alt/sampleAndroidApps.git
cd sampleAndroidApps
./gradlew clean assembleDebug
```
- ビルド時の注意点
	- 現状はなし
	- ミニアプリが複数になり、ミニアプリ別にアプリ化ができるようになったら必要になる予定

# 🚧 今後の予定
- 実装予定の機能
	- MPAndroidChartチャートミニアプリ
	- WebSocketチャットミニアプリ
	- GPS+Google Map APIを用いたマップミニアプリ
	- Bluetoothを用いたアプリ
	- USBを用いたアプリ
- リファクタ予定や改善したい点
	- GitHub ActionsでのAPK自動生成
	- マルチディメンジョンによるマルチアプリ化
	- GitHub ActionsでのktlintとDangerによるPRレビュー
	- UnitTest導入

# 🗒️ 補足
- 参考にしたリポジトリや記事
	- [android/nowinandroid](https://github.com/android/nowinandroid/tree/main)
	- [DroidKaigi/conference-app-2023](https://github.com/DroidKaigi/conference-app-2023)
- 開発中に気づいたこと・工夫した点
	- ミニアプリ毎にモジュール分割をすることによって、将来的に個別にアプリ化が行いやすくするようにしました
	- 最新のJetpack Navigation3を用いて画面遷移処理を実装しました

# 📚 ライセンス
このリポジトリは MIT ライセンスのもとで公開されており、商用利用・改変・再配布を自由に許可しています。
詳細は [LICENSE](./LICENSE) ファイルを参照してください。

##### 使用ライブラリのライセンスについて
本アプリで使用しているOSSライブラリのライセンスは、設定画面内で確認できます（[play-services-plugins/oss-licenses-plugin](https://github.com/google/play-services-plugins/tree/main/oss-licenses-plugin)を使用）。
