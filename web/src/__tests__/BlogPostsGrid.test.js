import React from 'react';
import {shallow, mount} from 'enzyme'
import BlogPostsGrid from "../components/BlogPostsGrid";
import {Card} from "react-bootstrap";
import BlogPostCard from "../components/BlogPostCard";
import AddNewPost from "../components/AddNewPost";
import BlogPost from "../components/BlogPost";

let layout = [
    {
        id:1,
        title:'testTitle',
        description:'testDesc'
    }
];

describe('Blog post grid component',()=>{

    it('shows posts',()=>{
        const wrapper = shallow(<BlogPostsGrid/>);
        wrapper.setState({
            posts:layout
        });
        const blogPostCard = wrapper.find(BlogPostCard);
        expect(blogPostCard).toHaveLength(layout.length);
    });

    it('shows login card when user not logged in',()=>{
        const wrapper = shallow(<BlogPostsGrid/>);
        wrapper.setProps({
            isUserLoggedIn: false
        });

        const card = wrapper.find(Card);
        expect(card.text()).toBe('Login to add a new post if space created by you');
    });

    it('shows add new post if user logged in and by the same user',()=>{
        const wrapper = shallow(<BlogPostsGrid/>);
        wrapper.setProps({
            isUserLoggedIn: true,
            userNameLoggedIn:'testUser'
        });

        wrapper.setState({
            spaceCreatedByUser:'testUser'
        });
        expect(wrapper.find(Card.Title).text()).toBe('Add new post')
    });

    it('does not show add new post if not logged in by same user',()=>{
        const wrapper = shallow(<BlogPostsGrid/>);
        wrapper.setProps({
            isUserLoggedIn: true,
            userNameLoggedIn:'testUser'
        });

        wrapper.setState({
            spaceCreatedByUser:'testUser1'
        });
        expect(wrapper.find(Card)).toHaveLength(0);
    });

    it('shows add new post component',()=>{
        const wrapper = shallow(<BlogPostsGrid/>);
        wrapper.setState({
            addNewPostVisible:true
        });
        expect(wrapper.find(AddNewPost)).not.toBe(undefined);
    });

    it('shows the blog post',()=>{
        const wrapper = shallow(<BlogPostsGrid/>);
        wrapper.setState({
            blogPostVisible:true
        });
        expect(wrapper.find(BlogPost)).not.toBe(undefined);
    })
});