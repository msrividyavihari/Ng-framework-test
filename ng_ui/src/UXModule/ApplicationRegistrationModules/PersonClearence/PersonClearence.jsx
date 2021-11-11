import { BaseComponent } from '@d-lift/uxcomponents';
import { AppContext } from '@d-lift/core';
import Template from './PersonClearence.rt';
import './PersonClearence.css';
import basicAxiosInterceptor from '@/HttpInterceptors/BasicAxiosInterCeptor';
import _ from 'lodash';
import errorMessages from '../../../../public/validation/commonValidation.json';
class PersonClearence extends BaseComponent {
    uxComponentDidMount = () => {
        AppContext.model.setValue('ar.pc.search.collection.above85', []);
        AppContext.model.setValue('ar.pc.search.collection.below85', false);
        AppContext.model.setValue('ar.pc.search.ssnMand', false);
        AppContext.model.setValue('ar.pc.search.didntFind', false);
        AppContext.model.setValue('ar.pc.search.ssnln', false);
        AppContext.model.setValue('ar.pc.search.ssnotherln', false);
        AppContext.model.setValue('ar.pc.search.ssnAddtMand', false);
        // console.log('ABCD');
        // AppContext.model.setValue('ar.pc.search.collection.above85', [
        //     {
        //         indvId: 1024124620,
        //         ssn: 202412462,
        //         dobDt: '1967-05-05',
        //         age: 53,
        //         lastName: 'Adasd',
        //         firstName: 'Sdasd',
        //         ethnicityCd: 'CH',
        //         gender: 'M',
        //         alisName: ['Naymer D', 'L Messi'],
        //         relationShips: [
        //             { name: 'Ram', relation: 'cousin' },
        //             { name: 'Sham', relation: 'spouse' },
        //         ],
        //         score: 100,
        //     },
        // ]);
        // let collection = [
        //     {
        //         indvId: 1024124620,
        //         ssn: 1024124620,
        //         dobDt: '1967-05-05T18:30:00.000+00:00',
        //         age: 53,
        //         lastName: 'Adasd',
        //         firstName: 'Sdasd',
        //         ethnicityCd: 'CH',
        //         gender: 'M',
        //         alisName: ['Naymer D', 'L Messi'],
        //         relationShips: [
        //             {
        //                 name: '',
        //             },
        //         ],
        //         score: 85,
        //     },
        //     {
        //         indvId: 1024124621,
        //         ssn: 1024124621,
        //         dobDt: '1960-05-18T18:30:00.000+00:00',
        //         age: 60,
        //         lastName: 'Asdad',
        //         firstName: 'Sdasd',
        //         ethnicityCd: 'ME',
        //         gender: 'M',
        //         alisName: [''],
        //         relationShips: [
        //             { name: 'Ram', relation: 'cousin' },
        //             { name: 'Sham', relation: 'spouse' },
        //         ],
        //         score: 60,
        //     },
        // ];
        // AppContext.model.setValue('ar.pc.search.collection.below85', collection);
    };

    async validate(id) {
        // Validation logic within the panel
        const node = document.getElementById(id);
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

        if (node.querySelectorAll("[is-error='true']").length === 0) {
            return true;
        }
        return false;
    }

