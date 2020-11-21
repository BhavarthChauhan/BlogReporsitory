import React from 'react';
import GridLayout from 'react-grid-layout';
import BlogPostCard from "./BlogPostCard";
import BlogService from "../services/BlogService";
import Card from "react-bootstrap/Card";
import Button from "react-bootstrap/Button";
import AddNewPost from "./AddNewPost";
import BlogPost from "./BlogPost";
import {Responsive , WidthProvider} from 'react-grid-layout';

const ResponsiveGridLayout = WidthProvider(Responsive);
class BlogPostsGrid extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            posts: [],
            addNewPostVisible: false,
            spaceCreatedByUser: '',
            spaceTheme: '',
            blogSpaceId: undefined,
            postId: undefined,
            blogPostVisible: false
        };

        this.getAllPosts = this.getAllPosts.bind(this);
        this.renderAddNewCard = this.renderAddNewCard.bind(this);
        this.addNewPost = this.addNewPost.bind(this);
        this.closeAddNewPost = this.closeAddNewPost.bind(this);
        this.addNewPostButtonClicked = this.addNewPostButtonClicked.bind(this);
        this.onLayoutChange = this.onLayoutChange.bind(this);
        this.loggedInUserCreatedSpace = this.loggedInUserCreatedSpace.bind(this);
        this.showBlogPost = this.showBlogPost.bind(this);
        this.closeBlogPost = this.closeBlogPost.bind(this);
    }

    componentWillMount() {
        this.getAllPosts();
    }


    getAllPosts() {
        BlogService.getPostsForBlogSpace(this.props.blogSpaceId)
            .then((response) => {
                this.setState({
                    posts: response.data.newPosts,
                    spaceCreatedByUser: response.data.userName,
                    spaceTheme: response.data.spaceTheme,
                    blogSpaceId: response.data.blogSpaceId
                })
            }).catch((error)=>{
                this.props.showError(error.response.data);
        });

    }

    generateLayout(maxCols) {
        var layouts = [];
        var row = 0;
        var col = 0;
        for (var post in this.state.posts) {
            let layout = {
                'i': this.state.posts[post].id.toString(),
                'x': col,
                'y': row,
                'h': 1,
                'w': 1,
                'static': true
            };
            layouts.push(layout);
            col++;
            if (col >=maxCols) {
                row++;
                col = 0;
            }
        }


        layouts.push({
            'i': 'addNewPost',
            'x': col,
            'y': row,
            'h': 1,
            'w': 1,
            'static': true
        });
        return layouts;

    }

    onLayoutChange(layout) {
        console.log(layout)
    }

    loggedInUserCreatedSpace() {
        return this.state.spaceCreatedByUser && this.state.spaceCreatedByUser === this.props.userNameLoggedIn;
    }

    renderAddNewCard() {
        let theme = this.props.cardTheme ==='Dark'?'secondary':'light';
        if (this.props.isUserLoggedIn && this.loggedInUserCreatedSpace()) {
            return (
                <div>
                    <Card bg={theme}>
                        <Card.Body>
                            <Card.Title>Add new post</Card.Title>
                            <Card.Text>
                                Click below to add
                            </Card.Text>
                            <Button variant="primary" onClick={this.addNewPost}>Add </Button>
                        </Card.Body>
                    </Card>
                </div>
            )
        } else if (!this.props.isUserLoggedIn) {
            return (
                <div>
                    <Card  bg={theme}>
                        <Card.Body>
                            <Card.Title>Login to add a new post if space created by you</Card.Title>

                        </Card.Body>
                    </Card>
                </div>
            )
        } else {
            return undefined;
        }
    }

    addNewPost() {
        this.setState({
            addNewPostVisible: true
        })
    }

    closeAddNewPost() {
        this.setState({
            addNewPostVisible: false
        })
    }

    addNewPostButtonClicked(title, description, content) {
        BlogService.addNewPost(title, description, content, this.props.userNameLoggedIn, this.props.blogSpaceId)
            .then((response) => {
                this.setState({
                    posts: response.data.newPosts,
                    spaceCreatedByUser: response.data.userName,
                    spaceTheme: response.data.spaceTheme,
                    blogSpaceId: response.data.blogSpaceId,
                    addNewPostVisible: false
                })
            }).catch((error)=>{
                this.props.showError(error.response.data)
        })
    }

    showBlogPost(postId) {
        this.setState({
            postId: postId,
            blogPostVisible: true
        })
    }

    closeBlogPost() {
        this.setState({
            blogPostVisible: false
        })
    }

    render() {

        let layout = {
            lg: this.generateLayout(5),
            md: this.generateLayout(4),
            sm: this.generateLayout(3),
            xs: this.generateLayout(2),
            xxs: this.generateLayout(1)
        };
        return (
            <div>
                <ResponsiveGridLayout className="layout" layouts={layout}
                            breakpoints={{lg: 1200, md: 996, sm: 768, xs: 480, xxs: 0}}
                            cols={ {lg: 5, md: 4, sm: 3, xs: 2, xxs: 1 }}>
                    {this.state.posts.map(post =>
                        <div key={post.id.toString()}>
                            <BlogPostCard
                                cardTitle={post.title}
                                cardDescription={post.description}
                                postId={post.id}
                                showBlogPost={this.showBlogPost}
                                cardTheme={this.state.spaceTheme}
                            />
                        </div>
                    )}
                    <div key="addNewPost">
                        {this.renderAddNewCard()}
                    </div>
                </ResponsiveGridLayout>

                {this.state.addNewPostVisible ?
                    <AddNewPost
                        isVisible={this.state.addNewPostVisible}
                        onCloseAddNewPost={this.closeAddNewPost}
                        addNewPostButtonClicked={this.addNewPostButtonClicked}
                    />
                    :
                    undefined
                }
                {this.state.blogPostVisible ?
                    <BlogPost
                        closeBlogPost={this.closeBlogPost}
                        postId={this.state.postId}
                        userLoggedIn={this.props.isUserLoggedIn}
                        userName={this.props.userNameLoggedIn}
                        showError={this.props.showError}
                    /> : undefined}
            </div>
        );
    }


}

export default BlogPostsGrid;