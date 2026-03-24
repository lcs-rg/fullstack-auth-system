import { useState } from "react";
import api from "../services/api";
import { Link, useNavigate } from "react-router-dom";
import "../styles/auth.css";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  async function handleLogin(e) {
    e.preventDefault();

    try {
      const response = await api.post("/api/auth/login", {
        email,
        password,
      });

      localStorage.setItem("token", response.data.token);

      alert("Login funcionando!");

      navigate("/dashboard"); 

    } catch (err) {
      console.error(err);
      alert("Erro ao logar");
    }
  }

  return (
  <div className="container">
    <form className="card" onSubmit={handleLogin}>
      <h1>Login</h1>

      <input
        placeholder="Email"
        onChange={(e) => setEmail(e.target.value)}
      />

      <input
        type="password"
        placeholder="Senha"
        onChange={(e) => setPassword(e.target.value)}
      />

      <button type="submit">Entrar</button>

      <p>
        Não tem conta? <Link to="/register">Cadastre-se</Link>
      </p>
    </form>
  </div>
);
}