# gdcRanking #

## Build & Run ##

```sh
$ cd gdcRanking
$ ./sbt
> container:start
> browse
```

If `browse` doesn't launch your browser, manually open [http://localhost:8080/](http://localhost:8080/) in your browser.

## Deploy standalone jar file ##

```sh
./sbt clean assembly
```

## Add ranking ##

GET:
/app/:appname/rankings

## Get all ranking ##

POST:
/app/:appname/rankings

body: name=awef&score=3213
