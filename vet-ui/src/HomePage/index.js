import React from 'react';
import { userService } from '../services';
import Appointments from '../Appointments';
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

    onLogout = ()=> {
        localStorage.clear();
        this.props.history.push('/login');
    }  

    render() {
        // const { user } = this.state;
        return (
                <div role="main" className="container-fluid">
                    <div class="dropdown">
                        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Add
                        <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="#" data-toggle="modal" data-target="#add-appointment-model">Add Appointment</a></li>
                            <li> <a href="#"  data-toggle="modal" data-target="#add-pet-owner-model">Add Pet Owner</a></li>
                            <li><a href="#"  data-toggle="modal" data-target="#add-vet-model">Add Vet</a></li>
                        </ul>
                    </div>
                    <Appointments />
                    <AddPetOwner />
                    <Vet />
                    <button className="btn btn-primary" onClick={this.onLogout}>Logout</button>
                </div>
        );
    }
}

export default HomePage;