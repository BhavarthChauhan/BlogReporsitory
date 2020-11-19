import React from "react";
import NavigationBar from "./NavigationBar";
import SignInModal from "./SignInModal";
import BlogPostsGrid from "./BlogPostsGrid";
import BlogPost from "./BlogPost";

class Home extends React.Component{
    constructor() {
        super();
        this.state= {
            userLoggedIn:false,
            userName:undefined,
            showSignInModal:false,
            blogPostVisible:false,
            postId:undefined
        };
        this.signInButtonClicked = this.signInButtonClicked.bind(this);
        this.toggleSignInModal = this.toggleSignInModal.bind(this);
        this.signInUser = this.signInUser.bind(this);
        this.logOutUser = this.logOutUser.bind(this);
        this.showBlogPost = this.showBlogPost.bind(this);
        this.closeBlogPost = this.closeBlogPost.bind(this);
    }

    signInButtonClicked(){
        this.setState({
            showSignInModal:true
        });
    }

    toggleSignInModal(){
        this.setState({
            showSignInModal:!this.state.showSignInModal
        });
    }

    signInUser(userName){
        this.setState({
            userName:userName,
            userLoggedIn:true,
            showSignInModal:false
        })
    }

   logOutUser(){
        this.setState({
            userLoggedIn:false,
            userName:undefined
        });
   }

    showBlogPost(postId) {
        this.setState({
            blogPostVisible:true,
            postId:postId
        })
    }

    closeBlogPost(){
        this.setState({
            blogPostVisible:false
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
                />

                <SignInModal
                    showSignInModal={this.state.showSignInModal}
                    closeSignInModal={this.toggleSignInModal}
                    signInUser={this.signInUser}
                    signUpUser={this.signUpUser}
                />

                <BlogPostsGrid
                    showBlogPost={this.showBlogPost}

                />
                {this.state.blogPostVisible ? <BlogPost
                            closeBlogPost={this.closeBlogPost}
                            postId={this.state.postId}
                            userLoggedIn={this.state.userLoggedIn}
                            userName={this.state.userName}
                /> : undefined}
            </div>
        )
    }
}
export default Home;