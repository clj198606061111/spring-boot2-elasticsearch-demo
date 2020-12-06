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

### 查询总条数
```shell script
POST /book/_doc/_search
{
    "track_total_hits": true,
    "query":{
        "match_all":{

        }
    }
}
```

### term精确查询
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

### 滚动查询
滚动查询分2步，
- 第一步查询索引获取第一批数据和滚动id
- 第二步通过滚动id分批滚动获取数据
```shell script
## scroll 滚动id存活时间，单位s(秒),m（分钟）
## size 每批次返回的记录条数
GET /book/_doc/_search?scroll=10m&size=10

POST /_search/scroll
{
    "scroll":"10m",
    "scroll_id":"FGluY2x1ZGVfY29udGV4dF91dWlkDXF1ZXJ5QW5kRmV0Y2gBFmp5cHVpalpfVDYtM0xJb3FVMm5RSWcAAAAAAAAABBYzTGgxLS1BWFNaR1E1VlRTdVUyckhB"
}
```


## 参考
- https://blog.csdn.net/jacksonary/article/details/82729556
- https://blog.csdn.net/qq_37743433/article/details/105414974
- https://www.jianshu.com/p/8dddb044576d
