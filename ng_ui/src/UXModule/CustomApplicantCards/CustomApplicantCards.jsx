import { BaseComponent } from '@d-lift/uxcomponents';
import { AppContext, Util } from '@d-lift/core';
import PropTypes from 'prop-types';
import './CustomApplicantCards.css';
import ApplicantFormTemplate from './CustomApplicantCards.rt';
import _ from 'lodash';
import { Date } from 'sugar';

class CustomApplicantCards extends BaseComponent {
    constructor(props) {
        super(props);
        this.state = { mode: 'edit', applicant: {}, index: 0 };
    }

    cancelEdit = index => {
        this.setState({ mode: 'view' });
        this.applicant = undefined;
        let applincantsArray = AppContext.model.getValue('applicants');
        //applincantsArray.pop();
        applincantsArray.splice(index, 1);
        if (applincantsArray.length <= 0) {
            AppContext.model.setValue('primaryMissingError', false);
        }
        AppContext.model.setValue('applicants', applincantsArray);
        AppContext.model.setValue('applicantsCustomError', false);
        AppContext.model.setValue('applicantsCustomErrorMsg', '');
    };

    checkPrimaryApplicant = primarySw => {
        var id = primarySw.target.id;
        var idNumber = id.split('_')[1];
        if (primarySw.target.checked === true) {
            primarySw.target.checked = true;

            let primaryCheckArray = [];
            primaryCheckArray.push('primaryApplicantCheckbox_' + idNumber);
            AppContext.model.setValue('primary_Applicant_Checkbox', primaryCheckArray);

            if (
                AppContext.model.getValue('include_Applicant_Checkbox') === undefined ||
                AppContext.model.getValue('include_Applicant_Checkbox').length === 0
            ) {
                let includeCheckArray = [];
                includeCheckArray.push('includeApplicantCheckbox_' + idNumber);
                AppContext.model.setValue('include_Applicant_Checkbox', includeCheckArray);
            } else {
                let includeCheckArray = AppContext.model.getValue('include_Applicant_Checkbox');
                includeCheckArray.push('includeApplicantCheckbox_' + idNumber);
                AppContext.model.setValue('include_Applicant_Checkbox', includeCheckArray);
            }

            AppContext.model.setValue('applicants[' + idNumber + '].primaryApplicantSw', 'Yes');
            AppContext.model.setValue('applicants[' + idNumber + '].includeApplicantSw', 'Yes');

            let applicants = AppContext.model.getValue('applicants');
            for (var i = 0; i < applicants.length; i++) {
                if (i !== parseInt(idNumber)) {
                    var applicant = AppContext.model.getValue('applicants[' + i + ']');
                    applicant.primaryApplicantSw = 'No';
                }
            }
            AppContext.model.setValue('primaryMissingError', false);
        } else {
            primarySw.target.checked = false;
            AppContext.model.setValue('applicants[' + idNumber + '].primaryApplicantSw', 'No');
            // AppContext.model.setValue('applicants[' + idNumber + '].includeApplicantSw', 'No');
            AppContext.model.getValue('primary_Applicant_Checkbox') === undefined ||
            AppContext.model.getValue('primary_Applicant_Checkbox').length === 0
                ? AppContext.model.setValue('primaryMissingError', true)
                : AppContext.model.setValue('primaryMissingError', false);
        }
    };

    checkIncludeApplicant = includeApplicantSw => {
        var id = includeApplicantSw.target.id;
        var idNumber = id.split('_')[1];
        if (includeApplicantSw.target.checked === true) {
            includeApplicantSw.target.checked = true;
            AppContext.model.setValue('applicants[' + idNumber + '].includeApplicantSw', 'Yes');
        } else {
            includeApplicantSw.target.checked = false;
            AppContext.model.setValue('applicants[' + idNumber + '].includeApplicantSw', 'No');
        }
    };

