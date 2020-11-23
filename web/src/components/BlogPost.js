import React from 'react';
import Rodal from 'rodal'
import Card from 'react-bootstrap/Card'
import BlogService from "../services/BlogService";
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Form from 'react-bootstrap/Form'
import Button from "react-bootstrap/Button";

class BlogPost extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            visible: false,
            postDetails: undefined,
            comments: undefined,
            addedComment: ''
        };

        this.renderComments = this.renderComments.bind(this);
        this.renderUserLoginCard = this.renderUserLoginCard.bind(this);

        this.handleCommentChange = this.handleCommentChange.bind(this);
        this.handleAddComment = this.handleAddComment.bind(this);
    }

    componentWillMount() {
        BlogService.getPostDetails(this.props.postId)
            .then((response) => {
                if (this.props.userLoggedIn) {
                    BlogService.getCommentsForPost(this.props.postId)
                        .then((commentResponse) => {
                            this.setState({
                                comments: commentResponse.data,
                                postDetails: response.data,
                                visible: true
                            })
                        }).catch((error)=>{
                            this.props.showError(error.response.data)
                    })
                } else {
                    this.setState({
                        postDetails: response.data,
                        visible: true
                    })
                }

            }).catch((error)=>{
                this.props.showError(error.response.data);
        });


    }

    handleCommentChange(e) {
        this.setState({
            addedComment: e.target.value
        })
    }

    handleAddComment() {
        if (this.state.addedComment !== '') {
            BlogService.addCommentToPost(this.state.postDetails.id, this.props.userName, this.state.addedComment)
                .then((response) => {
                    this.setState({
                        comments: response.data,
                        addedComment:''
                    })
                }).catch((error)=>{
                    this.props.showError(error.response.data);
            });
        }
    }

    renderComments() {
        return (
            <div>
                <Card id='commentCard'>
                    <Card.Body>
                        <Card.Title>Comments</Card.Title>
                        <Form.Group>
                            <Form.Control size="lg" type="text" placeholder="Add Comment"
                                          onChange={this.handleCommentChange}/>

                        </Form.Group>
                        <Button variant="primary" className=" btn pull-right" type="submit"
                                onClick={this.handleAddComment}>
                            Submit
                        </Button>
                        <br/> <br/>
                        {
                            this.state.comments.map(comment =>
                                <div>
                                    <Card>
                                        <br/>
                                        <Card.Subtitle className="mb-2 text-muted" style={{textAlign: 'left'}}>
                                            Comment by : {comment.user.name}
                                        </Card.Subtitle>
                                        <Card.Body>{comment.text}</Card.Body>
                                    </Card>
                                    <br/>
                                </div>
                            )
                        }
                    </Card.Body>
                </Card>
            </div>
        )
    }

    renderUserLoginCard() {
        return (
            <div>
                <Card id='loginCard'>
                    <Card.Body>
                        <Card.Title>Please login to view / add comments</Card.Title>
                    </Card.Body>
                </Card>
            </div>
        )
    }

    render() {
        return (
            <div>
                {this.state.postDetails ?
                    <div>
                        <Rodal visible={this.state.visible} onClose={this.props.closeBlogPost} height={100} width={100}
                               measure={"%"}>
                            <Row>
                                <Col lg={8}>
                                    <Card id='postCard'>
                                        <Card.Body>
                                            <Card.Title>{this.state.postDetails.title}</Card.Title>
                                            <Card.Subtitle
                                                className="mb-2 text-muted">{this.state.postDetails.description}</Card.Subtitle>
                                            <Card.Text>
                                                {this.state.postDetails.content}
                                            </Card.Text>

                                        </Card.Body>
                                    </Card>
                                </Col>
                                <Col lg={4}>
                                    {this.props.userLoggedIn ?
                                        this.renderComments()
                                        :
                                        this.renderUserLoginCard()
                                    }
                                </Col>
                            </Row>
                        </Rodal>
                    </div>
                    : undefined
                }
            </div>

        );
    }
}

export default BlogPost;