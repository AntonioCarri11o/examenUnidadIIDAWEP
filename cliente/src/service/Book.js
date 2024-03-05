import axios from "axios";
import { API_URL } from "@/utils/utils";


const list = async () => {
    try {
        const response = await axios.get(`${API_URL}/books/`);
        return response.data;
    } catch(err) {
        throw(err);
    }
}

export default {
    list,
}
