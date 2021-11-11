import Template from './DashboardNew.rt';
import { PageConfig, UXPage, AppContext } from '@d-lift/core';
import {} from '@/Validations/customValidations.js';
import spinnerUtil from '@/Utills/SpinnerUtil/SpinnerUtill';
import DashboardServiceNew from '@/Services/Correspondence/DashboardServiceNew';
import React from 'react';
import { UX } from '@d-lift/uxcomponents';

@PageConfig({
    ContextRoot: 'Correspondence',
    PageName: 'DashboardNew',
    Path: ['/DashboardNew', '/COVSR'],
    Template: Template,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'DashboardNew',
})
class NoticeStatus extends UXPage {
    initializeModel() {
        let noticestatusData = {
            noticestatusData: {
                errorField: [],
                isError: false,
                agency: 'DTA',
                status: '',
                startDate: '',
                endDate: '',
                channel:[],
                datatableCollection: [],
                statusList : [{ value: 'All', label: 'All' },
                { value: 'Success', label: 'Success' },
                { value: 'Failure', label: 'Failure' }],
                agencyList : [{ value: 'DTA', label: 'DTA' },
                { value: 'MASSHEALTH', label: 'Mass Health' }
                ],
                noticeType : [{value: 'ALL' , label : '<ALL>'}]
            }
        };

        return noticestatusData;
    }


    onPageLoad() {
        AppContext.notification.clearAll();
       
    }

    showTable = () => {
        let noticestatusData = AppContext.model.getValue('noticestatusData');
        console.log(noticestatusData);
      //  let valcheck = this.validate('search_card');
        noticestatusData.errorField=[];
       // if(valcheck){
        this.callDashboardServiceNew(noticestatusData);//}
    };

    callDashboardServiceNew = async noticestatusData => {
        spinnerUtil.show();
        await DashboardServiceNew.dashboardStatusService(noticestatusData).then(response => {
            if (!response.success) {
                AppContext.notification.clearAll();
                spinnerUtil.hide();
                AppContext.model.setValue('noticestatusData.show', false);
                window.scrollTo(0, 0);
            } else {
                if(response.foundRecords.length>0){
                        
                        AppContext.model.setValue('noticestatusData.dataCollection', response.foundRecords);
                    spinnerUtil.hide();
                    AppContext.notification.clearAll();
                    AppContext.model.setValue('noticestatusData.show', true);
                }else{
                    spinnerUtil.hide();
                    AppContext.notification.clearAll();
                    AppContext.model.setValue('noticestatusData.show', false);
                    window.scrollTo(0, 0);
                    AppContext.notification.error("No records found"); 
                }
            }
        });
    //     
    };
    
    perEmail = (columnData, rowData) => {
        rowData.percentEmailed = rowData.percentEmailed+'%';
        return rowData.percentEmailed;
    };

    perUndeliver = (columnData, rowData) => {
        rowData.percentUndeliveredEmail = rowData.percentUndeliveredEmail+'%';
        return rowData.percentUndeliveredEmail;
    };

    perPrinted = (columnData, rowData) => {
        rowData.percentPrinted = rowData.percentPrinted+'%';
        return rowData.percentPrinted;
    };

    perReturned = (columnData, rowData) => {
        rowData.percentMailReturned = rowData.percentMailReturned+'%';
        return rowData.percentMailReturned;
    };

    resetForm = () => {
        AppContext.model.setValue('noticestatusData', '');
        AppContext.model.setValue('noticestatusData.agency', 'DTA');
       let list = [{ value: 'All', label: 'All' },
       { value: 'Success', label: 'Success' },
       { value: 'Failure', label: 'Failure' }];
       let list2 = [{ value: 'DTA', label: 'DTA' },
       { value: 'MASSHEALTH', label: 'Mass Health' }
       ];
       AppContext.model.setValue('noticestatusData.statusList',list);
       AppContext.model.setValue('noticestatusData.agencyList',list2);
       AppContext.model.setValue('noticestatusData.status','');
       
    };

}

export default NoticeStatus;
