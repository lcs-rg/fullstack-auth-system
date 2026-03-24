import { useState } from "react";
import api from "../services/api";
import { useNavigate } from "react-router-dom";
import "../styles/auth.css";

export default function Register() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();

  async function handleRegister(e) {
    e.preventDefault();
    setLoading(true);
    setError("");

    try {
      await api.post("/api/auth/register", {
        name,
        email,
        password,
      });

      navigate("/");

    } catch {
      setError("Erro ao registrar usuário");
    } finally {
      setLoading(false);
    }
  }

  return (
  <div className="container">
    <form className="card" onSubmit={handleRegister}>
      <h2>Cadastro</h2>

      <input placeholder="Nome" onChange={e => setName(e.target.value)} />
      <input placeholder="Email" onChange={e => setEmail(e.target.value)} />
      <input type="password" placeholder="Senha" onChange={e => setPassword(e.target.value)} />

      <button type="submit">Cadastrar</button>
    </form>
  </div>
);
}