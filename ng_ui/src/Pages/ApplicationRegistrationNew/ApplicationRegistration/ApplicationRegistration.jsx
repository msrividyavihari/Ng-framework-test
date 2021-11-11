import Template from './ApplicationRegistration.rt';
import { PageConfig, UXPage, AppContext, Util } from '@d-lift/core';
import './ApplicationRegistration.css';
import { CommonUtils } from '../../../Utills/Common/CommonUtils';
import _ from 'lodash';
import { Date } from 'sugar';
import basicAxiosInterceptor from '@/HttpInterceptors/BasicAxiosInterCeptor';
import { ResponseHandlers } from '@/Utills/DataAdapter/DataAdaptedUtill';
import {} from '@/Validations/customValidations.js';
import spinnerUtil from '@/Utills/SpinnerUtil/SpinnerUtill';
import md5 from '@/Utills/MD5Utils';
import errorMessages from '../../../../public/validation/commonValidation.json';
import { UX } from '@d-lift/uxcomponents';
import PageUtils from '@/ScreenFlowUtils/PageUtills';

@PageConfig({
    ContextRoot: 'ApplicationRegistrationNew',
    PageName: 'ApplicationRegistration',
    Path: '/appRegNew',
    Template: Template,
    ReferenceTable: true,
    ShowTitle: true,
    PageTitle: 'Application Registration',
    TemplateOptions: true,
    Description: 'Application Registartion',
    LayoutStyle: 'home',
})
class ApplicationRegistration extends UXPage {
    onPageLoad() {
        // AppContext.model.setValue('sspApplicants', [
        //     {
        //         firstName: 'John',
        //         lastName: 'Doe',
        //         ssn: '998877670',
        //         dob: '05/09/1962',
        //         gender: 'Male',
        //     },
        //     {
        //         firstName: 'Kane',
        //         lastName: 'Adams',
        //         ssn: '998877665',
        //         dob: '11/1/1978',
        //         gender: 'Female',
        //     },
        //     {
        //         firstName: 'Smith',
        //         lastName: 'Will',
        //         ssn: '232323232',
        //         dob: '11/1/1977',
        //         gender: 'Male',
        //     },
        // {
        //     primaryApplicantSw: 'No',
        //     includeApplicantSw: 'Yes',
        //     firstName: 'Christopher',
        //     middleName: '',
        //     lastName: 'Smith',
        //     suffix: '',
        //     gender: 'Male',
        //     dob: '11/1/1979',
        //     age: '41',
        //     ssn: '012-45-3467',
        //     race: 'White',
        //     ethnicity: 'Hispanic',
        //     alias: 'No',
        //     aliasFirstName: '',
        //     aliasMiddleName: '',
        //     aliasLastName: '',
        //     aliasSuffix: '',
        //     aliasGender: '',
        //     interpreterSw: 'Yes',
        //     primaryLanguage: 'Spanish',
        //     accommodationSw: 'No',
        //     typeAccomodation: '',
        //     authRepresentativeSw: 'No',
        //     ebtcardSw: 'Yes',
        //     registervoteSw: 'Y',
        //     indvStatusSw: 'T',
        // },
        // ]);
        AppContext.model.setValue('conflictPanel.sspApplicant.open', false);
        let applicantsList = AppContext.model.getValue('applicants');
        if (undefined !== applicantsList) {
            const listItems = applicantsList.map((applicant) => {
                return applicant.firstName;
            });
            AppContext.model.setValue('applicantsNames', listItems);
        }
        if (this.props.location.state) {
            AppContext.model.setValue(
                'maintainMode',
                this.props.location.state.maintainMode
                    ? this.props.location.state.maintainMode
                    : 'N',
            );
            AppContext.model.setValue(
                'sspMode',
                this.props.location.state.sspMode ? this.props.location.state.sspMode : 'N',
            );
            AppContext.model.setValue(
                'appRegNew.ArApplicationForAid.appNum',
                this.props.location.state.appNumber,
            );

            AppContext.model.setValue('ARRAP.RN.appNum', this.props.location.state.appNumber);
        } else {
            AppContext.model.setValue('maintainMode', 'N');
            AppContext.model.setValue('sspMode', 'N');
        }
        AppContext.model.setValue('appReg.applicantType', 'P');
        AppContext.model.setValue('ARRAP.RN.rNavCond', 'appInfo');
        AppContext.model.setValue('ARRAP.RN.module', 'appReg');
        AppContext.model.setValue('fileClearIndvs', []);
        AppContext.model.setValue('contactInfo.addressDetails.addressInfo.RE.addrFormatCd', 'US');
        AppContext.model.setValue('contactInfo.addressDetails.addressInfo.MA.addrFormatCd', 'US');

        //let appList = ['T12345678', 'T87654321', 'T13579246', 'T99887766'];
        // AppContext.model.setValue('ARRAP.RN.rNavCond', 'searchTip');
        // AppContext.model.setValue('ARRAP.RN.module', 'searchApp');
        //AppContext.model.setValue('ARRAP.RN.recentAppList', appList);

        if (AppContext.model.getValue('maintainMode') === 'Y') {
            AppContext.model.setValue('appRegNew.ArApplicationForAid', '');
            AppContext.model.setValue('appReg.applicantType', 'A');
            AppContext.model.setValue('pcNxtclick', true);
            AppContext.model.setValue(
                'appRegNew.ArApplicationForAid.appNum',
                this.props.location.state.appNumber,
            );
            AppContext.model.setValue('fileClearStatus', 'Cleared');
            this.preloadDataInMaintainMode();
            document.getElementById(`application_info_exclamation`).classList.add('success');
            this.enablePanelById('file_clearance');
            document.getElementById(`file_clearance_exclamation`).classList.add('success');
            this.enablePanelById('applicants');
        }
        if (AppContext.model.getValue('sspMode') === 'Y') {
            AppContext.model.setValue(
                'appRegNew.ArApplicationForAid.appNum',
                this.props.location.state.appNumber,
            );
            AppContext.model.setValue('appReg.applicantType', 'P');
            this.preloadDataInSSPMode();
            // document.getElementById(`application_info_exclamation`).classList.add('success');
            // this.enablePanelById('file_clearance');
            //document.getElementById(`file_clearance_exclamation`).classList.add('success');
            //this.enablePanelById('applicants');
        }
    }

    openPanelByID = (id) => {
        if (!document.getElementById(id + '_panel').classList.contains('ux-custom-panel-active')) {
            document.getElementById(id + '_header').firstChild.click();
            document
                .getElementById(id + '_panel')
                .scrollIntoView({ behavior: 'smooth', block: 'nearest', inline: 'start' });
        }
    };

    closePanelByID = (id) => {
        if (document.getElementById(id + '_panel').classList.contains('ux-custom-panel-active'))
            document.getElementById(id + '_header').firstChild.click();
    };

    enablePanelById = (id) => {
        if (document.getElementById(id + '_panel').classList.contains('edit-mode-off')) {
            document.getElementById(id + '_panel').classList.remove('edit-mode-off');
            document.getElementById(id + '_panel').classList.add('edit-mode-on');
        }
    };

    disablePanelById = (id) => {
        if (document.getElementById(id + '_panel').classList.contains('edit-mode-on')) {
            document.getElementById(id + '_panel').classList.remove('edit-mode-on');
            document.getElementById(id + '_panel').classList.add('edit-mode-off');
        }
    };

    openNextPanel = (id) => {
        this.enablePanelById(id);
        this.openPanelByID(id);
    };

    contactInfoData = async () => {
        AppContext.model.setValue('caseAssoc.confirmFlg', false);
        AppContext.model.setValue('case.id.org', AppContext.model.getValue('caseAssociate.id'));

        await PageUtils.associateCase(0);
        await PageUtils.getContactDetails(
            AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum'),
            AppContext.model.getValue('caseAssociate.id'),
        );
    };

    async setRightNav(id) {
        if (id === 'file_clearance' || id === 'applicants') {
            AppContext.model.setValue('ARRAP.RN.rNavCond', '');
        }
    }

