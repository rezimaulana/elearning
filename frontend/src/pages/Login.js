import { useNavigate } from "react-router-dom"
import { Button } from "../components/Button"

const Login = () => {
    const navigate = useNavigate()
    const register = () => {
        navigate('/register')
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
                                <Button type={"button"} className={"btn btn-primary"} id={"btnRegister"} name={"btnRegister"} label={"Register"} onClick={register}/>
                            </div>
                        </div>
                    </div>
                    <div className="card-body">
                        <form className="justify-content-center">
                            <label htmlFor="email" className="form-label">Email</label>
                            <input type="email" className="form-control" id="email" name="email" disabled />
                            <label htmlFor="password" className="form-label">Password</label>
                            <input type="password" className="form-control" id="password" name="password" disabled />
                            <p className="card-text mt-3">Klik tombol untuk login.</p>
                            <div className="btn-group">
                                <button type="button" className="btn btn-primary" id="btnLoginAdmin">Admin</button>
                                <button type="button" className="btn btn-secondary" id="btnLoginInstructor">Guru</button>
                                <button type="button" className="btn btn-info" id="btnLoginStudent">Siswa</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Login