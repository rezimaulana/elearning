import { useNavigate } from "react-router-dom";
import { Button } from "../components/Button";

const Register = () => {
    const navigate = useNavigate()
    const login = () => {
        navigate('/login')
    }
    return(
        <div className="container-fluid bg-light">
            <div className="d-flex justify-content-center align-items-center vh-100">
                <div className="card">
                    <div className="card-header">
                        <div className="row justify-content-center align-items-center">
                            <div className="col-md-7">
                                E-Learning
                            </div>
                            <div className="col-md-5 mt-2 mb-1">
                                <Button type={"button"} className={"btn btn-primary"} id={"btnLogin"} name={"btnLogin"} label={"Login"} onClick={login}/>
                            </div>
                        </div>
                    </div>
                    <div className="card-body">
                        <form className="justify-content-center">
                            <label htmlFor="email" className="form-label">Email</label>
                            <input type="email" className="form-control" id="email" name="email"/>
                            <label htmlFor="fullname" className="form-label mt-1">Nama Lengkap</label>
                            <input type="text" className="form-control" id="fullname" name="fullname"/>
                            <div className="btn-group mt-4">
                                <button type="button" className="btn btn-primary" id="btnRegister">Register</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Register