    async nextClick(id, nxtId, isServiceCallRequired) {
        // Validation logic within the panel
        const node = document.getElementById(id);
        const childNodes = node.querySelectorAll('[model]');
        await _.forEach(childNodes, function (childnode) {
            if (childnode.getAttribute('model') !== undefined) {
                if (AppContext.model.getValue(childnode.getAttribute('model')) === undefined) {
                    AppContext.pagedetails
                        .getPageContext()
                        .stateHandler(childnode.getAttribute('model'), undefined);
                }
            }
        });
        if (id === 'applicants') {
            let appl = AppContext.model.getValue('applicants');
            this.enableFileClearPanel();
            if (!Array.isArray(appl)) {
                appl = [appl];
            }
            let fieldValidation = appl.some(
                (applicant) =>
                    applicant.firstName === undefined ||
                    applicant.lastName === undefined ||
                    applicant.dob === undefined ||
                    applicant.gender === undefined ||
                    applicant.race === undefined ||
                    applicant.ethnicity === undefined ||
                    applicant.ethnicity.length === 0 ||
                    applicant.race.length === 0 ||
                    applicant.gender.length === 0,
            );
            if (fieldValidation) {
                AppContext.model.setValue('applicantsCustomError', true);
                AppContext.model.setValue('applicantsCustomErrorMsg', errorMessages.GANEW552);
                document
                    .getElementById('applicants_panel')
                    .scrollIntoView({ behavior: 'smooth', block: 'start', inline: 'end' });
                return;
            } else {
                var existAppl = AppContext.model.getValue('applicants').filter((i) => {
                    if (i.indvStatusSw !== 'T') return i;
                });
                var existApplLen = existAppl.length;

                let validation = appl.some((applicant) => applicant.primaryApplicantSw === 'Yes');
                if (!validation) {
                    AppContext.model.setValue('primaryMissingError', true);
                }
                if (
                    AppContext.model.getValue('sspMode') === 'Y' &&
                    (existApplLen > 0 || !PageUtils.isSSPPanelGreenNotchResolved())
                ) {
                    AppContext.model.getValue('fileClearStatus') === 'Cleared'
                        ? AppContext.model.setValue('sspIndvsAlert', false)
                        : AppContext.model.setValue('sspIndvsAlert', true);

                    if (!AppContext.model.getValue('conflictApplicants')) {
                        AppContext.model.setValue('conflictPanelApplicants', 'solved');
                    }

                    AppContext.model.getValue('conflictPanelApplicants') === 'solved'
                        ? AppContext.model.setValue('applicantsConflictAlert', false)
                        : AppContext.model.setValue('applicantsConflictAlert', true);

                    if (
                        AppContext.model.getValue('sspIndvsAlert') === true ||
                        AppContext.model.getValue('applicantsConflictAlert') === true
                    ) {
                        document
                            .getElementById('applicants_panel')
                            .scrollIntoView({ behavior: 'smooth', block: 'start', inline: 'end' });
                    }
                }

                if (
                    node.querySelectorAll("[is-error='true']").length === 0 &&
                    AppContext.model.getValue('primaryMissingError') !== true &&
                    AppContext.model.getValue('sspIndvsAlert') !== true &&
                    AppContext.model.getValue('applicantsConflictAlert') !== true
                ) {
                    spinnerUtil.show();
                    if ('Y' === isServiceCallRequired) {
                        const serCall = await this.serviceCall(id);
                        if (AppContext.model.getValue('maintainMode') === 'Y') {
                            // CommonUtils.disableCaseAssociation();
                            await CommonUtils.selAssoCase(
                                AppContext.model.getValue('caseAssociate.id'),
                            );
                        }
                        if (serCall) {
                            this.closePanelByID(id);
                            if (AppContext.model.getValue('caseAssociation.totalCaseNum') === 0) {
                                await this.contactInfoData();
                                nxtId = 'contact_info';
                            }
                            this.openNextPanel(nxtId);
                            this.setProgressBar(nxtId);
                        }
                        this.setRightNav(nxtId);
                        spinnerUtil.hide();

                        document
                            .getElementById('applicants_header')
                            .classList.add('ux-panel-success');
                        let element = document.getElementById(`applicants_exclamation`);
                        if (element.classList.contains('exclamation')) {
                            element.classList.remove('exclamation');
                        }
                        element.classList.add('success');
                    } else {
                        document
                            .getElementById(`applicants_header`)
                            .classList.remove('ux-panel-success');
                        let element = document.getElementById(`applicants_exclamation`);
                        element.classList.add('exclamation');
                    }
                } else {
                    document
                        .getElementById(`applicants_header`)
                        .classList.remove('ux-panel-success');
                    let element = document.getElementById(`applicants_exclamation`);
                    element.classList.add('exclamation');
                    document
                        .getElementById('applicants_panel')
                        .scrollIntoView({ behavior: 'smooth', block: 'start', inline: 'end' });
                }
            }
        } else {
            if (node.querySelectorAll("[is-error='true']").length === 0) {
                spinnerUtil.show();
                if ('Y' === isServiceCallRequired) {
                    if (await this.serviceCall(id)) {
                        this.closePanelByID(id);
                        this.openNextPanel(nxtId);
                        this.setProgressBar(nxtId);
                    }
                } else {
                    this.closePanelByID(id);
                    this.openNextPanel(nxtId);
                    this.setProgressBar(nxtId);
                }
                this.setRightNav(nxtId);
                spinnerUtil.hide();
                document.getElementById(id + `_exclamation`).classList.add('success');
            } else {
                document.getElementById(id + `_exclamation`).classList.add('exclamation');
            }
        }
    }

    async setProgressBar(id) {
        let progress = AppContext.model.getValue('appReg.Progress');
        if (id === 'file_clearance') {
            if (!progress || progress < 10) AppContext.model.setValue('appReg.Progress', '10');
        } else if (id === 'case_association') {
            if (!progress || progress < 40) AppContext.model.setValue('appReg.Progress', '40');
        } else if (id === 'contact_info') {
            if (!progress || progress < 60) AppContext.model.setValue('appReg.Progress', '60');
        } else if (id === 'program_selection') {
            if (!progress || progress < 80) AppContext.model.setValue('appReg.Progress', '80');
        } else if (id === 'review') {
            if (!progress || progress < 90) AppContext.model.setValue('appReg.Progress', '90');
        }
        if (AppContext.model.getValue('appReg.Progress') > 0) {
            AppContext.model.setValue('appReg.ProgressCheck', 'true');
        }
    }

