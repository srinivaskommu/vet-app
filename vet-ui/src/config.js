export const authHeader = () => {
    // return authorization header with basic auth credentials
    let user = JSON.parse(localStorage.getItem('user'));

    if (user && user.authdata) {
        return { 'Authorization': 'Bearer ' + user.authdata,
                'Content-Type':'application/json' };
    } else {
        return {  'Content-Type':'application/json' };
    }
}

export const LOGIN_URL = "http://localhost:8080/vsp/login";
export const APPOINTMENTS_URL = "http://localhost:8080/vsp/appointments";
export const PET_OWNER_URL = "http://localhost:8080/vsp/petOwners";
export const VET_URL = "http://localhost:8080/vsp/veterinarians";