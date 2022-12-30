import RoleConst from "../data/RoleConst"
import VarConst from "../data/VarConst"
import HomeSuperAdmin from "./super-admin/HomeSuperAdmin"

const Home = () => {

    return (
        <>
            {VarConst.ROLE_CODE === RoleConst.ROLE_SUPER_ADMIN && <HomeSuperAdmin/>}
            {/* {VarConst.ROLE_CODE === RoleConst.ROLE_INSTRUCTOR && <HomeTeacher/>}
            {VarConst.ROLE_CODE === RoleConst.ROLE_STUDENT && <HomeStudent/>} */}
        </>
    )

}

export default Home