### Request Body Search

##### 排序
```shell
# 排序
POST kibana_sample_data_ecommerce/_search
{
  "sort": [{"order_date": "desc"}],
  "query": {
    "match_all": {}
  }
}


# source filtering 对结果字段过滤
POST kibana_sample_data_ecommerce/_search
{
  "_source": ["order_date"],
  "query": {
    "match_all": {}
  }
}

```

##### 脚本字段
```shell
# 脚本字段
POST kibana_sample_data_ecommerce/_search
{
  "script_fields": {
    "new_field": {
      "script": {
        "lang": "painless",
        "source": "doc['order_date'].value+'_hello'"
      }
    }
  },
  "from": 10,
  "size": 5,
  "query": {
    "match_all": {}
  }
}
```

##### 查询表达式
```shell
# Match Query 实现 Term
GET /movies/_search
{
  "query": {
    "match": {
      "title": "Last Christmas"
    }
  }
}


# Match Query 实现条件查询
GET /movies/_search
{
  "query": {
    "match": {
      "title": {
        "query": "Last Christmas",
        "operator": "and"
      }
    }
  }
}


# Match Phrase Query 实现  slop 说明中间可以有几个词
GET /movies/_search
{
  "query": {
    "match_phrase": {
      "title": {
        "query": "one love",
        "slop": 1
      }
    }
  }
}

```

##### Query String
```shell
# Query String

PUT /users/_doc/1
{
  "name": "Ruan Yiming",
  "about": "java, golang, node, swift, elastics"
}


PUT /users/_doc/2
{
  "name": "Li Yiming",
  "about": "Hadoop"
}


POST users/_search
{
  "query": {
    "query_string": {
      "default_field": "name",
      "query": "Ruan AND Yiming"
    }
  }
}

POST users/_search
{
  "query": {
    "query_string": {
      "fields": ["name", "about"],
      "query": "(Ruan AND Yiming) OR (Java AND Elasticsearch)"
    }
  }
}

# simple Query 默认operator 是 OR
POST users/_search
{
  "query": {
    "simple_query_string": {
      "query": "Ruan AND Yiming",
      "fields": ["name"]
    }
  }
}

POST users/_search
{
  "query": {
    "simple_query_string": {
      "query": "Ruan Yiming",
      "fields": ["name"],
      "default_operator": "AND"
    }
  }
}
```