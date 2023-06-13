# 单字符串多字段查询：Multi Match
## 课程demo

### 三种场景
* 最佳字段（Best Feilds）
> 当字段之间相互竞争，又相互关联。例如title 和 body 这样的字段。评分来自最佳匹配字段
* 多数字段（Most Fields）
> 处理英文内容时：一种常见的手段时，在主字段使用（English Analyzer）抽取词干，把同义词，
> 进行时，过去时这些匹配为相同的文本。相同的文本，加入字段，再使用（standard Analyzer），
> 以提供更加精确的匹配。其他字段作为匹配文档提高相关度的信号。匹配字符越多则越好。
* 混合字段（Cross Fields）
> 对于某些实体，例如人名，地址，图书信息。需要在多个字段中确定信息，单个字段只能作为整体
> 的一部分。希望在任何这些列出的字段中找到尽可能多的词

```
POST blogs/_search
{
    "query": {
        "dis_max": {
            "queries": [
                { "match": { "title": "Quick pets" }},
                { "match": { "body":  "Quick pets" }}
            ],
            "tie_breaker": 0.2
        }
    }
}

POST blogs/_search
{
  "query": {
    "multi_match": {
      "type": "best_fields",
      "query": "Quick pets",
      "fields": ["title","body"],
      "tie_breaker": 0.2,
      "minimum_should_match": "20%"
    }
  }
}



POST books/_search
{
    "multi_match": {
        "query":  "Quick brown fox",
        "fields": "*_title"
    }
}


POST books/_search
{
    "multi_match": {
        "query":  "Quick brown fox",
        "fields": [ "*_title", "chapter_title^2" ]
    }
}



DELETE /titles
PUT /titles
{
    "settings": { "number_of_shards": 1 },
    "mappings": {
        "my_type": {
            "properties": {
                "title": {
                    "type":     "string",
                    "analyzer": "english",
                    "fields": {
                        "std":   {
                            "type":     "string",
                            "analyzer": "standard"
                        }
                    }
                }
            }
        }
    }
}

PUT /titles
{
  "mappings": {
    "properties": {
      "title": {
        "type": "text",
        "analyzer": "english"
      }
    }
  }
}

POST titles/_bulk
{ "index": { "_id": 1 }}
{ "title": "My dog barks" }
{ "index": { "_id": 2 }}
{ "title": "I see a lot of barking dogs on the road " }


GET titles/_search
{
  "query": {
    "match": {
      "title": "barking dogs"
    }
  }
}

DELETE /titles
PUT /titles
{
  "mappings": {
    "properties": {
      "title": {
        "type": "text",
        "analyzer": "english",
        "fields": {"std": {"type": "text","analyzer": "standard"}}
      }
    }
  }
}

POST titles/_bulk
{ "index": { "_id": 1 }}
{ "title": "My dog barks" }
{ "index": { "_id": 2 }}
{ "title": "I see a lot of barking dogs on the road " }

GET /titles/_search
{
   "query": {
        "multi_match": {
            "query":  "barking dogs",
            "type":   "most_fields",
            "fields": [ "title", "title.std" ]
        }
    }
}

GET /titles/_search
{
   "query": {
        "multi_match": {
            "query":  "barking dogs",
            "type":   "most_fields",
            "fields": [ "title^10", "title.std" ]
        }
    }
}

```
## 相关阅读
- https://www.elastic.co/guide/en/elasticsearch/reference/7.1/query-dsl-dis-max-query.html
