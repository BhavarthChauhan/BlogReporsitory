import React from 'react';
import {shallow, mount} from 'enzyme'
import BlogSpacesGrid from "../components/BlogSpacesGrid";
import BlogSpaceCard from "../components/BlogSpaceCard";
import Card from "react-bootstrap/Card";
import AddNewBlogSpace from "../components/AddNewBlogSpace";


const layout = [{
    id:1,
    spaceName:'testName',
    description:'testDesc'
}];

describe('Blog spaces grid component', ()=>{

    it('shows cards',()=>{
        const wrapper = shallow(<BlogSpacesGrid/>);
        wrapper.setState({
            blogSpaces: layout
        });
        const cards = wrapper.find(BlogSpaceCard);
        expect(cards).toHaveLength(layout.length)
    });

    it('shows login card when not logged in',()=>{
        const wrapper = shallow(<BlogSpacesGrid/>);
        wrapper.setState({
            blogSpaces: layout
        });
        wrapper.props({isUserLoggedIn:false});
        const card = wrapper.find(Card.Title);
        expect(card.text()).toBe('Login to add a new blog space');
    });

    it('shows add new space card',()=>{
        const wrapper = shallow(<BlogSpacesGrid/>);
        wrapper.setState({
            blogSpaces: layout
        });
        wrapper.setProps({isUserLoggedIn:true});
        const card = wrapper.find(Card.Title);
        expect(card.text()).toBe('Add new blog space');
    });

    it('shows add new blog space',()=>{
        const wrapper = shallow(<BlogSpacesGrid/>);
        wrapper.setState({
            blogSpaces: layout,
            addNewBlogSpaceVisible:true
        });
        const addNewBlogSpace = wrapper.find(AddNewBlogSpace);
        expect(addNewBlogSpace).not.toBe(undefined);
    })
});

