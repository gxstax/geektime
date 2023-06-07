```shell
GET _cluster/health
GET _cat/nodes
GET _cat/shards

// 查看索引相关信息
GET kibana_sample_data_ecommerce


// create document 自动生成id
POST users/_doc
{
  "user" : "Mike",
  "post_date": "2023-06-005T12:30:25",
  "message": "trying out Kibana"
}

// create document 指定id，如果id存在，报错
PUT users/_doc/1?op_type=create
{
  "user" : "Jack",
  "post_date": "2023-06-005T12:30:25",
  "message": "trying out Elasticsearch"
}

GET users/_doc/1

PUT users/_doc/1
{
  "user": "Mike"
}

// 在原文档增加字段
POST users/_update/1/
{
  "doc": {
    "post_date": "2023-06-005T12:30:25",
    "message": "trying out Elasticsearch"
  }
}

// 执行 第一次
POST _bulk
{ "index": {"_index": "test", "_id": "1"} }
{ "field1": "value1"}
{ "delete": {"_index": "test", "_id": "2"} }
{ "create": {"_index": "test2", "_id": "3"} }
{ "field1": "value3"}
{ "update": {"_id": "1", "_index": "test"} }
{ "doc": {"field2": "value2"} }

GET test/_doc/1

// 执行 第二次
POST _bulk
{ "index": {"_index": "test", "_id": "1"} }
{ "field1": "value1"}
{ "delete": {"_index": "test", "_id": "2"} }
{ "create": {"_index": "test2", "_id": "3"} }
{ "field1": "value3"}
{ "update": {"_id": "1", "_index": "test"} }
{ "doc": {"field2": "value2"} }











```