import Template from './SearchApp.rt';
import { PageConfig, UXPage, AppContext, Util, Navigate } from '@d-lift/core';
import {} from '@/Validations/customValidations.js';
import errorMessages from '../../../../public/validation/commonValidation.json';
import React from 'react';
import { UX } from '@d-lift/uxcomponents';
import AppSearch from '@/Services/MaintainAppNew/AppSearch';
import './SearchApp.css';

@PageConfig({
    ContextRoot: 'ApplicationMaintainanceNew',
    PageName: 'SearchAppNew',
    Path: '/SearchAppNew',
    Template: Template,
    WorkFlowNavigation: false,
    PageType: 'workflow',
    Description: 'Application Search',
    LayoutStyle: 'home',
    ShowTitle: false,
    PageTitle: 'Search Application',
})
class SearchAppNew extends UXPage {
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
                datatableCollection: [],
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
                deleteModalOpen: false,
                deleteRowData: '',
                recentAppList: [],
            },
        };

        return searchData;
    }

    onPageLoad() {
        AppContext.model.setValue('searchData.module', 'searchApp');
        AppContext.model.setValue('searchData.rNavCond', 'searchTip');
        let recentAppListInSession = JSON.parse(Util.getSessionData('recentAppList'));
        if (recentAppListInSession != null)
            recentAppListInSession = recentAppListInSession.slice(0, 5);
        AppContext.model.setValue('searchData.recentAppList', recentAppListInSession);
    }
    onOpenModal = (columnData, rowData, a) => {
        AppContext.model.setValue('searchData.deleteRowData', rowData);
        AppContext.model.setValue('searchData.deleteModalOpen', true);
    };
    onCloseModal = () => {
        AppContext.model.setValue('searchData.deleteModalOpen', false);
    };
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

    NavToApplicationReg = e => {
        let appNum = e.target.innerText;
        Navigate.to('/ApplicationRegistrationNew/appRegNew', {
            maintainMode: 'Y',
            sspMode: 'N',
            appNumber: appNum,
        });
    };

    NavToSSPApplicationReg = e => {
        let appNum = e.target.innerText;
        Navigate.to('/ApplicationRegistrationNew/appRegNew', {
            maintainMode: 'N',
            sspMode: 'Y',
            appNumber: appNum,
        });
    };

    // showSSPData = () => {
    //     this.NavToSSPApplicationReg;
    // };

    showTable = () => {
        let validatedDom = AppContext.pagedetails.getPageContext().validate();
        let searchData = AppContext.model.getValue('searchData');
        this.validateAppNum(searchData);
        this.validateSSN(searchData);
        if (searchData.last || searchData.ssn || searchData.appNumber || searchData.first) {
            let errorField = [];
            errorField = searchData.errorField.filter(
                err => err !== errorMessages.AR4010 && err !== errorMessages.GL500,
            );
            searchData.errorField = errorField;
            searchData.isError = false;
            if (errorField.length !== 0) {
                searchData.isError = true;
            }
            AppContext.notification.error(searchData.errorField);
        } else {
            if (!searchData.errorField.some(err => err === errorMessages.AR4010)) {
                searchData.errorField.push(errorMessages.AR4010);
            }
            searchData.isError = true;
            AppContext.notification.error(searchData.errorField);
        }
        if (!validatedDom.isError && !searchData.isError) {
            this.searchAppAction(searchData);
            AppContext.notification.clearAll();
        } else {
            window.scrollTo(0, 0);
        }
    };

    callDeleteAppAction = () => {
        let appNum = AppContext.model.getValue('searchData.deleteRowData.appNum');
        let searchData = AppContext.model.getValue('searchData');
        AppContext.notification.clearAll();
        this.deleteAppAction(appNum, searchData);
        this.onCloseModal();
    };

    deleteAppAction = async (appNum, searchData) => {
        await AppSearch.deleteAppService(appNum).then(response => {
            if (!response.success) {
                if (!searchData.errorField.some(err => err === errorMessages.GL500)) {
                    searchData.errorField.push(errorMessages.GL500);
                }
                AppContext.notification.error(searchData.errorField);
                AppContext.model.setValue('searchData.show', false);
                window.scrollTo(0, 0);
            } else {
                let notification = errorMessages.GL200.replace('~', appNum);
                AppContext.notification.info(notification);
                window.scrollTo(0, 0);
            }
        });
    };

    searchAppAction = async searchData => {
        console.log('calling search app methods');
        let finalSearchData = [];
        await AppSearch.searchAppService(
            searchData.appNumber,
            searchData.ssn,
            searchData.first,
            searchData.last,
        ).then(response => {
            if (!response.success) {
                if (!searchData.errorField.some(err => err === errorMessages.GL500)) {
                    searchData.errorField.push(errorMessages.GL500);
                }
                AppContext.notification.error(searchData.errorField);
                AppContext.model.setValue('searchData.show', false);
                window.scrollTo(0, 0);
            } else {
                let recentAppList = JSON.parse(Util.getSessionData('recentAppList'));
                response.foundApps.forEach(element => {
                    let found = false;
                    if (recentAppList != null) {
                        recentAppList.forEach(appNum => {
                            if (appNum === element.appNum) {
                                found = true;
                            }
                        });
                    } else {
                        recentAppList = [];
                    }
                    if (!found) recentAppList.unshift(element.appNum);
                    recentAppList = recentAppList.slice(0, 5);
                });
                Util.setSessionData('recentAppList', JSON.stringify(recentAppList));
                AppContext.model.setValue('searchData.recentAppList', recentAppList);
                console.log(response.foundApps);
                AppContext.model.setValue('searchData.datatableCollection', response.foundApps);
                AppContext.model.setValue('searchData.show', true);
                AppContext.notification.clearAll();
            }
        });
    };

    editApp = (columnData, rowData, a) => {
        return <UX type="icon" symbol="fa fa-edit"></UX>;
    };

    appNumLink = (columnData, rowData) => {
        let appNumber = rowData.appNum;
        console.log(rowData);
        if (rowData.flag === 'SSP') {
            return (
                <UX
                    click={this.NavToSSPApplicationReg}
                    target="_self"
                    type="hyperLink"
                    className="hyperLink">
                    {appNumber}
                </UX>
            );
        } else {
            return (
                <UX
                    click={this.NavToApplicationReg}
                    target="_self"
                    type="hyperLink"
                    className="hyperLink">
                    {appNumber}
                </UX>
            );
        }
    };

    formatStatCode = (columnData, rowData) => {
        let statDesc = Util.getRefTableDescriptionByCode('APPLICATIONSTATUS', rowData.appStatCd);
        console.log(rowData.appStatCd);
        console.log(statDesc);
        if (rowData.appStatCd === 'AP' || rowData.appStatCd === 'CP') {
            return (
                <UX mode="warning" type="badge" className="warnBadge">
                    {statDesc}
                </UX>
            );
        } else if (
            rowData.appStatCd === 'DE' ||
            rowData.appStatCd === 'DN' ||
            rowData.appStatCd === 'DP'
        ) {
            return (
                <UX mode="danger" type="badge" className="dangerBadge">
                    {statDesc}
                </UX>
            );
        } else if (rowData.appStatCd === 'DI') {
            return (
                <UX mode="default" type="badge" className="defaultBadge">
                    {statDesc}
                </UX>
            );
        } else {
            return (
                <UX mode="success" type="badge" className="successBadge">
                    {statDesc}
                </UX>
            );
        }
    };

    withdraw = (columnData, rowData, a) => {
        if (rowData.appStatCd !== 'DI') {
            return (
                <UX
                    type="image"
                    src="../icons/delete-button.png"
                    className="del-icon"
                    adaLabelText ="Delete Application"
                    click={event => this.onOpenModal(columnData, rowData, a)}></UX>
            );
        }
    };

    toMaintainApp = (columnData, rowData, a) => {
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

export default SearchAppNew;
