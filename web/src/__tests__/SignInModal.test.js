import React from 'react';
import {shallow, mount} from 'enzyme'
import SignInModal from "../components/SignInModal";
import {Form, Modal, Tab} from "react-bootstrap";
import Button from 'react-bootstrap/Button'
describe('Sign in modal',()=>{

    it('shows sign in form',()=>{
        const wrapper = shallow(<SignInModal/>);
        const tab = wrapper.find(Tab).at(1);
       expect(tab.find(Form)).not.toBe(undefined);
    });

    it('shows error when submit button clicked with incomplete sign in form',()=>{

        const wrapper = shallow(<SignInModal/>);
        wrapper.setState({
            signInUserName:'testUser',
            signInPassword:''
        });
        const tab = wrapper.find(Tab).at(0);
        const modalFooter = tab.find(Modal.Footer);

        const submitButton = modalFooter.find(Button).at(1);
        // Clicked submit button
        submitButton.simulate('click');
        expect(wrapper.state('error')).toBe('Missing input value');

    });

    it('shows error when submit button clicked with incomplete sign up form',()=>{

        const wrapper = shallow(<SignInModal/>);
        wrapper.setState({
            signUpName:'testUser',
            signUpPassword:''
        });
        const tab = wrapper.find(Tab).at(1);
        const modalFooter = tab.find(Modal.Footer);

        const submitButton = modalFooter.find(Button).at(1);
        // Clicked submit button
        submitButton.simulate('click');
        expect(wrapper.state('error')).toBe('Missing input value');

    })

});