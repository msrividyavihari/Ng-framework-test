import Template from './ViewPending.rt';
import { PageConfig, UXPage, Util, AppContext, Navigate } from '@d-lift/core';
import {} from '@/Validations/customValidations.js';
import errorMessages from '../../../../public/validation/commonValidation.json';
import ViewPendingService from '@/Services/Correspondence/ViewPendingService';
import React from 'react';
import { UX } from '@d-lift/uxcomponents';
import './ViewPending.css';
import spinnerUtil from '@/Utills/SpinnerUtil/SpinnerUtill';
import NoticeRequestService from '@/Services/Correspondence/NoticeRequestService';
import AdditionalInfoService from '@/Services/Correspondence/AdditionalInfoService';

@PageConfig({
    ContextRoot: 'Correspondence',
    PageName: 'ViewPending',
    Path: ['/ViewPending', '/COVPC'],
    Template: Template,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Correspondence View Pending',
})
class ViewPending extends UXPage {
    initializeModel() {
        let viewPendingData = {
            viewPendingData: {
                errorField: [],
                isError: false,
                searchCriteria: 'C',
                appNumber: '',
                caseNumber: '',
                clientId: '',
                workerName: '',
                workerId: '',
                ccCertStartDate: '',
                ccCertEndDate: '',
                ccProviderId: '',
                source: '',
                freeformattext: '',
                datatableCollection: [],
            },
        };

        return viewPendingData;
    }
    onPageLoad() {
        AppContext.model.setValue('viewPendingData.selected', '');
        AppContext.notification.clearAll();
    }
    validateSearchCriteria = (viewPendingData) => {
        switch (viewPendingData.searchCriteria) {
            case 'C':
                return viewPendingData.caseNumber;
            case 'A':
                return viewPendingData.appNumber;
            case 'I':
                return viewPendingData.clientId;
            case 'E':
                return viewPendingData.workerName;
            case 'W':
                return viewPendingData.workerId;
            default:
                return false;
        }
    };

    getSearchCriteriaError = (criteriaVal) => {
        switch (criteriaVal) {
            case 'C':
                return errorMessages.CO001;
            case 'A':
                return errorMessages.CO002;
            case 'I':
                return errorMessages.CO003;
            case 'E':
                return errorMessages.CO004;
            case 'W':
                return errorMessages.CO005;
            default:
                return errorMessages.CO001;
        }
    };

    selectRow = (rowData, isSelected, rowIndex, event) => {
        AppContext.model.setValue('viewPendingData.selected', '');
        isSelected
            ? AppContext.model.setValue('viewPendingData.selected', { ...rowData })
            : AppContext.model.setValue('viewPendingData.selected');
        let noticeRequestData = AppContext.model.getValue('viewPendingData.selected');
        this.callNoticeRequestService(noticeRequestData);
    };

    callNoticeRequestService = async (noticeRequestData) => {
        spinnerUtil.show();
        if(noticeRequestData.coReqSeq !== undefined) {
            noticeRequestData.noticeId = noticeRequestData.coReqSeq;
        }
        await NoticeRequestService.logRequestService(noticeRequestData).then((response) => {
            if (!response.success) {
                AppContext.notification.clearAll();
                spinnerUtil.hide();
                window.scrollTo(0, 0);
            } else {
                AppContext.model.setValue(
                    'noticeRequestData.dataCollection',
                    response.foundRecords,
                );
                spinnerUtil.hide();
                AppContext.notification.clearAll();
            }
        });
    };

    suppress = () => {
        AppContext.model.setValue('noticeRequestData.dataCollection[0].status', 'SU');
        let data = AppContext.model.getValue('noticeRequestData.dataCollection[0]');
        this.callNoticeStatusService(data);
    };

    unsuppress = () => {
        AppContext.model.setValue('noticeRequestData.dataCollection[0].status', 'PP');
        let data = AppContext.model.getValue('noticeRequestData.dataCollection[0]');
        this.callNoticeStatusService(data);
    };

    hold = () => {
        AppContext.model.setValue('noticeRequestData.dataCollection[0].status', 'HD');
        let data = AppContext.model.getValue('noticeRequestData.dataCollection[0]');
        this.callNoticeStatusService(data);
    };

    release = () => {
        AppContext.model.setValue('noticeRequestData.dataCollection[0].status', 'PP');
        let data = AppContext.model.getValue('noticeRequestData.dataCollection[0]');
        this.callNoticeStatusService(data);
    };

