import { authHeader } from '../config';
class BaseService {

    getRequestOptions = (headers) => {
        return {
            method: 'GET',
            headers: headers || authHeader()
        };
    }

    postRequestOptions = (data, headers) => {
        return {
            method: 'POST',
            body: JSON.stringify(data),
            headers: headers || authHeader()
        };
    }

    deleteRequestOptions = (headers) => {
        return {
            method: 'DELETE',
            headers: headers || authHeader()
        };
    }
   
    get = (url, headers) => {
        return fetch(url, this.getRequestOptions(headers));
    }

    delete = (url, headers) => {
        return fetch(url, this.deleteRequestOptions(headers));
    }

    post = (url, data, headers) => {
        return fetch(url, this.postRequestOptions(data, headers));
    }
}
export default new BaseService();