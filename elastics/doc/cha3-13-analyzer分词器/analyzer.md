```shell
GET _analyze
{
  "analyzer": "standard",
  "text": "2 running Quick brown-foxes leap over lazy dogs in the summer evening."
}

GET _analyze
{
  "analyzer": "simple",
  "text": "2 running Quick brown-foxes leap over lazy dogs in the summer evening."
}

POST _analyze
{
  "analyzer": "standard",
  "text": "他说的确实在理"
}

POST _analyze
{
  "analyzer": "icu_analyzer",
  "text": "他说的确实在理"
}














```