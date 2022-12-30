import React from "react";
import jwt_decode from "jwt-decode";
import ProtectedRoute from "./ProtectedRoute";
import Header from "../../layouts/Header";
import Login from "../../pages/Login";

const RouteWithProtected = ({ Component }) => {

    const token = localStorage.getItem("data");

    if (token) {
      const decodedToken = jwt_decode(token);
      if (decodedToken.exp < Math.floor(Date.now() / 1000)) {
        alert("Expired Token");
        return <Login />;
      }
    }

  return (
    <ProtectedRoute>
      <Header />
      <Component />
    </ProtectedRoute>
  );
};

export default RouteWithProtected;
