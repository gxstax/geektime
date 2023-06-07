
##### Term Query:
> Beautiful Mind 等效于Beautiful OR Mind

##### Phrase Query:
> Beautiful Mind 等效于Beautiful AND Mind. Phrase查询，还要求前后顺序保持一致。

```shell

// phrase
GET /movies/_search?q=title: Beautiful Mind
{
  "profile": "true"
}


// term query:查询条件使用括号
GET /movies/_search?q=title:(Beautiful Mind)
{
  "profile": "true"
}

// phrase query:查询条件使用引号
GET /movies/_search?q=title:"Beautiful Mind"
{
  "profile": "true"
}
```

##### 布尔类型查询
```shell
// BooleanQuery 查找美丽心灵 AND 同时包含
GET /movies/_search?q=title:(Beautiful AND Mind)
{
  "profile": "true"
}


// BooleanQuery 查找美丽心灵 包含Beautiful
// 但是不能包含 Mind
GET /movies/_search?q=title:(Beautiful NOT Mind)
{
  "profile": "true"
}

// BooleanQuery 查找美丽心灵 %2B 代表+号 必须包含Mind
GET /movies/_search?q=title:(Beautiful %2BMind)
{
  "profile": "true"
}
```

##### 通配符查询
```shell
// 通配符查询
GET /movies/_search?q=title:b*
{
  "profile": "true"
}


// 通配符查询 模糊匹配&近似度匹配
GET /movies/_search?q=title:beautifl~1
{
  "profile": "true"
}


// 通配符查询 模糊匹配&近似度匹配
GET /movies/_search?q=title:"Lord Rings"~2
{
  "profile": "true"
}

```