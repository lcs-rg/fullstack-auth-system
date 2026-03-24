import { useState } from "react";
import api from "../services/api";

export default function UpdateEmail() {
  const [newEmail, setNewEmail] = useState("");
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");

  async function handleUpdate(e) {
    e.preventDefault();
    setMessage("");
    setError("");

    try {
      await api.put("/api/users/email", { newEmail });
      setMessage("Email atualizado com sucesso!");
    } catch {
      setError("Erro ao atualizar email");
    }
  }

  return (
    <form onSubmit={handleUpdate}>
      <h2>Atualizar Email</h2>

      <input
        placeholder="Novo email"
        required
        onChange={(e) => setNewEmail(e.target.value)}
      />

      <button type="submit">Atualizar</button>

      {message && <p style={{ color: "green" }}>{message}</p>}
      {error && <p style={{ color: "red" }}>{error}</p>}
    </form>
  );
}