import React from 'react';
import { BaseComponent } from '@d-lift/uxcomponents';
import { UX } from '@d-lift/uxcomponents';
import { AppContext, Util } from '@d-lift/core';
import Template from './CaseAssociation.rt';
import { CommonUtils } from '../../../Utills/Common/CommonUtils';
import basicAxiosInterceptor from '@/HttpInterceptors/BasicAxiosInterCeptor';
import _ from 'lodash';
import spinnerUtil from '@/Utills/SpinnerUtil/SpinnerUtill';
import './CaseAssociation.css';
import PageUtils from '@/ScreenFlowUtils/PageUtills';

class CaseAssociation extends BaseComponent {
    uxComponentDidMount = () => {
        // AppContext.model.setValue('associatedCase', [
        //     {
        //         lastUpdated: '11/1/20',
        //         hoh: 'Tim John',
        //         programs: 'SNAP, TANF',
        //         status: 'Active',
        //         caseNum: '9090862520',
        //         office: 'LA',
        //     },
        //     {
        //         lastUpdated: '1/2/20',
        //         hoh: 'Tim John',
        //         programs: 'SNAP, TANF',
        //         status: 'Active',
        //         caseNum: '9090862521',
        //         office: 'LA',
        //     },
        //     {
        //         lastUpdated: '3/10/19',
        //         hoh: 'Tim John',
        //         programs: 'SNAP, TANF',
        //         status: 'Active',
        //         caseNum: '9090862522',
        //         office: 'LA',
        //     },
        //     {
        //         lastUpdated: '11/1/20',
        //         hoh: 'Tim John',
        //         programs: 'SNAP, TANF',
        //         status: 'Active',
        //         caseNum: '9090862523',
        //         office: 'LA',
        //     },
        //     {
        //         lastUpdated: '11/1/20',
        //         hoh: 'Tim John',
        //         programs: 'SNAP, TANF',
        //         status: 'Active',
        //         caseNum: '9090862524',
        //         office: 'LA',
        //     },
        //     {
        //         lastUpdated: '11/1/20',
        //         hoh: 'Tim John',
        //         programs: 'SNAP, TANF',
        //         status: 'Active',
        //         caseNum: '9090862525',
        //         office: 'LA',
        //     },
        //     {
        //         lastUpdated: '11/1/20',
        //         hoh: 'Tim John',
        //         programs: 'SNAP, TANF',
        //         status: 'Active',
        //         caseNum: '9090862526',
        //         office: 'LA',
        //     },
        // ]);
        // console.log('Inside case ass');
        // console.log(AppContext.model.getValue('associateCaseList'));
        // AppContext.model.setValue('associatedCase', AppContext.model.getValue('assCaseList'));
    };

    associate = (columnData, rowData) => {
        let userCounty = '';
        if (Util.getSessionData('userCounty')) {
            userCounty = Util.getSessionData('userCounty');
        }
        if (
            rowData.status !== 'PE' &&
            (userCounty === '' || userCounty === rowData.office || userCounty === 'null')
        ) {
            return (
                <span className="selBtnSpan">
                    <UX
                        type="button"
                        mode="primary"
                        className="selbtn1"
                        size="small"
                        name={rowData.caseNum + '_B'}
                        id={rowData.caseNum + '_B'}
                        click={event => this.selAssoCase(rowData.caseNum)}>
                        Associate
                    </UX>
                    <UX
                        type="button"
                        mode="primary"
                        className="selbtn2"
                        size="small"
                        name={rowData.caseNum + '_G'}
                        id={rowData.caseNum + '_G'}
                        click={event => this.deselAssoCase(rowData.caseNum)}>
                        <span>&#10003; &nbsp;</span> Associated
                    </UX>
                </span>
            );
        } else {
            return (
                <span>
                    <UX type="button" mode="primary" disabled size="small">
                        Associate
                    </UX>
                </span>
            );
        }
    };

    formatTime = (columnData, rowData) => {
        var dt = new Date(rowData.lastUpdated.toString());
        var dtr =
            dt.getMonth() +
            1 +
            '/' +
            dt.getDate() +
            '/' +
            dt
                .getFullYear()
                .toString()
                .match(/\d{2}$/);
        return dtr;
    };

