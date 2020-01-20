### Run mongodb in docker mode

```
docker run -p 27017:27017 -v <LocalDirectoryPath>:/data/db \
--name docker_mongodb -d mongo
```