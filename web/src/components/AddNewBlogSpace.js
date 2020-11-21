import React  from "react";
import Rodal from "rodal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";


class AddNewBlogSpace extends React.Component {

    constructor(props) {
        super(props);

        this.state={
            spaceName:'',
            description:'',
            theme:'Light',
            error:undefined
        };

        this.onSpaceNameChange = this.onSpaceNameChange.bind(this);
        this.onDescriptionChange = this.onDescriptionChange.bind(this);
        this.handleSubmitClicked = this.handleSubmitClicked.bind(this);
        this.onDropDownChange = this.onDropDownChange.bind(this);
    }
    onSpaceNameChange(e){
        this.setState({
            spaceName:e.target.value
        })
    }

    onDescriptionChange(e){
        this.setState({
            description: e.target.value
        })
    }

    onDropDownChange(e){
       this.setState({
           theme:e.target.value
       })
    }

    handleSubmitClicked(){
        if(this.state.spaceName===''|| this.state.description===''||this.state.theme===''){
            this.setState({
                error:'Missing input value'
            })
        }else{
            this.props.addNewBlogSpaceClicked(this.state.spaceName, this.state.description, this.state.theme)
        }
    }

    render() {
        return (
            <div>
                <div>
                    <Rodal visible={this.props.isVisible} onClose={this.props.closeAddNewBlogSpace}
                           height={80} width={70}
                           measure={"%"}
                    >

                        <Form>
                            <Form.Group>
                                <Form.Label>Blog Space Name</Form.Label>
                                <Form.Control type="text" placeholder="Enter space name" onChange={this.onSpaceNameChange} />
                            </Form.Group>

                            <Form.Group>
                                <Form.Label>Description</Form.Label>
                                <Form.Control type="text" placeholder="Enter space description" onChange={this.onDescriptionChange}/>
                            </Form.Group>

                            <Form.Group>
                                <Form.Label>Blog space theme</Form.Label>
                                    <Form.Control as="select" onChange={this.onDropDownChange}>
                                        <option>Light</option>
                                        <option>Dark</option>
                                    </Form.Control>
                            </Form.Group>

                            {this.state.error ? <Form.Control type="text" placeholder={this.state.error} readOnly /> : undefined}

                            <Button variant="primary" onClick={this.handleSubmitClicked}>
                                Submit
                            </Button>
                        </Form>


                    </Rodal>
                </div>
            </div>
        );
    }


}
export default AddNewBlogSpace;