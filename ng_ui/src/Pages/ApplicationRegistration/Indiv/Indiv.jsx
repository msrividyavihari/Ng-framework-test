import Template from './Indiv.rt';
import { PageConfig, UXPage, AppContext, Lift } from '@d-lift/core';
import navigationUtills from '@/ScreenFlowUtils/NavigationUtills';
import workflowUtills from '@/ScreenFlowUtils/WorkFlowUtills';
import errorMessages from '../../../../public/validation/commonValidation.json';
import './Indiv.css';

@PageConfig({
    ContextRoot: 'ApplicationRegistration',
    PageName: 'Indiv',
    Path: ['/indiv', '/ARRII'],
    Template: Template,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Individual',
})
class Indiv extends UXPage {
    initializeModel() {
        let Validator = Lift.Validator;
        Validator.register('charCheck', function(value, requirement) {
            var pattern = /^[a-zA-Z'" ]+$/g;
            return pattern.test(value);
        });

        return {
            application: {
                errorMessages: {},
                errorField: [],
                isError: false,

                searchIndv: {
                    inputSSN: '',
                    indvId: '',
                },

                individual: {
                    firstName: '',
                    middleName: '',
                    lastName: '',
                    suffix: '',
                    genderCd: '',
                    dateOfBirth: '',
                    ssn: '',
                    raceCd: '',
                    ethnicityCd: '',
                    hasEbtCard: '',
                    hasAlias: 'N',
                    registerVote: 'N',
                    language: '0',
                },
            },
        };
    }

    getMonthDiff = (d1, d2) => {
        var months;
        months = (d2.getFullYear() - d1.getFullYear()) * 12;
        months -= d1.getMonth() + 1;
        months += d2.getMonth();
        if (d2.getDate() >= d1.getDate()) months++;

        if (months > 13) {
            return true;
        } else {
            return false;
        }
    };

    goBack = () => {
        navigationUtills.toPreviousPage();
    };

    goToHomePage = event => {
        navigationUtills.goToHomePage();
    };

    validateFields = () => {
        let okError = false;
        let ValidationErrorMessage = [];

        if (!this.minCharCheck(AppContext.model.getValue('application.individual.lastName'))) {
            okError = true;
            ValidationErrorMessage.push(errorMessages.AR3117);
        }

        if (okError) {
            AppContext.pagedetails
                .getPageContext()
                .stateHandler('msgsList', ValidationErrorMessage);
            AppContext.pagedetails.getPageContext().stateHandler('errorMsg', true);
        } else {
            AppContext.pagedetails.getPageContext().stateHandler('errorMsg', false);
        }
        return okError;
    };

    minCharCheck = value => {
        var charFlag = false;
        var regex = /^[A-Z ]$/gi;
        var numCount = 0;
        if (!value) {
            return true;
        }
        if (value) {
            for (var i = 0, len = value.length; i < len; i++) {
                var substr = value.substring(i, i + 1);

                if (substr.match(regex)) {
                    numCount++;
                    if (numCount >= 2) {
                        charFlag = true;
                    }
                } else {
                    numCount = 0;
                }
            }
        }
        return charFlag;
    };

    checkBlur() {
        let val = AppContext.model.getValue('application.individual.race');
        console.log(val);
    }

    searchSSN = () => {
        let application = AppContext.model.getValue('application');
        let searchSSN = application.searchIndv.inputSSN;
        this.validateSSN(searchSSN, application, 'application.searchIndv.inputSSN');
    };

    validateSSN = (ssn, application, concatSSN) => {
        if (ssn && /^0*$/.test(ssn)) {
            if (!application.errorField.some(err => err === errorMessages.GL035)) {
                application.errorField.push(errorMessages.GL035);
            }
            application.isError = true;
        } else if (ssn.length < 4 && !/^0*$/.test(ssn)) {
            if (!application.errorField.some(err => err === errorMessages.GL113)) {
                application.errorField.push(errorMessages.GL113);
            }
            let errorField = [];
            errorField = application.errorField.filter(err => err !== errorMessages.GL035);
            application.errorField = errorField;
            application.isError = true;
        } else {
            const ssnLength = ssn.length;
            if (ssnLength > 0) {
                let zeros = '';
                for (let i = 0; i < 9 - ssnLength; i++) {
                    zeros = zeros + '0';
                }
                AppContext.model.setValue(concatSSN, zeros.concat(ssn));
            }
            let errorField = [];
            errorField = application.errorField.filter(err => err !== errorMessages.GL035);
            errorField = errorField.filter(err => err !== errorMessages.GL113);
            application.errorField = errorField;
            application.isError = false;
            if (errorField.length !== 0) {
                application.isError = true;
            }
        }
        AppContext.notification.error(application.errorField);
        if (application.isError) {
            window.scrollTo(0, 0);
        } else {
            AppContext.notification.clearError();
        }
    };

    navigateToNextPage = () => {
        let finalErrorFlag = this.validateFields();
        let application = AppContext.model.getValue('application');
        let personSSN = application.individual.ssn;
        this.validateSSN(personSSN, application, 'application.individual.ssn');
        let hasAlias = AppContext.model.getValue('application.individual.hasAlias');
        let validateDOM = AppContext.pagedetails.getPageContext().validate();

        if (!validateDOM.isError && !finalErrorFlag) {
            if (hasAlias === 'Y') {
                workflowUtills.addSubScreenAfterCurrentScreen('ARRIO');
            } else {
                workflowUtills.removePage('ARRIO');
            }
            navigationUtills.toNextPage();
        } else {
            window.scrollTo(0, 0);
        }
    };
}

export default Indiv;
