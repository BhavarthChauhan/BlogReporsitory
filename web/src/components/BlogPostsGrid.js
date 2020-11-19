import React from 'react';
import GridLayout from 'react-grid-layout';
import BlogPostCard from "./BlogPostCard";
import BlogService from "../services/BlogService";

class BlogPostsGrid extends React.Component {

    constructor(props) {
        super(props);

        this.state={
            posts:[]
        }

        this.getAllPosts = this.getAllPosts.bind(this);
    }

    componentWillMount() {
        this.getAllPosts();
    }

    getAllPosts() {
        BlogService.getPosts()
            .then((response)=>{
                this.setState({
                    posts:response.data
                })
            });

    }

    generateLayout() {
        var layouts = [];
        var row = 0;
        var col = 0;
        for (var post in this.state.posts) {
            let layout = {
                i: this.state.posts[post].id.toString(),
                x: col,
                y: row,
                h: 1,
                w:1,
                static: true
            };
            layouts.push(layout);
            col++;
            if (col > 3) {
               row=row+4;
                col = 0;
            }
        }
        return layouts;

    }

    render() {

        let layout = this.generateLayout();
        return (
            <div>
                <GridLayout className="layout" layout={layout} cols={4} rowHeight={30} width={1200}>
                    {this.state.posts.map(post =>
                            <div key={post.id.toString()}>
                                <BlogPostCard
                                    cardTitle={post.title}
                                    cardDescription={post.description}
                                    postId={post.id}
                                    showBlogPost={this.props.showBlogPost}
                                    />
                            </div>
                    )}
                </GridLayout>
            </div>
        );
    }


}
export default BlogPostsGrid;