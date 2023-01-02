import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import BaseUrlConst from "../../../data/BaseUrlConst";
import axios from "../../../lib/axios";

const ClassHdrSuperAdmin = () => {

    const navigate = useNavigate();

    const authStr = `Bearer ${JSON.parse(localStorage.getItem("data")).token}`
    
    const [data, setData] = useState([]);

    useEffect(() => {
        axios
            .get(`class-hdr/data`, {
                headers: { Authorization: authStr },
            })
            .then((res) => {
                setData(res.data.data);
                console.log(res.data)
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

    return(
        <>
            <div class="container-fluid bg-light">
                <div class="row g-1">
                    <div class="col-md-10 offset-md-1">
                        <div class="card mt-5">
                            <div class="card-header">Data Kelas</div>
                            <div class="card-body">
                                <a href="./classes-insert.html" class="btn btn-primary">Create</a>
                                <div class="table-responsive">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Code</th>
                                                <th>Subject</th>
                                                <th>Description</th>
                                                <th>Instructor Email</th>
                                                <th>Fullname</th>
                                                <th>Logo</th>
                                                <th>Active</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        {data.map((val, idx) => {
                                            return (
                                                <tr>
                                                    <td>{idx+1}</td>
                                                    <td>{val.classHdrCode}</td>
                                                    <td>{val.classHdrSubject}</td>
                                                    <td>{val.classHdrDescription}</td>
                                                    <td>{val.insEmail}</td>
                                                    <td>{val.insFullname}</td>
                                                    <td>
                                                        <img src={BaseUrlConst.FILE_URL+val.fileId} id="class-hdr-photo-list" name="class-hdr-photo-list"/>
                                                    </td>
                                                    <td>
                                                        {val.isActive == true && 'true'}
                                                        {val.isActive == false && 'false'}
                                                    </td>
                                                    <td>
                                                        <div class="btn-group">
                                                            <a href="./classes-update.html" class="btn btn-primary">Update</a>
                                                            <a href="#" class="btn btn-danger" data-bs-toggle="modal"
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
                <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-body">
                                Apakah anda akan menghapus data ini?
                            </div>
                            <div class="modal-footer justify-content-md-start">
                                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Ya</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Tidak</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )

}

export default ClassHdrSuperAdmin