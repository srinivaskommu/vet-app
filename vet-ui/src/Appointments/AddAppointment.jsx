import React, { Component } from 'react';
import InputMoment from 'input-moment';
import moment from 'moment';

class AddAppointment extends Component {

    state = {
        dateTime: moment().add(1, 'hour'),
        durration: 15,
        description: '',
        petId: 1,
        vetId: 1
    };

    onDateChange = (newDateTime) => {
        this.setState({ newDateTime });
    }

    onDurrationChange = (e) => {
        this.setState({ durration: e.target.value });
    }

    onAddAppointment = () => {
        let endTime = moment(this.state.dateTime).add(this.state.durration, 'minutes');
        
        this.props.onAddAppointment(
            {
                startTime: this.state.dateTime,
                endTime: endTime,
                petId: this.state.petId,
                veterinarian: this.state.vetId,
                description: this.state.description
            });
    }

    handleInputChange = (event) => {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;
    
        this.setState({
          [name]: value
        });
      }

    render() {
        let displayTime = this.state.dateTime.format('MMMM Do, YYYY (hh:mm a)');
        
        return (
            <div className="modal fade" id="add-appointment-model" tabIndex="-1" role="dialog">
                <div className="modal-dialog modal-dialog-centered modal-lg" role="document">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title">Add New Appointment</h5>
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div className="modal-body">
                            <div className="container">
                                <div className="row">
                                    <div className="col-md-6">
                                        <InputMoment
                                            moment={this.state.dateTime}
                                            minStep={1}
                                            hourStep={1}
                                            onChange={this.onDateChange}
                                        />
                                    </div>
                                    <div className="col-md-6">
                                        <div className="form-group">
                                            <label>Proposed Time</label>
                                            <input readOnly type="text" className="form-control" value={displayTime}/>
                                        </div>
                                        <div className="form-group">
                                            <label>Duration</label>
                                            <select onChange={this.onDurrationChange} className="form-control" value={this.state.durration}>
                                                <option value="15">15 min</option>
                                                <option value="30">30 min</option>
                                                <option value="45">45 min</option>
                                                <option value="60">60 min</option>
                                            </select>
                                        </div>
                                        <div className="form-group">
                                            <label>Pet ID</label>
                                            <input
                                                name="petId"
                                                value={this.state.petId}
                                                onChange={this.handleInputChange} />
                                        </div>
                                        <div className="form-group">
                                            <label>Vet ID</label>
                                            <input
                                                name="vetId"
                                                value={this.state.vetId}
                                                onChange={this.handleInputChange} />
                                        </div>
                                        <div className="form-group">
                                            <label>Description</label>
                                            <input
                                                name="description"
                                                value={this.state.description}
                                                onChange={this.handleInputChange} />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" data-dismiss="modal">Cancel</button>
                            <button onClick={this.onAddAppointment} type="button" className="btn btn-primary" data-dismiss="modal">Add Appointment</button>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default AddAppointment;