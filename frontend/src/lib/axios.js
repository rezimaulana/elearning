import axios from "axios";
import BaseUrlConst from "../data/BaseUrlConst";

export default axios.create({
  baseURL: BaseUrlConst.BASE_URL,
});
