import React from 'react';
import ReactDOM from 'react-dom';
import App from '../App';
import {shallow, mount} from 'enzyme'
import Home from "../components/Home";
import NavigationBar from "../components/NavigationBar";
import SignInModal from "../components/SignInModal";
import BlogPostsGrid from "../components/BlogPostsGrid";
import BlogSpacesGrid from "../components/BlogSpacesGrid";
import ErrorModal from "../components/ErrorModal";



describe('Home component', ()=>{
    it('renders without crashing', () => {
        const div = document.createElement('div');
        ReactDOM.render(<App />, div);
    });

    it('renders navigation bar', () =>{
        const wrapper = shallow(<Home/>);
        const navBar = wrapper.find(NavigationBar);
        expect(navBar).not.toBe(undefined);
    });

    it('renders signInModal', ()=>{
        const wrapper = shallow(<Home/>);
        wrapper.setState({
            showSignInModal: true
        });
        const signInModal = wrapper.find(SignInModal);
        expect(signInModal).not.toBe(undefined);
    });

    it('shows blogSpaces initially',()=>{
        const wrapper = shallow(<Home/>);
        const blogSpaces = wrapper.find(BlogSpacesGrid);
        expect(blogSpaces).not.toBe(undefined);
    });

    it('shows posts',()=>{
        const wrapper = shallow(<Home/>);
        wrapper.setState({
            pageTypeVisible: 'blogPosts'
        });

        const blogPosts = wrapper.find(BlogPostsGrid);
        expect(blogPosts).not.toBe(undefined);
    });

    it('shows error modal',()=>{
        const wrapper = shallow(<Home/>);
        wrapper.setState({
            showErrorModal: true
        });

        const errorModal = wrapper.find(ErrorModal);
        expect(errorModal).not.toBe(undefined);
    })

});

