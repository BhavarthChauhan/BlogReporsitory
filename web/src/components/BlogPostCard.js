import React from 'react';
import Card from 'react-bootstrap/Card'
import Button from "react-bootstrap/Button";
class BlogPostCard extends React.Component {

    constructor(props) {
        super(props);
        this.state={
            blogPostVisible:false
        }
        this.showBlogPost = this.showBlogPost.bind(this);

    }

    showBlogPost() {
       this.props.showBlogPost(this.props.postId)
    }


    render() {
        return (
            <div>
                <Card style={{ width: '18rem' }}>
                    <Card.Body>
                        <Card.Title>{this.props.cardTitle}</Card.Title>
                        <Card.Text>
                            {this.props.cardDescription}
                        </Card.Text>
                        <Button variant="primary" onClick={this.showBlogPost}>Read More</Button>
                    </Card.Body>
                </Card>

            </div>
        );
    }
}

export default BlogPostCard