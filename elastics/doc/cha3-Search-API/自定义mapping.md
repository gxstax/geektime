##### 自定义mapping

```shell
# 设置 index 为false
DELETE users

# 自定义文档类型
PUT users
{
  "mappings": {
    "properties": {
      "firstName": {
        "type": "text"
      },
      "lastName": {
        "type":"text"
      },
      "mobile": {
        "type": "text",
        "index": false
      }
    }
  }
}

# 添加一条数据
PUT users/_doc/1
{
  "firstName": "Ma",
  "lastName": "Yi",
  "mobile": "12345678"
}


# 不能通过手机查查询（因为设置了mobile字段不作为索引）
POST users/_search
{
  "query": {
    "match": {
      "mobile": "12345678"
    }
  }
}



DELETE users

# 设定Null_value
PUT users
{
  "mappings": {
    "properties": {
      "firstName": {
        "type": "text"
      },
      "lastName": {
        "type":"text"
      },
      "mobile": {
        "type": "keyword",
        "null_value": "NULL"
      }
    }
  }
}


PUT users/_doc/1
{
  "firstName": "Ma",
  "lastName": "Yi",
  "mobile": null
}

PUT users/_doc/2
{
  "firstName": "Ma",
  "lastName": "Yi2"
}

# 尝试搜索为null的数据
GET users/_search
{
  "query": {
    "match": {
      "mobile": "NULL"
    }
  }
}


DELETE users
# 设置Copy to
PUT users
{
  "mappings": {
    "properties": {
      "firstName": {
        "type": "text",
        "copy_to": "fullName"
      },
      "lastName": {
        "type":"text",
        "copy_to": "fullName"
      }
    }
  }
}

PUT users/_doc/1
{
  "firstName": "Ma",
  "lastName": "Yi",
  "mobile": null
}

GET users/_search?q=fullName:(Ma Yi)

POST users/_search
{
  "query": {
    "match": {
      "fullName": {
        "query": "Ma Yi",
        "operator": "and"
      }
    }
  }
}


# 数组类型
PUT users/_doc/1
{
  "name": "onebird",
  "interests": "reading"
}

PUT users/_doc/1
{
  "name": "twobirds",
  "interests": ["reading", "music"]
}


POST users/_search
{
  "query": {
    "match_all": {}
  }
}


# 查看_mapping 结构
GET users/_mapping


```