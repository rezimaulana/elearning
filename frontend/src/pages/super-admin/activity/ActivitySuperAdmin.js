import { useEffect, useState } from "react";
import { Link, Navigate, useNavigate } from "react-router-dom";
import RoleConst from "../../../data/RoleConst";
import ProtectedRoute from "../../../features/authentication/UserGuardRoute";
import axios from "../../../lib/axios";
import Home from "../../Home";

const ActivitySuperAdmin = () => {

    const navigate = useNavigate()

    const roleCode = JSON.parse(localStorage.getItem("data")).roleCode

    const authStr = `Bearer ${JSON.parse(localStorage.getItem("data")).token}`
    
    const [data, setData] = useState([]);

    useEffect(() => {
        axios
            .get(`activities/data`, {
                headers: { Authorization: authStr },
            })
            .then((res) => {
                setData(res.data.data);
            });
    }, []);
    
    const [dataDelete, setDataDelete] = useState({
        data: {
        idClassromm: 0,
        nameClass: "",
        },
    });

    const btnDelete = () => {
        console.log(dataDelete);
    };

    if(roleCode!=RoleConst.ROLE_SUPER_ADMIN){
        return(
                <ProtectedRoute><Home/></ProtectedRoute>
        )
    }

    return(
        <>
            <div className="container-fluid bg-light">
                <div className="row g-1">
                    <div className="col-md-10 offset-md-1">
                        <div className="card mt-5">
                            <div className="card-header">Activity Data</div>
                            <div className="card-body">
                                <Link to="./classes-insert.html" className="btn btn-primary">Create</Link>
                                <div className="table-responsive">
                                    <table className="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Code</th>
                                                <th>Type</th>
                                                <th>Active</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        {data.map((val, idx) => {
                                            return (
                                                <tr key={idx}>
                                                    <td>{idx+1}</td>
                                                    <td>{val.activityCode}</td>
                                                    <td>{val.activityType}</td>
                                                    <td>
                                                        {val.isActive === true && 'true'}
                                                        {val.isActive === false && 'false'}
                                                    </td>
                                                    <td>
                                                        <div className="btn-group">
                                                            <a href="./classes-update.html" className="btn btn-primary">Update</a>
                                                            <a href="#" className="btn btn-danger" data-bs-toggle="modal"
                                                                data-bs-target="#deleteModal">Delete</a>
                                                        </div>
                                                    </td>
                                                </tr>
                                        )})}
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                {/* Modal */}
                <div className="modal fade" id="deleteModal" tabIndex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-body">
                                Apakah anda akan menghapus data ini?
                            </div>
                            <div className="modal-footer justify-content-md-start">
                                <button type="button" className="btn btn-danger" data-bs-dismiss="modal">Ya</button>
                                <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Tidak</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </>
    )

}

export default ActivitySuperAdmin