import { Button } from "../components/Button";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "../lib/axios"
import { ToastContainer, toast } from "react-toastify"
import 'react-toastify/dist/ReactToastify.css';
import { Input } from "../components/Input";

const Register = () => {

    const navigate = useNavigate()

    const [dataRegister, setDataRegister] = useState({
        data: {
            email: "",
            fullname: ""
        }
    })

    const login = () => {
        navigate("/login")
    }

    const dataHandle = (e) => {
        const {name, value} = e.target
        setDataRegister({
            data: {
                ...dataRegister.data,
                [name]: value
            }
        });
    }

    const register = () => {
        const emailRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
        const fullnameRegex = /^(?=.*[a-z]|.*[A-Z])(?=.{5,})/;

        if (dataRegister.data.email.length === 0 || !emailRegex.test(dataRegister.data.email)) {
            toast.error("Invalid e-mail!")
            return;
        }

        if (dataRegister.data.fullname.length === 0 || !fullnameRegex.test(dataRegister.data.fullname)) {
            toast.error("Invalid name!")
            return;
        }
        axios
            .post("/register", dataRegister.data)
            .then((result) => {
                toast.success(result.data.message, {autoClose: 1000})
                toast.success("Successful registration!", {autoClose: 1000})
                toast.success("Please login using the password that was sent to your email!", {autoClose: 3000})
                setTimeout(() => {
                    navigate('/login');
                }, 4000);
            })
            .catch((err) => {
                toast.error(err.response.data.message);
            })
    }

    return(
        <>
            <ToastContainer/>
            <div className="container-fluid bg-light">
                <div className="d-flex justify-content-center align-items-center vh-100">
                    <div className="card">
                        <div className="card-header">
                            <div className="row justify-content-center align-items-center">
                                <div className="col-md-7">
                                    E-Learning
                                </div>
                                <div className="col-md-5 mt-2 mb-1">
                                    <Button type={"button"} className={"btn btn-primary"} id={"btnLogin"}
                                        name={"btnLogin"} label={"Login"} onClick={login}/>
                                </div>
                            </div>
                        </div>
                        <div className="card-body">
                            <form className="justify-content-center">
                                <Input htmlFor={"email"} classNameLabel={"form-label"} label={"Email"} type={"email"}
                                    classNameInput={"form-control"} id={"email"} name={"email"} onChange={dataHandle}/>
                                <Input htmlFor={"fullname"} classNameLabel={"form-label"} label={"Fullname"} type={"text"}
                                    classNameInput={"form-control"} id={"fullname"} name={"fullname"} onChange={dataHandle}/>
                                <div className="btn-group mt-4">
                                    <Button type={"button"} className={"btn btn-primary"} id={"btnRegisterSubmit"}
                                        name={"btnRegisterSubmit"} label={"Register"} onClick={register}/>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )

}

export default Register