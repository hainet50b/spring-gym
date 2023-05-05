# spring-web-shiftjis-client
Please also look at [spring-web-shiftjis-server](https://github.com/hainet50b/spring-web-gym/tree/main/spring-web-shiftjis/spring-web-shiftjis-server).

# Setup
```sh
git clone https://github.com/hainet50b/spring-web-gym.git
cd spring-web-gym/spring-web-shiftjis/spring-web-shiftjis-client/docker
docker build -t hainet50b/spring-web-shiftjis-client .
docker run --name spring-web-shiftjis-client -p 80:80 -d hainet50b/spring-web-shiftjis-client
```

# Settings
```java
HttpHeaders headers = new HttpHeaders();
headers.setContentType(new MediaType(MediaType.TEXT_PLAIN, Charset.forName("Shift_JIS")));
```

```yaml
server:
  servlet:
    encoding:
      charset: Shift_JIS
```