    //If we select primary Applicant automatically we should check as included applicant.
    primaryCheckSelected = primarySw => {
        var id = primarySw.target.id;
        var idNumber = id.split('_')[1];
        let collectionList = AppContext.model.getValue(this.props.collectionList);
        if (
            document.getElementById(idNumber + '_includeApplicantCheckboxGroup').checked === false
        ) {
            document.getElementById(idNumber + '_includeApplicantCheckboxGroup').checked = true;
            //AppContext.model.setValue(idNumber + '_includeApplicantCheckboxGroup', true);
            document.getElementById(idNumber + '_includeApplicantCheckboxGroup').disabled = true;
            collectionList[idNumber].includeApplicantSw = true;
            AppContext.model.setValue('applicants[' + idNumber + '].primaryApplicantSw', true);
            AppContext.model.setValue('applicants[' + idNumber + '].includeApplicantSw', true);
        } else {
            document.getElementById(idNumber + '_includeApplicantCheckboxGroup').checked = false;
            document.getElementById(idNumber + '_includeApplicantCheckboxGroup').disabled = false;
            collectionList[idNumber].includeApplicantSw = false;
            AppContext.model.setValue('applicants[' + idNumber + '].primaryApplicantSw', false);
            AppContext.model.setValue('applicants[' + idNumber + '].includeApplicantSw', false);
        }
    };

    handleFirstNameInputChange(e) {
        var id = e.target.id;
        var idNumber = id.split('_')[1];
        AppContext.model.setValue('applicants[' + idNumber + '].firstName', e.target.value);
    }

    handleLastNameInputChange(e) {
        var id = e.target.id;
        var idNumber = id.split('_')[1];
        AppContext.model.setValue('applicants[' + idNumber + '].lastName', e.target.value);
    }

    handleMiddleNameInputChange(e) {
        var id = e.target.id;
        var idNumber = id.split('_')[1];
        AppContext.model.setValue('applicants[' + idNumber + '].middleName', e.target.value);
    }

    handleSuffixNameInputChange(e) {
        var id = e.target.id;
        var idNumber = id.split('_')[1];
        AppContext.model.setValue('applicants[' + idNumber + '].sufxName', e.target.value);
    }

    handleGenderInputChange(e) {
        var id = e.target.id;
        var idNumber = id.split('_')[1];
        AppContext.model.setValue('applicants[' + idNumber + '].gender', e.target.value);
    }

    handleAgeInputChange(value, e) {
        if (null != value) {
            var tr = e.target.parentElement.parentElement.parentElement.parentElement;
            var id = tr.id;
            var idNumber = id.split('_')[1];

            let birthDay = new Date(e.target.value).format('{MM}/{dd}/{yyyy}');
            AppContext.model.setValue('applicants[' + idNumber + '].dob', birthDay.raw);

            const now = new Date();
            const yearNow = now.getFullYear();
            const monthNow = now.getMonth();
            const dateNow = now.getDate();

            const dob = new Date(value);
            const yearDob = dob.getFullYear();
            const monthDob = dob.getMonth();
            const dateDob = dob.getDate();
            let yearAge = yearNow - yearDob;
            let monthAge;

            if (monthNow >= monthDob) {
                monthAge = monthNow - monthDob;
            } else {
                yearAge--;
                monthAge = 12 + monthNow - monthDob;
            }

            if (dateNow < dateDob) {
                monthAge--;

                if (monthAge < 0) {
                    monthAge = 11;
                    yearAge--;
                }
            }
            AppContext.model.setValue('applicants[' + idNumber + '].age', yearAge);
        } else {
            AppContext.model.setValue('applicants[' + idNumber + '].dob', undefined);
            AppContext.model.setValue('applicants[' + idNumber + '].age', undefined);
        }
    }

    handleSSNInputChange = () => {
        var index = this.props.index;
        let elem = document.getElementById('applicantSSnTextbox_' + index);
        if (null !== elem) {
            let value = elem.value;
            if (null !== value && value.length === 11) {
                value = value.replaceAll('-', '');
                AppContext.model.setValue('applicants[' + index + '].ssn', value);
            }
        }
    };

    handleRaceInputChange(e) {
        var id = e.target.id;
        var idNumber = id.split('_')[1];
        AppContext.model.setValue('applicants[' + idNumber + '].race', e.target.value);
    }

    handleEthnicityInputChange(e) {
        var id = e.target.id;
        var idNumber = id.split('_')[1];
        AppContext.model.setValue('applicants[' + idNumber + '].ethnicity', e.target.value);
    }

    handleAliasSwInputChange(e) {
        var id = e.target.id;
        var idNumber = id.split('_')[1];
        AppContext.model.setValue('applicants[' + idNumber + '].aliasSw', e.target.value);
    }

    handleAliasFirstNameInputChange(e) {
        var id = e.target.id;
        var idNumber = id.split('_')[1];
        AppContext.model.setValue('applicants[' + idNumber + '].aliasFirstName', e.target.value);
    }

