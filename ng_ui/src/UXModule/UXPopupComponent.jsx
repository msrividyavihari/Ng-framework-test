import React, { Component } from 'react';
import './UXPopupComponent.css';
import { UX } from '@d-lift/uxcomponents';
import { AppContext } from '@d-lift/core';

/* 
To use this component please follow the below code snippet.

<UXModule.UXPopupComponent
    popupId="popupFirst"
    fieldName="Application Id"
headerName="Application - Search"
    enableSubmitBtn="true">
    <UXModule.UserTable
        popupId="popupFirst" />
    </UXModule.UXPopupComponent>
*/
export default class UXPopupComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            open: false,
            errorMsg: '',
            userInputValue: '',
        };
    }
    onOpenModal = () => {
        this.setState({ userInputValue: AppContext.model.getValue(this.props.popupId) });
        AppContext.model.setValue(this.props.popupId, AppContext.model.getValue(''));
        this.setState({ open: true });
    };
    onCloseModal = type => {
        const inputValues = AppContext.model.getValue(this.props.popupId);
        if (type === 'submit') {
            if (inputValues && inputValues.length > 1) {
                this.setState({ errorMsg: 'Please select only one value' });
                return;
            } else if (!inputValues || (inputValues && inputValues.length === 0)) {
                this.setState({ errorMsg: 'Please at least one value' });
                return;
            }
            AppContext.model.setValue(
                this.props.popupId,
                AppContext.model.getValue(this.props.popupId),
            );
            this.setState({ errorMsg: '' });
            this.setState({ open: false });
        } else {
            AppContext.model.setValue(this.props.popupId, this.state.userInputValue);
            this.setState({ errorMsg: '' });
            this.setState({ open: false });
        }
    };

    render() {
        const { open } = this.state;
        let submit = null;
        let errorMessage = null;
        let closeBtn = null;
        if (this.props.enableCloseBtn) {
            closeBtn = (
                <UX
                    iconClass={null}
                    mode="primary"
                    size="large"
                    type="button"
                    buttonType="submit"
                    click={() => this.onCloseModal('')}>
                    Close
                </UX>
            );
        }
        if (this.props.enableSubmitBtn) {
            submit = (
                <UX
                    iconClass={null}
                    mode="primary"
                    size="large"
                    type="button"
                    buttonType="submit"
                    click={() => this.onCloseModal('submit')}>
                    Submit
                </UX>
            );
        }
        if (this.state.errorMsg) {
            errorMessage = (
                <UX type="page">

                    <UX id="fieldError" symbol="error" type="fieldError">
                        {this.state.errorMsg}

                    </UX>

                </UX>
            );
        }
        return (
            <div className="containers">

                <UX id="text1" model={this.props.popupId} placeholder={this.props.placeholder} type="text" className="ml-1">
                    {this.props.fieldName}
                </UX>
                <span className="search-btn" onClick={this.onOpenModal}>
                    <img src={require('./searchImage.gif')} alt="loading..." />
                </span>

                <UX type="modal" isOpen={open}>

                    <UX size="3" type="header" value="Test">
                        {this.props.headerName}
                    </UX>
                    {errorMessage}
                    {this.props.children}
                    {submit}
                    {closeBtn}

                </UX>

            </div>
        );
    }
}
