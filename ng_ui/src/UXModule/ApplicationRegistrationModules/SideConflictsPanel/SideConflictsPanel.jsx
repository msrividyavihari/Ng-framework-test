import { BaseComponent } from '@d-lift/uxcomponents';
import UITemplate from './SideConflictsPanel.rt';
import { AppContext } from '@d-lift/core';
import { Date } from 'sugar';
import _, { keys } from 'lodash';
import './SideConflictsPanel.css';
import React from 'react';

export default class SideConflictsPanel extends BaseComponent {
    json = {};
    constructor() {
        super();

        this.state = {
            open: false,
            errorMsg: '',
            checked: false,
        };

        AppContext.model.setValue('showApplicantConflicts', 'true');
        this.handleCheck = this.handleCheck.bind(this);
    }

    handleCheck(item, e) {
        let _mData = {};
        _mData = AppContext.model.getValue('appRegNew.conflits.solved');

        _mData !== undefined ? (_mData = _mData) : (_mData = {});

        let UNIQUE_ID = item.substring(item.indexOf('$') + 1, item.length);

        let conflictHandledData = {};
        let json = {};
        json = { ..._mData };
        conflictHandledData = { ...json[UNIQUE_ID] };
        if (e === 'ssp') {
            let sspVal = item.substring(item.indexOf(',') + 1, item.indexOf('-'));
            conflictHandledData[item.substring(0, item.indexOf(','))] = sspVal;
            //sspVal === 'No Value' ? '' : sspVal;
        } else {
            let dcVal = item.substring(item.indexOf('-') + 1, item.indexOf('$'));
            conflictHandledData[item.substring(0, item.indexOf(','))] = dcVal;
            //dcVal === 'No Value' ? '' : dcVal;
        }

        json[UNIQUE_ID] = conflictHandledData;
        if (Object.keys(json[UNIQUE_ID]).length >= this.json[UNIQUE_ID].length) {
            let id = 'conflictPanel' + UNIQUE_ID;
            var x = document.getElementById(id);
            console.log(x);
            if (x.className === 'ux-customcollapse-section headerIconAlert') {
                x.className = 'ux-customcollapse-section headerIconDone ';
            } else {
                //x.className = 'headerIconAlert';
                console.log('headerIconDone');
            }
        }
        AppContext.model.setValue('appRegNew.conflits.solved', json);
    }

    genRadioBtn = (item) => {
        return (
            item.substring(0, item.indexOf(',')) +
            item.substring(item.indexOf('$') + 1, item.length)
        );
    };

    headerKeys = (item) => {
        return item.substring(0, item.indexOf(','));
    };
    buildConflictPanelData = (UNIQUE_ID) => {
        let conflictHandledData = [];

        if (this.json[UNIQUE_ID]) {
            conflictHandledData = [...this.json[UNIQUE_ID]];
        }

        let result = [];
        let input = '';
        if (
            AppContext.model.getValue('showApplicantsConflicts') === true &&
            AppContext.model.getValue('fileClearStatus') === 'Cleared' &&
            AppContext.model.getValue('conflictPanelApplicants') !== 'solved'
        ) {
            input = AppContext.model.getValue('conflictApplicants');
        } else if (
            AppContext.model.getValue('showContactConflicts') === true &&
            AppContext.model.getValue('conflictPanelContacts') !== 'solved'
        ) {
            input = AppContext.model.getValue('contactConflicts');
        } else if (
            AppContext.model.getValue('showAuthRepConflicts') === true &&
            AppContext.model.getValue('conflictPanelAuthRep') !== 'solved'
        ) {
            input = AppContext.model.getValue('authRepConflicts');
        }

        input.map((temp) => {
            if (temp.PANEL_HEADERS.UNIQUE_ID === UNIQUE_ID) {
                let dcString = '';
                let sspString = '';
                let sspdata = temp.SSP_MODEL;
                Object.entries(sspdata).forEach(([key, value]) => {
                    sspString = value + '-';
                    let sspKey = key;
                    let dcdata = temp.DC_MODEL;
                    Object.entries(dcdata).forEach(([key, value]) => {
                        if (sspKey === key) {
                            dcString = value;
                            if (key !== 'EmailAdr') {
                                result.push(key + ',' + sspString + dcString + '$' + UNIQUE_ID);
                                if (!conflictHandledData.includes(key)) {
                                    conflictHandledData.push(key);
                                }
                            }
                        }
                    });
                });
            }
        });

        this.json[UNIQUE_ID] = conflictHandledData;
        return result;
    };

