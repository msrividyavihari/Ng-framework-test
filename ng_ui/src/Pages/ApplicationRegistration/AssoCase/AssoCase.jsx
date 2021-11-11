import Template from './AssoCase.rt';
import { PageConfig, UXPage, AppContext } from '@d-lift/core';

import _ from 'lodash';

import navigationUtills from '@/ScreenFlowUtils/NavigationUtills';
import errorMessages from '../../../../public/validation/commonValidation.json';
import { setAuditLogTransactions } from '@/Utills/AuditLog/AuditLogUtills';

@PageConfig({
    ContextRoot: 'ApplicationRegistration',
    PageName: 'AssoCase',
    Path: ['/assocase', '/ARTMC'],
    Template: Template,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Associated Case',
})
class AssoCase extends UXPage {
    initializeModel() {
        console.log('recived Data', this.state.recieved);
        if (this.state.recieved) {
            AppContext.model.setValue('selectedPerson', this.state.recieved?.perosnClear?.selected);
        }
        AppContext.model.setValue('associatedCase.selectedIndvs', []);
    }

    onPageLoad() {
        let caseIdnv = [
            {
                caseNum: 988327372,
                name: 'John Wick',
                Status: 'Ongoing - Approved',
                StatusDate: '2020-11-10',
                Programs: 'Food Stamps, SNAP , Meds',
                ServiceCouty: 'Gorgia',
            },
            {
                caseNum: 1829201873,
                name: 'Tim John',
                Status: 'Ongoing - Pending',
                StatusDate: '2020-11-10',
                Programs: 'Food Stamps, TANF , Meds',
                ServiceCouty: 'Lousania',
            },
        ];

        AppContext.model.setValue('casesInvolved', caseIdnv);
    }

    associateCase = () => {
        AppContext.notification.clearWarning();
        AppContext.model.setValue('associatedCase.indvs');
        if (!AppContext.model.getValue('associatedCase.selectedCase')) {
            AppContext.notification.warning(
                errorMessages['GANEW211'] + this.getModelValue('selectedPerson.name'),
            );
            window.scrollTo(0, 0);
            return;
        }
        setAuditLogTransactions('AssociateCases');
        let personList = [
            {
                personId: '1',
                name: 'Tim John',
                dateOfBirth: '2019-08-01',
                clinetId: '67255332',
                SSN: '678-321-1221',
                activeInCase: 'N',
                gender: 'Male',
                caseNum: 1829201873,
            },
            {
                personId: '2',
                name: 'John wick',
                dateOfBirth: '2019-08-01',
                clinetId: '67255392',
                SSN: '678-321-1221',
                activeInCase: 'Y',
                gender: 'Female',
                caseNum: 1829201873,
            },
            {
                personId: '3',
                name: 'John doe',
                dateOfBirth: '2019-08-01',
                clinetId: '64255332',
                SSN: '678-321-1221',
                activeInCase: 'Y',
                gender: 'Female',
                caseNum: 1829201873,
            },
            {
                personId: '4',
                name: 'Ram Singh',
                dateOfBirth: '2019-08-01',
                clinetId: '67255832',
                SSN: '678-321-1221',
                activeInCase: 'Y',
                gender: 'Male',
                caseNum: 988327372,
            },
            {
                personId: '5',
                name: 'Kachara',
                dateOfBirth: '2019-08-01',
                clinetId: '67655332',
                SSN: '678-321-1221',
                activeInCase: 'N',
                gender: 'Male',
                caseNum: 1829201873,
            },
            {
                personId: '6',
                name: 'Kim Um',
                dateOfBirth: '1996-08-01',
                clinetId: '9327393',
                SSN: '678-673-1221',
                activeInCase: 'Y',
                gender: 'Female',
                caseNum: 988327372,
            },
            {
                personId: '7',
                name: 'Don Seenu',
                dateOfBirth: '2019-08-01',
                clinetId: '7832683',
                SSN: '893-321-1221',
                activeInCase: 'N',
                gender: 'Male',
                caseNum: 988327372,
            },
        ];

        let personList2 = personList.filter(
            person =>
                person.caseNum === AppContext.model.getValue('associatedCase.selectedCase').caseNum,
        );
        console.table(personList2);
        AppContext.model.setValue('associatedCase.indvs', [...personList2]);
    };

    setAssociatedCaseData = (rowData, isSelected, rowIndex, event) => {
        isSelected
            ? AppContext.model.setValue('associatedCase.selectedCase', { ...rowData })
            : AppContext.model.setValue('associatedCase.selectedCase');
    };

    setAssociatedIndvs = (rowData, isSelected, rowIndex, event) => {
        console.table(rowData, isSelected, rowIndex, event);
        let accocIndvs = AppContext.model.getValue('associatedCase.selectedIndvs');
        isSelected ? accocIndvs.push(rowData) : _.remove(accocIndvs, data => data === rowData);
        AppContext.model.setValue('associatedCase.selectedIndvs', [...accocIndvs]);
    };
    goToNextPage = () => {
        let associatedCase = this.getModelValue('associatedCase');
        if (associatedCase.selectedIndvs.length === 0) {
            AppContext.notification.error(errorMessages['GANEW203']);
            window.scrollTo(0, 0);
            return;
        }
        setAuditLogTransactions('goToNextPage');
        navigationUtills.toNextPage(associatedCase);
    };
    goBack = () => {
        setAuditLogTransactions('goBack');
        navigationUtills.toPreviousPage();
    };

    selectAllRows = (isSelected, rows, e) => {
        console.table(isSelected, rows, e);
        isSelected
            ? AppContext.model.setValue('associatedCase.selectedIndvs', [...rows])
            : AppContext.model.setValue('associatedCase.selectedIndvs', []);
    };
}

export default AssoCase;
