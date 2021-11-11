import Template from './AdditionalInfo.rt';
import { PageConfig, UXPage, AppContext, Navigate} from '@d-lift/core';
import {} from '@/Validations/customValidations.js';
import errorMessages from '../../../../public/validation/commonValidation.json';
import './AdditionalInfo.css';
import AdditionalInfoService from '@/Services/Correspondence/AdditionalInfoService';
import spinnerUtil from '@/Utills/SpinnerUtil/SpinnerUtill';

@PageConfig({
    ContextRoot: 'Correspondence',
    PageName: 'AdditionalInfo',
    Path: ['/AdditionalInfo', '/COVAI'],
    Template: Template,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Correspondence Additional Info',
})
class AdditionalInfo extends UXPage {

    initializeModel() {
        let previewData = {
            previewData: {
                errorField: [],
                isError: false,
               // coReqSeq: '94249170',
               // docId: 'FGGA0015',
               // preview: 'true',
                // manualFields: [ {
                //     deElementId: 68650,
                //     deElementType: 'TX',
                //     docId: 'CO-FGGA0015',
                //     fieldOrderNum: 1,
                //     refTableName: '',
                //     requiredSw: 'Y',
                //     seqNum: 586268,
                //     templateId: 'CO-FGGA0015',
                //     x2Coord: -1,
                //     y2Coord: -1,
                //     xcoord: null,
                //     ycoord: null
                // }],
                
            },
        };
        AppContext.model.setValue('additionalInfoData.isGenerateManualFlow', false);
        return previewData;
    }

     onPageLoad(){
        AppContext.notification.clearAll();
        if (this.props.location.state) {
            AppContext.model.setValue('additionalInfoData', this.props.location.state.additionalInfoData);
        }
        AppContext.model.setValue('additionalInfoData.isGenerateManualFlow', false);
        let initializeData = AppContext.model.getValue('additionalInfoData');
       this.initializeAdditionalInfoService(initializeData);     
    };

    resetForm = () => {
        AppContext.model.setValue('additionalInfoData','');
    };

    initializeAdditionalInfoService  = async initializeData => {
        spinnerUtil.show();
        await AdditionalInfoService.callInitializeService(initializeData).then(response => {
            if (!response.success) {
                AppContext.notification.clearAll();
                if (!initializeData.errorField.some(err => err === errorMessages.GL500)) {
                    initializeData.errorField.push(errorMessages.GL500);
                }
                spinnerUtil.hide();
                AppContext.notification.error(initializeData.errorField);
                AppContext.model.setValue('initializeData.show', false);
                window.scrollTo(0, 0);
            } else {
                spinnerUtil.hide();
                AppContext.notification.clearAll();
                AppContext.model.setValue('initializeData',response.foundRecords);
                AppContext.model.setValue('additionalInfoData',response.foundRecords.coManualData);
                if(response.foundRecords.parentPageId === 'COVPD'){
                    AppContext.model.setValue('initializeData.ViewPendingDetail', initializeData);
                }
                else if (response.foundRecords.parentPageId === 'COMCI'){
                    AppContext.model.setValue('initializeData.generateManualDetail', initializeData);
                }
            }
        });
    }
           
    saveDraft = () => {
        let saveDraftData = AppContext.model.getValue('additionalInfoData');
        AppContext.model.setValue('initializeData.additionalInfoData',saveDraftData);
        let initializeData = AppContext.model.getValue('initializeData');
        console.log(saveDraftData);
        this.callSaveDraftService(initializeData);
    }

