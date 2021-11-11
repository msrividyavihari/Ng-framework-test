import { BaseComponent } from '@d-lift/uxcomponents';
import ApplicantsTemplate from './sspApplicants.rt';
import { AppContext } from '@d-lift/core';
import _ from 'lodash';
import './sspApplicants.css';
import pageUtills from '../../../../src/ScreenFlowUtils/PageUtills';

class sspApplicants extends BaseComponent {
    async sero(item, itemIndex) {
        AppContext.model.setValue('SSPApplicantsFCIndex', itemIndex);
        if (AppContext.model.getValue('applicants') === undefined) {
            AppContext.model.setValue('HohFileCleared', false);
            AppContext.model.setValue('HohAlert' + itemIndex, true);
        } else {
            let HohClearedStatus = AppContext.model.getValue('applicants').map((item) => {
                return item.primaryApplicantSw == 'Yes' ? true : false;
            });
            if (HohClearedStatus.includes(true)) {
                AppContext.model.setValue('HohFileCleared', true);
                //document.getElementById('fileClearButton' + itemIndex).setAttribute('disabled', true);
                document.getElementsByName(
                    item.ssn + itemIndex.toString() + '_B',
                )[0].style.display = 'none';
                document.getElementsByName(
                    item.ssn + itemIndex.toString() + '_B',
                )[1].style.display = 'none';

                //document.getElementById('fileClearButton' + itemIndex).style.display = 'none';
                // document.getElementById('fileClearRemoveButton' + itemIndex).style.display = 'none';
                // AppContext.model.setValue('ar.pc.search.firstName', item.firstName);
                // AppContext.model.setValue('ar.pc.search.lastName', item.lastName);
                // AppContext.model.setValue(
                //     'ar.pc.search.dobDt',
                //     item.dob
                //         .split('/')
                //         .reverse()
                //         .join('-'),
                // );
                // AppContext.model.setValue(
                //     'ar.pc.search.gender',
                //     item.gender === 'Male' ? 'M' : 'F',
                // );
                //AppContext.model.setValue('ar.pc.search.ssn', item.ssn);
                item.ssn === undefined
                    ? AppContext.model.setValue('ar.pc.search.ssnNotProvided', true)
                    : AppContext.model.setValue('ar.pc.search.ssnNotProvided', false);
                AppContext.model.setValue('sspCurrentApplicant', item);

                this.closePanelByID('applicants');
                this.openNextPanel('file_clearance');
                pageUtills.loadDataintoFileClearanceSSP(item.ssn);
                //AppContext.model.setValue('EstablishNewPerson', false);
                AppContext.model.setValue('sspFileClearSwitch' + itemIndex, true);
                AppContext.model.setValue('currentSspPanel', 'sspPanel_' + itemIndex);
            } else {
                AppContext.model.setValue('HohFileCleared', false);
                AppContext.model.setValue('HohAlert' + itemIndex, true);
            }
        }

        return true;
    }

    async import(item, itemIndex) {
        document.getElementById('sspPanel_' + itemIndex).style.display = 'none';
        let index = AppContext.model.getValue('applicants').length - 1;
        AppContext.model.setValue('applicants[' + index + '].firstName', item.firstName);
        AppContext.model.setValue('applicants[' + index + '].lastName', item.lastName);
        AppContext.model.setValue('applicants[' + index + '].dob', item.dob);
        AppContext.model.setValue('applicants[' + index + '].gender', item.gender);
        AppContext.model.setValue('applicants[' + index + '].ssn', item.ssn);
        let primaryCheckArray = AppContext.model.getValue('primary_Applicant_Checkbox');
        let includeCheckArray = AppContext.model.getValue('include_Applicant_Checkbox');
        if (primaryCheckArray === undefined) {
            primaryCheckArray = [];
        }
        if (includeCheckArray === undefined) {
            includeCheckArray = [];
        }
        if (item.primaryApplicantSw === 'Yes') {
            AppContext.model.setValue('applicants[' + index + '].primaryApplicantSw', 'Yes');
            AppContext.model.setValue('HohFileCleared', true);
            primaryCheckArray.push('primaryApplicantCheckbox_' + index);
        }
        AppContext.model.setValue('applicants[' + index + '].includeApplicantSw', 'Yes');
        includeCheckArray.push('includeApplicantCheckbox_' + index);
        AppContext.model.setValue('primary_Applicant_Checkbox', primaryCheckArray);
        AppContext.model.setValue('include_Applicant_Checkbox', includeCheckArray);

        AppContext.model.setValue('EstablishNewPerson', false);
        if (document.getElementById('hohWarning') !== null) {
            document.getElementById('hohWarning').style.display = 'none';
        }
        let dcapplicants = AppContext.model.getValue('applicants');
        let sspApplicants = AppContext.model.getValue('sspApplicants');
        let result = sspApplicants.filter(
            ({ ssn: id1 }) => !dcapplicants.some(({ ssn: id2 }) => id2 === id1),
        );
        let len =
            AppContext.model.getValue('removed') === undefined
                ? 0
                : AppContext.model.getValue('removed');
        AppContext.model.setValue('sspPending', result.length - len);
        console.log(result.length);
        if (result.length - len <= 0) {
            pageUtills.closeSSPApplicants();
        } else {
            AppContext.model.setValue('fileClearStatus', 'Not Cleared');
        }
        return true;
    }

