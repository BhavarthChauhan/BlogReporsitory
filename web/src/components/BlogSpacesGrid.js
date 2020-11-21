import React from "react";
import GridLayout from 'react-grid-layout';
import BlogService from "../services/BlogService";
import Card from "react-bootstrap/Card";
import Button from "react-bootstrap/Button";
import BlogSpaceCard from "./BlogSpaceCard";
import AddNewBlogSpace from "./AddNewBlogSpace";
import {Responsive , WidthProvider} from 'react-grid-layout';

const ResponsiveGridLayout = WidthProvider(Responsive);
class BlogSpacesGrid extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            blogSpaces: [],
            addNewBlogSpaceVisible: false
        };

        this.getAllBlogSpaces = this.getAllBlogSpaces.bind(this);
        this.generateLayout = this.generateLayout.bind(this);
        this.renderAddNewCard = this.renderAddNewCard.bind(this);
        this.addNewBlogSpace = this.addNewBlogSpace.bind(this);
        this.closeAddNewBlogSpace = this.closeAddNewBlogSpace.bind(this);
        this.addNewBlogSpaceClicked = this.addNewBlogSpaceClicked.bind(this);

    }

    componentWillMount() {
        this.getAllBlogSpaces();
    }

    getAllBlogSpaces() {
        BlogService.getAllBlogSpaces()
            .then((response) => {
                this.setState({
                    blogSpaces: response.data
                })
            })
    }

    generateLayout(maxCols) {
        let layouts = [];

        var row = 0;
        var col = 0;
        for (var blogSpace in this.state.blogSpaces) {

            let layout = {
                'i': this.state.blogSpaces[blogSpace].idString,
                'x': col,
                'y': row,
                'h': 1,
                'w': 1,
                'static': true
            };
            layouts.push(layout);
            col++;
            if (col >= maxCols) {
                row++;
                col = 0;
            }

        }

        layouts.push({
            'i': 'addNewBlogSpace',
            'x': col,
            'y': row,
            'h': 1,
            'w': 1,
            'static': true
        });

        return layouts;
    }

    addNewBlogSpace() {
        this.setState({
            addNewBlogSpaceVisible: true
        })
    }

    closeAddNewBlogSpace() {
        this.setState({
            addNewBlogSpaceVisible: false
        })
    }

    addNewBlogSpaceClicked(spaceName, description, theme) {

        BlogService.addNewSpace(spaceName, description, theme, this.props.userNameLoggedIn)
            .then((response) => {
                this.setState({
                    blogSpaces: response.data,
                    addNewBlogSpaceVisible: false
                })
            })
    }

    renderAddNewCard() {
        if (this.props.isUserLoggedIn) {
            return (
                <div>
                    <Card>
                        <Card.Body>
                            <Card.Title>Add new blog space</Card.Title>
                            <Card.Text>
                                Click below to add
                            </Card.Text>
                            <Button variant="primary" onClick={this.addNewBlogSpace}>Add </Button>
                        </Card.Body>
                    </Card>
                </div>
            )
        } else {
            return (
                <div>
                    <Card>
                        <Card.Body>
                            <Card.Title>Login to add a new blog space</Card.Title>

                        </Card.Body>
                    </Card>
                </div>
            )
        }
    }

    render() {
        let layout = {
            lg: this.generateLayout(5),
            md: this.generateLayout(4),
            sm: this.generateLayout(3),
            xs: this.generateLayout(2),
            xxs: this.generateLayout(1)
        };
        let addNewCardKey="addNewBlogSpace";
        return (
            <div>
                <ResponsiveGridLayout className="layout" layouts={layout}
                                      breakpoints={{lg: 1200, md: 996, sm: 768, xs: 480, xxs: 0}}
                                      cols={ {lg: 5, md: 4, sm: 3, xs: 2, xxs: 1 }}>
                    {this.state.blogSpaces.map(blogSpace =>

                        <div key={blogSpace.idString}>
                            <div>
                                <BlogSpaceCard
                                    cardTitle={blogSpace.spaceName}
                                    cardDescription={blogSpace.description}
                                    blogSapceId={blogSpace.id}
                                    showPosts={this.props.showPostsForBlogSpace}
                                    key={blogSpace.id}
                                />
                            </div>
                        </div>
                    )}
                    <div key={addNewCardKey}>
                        {this.renderAddNewCard()}
                    </div>
                </ResponsiveGridLayout>

                {this.state.addNewBlogSpaceVisible ?
                    <AddNewBlogSpace
                        isVisible={this.state.addNewBlogSpaceVisible}
                        addNewBlogSpaceClicked={this.addNewBlogSpaceClicked}
                        closeAddNewBlogSpace={this.closeAddNewBlogSpace}
                    />
                    :
                    undefined
                }
            </div>

        )
    }


}

export default BlogSpacesGrid;