    async serviceCall(id) {
        let mode = AppContext.model.getValue('maintainMode');

        // console.log(!md5(id));
        if (id === 'application_info') {
            if (
                AppContext.model.getValue('sspMode') === 'Y' &&
                !AppContext.model.getValue('conflictPanel.sspApplicant.open')
            ) {
                await PageUtils.loadDataintoFileClearanceSSP();
            }
            if (!md5(id)) {
                return true;
            }
            if (
                !AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum') ||
                AppContext.model.getValue('sspMode') === 'Y'
            ) {
                return true;
            } else {
                let updAppSuccFlg = this.updApp();
                return updAppSuccFlg;
            }
        }

        //Service Call on click of Next in File Clearance screen -- Start
        else if (id === 'file_clearance') {
            if (AppContext.model.getValue('sspMode') === 'Y') {
                if (AppContext.model.getValue('sspDcMatch') === true) {
                    await this.sspModeApplicantsOnFCNxt();
                    this.enablePanelById('applicants');
                    this.openPanelByID('applicants');
                }
            } else {
                if (AppContext.model.getValue('appReg.applicantType') === 'P') {
                    await this.createApp();
                    const appNumber = AppContext.model.getValue(
                        'appRegNew.ArApplicationForAid.appNum',
                    );
                    const indvIdSel = AppContext.model.getValue('primaryIndvSel');

                    if (appNumber) {
                        try {
                            let arAppResp = await basicAxiosInterceptor({
                                method: 'post',
                                url: AppContext.config.insertArAppIndv,
                                data: {
                                    appNum: appNumber,
                                    indvId: indvIdSel,
                                    headOfHouseholdSw: 'Y',
                                    employeeSw: 'N',
                                },
                            });
                        } catch (ex) {
                            console.log(ex);
                            return false;
                        }
                        try {
                            let applicantResp = await basicAxiosInterceptor({
                                method: 'POST',
                                url: AppContext.config.fetchApplicantsInfo,
                                data: {
                                    indvId: indvIdSel,
                                    applicantType: 'P',
                                },
                            });

                            let applicantsList = AppContext.model.getValue('applicants');
                            if (undefined !== applicantsList && applicantsList.length > 0) {
                                let resp = applicantResp.data.data;
                                applicantsList.push(resp[0]);
                                AppContext.model.setValue('applicants', applicantsList);
                            } else {
                                AppContext.model.setValue('applicants', applicantResp.data.data);
                            }

                            this.setCheckBoxesInApplicant(AppContext.model.getValue('applicants'));

                            AppContext.model.setValue(
                                'applicantsNames',
                                applicantResp.data.data.firstName,
                            );
                            let fileClearIndvs = AppContext.model.getValue('fileClearIndvs');
                            fileClearIndvs.push(applicantResp.data.data[0].firstName);
                            AppContext.model.setValue('fileClearIndvs', [...fileClearIndvs]);
                            AppContext.model.setValue('pcNxtclick', false);
                            await this.resetFileClearencePanel();
                            return true;
                        } catch (ex) {
                            console.log(ex);
                            return false;
                        }
                    }
                } else {
                    const indvIdSel = AppContext.model.getValue('primaryIndvSel');
                    if (indvIdSel) {
                        try {
                            let applicantResp = await basicAxiosInterceptor({
                                method: 'POST',
                                url: AppContext.config.fetchApplicantsInfo,
                                data: {
                                    indvId: indvIdSel,
                                    applicantType: 'A',
                                },
                            });

                            let applicantsList = AppContext.model.getValue('applicants');
                            if (applicantsList.length > 0) {
                                let resp = applicantResp.data.data;
                                applicantsList.push(resp[0]);
                                AppContext.model.setValue('applicants', applicantsList);
                            } else {
                                AppContext.model.setValue('applicants', applicantResp.data.data);
                            }

                            this.setCheckBoxesInApplicant(AppContext.model.getValue('applicants'));

                            let fileClearAddIndvs = AppContext.model.getValue('fileClearIndvs');
                            fileClearAddIndvs.push(applicantResp.data.data[0].firstName);
                            AppContext.model.setValue('fileClearIndvs', [...fileClearAddIndvs]);
                            AppContext.model.setValue('pcNxtclick', false);
                            await this.resetFileClearencePanel();
                            return true;
                        } catch (ex) {
                            console.log(ex);
                            return false;
                        }
                    }
                    return true;
                }
            }
        } //Service Call on click of Next in File Clearance screen -- End

        //Service Call on click of Next in Applicants screen -- Start
        else if (id === 'applicants') {
            if (AppContext.model.getValue('sspMode') === 'Y') {
                await this.createApp();
            }
            let caseNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.caseNum');
            //New added to see case association has changed or not
            if (caseNum === '0') {
                AppContext.model.setValue('caseAssoc.confirmFlg', true);
            } else {
                AppContext.model.setValue('caseAssoc.confirmFlg', false);
            }
            AppContext.model.setValue('case.id.org', caseNum);
            AppContext.model.setValue('caseAssociate.id', caseNum);

            let appl = AppContext.model.getValue('applicants');

            if (appl && appl.length > 0) {
                const appNumber = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
                appl.forEach((d) => (d.dob = new Date(d.dob).format('{MM}/{dd}/{yyyy}').raw));

                let payload = {
                    appNum: appNumber,
                    applicants: appl,
                };

                try {
                    let arAppResp = await basicAxiosInterceptor({
                        method: 'POST',
                        url: AppContext.config.insertUpdateApplicants,
                        data: payload,
                    });
                    let applicantsResponse = ResponseHandlers.handleResponse({
                        ...arAppResp.data,
                    });
                    if (undefined !== applicantsResponse) {
                        AppContext.model.setValue('applicants', applicantsResponse);
                        appl = AppContext.model.getValue('applicants');
                    }
                } catch (ex) {
                    console.log(ex);
                    return false;
                }

                const items = appl.filter(
                    (item) => undefined !== item && item.includeApplicantSw === 'Yes',
                );
                const applicantItems = items.map((applicant) => {
                    return applicant.firstName;
                });

                AppContext.model.setValue('applicantsNames', applicantItems);
            }

            try {
                const items = appl.filter((item) => undefined !== item);
                let indvId = 0;

                if (undefined !== items) {
                    items.map((tmp) => {
                        if (tmp.primaryApplicantSw === 'Yes') {
                            if (undefined != tmp.indvStatusSw && tmp.indvStatusSw === 'T') {
                                indvId = 0;
                            } else {
                                indvId = tmp.indvId;
                            }
                        }
                    });
                    // return true;
                }

                // let response = await Util.HTTP.get(
                //     AppContext.config.fetchAsscCasesForIndv + indvId,
                // );
                let response = await basicAxiosInterceptor({
                    method: 'POST',
                    url: AppContext.config.fetchAsscCasesForIndv,
                    data: {
                        indvId: indvId,
                        appNum: AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum'),
                    },
                });
                let servResp = ResponseHandlers.handleResponse({ ...response.data });
                AppContext.model.setValue('associatedCase', servResp);
                if (mode === 'Y') {
                    // CommonUtils.disableCaseAssociation();
                    await CommonUtils.selAssoCase(AppContext.model.getValue('caseAssociate.id'));
                }
                if (servResp.length === 0) {
                    this.enablePanelById('case_association');
                    document
                        .getElementById(`case_association_exclamation`)
                        .classList.add('success');
                    AppContext.model.setValue('caseAssociation.totalCaseNum', servResp.length);
                }
            } catch (ex) {
                return false;
            }
            return true;
        }
        //Service Call on click of Next in Applicants screen -- End

        //Save method for Contact Details
        else if (id === 'contact_info') {
            console.log('contact_info Start Time : ' + new Date().toLocaleString());
            if (
                AppContext.model.getValue('appRegNew.ArApplicationForAid.appModeCd') === 'SS' &&
                AppContext.model.getValue('contactConflicts') !== undefined &&
                AppContext.model.getValue('contactConflicts')[0].PANEL_HEADERS
                    .conflicts_resolved === 'NO'
            ) {
                document
                    .getElementById('contact_info_panel')
                    .scrollIntoView({ behavior: 'smooth', block: 'start', inline: 'end' });
                AppContext.model.setValue('contactsConflictAlert', true);
            } else {
                AppContext.model.setValue('contactsConflictAlert', false);

                //Removed the old code for clone and added new code
                try {
                    let arApplicationForAid = {};
                    let arPhoneList = [];
                    let arEmailList = [];
                    let finalArEmailList = [];
                    let arAppAddrPA = {};
                    let arAppAddrMA = {};

                    let appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
                    let caseNum = AppContext.model.getValue('caseAssociate.id');

                    //WeekDay
                    arApplicationForAid = {
                        weekdayContMethSw: AppContext.model.getValue(
                            'contactInfo.arApplicationForAid.preferredContactMethod',
                        ),
                        weekdayContTime: AppContext.model.getValue(
                            'contactInfo.arApplicationForAid.preferredContactTime',
                        ),
                    };

                    //Phone
                    if (
                        !_.isEmpty(AppContext.model.getValue('contactInfo.phoneDetails.PRP.phnNum'))
                    ) {
                        arPhoneList.push({
                            ...AppContext.model.getValue('contactInfo.phoneDetails.PRP'),
                            appNum: AppContext.model.getValue(
                                'appRegNew.ArApplicationForAid.appNum',
                            ),
                            phnTypeCd: 'PRP',
                            phoneSrcTyp: 'PA',
                        });
                    }
                    if (
                        !_.isEmpty(AppContext.model.getValue('contactInfo.phoneDetails.WOP.phnNum'))
                    ) {
                        arPhoneList.push({
                            ...AppContext.model.getValue('contactInfo.phoneDetails.WOP'),
                            appNum: AppContext.model.getValue(
                                'appRegNew.ArApplicationForAid.appNum',
                            ),
                            phnTypeCd: 'WOP',
                            phoneSrcTyp: 'PA',
                        });
                    }
                    if (
                        !_.isEmpty(AppContext.model.getValue('contactInfo.phoneDetails.ALP.phnNum'))
                    ) {
                        arPhoneList.push({
                            ...AppContext.model.getValue('contactInfo.phoneDetails.ALP'),
                            appNum: AppContext.model.getValue(
                                'appRegNew.ArApplicationForAid.appNum',
                            ),
                            phnTypeCd: 'ALP',
                            phoneSrcTyp: 'PA',
                        });
                    }

                    //Email
                    let emailData = AppContext.model.getValue('contactInfo.emailDetails');
                    if (!_.isEmpty(emailData)) {
                        arEmailList = emailData;
                    }
                    finalArEmailList = arEmailList.map((i) => {
                        let arEmailNew = {
                            ...i,
                            emailSrcTyp: 'PA',
                        };
                        return arEmailNew;
                    });

                    //PA Address
                    let resAddrSw = AppContext.model.getValue(
                        'contactInfo.addressDetails.resAddrSw',
                    );
                    let addrPA = AppContext.model.getValue(
                        'contactInfo.addressDetails.addressInfo.RE',
                    );
                    if (!_.isEmpty(addrPA)) {
                        arAppAddrPA = {
                            ...addrPA,
                            addrTypeCd: 'PA',
                            appNum: appNum,
                            resAddrSw: resAddrSw,
                        };
                    }

                    let livingResSw = AppContext.model.getValue(
                        'contactInfo.addressDetails.livingResSw',
                    );

                    //MA Address
                    let addrMA = AppContext.model.getValue(
                        'contactInfo.addressDetails.addressInfo.MA',
                    );
                    if (!_.isEmpty(addrMA)) {
                        arAppAddrMA = {
                            ...addrMA,
                            addrTypeCd: 'MA',
                            appNum: appNum,
                            resAddrSw: resAddrSw,
                        };
                    }

                    let phoneOnTopPanel = AppContext.model.getValue(
                        'contactInfo.phoneDetails.PRP.phnNum',
                    );
                    if (phoneOnTopPanel) {
                        var phoneNum =
                            '(' +
                            phoneOnTopPanel.substr(0, 3) +
                            ') ' +
                            phoneOnTopPanel.substr(3, 3) +
                            '-' +
                            phoneOnTopPanel.substr(6).replace(/[^\d]/g, '');
                        AppContext.model.setValue('phoneNumOnPanel', phoneNum);
                    }

                    let _mData = {
                        arPhoneList: arPhoneList,
                        arEmailList: finalArEmailList,
                        arAppAddrPA: arAppAddrPA,
                        arAppAddrMA: arAppAddrMA,
                        livingResSw: livingResSw,
                    };
                    AppContext.model.setValue('appreg.panal.contact_info', _mData);
                    if (md5(id)) {
                        let response = await basicAxiosInterceptor({
                            method: 'post',
                            url: AppContext.config.createContact,
                            data: {
                                appNum: appNum,
                                caseNum: caseNum ? caseNum : 0,
                                arApplicationForAid: arApplicationForAid,
                                arPhoneList: arPhoneList,
                                arEmailList: finalArEmailList,
                                arAppAddrPA: arAppAddrPA,
                                arAppAddrMA: arAppAddrMA,
                                livingResSw: livingResSw,
                            },
                        });
                        if (!_.isEmpty(response.data.data.arEmailList)) {
                            AppContext.model.setValue(
                                'contactInfo.emailDetails',
                                response.data.data.arEmailList,
                            );
                        }
                    }
                    await this.getAuthRepData();
                    console.log('contact_info End Time : ' + new Date().toLocaleString());
                    return true;
                } catch (ex) {
                    console.log(ex);
                    return false;
                }
            }
        }
        //end of contact_info
        else if (id === 'authorised_representative') {
            console.log('auth_rep start time:' + new Date().toLocaleTimeString());
            if (
                AppContext.model.getValue('appRegNew.ArApplicationForAid.appModeCd') === 'SS' &&
                AppContext.model.getValue('authRepConflicts') !== undefined &&
                AppContext.model.getValue('authRepConflicts')[0].PANEL_HEADERS
                    .conflicts_resolved === 'NO'
            ) {
                document
                    .getElementById('authorised_representative_panel')
                    .scrollIntoView({ behavior: 'smooth', block: 'start', inline: 'end' });
                AppContext.model.setValue('authRepConflictAlert', true);
            } else {
                AppContext.model.setValue('authRepConflictAlert', false);
                if (
                    AppContext.model.getValue('authRep.authRepDetails.firstName') &&
                    AppContext.model.getValue('authRep.authRepDetails.lastName')
                ) {
                    var concatNames =
                        AppContext.model.getValue('authRep.authRepDetails.firstName') +
                        ' ' +
                        AppContext.model.getValue('authRep.authRepDetails.lastName');
                    AppContext.model.setValue('authRepPanelNames', concatNames);
                }

                //If current Auth rep switch is No, return and previously there was Auth rep data, delete AR data
                if (
                    AppContext.model.getValue('authRep.authRepSw') === 'N' &&
                    AppContext.model.getValue('authRep.org.authRepSw') === 'Y'
                ) {
                    try {
                        let appNum = AppContext.model.getValue(
                            'appRegNew.ArApplicationForAid.appNum',
                        );
                        basicAxiosInterceptor({
                            method: 'DELETE',
                            url: AppContext.config.deleteAuthRep,
                            data: {
                                appNum: appNum,
                            },
                        });
                    } catch (ex) {
                        console.log(ex);
                        return false;
                    }
                }
                let saveFlag = false;
                //save AuthRep Details call Start
                if (AppContext.model.getValue('authRep.authRepSw') === 'Y') {
                    try {
                        let arApplicationForAid = {};
                        let arPhoneList = [];
                        let arEmailList = [];
                        let finalArEmailList = [];
                        let arAppAddrAA = {};

                        //Check for changes in ArApplicationForAid
                        arApplicationForAid = {
                            authrepFirstName: AppContext.model.getValue(
                                'authRep.authRepDetails.firstName',
                            ),
                            authrepLastName: AppContext.model.getValue(
                                'authRep.authRepDetails.lastName',
                            ),
                            authrepMidName: AppContext.model.getValue(
                                'authRep.authRepDetails.middleName',
                            ),
                            authrepSufxName: AppContext.model.getValue(
                                'authRep.authRepDetails.suffix',
                            ),
                            relCd: AppContext.model.getValue('authRep.authRepDetails.relCd'),
                            authRepSw: AppContext.model.getValue('authRep.authRepSw'),
                        };
                        //Check for changes in Email
                        let pagemodelEmail = AppContext.model.getValue('authRep.emailDetails');
                        if (!_.isEmpty(pagemodelEmail)) {
                            arEmailList = AppContext.model.getValue('authRep.emailDetails');
                            //Add a new field: emailSrcTyp = 'AA' for New Emails in the Array
                            finalArEmailList = arEmailList.map((arEmail) => {
                                let arEmailNew = {
                                    ...arEmail,
                                    emailSrcTyp: 'AA',
                                };
                                return arEmailNew;
                            });
                        }
                        //Check for changes in Phone
                        let appNum = AppContext.model.getValue(
                            'appRegNew.ArApplicationForAid.appNum',
                        );
                        if (
                            !_.isEmpty(AppContext.model.getValue('authRep.phoneDetails.PRP.phnNum'))
                        ) {
                            arPhoneList.push({
                                ...AppContext.model.getValue('authRep.phoneDetails.PRP'),
                                appNum: appNum,
                                phnTypeCd: 'PRP',
                                phoneSrcTyp: 'AA',
                            });
                        }
                        if (
                            !_.isEmpty(AppContext.model.getValue('authRep.phoneDetails.WOP.phnNum'))
                        ) {
                            arPhoneList.push({
                                ...AppContext.model.getValue('authRep.phoneDetails.WOP'),
                                appNum: appNum,
                                phnTypeCd: 'WOP',
                                phoneSrcTyp: 'AA',
                            });
                        }
                        if (
                            !_.isEmpty(AppContext.model.getValue('authRep.phoneDetails.ALP.phnNum'))
                        ) {
                            arPhoneList.push({
                                ...AppContext.model.getValue('authRep.phoneDetails.ALP'),
                                appNum: appNum,
                                phnTypeCd: 'ALP',
                                phoneSrcTyp: 'AA',
                            });
                        }
                        //Check for changes in Auth Rep Address
                        let pagemodelAddressAA = AppContext.model.getValue('authRep.addrDetails');
                        if (!_.isEmpty(pagemodelAddressAA)) {
                            arAppAddrAA = {
                                ...pagemodelAddressAA,
                                addrTypeCd: 'AA',
                                appNum: appNum,
                            };
                        } // if address present

                        //Send a Post request using Axios
                        let _mData = {
                            arApplicationForAid: arApplicationForAid,
                            arPhoneList: arPhoneList,
                            arEmailList: finalArEmailList,
                            arAppAddrAA: arAppAddrAA,
                        };
                        AppContext.model.setValue('appreg.panal.authorised_representative', _mData);
                        //let start = new Date().toLocaleTimeString();
                        if (md5(id)) {
                            let response = await basicAxiosInterceptor({
                                method: 'POST',
                                url: AppContext.config.createAuthRep,
                                data: {
                                    appNum: appNum,
                                    arApplicationForAid: arApplicationForAid,
                                    arPhoneList: arPhoneList,
                                    arEmailList: finalArEmailList,
                                    arAppAddrAA: arAppAddrAA,
                                },
                            });

                            if (!_.isEmpty(response.data.data.arEmailList)) {
                                AppContext.model.setValue(
                                    'authRep.emailDetails',
                                    response.data.data.arEmailList,
                                );
                            }
                            // if (!_.isEmpty(arContactInfoResp.data.data.arEmailList)) {
                            //     AppContext.model.setValue(
                            //         'authRep.emailDetails',
                            //         arContactInfoResp.data.data.arEmailList,
                            //     );
                            // }
                        }
                        saveFlag = true;
                    } catch (ex) {
                        console.log(ex);
                        return false;
                    }
                } else {
                    saveFlag = true;
                }
                //save AuthRep Details call End

                if (
                    saveFlag === true &&
                    (mode === 'Y' ||
                        AppContext.model.getValue('appRegNew.ArApplicationForAid.appModeCd') ===
                            'SS')
                ) {
                    await this.getProgramSelectionData();
                    //await this.findByAppResponse();
                    let MaintainModeProgramList = AppContext.model.getValue('programString');
                    AppContext.model.setValue('programListSelected', MaintainModeProgramList);
                }
                this.findPrgmIndvsResponse();
                console.log('auth_rep end time:' + new Date().toLocaleTimeString());
                return saveFlag === true ? true : false;
            }
            //end of else loop
            return true;
        }
        //end of auth rep
    }

