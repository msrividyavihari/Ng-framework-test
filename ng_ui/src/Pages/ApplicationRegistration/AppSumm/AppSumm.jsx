import Template from './AppSumm.rt';
import { PageConfig, UXPage, AppContext } from '@d-lift/core';
import errorMessages from '../../../../public/validation/commonValidation.json';

@PageConfig({
    ContextRoot: 'ApplicationRegistration',
    PageName: 'AppSumm',
    Path: ['/appsumm', "/ARRAS"],
    Template: Template,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Register Application - Summary',
})
class AppSumm extends UXPage {
    initializeModel() {
        //this.validateFields();

        return {
            summary: {
                programs: ['MA', 'TF'], // For disabling/enabling Aged/Blind/Disabled Checkbox.
                source: 'lowIncomeSubsidy', // For disabling/enabling the checkboxes.
                age: 65,
                isBlindOrDisabled: true,
                applicationFlags: ['QT', 'RF', 'RV', 'SP', 'PR', 'NH', 'WV', 'ABD'],
                appSum: {
                    currentPrimaryIndividual: 'Hamilton Banton 35M',
                    expedited: 'YES',
                    applicationSigned: 'NO',
                    isAgeBlindDisabled: 'YES',
                },
                program: [],
                priorityApplication: {
                    qTrack: true,
                    refugee: true,
                    revmax: true,
                    srsnap: true,
                    pregnancy: true,
                    nursingHome: true,
                    waiver: true,
                },
                datatableCollection: [
                    {
                        name: 'Test Abc',
                        ssn: '123-45-6789',
                        clientId: '987654321',
                        dob: '01/21/1989',
                        progarm: 'Food Stamps',
                        aliasNames: 'Test Def',
                    },
                ],
            }
        };
        //AppContext.model.setValue('summary', summaries);

    }

    validateFields() {
        if (AppContext.model.getValue('summary.programs').some(p => p === 'MA')) {
            if (
                AppContext.model.getValue('summary.source') === 'customerPortal' &&
                AppContext.model.getValue('summary.age') > 65 &&
                AppContext.model.getValue('summary.isBlindOrDisabled')
            ) {
                AppContext.model.setValue('summary.appSum.isAgeBlindDisabled', false);
            } else if (
                AppContext.model.getValue('summary.source') === 'paper' &&
                AppContext.model.getValue('summary.age') > 65
            ) {
                AppContext.model.setValue('summary.appSum.isAgeBlindDisabled', false);
            }
        }
        if (AppContext.model.getValue('summary.source') === 'customerPortal') {
            let qTrack = AppContext.model.getValue('summary.program');
            if (AppContext.model.getValue('summary.applicationFlags').some(p => p === 'QT')) {
                AppContext.model.setValue('checkedUnchecked.qTrack', true);
                qTrack.push('QT');
            }
            if (AppContext.model.getValue('summary.applicationFlags').some(p => p === 'RF')) {
                AppContext.model.setValue('checkedUnchecked.refugee', true);
                qTrack.push('RF');
            }
            if (AppContext.model.getValue('summary.applicationFlags').some(p => p === 'SP')) {
                AppContext.model.setValue('checkedUnchecked.srsnap', true);
                qTrack.push('SP');
            }
            if (AppContext.model.getValue('summary.applicationFlags').some(p => p === 'PR')) {
                AppContext.model.setValue('checkedUnchecked.pregnancy', true);
                qTrack.push('PR');
            }
            if (AppContext.model.getValue('summary.applicationFlags').some(p => p === 'NH')) {
                AppContext.model.setValue('checkedUnchecked.nursingHome', true);
                qTrack.push('NH');
            }
            if (AppContext.model.getValue('summary.applicationFlags').some(p => p === 'ABD')) {
                AppContext.model.setValue('checkedUnchecked.waiver', true);
                qTrack.push('WV');
            }
        }

        if (AppContext.model.getValue('summary.source') === 'paper') {
            AppContext.model.setValue('summary.priorityApplication.qTrack', false);
            AppContext.model.setValue('summary.priorityApplication.revmax', false);
            AppContext.model.setValue('summary.priorityApplication.srsnap', false);
            AppContext.model.setValue('summary.priorityApplication.pregnancy', false);
            AppContext.model.setValue('summary.priorityApplication.nursingHome', false);
            AppContext.model.setValue('summary.priorityApplication.waiver', false);
            AppContext.model.setValue('summary.priorityApplication.refugee', false);
        }

        if (AppContext.model.getValue('summary.source') === 'lowIncomeSubsidy') {
            AppContext.model.setValue('summary.priorityApplication.revmax', false);
            AppContext.model.setValue('summary.priorityApplication.srsnap', false);
            AppContext.model.setValue('summary.priorityApplication.pregnancy', false);
            AppContext.model.setValue('summary.priorityApplication.nursingHome', false);
            AppContext.model.setValue('summary.priorityApplication.waiver', false);
            AppContext.model.setValue('summary.priorityApplication.refugee', false);
            AppContext.model.setValue('checkedUnchecked.qTrack', true);
            let qTrack = AppContext.model.getValue('summary.program');
            qTrack.push('QT');
            AppContext.model.setValue('summary.program', qTrack);
        }
    }

    SubmitForm = () => {
        if (AppContext.model.getValue('summary.appSum.applicationSigned') === 'NO') {
            AppContext.pagedetails
                .getPageContext()
                .stateHandler('msgsList', [errorMessages.GANEW225]);
            AppContext.pagedetails.getPageContext().stateHandler('errorMsg', true);
            window.scrollTo(0, 0);
        } else {
            AppContext.pagedetails.getPageContext().stateHandler('errorMsg', false);
            window.scrollTo(0, 0);
        }
    };
}

export default AppSumm;
