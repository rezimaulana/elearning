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
                    <Link className="navbar-brand">E-Learning</Link>
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
                                    <Link className="nav-link dropdown-toggle" to="#" role="button" data-bs-toggle="dropdown"
                                        aria-expanded="false">
                                        Master Data
                                    </Link>
                                    <ul className="dropdown-menu">
                                        <li><Link className="dropdown-item" to="/activities">Activity</Link></li>
                                        <li><Link className="dropdown-item" to="/class-hdr">Class Header</Link></li>
                                        <li><Link className="dropdown-item" to="/users">User</Link></li>
                                    </ul>
                                </li>
                            )}
                            {roleCode === RoleConst.ROLE_INSTRUCTOR && (
                                <li className="nav-item">
                                    <Link className="nav-link" to="./classes.html">Manage Class</Link>
                                </li>
                            )}
                            {roleCode === RoleConst.ROLE_STUDENT && (
                                <>
                                    <li className="nav-item">
                                        <Link className="nav-link" to="./classes-enroll.html">Enroll Class</Link>
                                    </li>
                                    <li className="nav-item">
                                        <Link className="nav-link" to="./className.html">My Learning</Link>
                                    </li>
                                </>
                            )}
                        </ul>
                        <button type="button" className="btn btn-primary nav-item dropdown">
                            <Link className="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown"
                                aria-expanded="false">Account</Link>
                            <ul className="dropdown-menu dropdown-menu-lg-end">
                                <li><Link className="dropdown-item" to="./profile.html">Profile</Link></li>
                                <li>
                                    <hr className="dropdown-divider"/>
                                </li>
                                <li><Link className="dropdown-item" id="btnLogoutAdmin" onClick={logout}>Logout</Link></li>
                            </ul>
                        </button>
                    </div>
                </div>
            </nav>
        </>
    )

};

export default Header