    callNoticeStatusService = async (noticeRequestData) => {
        spinnerUtil.show();
        if (noticeRequestData.status === 'SU') {
            let requestJson = AppContext.model.getValue(
                'noticeRequestData.dataCollection[0].requestJson',
            );            
            let req = JSON.parse(requestJson);
            req.Customer.NoticeRequest.metaData.suppressed = 'Y';
            let updatedJson = JSON.stringify(req);
            AppContext.model.setValue(
                'noticeRequestData.dataCollection[0].requestJson',
                updatedJson,
            );
        }
        let noticeData = AppContext.model.getValue('noticeRequestData.dataCollection[0]');
        await NoticeRequestService.noticeStatusUpdateService(noticeData).then((response) => {
            spinnerUtil.hide();
            AppContext.notification.clearAll();
        });
    };

    showTable = () => {
        let validatedDom = AppContext.pagedetails.getPageContext().validate();
        let viewPendingData = AppContext.model.getValue('viewPendingData');
        let validSearchCriteria = this.validateSearchCriteria(viewPendingData);
        console.log(validSearchCriteria);
        if (validSearchCriteria) {
            let errorField = [];
            errorField = viewPendingData.errorField.filter(
                (err) =>
                    err !== errorMessages.CO001 &&
                    err !== errorMessages.CO002 &&
                    err !== errorMessages.CO003 &&
                    err !== errorMessages.CO004 &&
                    err !== errorMessages.CO005 &&
                    err !== errorMessages.GL500,
            );
            viewPendingData.errorField = errorField;
            viewPendingData.isError = false;
            if (errorField.length !== 0) {
                viewPendingData.isError = true;
            }
            AppContext.notification.error(viewPendingData.errorField);
        } else {
            let errorMsg = this.getSearchCriteriaError(viewPendingData.searchCriteria);
            if (!viewPendingData.errorField.some((err) => err === errorMsg)) {
                viewPendingData.errorField = [];
                viewPendingData.errorField.push(errorMsg);
            }
            viewPendingData.isError = true;
            AppContext.notification.error(viewPendingData.errorField);
        }
        if (!validatedDom.isError && !viewPendingData.isError) {
            //AppContext.model.setValue('viewPendingData.show', true);
            this.callViewPendingService(viewPendingData);
            AppContext.notification.clearAll();
        } else {
            window.scrollTo(0, 0);
        }
    };

    callViewPendingService = async (viewPendingData) => {
        spinnerUtil.show();
        await ViewPendingService.viewPendingService(viewPendingData).then((response) => {
            if (!response.success) {
                AppContext.notification.clearAll();
                if (!viewPendingData.errorField.some((err) => err === errorMessages.GL500)) {
                    viewPendingData.errorField.push(errorMessages.GL500);
                }
                spinnerUtil.hide();
                AppContext.notification.error(viewPendingData.errorField);
                AppContext.model.setValue('viewPendingData.show', false);
                window.scrollTo(0, 0);
            } else {
                spinnerUtil.hide();
                AppContext.notification.clearAll();
                this.formatStatusText(response.foundRecords);
                AppContext.model.setValue(
                    'viewPendingData.datatableCollection',
                    response.foundRecords,
                );
                AppContext.model.setValue('viewPendingData.show', true);
                window.scrollTo(0, 0);
                AppContext.notification.success(
                    response.foundRecords.length + ' Pending Records Fetched',
                );
            }
        });
    };

    formatStatusText=(viewPendingRecords)=>{
        viewPendingRecords.forEach((element)=>{
            if(element!=null){
                console.log(element.status);
                element.coStatusSw=element.status;
            }
        });
    };

    resetForm = () => {
        AppContext.model.setValue('viewPendingData.caseNumber','');
    };

    NavToPendingDetail = (coReqSeq) => {
        let viewPendingData = AppContext.model.getValue('viewPendingData.datatableCollection');
        let viewPendingDetail = viewPendingData.find((element) => {
            return element.coReqSeq === coReqSeq;
        });
        Navigate.to('/Correspondence/COVPD', {
            viewPendingDetail: viewPendingDetail,
        });
    };

    docTitleLink = (columnData, rowData) => {
        let docName = rowData.docName;
        return (
            <UX
                click={(event) => this.editRow(rowData)}
                target="_self"
                type="hyperLink"
                className="hyperLink">
                {docName}
            </UX>
        );
    };

    editRow = (rowData) => {
        Navigate.to('/Correspondence/COVPD', {
            viewPendingDetail: rowData,
        });
    };

    formatLangCd = (columnData, rowData) => {
        //TODO : Please check why the RT Table is not coming
        let langDesc = Util.getRefTblFldDescription('LANGUAGE', 'LEGACYCD', rowData.languageCd);
        if (!langDesc) {
            langDesc = 'English';
        }
        console.log('Language:' + langDesc);
        return langDesc;
    };
}

export default ViewPending;
