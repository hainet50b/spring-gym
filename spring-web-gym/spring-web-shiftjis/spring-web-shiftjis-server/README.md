# spring-web-shiftjis-server
Please also look at [spring-web-shiftjis-client](https://github.com/hainet50b/spring-web-gym/tree/main/spring-web-shiftjis/spring-web-shiftjis-client).

# Settings
```yaml
server:
  servlet:
    encoding:
      charset: Shift_JIS
```

# /request-check
```sh
$ curl -XPOST localhost:8080/request-check -H "Content-Type: text/plain" -d @Shift_JIS.txt
SHIFT_JIS

$ curl -XPOST localhost:8080/request-check -H "Content-Type: text/plain" -d @UTF-8.txt
UTF-8
```

# /request
```sh
$ curl -XPOST localhost:8080/request -H "Content-Type: text/plain" -d @Shift_JIS.txt
Content-Type: text/plain
Request Body: しふとじす

$ curl -XPOST localhost:8080/request -H "Content-Type: text/plain; charset=Shift_JIS" -d @Shift_JIS.txt
Content-Type: text/plain;charset=Shift_JIS
Request Body: しふとじす

$ curl -XPOST localhost:8080/request -H "Content-Type: text/plain" -d @UTF-8.txt
Content-Type: text/plain
Request Body: 繧�繝ｼ縺ｦ縺�繝ｼ縺医�ｵ縺医＞縺ｨ

$ curl -XPOST localhost:8080/request -H "Content-Type: text/plain; charset=UTF-8" -d @UTF-8.txt
Content-Type: text/plain; charset=UTF-8
Request Body: ゆーてぃーえふえいと
```

# /response/shift-jis
```sh
$ curl -s localhost:8080/response/shift-jis -v
Content-Type: text/plain;charset=Shift_JIS

$ curl -s localhost:8080/response/shift-jis | nkf -g
Shift_JIS
```
# /response/utf-8
```sh
$ curl -s localhost:8080/response/utf-8 -v
Content-Type: text/plain;charset=UTF-8

$ curl -s localhost:8080/response/shift-jis | nkf -g
UTF-8
```