    buildApplicantCards = () => {
        var returnObj = '';
        if (
            AppContext.model.getValue('showApplicantsConflicts') === true &&
            AppContext.model.getValue('fileClearStatus') === 'Cleared' &&
            AppContext.model.getValue('conflictPanelApplicants') !== 'solved'
        ) {
            returnObj = AppContext.model.getValue('conflictApplicants');
        } else if (
            AppContext.model.getValue('showContactConflicts') === true &&
            AppContext.model.getValue('conflictPanelContacts') !== 'solved'
        ) {
            returnObj = AppContext.model.getValue('contactConflicts');
        } else if (
            AppContext.model.getValue('showAuthRepConflicts') === true &&
            AppContext.model.getValue('conflictPanelAuthRep') !== 'solved'
        ) {
            returnObj = AppContext.model.getValue('authRepConflicts');
        }
        return returnObj;
    };

    totalItems = () => {
        let pendingConflicts = '';
        if (
            AppContext.model.getValue('showApplicantsConflicts') === true &&
            AppContext.model.getValue('fileClearStatus') === 'Cleared' &&
            AppContext.model.getValue('conflictPanelApplicants') !== 'solved'
        ) {
            pendingConflicts = AppContext.model.getValue('conflictApplicants');
        } else if (
            AppContext.model.getValue('showContactConflicts') === true &&
            AppContext.model.getValue('conflictPanelContacts') !== 'solved'
        ) {
            pendingConflicts = AppContext.model.getValue('contactConflicts');
        } else if (
            AppContext.model.getValue('showAuthRepConflicts') === true &&
            AppContext.model.getValue('conflictPanelAuthRep') !== 'solved'
        ) {
            pendingConflicts = AppContext.model.getValue('authRepConflicts');
        }
        let containedItems = 0;
        if (pendingConflicts !== undefined) {
            for (let i = 0; i < pendingConflicts.length; i++) {
                keys(pendingConflicts[i].SSP_MODEL).includes('EmailAdr') === true
                    ? (containedItems += keys(pendingConflicts[i].SSP_MODEL).length - 1)
                    : (containedItems += keys(pendingConflicts[i].SSP_MODEL).length);
            }
        }
        let item = containedItems > 1 ? `${containedItems} Items` : `${containedItems} Item`;
        return item;
    };

    renderComponent() {
        return UITemplate.bind(this).apply(this);
    }
    submitData = () => {
        let aboolean = false;
        if (
            AppContext.model.getValue('showApplicantsConflicts') === true &&
            AppContext.model.getValue('fileClearStatus') === 'Cleared' &&
            AppContext.model.getValue('conflictPanelApplicants') !== 'solved'
        ) {
            aboolean = this.conflictApplicants();
        } else if (
            AppContext.model.getValue('showContactConflicts') === true &&
            AppContext.model.getValue('conflictPanelContacts') !== 'solved'
        ) {
            aboolean = this.conflictContacts();
        } else if (
            AppContext.model.getValue('showAuthRepConflicts') === true &&
            AppContext.model.getValue('conflictPanelAuthRep') !== 'solved'
        ) {
            aboolean = this.conflictAuthRep();
        }
        AppContext.model.setValue('conflictPanelAlert', aboolean);
        if (!aboolean) {
            document
                .getElementById('btn-heading')
                .scrollIntoView({ behavior: 'smooth', block: 'start', inline: 'end' });
        } else {
            var x = document.getElementById('mySidepanel');
            if (x.className === 'sidepanel-open') {
                x.className = 'sidepanel-close';
            }
        }
    };

    conflictApplicants = () => {
        var applicants = AppContext.model.getValue('applicants');
        var data = AppContext.model.getValue('appRegNew.conflits.solved');
        let conflits = AppContext.model.getValue('conflictApplicants');
        let aboolean = data !== undefined ? this.validateSubmit(conflits, data) : false;
        if (data !== undefined && aboolean) {
            Object.entries(data).map((item) => {
                applicants.forEach((ele) => {
                    if (ele.ssn == item[0]) {
                        Object.entries(item[1]).map((e) => {
                            if (e[1] !== 'No Value') {
                                ele[e[0]] = e[1] ? e[1] : ele[e[1]];
                            }
                        });
                    }
                });
            });
            this.setState({ open: true });
            AppContext.model.setValue('applicants', applicants);
            AppContext.model.setValue('appRegNew.conflits.solved', null);
            AppContext.model.setValue('conflictPanelApplicants', 'solved');
            AppContext.model.getValue('conflictApplicants').map((i) => {
                if (i.PANEL_HEADERS !== undefined) {
                    i.PANEL_HEADERS.conflicts_resolved = 'YES';
                }
            });
            AppContext.model.setValue('applicantsConflictAlert', false);
        } else {
            AppContext.model.getValue('conflictApplicants').map((i) => {
                if (i.PANEL_HEADERS !== undefined) {
                    i.PANEL_HEADERS.conflicts_resolved = 'NO';
                }
            });
        }

        return aboolean;
    };

