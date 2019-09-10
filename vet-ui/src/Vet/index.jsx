import React from 'react';
import ListItems from '../ListItems';
class Vet extends React.Component {
    render(){
        return <ListItems apiUrl="http://localhost:8080/vsp/veterinarians" />
    }
}

export default Vet;