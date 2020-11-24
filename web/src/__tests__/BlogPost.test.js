import React from 'react';
import {shallow, mount} from 'enzyme'
import BlogPost from "../components/BlogPost";
import {Card} from "react-bootstrap";

describe("Blog post component",()=>{

    it('shows blog details',()=>{
        const wrapper = shallow(<BlogPost/>);
        wrapper.setState({
            postDetails:{
                title:'testTitle',
                user:{
                    name:'testName'
                }
            }
        });

        const card = wrapper.find('#postCard');
        expect(card.text()).toContain('testTitle');
    });

    it('shows login card when userNotLoggedIn',()=>{
        const wrapper = shallow(<BlogPost/>);
        wrapper.setProps({
            userLoggedIn:false
        });

        const loginCard = wrapper.find('#loginCard');
        expect(loginCard).not.toBe(undefined);
    });

    it('shows comments when user logged in',()=>{
        const wrapper = shallow(<BlogPost/>);
        wrapper.setProps({
            userLoggedIn:true
        });

        const commentCard =  wrapper.find('#commentCard');
        expect(commentCard).not.toBe(undefined);

    })

});