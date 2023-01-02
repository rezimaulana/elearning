import { useNavigate } from "react-router-dom"
import { useState } from "react"
import axios from "../lib/axios"
import { ToastContainer, toast } from "react-toastify"
import 'react-toastify/dist/ReactToastify.css';
import { Button } from "../components/Button"
import BaseUrlConst from "../data/BaseUrlConst"

const Login = () => {
    
    const navigate = useNavigate()
    
    const [email, setEmail] = useState("");

    const [password, setPassword] = useState("");
    
    const register = () => {
        navigate('/register')
    }

    const emailHandle = (e) => {
        setEmail(e.target.value);
    }
    
    const passwordHandle = (e) => {
        setPassword(e.target.value);
    }

    const login = () => {
        const emailRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
        const passwordRegex = /^(?=.*[a-z]|.*[A-Z])(?=.{5,})/;
        
        // good password recommend
        // const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})/;
        
        if (email.length === 0 || !emailRegex.test(email)) {
            toast.error("Invalid e-mail!")
            return;
        }

        if (password.length === 0 || !passwordRegex.test(password)) {
            toast.error("Invalid password!")
            return;
        }
        axios
            .post("/login", {
                email: email,
                password: password,
            })
            .then((result) => {
                console.log(result);
                localStorage.setItem("data", JSON.stringify(result.data));
                toast.success("Verified account! Welcome "+result.data.fullname+"!", {autoClose: 1000})
                toast.success("Successful login!", {autoClose: 1000})
                setTimeout(() => {
                    navigate('/');
                    
                }, 2000);
            })
            .catch((err) => {
                toast.error(err.response.data.message);
        })
    }

    return(
        <>
            <ToastContainer/>
            {console.log(BaseUrlConst.BASE_URL)}
            {console.log(BaseUrlConst.FILE_URL)}
            
            <div className="container-fluid bg-light">
                <div className="d-flex justify-content-center align-items-center vh-100">
                    <div className="card">
                        <div className="card-header">
                            <div className="row justify-content-center align-items-center">
                                <div className="col-md-7">
                                    E-Learning
                                </div>
                                <div className="col-md-5 mt-2 mb-1">
                                    <Button type={"button"} className={"btn btn-primary"} id={"btnRegister"} 
                                        name={"btnRegister"} label={"Register"} onClick={register}/>
                                </div>
                            </div>
                        </div>
                        <div className="card-body">
                            <form className="justify-content-center" noValidate>
                                <label htmlFor="email" className="form-label">Email</label>
                                <input type="email" className="form-control" id="email" name="email" onChange={emailHandle} required/>
                                <label htmlFor="password" className="form-label">Password</label>
                                <input type="password" className="form-control" id="password" name="password" onChange={passwordHandle} required/>
                                <div className="btn-group mt-3">
                                    <Button type={"button"} className={"btn btn-primary"} id={"btnLoginSubmit"}
                                        name={"btnLoginSubmit"} label={"Login"} onClick={login}/>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )

}

export default Login