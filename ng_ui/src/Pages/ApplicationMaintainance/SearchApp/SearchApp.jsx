import Template from './SearchApp.rt';
import { PageConfig, UXPage, AppContext } from '@d-lift/core';
import {} from '@/Validations/customValidations.js';
import errorMessages from '../../../../public/validation/commonValidation.json';
import navigationUtills from '@/ScreenFlowUtils/NavigationUtills';
import React from 'react';
import { UX } from '@d-lift/uxcomponents';
import './SearchApp.css';

@PageConfig({
    ContextRoot: 'ApplicationMaintainance',
    PageName: 'SearchApp',
    Path: ['/searchapp', '/ARSAR'],
    Template: Template,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Application Search',
})
class SearchApp extends UXPage {
    initializeModel() {
        let searchData = {
            searchData: {
                errorField: [],
                isError: false,
                first: '',
                middle: '',
                last: '',
                suffix: '',
                ssn: '',
                appNumber: '',
                caseNumber: '',
                beginDate: '',
                endDate: '',
                selectedPrograms: [],
                selectedCounty: '',
                primaryIndividual: [],
                workerUserId: '',
                source: '',
                datatableCollection: [
                    {
                        appNumber: 'T12345678',
                        primaryIndividual: 'Test Data1',
                        program: 'SNAP',
                        appCounty: 'India',
                        appReceivedDate: '14-09-2020',
                        appStatus: 'Pending Application Denied',
                        workerUserId: 'DevId1',
                        readOnly: false,
                    },
                    {
                        appNumber: 'T12345679',
                        primaryIndividual: 'Test Data2',
                        program: 'TANF',
                        appCounty: 'India',
                        appReceivedDate: '14-09-2020',
                        appStatus: 'Application Pending',
                        workerUserId: 'DevId2',
                        readOnly: true,
                    },
                    {
                        appNumber: 'T12345677',
                        primaryIndividual: 'Test Data3',
                        program: 'WIC',
                        appCounty: 'India',
                        appReceivedDate: '14-09-2020',
                        appStatus: 'Application Pending',
                        workerUserId: 'DevId3',
                        readOnly: true,
                    },
                ],
                withdrawalDate: '',
                withdrawalReason: '',
                clinicCounty: '',
                clinicName: '',
                clinicId: '',
                addrLine1: '',
                addrLine2: '',
                city: '',
                state: '',
                zipcode: '',
                phone: '',
            },
        };

        return searchData;
    }

    validateAppNum = searchData => {
        if (searchData.appNumber && !searchData.appNumber.toUpperCase().startsWith('T')) {
            let errorField = [];
            errorField = searchData.errorField.filter(err => !err.includes(errorMessages.GANEW173));
            searchData.errorField = errorField;
            if (!searchData.errorField.some(err => err === errorMessages.GANEW173)) {
                searchData.errorField.push(errorMessages.GANEW173);
            }
            searchData.isError = true;
        } else if (
            searchData.appNumber &&
            searchData.appNumber.toUpperCase().startsWith('T') &&
            searchData.appNumber.length < 9
        ) {
            var msg;
            if (/\D{1}/.test(searchData.appNumber.slice(1))) {
                msg = ' and contain only digits';
            } else {
                msg = ' and 8 digits, missing ' + (9 - searchData.appNumber.length) + ' digits';
            }
            let errorField = [];
            errorField = searchData.errorField.filter(err => !err.includes(errorMessages.GANEW173));
            searchData.errorField = errorField;
            searchData.errorField.push(errorMessages.GANEW173 + msg);
            searchData.isError = true;
        } else {
            if (/\D{1}/.test(searchData.appNumber.slice(1))) {
                msg = ' and contain only digits';
                let errorField = [];
                errorField = searchData.errorField.filter(
                    err => !err.includes(errorMessages.GANEW173),
                );
                searchData.errorField = errorField;
                searchData.errorField.push(errorMessages.GANEW173 + msg);
                searchData.isError = true;
            } else {
                let errorField = [];
                errorField = searchData.errorField.filter(
                    err => !err.includes(errorMessages.GANEW173),
                );
                searchData.errorField = errorField;
                searchData.isError = false;
                if (searchData.errorField.length !== 0) {
                    searchData.isError = true;
                }
            }
        }
    };

    validateEndDate = event => {
        let searchData = AppContext.model.getValue('searchData');
        if (event && event._sugar_utc === false) {
            const beginDate = new Date(searchData.beginDate);
            const endDate = new Date(searchData.endDate);
            if (beginDate && endDate && beginDate > endDate) {
                if (!searchData.errorField.some(err => err === errorMessages.GL021)) {
                    searchData.errorField.push(errorMessages.GL021);
                }
                searchData.isError = true;
                AppContext.notification.error(searchData.errorField);
            } else {
                let errorField = [];
                errorField = searchData.errorField.filter(err => err !== errorMessages.GL021);
                searchData.errorField = errorField;
                searchData.isError = false;
                if (searchData.errorField.length !== 0) {
                    searchData.isError = true;
                    AppContext.notification.error(searchData.errorField);
                }
            }
        }
    };

