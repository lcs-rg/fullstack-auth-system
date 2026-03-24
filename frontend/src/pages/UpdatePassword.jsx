import { useState } from "react";
import api from "../services/api";

export default function UpdatePassword() {
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");

  async function handleUpdate(e) {
    e.preventDefault();
    setMessage("");
    setError("");

    if (newPassword !== confirmPassword) {
      setError("Senhas não coincidem");
      return;
    }

    try {
      await api.put("/api/users/password", {
        currentPassword,
        newPassword,
        confirmPassword,
      });

      setMessage("Senha atualizada com sucesso!");
    } catch {
      setError("Erro ao atualizar senha");
    }
  }

  return (
    <form onSubmit={handleUpdate}>
      <h2>Atualizar Senha</h2>

      <input type="password" placeholder="Senha atual" required onChange={e => setCurrentPassword(e.target.value)} />
      <input type="password" placeholder="Nova senha" required onChange={e => setNewPassword(e.target.value)} />
      <input type="password" placeholder="Confirmar senha" required onChange={e => setConfirmPassword(e.target.value)} />

      <button type="submit">Atualizar</button>

      {message && <p style={{ color: "green" }}>{message}</p>}
      {error && <p style={{ color: "red" }}>{error}</p>}
    </form>
  );
}