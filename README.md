# Simple User MongoDB (NoSQL) & H2 (SQL)
Simple signup and signin with spring boot and spring security json web token and combine database MongoDB (NoSQL) & H2 (SQL)

## Featured :
- Spring Boot 
- Spring Security
- Spring Data JPA
- JWT Authentication & Authorization
- JWT Token
- Refresh Token
- MongoDB
- H2 

## Roles
- USER
- ADMIN 
- ROOT
 
## API
Methods | Url | Action |
--- | --- | --- |
| POST | /api/auth/signup | signup new account |
| POST | /api/auth/signin | login an account |
| POST | /api/auth/refreshtoken | get new token from refresh token |
| GET | /api/test/all | public access content |
| GET | /api/test/user | user, admion, root access content |
| GET | /api/test/admin | admin, root access content |
| GET | /api/test/root | only root access content |

## Format JSON
### Signup
```
{
    "nama": "{name}",
    "email": "{email}",
    "username": "{username}",
    "password": "{password}",
    "roles": [
        {
        "id": {id_roles}
        "name":"{ROLE_"USER/ADMIN/ROOT"}"
        }
    ]
}
```

### Signin
```
{
    "username": "{username}",
    "password": "{password}",
}
```

### Refresh Token
```
{
    "refreshToken": "{refreshtoken}"
}
```
