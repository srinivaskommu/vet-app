import React from 'react';
import { Link } from 'react-router-dom';

import { userService } from '../_services';
import Appointments from '../Appointments';
import AddAppointment from '../Appointments/AddAppointment'
import AddPetOwner from '../PetOwner';
import Vet from '../Vet';

class HomePage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            user: {},
            users: []
        };
    }

    componentDidMount() {
        this.setState({ 
            user: JSON.parse(localStorage.getItem('user')),
            users: { loading: true }
        });
        userService.getAll().then(users => this.setState({ users }));
    }

    render() {
        const { user } = this.state;
        // return (
        //     <div className="col-md-6 col-md-offset-3">
        //         <h1>Hi {user.firstName}!</h1>
            
        //         <p>
        //             <Link to="/login">Logout</Link>
        //         </p>
        //         <Nav />
        //     </div>
        // );

        return (
                <div role="main" className="container-fluid">
                    <div class="dropdown">
                        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Add
                        <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="#" data-toggle="modal" data-target="#add-appointment-model">Add Appointment</a></li>
                            {/* <li><a href="#"  data-toggle="modal" data-target="#add-pet-model">Add Pet</a></li> */}
                            <li> <a href="#"  data-toggle="modal" data-target="#add-pet-owner-model">Add Pet Owner</a></li>
                            <li><a href="#"  data-toggle="modal" data-target="#add-vet-model">Add Vet</a></li>
                        </ul>
                    </div>
                    <Appointments />
                    <AddPetOwner />
                    <Vet />
                </div>
        );
    }
}

export { HomePage };