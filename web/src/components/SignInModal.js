import React from "react";
import Modal from "react-bootstrap/Modal"
import Button from "react-bootstrap/Button";
import Tabs from "react-bootstrap/Tabs"
import Tab from "react-bootstrap/Tab"
import Form from "react-bootstrap/Form";
import UserService from "../services/UserService";

class SignInModal extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            signInUserName: '',
            signInPassword: '',
            signUpUserName: '',
            signUpName: '',
            signUpPassword: '',
            signUpConfirmPassword: '',
            error: undefined
        };

        this.handleSignInUserNameChange = this.handleSignInUserNameChange.bind(this);
        this.handleSignInPasswordChange = this.handleSignInPasswordChange.bind(this);

        this.handleSignUpNameChange = this.handleSignUpNameChange.bind(this);
        this.handleSignUpConfirmPasswordChange = this.handleSignUpConfirmPasswordChange.bind(this);
        this.handleSignUpPasswordChange = this.handleSignUpPasswordChange.bind(this);
        this.handleSignUpUserNameChange = this.handleSignUpUserNameChange.bind(this);

        this.handleSignInClick = this.handleSignInClick.bind(this);
        this.handleSignUpClick = this.handleSignUpClick.bind(this);

        this.tabClicked = this.tabClicked.bind(this);
    }

    handleSignInUserNameChange(e) {
        this.setState({
            signInUserName: e.target.value,
            error: undefined
        })
    }

    handleSignInPasswordChange(e) {
        this.setState({
            signInPassword: e.target.value,
            error: undefined
        })
    }

    handleSignUpNameChange(e) {
        this.setState({
            signUpName: e.target.value,
            error: undefined
        });
    }

    handleSignUpUserNameChange(e) {
        this.setState({
            signUpUserName: e.target.value,
            error: undefined
        });
    }

    handleSignUpPasswordChange(e) {
        this.setState({
            signUpPassword: e.target.value,
            error: undefined
        });
    }

    handleSignUpConfirmPasswordChange(e) {
        this.setState({
            signUpConfirmPassword: e.target.value,
            error: undefined
        });
    }

    handleSignInClick() {
        var errorString = '';
        if (this.state.signInUserName === '' || this.state.signInPassword === '') {
            errorString = "Missing input value"
        }

        if (errorString === '') {
            UserService.signInUser(this.state.signInUserName, this.state.signInPassword)
                .then((response) => {
                    if (response.data.statusOk) {
                        this.props.signInUser(this.state.signInUserName)
                    } else {
                        this.setState({
                            error: response.data.errorMessage
                        })
                    }
                }).catch(error=>{
                    this.props.showError(error.response.data);
            })
        } else {
            this.setState({
                error: errorString
            })
        }
    }

    handleSignUpClick() {

        if (this.state.signUpConfirmPassword === '' || this.state.signUpName === ''
            || this.state.signUpUserName === '' || this.state.signUpPassword === '') {
            this.setState({
                error: 'Missing input value'
            });
        } else {
            if (this.state.signUpPassword !== this.state.signUpConfirmPassword) {
                this.setState({
                    error: 'Passwords do not match'
                })
            } else {
                UserService.createUserAccount(this.state.signUpName, this.state.signUpUserName, this.state.signUpPassword)
                    .then((response) => {
                        if (response.data.statusOk) {
                            this.props.signInUser(this.state.signUpUserName)
                        } else {
                            this.setState({
                                error: response.data.errorMessage
                            });
                        }
                    }).catch((error)=>{
                        this.props.showError(error.response.data);
                })
            }
        }
    }

    tabClicked() {
        this.setState({
            error: undefined
        })
    }

    render() {
        return (
            <div>
                <Modal show={this.props.showSignInModal} onHide={this.props.closeSignInModal}>
                    <Tabs defaultActivationKey="SignIn" onSelect={this.tabClicked}>
                        <Tab eventKey="SignIn" title="Sign In">
                            <Modal.Header>
                                <Modal.Title>Sign In</Modal.Title>
                            </Modal.Header>

                            <Modal.Body>
                                <Form>
                                    <Form.Group controlId="formUserName">
                                        <Form.Label>User Name</Form.Label>
                                        <Form.Control type="email" placeholder="User Name"
                                                      onChange={this.handleSignInUserNameChange}/>
                                    </Form.Group>

                                    <Form.Group controlId="formBasicPassword">
                                        <Form.Label>Password</Form.Label>
                                        <Form.Control type="password" placeholder="Password"
                                                      onChange={this.handleSignInPasswordChange}/>
                                    </Form.Group>
                                </Form>

                            </Modal.Body>

                            <Modal.Footer>
                                {this.state.error ? <p>{this.state.error}</p>   : undefined}
                                <Button variant="secondary" onClick={this.props.closeSignInModal}>Close</Button>
                                <Button variant="primary" onClick={this.handleSignInClick}>Sign In</Button>
                            </Modal.Footer>
                        </Tab>
                        <Tab eventKey="SignUp" title="Sign Up">
                            <Modal.Header>
                                <Modal.Title>Sign Up</Modal.Title>
                            </Modal.Header>

                            <Modal.Body>
                                <Form>
                                    <Form.Group controlId="formUserName">
                                        <Form.Label>Name</Form.Label>
                                        <Form.Control type="email" placeholder="Enter Name"
                                                      onChange={this.handleSignUpNameChange}/>
                                    </Form.Group>

                                    <Form.Group controlId="formUserName">
                                        <Form.Label>User Name</Form.Label>
                                        <Form.Control type="email" placeholder="User Name"
                                                      onChange={this.handleSignUpUserNameChange}/>
                                    </Form.Group>

                                    <Form.Group controlId="formBasicPassword">
                                        <Form.Label>Password</Form.Label>
                                        <Form.Control type="password" placeholder="Password"
                                                      onChange={this.handleSignUpPasswordChange}/>
                                    </Form.Group>

                                    <Form.Group controlId="formBasicPassword">
                                        <Form.Label>Confirm Password</Form.Label>
                                        <Form.Control type="password" placeholder="Confirm Password"
                                                      onChange={this.handleSignUpConfirmPasswordChange}/>
                                    </Form.Group>
                                </Form>
                            </Modal.Body>

                            <Modal.Footer>
                                {this.state.error ? <p id='errorString'>{this.state.error}</p>:undefined}
                                <Button variant="secondary" onClick={this.props.closeSignInModal}>Close</Button>
                                <Button variant="primary" onClick={this.handleSignUpClick}>Create Account</Button>
                            </Modal.Footer>
                        </Tab>
                    </Tabs>
                </Modal>
            </div>
        )
    }
}

export default SignInModal;