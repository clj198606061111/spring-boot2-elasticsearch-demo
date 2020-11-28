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
1、查询所有
http://192.168.10.133:9200/book/_search?pretty

## 参考
- https://blog.csdn.net/jacksonary/article/details/82729556
- https://blog.csdn.net/qq_37743433/article/details/105414974
- https://www.jianshu.com/p/8dddb044576d
