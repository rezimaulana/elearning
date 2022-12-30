const VarConst = {
  AUTH_STR: `Bearer ${JSON.parse(localStorage.getItem("data")).token}`,
  FULLNAME: JSON.parse(localStorage.getItem("data")).fullname,
  ROLE_CODE: JSON.parse(localStorage.getItem("data")).roleCode,
};

export default VarConst
