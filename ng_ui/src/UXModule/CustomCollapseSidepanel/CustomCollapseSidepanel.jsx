/**
 * @author Ketan Kulkarni <ketkulkarni@deloitte.com>
 */
import React, { Component } from 'react';
import { UX } from '@d-lift/uxcomponents';
import './CustomCollapseSidepanel.css';
import { AppContext } from '@d-lift/core';

class CustomCollapseSidepanel extends Component {
    ssnln;
    constructor() {
        super();

        this.state = {
            open: false,
            errorMsg: '',
        };
        this.ssnln = AppContext.model.getValue('ar.pc.search.ssnln');
    }
    openSidePanal = () => {
        var x = document.getElementById('mySidepanel');
        // console.log(x);
        // x.sidepanel.width = '250px !importent';
        if (x.className === 'sidepanel-close') {
            x.className = 'sidepanel-open';
        } else {
            x.className = 'sidepanel-close';
        }
    };
    closeNav = () => {
        var x = document.getElementById('mySidepanel');
        if (x.className === 'sidepanel-close') {
            x.className = 'sidepanel-open';
        } else {
            x.className = 'sidepanel-close';
        }
    };

    render = () => {
        let con = (
            <div id="mySidenav">
                <a href="javascript:void(0)" id="conflits" onClick={this.openSidePanal}>
                    Conflicts
                </a>
            </div>
        );

        return (
            <div>
                {//Red Conflicts Notch should be shown if any of the below 3 satisfies

                //Applicants Conflicts Conditions
                (AppContext.model.getValue('fileClearStatus') === 'Cleared' &&
                    AppContext.model.getValue('showApplicantsConflicts') === true &&
                    AppContext.model.getValue('conflictPanelApplicants') !== 'solved') ||
                //Contact Conflicts Conditions
                (AppContext.model.getValue('showContactConflicts') === true &&
                    AppContext.model.getValue('conflictPanelContacts') !== 'solved') ||
                //AuthRep Conflicts Conditions
                (AppContext.model.getValue('showAuthRepConflicts') === true &&
                    AppContext.model.getValue('conflictPanelAuthRep') !== 'solved')
                    ? con
                    : ''}
                <div id="mySidepanel" className="sidepanel-close">
                    <a href="javascript:void(0)" className="closebtn" onClick={this.closeNav}>
                        &times;
                    </a>
                    <div className="heading-btn">
                        <UX id="btn-heading" size="6" type="header" className="paraHeading">
                            {this.props.lable}
                        </UX>
                        <div>
                            <a
                                href="javascript:void(0)"
                                className="closebtn"
                                onClick={this.closeNav}>
                                &times;
                            </a>
                        </div>
                    </div>
                    <div className="children">{this.props.children}</div>
                </div>
            </div>
        );
    };
}
export default CustomCollapseSidepanel;