    conflictContacts = () => {
        var contactInfo = AppContext.model.getValue('contactInfo');
        var data = AppContext.model.getValue('appRegNew.conflits.solved');
        console.log(data);
        let conflits = AppContext.model.getValue('contactConflicts');
        let aboolean = false;
        if (
            data !== undefined &&
            data !== null &&
            conflits.length === Object.entries(data).length
        ) {
            aboolean = this.validateSubmit(conflits, data);
        } else {
            aboolean = false;
        }
        if (data !== undefined && aboolean) {
            AppContext.model.setValue('showContactConflicts', false);
            if (data['WeekDayPreference'] !== undefined) {
                let weekDayPreference = data['WeekDayPreference'];
                if (
                    weekDayPreference.weekdayContTime &&
                    weekDayPreference.weekdayContTime !== 'No Value'
                ) {
                    contactInfo.arApplicationForAid.preferredContactTime =
                        weekDayPreference.weekdayContTime;
                }
                if (
                    weekDayPreference.weekdayContMethSw &&
                    weekDayPreference.weekdayContMethSw !== 'No Value'
                ) {
                    contactInfo.arApplicationForAid.preferredContactMethod =
                        weekDayPreference.weekdayContMethSw;
                }
            }

            if (data['PhoneDetails'] !== undefined) {
                let phoneDetails = data['PhoneDetails'];
                Object.entries(phoneDetails).forEach(([key, value]) => {
                    if (value === 'No Value') {
                        value = '';
                    }
                    if (key.substring(0, 4) === 'prim') {
                        if (key === 'primPhnNum') {
                            AppContext.model.setValue('contactInfo.phoneDetails.PRP.phnNum', value);
                        }
                        if (key === 'primPhoneExtn') {
                            AppContext.model.setValue(
                                'contactInfo.phoneDetails.PRP.phoneExtn',
                                value,
                            );
                        } else if (key === 'primPhnComments') {
                            AppContext.model.setValue(
                                'contactInfo.phoneDetails.PRP.phnComments',
                                value,
                            );
                        }
                    } else if (key.substring(0, 3) === 'wrk') {
                        AppContext.model.setValue('contactInfo.phoneDetails.WOP.phnNum', value);
                    } else if (key.substring(0, 3) === 'alt') {
                        AppContext.model.setValue('contactInfo.phoneDetails.ALP.phnNum', value);
                    }
                });
            }

            if (data['AddressDetails'] !== undefined) {
                let addressDetails = data['AddressDetails'];
                Object.entries(addressDetails).forEach(([key, value]) => {
                    if (value === 'No Value') {
                        value = '';
                    }
                    if (key.substring(0, 3) === 'res') {
                        contactInfo.addressDetails.addressInfo.RE[
                            this.getKeyForcontactInfo(key, 3)
                        ] = value;
                    } else if (key.substring(0, 4) === 'mail') {
                        contactInfo.addressDetails.addressInfo.MA[
                            this.getKeyForcontactInfo(key, 4)
                        ] = value;
                    }
                });
            }
            this.setState({ open: true });
            AppContext.model.setValue('contactInfo', contactInfo);
            AppContext.model.setValue('appRegNew.conflits.solved', null);
            AppContext.model.setValue('conflictPanelContacts', 'solved');
            AppContext.model.getValue('contactConflicts').map((i) => {
                if (i.PANEL_HEADERS !== undefined) {
                    i.PANEL_HEADERS.conflicts_resolved = 'YES';
                }
            });
            AppContext.model.setValue('contactsConflictAlert', false);
        } else {
            AppContext.model.getValue('contactConflicts').map((i) => {
                if (i.PANEL_HEADERS !== undefined) {
                    i.PANEL_HEADERS.conflicts_resolved = 'NO';
                }
            });
        }

        return aboolean;
    };

