import { useNavigate } from "react-router-dom";
import "../styles/dashboard.css"

export default function Dashboard() {
  const navigate = useNavigate();

  function handleLogout() {
    localStorage.removeItem("token");
    navigate("/");
  }

  return (
  <div className="container">
    <div className="card">
      <h1>Dashboard</h1>

      <button onClick={() => navigate("/updateEmail")}>
        Atualizar Email
      </button>

      <button onClick={() => navigate("/updatePassword")}>
        Atualizar Senha
      </button>

      <button onClick={handleLogout}>
        Logout
      </button>
    </div>
  </div>
);
}