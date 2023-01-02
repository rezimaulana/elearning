import RoleConst from "../data/RoleConst"
import HomeSuperAdmin from "./super-admin/HomeSuperAdmin"
import HomeInstructor from "./instructor/HomeInstructor"
import HomeStudent from "./student/HomeStudent"

const Home = () => {

    const roleCode = JSON.parse(localStorage.getItem("data")).roleCode

    return (
        <>
            {roleCode === RoleConst.ROLE_SUPER_ADMIN && <HomeSuperAdmin/>}
            {roleCode === RoleConst.ROLE_INSTRUCTOR && <HomeInstructor/>}
            {roleCode === RoleConst.ROLE_STUDENT && <HomeStudent/>}
        </>
    )

}

export default Home