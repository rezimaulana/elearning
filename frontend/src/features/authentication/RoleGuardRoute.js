import React from "react";
import jwt_decode from "jwt-decode";
import UserGuardRoute from "./UserGuardRoute";
import Header from "../../layouts/Header";
import Login from "../../pages/Login";

const RoleGuardRoute = ({ Component, roleAuth }) => {

  const roleCode = JSON.parse(localStorage.getItem("data")).roleCode
  
  const token = localStorage.getItem("data");

    if (token) {
      const decodedToken = jwt_decode(token);
      if (decodedToken.exp < Math.floor(Date.now() / 1000)) {
        alert("Expired Token");
        localStorage.removeItem("data")
        return <Login />;
      }
    }

    if(roleAuth === roleCode){
      return (
        <UserGuardRoute>
          <Header/>
          <Component/>
        </UserGuardRoute>
      )
    } else {
      return (
        <h1>Forbidden</h1>
      )
    }

}

export default RoleGuardRoute;
