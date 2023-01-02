import { createBrowserRouter } from "react-router-dom";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Home from "./pages/Home";
import RouteWithProtected from "./features/authentication/RouteWithProtected";
import UserSession from "./features/authentication/UserSession";
import ClassHdrSuperAdmin from "./pages/super-admin/class-hdr/ClassHdrSuperAdmin";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <RouteWithProtected Component={Home}/>,
  },
  {
    path: "login",
    element: <UserSession><Login/></UserSession>,
  },
  {
    path: "register",
    element:<UserSession><Register/></UserSession>,
  },
  {
    path: "class-hdr",
    children: [
      {
        path: "data",
        element: <RouteWithProtected Component={ClassHdrSuperAdmin}/>,
      }
    ]
  }
]);
