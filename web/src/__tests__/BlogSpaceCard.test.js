import React from 'react';
import {shallow, mount} from 'enzyme'
import BlogSpaceCard from "../components/BlogSpaceCard";
import {Card} from "react-bootstrap";

describe('Blog space card component',()=>{
    it('shows blog space details',()=>{

        const wrapper = shallow(<BlogSpaceCard/>);
        wrapper.setProps({
            cardTitle:'testTitle'
        });

        expect(wrapper.find(Card.Title).text()).toBe('testTitle');
    })
});