    searchBySSN = async event => {
        await AppContext.model.setValue('ar.pc.search.ssnMand', true);
        AppContext.model.setValue('ar.pc.search.collection.below85', []);
        AppContext.model.setValue('ar.pc.search.collection.above85', []);
        AppContext.model.setValue('ar.pc.search.ssnotherln', false);
        AppContext.model.setValue('ar.pc.search.ssnln', false);
        let valcheck = await this.validate('file_clearance');
        if (
            AppContext.model.getValue('ar.pc.search.ssn') &&
            AppContext.model.getValue('ar.pc.search.ssn') !== ''
        ) {
            if (AppContext.model.getValue('ar.pc.search.ssn') && valcheck) {
                let fileClearData = await basicAxiosInterceptor({
                    method: 'POST',
                    url: AppContext.config.fileClearBySSN,
                    data: {
                        ssn: AppContext.model.getValue('ar.pc.search.ssn'),
                    },
                });
                AppContext.model.setValue('ar.pc.search.collection.above85', [
                    ...fileClearData.data,
                ]);

                if (AppContext.model.getValue('ar.pc.search.collection.above85').length === 0) {
                    AppContext.model.setValue('ar.pc.search.zeroResults', true);
                    AppContext.model.setValue('ARRAP.RN.rNavCond', 'fileClrNoMatch');
                    AppContext.model.setValue('sspDcMatch', false);
                } else {
                    AppContext.model.setValue('ARRAP.RN.rNavCond', 'fileClrOneMatch1');
                    AppContext.model.setValue('ar.pc.search.zeroResults', false);
                    AppContext.model.setValue('sspDcMatch', true);
                }
                AppContext.model.setValue('ar.pc.search.ssnln', true);
            } else {
                AppContext.model.setValue('ar.pc.search.ssnln', false);
            }
        } else {
            AppContext.model.setValue('ar.pc.search.ssnln', false);
        }
        AppContext.model.setValue('ar.pc.search.didntFind', true);

        await AppContext.model.setValue('ar.pc.search.ssnMand', false);
        if (document.getElementById('search_ssn') != null) {
            document.getElementById('search_ssn').classList.remove('panel-left-border-color');
        }
    };

    searchByDetails = async event => {
        await AppContext.model.setValue('ar.pc.search.ssnAddtMand', true);
        AppContext.model.setValue('ar.pc.search.collection.below85', []);
        AppContext.model.setValue('ar.pc.search.collection.above85', []);
        AppContext.model.setValue('ar.pc.search.ssnln', false);
        AppContext.model.setValue('ar.pc.search.ssnotherln', false);
        let valcheck = await this.validate('file_clearance');
        if (
            AppContext.model.getValue('ar.pc.search.firstName') === undefined ||
            AppContext.model.getValue('ar.pc.search.lastName') === undefined ||
            AppContext.model.getValue('ar.pc.search.gender') === undefined ||
            AppContext.model.getValue('ar.pc.search.dobDt') === undefined
        ) {
            AppContext.model.setValue('fileClearError', true);
            AppContext.model.setValue('fileClearErrorMsg', errorMessages.GANEW552);
            document
                .getElementById('file_clearance_panel')
                .scrollIntoView({ behavior: 'smooth', block: 'start', inline: 'end' });
            return;
        } else {
            AppContext.model.setValue('fileClearError', false);
            AppContext.model.setValue('fileClearErrorMsg', '');

            if (valcheck) {
                let fileClearData = await basicAxiosInterceptor({
                    method: 'post',
                    url: AppContext.config.fileClearByDetails,
                    data: { ...AppContext.model.getValue('ar.pc.search') },
                });
                console.log(AppContext.model.getValue('ar.pc.search'));
                console.log([...fileClearData.data]);
                AppContext.model.setValue(
                    'ar.pc.search.collection.above85',
                    fileClearData.data.filter(data => {
                        return data.score > 84;
                    }),
                );

                AppContext.model.setValue(
                    'ar.pc.search.collection.below85',
                    fileClearData.data.filter(data => {
                        return data.score < 85;
                    }),
                );
                AppContext.model.setValue('ar.pc.search.ssnotherln', true);
            } else {
                AppContext.model.setValue('ar.pc.search.ssnotherln', false);
            }

            AppContext.model.setValue('ar.pc.search.didntFind', true);
            if (AppContext.model.getValue('ar.pc.search.collection.above85').length === 0) {
                AppContext.model.setValue('ARRAP.RN.rNavCond', 'fileClrNoMatch');
            } else if (AppContext.model.getValue('ar.pc.search.collection.above85').length === 1) {
                AppContext.model.setValue('ARRAP.RN.rNavCond', 'fileClrOneMatch2');
            } else if (AppContext.model.getValue('ar.pc.search.collection.above85').length > 1) {
                AppContext.model.setValue('ARRAP.RN.rNavCond', 'fileClrMultiMatch');
            }

            await AppContext.model.setValue('ar.pc.search.ssnAddtMand', false);
            if (document.getElementById('search_by_details') != null) {
                document
                    .getElementById('search_by_details')
                    .classList.remove('panel-left-border-color');
            }
        }
    };

