# spring-boot2-elasticsearch-demo
spring-boot2-elasticsearch-demo


## ES基本操作
1. 查看集群的健康状态。
- http://192.168.10.133:9200/_cat/health?v
- http://192.168.10.133:9200/_cluster/health?pretty=true

2. 查看集群的索引数。
- http://192.168.10.133:9200/_cat/indices?v

3. 查看集群所在磁盘的分配状况
- http://192.168.10.133:9200/_cat/allocation?v

4. 查看集群的节点
- http://192.168.10.133:9200/_cat/nodes?v

## 查询
1. 查询所有
- http://192.168.10.133:9200/book/_search?pretty

2. 根据id查询
- http://192.168.10.133:9200/book/_doc/cHczGTXuxyN1ownvsUM7hFjHrBRsbL3u?pretty

## term精确查询
只能匹配"type" : "keyword"
```shell script
POST /book/_doc/_search
{
  "query": {
    "term": {
      "name.keyword" : "itclj_test_SKZHqtvKpa"
    }
  }
}
```

```shell script
POST /book/_doc/_search
{
  "query": {
    "match": {
      "name" : "itclj_test_55uSth7adT"
    }
  }
}
```

### bool must 多条件查询
```shell script
POST /book/_doc/_search
{
    "query":{
        "bool":{
            "must":[
                {
                    "match":{
                        "name":"itclj_test_SKZHqtvKpa"
                    }
                },
                {
                    "match":{
                        "author":"itclj_etL"
                    }
                }
            ]
        }
    }
}
```

### wildcard 模糊匹配
```shell script
POST /indexname/typename/_search
POST /book/_doc/_search
{
  "query": {
    "wildcard": {
      "name":  "*itclj*"
    }
  }
}
```


## 参考
- https://blog.csdn.net/jacksonary/article/details/82729556
- https://blog.csdn.net/qq_37743433/article/details/105414974
- https://www.jianshu.com/p/8dddb044576d
