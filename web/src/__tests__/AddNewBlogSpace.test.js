import React from 'react';
import {shallow, mount} from 'enzyme'
import AddNewBlogSpace from "../components/AddNewBlogSpace";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

describe('Add new blog space component',()=>{

    it('shows form fields',()=>{
        const wrapper = shallow(<AddNewBlogSpace/>);
        const form = wrapper.find(Form);
        expect(form).not.toBe(undefined);
    });

    it('shows error when field is missing',()=>{
        const wrapper = shallow(<AddNewBlogSpace/>);
        wrapper.setState({
            spaceName:'test',
            description:''
        });

        //Simulate submit button click
        wrapper.find(Button).simulate('click');
        expect(wrapper.state('error')).toBe('Missing input value')
    })

});