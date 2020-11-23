import React from 'react';
import {shallow, mount} from 'enzyme'
import ErrorModal from "../components/ErrorModal";
import Modal from 'react-bootstrap/Modal';

describe('Error modal component',()=>{
    it('shows error',()=>{
        const wrapper = shallow(<ErrorModal/>);
        wrapper.setProps({
            error:'testError'
        });

        expect(wrapper.find(Modal.Title).text()).toBe('testError');
    })
});