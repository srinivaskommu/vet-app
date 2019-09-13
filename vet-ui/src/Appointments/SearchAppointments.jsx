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
        let searchQuery;
        switch(searchType) {
            case 'Pet':
                searchQuery = `petId=${petId}`;
                break;
            case 'Vet':
                searchQuery = `vetId=${vetId}`;
                break;
            case 'Date':
                searchQuery = `date=${startTime}`;
                break;
            default:
              searchQuery = '';
        
        }
      this.props.onSearchAppointment(searchQuery)
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
                <select className="form-control col-sm-4" id="search-dropdown" onChange={this.onSearchTypeChange}>
                    <option></option>
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