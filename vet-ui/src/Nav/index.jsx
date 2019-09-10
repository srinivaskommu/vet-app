import React, { Component } from 'react'
import { Menu } from 'semantic-ui-react'
import Pet from '../Pet'
import Vet from '../Vet'
import Appointments from '../Appointments'

export default class Nav extends Component {
  state = { activeItem: 'home' }

  handleItemClick = (e, { name }) => this.setState({ activeItem: name })

  render() {
    const { activeItem } = this.state

    return (
        <div>
             <Menu pointing secondary vertical>
        <Menu.Item
          name='home'
          active={activeItem === 'home'}
          onClick={this.handleItemClick}
        />
        <Menu.Item
          name='messages'
          active={activeItem === 'messages'}
          onClick={this.handleItemClick}
        />
        <Menu.Item
          name='friends'
          active={activeItem === 'friends'}
          onClick={this.handleItemClick}
        />
      </Menu>
      <div>
          {activeItem === 'home' && <Pet />} 
          {activeItem === 'messages' && <Vet />} 
          {activeItem === 'friends' && <Appointments />} 
      </div>
        </div>
     
    )
  }
}