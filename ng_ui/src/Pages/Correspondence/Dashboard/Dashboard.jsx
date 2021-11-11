import Template from './Dashboard.rt';
import { PageConfig, UXPage, AppContext } from '@d-lift/core';
import {} from '@/Validations/customValidations.js';
import spinnerUtil from '@/Utills/SpinnerUtil/SpinnerUtill';
import DashboardService from '@/Services/Correspondence/DashboardService';
import React from 'react';
import { UX } from '@d-lift/uxcomponents';

@PageConfig({
    ContextRoot: 'Correspondence',
    PageName: 'Dashboard',
    Path: ['/Dashboard', '/COVSS'],
    Template: Template,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Dashboard',
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
                ]
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
        this.callDashboardService(noticestatusData);//}
    };

    callDashboardService = async noticestatusData => {
        spinnerUtil.show();
        await DashboardService.dashboardStatusService(noticestatusData).then(response => {
            if (!response.success) {
                AppContext.notification.clearAll();
                spinnerUtil.hide();
                AppContext.model.setValue('noticestatusData.show', false);
                window.scrollTo(0, 0);
            } else {
                if(response.foundRecords.emailSuccessCount>0 ||
                    response.foundRecords.emailFailureCount>0 || 
                    response.foundRecords.mailSuccessCount>0 || 
                    response.foundRecords.mailFailureCount>0 ||
                    response.foundRecords.textSuccessCount>0 || 
                    response.foundRecords.textFailureCount>0){
                        let options = [{
                            noticestatus: 'Success',
                            email: response.foundRecords.emailSuccessCount,
                            text: response.foundRecords.textSuccessCount,
                            mail: response.foundRecords.mailSuccessCount
                        },
                        {
                            noticestatus: 'Failure',
                            email: response.foundRecords.emailFailureCount,
                            text: response.foundRecords.textFailureCount,
                            mail: response.foundRecords.mailFailureCount
                        }]
                        AppContext.model.setValue('noticestatusData.dataCollection', options);
                        AppContext.model.setValue('noticestatusData.emailSuccessDetails', response.foundRecords.emailSuccessDetails);
                        AppContext.model.setValue('noticestatusData.emailFailureDetails', response.foundRecords.emailFailureDetails);
                        AppContext.model.setValue('noticestatusData.mailSuccessDetails', response.foundRecords.mailSuccessDetails);
                        AppContext.model.setValue('noticestatusData.mailFailureDetails', response.foundRecords.mailFailureDetails);
                        AppContext.model.setValue('noticestatusData.textSuccessDetails', response.foundRecords.textSuccessDetails);
                        AppContext.model.setValue('noticestatusData.textFailureDetails', response.foundRecords.textFailureDetails);
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
    //     AppContext.model.setValue('noticestatusData.show', true);
    //    let options = [{noticestatus:'Success',
    //                     email:'1',
    //                     text:'2',
    //                     mail:'3'},
                        
    //                     {noticestatus:'Failure',
    //                     email:'4',
    //                     text:'5',
    //                     mail:'0'}
    //                 ]
    //     AppContext.model.setValue('noticestatusData.dataCollection', options);
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
       AppContext.model.setValue('noticestatusData.emaildataCollection', '');
       AppContext.model.setValue('noticestatusData.textdataCollection', '');
       AppContext.model.setValue('noticestatusData.maildataCollection', '');
       
    };

    // formatCreateDate = (columnData, rowData) => {
    //     return new Date(rowData.createDt).toLocaleDateString();
    // };

    // formatUpdateDate = (columnData, rowData) => {
    //     return new Date(rowData.updateDt).toLocaleDateString();
    // };

    emailLink = (columnData, rowData) => {
        let emailCount = rowData.email;        
        if(emailCount>0){
        return(  <UX
                    click={event =>this.EmailDetails(columnData,rowData)}
                    target="_self"
                    type="hyperLink"
                    className="hyperLink">
                    {emailCount}
                </UX>);
        }
        else return(<UX type='label'>
            
            {emailCount}
        </UX>);
    };
    EmailDetails = (columnData,rowData) => {
        AppContext.model.setValue('noticestatusData.emailshow',true);
        let status = rowData.noticestatus;
        let successDetails = AppContext.model.getValue('noticestatusData.emailSuccessDetails');
        let failureDetails = AppContext.model.getValue('noticestatusData.emailFailureDetails');
        // let options =[
        //     {
        //         requestDate: '08/03/2021',
        //         noticeRequestId: '456',
        //         reasonForFailure: 'DEF'
        //     }
        // ]
        // AppContext.model.setValue('noticestatusData.emaildataCollection', options);
        if(status ==='Success'){
            AppContext.model.setValue('noticestatusData.emaildataCollection', successDetails);
        }else{
            AppContext.model.setValue('noticestatusData.emaildataCollection', failureDetails);
        }
        AppContext.model.setValue('noticestatusData.mailshow',false);
        AppContext.model.setValue('noticestatusData.textshow',false);
    };
    textLink = (columnData, rowData) => {
        let textCount = rowData.text;
        if(textCount>0){
        return(  <UX
                    click={event =>this.TextDetails(columnData, rowData)}
                    target="_self"
                    type="hyperLink"
                    className="hyperLink">
                    {textCount}
                </UX>);
        }
        else return(<UX type='label'>
            
            {textCount}
        </UX>);
    };
    TextDetails = (columnData, rowData) => {
        AppContext.model.setValue('noticestatusData.textshow',true);
        let status = rowData.noticestatus;
        let successDetails = AppContext.model.getValue('noticestatusData.textSuccessDetails');
        let failureDetails = AppContext.model.getValue('noticestatusData.textFailureDetails');
        // let options =[
        //     {
        //         textdate: '08/03/2021',
        //         textnoticeId: '456',
        //         textreason: 'DEF'
        //     }
        // ]
        // AppContext.model.setValue('noticestatusData.textdataCollection', options);
        if(status ==='Success'){
            AppContext.model.setValue('noticestatusData.textdataCollection', successDetails);
            let status = AppContext.model.setValue('noticestatusData.textColumnStatus','');
        }else{
            AppContext.model.setValue('noticestatusData.textdataCollection', failureDetails);
            let status = AppContext.model.setValue('noticestatusData.textColumnStatus','');
        }
        AppContext.model.setValue('noticestatusData.mailshow',false);
        AppContext.model.setValue('noticestatusData.emailshow',false);
    };
    mailLink = (columnData, rowData) => {
        let mailCount = rowData.mail;
        if(mailCount>0){
        return(  <UX
                    click={event =>this.MailDetails(columnData, rowData)}
                    target="_self"
                    type="hyperLink"
                    className="hyperLink">
                    {mailCount}
                </UX>);
        }
        else return(<UX type='label'>
            
            {mailCount}
        </UX>);
    };
    MailDetails = (columnData, rowData) => {
        AppContext.model.setValue('noticestatusData.mailshow',true);
        let status = rowData.noticestatus;
        let successDetails = AppContext.model.getValue('noticestatusData.mailSuccessDetails');
        let failureDetails = AppContext.model.getValue('noticestatusData.mailFailureDetails');
        // let options =[
        //     {
        //         maildate: '07/03/2021',
        //         mailnoticeId: '789',
        //         mailreason: 'GHI'
        //     }
        // ]
        // AppContext.model.setValue('noticestatusData.maildataCollection', options);
        if(status ==='Success'){
            AppContext.model.setValue('noticestatusData.maildataCollection', successDetails);
        }else{
            AppContext.model.setValue('noticestatusData.maildataCollection', failureDetails);
        }
        AppContext.model.setValue('noticestatusData.textshow',false);
        AppContext.model.setValue('noticestatusData.emailshow',false);
    };

    // async validate(id) {
    //     const node = document.getElementById(id);
    //     const childNodes = node.querySelectorAll('[model]');
    //     _.forEach(childNodes, function(childnode) {
    //         if (childnode.getAttribute('model') !== undefined) {
    //             if (AppContext.model.getValue(childnode.getAttribute('model')) === undefined) {
    //                 AppContext.pagedetails
    //                     .getPageContext()
    //                     .stateHandler(childnode.getAttribute('model'), undefined);
    //             }
    //         }
    //     });

    //     if (node.querySelectorAll("[is-error='true']").length === 0) {
    //         return true;
    //     }
    //     return false;
    // }
   

}

export default NoticeStatus;
