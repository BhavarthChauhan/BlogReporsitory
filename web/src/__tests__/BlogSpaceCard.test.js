import React from 'react';
import {shallow, mount} from 'enzyme'
import BlogSpaceCard from "../components/BlogSpaceCard";
import Card from "react-bootstrap/Card";

describe('Blog space card component',()=>{
    it('shows blog space details',()=>{

        const wrapper = shallow(<BlogSpaceCard/>);
        wrapper.setProps({
            cardDescription:'testTitle'
        });

        expect(wrapper.find(Card.Text).text()).toBe('testTitle');
    })
});