    async getAuthRepData() {
        try {
            let appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
            let caseNum = AppContext.model.getValue('caseAssociate.id')
                ? AppContext.model.getValue('caseAssociate.id')
                : '0';
            const response = await basicAxiosInterceptor({
                method: 'POST',
                url: AppContext.config.findAuthRepDetailsForAppNum,
                data: {
                    appNum: appNum,
                    caseNum: caseNum,
                },
            });

            AppContext.model.setValue('autRepConflictInput', response.data.data);
            this.setAuthRepModelDetails(response.data.data, appNum);
        } catch (ex) {
            console.log(ex);
        }

        if (
            AppContext.model.getValue('showAuthRepConflicts') !== false &&
            AppContext.model.getValue('autRepConflictInput').source === 'DC' &&
            AppContext.model.getValue('appRegNew.ArApplicationForAid.appModeCd') === 'SS'
        ) {
            try {
                let sspAppNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
                let conflictResponse = await basicAxiosInterceptor({
                    method: 'POST',
                    url: AppContext.config.fetchConflictAuthRep,
                    data: AppContext.model.getValue('autRepConflictInput'),
                });
                if (conflictResponse.data.data.length > 0) {
                    let email = '';
                    conflictResponse.data.data.map((i) => {
                        if (i.PANEL_HEADERS !== undefined) {
                            i.PANEL_HEADERS.topHeading =
                                'Resolve Conflicts - Authorized Representative';
                            i.PANEL_HEADERS.conflicts_resolved = 'NO';
                        }
                        if (i.PANEL_HEADERS.UNIQUE_ID === 'AuthPhoneConflicts') {
                            email = i.SSP_MODEL.EmailAdr;
                        }
                    });
                    if (email !== undefined) {
                        if (
                            typeof AppContext.model.getValue('authRep.emailDetails') ===
                                'undefined' ||
                            AppContext.model.getValue('authRep.emailDetails') === null
                        ) {
                            AppContext.model.setValue('authRep.emailDetails', []);
                        }
                        AppContext.model.setValue('authRep.emailDetails', [
                            ...AppContext.model.getValue('authRep.emailDetails'),
                            {
                                email: email,
                                emailComments: '',
                                appNum: sspAppNum,
                            },
                        ]);
                    }
                    AppContext.model.setValue('authRepConflicts', conflictResponse.data.data);
                    AppContext.model.setValue('showAuthRepConflicts', true);
                    if (conflictResponse.data.data.length > 0) {
                        AppContext.model.setValue('authRep.authRepSw', 'Y');
                    }
                }
            } catch (ex) {
                console.log(ex);
            }
        }
    }

