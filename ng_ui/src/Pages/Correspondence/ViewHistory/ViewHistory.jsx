import Template from './ViewHistory.rt';
import { PageConfig, UXPage, Util, AppContext, Navigate } from '@d-lift/core';
import {} from '@/Validations/customValidations.js';
import errorMessages from '../../../../public/validation/commonValidation.json';
import './ViewHistory.css';
import React from 'react';
import { UX } from '@d-lift/uxcomponents';
import spinnerUtil from '@/Utills/SpinnerUtil/SpinnerUtill';
import ViewHistoryService from '@/Services/Correspondence/ViewHistoryService';

@PageConfig({
    ContextRoot: 'Correspondence',
    PageName: 'ViewHistory',
    Path: ['/ViewHistory', '/COVHC'],
    Template: Template,
    ReferenceTable: true,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'View History',
})
class ViewHistory extends UXPage {
    initializeModel() {
        let viewHistoryData = {
            viewHistoryData: {
                errorField: [],
                isError: false,
                searchCriteria: 'C',
                appNumber: '',
                caseNumber: '',
                clientId: '',
                workerName: '',
                workerId: '',
                benefitMonStartDate: '',
                benefitMonEndDate: '',
                printStartDate:'',
                printEndDate:'',
                ccCertStartDate: '',
                ccCertEndDate: '',
                ccProviderId: '',
                printMode:'',
                docTitle: '',
                dataCollection: []
            }
        };

        return viewHistoryData;
    }

    onPageLoad(){
        AppContext.notification.clearAll();
    }
    
    callViewHistoryService = async viewHistoryData => {
        spinnerUtil.show();
        await ViewHistoryService.viewHistoryService(viewHistoryData).then(response => {
            if (!response.success) {
                AppContext.notification.clearAll();
                if (!viewHistoryData.errorField.some(err => err === errorMessages.GL500)) {
                    viewHistoryData.errorField.push(errorMessages.GL500);
                }
                spinnerUtil.hide();
                AppContext.notification.error(viewHistoryData.errorField);
                AppContext.model.setValue('viewHistoryData.show', false);
                window.scrollTo(0, 0);
            } else {
                if(response.foundRecords.length>0){
                    AppContext.model.setValue(
                        'viewHistoryData.dataCollection',
                        response.foundRecords,
                    );
                    spinnerUtil.hide();
                    AppContext.model.setValue('viewHistoryData.show', true);
                    AppContext.notification.clearAll();
                }else{
                    spinnerUtil.hide();
                    AppContext.notification.clearAll();
                    AppContext.model.setValue('viewHistoryData.show', false);
                    window.scrollTo(0, 0);
                    AppContext.notification.error("No records found"); 
                }
            }
        });
    };

    docTitleLink = (columnData, rowData) => {
        let docName = rowData.docName;
        return (
            <UX
               click={event => this.editRow(rowData)}
                target="_self"
                type="hyperLink"
                className="hyperLink">
                {docName}
            </UX>
        );
    };

    editRow = ( rowData) => {
        Navigate.to('/Correspondence/COVHD', {
            viewHistoryDetail: rowData,
        });
    };

    getSearchCriteriaError = criteriaVal => {
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

    validateSearchCriteria = viewHistoryData => {
        switch (viewHistoryData.searchCriteria) {
            case 'C':
                return viewHistoryData.caseNumber;
            case 'A':
                return viewHistoryData.appNumber;
            case 'I':
                return viewHistoryData.clientId;
            case 'E':
                return viewHistoryData.workerName;
            case 'W':
                return viewHistoryData.workerId;
            default:
                return false;
        }
    };

    showTable = () => {
        let validatedDom = AppContext.pagedetails.getPageContext().validate();
        let viewHistoryData = AppContext.model.getValue('viewHistoryData');
        let validSearchCriteria = this.validateSearchCriteria(viewHistoryData);
        console.log(validSearchCriteria);
        if (validSearchCriteria) {
            let errorField = [];
            errorField = viewHistoryData.errorField.filter(
                err =>
                    err !== errorMessages.CO001 &&
                    err !== errorMessages.CO002 &&
                    err !== errorMessages.CO003 &&
                    err !== errorMessages.CO004 &&
                    err !== errorMessages.CO005 &&
                    err !== errorMessages.GL500
            );
            viewHistoryData.errorField = errorField;
            viewHistoryData.isError = false;
            if (errorField.length !== 0) {
                viewHistoryData.isError = true;
            }
            AppContext.notification.error(viewHistoryData.errorField);
        } else {
            let errorMsg = this.getSearchCriteriaError(viewHistoryData.searchCriteria);
            if (!viewHistoryData.errorField.some(err => err === errorMsg)) {
                viewHistoryData.errorField = [];
                viewHistoryData.errorField.push(errorMsg);
            }
            viewHistoryData.isError = true;
            AppContext.notification.error(viewHistoryData.errorField);
        }
        if (!validatedDom.isError && !viewHistoryData.isError) {
            this.callViewHistoryService(viewHistoryData);
            AppContext.notification.clearAll();
        } else {
            window.scrollTo(0, 0);
        }
    };
}

export default ViewHistory;
