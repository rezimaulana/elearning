import { Navigate } from "react-router-dom";

const UserSession = ({ children }) => {
  const token = localStorage.getItem("data");
  if (token) {
    return <Navigate to="/" />;
  }
  return children;
};

export default UserSession;
