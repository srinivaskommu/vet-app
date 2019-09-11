import React from 'react';
class SearchAppointments extends React.Component{
    constructor(props){
      // Pass props to parent class
      super(props);
      // Set initial state
      this.state = {
        petId: '',
        vetId: '',
        startTime: '',
        searchType: ''
      }
    }
  
    onSearchAppointment = () => {
        const {searchType, petId, vetId, startTime} = this.state;
        let searchObj;
        switch(searchType) {
            case 'Pet':
                searchObj = {petId}
                break;
            case 'Vet':
                searchObj = {vetId}
                break;
            case 'Date':
                searchObj = {startTime}
                break;
        
        }
      this.props.onSearchAppointment(searchObj)
    }
    onSearchTypeChange = ({target}) => {
     this.setState({searchType: target.value});
    }

    handleInputChange = (event) => {
        const target = event.target;
        const value =  target.value;
        const name = target.name;
    
        this.setState({
          [name]: value
        });
      }
  
    render(){
      const {searchType} = this.state;
      return (
        <div>
            <div className="form-group" style={{display: 'flex', justifyContent:'space-between'}}>
                <label for="search-dropdown">Search By:</label>
                <select className="form-control" id="search-dropdown" onChange={this.onSearchTypeChange}>
                    <option>Pet</option>
                    <option>Vet</option>
                    <option>Date</option>
                </select>
                {searchType === 'Pet' && <input name="petId" onChange={this.handleInputChange}/>}
                {searchType === 'Vet' && <input name="vetId" onChange={this.handleInputChange}/>}
                {searchType === 'Date' && <input type="date" name="startTime" onChange={this.handleInputChange}/>}
                <button className="btn btn-primary" onClick={this.onSearchAppointment}>Search</button>
            </div>
            
        </div>    
      );
    }
  }
  
  export default SearchAppointments;