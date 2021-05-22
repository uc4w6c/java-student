# メモ
$ docker build -t virtualhost-nginx .
$ docker run -it -d -p 80:80 virtualhost-nginx

$ curl localhost:80 -v -H "Host: test1.co.jp"

```
{
  "server":"test1",
  "message":"test1 hello"
}
```

$ curl localhost:80 -v -H "Host: test2.co.jp"

```
{
  "server":"test2",
  "message":"test2 hello"
}
```

## 参考
https://bugs.openjdk.java.net/browse/JDK-8213696
