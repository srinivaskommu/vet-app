import React, { Component } from 'react';
import { VET_URL } from '../config';
import baseService from '../services/base.service';

class Vet extends Component {

    state = {
        firstName: "",
        lastName: "",
        phoneNumber: "",
        email: "",
        city: "",
        zipCode: ""
    };

    getHeaders = () => {
        let token = localStorage.getItem('apiToken');
        return {
            'headers': {
                'Authorization': 'Bearer '+ token
            }
        }
      }

    onAddVet = () => {
        const {firstName, lastName, phoneNumber, email, city, zipCode} = this.state;
        const ownerData = {
            firstName, lastName, phoneNumber, email, city, zipCode
        }
        
        baseService.post(VET_URL, ownerData)
        .then(function(response) {
            return response.json();
          })
       .then((res) => {
           this.resetData();
       });
    
    }

    handleInputChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;
    
        this.setState({
          [name]: value
        });
      }

    resetData = () => {
        this.setState({
                firstName: "",
                lastName: "",
                phoneNumber: "",
                email: "",
                city: "",
                zipCode: ""
            }
        );
    }


    render() {
        
        return (
            <div className="modal fade" id="add-vet-model" tabIndex="-1" role="dialog">
                <div className="modal-dialog modal-dialog-centered modal-lg" role="document">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title">Add Veterinarian</h5>
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div className="modal-body">
                            <div className="container">
                                    <div>
                                        <div className="form-group">
                                            <label>FirstName</label>
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
                                        <div className="form-group">
                                            <label>City</label>
                                            <input className="form-control"
                                                name="city"
                                                value={this.state.city}
                                                onChange={this.handleInputChange} />
                                        </div>
                                        <div className="form-group">
                                            <label>Zip Code</label>
                                            <input className="form-control"
                                                name="zipCode"
                                                value={this.state.zipCode}
                                                onChange={this.handleInputChange} />
                                        </div>
                                    </div>
                            </div>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" data-dismiss="modal" onClick={this.resetData}>Cancel</button>
                            <button onClick={this.onAddVet} type="button" className="btn btn-primary" data-dismiss="modal">Add Vet</button>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Vet;