import { Navigate } from "react-router-dom";

const UserSessionGuardRoute = ({ children }) => {
  const token = localStorage.getItem("data");
  if (token) {
    return <Navigate to="/" />;
  }
  return children;
};

export default UserSessionGuardRoute;
