import { LOGIN_URL, PET_OWNER_URL } from '../config';
import BaseService from './base.service';

export const userService = {
    login,
    logout,
    getAll
};

function login(username, password) {

    return BaseService.post(LOGIN_URL, {username, password})
        .then(handleResponse)
        .then(user => {
            // login successful if there's a user in the response
            if (user) {
                // store user details and basic auth credentials in local storage 
                // to keep user logged in between page refreshes
                user.authdata = user.token;
                localStorage.setItem('user', JSON.stringify(user));
                localStorage.setItem('apiToken', user.token);
            }

            return user;
        });
}

function logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('user');
}

function getAll() {
    return BaseService.get(PET_OWNER_URL).then(handleResponse);
}

function handleResponse(response) {
    return response.text().then(text => {
        const data = text && JSON.parse(text);
        if (!response.ok) {
            if (response.status === 401) {
                // auto logout if 401 response returned from api
                logout();
                //location.reload(true);
            }

            const error = (data && data.message) || response.statusText;
            return Promise.reject(error);
        }

        return data;
    });
}