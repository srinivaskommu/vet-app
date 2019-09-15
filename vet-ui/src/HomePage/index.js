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
        if(localStorage.getItem('apiToken')){
            console.log(localStorage.getItem('apiToken'));
        }else{
            localStorage.clear();
            this.props.history.push('/login');
        }
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
                   <div style={{textAlign:'right'}}><button className="btn btn-primary" onClick={this.onLogout}>Logout</button></div> 
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

                </div>
        );
    }
}

export default HomePage;