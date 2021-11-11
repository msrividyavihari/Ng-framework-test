import { BaseComponent } from '@d-lift/uxcomponents';
import ApplicantsTemplate from './Applicants.rt';
import { AppContext } from '@d-lift/core';
import { Date } from 'sugar';
import _ from 'lodash';
import './Applicants.css';
import basicAxiosInterceptor from '@/HttpInterceptors/BasicAxiosInterCeptor';
import { ResponseHandlers } from '@/Utills/DataAdapter/DataAdaptedUtill';

class Applicants extends BaseComponent {
    uxComponentDidMount = () => {};

    renderComponent() {
        return ApplicantsTemplate.bind(this).apply(this);
    }

    buildApplicantCards(children) {
        let applicantCards = AppContext.model.getValue('applicants');
        if (!Array.isArray(applicantCards)) {
            applicantCards = [applicantCards];
        }
        const items = applicantCards.filter(item => undefined !== item);

        return items;
    }

    async addApplicant() {
        let applicantCards = AppContext.model.getValue('applicants');
        if (!Array.isArray(applicantCards)) {
            applicantCards = [applicantCards];
        }
        let validation = applicantCards.some(applicant => applicant.primaryApplicantSw === 'Yes');
        if (!validation && applicantCards.length > 0) {
            AppContext.model.setValue('primaryMissingError', true);
        }

        // Validation logic within the panel
        const node = document.getElementById('applicants');
        const childNodes = node.querySelectorAll('[model]');
        await _.forEach(childNodes, function(childnode) {
            if (childnode.getAttribute('model') !== undefined) {
                if (
                    AppContext.model.getValue(childnode.getAttribute('model')) === undefined ||
                    AppContext.model.getValue(childnode.getAttribute('model')) === ''
                ) {
                    AppContext.pagedetails
                        .getPageContext()
                        .stateHandler(childnode.getAttribute('model'), undefined);
                }
            }
        });

        if (
            node.querySelectorAll("[is-error='true']").length === 0 &&
            (AppContext.model.getValue('primaryMissingError') === false ||
                AppContext.model.getValue('primaryMissingError') === undefined)
        ) {
            document.getElementById('applicants_header').classList.add('ux-panel-success');

            let element = document.getElementById('applicants_exclamation');
            if (element.classList.contains('exclamation')) {
                element.classList.remove('exclamation');
            }
            element.classList.add('success');

            /*
            let appl = AppContext.model.getValue('applicants');
            if (appl && appl.length > 0) {
                appl.forEach(d => (d.dob = new Date(d.dob).format('{MM}/{dd}/{yyyy}').raw));

                const appNumber = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');

                let payload = {
                    appNum: appNumber,
                    applicants: appl,
                };

                try {
                    let arAppResp = await basicAxiosInterceptor({
                        method: 'POST',
                        url: AppContext.config.insertUpdateApplicants,
                        data: payload,
                    });
                    let applicantsResponse = ResponseHandlers.handleResponse({ ...arAppResp.data });
                    if (undefined !== applicantsResponse) {
                        AppContext.model.setValue('applicants', applicantsResponse);
                    }
                } catch (ex) {
                    console.log(ex);
                    return false;
                }

                let finalApplicants = AppContext.model.getValue('applicants');
                const items = finalApplicants.filter(item => undefined !== item);
                const applicantItems = items.map(applicant => {
                    return applicant.firstName;
                });

                AppContext.model.setValue('applicantsNames', applicantItems);
            }*/
            AppContext.model.setValue('disableFrSSP', 'false');
            this.closePanelByID('applicants');
            this.openNextPanel('file_clearance');
            return true;
        } else {
            document.getElementById('applicants_header').classList.remove('ux-panel-success');

            let element = document.getElementById('applicants_exclamation');
            element.classList.add('exclamation');
            AppContext.model.setValue('disableFrSSP', 'false');
        }
        return false;
    }

    closePanelByID = id => {
        if (document.getElementById(id + '_panel').classList.contains('ux-custom-panel-active'))
            document.getElementById(id + '_header').firstChild.click();
    };

    openNextPanel = id => {
        this.enablePanelById(id);
        this.openPanelByID(id);
    };

    openPanelByID = id => {
        console.log(id + '_panel');
        if (!document.getElementById(id + '_panel').classList.contains('ux-custom-panel-active')) {
            document.getElementById(id + '_header').firstChild.click();
            document
                .getElementById(id + '_panel')
                .scrollIntoView({ behavior: 'smooth', block: 'nearest', inline: 'start' });
        }
    };

    enablePanelById = id => {
        if (document.getElementById(id + '_panel').classList.contains('edit-mode-off')) {
            document.getElementById(id + '_panel').classList.remove('edit-mode-off');
            document.getElementById(id + '_panel').classList.add('edit-mode-on');
        }
    };
}

export default Applicants;