    formatProg = (columnData, rowData) => {
        var progArr = rowData.programs ? rowData.programs.split(',') : [];

        var progArrFinal = '';
        progArr.forEach(function(item, index) {
            progArrFinal =
                progArrFinal +
                (progArrFinal === '' ? '' : ',') +
                Util.getRefTableDescriptionByCode('PROGRAM', item);
        });
        return progArrFinal;
    };

    formatOffice = (columnData, rowData) => {
        return Util.getRefTableDescriptionByCode('COUNTY', rowData.office);
    };

    statusdesc = (columnData, rowData) => {
        return Util.getRefTableDescriptionByCode('EDEDGSTATUS', rowData.status);
    };

    selAssoCase = id => {
        if (!document.getElementById(id + '_B').disabled) {
            AppContext.model.setValue('caseAssociate.id', id);
            AppContext.model.setValue('caseAssociate.selected', true);

            document.getElementsByName(id + '_B')[0].style.display = 'none';
            document.getElementsByName(id + '_G')[0].style.display = 'block';
            document.getElementById(
                id + '_B',
            ).parentElement.parentElement.parentElement.style.borderStyle = 'solid';
            document.getElementById(
                id + '_B',
            ).parentElement.parentElement.parentElement.style.borderColor = 'rgb(20, 241, 187)';
            let x = document.querySelectorAll('.selbtn2');
            let i;
            for (i = 0; i < x.length; i++) {
                let name = x[i].name.slice(0, -2);
                if (name !== id.toString()) this.deselAssoCase(name);
            }
        }
    };

    deselAssoCase = id => {
        if (!document.getElementById(id + '_G').disabled) {
            if (AppContext.model.getValue('caseAssociate.id').toString() === id.toString()) {
                AppContext.model.setValue('caseAssociate.selected', false);
                AppContext.model.setValue('caseAssociate.id', '0');
            }
            if (null !== document.getElementById(id + '_B')) {
                document.getElementById(
                    id + '_B',
                ).parentElement.parentElement.parentElement.style.borderStyle = 'none';
            }

            /*  document.getElementById(
            id + '_B',
        ).parentElement.parentElement.parentElement.style.borderColor = 'rgb(20, 241, 187)'; */
            document.getElementsByName(id + '_G')[0].style.display = 'none';
            document.getElementsByName(id + '_B')[0].style.display = 'block';
        }
    };

    fetchContactConflicts = async appNum => {
        try {
            let payload = AppContext.model.getValue('contactConflictsInput');
            let response = await basicAxiosInterceptor({
                method: 'POST',
                url: AppContext.config.fetchConflictContacts,
                data: payload,
            });

            if (response.data.data.length > 0) {
                let email = '';
                response.data.data.map(i => {
                    if (i.PANEL_HEADERS !== undefined) {
                        i.PANEL_HEADERS.topHeading = 'Resolve Conflicts - Contact Info';
                        i.PANEL_HEADERS.conflicts_resolved = 'NO';
                    }
                    if (i.PANEL_HEADERS.UNIQUE_ID === 'AddressDetails') {
                        email = i.SSP_MODEL.EmailAdr;
                    }
                });
                if (email !== undefined) {
                    if (
                        typeof AppContext.model.getValue('contactInfo.emailDetails') ===
                            'undefined' ||
                        AppContext.model.getValue('contactInfo.emailDetails') === null
                    ) {
                        AppContext.model.setValue('contactInfo.emailDetails', []);
                    }
                    AppContext.model.setValue('contactInfo.emailDetails', [
                        ...AppContext.model.getValue('contactInfo.emailDetails'),
                        {
                            email: email,
                            emailComments: '',
                            appNum: appNum,
                        },
                    ]);
                }
                AppContext.model.setValue('contactConflicts', response.data.data);
                AppContext.model.setValue('showContactConflicts', true);
            }
            return true;
        } catch (ex) {
            console.log(ex);
        }
    };

