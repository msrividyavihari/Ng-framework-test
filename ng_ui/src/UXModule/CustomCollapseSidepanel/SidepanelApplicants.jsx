import React, { Component } from 'react';
import { UX } from '@d-lift/uxcomponents';
import './CustomCollapseSidepanel.css';
import { AppContext } from '@d-lift/core';

class SidepanelApplicants extends Component {
    ssnln;
    constructor() {
        super();

        this.state = {
            open: false,
            errorMsg: '',
        };
    }
    openSidePanal = () => {
        var x = document.getElementById('mySidepanelApplicants');
        if (x.className === 'sidepanel-close') {
            x.className = 'sidepanel-open';
        } else {
            x.className = 'sidepanel-close';
        }
    };
    closeNav = () => {
        var x = document.getElementById('mySidepanelApplicants');
        if (x.className === 'sidepanel-close') {
            x.className = 'sidepanel-open';
        } else {
            x.className = 'sidepanel-close';
        }
    };

    submitData = () => {
        var applicants = AppContext.model.getValue('applicants');
        console.log('kjfjbhhi', applicants);
        var conflictsSolved = AppContext.model.getValue(
            'appRegNew.ConflictApplicant.ssn' + this.props.SSN,
        );
        var ConflictApplicants = AppContext.model.getValue(
            'appRegNew.ArApplicationForAid.ConflictApplicants',
        );
        let temp = []; //ConflictApplicants[this.props.SSN];
        temp.push('lastName');
        temp.push('firstName');
        temp.push('ssn');
        applicants.forEach(ele => {
            if (ele.ssn === this.props.SSN) {
                temp.forEach(e => {
                    console.log(conflictsSolved[this.props.SSN][e]);
                    ele[e] = conflictsSolved[this.props.SSN][e]
                        ? conflictsSolved[this.props.SSN][e]
                        : ele[e];
                });
            }
        });
        // console.log(applicants[0]);
        // applicants[0].ssn = 556655;
        this.setState({ open: true });
        AppContext.model.setValue('applicants', applicants);
    };
    render = () => {
        let submit = null;
        submit = (
            <UX
                iconClass={null}
                mode="primary"
                size="large"
                type="button"
                buttonType="submit"
                click={() => this.submitData()}>
                Submit
            </UX>
        );
        let con = (
            <div id="mySidenavApplicants">
                <a href="javascript:void(0)" id="sspIndvs" onClick={this.openSidePanal}>
                    {this.props.lable}
                </a>
            </div>
        );
        return (
            <div>
                {AppContext.model.getValue('sspMode') === 'Y'
                    ? //&& AppContext.model.getValue('pcNxtclick')
                      con
                    : ''}
                <div id="mySidepanelApplicants" className="sidepanel-close">
                    <div className="heading-btn pt-5">
                        <UX size="6" type="header" className="paraHeading">
                            File Clear - SSP Applicants
                        </UX>
                    </div>
                    <div>
                        <a href="javascript:void(0)" className="closebtn" onClick={this.closeNav}>
                            &times;
                        </a>
                    </div>
                    <div className="children">{this.props.children}</div>
                    {/* <div className="custom-btn">{submit}</div> */}
                </div>
            </div>
        );
    };
}
export default SidepanelApplicants;
