import RoleConst from "../data/RoleConst"
import HomeSuperAdmin from "./super-admin/HomeSuperAdmin"

const Home = () => {

    const roleCode = JSON.parse(localStorage.getItem("data")).roleCode

    return (
        <>
            {roleCode === RoleConst.ROLE_SUPER_ADMIN && <HomeSuperAdmin/>}
            {/* {roleCode === RoleConst.ROLE_INSTRUCTOR && <HomeTeacher/>}
            {roleCode === RoleConst.ROLE_STUDENT && <HomeStudent/>} */}
        </>
    )

}

export default Home