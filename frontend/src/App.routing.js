import { createBrowserRouter } from "react-router-dom";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Home from "./pages/Home";
import RouteWithProtected from "./features/authentication/RouteWithProtected";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <RouteWithProtected Component={Home}/>,
  },
  {
    path: "login",
    element: <Login/>,
  },
  {
    path: "register",
    element: <Register/>,
  },
]);
