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

const getById = async (id) => {
    try {
        const response = await axios.get(`${API_URL}/books/${id}`);
        return response.data;
    } catch(err) {
        throw(err);
    }
}

const save = async (payload) => {
    try {
        const response = await axios.post(`${API_URL}/books/`, payload);
    } catch(err) {
        throw(err);
    }
}

const update = async (payload) => {
    try {
        const response = await axios.update(`${API_URL}/books/`, payload);
    } catch(err) {
        throw(err);
    }
}

const deleteBook = async (id) => {
    try {
        const response = await axios.delete(API_URL + '/books/' + id);
    } catch(err) {
        throw(err);
    }
}

export default {
    list,
    deleteBook,
    save,
    update,
    getById,
}
