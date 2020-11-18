import React from 'react';
import UserService from '../services/UserService';
import Table from 'react-bootstrap/Table'

class UserComponent extends React.Component {
    constructor() {
        super();
        this.state = {
            users: []
        }
    }

    componentDidMount() {
        UserService.getUsers()
            .then((response) => {
                this.setState({
                    users: response.data
                })
            })
    }

    render() {
        return (
            <div>
                <h1 className="text-center">
                    Users List
                </h1>

                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Username</th>
                            <th>password</th>
                        </tr>

                    </thead>

                    <tbody>
                    {
                        this.state.users.map(
                            user =>
                                <tr key={user.id}>
                                    <td>{user.firstName}</td>
                                    <td>{user.userName}</td>
                                    <td>{user.password}</td>
                                </tr>
                        )
                    }
                    </tbody>
                </Table>


            </div>

        )
    }
}

export default UserComponent;