    establishNewPerson = event => {
        if (AppContext.model.getValue('appReg.applicantType') === 'P') {
            if (AppContext.model.getValue('sspMode') !== 'Y') {
                this.props.createApp();
            } else {
                if (document.getElementById('sspPanelInfoAlert1'))
                    document
                        .getElementById('sspPanelInfoAlert1')
                        .setAttribute('style', 'display:none !important');
                if (document.getElementById('sspPanelInfoAlert2'))
                    document
                        .getElementById('sspPanelInfoAlert2')
                        .setAttribute('style', 'display:none !important');
            }
            AppContext.model.setValue('appReg.applicantType', 'A');
        }
        if (AppContext.model.getValue('sspMode') === 'Y') {
            AppContext.model.setValue('EstablishNewPerson', true);
        }
        this.props.closePanel('file_clearance');
        this.props.openPanel('applicants');
        this.props.enablePanel('applicants');
        this.props.resetFileClearencePanel();

        //Adding new applicant.
        let newApplicant = { indvStatusSw: 'T', ssn: '' };
        let applicantsList = AppContext.model.getValue('applicants');
        if (undefined !== applicantsList && applicantsList.length > 0) {
            applicantsList.push(newApplicant);
            AppContext.model.setValue('applicants', applicantsList);
            console.log('Establishing a new applicant');
        } else {
            let applicants = [newApplicant];
            AppContext.model.setValue('applicants', applicants);
            console.log('Creating applicants array and establishing a new applicant');
        }
    };

    renderComponent() {
        return Template.bind(this).apply(this);
    }

    selPrimaryIndv = (id, rp) => {
        document.getElementsByName(id + '_B')[0].style.display = 'none';
        document.getElementsByName(id + '_G')[0].style.display = 'block';
        if (AppContext.model.getValue('maintainMode') === 'N') {
            AppContext.model.setValue('pcNxtclick', true);
        }
        AppContext.model.setValue('primaryIndvSel', id);
        if (rp && document.getElementById(id))
            document.getElementById(id).style.borderColor = '#14F1BB';
        let x = document.querySelectorAll('.selbtnFc2');
        let i;
        for (i = 0; i < x.length; i++) {
            let name = x[i].name.slice(0, -2);
            if (name !== id.toString()) this.deselPrimaryIndvNoMode(name, true);
        }
    };

    deselPrimaryIndvNoMode = (id, rp) => {
        if (rp && document.getElementById(id))
            document.getElementById(id).style.borderColor = '#DCDCDC';
        document.getElementsByName(id + '_G')[0].style.display = 'none';
        document.getElementsByName(id + '_B')[0].style.display = 'block';
    };

    deselPrimaryIndv = (id, rp) => {
        if (rp && document.getElementById(id))
            document.getElementById(id).style.borderColor = '#DCDCDC';
        document.getElementsByName(id + '_G')[0].style.display = 'none';
        document.getElementsByName(id + '_B')[0].style.display = 'block';
        if (AppContext.model.getValue('maintainMode') === 'N') {
            AppContext.model.setValue('pcNxtclick', false);
        }
    };
    ssnNotProvidedSw() {
        AppContext.model.setValue('ar.pc.search.ssn', '');
        if (
            !AppContext.model.getValue('ar.pc.search.ssnNotProvided') ||
            AppContext.model.getValue('ar.pc.search.ssnNotProvided') === false
        ) {
            if (document.getElementById('search_ssn') != null) {
                document.getElementById('search_ssn').classList.remove('panel-left-border-color');
            }
        } else {
            document.getElementById('search_ssn').classList.add('panel-left-border-color');
        }
    }
}

export default PersonClearence;