    conflictAuthRep = () => {
        var data = AppContext.model.getValue('appRegNew.conflits.solved');
        let conflits = AppContext.model.getValue('authRepConflicts');
        let aboolean = false;
        if (
            data !== undefined &&
            data !== null &&
            conflits.length === Object.entries(data).length
        ) {
            aboolean = this.validateSubmit(conflits, data);
        } else {
            aboolean = false;
        }

        if (data !== undefined && aboolean) {
            AppContext.model.setValue('showAuthRepConflicts', false);
            if (data['AuthIndividualInformation'] !== undefined) {
                let authRepDetails = data['AuthIndividualInformation'];

                Object.entries(authRepDetails).forEach(([key, value]) => {
                    if (value === 'No Value') {
                        value = '';
                    }

                    if (key === 'authRepFirstName') {
                        AppContext.model.setValue('authRep.authRepDetails.firstName', value);
                    } else if (key === 'authRepMiddleName') {
                        AppContext.model.setValue('authRep.authRepDetails.middleName', value);
                    } else if (key === 'authRepLastName') {
                        AppContext.model.setValue('authRep.authRepDetails.lastName', value);
                    } else if (key === 'l1Adr') {
                        AppContext.model.setValue('authRep.addrDetails.addrLine', value);
                    } else if (key === 'l2Adr') {
                        AppContext.model.setValue('authRep.addrDetails.addrLine1', value);
                    } else if (key === 'cityAdr') {
                        AppContext.model.setValue('authRep.addrDetails.addrCity', value);
                    } else if (key === 'staAdr') {
                        AppContext.model.setValue('authRep.addrDetails.addrStateCd', value);
                    } else if (key === 'zipAdr') {
                        AppContext.model.setValue('authRep.addrDetails.addrZip5', value);
                    }
                });
            }

            if (data['AuthPhoneConflicts'] !== undefined) {
                let authRepDetails = data['AuthPhoneConflicts'];

                Object.entries(authRepDetails).forEach(([key, value]) => {
                    if (value === 'No Value') {
                        value = '';
                    }

                    if (key === 'phnNum') {
                        AppContext.model.setValue('authRep.phoneDetails.PRP.phnNum', value);
                    }
                });
            }

            this.setState({ open: true });
            AppContext.model.setValue('appRegNew.conflits.solved', null);
            AppContext.model.setValue('conflictPanelAuthRep', 'solved');
            AppContext.model.getValue('authRepConflicts').map((i) => {
                if (i.PANEL_HEADERS !== undefined) {
                    i.PANEL_HEADERS.conflicts_resolved = 'YES';
                }
            });
            AppContext.model.setValue('authRepConflictAlert', false);
        } else {
            AppContext.model.getValue('authRepConflicts').map((i) => {
                if (i.PANEL_HEADERS !== undefined) {
                    i.PANEL_HEADERS.conflicts_resolved = 'NO';
                }
            });
        }
        return aboolean;
    };

    getKeyForcontactInfo = (key, index) => {
        let result = key.substring(index, key.length);
        result = result.charAt(0).toLowerCase() + result.slice(1);
        console.log(result);
        return result;
    };

    getHeader = () => {
        let pendingConflicts;
        if (
            AppContext.model.getValue('showApplicantsConflicts') === true &&
            AppContext.model.getValue('fileClearStatus') === 'Cleared' &&
            AppContext.model.getValue('conflictPanelApplicants') !== 'solved'
        ) {
            pendingConflicts = AppContext.model.getValue('conflictApplicants');
        } else if (
            AppContext.model.getValue('showContactConflicts') === true &&
            AppContext.model.getValue('conflictPanelContacts') !== 'solved'
        ) {
            pendingConflicts = AppContext.model.getValue('contactConflicts');
        } else if (
            AppContext.model.getValue('showAuthRepConflicts') === true &&
            AppContext.model.getValue('conflictPanelAuthRep') !== 'solved'
        ) {
            pendingConflicts = AppContext.model.getValue('authRepConflicts');
        }
        let item = 'NA';
        if (pendingConflicts !== undefined) {
            for (let i = 0; i < pendingConflicts.length; i++) {
                if (pendingConflicts[i].PANEL_HEADERS !== undefined) {
                    item = pendingConflicts[i].PANEL_HEADERS.topHeading;
                    break;
                }
            }
        }

        return item;
    };

    validateSubmit = (conflits, data) => {
        let aboolean = [];
        // let allItemSelected = true;
        if (data !== undefined) {
            Object.entries(data).map((item) => {
                console.log(item);
                let size = 0;
                conflits.forEach((key) => {
                    console.log(key);
                    if (key.PANEL_HEADERS.UNIQUE_ID == item[0]) {
                        if (key.SSP_MODEL.hasOwnProperty('EmailAdr')) {
                            size = Object.keys(key.DC_MODEL).length - 1;
                        } else {
                            size = Object.keys(key.DC_MODEL).length;
                        }
                    }
                });
                let length = Object.keys(item[1]).length;
                if (size === length) {
                    aboolean.push(true);
                } else {
                    // allItemSelected = false;
                    aboolean.push(false);
                }
            });
        }
        return aboolean.includes(false) ? false : true;
    };
}
