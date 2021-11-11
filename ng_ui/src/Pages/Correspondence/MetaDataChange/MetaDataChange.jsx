import Template from './MetaDataChange.rt';
import { PageConfig, UXPage, AppContext, Util } from '@d-lift/core';
import {} from '@/Validations/customValidations.js';
import errorMessages from '../../../../public/validation/commonValidation.json';
import spinnerUtil from '@/Utills/SpinnerUtil/SpinnerUtill';
import SearchDocumentsService from '@/Services/Correspondence/SearchDocumentsService';

@PageConfig({
    ContextRoot: 'Correspondence',
    PageName: 'MetaDataChange',
    Path: ['/MetaDataChange', '/DMMDC'],
    Template: Template,
    ReferenceTable: true,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'MetaData Change',
})
class MetaDataChange extends UXPage {
    onPageLoad(){
        AppContext.notification.clearAll();
        if (this.props.location.state) {
            AppContext.model.setValue('metaData', this.props.location.state.metaDataChange);
            AppContext.model.setValue('metaData.parentPageID', "DMMDC");
            let metaData = AppContext.model.getValue('metaData');
            this.callMetaDataChangeService(metaData);
        }
    };
  
    initializeModel() {
        let metaData = {
            metaData: {
                modifiedDocumentType:'',
                modifiedSsn : '',
                errorField: []
            }
        };
        return metaData;
    }

    callMetaDataChangeService = async metaData => {
        spinnerUtil.show();
        await SearchDocumentsService.metaDataChangeService(metaData).then(response => {
            if (!response.success) {
                AppContext.notification.clearAll();
                if (!metaData.errorField.some(err => err === errorMessages.GL500)) {
                    metaData.errorField.push(errorMessages.GL500);
                }
                spinnerUtil.hide();
                AppContext.notification.error(metaData.errorField);
                window.scrollTo(0, 0);
            }else {
                spinnerUtil.hide();
                AppContext.notification.clearAll();
                AppContext.model.setValue('metadata', response.foundRecords);
                let originalSsn = AppContext.model.getValue('metadata.ssn');
                AppContext.model.setValue('metaData.originalSsn', originalSsn);
                let action = AppContext.model.getValue('metaData.actionType');
                if(action==="updateMetaData"){
                    let origDocType = Util.getRefTableDescriptionByCode('DISDOCTYPE', AppContext.model.getValue('metadata.originalDocumentType'));
                    AppContext.model.setValue('metaData.docType', origDocType);
                }
                if(response.foundRecords.messageCodes=="Successfully updated"){
                    AppContext.notification.clearAll();
                    AppContext.notification.success(response.foundRecords.messageCodes);
                }else if(response.foundRecords.messageCodes!=null){
                    AppContext.notification.clearAll();
                    AppContext.notification.error(response.foundRecords.messageCodes[0]);
                }
                window.scrollTo(0, 0);
            }
        });  
    }

    metadataChange = () => {
        this.validateAllFields();
    };

    validateAllFields = () => {
        let metadata = AppContext.model.getValue('metaData');
        if (!Array.isArray(metadata)) {
            metadata = [metadata];
        }
        let fieldValidation = metadata.some(
            metadata =>
                metadata.modifiedDocumentType === undefined || metadata.modifiedDocumentType === "" ||
                metadata.modifiedSsn === undefined || metadata.modifiedSsn === ""
            );
        if(fieldValidation){
            AppContext.notification.error(errorMessages.DM689);
            window.scroll(0,0);
        }else{
            AppContext.notification.clearAll();
            AppContext.model.setValue('metaData.actionType', 'updateMetaData');
            let modDocType = AppContext.model.getValue('metaData.modifiedDocumentType');
            AppContext.model.setValue('metaData.docType', modDocType);
            let metadata = AppContext.model.getValue('metaData');
            this.callMetaDataChangeService(metadata);
        }
    };

    formatDocType = docType => {
        return Util.getRefTableDescriptionByCode('DISDOCTYPE', docType);
    };

}

export default MetaDataChange;