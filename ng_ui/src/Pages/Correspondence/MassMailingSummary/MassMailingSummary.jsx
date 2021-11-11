import Template from './MassMailingSummary.rt';
import { PageConfig, UXPage, AppContext, Navigate } from '@d-lift/core';
import {} from '@/Validations/customValidations.js';
import React from 'react';
import { UX } from '@d-lift/uxcomponents';
import spinnerUtil from '@/Utills/SpinnerUtil/SpinnerUtill';
import errorMessages from '../../../../public/validation/commonValidation.json';
import MassMailingService from '@/Services/Correspondence/MassMailingService';

@PageConfig({
    ContextRoot: 'Correspondence',
    PageName: 'MassMailingSummary',
    Path: ['/MassMailingSummary', '/COMSO'],
    Template: Template,
    ReferenceTable: true,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Mass Mailing Summary',
})
class MassMailingSummary extends UXPage {

    onPageLoad() {
        let massSummaryData;
        this.callMassMailingSummaryService(massSummaryData);
    };

    callMassMailingSummaryService = async massSummaryData => {
        spinnerUtil.show();
        await MassMailingService.massMailingSummaryService(massSummaryData).then(response => {
            if(!response.success){
                spinnerUtil.hide();
                AppContext.notification.error(errorMessages.GL500);
                window.scrollTo(0, 0);
            }else{
                if (response.foundRecords.length>0) {
                    spinnerUtil.hide();
                    AppContext.model.setValue('massSummaryData.dataCollection', response.foundRecords);
                    let responseData = AppContext.model.getValue('massSummaryData.dataCollection');
                    console.log(responseData);
                    for(let i=0;i<responseData.length;i++){
                        responseData[i].edit = "Edit";
                        responseData[i].delete= "Delete";
                    }
                    AppContext.model.setValue('massSummaryData.dataCollection', responseData);
                    AppContext.model.setValue('massSummaryData.show', true);
                    AppContext.notification.clearAll();
                }else{
                    spinnerUtil.hide();
                    AppContext.model.setValue('massSummaryData.show', false);
                }
            } 
        });
    };

    deleteMassLink = (columnData, rowData) => {
        return (
            <UX
                click={event => this.callDeleteMassMailingService(rowData)}
                target="_self"
                type="hyperLink"
                className="hyperLink">
                Delete
            </UX>
        );
    };

    editLink = (columnData, rowData) =>{
        return (
            <UX
                click={event => this.navToEditMassRequest(rowData)}
                target="_self"
                type="hyperLink"
                className="hyperLink">
                Edit
            </UX>
        );
    };
    
    callDeleteMassMailingService = async massMailingData => {
        spinnerUtil.show();
        await MassMailingService.deleteMassMailingService(massMailingData).then(response => {
            if (!response.success) {
                if (!massMailingData.errorField.some(err => err === errorMessages.GL500)) {
                    massMailingData.errorField.push(errorMessages.GL500);
                }
                spinnerUtil.hide();
                AppContext.notification.error(massMailingData.errorField);
                window.scrollTo(0, 0);
            }else {
                this.onPageLoad();
                spinnerUtil.hide();
                AppContext.notification.success(response.foundRecords.messageCodes);
                window.scrollTo(0, 0);
            }
        });  
    };

    massIdLink = (columnData, rowData) => {
        let massId = rowData.massMailingSeqNum;
        return (
            <UX
                click={event => this.navToEditMassRequest(rowData)}
                target="_self"
                type="hyperLink"
                className="hyperLink">
                {massId}
            </UX>
        );
    };

    navToEditMassRequest = (rowData) => {
        Navigate.to('/Correspondence/COMRO', {
            massMailingData : rowData
        })
    };

    createNewRequest = () => {
        Navigate.to('/Correspondence/COMRO',{
            massMailingData : '',
            recptPop : 'DCH'
        });
    };
}

export default MassMailingSummary;
