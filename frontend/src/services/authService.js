import { api } from "./api";

export const login = async (data) => {
  const response = await api.post("/auth/login", data);

  localStorage.setItem("token", response.data.token);

  return response.data;
};

export const register = async (data) => {
  return api.post("/auth/register", data);
};