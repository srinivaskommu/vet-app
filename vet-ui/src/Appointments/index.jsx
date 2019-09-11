import React from 'react';
import axios from 'axios';
import AddAppointment from './AddAppointment';
import SearchAppointments from './SearchAppointments';

const Title = ({appointmentCount}) => {
  return (
    <div>
       <div>
          <h4>Appointments ({appointmentCount})</h4>
       </div>
    </div>
  );
}


const Appointment = ({appointment, remove}) => {
  return (<tr>
        <td>{appointment.petId}</td>
        <td>{appointment.description}</td>
        <td>{appointment.startTime}</td>
        <td>{appointment.endTime}</td>
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
    this.apiUrl = 'http://localhost:8080/vsp/appointments'
  }
  // Lifecycle method
  componentDidMount(){
    // Make HTTP reques with Axios
    this.getAppointments();
  }

  getAppointments = () => {
    axios.get(this.apiUrl, this.getHeaders())
      .then((res) => {
        // Set state with result
        this.setState({data:res.data});
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
    axios.post(this.apiUrl, appointment, this.getHeaders())
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
      if(appointment.id !== id) return appointment;
    });
    // Update state with filter
    axios.delete(this.apiUrl+'/'+id)
      .then((res) => {
        this.setState({data: remainder});
      })
  }

  onSearchAppointment = (searchObj) => {
    axios.post(this.apiUrl+'/search', searchObj, this.getHeaders())
      .then((res) => {
        this.setState({data: res.data});
      })
  }

  render(){
    return (
        <div>
            <div className="container-fluid">
                {/* <button className="btn btn-primary" data-toggle="modal" data-target="#add-appointment-model">Add Appointment</button> */}
                <div className="card mt-3">
                    <div className="card-header">
                        <Title appointmentCount={this.state.data.length}/>
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