# easyms
Easy Rest Microservice

# podman-ws-csa

<a name="readme-top"></a>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#exec-application">Exec Application </a></li>
        <li><a href="#api-manage">API Manage</a></li>
        <li><a href="#apis">API Request</a></li>
        <li><a href="#other-tips">Other Tips</a></li>
      </ul>
    </li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

Springboot3で構成した単純なAPIを提供するマイクロサービスです。
H2を内臓しているので、コンテナなどで外部DBを用意せずに擬似的なRestAPIとして作用させることができます。

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

Written [build.gradle](./build.gradle)

検証している動作環境
* openjdk 17.0.5
* gradle 7.6(またはgradlewを利用してみてください)

<!-- GETTING STARTED -->
## Getting Started

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Exec Application 
アプリケーションの実行

展開したディレクトリの中に移動して以下を実行してください。
アプリケーションを終了する場合はを中断(Ctrl + C)してください

```sh
./gradlew bootRun
```
    
### API Manage
APIはOAS3フォーマットで定義されており、以下URLからWebベースで実行可能です

[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

### APIs
APIの実態は/apiの配下に配置されています。各種仕様は上記Webから参照可能です

[http://localhost:8080/api](http://localhost:8080/api)

### Database Manage
H2DBの管理は以下のURLから実行できます(接続情報の設定はapplication.properties)

[http://localhost:8080/h2-ui](http://localhost:8080/h2-ui)

### Other Tips

