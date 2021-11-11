import Template from './ViewHistoryDetail.rt';
import { PageConfig, UXPage, AppContext, Navigate } from '@d-lift/core';
import {} from '@/Validations/customValidations.js';
import spinnerUtil from '@/Utills/SpinnerUtil/SpinnerUtill';
import ViewHistoryDetailService from '@/Services/Correspondence/ViewHistoryDetailService';

@PageConfig({
    ContextRoot: 'Correspondence',
    PageName: 'ViewHistoryDetail',
    Path: ['/ViewHistoryDetail', '/COVHD'],
    Template: Template,
    ReferenceTable: true,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'View History Detail',
})
class ViewHistoryDetail extends UXPage {
    onPageLoad(){
        if (this.props.location.state) {
            AppContext.model.setValue('viewHistoryDetail', this.props.location.state.viewHistoryDetail);
            let viewHistoryData = AppContext.model.getValue('viewHistoryDetail');
            this.callViewHistoryDetailService(viewHistoryData);
            AppContext.model.setValue('viewHistoryDetail.parentPageID', "COVHD");
        }
    };
    
    callViewHistoryDetailService = async viewHistoryData => {
        spinnerUtil.show();
        await ViewHistoryDetailService.viewHistoryDetailService(viewHistoryData).then(response => {
            if (response.success) {
                AppContext.model.setValue(
                    'recipientsDetail',
                    response.foundRecords,
                );
                spinnerUtil.hide();
                AppContext.notification.clearAll();
            }
            spinnerUtil.hide();
        });
    };

    callCentralReprintService = async viewHistoryDet => {
        spinnerUtil.show();
        await ViewHistoryDetailService.centralReprintService(viewHistoryDet).then(response => {
            if(response.success && response.foundRecords.length>0){
                AppContext.model.setValue(
                    'viewHistoryDet.info',
                    response.foundRecords,
                );
                spinnerUtil.hide();
                let info = AppContext.model.getValue('viewHistoryDet.info');
                window.scrollTo(0, 0);
                AppContext.notification.success(info);
            }
            spinnerUtil.hide();
        });
    };

    callPreviewService = async viewHistoryDet => {
        spinnerUtil.show();
        await ViewHistoryDetailService.previewService(viewHistoryDet).then(response => {
            if(response.success){
                spinnerUtil.hide();
                window.scrollTo(0, 0);
            }
            spinnerUtil.hide();
        });
    };

    callLocalReprintService = async viewHistoryDet => {
        spinnerUtil.show();
        await ViewHistoryDetailService.localReprintService(viewHistoryDet).then(response => {
            if(response.success){
                spinnerUtil.hide();
                window.scrollTo(0, 0);
            }
            spinnerUtil.hide();
        });
    };

    formatPrintMode = printMode => {
        switch (printMode) {
            case 'O':
                return "Local Print";
            case 'B':
                return "Central Print";
        }
    };

    formatPrintType = printType => {
        switch (printType) {
            case 'O':
                return "Original";
            case 'R':
                return "Reprint";
        }
    };
    
    formatSearchCriteria = (searchCriteria, docId) => {
        if(docId==='FXX317'){
            return "Facility";
        }else{
            return this.modifyCrValue(searchCriteria);
        }
    }

    modifyCrValue = searchCriteria => {
        switch (searchCriteria) {
            case 'C':
                return "Case";
            case 'A':
                return "Application";
            case 'L':
                return "Log";
            case 'P':
                return "Provider";
            case 'S':
                return "FEIN or SSN";  
        }
    };

    origRecipient = (rePrintSw,origRecipient) => {
        if(rePrintSw!=='R'){
            return origRecipient;
        }else{
            return "N/A";
        }
    };

    copyRecipient = recipientWithCoRptSeq => {
        let copyRept='';
		if (recipientWithCoRptSeq !== undefined && recipientWithCoRptSeq.length !== 0) {
			recipientWithCoRptSeq.forEach(element => {
                copyRept+=element+",";
            });
            copyRept = copyRept.replace(/,\s*$/, "");
		}
        return copyRept;
    };

    goBack = () =>{
        let viewHistoryDetail = AppContext.model.getValue('viewHistoryDetail');
        Navigate.to('/Correspondence/COVHC', {
            viewHistoryDetail: viewHistoryDetail,
         });
    };

    centralReprint = () => {
        let viewHistoryDet = AppContext.model.getValue('viewHistoryDetail');
        this.callCentralReprintService(viewHistoryDet);  
    };

    localReprint = () => {
        let viewHistoryDet = AppContext.model.getValue('viewHistoryDetail');
        this.callLocalReprintService(viewHistoryDet);
    };

    preview = () => {
        let viewHistoryDet = AppContext.model.getValue('viewHistoryDetail');
        this.callPreviewService(viewHistoryDet);
    };

}

export default ViewHistoryDetail;
