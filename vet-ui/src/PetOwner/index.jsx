import React, { Component } from 'react';
import BaseService from '../services/base.service';
import { PET_OWNER_URL } from '../config';

class AddPetOwner extends Component {

    state = {
        firstName: "",
        lastName: "",
        phoneNumber: "",
        email: "",
        name: "",
        spiecesType: "",
        age: "",
        pets: []
    };

    getHeaders = () => {
        let token = localStorage.getItem('apiToken');
        return {
            'headers': {
                'Authorization': 'Bearer '+ token
            }
        }
      }

    onAddOwner = () => {
        const {firstName, lastName, phoneNumber, email, pets} = this.state;
        const ownerData = {
            firstName, lastName, phoneNumber, email, pets
        }
        BaseService.post(PET_OWNER_URL, ownerData)
       .then((res) => {
           this.resetData();
           alert("Pet owner Successfully added ");

       });
    }

    resetData = () => {
        this.setState({
            firstName: "",
            lastName: "",
            phoneNumber: "",
            email: "",
            name: "",
            spiecesType: "",
            age: "",
            pets: []
        }
        )
    }

    handleInputChange = (event) => {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;
    
        this.setState({
          [name]: value
        });
      }

      onAddPet = ()=> {
          const {name, spiecesType, age, pets} = this.state;
          const newPets = [...pets];
          newPets.push({name, spiecesType, age});
          this.setState({
            name: '',
            spiecesType: '',
            age: '',
            pets: newPets
          })
      }

    render() {
        
        return (
            <div className="modal fade" id="add-pet-owner-model" tabIndex="-1" role="dialog">
                <div className="modal-dialog modal-dialog-centered modal-lg" role="document">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title">Add Pet Owner</h5>
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div className="modal-body">
                            <div className="container">
                                    <div>
                                        <div className="form-group">
                                            <label>Pet Owner FirstName</label>
                                            <input className="form-control"
                                                name="firstName"
                                                value={this.state.firstName}
                                                onChange={this.handleInputChange} />
                                        </div>
                                        <div className="form-group">
                                            <label>Last Name</label>
                                            <input className="form-control"
                                                name="lastName"
                                                value={this.state.lastName}
                                                onChange={this.handleInputChange} />
                                        </div>
                                        <div className="form-group">
                                            <label>Phone number</label>
                                            <input className="form-control"
                                                name="phoneNumber"
                                                value={this.state.phoneNumber}
                                                onChange={this.handleInputChange} />
                                        </div>
                                        <div className="form-group">
                                            <label>Email</label>
                                            <input className="form-control"
                                                name="email"
                                                value={this.state.email}
                                                onChange={this.handleInputChange} />
                                        </div>
                                        <div className="form-group" style={{display:'flex', justifyContent:'space-between'}}>
                                            <input 
                                                    placeholder="Pet Name"
                                                    name="name"
                                                    value={this.state.name}
                                                    onChange={this.handleInputChange} />
                                            <input 
                                                placeholder="Pet Type"
                                                name="spiecesType"
                                                value={this.state.spiecesType}
                                                onChange={this.handleInputChange} />

                                            <input
                                                placeholder="Pet Age"
                                                name="age"
                                                value={this.state.age}
                                                onChange={this.handleInputChange} />
                                            <button className="btn btn-primary" onClick={this.onAddPet}>Add Pet</button>
                                        </div>
                                        {this.state.pets.map(pet => {
                                            return (
                                                <div className="form-group">
                                                    <label>{pet.name}</label>
                                                    - <label>{pet.spiecesType}</label>
                                                    -  <label>{pet.age}</label>
                                                </div>
                                            );
                                        })}
                                        
                                    </div>
                            </div>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" data-dismiss="modal" onClick={this.resetData}>Cancel</button>
                            <button onClick={this.onAddOwner} type="button" className="btn btn-primary" data-dismiss="modal">Add Owner</button>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default AddPetOwner;