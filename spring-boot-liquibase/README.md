Spring boot liquibase
==========================================

## H2 database
默认可以通过h2-console查看数据库信息

数据库名称：jdbc:h2:mem:testdb

## Maven Liquibase commands

### 迁移
```
mvn liquibase:update
```

### 回滚
```
mvn liquibase:rollback
```

Liquibase 提供了三种回滚的方式
- rollbackTag
    回滚到指定的Tag
    ```
        mvn liquibase:rollback -Dliquibase.rollbackTag=1.0
    ```
- rollbackCount
    指定回滚的changeset个数
    ```
       mvn liquibase:rollback -Dliquibase.rollbackCount=1 
    ```
- rollbackDate
    指定回滚到具体的日期
    ```
        mvn liquibase:rollback "-Dliquibase.rollbackDate=Jun 03, 2017"
    ```
 

## Liquibase Best Practices
1. changelog的文件名 + sprint version，如`db.changelog-1.1.xml` 标识spring 1.1的数据库迁移文件。
    或者通过文件夹名称
2. 分离DDL、DML为不同的changelog文件
3. 一个ChangeSet只作一次更新，避免在一个changeset中执行多个schema的变更
4. ChangeSet中Schema的变更需要考虑rollback的操作
5. 在ChangeSet中维护Comment注释，说明本次变更的详情

## References
- [liquibase](http://www.liquibase.org/index.html)
- [Introduction to Liquibase Rollback](https://www.baeldung.com/liquibase-rollback)