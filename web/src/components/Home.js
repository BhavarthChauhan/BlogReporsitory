import React from "react";
import NavigationBar from "./NavigationBar";
import SignInModal from "./SignInModal";
import BlogPostsGrid from "./BlogPostsGrid";
import BlogPost from "./BlogPost";
import BlogSpacesGrid from "./BlogSpacesGrid";

class Home extends React.Component {
    constructor() {
        super();
        this.state= {
            userLoggedIn:false,
            userName:undefined,
            showSignInModal:false,
            pageTypeVisible:'blogSpaces',
            blogSpaceId:undefined
        };
        this.signInButtonClicked = this.signInButtonClicked.bind(this);
        this.toggleSignInModal = this.toggleSignInModal.bind(this);
        this.signInUser = this.signInUser.bind(this);
        this.logOutUser = this.logOutUser.bind(this);

        this.renderBlogSpaces = this.renderBlogSpaces.bind(this);
        this.renderPosts = this.renderPosts.bind(this);

        this.showPostsForBlogSpace = this.showPostsForBlogSpace.bind(this);
        this.onViewBlogSpacesClicked = this.onViewBlogSpacesClicked.bind(this);
    }

    signInButtonClicked() {
        this.setState({
            showSignInModal: true
        });
    }

    toggleSignInModal() {
        this.setState({
            showSignInModal: !this.state.showSignInModal
        });
    }

    signInUser(userName) {
        this.setState({
            userName: userName,
            userLoggedIn: true,
            showSignInModal: false
        })
    }

    logOutUser() {
        this.setState({
            userLoggedIn: false,
            userName: undefined
        });
    }

    showPostsForBlogSpace(blogSpaceId){
        this.setState({
            blogSpaceId:blogSpaceId,
            pageTypeVisible:'blogPosts'
        })
    }

    renderBlogSpaces(){
        return (
            <div>
                <BlogSpacesGrid
                    isUserLoggedIn={this.state.userLoggedIn}
                    userNameLoggedIn={this.state.userName}
                    showPostsForBlogSpace={this.showPostsForBlogSpace}
                />
            </div>
        )
    }

    renderPosts(){
        return (
            <div>
                <BlogPostsGrid
                    showBlogPost={this.showBlogPost}
                    isUserLoggedIn={this.state.userLoggedIn}
                    userNameLoggedIn={this.state.userName}
                    blogSpaceId={this.state.blogSpaceId}
                />
            </div>
        )
    }

    onViewBlogSpacesClicked(){
        this.setState({
            pageTypeVisible:'blogSpaces'
        })
    }

    render() {
        return (
            <div>
                <NavigationBar
                    userLoggedIn={this.state.userLoggedIn}
                    userName={this.state.userName}
                    onSignInButtonClick={this.signInButtonClicked}
                    logOutUser={this.logOutUser}
                    pageTypeVisible={this.state.pageTypeVisible}
                    onViewBlogSpacesClick={this.onViewBlogSpacesClicked}
                />

                <SignInModal
                    showSignInModal={this.state.showSignInModal}
                    closeSignInModal={this.toggleSignInModal}
                    signInUser={this.signInUser}
                    signUpUser={this.signUpUser}
                />

                {
                    this.state.pageTypeVisible==='blogSpaces'?
                        this.renderBlogSpaces(): this.renderPosts()
                }


            </div>
        )
    }
}
export default Home;