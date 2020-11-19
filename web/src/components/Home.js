import React from "react";
import Navbar from "react-bootstrap/Navbar";
import UserComponent from "./UserComponent";
import NavigationBar from "./NavigationBar";
import SignInModal from "./SignInModal";
import UserService from "../services/UserService";

class Home extends React.Component{
    constructor() {
        super();
        this.state= {
            userLoggedIn:false,
            userName:undefined,
            showSignInModal:false
        };
        this.signInButtonClicked = this.signInButtonClicked.bind(this);
        this.toggleSignInModal = this.toggleSignInModal.bind(this);
        this.signInUser = this.signInUser.bind(this);
        this.logOutUser = this.logOutUser.bind(this);
    }

    signInButtonClicked(){
        this.setState({
            showSignInModal:true
        });
    }

    toggleSignInModal(){
        this.setState({
            showSignInModal:!this.state.showSignInModal
        });
    }

    signInUser(userName){
        this.setState({
            userName:userName,
            userLoggedIn:true,
            showSignInModal:false
        })
    }

   logOutUser(){
        this.setState({
            userLoggedIn:false,
            userName:undefined
        });
   }

    render() {
        return (
            <div>
                <NavigationBar
                    userLoggedIn={this.state.userLoggedIn}
                    userName={this.state.userName}
                    onSignInButtonClick={this.signInButtonClicked}
                    logOutUser={this.logOutUser}
                />

                <SignInModal
                    showSignInModal={this.state.showSignInModal}
                    closeSignInModal={this.toggleSignInModal}
                    signInUser={this.signInUser}
                    signUpUser={this.signUpUser}
                />


            </div>
        )
    }
}
export default Home;