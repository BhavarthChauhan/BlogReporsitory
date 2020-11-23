import React from 'react';
import {shallow, mount} from 'enzyme'
import AddNewPost from "../components/AddNewPost";
import {Button, Form} from "react-bootstrap";

describe("Add new post component",()=>{

    it("shows form",()=>{
        const wrapper = shallow(<AddNewPost/>);
        const form = wrapper.find(Form);
        expect(form).not.toBe(undefined);
    });

    it('shows error when form is not filled',()=>{
        const wrapper = shallow(<AddNewPost/>);
        wrapper.setState({
            title:'test'
        });

        // Simulate submit button click
        wrapper.find(Button).simulate('click');
        expect(wrapper.state('error')).toBe('Missing fields');
    })

});