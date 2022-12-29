import { createBrowserRouter } from "react-router-dom";
import Login from "./pages/Login";
import Register from "./pages/Register";

export const router = createBrowserRouter([
//   {
//     path: "/",
//     element: <Home />,
//   },
  {
    path: "login",
    element: <Login/>,
  },
  {
    path: "register",
    element: <Register/>,
  },
]);