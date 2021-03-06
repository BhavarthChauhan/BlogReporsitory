import React from "react";
import Card from 'react-bootstrap/Card'
import Button from "react-bootstrap/Button";

class BlogSpaceCard extends React.Component{
    constructor(props) {
        super(props);
        this.state={
            blogPostVisible:false
        }
        this.showBlogPost = this.showBlogPost.bind(this);

    }

    showBlogPost() {
        this.props.showPosts(this.props.blogSapceId)
    }


    render() {
        return (
            <div>
                <Card style={{height:'10rem'}}   >
                    <Card.Body>
                        <Card.Subtitle>{this.props.cardTitle} by {this.props.createdBy}</Card.Subtitle>
                        <Card.Text>
                            {this.props.cardDescription}
                        </Card.Text>
                        <Button variant="primary" onClick={this.showBlogPost}>View posts in this space</Button>
                    </Card.Body>
                </Card>

            </div>
        );
    }
}

export default BlogSpaceCard
