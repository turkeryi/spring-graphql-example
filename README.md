## spring-graphql-example

Sample application using the new Spring GraphQL library.

The application is basketball player registration for a basketball team. A Player can be added, deleted and also all players in the system can be listed.

#### Doc

```
http://localhost:8761/graphql
```

```graphql
query {
    players {
        id
        name
        surname
        position
    }
}
```

```graphql
mutation addPlayer($playerInput: PlayerInput!) {
  addPlayer(playerInput: $playerInput){
        id
        name
        surname
        position
    }
}
```

```graphql
mutation deletePlayer($id: ID!) {
  deletePlayer(id: $id)
}
```

or you can use postman collection within the project.
