import { createBrowserRouter } from "react-router-dom";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Home from "./pages/Home";
import RoleGuardRoute from "./features/authentication/RoleGuardRoute";
import UserSessionGuardRoute from "./features/authentication/UserSessionGuardRoute";
import RoleConst from "./data/RoleConst";
import ClassHdrSuperAdmin from "./pages/super-admin/class-hdr/ClassHdrSuperAdmin";
import ActivitySuperAdmin from "./pages/super-admin/activity/ActivitySuperAdmin";
import UserGuardRoute from "./features/authentication/UserGuardRoute";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <Home/>,
  },
  {
    path: "login",
    element: <UserSessionGuardRoute><Login/></UserSessionGuardRoute>,
  },
  {
    path: "register",
    element:<UserSessionGuardRoute><Register/></UserSessionGuardRoute>,
  },
  {
    path: "",
    children: [
      {
        path: "activities",
        element: <RoleGuardRoute Component={ActivitySuperAdmin} role={RoleConst.ROLE_SUPER_ADMIN}/>,
      }
    ]
  },
  {
    path: "",
    children: [
      {
        path: "class-hdr",
        element: <RoleGuardRoute Component={ClassHdrSuperAdmin} role={RoleConst.ROLE_SUPER_ADMIN}/>,
      }
    ]
  }
]);