    async getProgramSelectionData() {
        this.findByAppResponse();
        await this.fetchByAppNumCall();
        this.fetchExpResponse();
        this.getAppIndvResponse();
        //this.findPrgmIndvsResponse();
    }

    async fetchByAppNumCall() {
        let start = new Date().toLocaleTimeString();
        try {
            let appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
            let fetchByAppNumresponse = await basicAxiosInterceptor({
                method: 'POST',
                url: AppContext.config.fetchPrgByAppNum,
                data: {
                    appNum: appNum,
                },
            });
            let program = [];
            fetchByAppNumresponse.data.data.map((prog) => {
                if (prog.programCd === 'FS') {
                    program.push('FS');
                } else if (prog.programCd === 'TF') {
                    program.push('TF');
                } else if (prog.programCd === 'MA') {
                    program.push('MA');
                }
            });
            AppContext.model.setValue('pSel.selPrograms', program);
            if (program.includes('MA')) {
                AppContext.model.setValue('nonhealth', false);
                AppContext.model.setValue('health', true);
            } else {
                AppContext.model.setValue('nonhealth', true);
                AppContext.model.setValue('health', false);
            }
            if (program.includes('FS')) {
                AppContext.model.setValue('snap', true);
            }
            AppContext.model.setValue('programArray', program);
            let progFullNameList = program.map((i) => {
                return i === 'FS' ? 'SNAP' : i === 'TF' ? 'TANF' : 'Healthcare';
            });
            var programString = progFullNameList.join(',');
            AppContext.model.setValue('programString', programString);
            //console.log('call--1: START:' + start + ' END:' + new Date().toLocaleTimeString());
            return true;
        } catch (ex) {
            console.log('exception');
            console.log(ex);
            return false;
        }
    }

    async findByAppResponse() {
        let start = new Date().toLocaleTimeString();
        try {
            let appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
            let response = await basicAxiosInterceptor({
                method: 'POST',
                url: AppContext.config.findByAppNum,
                data: {
                    appNum: appNum,
                },
            });
            let prioritystatus = [];
            let status = response.data.data;
            if (status.qtrackSw === 'Y') {
                prioritystatus.push('QTrack');
            }
            if (status.pregnancySw === 'Y') {
                prioritystatus.push('Pregnancy');
            }
            if (status.revMaxSw === 'Y') {
                prioritystatus.push('RevMax');
            }
            if (status.srSnapSw === 'Y') {
                prioritystatus.push('SrSNAP');
            }
            if (status.waiverSw === 'Y') {
                prioritystatus.push('Waiver');
            }
            if (status.nursingHomeSw === 'Y') {
                prioritystatus.push('NursingHome');
            }
            if (status.refugeeSw === 'Y') {
                prioritystatus.push('Refugee');
            }
            AppContext.model.setValue('pSel.disabled', status.abdCheckSw);
            AppContext.model.setValue('pSel.priorityAppStatus', prioritystatus);
            //console.log('call--2: START:' + start + ' END:' + new Date().toLocaleTimeString());
            return true;
        } catch (ex) {
            return false;
        }
    }

