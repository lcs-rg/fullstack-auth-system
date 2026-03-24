import { api } from "./api";

export const updateEmail = (data) => {
  return api.put("/users/email", data);
};

export const updatePassword = (data) => {
  return api.put("/users/password", data);
};