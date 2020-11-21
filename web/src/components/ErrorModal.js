import React from 'react';
import Modal from 'react-bootstrap/Modal';
class ErrorModal extends  React.Component{

    constructor() {
        super();

    }

    render() {
        return(
            <div>
                <Modal show={this.props.showErrorModal} onHide={this.props.closeErrorModal}>
                    <Modal.Header closeButton>
                        <Modal.Title>{this.props.error}</Modal.Title>
                    </Modal.Header>
                </Modal>
            </div>
        )
    }

}

export default ErrorModal;