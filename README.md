### Простое API для блога с использованием GraphQL

Для создания поста и\или автора можно использовать следующий mutation:

```json
mutation {
  newPost(input:{
    title: "Simple post"
    authorId: 1
  }) {
    id
    title
  }
  newAuthor(input:{
    name: "Alex"
    age: 35
  }) {
    name
    age
    status
  }
}
```

Для поиска можно использовать следующий пример query:

```json
{
  recentPosts(limit: 0, offset: 5, orderBy: "id") {
    id
    title
    content
    status
  }
  authorsWithTopPosts {
    id
    name
    age
    posts {
      id
      title
    }
    status
  }
}
```