    async remove(item, itemIndex) {
        // document.getElementById('fileClearButton' + itemIndex).style.display = 'none';
        // document.getElementById('fileClearRemoveButton' + itemIndex).style.display = 'none';
        document.getElementsByName(item.ssn + itemIndex.toString() + '_B')[0].style.display =
            'none';
        document.getElementsByName(item.ssn + itemIndex.toString() + '_B')[1].style.display =
            'none';
        AppContext.model.setValue('remove.popup' + itemIndex, true);
    }

    closePanelByID = (id) => {
        if (document.getElementById(id + '_panel').classList.contains('ux-custom-panel-active'))
            document.getElementById(id + '_header').firstChild.click();
    };

    openNextPanel = (id) => {
        this.enablePanelById(id);
        this.openPanelByID(id);
    };

    async onConfirmModal(itemIndex) {
        let len =
            AppContext.model.getValue('removed') === undefined
                ? 0
                : AppContext.model.getValue('removed');
        console.log('RemovedIndex --' + itemIndex);
        AppContext.model.setValue('removed', len + 1);
        AppContext.model.setValue('remove.popup' + itemIndex, false);
        document.getElementById('sspPanel_' + itemIndex).style.display = 'none';
        let dcapplicants = AppContext.model.getValue('applicants');
        let sspApplicants = AppContext.model.getValue('sspApplicants');
        let result = sspApplicants.filter(
            ({ ssn: id1 }) => !dcapplicants.some(({ ssn: id2 }) => id2 === id1),
        );

        AppContext.model.setValue(
            'sspPending',
            result.length - AppContext.model.getValue('removed'),
        );
        console.log(AppContext.model.getValue('sspPending'));
        if (AppContext.model.getValue('sspPending') <= 0) {
            document.getElementById('mySidepanelApplicants').style.display = 'none';
            document.getElementById('sspIndvs').style.display = 'none';
            AppContext.model.setValue('fileClearStatus', 'Cleared');
            if (
                AppContext.model.getValue('conflictApplicants') !== undefined &&
                AppContext.model.getValue('conflictApplicants').length > 0
            ) {
                AppContext.model.setValue('showApplicantConflicts', 'true');
            }
            document.getElementById(`file_clearance_exclamation`).classList.add('success');
        } else {
            AppContext.model.setValue('fileClearStatus', 'Not Cleared');
        }
    }

    async onCloseModal(itemIndex) {
        document.getElementById('fileClearButton' + itemIndex).style.display = '';
        document.getElementById('fileClearRemoveButton' + itemIndex).style.display = '';
        AppContext.model.setValue('remove.popup' + itemIndex, false);
    }

    openPanelByID = (id) => {
        console.log(id + '_panel');
        if (!document.getElementById(id + '_panel').classList.contains('ux-custom-panel-active')) {
            document.getElementById(id + '_header').firstChild.click();
            document
                .getElementById(id + '_panel')
                .scrollIntoView({ behavior: 'smooth', block: 'nearest', inline: 'start' });
        }
    };

    enablePanelById = (id) => {
        if (document.getElementById(id + '_panel').classList.contains('edit-mode-off')) {
            document.getElementById(id + '_panel').classList.remove('edit-mode-off');
            document.getElementById(id + '_panel').classList.add('edit-mode-on');
        }
    };

    renderComponent() {
        return ApplicantsTemplate.bind(this).apply(this);
    }

    buildApplicantCards(children) {
        let applicantCards = AppContext.model.getValue('sspApplicants');
        if (!Array.isArray(applicantCards)) {
            applicantCards = [applicantCards];
        }
        const items = applicantCards.filter((item) => undefined !== item);
        return items;
    }
}

export default sspApplicants;
