import Template from './SearchDocuments.rt';
import { PageConfig, UXPage, AppContext, Util, Navigate } from '@d-lift/core';
import {} from '@/Validations/customValidations.js';
import errorMessages from '../../../../public/validation/commonValidation.json';
import './SearchDocuments.css';
import React from 'react';
import { UX } from '@d-lift/uxcomponents';
import spinnerUtil from '@/Utills/SpinnerUtil/SpinnerUtill';
import SearchDocumentsService from '@/Services/Correspondence/SearchDocumentsService';

@PageConfig({
    ContextRoot: 'Correspondence',
    PageName: 'SearchDocuments',
    Path: ['/SearchDocuments', '/COVSD'],
    Template: Template,
    ReferenceTable: true,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Correspondence Search Documents',
})
class SearchDocuments extends UXPage {

    onPageLoad(){
        AppContext.notification.clearAll();
    }

    initializeModel() {
        let searchDocumentsData = {
            searchDocumentsData: {
                errorField: [],
                isError: false,
                searchCriteria: 'C',
                caseNumber: '',
                clientId: '',
                refNumber: '',
                transactionNumber: '',
                docType: '',
                startDate: '',
                endDate: '',
                source: '',
                ssnId: '',
                firstName: '',
                lastName: '',
                middleName: '',
                dateOfBirth: '',
                dataCollection: []
            }
        };

        return searchDocumentsData;
    }

    callSearchDocumentsService = async searchDocumentsData => {
        spinnerUtil.show();
        await SearchDocumentsService.searchDocumentsService(searchDocumentsData).then(response => {
            if (!response.success) {
                AppContext.notification.clearAll();
                if (!searchDocumentsData.errorField.some(err => err === errorMessages.GL500)) {
                    searchDocumentsData.errorField.push(errorMessages.GL500);
                }
                spinnerUtil.hide();
                AppContext.notification.error(searchDocumentsData.errorField);
                AppContext.model.setValue('searchDocumentsData.show', false);
                window.scrollTo(0, 0);
            } else {
                if(response.foundRecords.length>0){
                    AppContext.model.setValue(
                        'searchDocumentsData.dataCollection',
                        response.foundRecords
                    );
                    spinnerUtil.hide();
                    let data = AppContext.model.getValue('searchDocumentsData.dataCollection');
                    for(let i=0;i<data.length;i++){   
                        data[i].originalDocumentType = data[i].docType;
                        data[i].docType = Util.getRefTableDescriptionByCode('DISDOCTYPE', data[i].docType);
                    }
                    AppContext.model.setValue('searchDocumentsData.dataCollection',data);
                    let modifyData = AppContext.model.getValue('searchDocumentsData.dataCollection');
                    this.formatClientId(modifyData);
                    AppContext.model.setValue('searchDocumentsData.dataCollection', modifyData);
                    AppContext.model.setValue('searchDocumentsData.show', true);
                    AppContext.notification.clearAll(); 
                }else{
                    spinnerUtil.hide();
                    AppContext.notification.clearAll();
                    AppContext.model.setValue('searchDocumentsData.show', false);
                    window.scrollTo(0, 0);
                    AppContext.notification.error("No records found"); 
                }
            }
        });
    };

    documentLink = (columnData, rowData) => {      
        return (
            <UX
                target="_self"
                type="hyperLink"
                click={event => {this.editRow(rowData)}}
                className="hyperLink">
                View
            </UX>
        );
    };

    editRow = (rowData) => {
        Navigate.to('/Correspondence/DMVED', {
            viewDocumentDetail : rowData
        })
    };

    formatClientId = records => {
        records.forEach(element => {
               element.fullName = element.fullName+" "+element.age+" "+element.gender;
        });
    }

    getSearchCriteriaError = criteriaVal => {
        switch (criteriaVal) {
            case 'C':
                return errorMessages.CO001;
            case 'O':
                return errorMessages.DM023;
            default:
                return errorMessages.CO001;
        }
    };

    validateSearchCriteria = searchDocumentsData => {
        switch (searchDocumentsData.searchCriteria) {
            case 'C':
                return searchDocumentsData.caseNumber;
            case 'O':
                return searchDocumentsData.ssnId;
            default:
                return false;
        }
    };

    showTable = () => {
        let validatedDom = AppContext.pagedetails.getPageContext().validate();
        let searchDocumentsData = AppContext.model.getValue('searchDocumentsData');
        let validSearchCriteria = this.validateSearchCriteria(searchDocumentsData);
        console.log(validSearchCriteria);
        if (validSearchCriteria) {
            let errorField = [];
            errorField = searchDocumentsData.errorField.filter(
                err =>
                    err !== errorMessages.CO001 &&
                    err !== errorMessages.DM023 &&
                    err !== errorMessages.GL500
            );
            searchDocumentsData.errorField = errorField;
            searchDocumentsData.isError = false;
            if (errorField.length !== 0) {
                searchDocumentsData.isError = true;
            }
            AppContext.notification.error(searchDocumentsData.errorField);
        } else {
            let errorMsg = this.getSearchCriteriaError(searchDocumentsData.searchCriteria);
            if (!searchDocumentsData.errorField.some(err => err === errorMsg)) {
                searchDocumentsData.errorField = [];
                searchDocumentsData.errorField.push(errorMsg);
            }
            searchDocumentsData.isError = true;
            AppContext.notification.error(searchDocumentsData.errorField);
        }
        if (!validatedDom.isError && !searchDocumentsData.isError) {
            this.callSearchDocumentsService(searchDocumentsData);
            AppContext.notification.clearAll();
        } else {
            window.scrollTo(0, 0);
        }
    };
}

export default SearchDocuments;
