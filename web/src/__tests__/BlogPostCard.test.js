import React from 'react';
import {shallow, mount} from 'enzyme'
import BlogPostCard from "../components/BlogPostCard";
import {Card} from "react-bootstrap";

describe('Blog post card component',()=>{

    it('shows post details',()=>{

        const wrapper = shallow(<BlogPostCard/>);
        wrapper.setProps({
            cardTitle:'testTitle'
        });
        expect(wrapper.find(Card.Title).text()).toBe('testTitle');
    });

    it('shows correct theme',()=>{
        const wrapper = shallow(<BlogPostCard/>);
        wrapper.setProps({
            cardTheme:'Dark'
        });
        const card = wrapper.find(Card);
        expect(card.prop('bg')).toBe('secondary');
    })


});