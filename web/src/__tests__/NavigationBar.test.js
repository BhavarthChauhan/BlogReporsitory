import React from 'react';
import {shallow, mount} from 'enzyme'
import NavigationBar from "../components/NavigationBar";
import Button from "react-bootstrap/Button";


describe('Navigation bar component',()=>{

    it('shows signIn button on blog spaces page',()=>{
        const wrapper = shallow(<NavigationBar/>);
        wrapper.setProps({
            pageTypeVisible:'blogSpaces',
            userLoggedIn:false
        });

        expect(wrapper.find(Button).text()).toBe('Sign in / Create Account')

    });

    it('shows logout button when user logged in',()=>{
        const wrapper = shallow(<NavigationBar/>);
        wrapper.setProps({
            pageTypeVisible:'blogSpaces',
            userLoggedIn:true
        });
        expect(wrapper.find(Button).text()).toBe('Log out')
    });

    it('shows button to go back to blog spaces when user not logged in',()=>{
        const wrapper = shallow(<NavigationBar/>);
        wrapper.setProps({
            pageTypeVisible:'blogPosts',
            userLoggedIn:false
        });
        // Shows 2 buttons
        expect(wrapper.find(Button)).toHaveLength(2);

    });

    it('shows button to go back to blog spaces when user logged in',()=>{
        const wrapper = shallow(<NavigationBar/>);
        wrapper.setProps({
            pageTypeVisible:'blogPosts',
            userLoggedIn:true
        });
        // Shows 2 buttons
        expect(wrapper.find(Button)).toHaveLength(2);

    })
});