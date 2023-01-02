import { Link, useNavigate } from "react-router-dom";
import RoleConst from "../data/RoleConst";

const Header = () => {
    
    const navigate = useNavigate();

    const roleCode = JSON.parse(localStorage.getItem("data")).roleCode

    const logout = () => {
        navigate("/login")
        localStorage.removeItem("data")
    };

    return(
        <>
            <nav className="navbar navbar-expand-lg navbar-dark bg-primary mb-2">
                <div className="container-fluid">
                    <a className="navbar-brand">E-Learning</a>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                        aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                            <li className="nav-item">
                                <Link className="nav-link active" aria-current="page" to="/">Home</Link>
                            </li>
                            {roleCode === RoleConst.ROLE_SUPER_ADMIN && (
                                <li className="nav-item dropdown">
                                    <a className="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                        aria-expanded="false">
                                        Master Data
                                    </a>
                                    <ul className="dropdown-menu">
                                        <li><a className="dropdown-item" href="./classes.html">Activity</a></li>
                                        <li><a className="dropdown-item" href="./classes.html">Class</a></li>
                                        <li><a className="dropdown-item" href="./users.html">User</a></li>
                                    </ul>
                                </li>
                            )}
                            {roleCode === RoleConst.ROLE_INSTRUCTOR && (
                                <li className="nav-item">
                                    <a className="nav-link" href="./classes.html">Manage Class</a>
                                </li>
                            )}
                            {roleCode === RoleConst.ROLE_STUDENT && (
                                <>
                                    <li className="nav-item">
                                        <a className="nav-link" href="./classes-enroll.html">Enroll Class</a>
                                    </li>
                                    <li className="nav-item">
                                        <a className="nav-link" href="./className.html">My Learning</a>
                                    </li>
                                </>
                            )}
                        </ul>
                        <button type="button" className="btn btn-primary nav-item dropdown">
                            <a className="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown"
                                aria-expanded="false">Akun</a>
                            <ul className="dropdown-menu dropdown-menu-lg-end">
                                <li><a className="dropdown-item" href="./profile.html">Profil</a></li>
                                <li>
                                    <hr className="dropdown-divider"/>
                                </li>
                                <li><a className="dropdown-item" id="btnLogoutAdmin" onClick={logout}>Logout</a></li>
                            </ul>
                        </button>
                    </div>
                </div>
            </nav>
        </>
    )

};

export default Header
