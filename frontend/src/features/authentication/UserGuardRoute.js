import { Navigate } from "react-router-dom";

const UserGuardRoute = ({ children }) => {
  const token = localStorage.getItem("data");
  if (!token) {
    return <Navigate to="/login" />;
  }
  return children;
};

export default UserGuardRoute;
