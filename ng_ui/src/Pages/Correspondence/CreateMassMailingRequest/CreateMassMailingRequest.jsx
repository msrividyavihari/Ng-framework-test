import Template from './CreateMassMailingRequest.rt';
import { PageConfig, UXPage, AppContext, Navigate } from '@d-lift/core';
import {} from '@/Validations/customValidations.js';
import './CreateMassMailingRequest.css';
import spinnerUtil from '@/Utills/SpinnerUtil/SpinnerUtill';
import errorMessages from '../../../../public/validation/commonValidation.json';
import MassMailingService from '@/Services/Correspondence/MassMailingService';

@PageConfig({
    ContextRoot: 'Correspondence',
    PageName: 'CreateMassMailingRequest',
    Path: ['/CreateMassMailingRequest', '/COMRO'],
    Template: Template,
    ReferenceTable: true,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Create Mass Mailing Request',
})
class CreateMassMailingRequest extends UXPage {
    onPageLoad(){
        AppContext.notification.clearAll();
        if (this.props.location.state) {
            AppContext.model.setValue('massMailingData', this.props.location.state.massMailingData);
            let data = AppContext.model.getValue('massMailingData');
            if(data==""){
                AppContext.model.setValue('massMailingData.recptPop', 'DCH');
            }
            AppContext.model.setValue('massMailingData.parentPageID', "COMRO");
        }
    };
    initializeModel() {
        let massMailingData = {
            massMailingData: {
                errorField: [],
                programLst: [],
                recptPop: 'DCH',
                scheduledDt: '',
                noticeTitle: '',
                noticeText: '',
                policyManualRef: '',
                countyCd: []
            }
        };
        return massMailingData;
    }

    callCreateMassMailingService = async massMailingData => {
        spinnerUtil.show();
        await MassMailingService.createMassMailingService(massMailingData).then(response => {
            if (!response.success) {
                if (!massMailingData.errorField.some(err => err === errorMessages.GL500)) {
                    massMailingData.errorField.push(errorMessages.GL500);
                }
                spinnerUtil.hide();
                AppContext.notification.error(massMailingData.errorField);
                window.scrollTo(0, 0);
            }else {
                spinnerUtil.hide();
                this.goBack();
                AppContext.notification.success(response.foundRecords.messageCodes);
                window.scrollTo(0, 0);
            }
        });
    };

    callPreviewService = async massMailingData => {
        spinnerUtil.show();
        await MassMailingService.previewService(massMailingData).then(response => {
            if (!response.success) {
                if (!massMailingData.errorField.some(err => err === errorMessages.GL500)) {
                    massMailingData.errorField.push(errorMessages.GL500);
                }
                spinnerUtil.hide();
                AppContext.notification.error(massMailingData.errorField);
                window.scrollTo(0, 0);
            }else {
                spinnerUtil.hide();
                window.scrollTo(0, 0);
                AppContext.notification.clearAll();
            }
        });
    };

    submitRequest = () => {
        this.validateAllFields();
    };

    validateAllFields = () => {
        let mass = AppContext.model.getValue('massMailingData');
        if (!Array.isArray(mass)) {
            mass = [mass];
        }
        let fieldValidation = mass.some(
            massMail =>
                massMail.scheduledDt === undefined ||
                (massMail.programLst === undefined && (massMail.recptPop != 'CC' || massMail.recptPop != 'PC')) ||
                massMail.noticeTitle === undefined ||
                massMail.noticeText === undefined ||
                massMail.policyManualRef === undefined ||
                massMail.countyCd === undefined
            );
        if(fieldValidation){
            AppContext.notification.error(errorMessages.CO006);
            window.scroll(0,0);
        }else{
            AppContext.notification.clearAll();
            let massMailingData = AppContext.model.getValue('massMailingData');
            this.callCreateMassMailingService(massMailingData);
        }
    };

    onRecptPopChange = () => {
        AppContext.model.setValue('massMailingData.programLst',[]);
    };

    goBack = () => {
        Navigate.to('/Correspondence/COMSO');
    };

    preview = () => {
        let massMailingData = AppContext.model.getValue('massMailingData');
        this.callPreviewService(massMailingData);
    };

}

export default CreateMassMailingRequest;
