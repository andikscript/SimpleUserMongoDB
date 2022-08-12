# Simple User MongoDB (NoSQL) & PostgreSQL (SQL)
Simple signup and signin with spring boot and spring security json web token and combine database MongoDB (NoSQL) & PostgreSQL (SQL)

## Featured :
- Spring Boot 
- Spring Security
- Spring Data JPA
- JWT Authentication & Authorization
- JWT Token
- Refresh Token
- MongoDB
- PostgreSQL
- Spring Mail
- Email confirmed for register
- SMS confirmed by verification code if user login
- Spring validation

## Roles
- USER
- ADMIN 
- ROOT
 
## API
Methods | Url | Action |
--- | --- | --- |
| POST | /api/auth/signup | signup new account |
| POST | /api/auth/signin | register confirmation by email an account |
| GET | /api/auth/{verify code}/verification | login an account by verify code send to sms |
| POST | /api/auth/refreshtoken | get new token from refresh token |
| POST | /api/auth/signout | logout an account |
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
    ],
    "phone": "{number phone}"
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
