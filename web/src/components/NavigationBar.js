import React  from "react";
import Navbar from "react-bootstrap/Navbar";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

class NavigationBar extends React.Component{

    constructor() {
        super();
    }

    render() {
      return (
          <div>
              <Navbar bg="primary" variant="dark">
                  <Navbar.Brand>Adobe Blog Engine</Navbar.Brand>
                  <Navbar.Toggle/>
                  <Navbar.Collapse className="justify-content-end">
                      {
                          this.props.userLoggedIn ?
                              <Navbar.Text>
                                  {
                                      this.props.pageTypeVisible ==='blogPosts'?
                                          <Button variant="outline-light"
                                                  id='viewBlogSpacesButton'
                                                  onClick={this.props.onViewBlogSpacesClick}
                                          >View Blog Spaces</Button>
                                          :
                                          undefined
                                  }
                                  &nbsp;&nbsp;&nbsp;
                                  Signed in as: {this.props.userName + '      '}
                                  <Button variant="outline-light"
                                  onClick={this.props.logOutUser}>
                                      Log out
                                  </Button>
                              </Navbar.Text>
                              :
                              <Form inline>
                                  {
                                      this.props.pageTypeVisible ==='blogPosts'?
                                          <Button variant="outline-light"
                                                  id='viewBlogSpacesButton'
                                                  onClick={this.props.onViewBlogSpacesClick}
                                          >View Blog Spaces</Button>
                                          :
                                          undefined
                                  }
                                  &nbsp;&nbsp;&nbsp;
                                  <Button variant="outline-light"
                                            onClick={this.props.onSignInButtonClick}
                                  >Sign in / Create Account</Button>
                              </Form>
                      }
                  </Navbar.Collapse>
              </Navbar>
          </div>
      )
    }
}

export default NavigationBar