    async fetchExpResponse() {
        let start = new Date().toLocaleTimeString();
        try {
            let appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
            let response = await basicAxiosInterceptor({
                method: 'POST',
                url: AppContext.config.fetchExpInfo,
                data: {
                    appNum: appNum,
                },
            });
            //let expense = [];
            let pselExp = {
                paysFor: [],
            };
            response.data.data.map((exp) => {
                if (exp.questCd === 'EG') {
                    pselExp['EG'] = exp.response;
                    //AppContext.model.setValue('pSel.exp.EG', exp.response);
                } else if (exp.questCd === 'EH') {
                    pselExp['EH'] = exp.response;
                    //AppContext.model.setValue('pSel.exp.EH', exp.response);
                } else if (exp.questCd === 'EM') {
                    pselExp['EM'] = exp.response;
                    //AppContext.model.setValue('pSel.exp.EM', exp.response);
                } else if (exp.questCd === 'ER') {
                    pselExp['ER'] = exp.response;
                    //AppContext.model.setValue('pSel.exp.ER', exp.response);
                } else if (exp.questCd === 'HT' && exp.response === 'Y') {
                    pselExp.paysFor.push('HT');
                    //expense.push('HT');
                } else if (exp.questCd === 'NE' && exp.response === 'Y') {
                    pselExp.paysFor.push('NE');
                    //expense.push('NE');
                } else if (exp.questCd === 'WS' && exp.response === 'Y') {
                    pselExp.paysFor.push('WS');
                    //expense.push('WS');
                } else if (exp.questCd === 'TP' && exp.response === 'Y') {
                    pselExp.paysFor.push('TP');
                    //expense.push('TP');
                } else if (exp.questCd === 'GR' && exp.response === 'Y') {
                    pselExp.paysFor.push('GR');
                    //expense.push('GR');
                }
            });
            AppContext.model.setValue('pSel.exp', pselExp);
            //AppContext.model.setValue('pSel.exp.paysFor', expense);
            //console.log('call--3: START:' + start + ' END:' + new Date().toLocaleTimeString());
            return true;
        } catch (ex) {
            return false;
        }
    }

    async getAppIndvResponse() {
        let start = new Date().toLocaleTimeString();
        try {
            let appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
            let appIndvresponse = await basicAxiosInterceptor({
                method: 'POST',
                url: AppContext.config.fetchArAppProgramIndv,
                data: {
                    appNum: appNum,
                },
            });
            let indvProgresp = appIndvresponse.data.data;
            if (indvProgresp.length > 0) {
                let receivedDate = appIndvresponse.data.data[0].requestDt;
                var selectedDate = new Date(receivedDate);
                let setRequestDate =
                    selectedDate.getMonth() +
                    1 +
                    '/' +
                    selectedDate.getDate() +
                    '/' +
                    selectedDate.getFullYear();
                AppContext.model.setValue('psel.ReceivedRequestDate', setRequestDate);
            } else {
                AppContext.model.setValue('psel.ReceivedRequestDate', 'NO');
            }
            let inputretro = [];
            indvProgresp.map((temp) => {
                if (temp.programCd === 'FS') {
                    let snapProg = [];
                    snapProg.push(temp.indvId + temp.programCd);
                    AppContext.model.setValue(temp.indvId + temp.programCd, snapProg);
                }
                if (temp.programCd === 'MA') {
                    let healthProg = [];
                    healthProg.push(temp.indvId + temp.programCd);
                    AppContext.model.setValue(temp.indvId + temp.programCd, healthProg);
                }
                if (temp.programCd === 'TF') {
                    let tanfProg = [];
                    tanfProg.push(temp.indvId + temp.programCd);
                    AppContext.model.setValue(temp.indvId + temp.programCd, tanfProg);
                }
                if (
                    temp.programCd === 'MA' &&
                    temp.priorMedicaidCd.length > 0 &&
                    temp.priorMedicaidCd !== 'P0'
                ) {
                    inputretro.push(temp.indvId + temp.priorMedicaidCd);
                }
                if (temp.requestDt !== undefined && temp.requestDt.length > 0) {
                    AppContext.model.setValue(temp.indvId + 'DATE', temp.requestDt);
                }
            });
            AppContext.model.setValue('retro', inputretro);
            //console.log('call--4: START:' + start + ' END:' + new Date().toLocaleTimeString());
            return true;
        } catch (ex) {
            console.log('exception :' + ex);
            return false;
        }
    }

    async findPrgmIndvsResponse() {
        let start = new Date().toLocaleTimeString();
        try {
            let appNumberForSearch = AppContext.model.getValue(
                'appRegNew.ArApplicationForAid.appNum',
            );
            let indvListForProg = await basicAxiosInterceptor({
                method: 'POST',
                url: AppContext.config.findProgramIndividuals,
                data: {
                    appNum: appNumberForSearch,
                },
            });
            let emptyArray = [];
            if (undefined !== indvListForProg) {
                indvListForProg.data.data.map((temp) => {
                    emptyArray.push({
                        indvId: temp.indvId,
                        applicant: temp.indvName,
                        age: temp.age,
                        coverage: new Date().toLocaleDateString().raw,
                    });
                });
                AppContext.model.setValue('psel.applicant', emptyArray);
                //console.log('call--5: START:' + start + ' END:' + new Date().toLocaleTimeString());
                return true;
            }
        } catch (ex) {
            console.log(ex);
            return false;
        }
    }

    setAuthRepModelDetails = async (responseData, appNum) => {
        //If there are no Auth rep details, default the Switch to N and return
        if (
            !responseData.arApplicationForAid.authRepSw ||
            (responseData.arApplicationForAid.authRepSw &&
                responseData.arApplicationForAid.authRepSw === 'N')
        ) {
            AppContext.model.setValue('authRep.authRepSw', 'N');
            AppContext.model.setValue('authRep.org.authRepSw', 'N');
            return;
        }

        AppContext.model.setValue('authRep.authRepSw', 'Y');
        AppContext.model.setValue('authRep.org.authRepSw', 'Y');

        let authrepIndDetails = {
            firstName: responseData.arApplicationForAid.authrepFirstName
                ? responseData.arApplicationForAid.authrepFirstName
                : '',
            middleName: responseData.arApplicationForAid.authrepMidName
                ? responseData.arApplicationForAid.authrepMidName
                : '',
            lastName: responseData.arApplicationForAid.authrepLastName
                ? responseData.arApplicationForAid.authrepLastName
                : '',
            suffix: responseData.arApplicationForAid.authrepSufxName
                ? responseData.arApplicationForAid.authrepSufxName
                : '',
            relCd: responseData.arApplicationForAid.relCd
                ? responseData.arApplicationForAid.relCd
                : '',
        };

        if (responseData.arApplicationForAid.authrepFirstName) {
            AppContext.model.setValue('authRep.authRepDetails', authrepIndDetails);
        }

        //Populate Phone details
        let phnDetails = {};
        if (!_.isEmpty(responseData.arPhoneList)) {
            AppContext.model.setValue('authRep.phoneDetails.org', responseData.arPhoneList);
            const listPhones = responseData.arPhoneList.map((phn, i) => {
                phn.appNum = appNum;
                if (phn.phnTypeCd === 'PRP') {
                    //AppContext.model.setValue('authRep.phoneDetails.PRP', phn);
                    phnDetails['PRP'] = phn;
                } else if (phn.phnTypeCd === 'WOP') {
                    //AppContext.model.setValue('authRep.phoneDetails.WOP', phn);
                    phnDetails['WOP'] = phn;
                } else if (phn.phnTypeCd === 'ALP') {
                    // AppContext.model.setValue('authRep.phoneDetails.ALP', phn);
                    phnDetails['ALP'] = phn;
                }
            });
            AppContext.model.setValue('authRep.phoneDetails', phnDetails);
        }

        //Populate Email details Array
        if (!_.isEmpty(responseData.arEmailList)) {
            AppContext.model.setValue('authRep.emailDetails', responseData.arEmailList);
        }

        //Populate Address details
        if (!_.isEmpty(responseData.arAppAddrAA)) {
            AppContext.model.setValue('authRep.addrDetails', responseData.arAppAddrAA);
        }
    };

