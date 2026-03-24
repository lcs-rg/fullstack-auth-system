import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "../pages/Login";
import Register from "../pages/Register";
import Dashboard from "../pages/Dashboard";
import UpdateEmail from "../pages/UpdateEmail";
import UpdatePassword from "../pages/UpdatePassword";
import PrivateRoute from "../components/PrivateRoute";

export default function RoutesApp() {
  return (
    <BrowserRouter>
      <Routes>

        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />

        <Route
          path="/dashboard"
          element={
            <PrivateRoute>
              <Dashboard />
            </PrivateRoute>
          }
        />

        <Route
          path="/updateEmail"
          element={
            <PrivateRoute>
              <UpdateEmail />
            </PrivateRoute>
          }
        />

        <Route
          path="/updatePassword"
          element={
            <PrivateRoute>
              <UpdatePassword />
            </PrivateRoute>
          }
        />

      </Routes>
    </BrowserRouter>
  );
}