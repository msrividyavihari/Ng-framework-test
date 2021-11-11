import Template from './GenerateManual.rt';
import { PageConfig, UXPage, AppContext, Navigate } from '@d-lift/core';
import {} from '@/Validations/customValidations.js';
import errorMessages from '../../../../public/validation/commonValidation.json';
import GenerateManualService from '@/Services/Correspondence/GenerateManualService';
import './GenerateManual.css';
import spinnerUtil from '@/Utills/SpinnerUtil/SpinnerUtill';

@PageConfig({
    ContextRoot: 'Correspondence',
    PageName: 'GenerateManual',
    Path: ['/GenerateManual', '/COMCI'],
    ReferenceTable: true,
    Template: Template,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Correspondence Generate Manual',
})
class GenerateManual extends UXPage {
    initializeModel() {
        let generateManualData = {
            generateManualData: {
                errorField: [],
                caseNumber: '',
                datatableCollection: [
                    // {
                    //     clientId: 1024124621,
                    //     fullName: 'Martin Louis',
                    //     age: 60,
                    //     gender: 'M'
                    // },
                    // {
                    //     clientId: 1024124621,
                    //     fullName: 'Martin Louis',
                    //     age: 60,
                    //     gender: 'M'
                    // },
                    // {
                    //     clientId: 1024124621,
                    //     fullName: 'Martin Louis',
                    //     age: 60,
                    //     gender: 'M'
                    // },
                ],
            },
        };

        AppContext.model.setValue('GenerateManual.caseId',);
        AppContext.model.setValue('generateManualData.show', false);
        AppContext.model.setValue('COMCI', {
            DocTitle: [
                {
                    title: 'title 1',
                },
                {
                    title: 'title 2'
                }
            ],

        })

        console.table(AppContext.model.getValue('GenerateManual'));
        return generateManualData;
    }

    onPageLoad(){
        AppContext.notification.clearAll();
        let generateManualData = AppContext.model.getValue('generateManualData');
        console.log(generateManualData);
        this.callCoMasterService(generateManualData);
    }

    showTable = () => {
        let generateManualData = AppContext.model.getValue('generateManualData');
        console.log(generateManualData);
        this.callCaseSearchService(generateManualData);
    }

    setGenerateManualData = (rowData, isSelected, rowIndex, event) => {
        isSelected
            ? AppContext.model.setValue('GenerateManual.selected', { ...rowData })
            : AppContext.model.setValue('GenerateManual.selected');
        AppContext.model.setValue('additionalInfoData.generateManual', rowData);
    };

    NavToAdditionalInfo = e => {
        console.log("generateManualData", AppContext.model.getValue('generateManualData'));
        AppContext.model.setValue('additionalInfoData.parentPageID', 'COMCI');

        
        let docId = AppContext.model.getValue('generateManualData.docId');
        AppContext.model.setValue('additionalInfoData.docId', docId);
        let coMaster =  AppContext.model.getValue('generateManualData.coMaster');
        for(let i=0; i< coMaster["Document Type List"].length; i++) {
            if(coMaster["Document Type List"][i]['docId'] === docId) {
                AppContext.model.setValue('additionalInfoData.docName', 
                coMaster["Document Type List"][i]['docName']);
            }
        }
        let caseNumber = AppContext.model.getValue('generateManualData.caseNumber');
        AppContext.model.setValue('additionalInfoData.caseNumber', caseNumber);
        let fullName = AppContext.model.getValue('generateManualData.datatableCollection.data[0].fullName');
        AppContext.model.setValue('additionalInfoData.fullName', fullName);
        let additionalInfoData = AppContext.model.getValue('additionalInfoData');
        console.log("additionalInfo: ", additionalInfoData);

        Navigate.to('/Correspondence/COVAI', {
            additionalInfoData: additionalInfoData,
        });
    };

    callCoMasterService = async generateManualData => {
        spinnerUtil.show();
        await GenerateManualService.callCoMasterService(generateManualData).then(response => {
            if (!response.success) {
                AppContext.notification.clearAll();
                if (!generateManualData.errorField.some(err => err === errorMessages.GL500)) {
                    generateManualData.errorField.push(errorMessages.GL500);
                }
                spinnerUtil.hide();
                AppContext.notification.error(generateManualData.errorField);
                // window.scrollTo(0, 0);
            } else {
                AppContext.model.setValue(
                    'generateManualData.coMaster.Document Type List',
                    response.foundRecords.data,
                );
                spinnerUtil.hide();
                AppContext.notification.clearAll();
            }
        });
    };

    // options({ coMaster }) {
    //     return (
    //         coMaster.map(option =>
    //                     <option key={coMaster.id} value={coMaster.value}>
    //                     {coMaster.value}
    //                     </option>)
    //                    );
    // }
    callCaseSearchService = async generateManualData => {
        spinnerUtil.show();
        await GenerateManualService.generateManualService(generateManualData).then(response => {
            if (!response.success) {
                AppContext.notification.clearAll();
                if (!generateManualData.errorField.some(err => err === errorMessages.GL500)) {
                    generateManualData.errorField.push(errorMessages.GL500);
                }
                spinnerUtil.hide();
                AppContext.notification.error(generateManualData.errorField);
                AppContext.model.setValue('generateManualData.show', false);
                window.scrollTo(0, 0);
            } else {
                spinnerUtil.hide();
                AppContext.notification.clearAll();
                // this.formatStatusText(response.foundRecords);
                AppContext.model.setValue(
                    'generateManualData.datatableCollection',
                    response.foundRecords,
                );
                AppContext.model.setValue('generateManualData.docId',generateManualData.docId);
                AppContext.model.setValue('generateManualData.caseNumber',generateManualData.caseNumber);
                AppContext.model.setValue('generateManualData.show', true);
                window.scrollTo(0, 0);
                AppContext.notification.success("Case Search Success");
            }
        });
    };
}

export default GenerateManual;
