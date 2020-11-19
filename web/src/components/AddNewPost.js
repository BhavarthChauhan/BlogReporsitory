import React from 'react';
import Rodal from 'rodal'
import Form from 'react-bootstrap/Form'
import Button from "react-bootstrap/Button";
class AddNewPost extends React.Component {

    constructor(props) {
        super(props);
        this.state={
            title:'',
            description:'',
            content:'',
            error:undefined
        };

        this.onTitleChange = this.onTitleChange.bind(this);
        this.onContentChange = this.onContentChange.bind(this);
        this.onDescriptionChange = this.onDescriptionChange.bind(this);
        this.handleSubmitClick = this.handleSubmitClick.bind(this);

    }

    onTitleChange(e){
        this.setState({
            title:e.target.value
        })
    }

    onDescriptionChange(e){
        this.setState({
            description :e.target.value
        })
    }

    onContentChange(e){
        this.setState({
            content: e.target.value
        })
    }

    handleSubmitClick(){
        if(this.state.title === '' || this.state.description==='' || this.state.content===''){
            this.setState({
                error:'Missing fields'
            })
        }else{
            this.props.addNewPostButtonClicked(this.state.title, this.state.description, this.state.content)
        }
    }


    render() {
        return(
            <div>
                <Rodal visible={this.props.isVisible} onClose={this.props.onCloseAddNewPost}
                       height={80} width={70}
                       measure={"%"}
                >

                    <Form>
                        <Form.Group>
                            <Form.Label>Title</Form.Label>
                            <Form.Control type="text" placeholder="Enter Title" onChange={this.onTitleChange} />
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Description</Form.Label>
                            <Form.Control type="text" placeholder="Enter Description" onChange={this.onDescriptionChange}/>
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Content</Form.Label>
                            <Form.Control as="textarea" rows={5} placeholder="Enter content" onChange={this.onContentChange}/>
                        </Form.Group>
                        {this.state.error ? <Form.Control type="text" placeholder={this.state.error} readOnly /> : undefined}

                        <Button variant="primary" onClick={this.handleSubmitClick}>
                            Submit
                        </Button>
                    </Form>


                </Rodal>
            </div>
        )
    }

}

export default AddNewPost;