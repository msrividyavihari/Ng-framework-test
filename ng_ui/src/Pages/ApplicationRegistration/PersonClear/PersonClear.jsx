import Template from './PersonClear.rt';
import { PageConfig, UXPage, AppContext } from '@d-lift/core';
import React from 'react';

import navigationUtills from '@/ScreenFlowUtils/NavigationUtills';
import workFlowUtills from '@/ScreenFlowUtils/WorkFlowUtills';
import errorMessages from '../../../../public/validation/commonValidation.json';
import { setAuditLogTransactions } from '@/Utills/AuditLog/AuditLogUtills';

@PageConfig({
    ContextRoot: 'ApplicationRegistration',
    PageName: 'PersonClear',
    Path: ['/personclear', '/ARTMP'],
    Template: Template,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'File Clearence',
})
class PersonClear extends UXPage {
    initializeModel() {
        let YESNO = {
            '': [
                {
                    text: 'Yes',
                    value: 'Y',
                },
                {
                    text: 'No',
                    value: 'N',
                },
            ],
        };
        AppContext.model.setValue('YESNO', YESNO);
    }
    goToNextPage = () => {
        console.table(AppContext.model.getValue('personClear'));
        if (
            this.getModelValue('personCollection') &&
            !AppContext.pagedetails.getPageContext().validate().isError
        ) {
            let perosnClear = this.getModelValue('personClear');
            console.log(perosnClear);
            if (!perosnClear.selected && perosnClear.associateIndv === 'Y') {
                AppContext.notification.warning(errorMessages['GANEW211']);
                window.scrollTo(0, 0);
            } else {
                if (perosnClear && perosnClear.associateIndv === 'Y')
                    workFlowUtills.addSubScreenAfterCurrentScreen('ARTMC');
                else workFlowUtills.removePage('ARTMC');
                setAuditLogTransactions('goToNextPage');
                navigationUtills.toNextPage({ perosnClear });
            }

            // AppContext.notification.warning(
            //     AppContext.pagedetails.getPageContext().wcmResourceCache.en['select_a_case'],
            // );
            // AppContext.notification.info(
            //     AppContext.pagedetails.getPageContext().wcmResourceCache.en['select_a_case'],
            // );
            // AppContext.notification.success(
            //     AppContext.pagedetails.getPageContext().wcmResourceCache.en['select_a_case'],
            // );
            // AppContext.notification.error(
            //     AppContext.pagedetails.getPageContext().wcmResourceCache.en['select_a_case'],
            // );

            // AppContext.pagedetails
            //     .getPageContext()
            //     .stateHandler(
            //         'msgsList',
            //         AppContext.pagedetails.getPageContext().wcmResourceCache.en['select_a_case'],
            //     );
            // AppContext.pagedetails.getPageContext().stateHandler('errorMsg', true);
        }
    };

    goBack = () => {
        setAuditLogTransactions('GoBack');
        navigationUtills.toPreviousPage();
    };

    goToHomePage = () => {
        setAuditLogTransactions('goToHomePage');
        navigationUtills.goToHomePage();
    };

    expandTheRow = (row, rowIndex) => {
        return (
            <div>
                <p>{`This Expand row is belong to rowKey ${row.name}`}</p>
                <p>
                    You can render anything here, also you can add additional data on every row
                    object
                </p>
                <p>expandRow.renderer callback will pass the origin row object to you</p>
            </div>
        );
    };

    onPageLoad() {
        // AppContext.pagedetails.getPageContext().stateHandler('msgsList', ['Hellow']);
        // AppContext.pagedetails.getPageContext().stateHandler('errorMsg', true);
        // AppContext.pagedetails.getPageContext().stateHandler('warningMsgsList', ['Hellow Error']);
        // AppContext.pagedetails.getPageContext().stateHandler('warningMsg', true);
        // AppContext.pagedetails
        //     .getPageContext()
        //     .stateHandler('infoMsgsList', ['Hellow Error', 'Error Aaagay']);
        // AppContext.pagedetails.getPageContext().stateHandler('infoMsg', true);
        // AppContext.pagedetails.getPageContext().stateHandler('infoMsg', false);
        // console.table(personData);
        AppContext.model.setValue('personCollection', [
            {
                personId: '1',
                name: 'Tim John',
                dateOfBirth: '2019-08-01',
                clinetId: '67255332',
                SSN: '678-321-1221',
                Score: '90',
            },
            {
                personId: '2',
                name: 'John wick',
                dateOfBirth: '2019-08-01',
                clinetId: '67255332',
                SSN: '678-321-1221',
                Score: '90',
            },
            {
                personId: '3',
                name: 'John doe',
                dateOfBirth: '2019-08-01',
                clinetId: '67255332',
                SSN: '678-321-1221',
                Score: '90',
            },
            {
                personId: '4',
                name: 'Ram Singh',
                dateOfBirth: '2019-08-01',
                clinetId: '67255332',
                SSN: '678-321-1221',
                Score: '90',
            },
            {
                personId: '5',
                name: 'Kachara',
                dateOfBirth: '2019-08-01',
                clinetId: '67255332',
                SSN: '678-321-1221',
                Score: '90',
            },
            {
                personId: '6',
                name: 'Kim Um',
                dateOfBirth: '1996-08-01',
                clinetId: '9328393',
                SSN: '678-673-1221',
                Score: '90',
            },
            {
                personId: '7',
                name: 'Don Seenu',
                dateOfBirth: '2019-08-01',
                clinetId: '7832383',
                SSN: '893-321-1221',
                Score: '100',
            },
        ]);

        if (!AppContext.model.getValue('personCollection')) {
            AppContext.model.setValue('personClear', { associateIndv: 'N', establishAsNew: 'Y' });
            //AppContext.model.setValue('showPotentialMatch', false);
        }
    }

    setPersonClearData = (rowData, isSelected, rowIndex, event) => {
        isSelected
            ? AppContext.model.setValue('personClear.selected', { ...rowData })
            : AppContext.model.setValue('personClear.selected');
    };

    associateIndvChange = () => {
        let establishAsNew = this.getModelValue('personClear.associateIndv');
        if (establishAsNew === 'N') AppContext.model.setValue('personClear.establishAsNew', 'Y');
        else AppContext.model.setValue('personClear.establishAsNew', 'N');
    };

    establishAsNewChange = () => {
        let establishAsNew = this.getModelValue('personClear.establishAsNew');
        if (establishAsNew === 'N') AppContext.model.setValue('personClear.associateIndv', 'Y');
        else AppContext.model.setValue('personClear.associateIndv', 'N');
    };
}

export default PersonClear;
