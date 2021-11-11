import Template from './ViewDocumentDetail.rt';
import { PageConfig, UXPage, AppContext, Navigate } from '@d-lift/core';
import React from 'react';
import { UX } from '@d-lift/uxcomponents';
import spinnerUtil from '@/Utills/SpinnerUtil/SpinnerUtill';
import SearchDocumentsService from '@/Services/Correspondence/SearchDocumentsService';
import {} from '@/Validations/customValidations.js';

@PageConfig({
    ContextRoot: 'Correspondence',
    PageName: 'ViewDocumentDetail',
    Path: ['/ViewDocumentDetail', '/DMVED'],
    Template: Template,
    ReferenceTable: true,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'View Document Detail',
})
class ViewDocumentDetail extends UXPage {
    onPageLoad(){
        if (this.props.location.state) {
            AppContext.model.setValue('viewDocumentDetail', this.props.location.state.viewDocumentDetail);
            AppContext.model.setValue('viewDocumentDetail.parentPageID', "DMVED");
        }
    };
  
    goBack = () =>{
        let viewDocumentDetail = AppContext.model.getValue('viewDocumentDetail');
        Navigate.to('/Correspondence/COVSD', {
            viewDocumentDetail: viewDocumentDetail,
         });
    };

    viewDocumentLink = docuedgeDocumentId => {
            return (
                <UX
                    target="_blank"
                    type="hyperLink"
                    click={event => {this.callDocumentStreamService(docuedgeDocumentId)}}
                    className="hyperLink">
                    View document
                </UX>
            );
    };

    callDocumentStreamService = async docuedgeDocumentId => {
        spinnerUtil.show();
        await SearchDocumentsService.getDocumentStreamService(docuedgeDocumentId).then(response => {
            if (response.success) {
                spinnerUtil.hide();
                AppContext.notification.clearAll();
            }
            spinnerUtil.hide();
        });
    };

    metadataChange = () => {
        let viewDocumentDetail = AppContext.model.getValue('viewDocumentDetail');
        Navigate.to('/Correspondence/DMMDC',{
            metaDataChange : viewDocumentDetail
        })
    };

    linkDelink = () => {
        let viewDocumentDetail = AppContext.model.getValue('viewDocumentDetail');
        Navigate.to('/Correspondence/DMVLD',{
            linkDelinkData : viewDocumentDetail
        })
    };

}

export default ViewDocumentDetail;