    validateSSN = searchData => {
        if (searchData.ssn && /^0*$/.test(searchData.ssn)) {
            if (!searchData.errorField.some(err => err === errorMessages.GL035)) {
                searchData.errorField.push(errorMessages.GL035);
            }
            searchData.isError = true;
        } else if (searchData.ssn.length < 4 && !/^0*$/.test(searchData.ssn)) {
            if (!searchData.errorField.some(err => err === errorMessages.GL113)) {
                searchData.errorField.push(errorMessages.GL113);
            }
            let errorField = [];
            errorField = searchData.errorField.filter(err => err !== errorMessages.GL035);
            searchData.errorField = errorField;
            searchData.isError = true;
        } else {
            const ssnLength = searchData.ssn.length;
            if (ssnLength > 0) {
                let zeros = '';
                for (let i = 0; i < 9 - ssnLength; i++) {
                    zeros = zeros + '0';
                }
                searchData.ssn = zeros.concat(searchData.ssn);
            }
            let errorField = [];
            errorField = searchData.errorField.filter(err => err !== errorMessages.GL035);
            errorField = errorField.filter(err => err !== errorMessages.GL113);
            searchData.errorField = errorField;
            searchData.isError = false;
            if (errorField.length !== 0) {
                searchData.isError = true;
            }
        }
    };
    showTable = () => {
        let validatedDom = AppContext.pagedetails.getPageContext().validate();
        let searchData = AppContext.model.getValue('searchData');
        this.validateAppNum(searchData);
        this.validateSSN(searchData);
        if (
            searchData.last ||
            searchData.ssn ||
            searchData.appNumber ||
            searchData.caseNumber ||
            (searchData.beginDate && searchData.endDate)
        ) {
            let errorField = [];
            errorField = searchData.errorField.filter(err => err !== errorMessages.AR4009);
            searchData.errorField = errorField;
            searchData.isError = false;
            if (errorField.length !== 0) {
                searchData.isError = true;
            }
            AppContext.notification.error(searchData.errorField);
        } else {
            if (!searchData.errorField.some(err => err === errorMessages.AR4009)) {
                searchData.errorField.push(errorMessages.AR4009);
            }
            searchData.isError = true;
            AppContext.notification.error(searchData.errorField);
        }

        if (!validatedDom.isError && !searchData.isError) {
            AppContext.model.setValue('searchData.show', true);
            AppContext.notification.clearAll();
        } else {
            window.scrollTo(0, 0);
        }
    };

    editApp = (columnData, rowData, a) => {
        return <UX type="icon" symbol="fa fa-edit"></UX>;
    };

    withdraw = (columnData, rowData, a) => {
        if (rowData.readOnly) {
            return (
                <UX
                    type="icon"
                    symbol="fa fa-window-close"
                    click={event => this.toMaintainAppOld(columnData, rowData, a)}></UX>
            );
        } /* else {
            return (
                <UX
                    type="icon"
                    symbol="fa fa-file"
                    click={event => this.toMaintainAppOld(columnData, rowData, a)}></UX>
            );
        } */
    };

    toMaintainAppOld = (columnData, rowData, a) => {
        if (rowData.readOnly) {
            AppContext.model.setValue('searchData.appWithdraw', true);
            AppContext.model.setValue('searchData.selectedProgram', rowData.program);
            if (rowData.program === 'WIC') {
                AppContext.model.setValue('searchData.wicInfo', true);
                AppContext.model.setValue('searchData.wicInfoSubmit', true);
                AppContext.model.setValue('searchData.appWithdrawSubmit', false);
            } else {
                AppContext.model.setValue('searchData.wicInfo', false);
                AppContext.model.setValue('searchData.appWithdrawSubmit', true);
                AppContext.model.setValue('searchData.wicInfoSubmit', false);
            }
        } else {
            AppContext.model.setValue('searchData.appWithdraw', false);
        }
    };

    disposition = () => {
        let validatedDom = AppContext.pagedetails.getPageContext().validate();
        let searchData = AppContext.model.getValue('searchData');
        if (searchData.withdrawalDate && searchData.withdrawalReason) {
            let errorField = [];
            errorField = searchData.errorField.filter(
                err => err !== errorMessages.GANEW8 && err !== errorMessages.GANEW9,
            );
            searchData.errorField = errorField;
            searchData.isError = false;
            if (errorField.length !== 0) {
                searchData.isError = true;
            }
            AppContext.notification.error(searchData.errorField);
        } else {
            if (!searchData.withdrawalDate && !searchData.withdrawalReason) {
                let errorField = [];
                errorField.push(errorMessages.GANEW8);
                errorField.push(errorMessages.GANEW9);
                searchData.errorField = errorField;
            } else if (!searchData.withdrawalDate) {
                let errorField = [];
                errorField.push(errorMessages.GANEW8);
                searchData.errorField = errorField;
            } else if (!searchData.withdrawalReason) {
                let errorField = [];
                errorField.push(errorMessages.GANEW9);
                searchData.errorField = errorField;
            }
            searchData.isError = true;
            AppContext.notification.error(searchData.errorField);
        }

        if (!validatedDom.isError && !searchData.isError) {
            alert('Going to Next Page');
            AppContext.notification.clearAll();
            //navigationUtills.toNextPage();
        } else {
            window.scrollTo(0, 0);
        }
    };
}

export default SearchApp;
