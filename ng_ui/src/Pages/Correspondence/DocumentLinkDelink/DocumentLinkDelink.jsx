import Template from './DocumentLinkDelink.rt';
import { PageConfig, UXPage, AppContext, Navigate, Util } from '@d-lift/core';
import {} from '@/Validations/customValidations.js';
import spinnerUtil from '@/Utills/SpinnerUtil/SpinnerUtill';
import React from 'react';
import { UX } from '@d-lift/uxcomponents';
import SearchDocumentsService from '@/Services/Correspondence/SearchDocumentsService';

@PageConfig({
    ContextRoot: 'Correspondence',
    PageName: 'DocumentLinkDelink',
    Path: ['/DocumentLinkDelink', '/DMVLD'],
    Template: Template,
    ReferenceTable: true,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Document Link Delink',
})
class DocumentLinkDelink extends UXPage {
    onPageLoad(){
        if (this.props.location.state) {
            AppContext.model.setValue('linkDelinkData', this.props.location.state.linkDelinkData);
            AppContext.model.setValue('linkDelinkData.parentPageID', "DMVLD");
            AppContext.model.setValue('linkDelinkData.pageId', "DMVED");
            AppContext.model.setValue('linkDelinkData.actionType', "LinkDelink");
            let linkDelinkData = AppContext.model.getValue('linkDelinkData');
            this.callLinkDelinkService(linkDelinkData);
        }
    };
  
    initializeModel() {
        let linkDelinkData = {
            linkDelinkData: {
                caseAppNum:'',
                caseNo:'',
                appNo:'',
                clientId : '',
                errorField: []
            }
        };
        return linkDelinkData;
    }

    callRetrieveService = async linkDelinkData => {
        spinnerUtil.show();
        await SearchDocumentsService.retrieveService(linkDelinkData).then(response => {
            if (!response.success) {
                spinnerUtil.hide();
            }else {
                    AppContext.model.setValue(
                        'linkDelinkData.clients',
                        response.foundRecords
                    );
                    spinnerUtil.hide();
                    AppContext.notification.clearAll(); 
            }
        });    
    };

    callLinkDelinkService = async linkDelinkData => {
        spinnerUtil.show();
        await SearchDocumentsService.linkDelinkService(linkDelinkData).then(response => {
            if (!response.success) {
                spinnerUtil.hide();
                AppContext.model.setValue('linkDelinkData.show', false);
                window.scrollTo(0, 0);
            } else {
                if(response.foundRecords===undefined && response.message.length===1){
                    AppContext.notification.success(response.message); 
                    spinnerUtil.hide();
                    Navigate.to('/Correspondence/COVSD');
                }else if(response.message===undefined && response.foundRecords.length>0){
                    AppContext.model.setValue(
                        'linkDelinkData.dataCollection',
                        response.foundRecords
                    );
                    let responseData = AppContext.model.getValue('linkDelinkData.dataCollection');
                    console.log(responseData);
                    for(let i=0;i<responseData.length;i++){
                        responseData[i].delink = "Delink";
                    }
                    AppContext.model.setValue('linkDelinkData.dataCollection', responseData);
                    spinnerUtil.hide();
                    AppContext.model.setValue('linkDelinkData.show', true);
                    AppContext.notification.clearAll(); 
                }else{
                    spinnerUtil.hide();
                    window.scrollTo(0, 0);
                    AppContext.model.setValue('linkDelinkData.show', false);
                }
            }
        });    
    };

    link = () => {
        AppContext.model.setValue('linkDelinkData.actionType', "Link");
        AppContext.model.setValue('linkDelinkData.pageId', "DMVLD");
        let linkDelinkData = AppContext.model.getValue('linkDelinkData');
        this.callLinkDelinkService(linkDelinkData);
    };

    delinkDocument = (columnData, rowData) => {
        return (
            <UX
               click={event => this.editRow(rowData)}
                target="_self"
                type="hyperLink"
                className="hyperLink">
                Delink
            </UX>
        );
    };

    editRow = (rowData) => {
        if(window.confirm('You are about to delink the current case number and client ID from this document. Are you sure you would like to proceed?')){
           AppContext.model.setValue('linkDelinkData.actionType', "Delink");
           AppContext.model.setValue('linkDelinkData.pageId', "DMVLD");
           let linkDelinkData = AppContext.model.getValue('linkDelinkData');
           this.callLinkDelinkService(linkDelinkData);
        }
    };

    loadDropDownList = () => {
        let caseNo = document.getElementById('case_id').value;
        if(caseNo.length===9){
            AppContext.model.setValue('linkDelinkData.caseNo', caseNo);
            let data = AppContext.model.getValue('linkDelinkData');
            this.callRetrieveService(data);
        }
        let appNo = document.getElementById('app_id').value;
        if(appNo.length===9){
            AppContext.model.setValue('linkDelinkData.appNo', appNo);
            let data = AppContext.model.getValue('linkDelinkData');
            this.callRetrieveService(data);
        }
    };

}

export default DocumentLinkDelink;