    callSaveDraftService = async initializeData => {
        spinnerUtil.show();
        await AdditionalInfoService.saveDraftService(initializeData).then(response => {
            if (!response.success) {
                AppContext.notification.clearAll();
                if (!initializeData.errorField.some(err => err === errorMessages.GL500)) {
                    initializeData.errorField.push(errorMessages.GL500);
                }
                spinnerUtil.hide();
                AppContext.notification.error(initializeData.errorField);
                AppContext.model.setValue('saveDraftData.show', false);
                window.scrollTo(0, 0);
            } else {
                spinnerUtil.hide();
                AppContext.notification.clearAll();
                AppContext.model.setValue('saveDraftData',response.foundRecords);
                window.scrollTo(0, 0);
                AppContext.notification.success("Save Draft Success");
            }            
        });
    };

    preview = () => {
        let previewData = AppContext.model.getValue('additionalInfoData');
        AppContext.model.setValue('initializeData.additionalInfoData',previewData);
        let initializeData = AppContext.model.getValue('initializeData');
        console.log(initializeData);
        this.callPreviewService(initializeData);
    }

    callPreviewService = async initializeData  => {
        spinnerUtil.show();
        await AdditionalInfoService.previewService(initializeData).then(response => {
            if (!response.success) {
                AppContext.notification.clearAll();
                if (!initializeData.errorField.some(err => err === errorMessages.GL500)) {
                    initializeData.errorField.push(errorMessages.GL500);
                }
                spinnerUtil.hide();
                AppContext.notification.error(initializeData.errorField);
                AppContext.model.setValue('initializeData.show', false);
                window.scrollTo(0, 0);
            } 
            else {
                spinnerUtil.hide();
                window.scrollTo(0, 0);
                AppContext.notification.clearAll();
                AppContext.model.setValue('initializeData.additionalInfoData',"");
                AppContext.notification.info("PDF Preview Success");

            }  
        });
    };

    centralPrint = () => {
        let centralPrintData = AppContext.model.getValue('additionalInfoData');
        AppContext.model.setValue('initializeData.additionalInfoData',centralPrintData);
        let initializeData = AppContext.model.getValue('initializeData');
        this.callCentralPrintService(initializeData);
    }

    callCentralPrintService = async initializeData  => {
        spinnerUtil.show();
        await AdditionalInfoService.centralPrintService(initializeData).then(response => {
            if (!response.success) {
                if (!initializeData.errorField.some(err => err === errorMessages.GL500)) {
                    initializeData.errorField.push(errorMessages.GL500);
                }
                spinnerUtil.hide();
                AppContext.notification.error(initializeData.errorField);
                AppContext.model.setValue('initializeData.show', false);
                window.scrollTo(0, 0);
            } 
            else {
                spinnerUtil.hide();
                window.scrollTo(0, 0);
                AppContext.model.setValue('initializeData.additionalInfoData',"");
                AppContext.notification.info("Central Print Success");

            }  
        });
    };

    localPrint = () => {
        let localPrintData = AppContext.model.getValue('additionalInfoData');
        AppContext.model.setValue('initializeData.additionalInfoData',localPrintData);
        let initializeData = AppContext.model.getValue('initializeData');
        this.callLocalPrintService(initializeData);
    }

    callLocalPrintService = async initializeData  => {
        spinnerUtil.show();
        await AdditionalInfoService.localPrintService(initializeData).then(response => {
            if (!response.success) {
                AppContext.notification.clearAll();
                if (!initializeData.errorField.some(err => err === errorMessages.GL500)) {
                    initializeData.errorField.push(errorMessages.GL500);
                }
                spinnerUtil.hide();
                AppContext.notification.error(initializeData.errorField);
                AppContext.model.setValue('initializeData.show', false);
                window.scrollTo(0, 0);
            } 
            else {
                spinnerUtil.hide();
                window.scrollTo(0, 0);
                AppContext.notification.clearAll();
                AppContext.model.setValue('initializeData.additionalInfoData',"");
                AppContext.notification.info("Local Print Success");

            }  
        });
    };

    goBack = () =>{
        let parentPageId = AppContext.model.getValue('initializeData.parentPageId');
        Navigate.to('/Correspondence/'+parentPageId, {
         });
    };

    
}

export default AdditionalInfo;
