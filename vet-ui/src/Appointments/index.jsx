import React from 'react';
import AddAppointment from './AddAppointment';
import SearchAppointments from './SearchAppointments';
import { APPOINTMENTS_URL } from '../config';
import baseService from '../services/base.service';
import moment from 'moment';

const Title = ({appointmentCount}) => {
  return (
    <div>
       <div>
          <h4>Appointments ({appointmentCount})</h4>
       </div>
    </div>
  );
}

moment().format('MMMM Do YYYY, h:mm:ss a')
const Appointment = ({appointment, remove}) => {

let now = moment(appointment.startTime); //todays date
let end = moment(appointment.endTime); // another date
let duration = moment.duration(end.diff(now));
  return (<tr>
        <td>{appointment.petId}</td>
        <td>{appointment.description}</td>
        <td>{moment(appointment.startTime).format('MMMM Do YYYY, h:mm:ss a')}</td>
        <td>{duration.minutes()}</td>
        <td>{appointment.veterinarian}</td>
        <td><button className="danger" onClick={() => {remove(appointment.id)}}>Delete</button></td>
      </tr>)
}

const AppointmentList = ({appointments, remove}) => {
  const appointmentNode = appointments.map((appointment) => {
    return (<Appointment appointment={appointment} key={appointment.id} remove={remove}/>)
  });
  return (<table class="table table-striped">
            <thead>
              <tr>
                <th>Pet Id</th>
                <th>Description</th>
                <th>Start Time</th>
                <th>Duration</th>
                <th>Vet Id</th>
              </tr>
            </thead>
            <tbody>{appointmentNode}</tbody></table>);
}

// Contaner Component
// Appointment Id
window.id = 0;

class Appointments extends React.Component{
  constructor(props){
    // Pass props to parent class
    super(props);
    // Set initial state
    this.state = {
      data: []
    }
  }
  // Lifecycle method
  componentDidMount(){
    // Make HTTP request
    this.getAppointments();
  }


  getAppointments = () => {
    baseService.get(APPOINTMENTS_URL)
    .then(function(response) {
      return response.json();
    })
      .then((res) => {
        
        // Set state with result
        if(res )
          this.setState({data:res});
          else
          this.setState({data:[]});
      });
  }

  getHeaders = () => {
    let token = localStorage.getItem('apiToken');
    return {
        'headers': {
            Authorization: 'Bearer '+ token
        }
    }
  }
  
  onAddAppointment = (appointment) => {
    // Assemble data
    // Update data
    baseService.post(APPOINTMENTS_URL, appointment)
    .then(function(response) {
      if(response.ok){
        return response.json();
      }
      else if (!response.ok) {
        throw Error(response.statusText);
      }

    })
       .then((res) => {
         this.getAppointments();
          // this.state.data.push(appointment);
          // this.setState({data: this.state.data});
       }).catch((error) => {
          alert("Failed to create appointment")
       });
  }
  // Handle remove
  handleRemove = (id) => {
    // Filter all appointments except the one to be removed
    const remainder = this.state.data.filter((appointment) => {
      return (appointment.id !== id);
    });
    // Update state with filter
    baseService.delete(APPOINTMENTS_URL+'/'+id)
    .then(function(response) {
      return response.json();
    })
      .then((res) => {
        this.setState({data: remainder});
      })
  }

  onSearchAppointment = (searchQuery) => {
    baseService.get(APPOINTMENTS_URL+'?'+searchQuery)
    .then(function(response) {
      return response.json();
    })
      .then((res) => {
        this.setState({data: res});
      })
  }

  render(){
    return (
        <div>
            <div className="container-fluid">
                {/* <button className="btn btn-primary" data-toggle="modal" data-target="#add-appointment-model">Add Appointment</button> */}
                <div className="card mt-3">
                    <div className="card-header">
                      {this.state.data &&  <Title appointmentCount={this.state.data.length}/> }
                        <SearchAppointments onSearchAppointment={this.onSearchAppointment} />
                    </div>
                    <AppointmentList
                        appointments={this.state.data}
                        remove={this.handleRemove}
                        />
                </div>
            </div>
            <AddAppointment onAddAppointment={this.onAddAppointment}/>
        </div>
      
    );
  }
}

export default Appointments;