    handleAliasMiddleNameInputChange(e) {
        var id = e.target.id;
        var idNumber = id.split('_')[1];
        AppContext.model.setValue('applicants[' + idNumber + '].aliasMiddleName', e.target.value);
    }

    handleAliasLastNameInputChange(e) {
        var id = e.target.id;
        var idNumber = id.split('_')[1];
        AppContext.model.setValue('applicants[' + idNumber + '].aliasLastName', e.target.value);
    }

    handleAliasGenderInputChange(e) {
        var id = e.target.id;
        var idNumber = id.split('_')[1];
        AppContext.model.setValue('applicants[' + idNumber + '].aliasGender', e.target.value);
    }

    handleInterpreterSwInputChange(e) {
        var id = e.target.id;
        var idNumber = id.split('_')[1];
        AppContext.model.setValue('applicants[' + idNumber + '].interpreterSw', e.target.value);
    }

    handlePrimaryLanguageInputChange(e) {
        var id = e.target.id;
        var idNumber = id.split('_')[1];
        AppContext.model.setValue('applicants[' + idNumber + '].primaryLanguage', e.target.value);
    }

    handleSpecificPrimaryLanguageInputChange(e) {
        var id = e.target.id;
        var idNumber = id.split('_')[1];
        AppContext.model.setValue('applicants[' + idNumber + '].specificLanguage', e.target.value);
    }

    handleAccommodationSwInputChange(e) {
        var id = e.target.id;
        var idNumber = id.split('_')[1];
        AppContext.model.setValue('applicants[' + idNumber + '].accommodationSw', e.target.value);
    }

    handleTypeAccommodationInputChange(e) {
        var id = e.target.id;
        var idNumber = id.split('_')[1];
        AppContext.model.setValue('applicants[' + idNumber + '].typeAccommodation', e.target.value);
    }

    handleAuthRepresentativeSwInputChange(e) {
        var id = e.target.id;
        var idNumber = id.split('_')[1];
        AppContext.model.setValue(
            'applicants[' + idNumber + '].authRepresentativeSw',
            e.target.value,
        );
    }

    handleEbtcardSwInputChange(e) {
        var id = e.target.id;
        var idNumber = id.split('_')[1];
        AppContext.model.setValue('applicants[' + idNumber + '].ebtcardSw', e.target.value);
    }

    handleRegistervoteSwInputChange(e) {
        var id = e.target.id;
        var idNumber = id.split('_')[1];
        AppContext.model.setValue('applicants[' + idNumber + '].registervoteSw', e.target.value);
    }

    handleAliasSuffixInputChange(e) {
        var id = e.target.id;
        var idNumber = id.split('_')[1];
        AppContext.model.setValue('applicants[' + idNumber + '].aliasSuffix', e.target.value);
    }

    checkApplicantList() {
        let applicantCardsList = AppContext.model.getValue('applicants');
        if (!Array.isArray(applicantCardsList)) {
            applicantCardsList = [applicantCardsList];
        }
        if (applicantCardsList.length > 0) {
            var first = _.first(applicantCardsList);
            if (undefined === first) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    handlestring(chain) {
        if (null === chain || undefined === chain || 'null' === chain) {
            return '';
        }
        return chain;
    }

    getRefTableDescriptionFromCode(code, rtTable) {
        if (code === undefined || code === null || code === '') {
            return '';
        }
        let description = '';
        let tableData = Util.getRefTableData(rtTable, Util.getLocale(), this);
        if (tableData !== undefined) {
            _.forEach(tableData, (val, key) => {
                if (val.CODE === code) {
                    description = val.DESCRIPTION;
                }
            });
        }
        return description;
    }

    renderComponent() {
        if (undefined !== this.props.applicant && undefined === this.props.applicant.firstName) {
            this.className = 'new-applicant-vl';
            this.title = true;
        } else {
            this.className = '';
            this.title = false;
        }

        return ApplicantFormTemplate.bind(this).apply(this);
    }
}

CustomApplicantCards.propTypes = {
    /** Define unique id for the component */
    id: PropTypes.string,
    /** Accepts a Content Manager key and assigns the value to the Paragraph's inner HTML */
    labelKey: PropTypes.string,
    addEditForm: PropTypes.bool,
    applicant: PropTypes.shape({
        indvStatusSw: PropTypes.symbol,
        firstName: PropTypes.string,
        lastName: PropTypes.string,
    }),
    index: PropTypes.number,
    mode: PropTypes.string,
    className: PropTypes.string,
    title: PropTypes.boolean,
};

export default CustomApplicantCards;
