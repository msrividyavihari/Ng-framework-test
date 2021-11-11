import Template from './ViewPendingDetail.rt';
import { PageConfig, UXPage, AppContext, Navigate } from '@d-lift/core';
import {} from '@/Validations/customValidations.js';
import './ViewPendingDetail.css';
import spinnerUtil from '@/Utills/SpinnerUtil/SpinnerUtill';
import ViewHistoryDetailService from '@/Services/Correspondence/ViewHistoryDetailService';

@PageConfig({
    ContextRoot: 'Correspondence',
    PageName: 'ViewPendingDetail',
    Path: ['/viewPendingDetail', '/COVPD'],
    Template: Template,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Correspondence View Pending Detail',
})
class ViewPending extends UXPage {
    onPageLoad(){
        AppContext.notification.clearAll();
        if (this.props.location.state) {
            AppContext.model.setValue('viewPendingDetail', this.props.location.state.viewPendingDetail);
            let viewPendingData = AppContext.model.getValue('viewPendingDetail');
            this.callViewPendingDetailService(viewPendingData);
            AppContext.model.setValue('viewPendingDetail.parentPageID', "COVPD");
        }
    };

    callViewPendingDetailService = async viewPendingData => {
        spinnerUtil.show();
        await ViewHistoryDetailService.viewHistoryDetailService(viewPendingData).then(response => {
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
        let viewPendingDetail = AppContext.model.getValue('viewPendingDetail');
        Navigate.to('/Correspondence/COVPC', {
             viewPendingDetail: viewPendingDetail,
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

    goToNextPage = () =>{
        let fullName =  AppContext.model.getValue('recipientsDetail.originalRecipient');
        AppContext.model.setValue('viewPendingDetail.fullName', fullName);
        let additionalInfoData = AppContext.model.getValue('viewPendingDetail');
        Navigate.to('/Correspondence/COVAI', {
            additionalInfoData: additionalInfoData,
         });

    };

}

export default ViewPending;
