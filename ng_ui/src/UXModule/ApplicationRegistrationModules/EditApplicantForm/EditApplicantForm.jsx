import { BaseComponent } from '@d-lift/uxcomponents';
import { AppContext } from '@d-lift/core';
import React from 'react';
import PropTypes from 'prop-types';
import EditApplicantFormTemplate from './EditApplicantForm.rt';

class EditApplicantForm extends BaseComponent {
    constructor(props) {
        super(props);
        this.state = { editMode: false };
    }

    viewMode = () => {
        this.setState({ editMode: false });
        AppContext.model.setValue('ar.pc.establish.new.person.clicked', false);
        AppContext.model.setValue('appRegNew.ArApplicationForAid.applicant', {});
    };

    //If we select primary Applicant automatically we should check as included applicant.
    primaryCheckSelected = primarySw => {
        if (primarySw.target.checked === true) {
            primarySw.target.checked = true;
            AppContext.model.setValue(
                'appRegNew.ArApplicationForAid.applicant.primaryApplicantSw',
                'Yes',
            );
            AppContext.model.setValue(
                'appRegNew.ArApplicationForAid.applicant.includeApplicantSw',
                'Yes',
            );
            document.getElementById('newIncludeAppCheckbox').checked = true;
            document.getElementById('newIncludeAppCheckbox').disabled = true;
        } else {
            primarySw.target.checked = false;
            AppContext.model.setValue(
                'appRegNew.ArApplicationForAid.applicant.primaryApplicantSw',
                'No',
            );
            document.getElementById('newIncludeAppCheckbox').disabled = false;
        }
    };

    checkIncludeApplicant = includeApplicantSw => {
        if (includeApplicantSw.target.checked === true) {
            includeApplicantSw.target.checked = true;
            AppContext.model.setValue(
                'appRegNew.ArApplicationForAid.applicant.includeApplicantSw',
                'Yes',
            );
        } else {
            includeApplicantSw.target.checked = false;
            AppContext.model.setValue(
                'appRegNew.ArApplicationForAid.applicant.includeApplicantSw',
                'No',
            );
        }
    };

    renderComponent() {
        let editMode = AppContext.model.getValue('ar.pc.establish.new.person.clicked');
        if (editMode === true) {
            return EditApplicantFormTemplate.bind(this).apply(this);
        } else {
            return <React.Fragment></React.Fragment>;
        }
    }
}

EditApplicantForm.propTypes = {
    /** Define unique id for the component */
    id: PropTypes.string,
    addEditForm: PropTypes.bool,
};

export default EditApplicantForm;
