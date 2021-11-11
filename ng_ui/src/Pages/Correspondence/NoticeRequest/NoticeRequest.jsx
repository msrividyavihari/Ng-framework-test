import Template from './NoticeRequest.rt';
import { PageConfig, UXPage, AppContext } from '@d-lift/core';
import {} from '@/Validations/customValidations.js';
import spinnerUtil from '@/Utills/SpinnerUtil/SpinnerUtill';
import NoticeRequestService from '@/Services/Correspondence/NoticeRequestService';
import errorMessages from '../../../../public/validation/commonValidation.json';

@PageConfig({
    ContextRoot: 'Correspondence',
    PageName: 'NoticeRequest',
    Path: ['/NoticeRequest', '/COVSH'],
    Template: Template,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Notice Request',
})
class NoticeRequest extends UXPage {
    initializeModel() {
        let noticeRequestData = {
            noticeRequestData: {
                errorField: [],
                isError: false,
                caseNumber: '',
                noticeId: '',
                startDate: '',
                endDate: '',
                datatableCollection: []
            }
        };

        return noticeRequestData;
    }

    onPageLoad() {
        AppContext.notification.clearAll();
    }

    showTable = () => {
        let noticeRequestData = AppContext.model.getValue('noticeRequestData');
        console.log(noticeRequestData);
        noticeRequestData.errorField=[];
        this.callNoticeRequestService(noticeRequestData);
    };

    callNoticeRequestService = async noticeRequestData => {
        spinnerUtil.show();
        await NoticeRequestService.noticeRequestService(noticeRequestData).then(response => {
            if (!response.success) {
                AppContext.notification.clearAll();
                spinnerUtil.hide();
                AppContext.model.setValue('noticeRequestData.show', false);
                window.scrollTo(0, 0);
            } else {
                if(response.foundRecords.length>0){
                    AppContext.model.setValue(
                        'noticeRequestData.dataCollection',
                        response.foundRecords,
                    );
                    spinnerUtil.hide();
                    AppContext.notification.clearAll();
                    AppContext.model.setValue('noticeRequestData.show', true);
                }else{
                    spinnerUtil.hide();
                    AppContext.notification.clearAll();
                    AppContext.model.setValue('noticeRequestData.show', false);
                    window.scrollTo(0, 0);
                    AppContext.notification.error("No records found"); 
                }
            }
        });
    };

    selectRow = (rowData, isSelected, rowIndex, event) => {
        AppContext.model.setValue('noticeRequestData.selected', '');
        isSelected
            ? AppContext.model.setValue('noticeRequestData.selected', { ...rowData })
            : AppContext.model.setValue('noticeRequestData.selected');
    };

    preview = () => {
        let previewData = AppContext.model.getValue('noticeRequestData.selected');
        if(previewData){
            this.callPreviewService(previewData);
        }
    }

    callPreviewService = async previewData  => {
        spinnerUtil.show();
        await NoticeRequestService.viewPendingPreviewService(previewData).then(response => {
            if (!response.success) {
                AppContext.notification.clearAll();
                if (!previewData.errorField.some(err => err === errorMessages.GL500)) {
                    previewData.errorField.push(errorMessages.GL500);
                }
                spinnerUtil.hide();
                AppContext.notification.error(previewData.errorField);
                window.scrollTo(0, 0);
            } 
            else {
                spinnerUtil.hide();
                window.scrollTo(0, 0);
                AppContext.notification.clearAll();
                AppContext.notification.info("PDF Preview Success");

            }  
        });
    };

    centralPrint = () => {
        let centralPrintData = AppContext.model.getValue('noticeRequestData.selected');
        if(centralPrintData){
            this.callCentralPrintService(centralPrintData);
        }
    }

    callCentralPrintService = async centralPrintData  => {
        spinnerUtil.show();
        await NoticeRequestService.viewPendingCentralPrintService(centralPrintData).then(response => {
            if (!response.success) {
                if (!centralPrintData.errorField.some(err => err === errorMessages.GL500)) {
                    centralPrintData.errorField.push(errorMessages.GL500);
                }
                spinnerUtil.hide();
                AppContext.notification.error(centralPrintData.errorField);
                window.scrollTo(0, 0);
            } 
            else {
                spinnerUtil.hide();
                window.scrollTo(0, 0);
                AppContext.notification.info("Central Print Success");

            }  
        });
    };

    localPrint = () => {
        let localPrintData = AppContext.model.getValue('noticeRequestData.selected');
        if(localPrintData){
            this.callLocalPrintService(localPrintData);
        }
    }

    callLocalPrintService = async localPrintData  => {
        spinnerUtil.show();
        await NoticeRequestService.viewPendinglocalPrintService(localPrintData).then(response => {
            if (!response.success) {
                AppContext.notification.clearAll();
                if (!localPrintData.errorField.some(err => err === errorMessages.GL500)) {
                    localPrintData.errorField.push(errorMessages.GL500);
                }
                spinnerUtil.hide();
                AppContext.notification.error(localPrintData.errorField);
                window.scrollTo(0, 0);
            } 
            else {
                spinnerUtil.hide();
                window.scrollTo(0, 0);
                AppContext.notification.clearAll();
                AppContext.notification.info("Local Print Success");

            }  
        });
    };

    resetForm = () => {
        AppContext.model.setValue('noticeRequestData', '');
    };

    formatCreateDate = (columnData, rowData) => {
        return new Date(rowData.createDt).toLocaleDateString();
    };

    formatUpdateDate = (columnData, rowData) => {
        return new Date(rowData.updateDt).toLocaleDateString();
    };

}

export default NoticeRequest;
