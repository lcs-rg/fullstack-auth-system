import { createContext, useState, useEffect } from "react";
import api from "../services/api";

export const AuthContext = createContext();

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);

  async function loadUser() {
    try {
      const response = await api.get("/api/users/me");
      setUser(response.data);
    } catch (err) {
      logout();
    }
  }

  function login(token) {
    localStorage.setItem("token", token);
    loadUser();
  }

  function logout() {
    localStorage.removeItem("token");
    setUser(null);
    window.location.href = "/";
  }

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      loadUser();
    }
  }, []);

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}