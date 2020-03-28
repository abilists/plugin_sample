# plugin_sample <a href="http://www.abilists.com" ><img src="https://github.com/minziappa/abilists_client/blob/master/src/main/webapp/static/apps/img/abilists/logo01.png" height="22" alt="Abilists"></a>

[![Build Status](https://travis-ci.org/abilists/plugin_sample.svg?branch=master)](https://travis-ci.org/abilists/plugin_sample)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/abilists/plugin_sample)

![markdown](https://github.com/abilists/plugin_sample/blob/master/doc/img/record01.png)

This is a sample for plugins of Abilists
이 심플은 새 플러그인을 개발하기 쉽게 해주는 소스코드 입니다.

## 필요한 시스템 환경

* More [information](http://freemarker.org) about Freemarker.
* More [information](http://projects.spring.io/spring-framework) about Spring4.
* More [information](http://blog.mybatis.org) about Mybatis3
* More [information](https://www.gradle.org) about Gradle3.3.

---

## New in v0.0.1

- 새 데이터 입력
- 데이터 습득
- 데이터 갱신
- 데이터 삭제

## See Also

- **[Abilists](https://github.com/abilists/abilists_client)**
- **[abilists_plugins](https://github.com/abilists/abilists_plugins)**
- **[paging](https://github.com/abilists/paging)**

---

## How to install

Git를 통해 다운로드 하기
```
$ git clone https://github.com/abilists/plugin_sample.git
```

## How to start or stop

개발자 환경에서 실행하기
```
$ gradle jettyRun
```
확인하는 URL
```
http://localhost:9005/plugins/sample/index
```

## How to build

Product버전으로 빌드해서 Jar파일 생성하기
```
$ gradle -b ./probuild.gradle buildJar
```

빌드된 Jar파일이 있는 곳
```
/plugin_sample/build/libs/plugin_sample.jar
```

## Troubleshooting

실행에 에러가 발생하면, Clean빌드를 하시고 다시 실행해 주시기 바랍니다.
```
$ gradle clean
$ gradle jettyRun
```

## Utilities Libraries

아래의 유틸리티를 Clone을 해서 Local에 설치할 필요가 있습니다.

* [io.utility:security:0.0.1](https://github.com/abilists/security_utility)
* [io.utility:letter:0.0.4](https://github.com/abilists/letter_utility)
* [io.utility:api:0.0.4](https://github.com/abilists/api_utility)

Local 시스템에서 설치합니다.
```
$ gradle install
```

## License
This software is licensed under the MIT © Abilists.