    clearModel = () => {
        AppContext.model.setValue('contactInfo', null);
        AppContext.model.setValue('contactInfo.addressDetails.addressInfo.RE.addrFormatCd', 'US');
        AppContext.model.setValue('contactInfo.addressDetails.addressInfo.MA.addrFormatCd', 'US');
        AppContext.model.setValue('authRep',null);
    };

    onOpenModal = async () => {
        try {
            //Compare old associated case_num on AR and new case_num.
            //0 or undefined
            spinnerUtil.show();
            if (
                AppContext.model.getValue('caseAssociate.id') !==
                AppContext.model.getValue('case.id.org')
            ) {
                let appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
                await basicAxiosInterceptor({
                    method: 'PUT',
                    url: AppContext.config.deassociateCase,
                    data: {
                        appNum: appNum,
                    },
                });
                this.clearModel();
            }

            if (!AppContext.model.getValue('caseAssociate.selected')) {
                if (AppContext.model.getValue('caseAssoc.confirmFlg') === true) {
                    await this.confirm();
                } else {
                    spinnerUtil.hide();
                    AppContext.model.setValue('caseAssociate.popup', true);
                }
            } else {
                spinnerUtil.show();
                AppContext.model.setValue('caseAssoc.confirmFlg', false);
                AppContext.model.setValue(
                    'case.id.org',
                    AppContext.model.getValue('caseAssociate.id'),
                );
                PageUtils.associateCase(AppContext.model.getValue('caseAssociate.id'));
                await PageUtils.getContactDetails(
                    AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum'),
                    AppContext.model.getValue('caseAssociate.id'),
                );
                this.openNextPanel();
                AppContext.model.setValue(
                    'associatedCaseToApp',
                    'Case associated – ' + AppContext.model.getValue('caseAssociate.id'),
                );
                if (
                    AppContext.model.getValue('showContactConflicts') !== false &&
                    AppContext.model.getValue('appRegNew.ArApplicationForAid.appModeCd') === 'SS' &&
                    AppContext.model.getValue('contactConflictsInput').source === 'DC'
                ) {
                    this.fetchContactConflicts(
                        AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum'),
                    );
                }
            }
            spinnerUtil.hide();
        } catch (ex) {
            spinnerUtil.hide();
            console.log(ex);
        }
    };

    cancel = () => {
        AppContext.model.setValue('caseAssociate.popup', false);
    };

    confirm = async () => {
        spinnerUtil.show();
        AppContext.model.setValue('caseAssociate.popup', false);
        AppContext.model.setValue('associatedCaseToApp', '');
        AppContext.model.setValue('caseAssoc.confirmFlg', true);
        await PageUtils.associateCase(0);
        await PageUtils.getContactDetails(
            AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum'),
            '0',
        );
        this.openNextPanel();
        spinnerUtil.hide();
    };

    openNextPanel = () => {
        this.props.closePanel('case_association');
        document.getElementById(`case_association_exclamation`).classList.add('success');
        this.props.openPanel('contact_info');
        this.props.enablePanel('contact_info');
        if (
            AppContext.model.getValue('caseAssociate.id') !== undefined &&
            AppContext.model.getValue('caseAssociate.id').length > 1
        ) {
            AppContext.model.setValue(
                'associatedCaseToApp',
                'Case associated – ' + AppContext.model.getValue('caseAssociate.id'),
            );
        } else {
            AppContext.model.setValue('associatedCaseToApp', 'Opted for new Case');
        }
        let progress = AppContext.model.getValue('appReg.Progress');
        if (!progress || progress < 60) AppContext.model.setValue('appReg.Progress', '60');
    };

    associateCase = async caseNum => {
        try {
            let appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');

            //await Util.HTTP.post(AppContext.config.associateCase + appNum + '/' + caseNum);
            await basicAxiosInterceptor({
                method: 'POST',
                url: AppContext.config.associateCase,
                data: {
                    appNum: appNum,
                    caseNum: caseNum,
                },
            });
            return true;
        } catch (ex) {
            console.log(ex);
        }
    };

    disableCaseAssc = () => {
        CommonUtils.disableCaseAssociation();
    };

    renderComponent() {
        return Template.bind(this).apply(this);
    }
}

export default CaseAssociation;
