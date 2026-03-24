# 🔐 Sistema de Autenticação de Usuários

Projeto fullstack de autenticação utilizando **Spring Boot (backend)** e **React (frontend)**, com foco em boas práticas como **DDD, JWT e segurança com Spring Security**.

---

## 📌 Sobre o projeto

Este projeto implementa um sistema completo de autenticação de usuários com:

- Registro de usuário
- Login com JWT
- Atualização de email
- Atualização de senha
- Rotas protegidas
- Logout no frontend

O objetivo foi simular um cenário real de aplicação com separação de responsabilidades e arquitetura limpa.

---

## 🧠 Arquitetura

### Backend (DDD)

```
src/main/java/
 ├── auth
 │   ├── application
 │   ├── domain
 │   └── infrastructure
 │
 ├── user
 │   ├── application
 │   ├── domain
 │   └── infrastructure
 │
 ├── interfaces
 │   └── http
 │
 ├── shared
 │   ├── config
 │   ├── exception
 │   └── security
```

✔ Separação por domínio  
✔ UseCases isolados  
✔ Controllers desacoplados  

---

### Frontend (React)

```
src/
 ├── app
 ├── assets
 ├── components
 ├── context
 ├── hooks
 ├── pages
 ├── services
 ├── styles
 ├── App.jsx
 ├── App.css
```

✔ Organização modular  
✔ Context API para autenticação  
✔ Rotas protegidas  
✔ Integração com backend via Axios  

---

## 🔐 Fluxo de autenticação

1. Usuário faz login  
2. Backend gera JWT  
3. Token é armazenado no localStorage  
4. Requests autenticados enviam o token no header:

```
Authorization: Bearer <token>
```

5. Backend valida o token com filtro JWT  

---

## 🛠️ Tecnologias

### Backend
- Java 24
- Spring Boot
- Spring Security
- JWT
- JPA / Hibernate
- PostgreSQL

### Frontend
- React
- React Router
- Axios

---

## ⚙️ Como rodar o projeto

### 🔹 Backend

```bash
cd backend/Autenticacao-Usuarios
mvn spring-boot:run
```

Servidor roda em:
```
http://localhost:8080
```

---

### 🔹 Frontend

```bash
cd frontend
npm install
npm run dev
```

Aplicação roda em:
```
http://localhost:5173
```

---

## 🔗 Endpoints principais

### Auth
- POST /api/auth/register
- POST /api/auth/login

### Usuário
- PUT /api/users/email
- PUT /api/users/password
- GET /api/users/me

---

## 🎨 Melhorias de UX implementadas

- Redirecionamento automático após login
- Proteção de rotas (Dashboard)
- Feedback com alertas
- Navegação entre login e cadastro
- Logout funcional

---

## 🚧 Melhorias futuras

- Refresh Token
- Validação de formulários (Yup/Formik)
- Testes automatizados
- Docker (containerização da aplicação)
- Deploy em cloud (Render, AWS, Vercel)
- Integração CI/CD
- Dark mode

---

## 📚 Aprendizados

- Implementação completa de autenticação JWT
- Integração front-back
- Uso de DDD na prática
- Controle de estado com Context API
- Segurança com Spring Security

---

## 👨‍💻 Autor

**Lucas Rocha Guerra**

[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/lcs-rg)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/lcsrg)

---

## ⭐ Se esse projeto te ajudou, deixe uma estrela!
---

## ⭐ Se esse projeto te ajudou, deixe uma estrela!