    async createApp() {
        await AppContext.model.setValue('appRegNew.ArApplicationForAid.applicationStatusCd', 'AP');
        await AppContext.model.setValue(
            'appRegNew.ArApplicationForAid.applicationStatusDt',
            AppContext.model.getValue('appRegNew.ArApplicationForAid.dateTimeRegistered'),
        );

        try {
            let arAppResp = await basicAxiosInterceptor({
                method: 'post',
                url: AppContext.config.createApp,
                data: {
                    ...AppContext.model.getValue('appRegNew.ArApplicationForAid'),
                    authRepSw: 'N',
                    appForaidSw: 'Y',
                    officeNum: 10000042, //office is hardocded need to be updated in Future
                },
            });
            AppContext.model.setValue(
                'appRegNew.ArApplicationForAid.appNum',
                arAppResp.data.data.appNum,
            );
            AppContext.model.setValue('ARRAP.RN.appNum', arAppResp.data.data.appNum);
            return true;
        } catch (ex) {
            console.log(ex);
            return false;
        }
    }

    async updApp() {
        try {
            let arAppResp = await basicAxiosInterceptor({
                method: 'post',
                url: AppContext.config.createApp,
                data: {
                    appNum: AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum'),
                    appRecvdDt: AppContext.model.getValue(
                        'appRegNew.ArApplicationForAid.appRecvdDt',
                    ),
                    appModeCd: AppContext.model.getValue('appRegNew.ArApplicationForAid.appModeCd'),
                    appSignedSw: AppContext.model.getValue(
                        'appRegNew.ArApplicationForAid.appSignedSw',
                    ),
                },
            });
            return true;
        } catch (ex) {
            console.log(ex);
            return false;
        }
    }

    async preloadDataInMaintainMode() {
        spinnerUtil.show();
        await this.getProgress();
        await this.loadDataForAppInfoPanel();
        await this.loadDataintoApplicantsPanel();
        spinnerUtil.hide();
    }

    async preloadDataInSSPMode() {
        spinnerUtil.show();
        await this.getSSPProgress();
        await this.loadDataForAppInfoPanelSSP();
        await this.fetchDataForApplicantsPanelSSP();
        spinnerUtil.hide();
    }

    async getProgress() {
        let appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
        let response = await basicAxiosInterceptor({
            method: 'POST',
            url: AppContext.config.getProgress,
            data: {
                appNum: appNum,
            },
        });
        let progress = response.data.data;
        AppContext.model.setValue('appReg.Progress', progress);
    }

    async getSSPProgress() {
        let appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
        // let response = await basicAxiosInterceptor({
        //     method: 'POST',
        //     url: AppContext.config.getSSPProgress + appNum,
        // });
        // let progress = response.data.data;
        AppContext.model.setValue('appReg.Progress', '30');
    }

    async loadDataForAppInfoPanel() {
        let appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
        let response = await basicAxiosInterceptor({
            method: 'POST',
            url: AppContext.config.findByAppNum,
            data: {
                appNum: appNum,
            },
        });
        let arAppData = AppContext.model.getValue('appRegNew.ArApplicationForAid');
        if (response.data.data) {
            arAppData['appSignedSw'] = response.data.data.appSignedSw;
            arAppData['appRecvdDt'] = response.data.data.appRecvdDt.split(' ')[0];
            let dateTimeRegistered = response.data.data.dateTimeRegistered;
            let dateArr = dateTimeRegistered.split(' ');
            arAppData['dateTimeRegistered'] = dateArr[0];
            arAppData['appRegDt'] = dateArr[1];
            arAppData['time'] = dateArr[2];
            arAppData['appModeCd'] = response.data.data.appModeCd;
            arAppData['caseNum'] = response.data.data.caseNum ? response.data.data.caseNum : 0;
            AppContext.model.setValue('appRegNew.ArApplicationForAid', arAppData);
            let appStatus = response.data.data.applicationStatusCd;

            if (appStatus === 'AC' || appStatus === 'FI') {
                AppContext.model.setValue('isAppSubmitted', 'true');
                AppContext.model.setValue('appReg.readOnly', true);
            }
        }
        if (AppContext.model.getValue('appRegNew.ArApplicationForAid.appModeCd') === 'SS') {
            AppContext.model.setValue('sourceDisable', 'true');
        }
    }

    //SSP APP INFO PANEL START
    async loadDataForAppInfoPanelSSP() {
        let appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
        let response = await basicAxiosInterceptor({
            method: 'POST',
            url: AppContext.config.getAppRqst, //'T00001503',
            data: {
                appNum: appNum,
            },
        });

        let arAppData = AppContext.model.getValue('appRegNew.ArApplicationForAid');
        if (response.data) {
            //AppContext.model.setValue('appRegNew.ArApplicationForAid.appSignedSw', 'Y');
            arAppData['appSignedSw'] = 'Y';

            //let sspReceivedDate = new Date(response.data.appRecvdDt);
            // AppContext.model.setValue(
            //     'appRegNew.ArApplicationForAid.appRecvdDt',
            //     response.data.appRecvdDt,
            // );
            arAppData['appRecvdDt'] = response.data.appRecvdDt;

            // let sspRegdDt =
            //     parseInt(new Date().getMonth() + 1) +
            //     '-' +
            //     new Date().getDate() +
            //     '-' +
            //     new Date().getFullYear();
            let sspRegAMPM = new Date().toLocaleTimeString().slice(-2) == 'AM' ? 'AM' : 'PM';
            let sspRegTime = new Date()
                .toLocaleTimeString()
                .replace(/.M/gm, '')
                .raw.replace(/,/g, '');
            // AppContext.model.setValue(
            //     'appRegNew.ArApplicationForAid.dateTimeRegistered',
            //     new Date().toLocaleString(), // sspRegdDt,
            // );
            //AppContext.model.setValue('appRegNew.ArApplicationForAid.appRegDt', sspRegTime);
            arAppData['appRegDt'] = sspRegTime;
            //AppContext.model.setValue('appRegNew.ArApplicationForAid.time', sspRegAMPM);
            arAppData['time'] = sspRegAMPM;
            // AppContext.model.setValue(
            //     'appRegNew.ArApplicationForAid.appModeCd',
            //     response.data.appMode,
            // );
            arAppData['appModeCd'] = response.data.appMode;

            AppContext.model.setValue('appRegNew.ArApplicationForAid', arAppData);
            let appStatus = response.data.applicationStatusCd;

            if (appStatus === 'AC' || appStatus === 'FI') {
                AppContext.model.setValue('isAppSubmitted', 'true');
                AppContext.model.setValue('appReg.readOnly', true);
            }
        }
        if (AppContext.model.getValue('appRegNew.ArApplicationForAid.appModeCd') === 'SS') {
            AppContext.model.setValue('sourceDisable', 'true');
        }
        return true;
    }
    //SSP APP INFO PANEL END

    async loadDataintoApplicantsPanel() {
        let appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
        try {
            let applicantResp = await basicAxiosInterceptor({
                method: 'POST',
                url: AppContext.config.fetchApplicantsInMaintMode,
                data: {
                    appNum: appNum,
                },
            });

            AppContext.model.setValue('applicants', applicantResp.data.data);

            this.setCheckBoxesInApplicant(AppContext.model.getValue('applicants'));

            const listOfFirstNames = applicantResp.data.data.map((applicant) => {
                return applicant.firstName;
            });
            AppContext.model.setValue('applicantsNames', listOfFirstNames);
            return true;
        } catch (ex) {
            console.log(ex);
            return false;
        }
    }

    //SSP APPLICANTS PANEL START
    async fetchDataForApplicantsPanelSSP() {
        try {
            let appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
            let applicantResp = await basicAxiosInterceptor({
                method: 'POST',
                url: AppContext.config.getOneIndv,
                data: {
                    appNum: appNum,
                },
            });

            if (applicantResp.data) {
                if (!Array.isArray(applicantResp.data)) {
                    applicantResp.data = [applicantResp.data];
                }
                let sspApplicantsFromDb = [...applicantResp.data];
                for (let index = 0; index < sspApplicantsFromDb.length; index++) {
                    let element = sspApplicantsFromDb[index];
                    element.primaryApplicantSw === 'Y'
                        ? (element.primaryApplicantSw = 'Yes')
                        : (element.primaryApplicantSw = 'No');
                }
                AppContext.model.setValue('sspApplicants', sspApplicantsFromDb);
                AppContext.model.setValue('allSSPApplicants', sspApplicantsFromDb);
            }
            return true;
        } catch (e) {
            console.log(e);
            return false;
        }
    }

