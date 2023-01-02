const HomeStudent = () => {

    return(
        <>
            <div className="container-fluid bg-light">
                <div className="row g-1">
                    <div className="col-md-10 offset-md-1">
                        <div className="card mt-5">
                            
                            <div className="card-header">
                                Dashboard
                            </div>
                            <div className="card-body">
                                
                                <div className="row justify-content-evenly g-1">
                                    <div className="card text-bg-light col-md-3" style={{maxWidth : '18rem'}}>
                                        <div className="card-header">Kelas</div>
                                        <div className="card-body">
                                            <h5 className="card-title">Jumlah Kelas</h5>
                                            <p className="card-text">10</p>
                                        </div>
                                    </div>
                                    <div className="card text-bg-light col-md-3" style={{maxWidth : '18rem'}}>
                                        <div className="card-header">Nilai</div>
                                        <div className="card-body">
                                            <h5 className="card-title">Rata-rata Nilai</h5>
                                            <p className="card-text">9.1</p>
                                        </div>
                                    </div>
                                </div>

                                <div className="row justify-content-evenly g-1 mt-1">
                                    <div className="card text-bg-light col-md-3" style={{maxWidth : '18rem'}}>
                                        <div className="card-header">Materi</div>
                                        <div className="card-body">
                                            <h5 className="card-title">Jumlah Materi</h5>
                                            <p className="card-text">12</p>
                                        </div>
                                    </div>
                                    <div className="card text-bg-light col-md-3" style={{maxWidth : '18rem'}}>
                                        <div className="card-header">Jadwal</div>
                                        <div className="card-body">
                                            <h5 className="card-title">Jumlah Jadwal</h5>
                                            <p className="card-text">20</p>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </>       
    )

}

export default HomeStudent