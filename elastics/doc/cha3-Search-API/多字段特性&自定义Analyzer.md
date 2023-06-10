##### 多字段特性&自定义Analyzer

```shell
POST _analyze
{
  "tokenizer": "keyword",
  "char_filter": ["html_strip"],
  "text": "<b>hello world</b>"
}

# 使用char filter 进行替换
POST _analyze
{
  "tokenizer": "standard",
  "char_filter": [
    {
      "type": "mapping",
      "mappings": ["- => _"]
    }
  ],
  "text": "123-456, I-test! test-990 650-555-1234"
}

# char filter 替换表情符号
POST _analyze
{
  "tokenizer": "standard",
  "char_filter": [
    {
      "type": "mapping",
      "mappings": [":) => happy", ":( => sad"]
    }
  ],
  "text": ["I am felling :)", "Feeling :( today"]
}

# 正则表达式
POST _analyze
{
  "tokenizer": "standard",
  "char_filter": [
    {
      "type": "pattern_replace",
      "pattern": "http://(.*)",
      "replacement": "$1"
    }
  ],
  "text": "http://www.elastic.co"
}

# tokenizer

POST _analyze
{
  "tokenizer": "path_hierarchy",
  "text": "/user/mayi/a/b/c/d/e"
}


POST _analyze
{
  "tokenizer": "whitespace",
  "filter": ["stop"], 
  "text": ["The Rain in Spain falls mainly on the plain."]
}

POST _analyze
{
  "tokenizer": "whitespace",
  "filter": ["lowercase","stop"], 
  "text": ["The gilrs in China are playing this game"]
}

```