    async sspModeApplicantsOnFCNxt() {
        let sspApplicants = AppContext.model.getValue('sspApplicants');
        if (sspApplicants.length === 1) {
            PageUtils.closeSSPApplicants();
        }
        await this.fetchSSPConflicts();
        if (AppContext.model.getValue('appReg.applicantType') === 'P') {
            const appNumber = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
            const indvIdSel = AppContext.model.getValue('primaryIndvSel');
            if (appNumber) {
                try {
                    let applicantResp = await basicAxiosInterceptor({
                        method: 'POST',
                        url: AppContext.config.fetchApplicantsInfo,
                        data: {
                            indvId: indvIdSel,
                            applicantType: 'P',
                        },
                    });

                    let applicantsList = AppContext.model.getValue('applicants');
                    if (undefined !== applicantsList && applicantsList.length > 0) {
                        let resp = applicantResp.data.data;
                        applicantsList.push(resp[0]);
                        AppContext.model.setValue('applicants', applicantsList);
                        console.log('Adding Primary applicant');
                    } else {
                        AppContext.model.setValue('applicants', applicantResp.data.data);
                        console.log('Creating applicants array and adding Primary applicant');
                    }

                    this.setCheckBoxesInApplicant(AppContext.model.getValue('applicants'));

                    AppContext.model.setValue('applicantsNames', applicantResp.data.data.firstName);
                    let fileClearIndvs = AppContext.model.getValue('fileClearIndvs');
                    fileClearIndvs.push(applicantResp.data.data[0].firstName);
                    AppContext.model.setValue('fileClearIndvs', [...fileClearIndvs]);
                    AppContext.model.setValue('pcNxtclick', false);
                    await this.resetFileClearencePanel();
                    let dcapplicants = AppContext.model.getValue('applicants');
                    sspApplicants = sspApplicants.filter(
                        ({ ssn: id1 }) => !dcapplicants.some(({ ssn: id2 }) => id2 === id1),
                    );
                    AppContext.model.setValue('sspApplicants', sspApplicants);
                    AppContext.model.setValue('HohFileCleared', true);
                    return true;
                } catch (ex) {
                    console.log(ex);
                    return false;
                }
            }
            return true;
        } else {
            const indvIdSel = AppContext.model.getValue('primaryIndvSel');
            if (indvIdSel) {
                try {
                    let applicantResp = await basicAxiosInterceptor({
                        method: 'POST',
                        url: AppContext.config.fetchApplicantsInfo,
                        data: {
                            indvId: indvIdSel,
                            applicantType: 'A',
                        },
                    });

                    let applicantsList = AppContext.model.getValue('applicants');
                    if (applicantsList.length > 0) {
                        let resp = applicantResp.data.data;
                        applicantsList.push(resp[0]);
                        AppContext.model.setValue('applicants', applicantsList);
                    } else {
                        AppContext.model.setValue('applicants', applicantResp.data.data);
                    }

                    this.setCheckBoxesInApplicant(AppContext.model.getValue('applicants'));

                    let fileClearAddIndvs = AppContext.model.getValue('fileClearIndvs');
                    fileClearAddIndvs.push(applicantResp.data.data[0].firstName);
                    AppContext.model.setValue('fileClearIndvs', [...fileClearAddIndvs]);
                    AppContext.model.setValue('pcNxtclick', false);
                    await this.resetFileClearencePanel();
                    let dcapplicants = AppContext.model.getValue('applicants');
                    let sspApplicants = AppContext.model.getValue('sspApplicants');
                    sspApplicants = sspApplicants.filter(
                        ({ ssn: id1 }) => !dcapplicants.some(({ ssn: id2 }) => id2 === id1),
                    );
                    let itemIndex = AppContext.model.getValue('SSPApplicantsFCIndex');
                    document.getElementById('sspPanel_' + itemIndex).style.display = 'none';
                    if (sspApplicants.length - AppContext.model.getValue('removed') === 0) {
                        document.getElementById('mySidepanelApplicants').style.display = 'none';
                        document.getElementById('sspIndvs').style.display = 'none';
                        AppContext.model.setValue('fileClearStatus', 'Cleared');
                        document
                            .getElementById(`file_clearance_exclamation`)
                            .classList.add('success');
                    } else {
                        AppContext.model.setValue('fileClearStatus', 'Not Cleared');
                    }
                    return true;
                } catch (ex) {
                    console.log(ex);
                    return false;
                }
            }
            return true;
        }
    }

    async fetchSSPConflicts() {
        try {
            let appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
            let conflictsResponse = await basicAxiosInterceptor({
                method: 'POST',
                url: AppContext.config.fetchConflictApplicants,
                data: {
                    indvId: AppContext.model.getValue('primaryIndvSel'),
                    applicantType: AppContext.model.getValue('appReg.applicantType'),
                    appNum: appNum,
                },
            });
            if (conflictsResponse.data.data.length > 0) {
                conflictsResponse.data.data.map((i) => {
                    if (i.PANEL_HEADERS !== undefined) {
                        i.PANEL_HEADERS.topHeading = 'Resolve Conflicts - Applicants';
                        i.PANEL_HEADERS.panelName =
                            i.PANEL_HEADERS.firstName +
                            ' ' +
                            i.PANEL_HEADERS.lastName +
                            ' ' +
                            '-' +
                            ' ' +
                            'Age' +
                            ' ' +
                            i.PANEL_HEADERS.age;
                        i.PANEL_HEADERS.conflicts_resolved = 'NO';
                    }
                });
                AppContext.model.setValue('conflictApplicants', conflictsResponse.data.data);
                AppContext.model.setValue('showApplicantsConflicts', true);
                return true;
            }
        } catch (e) {
            console.log(e);
            return false;
        }
    }

    //SSP APPLICANTS PANEL END

    //SSP APPLICANTS FILE CLEARANCE END

    //DisbaleFileClearPanelForSSP
    async disableFileClearPanel() {
        AppContext.model.setValue('disableFrSSP', 'true');
    }

    //EnableFileClearPanelForSSP
    async enableFileClearPanel() {
        AppContext.model.setValue('disableFrSSP', 'false');
    }

    async resetFileClearencePanel() {
        AppContext.model.setValue('appReg.applicantType', 'A');
        AppContext.model.setValue('ar.pc.search', '');
        AppContext.model.setValue('ar.pc.search.collection.above85', []);
    }

    async submitApplication() {
        spinnerUtil.show();
        let appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
        try {
            let submitAppResp = await basicAxiosInterceptor({
                method: 'PUT',
                url: AppContext.config.submitApp,
                data: {
                    appNum: appNum,
                },
            });
        } catch (ex) {
            AppContext.model.setValue('isAppSubmitted', 'false');
            console.log(ex);
        }
        AppContext.model.setValue('isAppSubmitted', 'true');
        spinnerUtil.hide();
        AppContext.notification.success('Application Submitted successfully');
        AppContext.model.setValue('appReg.Progress', '100');
        AppContext.model.setValue('appReg.readOnly', true);
        window.scrollTo(0, 0);
    }

    setCheckBoxesInApplicant(applicantResp) {
        //primary and include applicant switch START
        let primaryCheckArray = [];
        let includeCheckArray = [];
        for (var i = 0; i < applicantResp.length; i++) {
            var applicant = AppContext.model.getValue('applicants[' + i + ']');
            if (applicant.primaryApplicantSw === 'Yes') {
                primaryCheckArray.push('primaryApplicantCheckbox_' + i);
                includeCheckArray.push('includeApplicantCheckbox_' + i);
            }
            if (applicant.includeApplicantSw === 'Yes' && applicant.primaryApplicantSw === 'No') {
                includeCheckArray.push('includeApplicantCheckbox_' + i);
            }
        }
        AppContext.model.setValue('primary_Applicant_Checkbox', primaryCheckArray);
        AppContext.model.setValue('include_Applicant_Checkbox', includeCheckArray);
        //primary and include applicant switch END
    }
}

export default ApplicationRegistration;
