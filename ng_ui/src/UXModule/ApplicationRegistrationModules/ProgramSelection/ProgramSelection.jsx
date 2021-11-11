import React from 'react';
import { BaseComponent } from '@d-lift/uxcomponents';
import { UX } from '@d-lift/uxcomponents';
import { AppContext, Util } from '@d-lift/core';
import Template from './ProgramSelection.rt';
import _, { truncate } from 'lodash';
import basicAxiosInterceptor from '@/HttpInterceptors/BasicAxiosInterCeptor';
import './ProgramSelection.css';
import spinnerUtil from '@/Utills/SpinnerUtil/SpinnerUtill';
import md5 from '@/Utills/MD5Utils';
import errorMessages from '../../../../public/validation/commonValidation.json';
//import ka from 'date-fns/locale/ka/index';

class ProgramSelection extends BaseComponent {
    constructor(props) {
        super(props);
    }
    uxComponentDidMount = () => {
        // AppContext.model.setValue('applicant', [
        //     {
        //         applicant: 'Bibhu Prasad',
        //          coverage: '12/25/2020',
        // },
        //     {
        //         applicant: 'Phani',
        //         coverage: '12/25/2020',
        //     },
        //     {
        //         applicant: 'Kowshik',
        //         coverage: '12/25/2020',
        //     },
        // ]);
        AppContext.model.setValue('psel.indvProgram', '');
        AppContext.model.setValue('psel.indvId.MAindvIds', []);
        AppContext.model.setValue('programArray', []);
        AppContext.model.setValue('programString', '');
        AppContext.model.setValue('button1', true);
        AppContext.model.setValue('nonhealth', false);
        AppContext.model.setValue('expedited', false);

        var healthCareImage = document.createElement('img');
        healthCareImage.setAttribute('src', '/icons/healthCare.svg');
        healthCareImage.className = 'programs-card';
        healthCareImage.alt = 'Medicaid';
        document.getElementById('healthCare_label').prepend(healthCareImage);

        var snapImage = document.createElement('img');
        snapImage.setAttribute('src', '/icons/snap.svg');
        snapImage.className = 'programs-card';
        snapImage.alt = 'SNAP';
        document.getElementById('snap_label').prepend(snapImage);

        var tanfImage = document.createElement('img');
        tanfImage.setAttribute('src', '/icons/tanf.svg');
        tanfImage.className = 'programs-card';
        tanfImage.alt = 'TANF';
        document.getElementById('tanf_label').prepend(tanfImage);

        for (var i = 0; i < 3; i++) {
            document.getElementById('programCheckbox').querySelectorAll('i.icon.fa.fa-check')[
                i
            ].style.display = 'none';
        }
    };

    reflectIncomeChange = async event => {
        AppContext.model.setValue('pSel.exp.EG', event.target.value);
        if (AppContext.model.getValue('button3') === false) {
            AppContext.model.setValue('button3', true);

            AppContext.model.setValue('section3', false);
            AppContext.model.setValue('expeditedResult', false);
            AppContext.model.setValue('button4', false);

            AppContext.model.setValue('section4', false);
            //AppContext.model.setValue('pSel.exp.ER', '');
            AppContext.model.setValue('expeditedMessage', false);
            AppContext.model.setValue('expedited', false);
            AppContext.model.setValue('common', false);
        }
    };

    reflectResourcesChange = async event => {
        AppContext.model.setValue('pSel.exp.EH', event.target.value);
        if (AppContext.model.getValue('button3') === false) {
            AppContext.model.setValue('button3', true);

            AppContext.model.setValue('section3', false);
            AppContext.model.setValue('expeditedResult', false);
            AppContext.model.setValue('button4', false);

            AppContext.model.setValue('section4', false);
            //AppContext.model.setValue('pSel.exp.ER', '');
            AppContext.model.setValue('expeditedMessage', false);
            AppContext.model.setValue('expedited', false);
            AppContext.model.setValue('common', false);
        }
    };

    reflectRentalChange = async event => {
        AppContext.model.setValue('pSel.exp.ER', event.target.value);
        if (AppContext.model.getValue('button4') === false) {
            AppContext.model.setValue('button4', true);
            AppContext.model.setValue('section4', false);
            AppContext.model.setValue('expeditedMessage', false);
            AppContext.model.setValue('expedited', false);
            AppContext.model.setValue('common', false);
        }
    };

    programSelected = program => {
        var prog = program.target.value;
        var selected = program.target.checked;
        let programArray = AppContext.model.getValue('programArray');

        if (prog === 'FS') {
            if (selected) {
                this.defaultTick('FS');
                AppContext.model.setValue('snap', true);
                AppContext.model.setValue('section2', true);
                if (AppContext.model.getValue('common')) {
                    AppContext.model.setValue('common', false);
                    AppContext.model.setValue('expeditedResult', false);
                    AppContext.model.setValue('expeditedMessage', false);
                    AppContext.model.setValue('expedited', false);
                }
                AppContext.model.setValue('button3', true);

                if (!programArray.some(err => err === prog)) {
                    programArray.push(prog);
                }
            } else {
                AppContext.model.setValue('snap', false);
                AppContext.model.setValue('button3', false);
                if (AppContext.model.getValue('common') === true) {
                    AppContext.model.setValue('button1', false);
                    AppContext.model.setValue('section3', false);
                    AppContext.model.setValue('expeditedResult', false);
                    AppContext.model.setValue('section4', false);
                } else {
                    AppContext.model.setValue('button1', true);
                }
                programArray = programArray.filter(prog => prog !== 'FS');
            }
        }

        if (prog === 'MA') {
            if (selected) {
                this.defaultTick('MA');
                AppContext.model.setValue('health', true);
                AppContext.model.setValue('nonhealth', false);
                if (!programArray.some(err => err === prog)) {
                    programArray.push(prog);
                }
            } else {
                AppContext.model.setValue('health', false);
                AppContext.model.setValue('nonhealth', true);
                programArray = programArray.filter(prog => prog !== 'MA');
            }
        }

        if (prog === 'TF') {
            if (selected) {
                this.defaultTick('TF');
                if (!programArray.some(err => err === prog)) {
                    programArray.push(prog);
                }
            } else {
                programArray = programArray.filter(prog => prog !== 'TF');
            }
        }

        programArray.sort();

        let progList = programArray.map(i => {
            return i; // === 'FS' ? 'SNAP' : i === 'TF' ? 'TANF' : 'Healthcare';
        });

        let progFullNameList = programArray.map(i => {
            return i === 'FS' ? 'SNAP' : i === 'TF' ? 'TANF' : 'Healthcare';
        });

        AppContext.model.setValue('programArray', progList);
        var programString = progFullNameList.join(',');
        AppContext.model.setValue('programString', programString);
        //AppContext.model.setValue('programListSelected', programString);
        if (programArray.includes('MA')) {
            AppContext.model.setValue('nonhealth', false);
            AppContext.model.setValue('health', true);
        } else {
            AppContext.model.setValue('nonhealth', true);
            AppContext.model.setValue('health', false);
        }
        if (programArray.length === 0) {
            AppContext.model.setValue('common', false);
            AppContext.model.setValue('button1', true);
            AppContext.model.setValue('section1', false);
        }
    };

    defaultTick = program => {
        let list = AppContext.model.getValue('psel.applicant');
        for (let i in list) {
            if (list.hasOwnProperty(i)) {
                let applicant = list[i];
                let tick = [applicant.indvId + program];
                AppContext.model.setValue(applicant.indvId + program, tick);
            }
        }
    };

    openSection1 = () => {
        if (!(AppContext.model.getValue('programArray').length === 0)) {
            AppContext.model.setValue('program_required', false);
            AppContext.model.setValue('button1', false);
            AppContext.model.setValue('section1', true);

            if (AppContext.model.getValue('snap')) {
                AppContext.model.setValue('section2', true);
                AppContext.model.setValue('button3', true);
            } else {
                AppContext.model.setValue('common', true);
                AppContext.model.setValue('button2', true);
            }
        } else {
            AppContext.model.setValue('program_required', true);
        }
        if (document.getElementById('first_part') != null) {
            document.getElementById('first_part').classList.remove('panel-left-border-color');
        }
    };

    openSection3 = () => {
        AppContext.model.setValue('openSection3', true);
        let valcheck = this.validate('mandatory1');
        const monthlyIncome = parseInt(AppContext.model.getValue('pSel.exp.EG'));
        const liquidResource = parseInt(AppContext.model.getValue('pSel.exp.EH'));
        var migrant = AppContext.model.getValue('pSel.exp.EM');

        if (monthlyIncome && liquidResource && migrant && valcheck) {
            if (document.getElementById('submandatory1') != null) {
                document
                    .getElementById('submandatory1')
                    .classList.remove('panel-left-border-color');
            }
            if (
                (monthlyIncome < 150 && liquidResource <= 100) ||
                (migrant === 'Y' && liquidResource < 100)
            ) {
                AppContext.model.setValue('expedited', true);
                AppContext.model.setValue('section4', true);
                AppContext.model.setValue('common', true);
                AppContext.model.setValue('button2', true);
            } else {
                AppContext.model.setValue('expeditedResult', true);
                var date = new Date();
                var year = date.getFullYear();
                var month = date.toLocaleString('default', { month: 'long' });
                var monthYear = month + ' ' + year;
                AppContext.model.setValue('monthYear', monthYear);
            }
            AppContext.model.setValue('button3', false);
            AppContext.model.setValue('section3', true);
            AppContext.model.setValue('button4', true);
        }
        AppContext.model.setValue('openSection3', false);
    };

    openSection4 = () => {
        AppContext.model.setValue('openSection4', true);
        let valcheck = this.validate('mandatory2');
        const mortage = parseInt(AppContext.model.getValue('pSel.exp.ER'));
        const monthlyIncome = parseInt(AppContext.model.getValue('pSel.exp.EG'));
        const liquidResource = parseInt(AppContext.model.getValue('pSel.exp.EH'));
        var paysFor = AppContext.model.getValue('pSel.exp.paysFor');
        if (Array.isArray(paysFor) && paysFor.length) {
            AppContext.model.setValue('paysError', false);
        } else {
            AppContext.model.setValue('paysError', true);
        }

        if (mortage && Array.isArray(paysFor) && paysFor.length && valcheck) {
            if (document.getElementById('submandatory2') != null) {
                document
                    .getElementById('submandatory2')
                    .classList.remove('panel-left-border-color');
            }
            if (mortage > monthlyIncome + liquidResource) {
                AppContext.model.setValue('expedited', true);
            } else {
                AppContext.model.setValue('expeditedMessage', true);
            }

            AppContext.model.setValue('button4', false);
            AppContext.model.setValue('section4', true);
            AppContext.model.setValue('common', true);
            AppContext.model.setValue('button2', true);
        }
        AppContext.model.setValue('openSection4', false);
    };

    finalNext = async () => {
        console.log('finalNext');
        const valcheck = await this.validate('program_selection');
        var disabled = AppContext.model.getValue('pSel.disabled');
        //validation to check if all the selected programs are linked to atleast one indv
        let linkStatus = await this.prgmLinkCheck();
        console.log(linkStatus);
        //
        if (linkStatus) {
            try {
                AppContext.model.setValue('progarmSelError', false);
                if (disabled && valcheck) {
                    spinnerUtil.show();
                    let insertProgSel = await this.insertProgramSelection();
                    let sumarryData = await this.fetchSummaryData();
                    if (insertProgSel && sumarryData) {
                        this.props.closePanel('program_selection');
                        document
                            .getElementById(`program_selection_exclamation`)
                            .classList.add('success');
                        this.props.openPanel('review');
                        this.props.enablePanel('review');
                        let progress = AppContext.model.getValue('appReg.Progress');
                        if (!progress || progress < 90)
                            AppContext.model.setValue('appReg.Progress', '90');
                    }
                    spinnerUtil.hide();
                }
                let listOfProgramsSelected = AppContext.model.getValue('programString');
                AppContext.model.setValue('programListSelected', listOfProgramsSelected);
                let specialCirumstancesFlag = AppContext.model.getValue('pSel.disabled');
                AppContext.model.setValue(
                    'appOverview.special_circumstances',
                    specialCirumstancesFlag === 'N' ? 'No' : 'Yes',
                );
                AppContext.model.setValue('appOverview.special_circumstancesCheck', 'true');
                return true;
            } catch (ex) {
                console.log(ex);
                return false;
            }
        }
    };

    async insertProgramSelection() {
        let selPrograms = AppContext.model.getValue('pSel.selPrograms');
        let prioAppStatus = AppContext.model.getValue('pSel.priorityAppStatus');
        let disabled = AppContext.model.getValue('pSel.disabled');
        let expResp = {
            questCd: '',
            response: '',
        };

        let expRespTotal = [];
        if (AppContext.model.getValue('pSel.exp.EG')) {
            expResp.questCd = 'EG';
            expResp.response = AppContext.model.getValue('pSel.exp.EG');
            expRespTotal.push({ ...expResp });
        } else {
            expResp.questCd = 'EG';
            expResp.response = '';
            expRespTotal.push({ ...expResp });
        }

        if (AppContext.model.getValue('pSel.exp.EH')) {
            expResp.questCd = 'EH';
            expResp.response = AppContext.model.getValue('pSel.exp.EH');
            expRespTotal.push({ ...expResp });
        }

        if (AppContext.model.getValue('pSel.exp.EM')) {
            expResp.questCd = 'EM';
            expResp.response = AppContext.model.getValue('pSel.exp.EM');
            expRespTotal.push({ ...expResp });
        }

        if (AppContext.model.getValue('pSel.exp.ER')) {
            expResp.questCd = 'ER';
            expResp.response = AppContext.model.getValue('pSel.exp.ER');
            expRespTotal.push({ ...expResp });
        } else {
            expResp.questCd = 'ER';
            expResp.response = 'N';
            expRespTotal.push({ ...expResp });
        }
        if (AppContext.model.getValue('expedited')) {
            expResp.questCd = 'ET';
            expResp.response = 'Y';
            expRespTotal.push({ ...expResp });
        } else {
            expResp.questCd = 'ET';
            expResp.response = 'N';
            expRespTotal.push({ ...expResp });
        }

        if (
            AppContext.model.getValue('pSel.exp.paysFor') &&
            AppContext.model.getValue('pSel.exp.paysFor').includes('HT')
        ) {
            expResp.questCd = 'HT';
            expResp.response = 'Y';
            expRespTotal.push({ ...expResp });
        } else {
            expResp.questCd = 'HT';
            expResp.response = 'N';
            expRespTotal.push({ ...expResp });
        }

        if (
            AppContext.model.getValue('pSel.exp.paysFor') &&
            AppContext.model.getValue('pSel.exp.paysFor').includes('NE')
        ) {
            expResp.questCd = 'NE';
            expResp.response = 'Y';
            expRespTotal.push({ ...expResp });
        } else {
            expResp.questCd = 'NE';
            expResp.response = 'N';
            expRespTotal.push({ ...expResp });
        }
        if (
            AppContext.model.getValue('pSel.exp.paysFor') &&
            AppContext.model.getValue('pSel.exp.paysFor').includes('WS')
        ) {
            expResp.questCd = 'WS';
            expResp.response = 'Y';
            expRespTotal.push({ ...expResp });
        } else {
            expResp.questCd = 'WS';
            expResp.response = 'N';
            expRespTotal.push({ ...expResp });
        }
        if (
            AppContext.model.getValue('pSel.exp.paysFor') &&
            AppContext.model.getValue('pSel.exp.paysFor').includes('TP')
        ) {
            expResp.questCd = 'TP';
            expResp.response = 'Y';
            expRespTotal.push({ ...expResp });
        } else {
            expResp.questCd = 'TP';
            expResp.response = 'N';
            expRespTotal.push({ ...expResp });
        }
        if (
            AppContext.model.getValue('pSel.exp.paysFor') &&
            AppContext.model.getValue('pSel.exp.paysFor').includes('GR')
        ) {
            expResp.questCd = 'GR';
            expResp.response = 'Y';
            expRespTotal.push({ ...expResp });
        } else {
            expResp.questCd = 'GR';
            expResp.response = 'N';
            expRespTotal.push({ ...expResp });
        }

        let appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
        let pselCustomJson = [];
        let pselOverviewList = '';
        //Program Selection Custom Json for Individual Program and Retro coverage List
        try {
            let indvIds = AppContext.model.getValue('psel.applicant');
            let indvPrgmCd = [];
            let customPselOverView = [];
            // let currentDate =
            //     new Date().getMonth() +
            //     1 +
            //     '/' +
            //     new Date().getDate() +
            //     '/' +
            //     new Date().getFullYear();
            // AppContext.model.setValue('psel.SendingRequestDate', currentDate);
            indvIds.map(temp => {
                let helthCareTick = AppContext.model.getValue(temp.indvId + 'MA');
                let tanfTick = AppContext.model.getValue(temp.indvId + 'TF');
                let snapTick = AppContext.model.getValue(temp.indvId + 'FS');
                if (helthCareTick !== undefined && helthCareTick.length > 0) {
                    indvPrgmCd.push({
                        indvId: temp.indvId,
                        programCode: 'MA',
                    });
                    customPselOverView.push({
                        program: 'MA',
                        applicantNames: temp.applicant
                            .split(' ')
                            .slice(0, -1)
                            .join(' '),
                        age: temp.age,
                    });
                }
                if (snapTick !== undefined && snapTick.length > 0) {
                    indvPrgmCd.push({
                        indvId: temp.indvId,
                        programCode: 'FS',
                    });
                    customPselOverView.push({
                        program: 'FS',
                        applicantNames: temp.applicant
                            .split(' ')
                            .slice(0, -1)
                            .join(' '),
                        age: temp.age,
                    });
                }
                if (tanfTick !== undefined && tanfTick.length > 0) {
                    indvPrgmCd.push({
                        indvId: temp.indvId,
                        programCode: 'TF',
                    });
                    customPselOverView.push({
                        program: 'TF',
                        applicantNames: temp.applicant
                            .split(' ')
                            .slice(0, -1)
                            .join(' '),
                        age: temp.age,
                    });
                }
            });

            let indvRetroCvg = [];
            indvIds.map(temp => {
                let retroFlag = AppContext.model.getValue('retro');
                if (retroFlag) {
                    retroFlag.map(i => {
                        if (i === temp.indvId + 'P1') {
                            indvRetroCvg.push({
                                indvId: temp.indvId,
                                priorMedicaidCd: 'P1',
                            });
                        }
                        if (i === temp.indvId + 'P2') {
                            indvRetroCvg.push({
                                indvId: temp.indvId,
                                priorMedicaidCd: 'P2',
                            });
                        }
                        if (i === temp.indvId + 'P3') {
                            indvRetroCvg.push({
                                indvId: temp.indvId,
                                priorMedicaidCd: 'P3',
                            });
                        }
                    });
                }
            });

            //Check if snap is checked and remove snapmodels from the indvPrgmCd && customPselOverView array
            let finalNextProgramArray = AppContext.model.getValue('programArray');
            if (!finalNextProgramArray.includes('MA')) {
                indvPrgmCd = indvPrgmCd.filter(indvPrgmCd => indvPrgmCd.programCode !== 'MA');
                customPselOverView = customPselOverView.filter(
                    customPselOverView => customPselOverView.program !== 'MA',
                );
            }
            if (!finalNextProgramArray.includes('FS')) {
                indvPrgmCd = indvPrgmCd.filter(indvPrgmCd => indvPrgmCd.programCode !== 'FS');
                customPselOverView = customPselOverView.filter(
                    customPselOverView => customPselOverView.program !== 'FS',
                );
            }
            if (!finalNextProgramArray.includes('TF')) {
                indvPrgmCd = indvPrgmCd.filter(indvPrgmCd => indvPrgmCd.programCode !== 'TF');
                customPselOverView = customPselOverView.filter(
                    customPselOverView => customPselOverView.program !== 'TF',
                );
            }

            let pselApplicant = AppContext.model.getValue('psel.applicant');
            const indvPrgmCdGroup = _(indvPrgmCd)
                .groupBy('indvId')
                .map((temp, indvId) => {
                    const prgmCdList = temp.map(temp => temp.programCode);
                    return { indvId, prgmCdList };
                })
                .value();
            // AppContext.model.setValue('psel.applicant.indvprgrmCd', indvPrgmCdGroup);
            pselApplicant['indvprgrmCd'] = indvPrgmCdGroup;

            const retroCvgGroup = _(indvRetroCvg)
                .groupBy('indvId')
                .map((temp, indvId) => {
                    const retrolist = temp.map(temp => temp.priorMedicaidCd);
                    return { indvId, retrolist };
                })
                .value();
            //AppContext.model.setValue('psel.applicant.retroCvrg', retroCvgGroup);
            pselApplicant['retroCvrg'] = retroCvgGroup;
            AppContext.model.setValue('psel.applicant', pselApplicant);

            const pselOverviewgrp = _(customPselOverView)
                .groupBy('program')
                .map((temp, program) => {
                    const applicantName = temp.map(temp => temp.applicantNames);
                    const applicantAge = temp.map(temp => temp.age);
                    return { program, applicantName, applicantAge };
                })
                .value();

            let indvCvrgDate = [];
            indvIds.map(temp => {
                let dateSelcted = AppContext.model.getValue(temp.indvId + 'DATE');
                if (dateSelcted !== undefined && dateSelcted.length > 0) {
                    indvCvrgDate.push({
                        indvId: temp.indvId,
                        cvrgDate: dateSelcted,
                    });
                }
            });

            let indvPrgmList = AppContext.model.getValue('psel.applicant.indvprgrmCd');
            let indvRetroList = AppContext.model.getValue('psel.applicant.retroCvrg');
            pselOverviewList = pselOverviewgrp;
            indvIds.map(test => {
                let sample = test.indvId.toString();
                var prgmString = '';
                var retroString = '';
                let customCvrgDate = '';
                indvCvrgDate.map(i => {
                    if (test.indvId === i.indvId) {
                        customCvrgDate = new Date(i.cvrgDate);
                    }
                });
                customCvrgDate =
                    customCvrgDate.getMonth() +
                    1 +
                    '/' +
                    customCvrgDate.getDate() +
                    '/' +
                    customCvrgDate.getFullYear();
                indvPrgmList.map(tmp => {
                    if (tmp.indvId === sample) {
                        prgmString = tmp.prgmCdList.join(',');
                    }
                });
                indvRetroList.map(i => {
                    if (sample === i.indvId) {
                        retroString = i.retrolist.join(',');
                    }
                });
                pselCustomJson.push({
                    appNum: appNum,
                    indvId: test.indvId,
                    programCd: [prgmString],
                    priorMedicaidCd: [retroString],
                    requestDt: customCvrgDate,
                });
            });
        } catch (ex) {
            console.log('exception : ' + ex);
            return false;
        }
        //

        let md5Model = {
            programCd: selPrograms,
            priorityAppStatus: prioAppStatus,
            abdCheckSw: disabled,
            arExpScreenRespDtoList: expRespTotal,
            pselJson: pselCustomJson,
        };
        AppContext.model.setValue('appreg.panel.program_selection', md5Model);

        if (md5('program_selection')) {
            try {
                let arAppProgramResp = basicAxiosInterceptor({
                    method: 'post',
                    url: AppContext.config.insertArAppProgram,
                    data: {
                        appNum: appNum,
                        programCd: selPrograms,
                        requestDt: new Date().toLocaleString().replace(/,/g, ''),
                    },
                });
            } catch (ex) {
                console.log(ex);
                return false;
            }

            try {
                let arAppProgramResp = basicAxiosInterceptor({
                    method: 'post',
                    url: AppContext.config.insertPriorityAppStatus,
                    data: {
                        priorityAppStatus: prioAppStatus,
                        abdCheckSw: disabled,
                        appNum: appNum,
                    },
                });
            } catch (ex) {
                console.log(ex);
                return false;
            }

            if (AppContext.model.getValue('pSel.selPrograms').includes('FS')) {
                try {
                    let arAppProgramResp = basicAxiosInterceptor({
                        method: 'post',
                        url: AppContext.config.insertArExpResp,
                        data: {
                            arExpScreenRespDtoList: expRespTotal,
                            appNum: appNum,
                        },
                    });
                } catch (ex) {
                    console.log(ex);
                    return false;
                }
            }

            try {
                let arAppProgramResp = await basicAxiosInterceptor({
                    method: 'post',
                    url: AppContext.config.insertArAppProgramIndv,
                    data: pselCustomJson,
                });
            } catch (ex) {
                console.log(ex);
                return false;
            }
        }

        //Models setting for Review Panel Start
        let appOverview = AppContext.model.getValue('appOverview')
            ? AppContext.model.getValue('appOverview')
            : {};
        //AppContext.model.setValue('appOverview.programList', pselOverviewList);
        appOverview['programList'] = pselOverviewList;
        let priority_app_status = AppContext.model.getValue('pSel.priorityAppStatus');
        if (priority_app_status) {
            var prior_app_sts = priority_app_status.join(', ');
            //AppContext.model.setValue('appOverview.priority_status', prior_app_sts);
            appOverview['priority_status'] = prior_app_sts;
        }
        let expedited_snap = AppContext.model.getValue('expedited');
        // AppContext.model.setValue(
        //     'appOverview.expedited_snap',
        //     expedited_snap === true ? 'Yes' : 'No',
        // );
        appOverview['expedited_snap'] = expedited_snap === true ? 'Yes' : 'No';
        AppContext.model.setValue('appOverview', appOverview);
        AppContext.model.setValue(
            'appOverview.expedited_snap',
            expedited_snap === true ? 'Yes' : 'No',
        );
        AppContext.model.setValue('appOverview.priority_statusCheck', 'true');
        AppContext.model.setValue('appOverview.expedited_snapCheck', 'true');
        //Models setting for Review Panel end
        return true;
    }

    async prgmLinkCheck() {
        try {
            let output = AppContext.model.getValue('programArray').map(item => {
                let res = AppContext.model.getValue('psel.applicant').map(temp => {
                    let MA = '';
                    let healthProgTick = AppContext.model.getValue(temp.indvId + 'MA');
                    let tanfProgTick = AppContext.model.getValue(temp.indvId + 'TF');
                    let snapProgTick = AppContext.model.getValue(temp.indvId + 'FS');
                    if (healthProgTick !== undefined && healthProgTick.length > 0) {
                        MA = MA + 'MA';
                    }
                    if (snapProgTick !== undefined && snapProgTick.length > 0) {
                        MA = MA + 'FS';
                    }
                    if (tanfProgTick !== undefined && tanfProgTick.length > 0) {
                        MA = MA + 'TF';
                    }
                    return MA;
                });
                return res.some(i => i.includes(item));
            });
            console.log(output);
            if (output.includes(false)) {
                AppContext.model.setValue('progarmSelError', true);
                AppContext.model.setValue('progarmSelErrorMsg', errorMessages.GANEW553);
                document
                    .getElementById('program_selection_panel')
                    .scrollIntoView({ behavior: 'smooth', block: 'start', inline: 'end' });
                return false;
            } else {
                return true;
            }
        } catch (ex) {
            console.log('exception : selectedPrgms' + ex);
            return false;
        }
    }

    async validate(id) {
        const node = document.getElementById(id);
        const childNodes = node.querySelectorAll('[model]');
        _.forEach(childNodes, function(childnode) {
            if (childnode.getAttribute('model') !== undefined) {
                if (AppContext.model.getValue(childnode.getAttribute('model')) === undefined) {
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

    coverageDatePicker = (columnData, rowData) => {
        let tick = rowData.indvId + 'DATE';
        let todaysDate = new Date().toLocaleDateString();
        //        new Date().toLocaleString().replace(',', ' ');
        let dateValue = AppContext.model.getValue(tick);
        if (dateValue === undefined) {
            AppContext.model.setValue(tick, todaysDate);
        }
        return (
            <UX
                col-width="10"
                id="coverageDate"
                type="date"
                model={tick}
                value={tick}
                maxDate="today"
                minDate="1990-01-01"></UX>
        );
    };

    snapBox = (columnData, rowData) => {
        let tick = rowData.indvId + 'FS';
        if (AppContext.model.getValue('psel.ReceivedRequestDate') === 'NO') {
            this.defaultTick('FS');
        }
        // let indVprogramCode = rowData.indvId + 'FS';

        return (
            <UX col-width="5" id="snapBox" type="checkboxGroup" vertical-align="true" model={tick}>
                <UX id="snaptick" text="" type="checkbox" value={tick} />
            </UX>
        );
    };

    healthBox = (columnData, rowData) => {
        let tick = rowData.indvId + 'MA';
        if (AppContext.model.getValue('psel.ReceivedRequestDate') === 'NO') {
            this.defaultTick('MA');
        }
        // let indVprogramCode = rowData.indvId + 'MA';

        return (
            <UX
                col-width="5"
                id="healthBox"
                type="checkboxGroup"
                vertical-align="true"
                model={tick}>
                <UX
                    id="healthtick"
                    text=""
                    type="checkbox"
                    change={e => this.healthBoxAction(columnData, rowData, e)}
                    value={tick}
                />
            </UX>
        );
    };

    healthBoxAction = (columnData, rowData, event) => {
        if (event.target.value.includes('MA')) {
            if (!event.target.checked) {
                document.getElementById(rowData.indvId + '1st').setAttribute('disabled', true);
                document.getElementById(rowData.indvId + '2nd').setAttribute('disabled', true);
                document.getElementById(rowData.indvId + '3rd').setAttribute('disabled', true);
                let retroList = AppContext.model.getValue('retro');
                if (retroList !== undefined) {
                    retroList = retroList.filter(i => !i.includes(rowData.indvId));
                    AppContext.model.setValue('retro', retroList);
                }
            } else {
                document.getElementById(rowData.indvId + '1st').removeAttribute('disabled');
                document.getElementById(rowData.indvId + '2nd').removeAttribute('disabled');
                document.getElementById(rowData.indvId + '3rd').removeAttribute('disabled');
                AppContext.model.setValue(rowData.indvId + 'DisplayFlag', false);
                rowData.displayFlag = false;
            }
        }
    };

    tanfBox = (columnData, rowData) => {
        let tick = rowData.indvId + 'TF';
        if (AppContext.model.getValue('psel.ReceivedRequestDate') === 'NO') {
            this.defaultTick('TF');
        }
        return (
            <UX col-width="5" id="tanfBox" type="checkboxGroup" vertical-align="true" model={tick}>
                <UX id="tanftick" text="" type="checkbox" value={tick} />
            </UX>
        );
    };

    retroCoverage = (columnData, rowData) => {
        /*
        var months = [
            'Jan',
            'Feb',
            'Mar',
            'Apr',
            'May',
            'Jun',
            'Jul',
            'Aug',
            'Sept',
            'Oct',
            'Nov',
            'Dec',
        ];
        var current = new Date().getMonth();
        
        var first;
        var second;
        var third;
        if (current === 0) {
            first = months[9];
            second = months[10];
            third = months[11];
        } else if (current === 1) {
            first = months[10];
            second = months[11];
            third = months[0];
        } else if (current === 2) {
            first = months[11];
            second = months[0];
            third = months[1];
        } else {
            first = months[current - 3];
            second = months[current - 2];
            third = months[current - 1];
        }
*/
        let displayFlag =
            AppContext.model.getValue(rowData.indvId + 'MA') === undefined ? true : false;

        return (
            <UX id="retroCustomBox" type="checkboxGroup" model="retro" width="40">
                <UX id="retro" type="checkboxGroup" vertical-align="true" model="retro">
                    <UX
                        id={rowData.indvId + '1st'}
                        disabled={displayFlag}
                        text="Prior Month 1"
                        type="checkbox"
                        value={rowData.indvId + 'P1'}
                    />
                    <UX
                        id={rowData.indvId + '2nd'}
                        disabled={displayFlag}
                        text="Prior Month 2"
                        type="checkbox"
                        value={rowData.indvId + 'P2'}
                    />
                    <UX
                        id={rowData.indvId + '3rd'}
                        disabled={displayFlag}
                        text="Prior Month 3"
                        type="checkbox"
                        value={rowData.indvId + 'P3'}
                    />
                </UX>
            </UX>
        );
    };

    phonesData = (str, phone) => {
        let finalStr = '(';
        for (var i = 0; i < str.length; i++) {
            if (i === 3) {
                finalStr = finalStr + ') ';
            }
            if (i === 6) {
                finalStr = finalStr + '-';
            }
            finalStr = finalStr + str[i];
        }
        if (phone.phoneExtn > 0) {
            finalStr = finalStr + ', Ext ' + phone.phoneExtn;
        }
        return finalStr;
    };

    async fetchSummaryData() {
        //fetching AuthRep Data
        let contRespFlg = this.fetchContactFrSummary();
        let authRespFlg = this.fetchAuthRepFrSummary();
        if (contRespFlg && authRespFlg) {
            return true;
        }
        return false;
    }

    async fetchContactFrSummary() {
        try {
            let appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
            // let caseNum = AppContext.model.getValue('caseAssociate.id')
            //     ? AppContext.model.getValue('caseAssociate.id')
            //     : '0';
            let caseNum = '0';
            let response = await basicAxiosInterceptor({
                method: 'POST',
                url: AppContext.config.findContactDetailsForAppNum,
                data: {
                    appNum: appNum,
                    caseNum: caseNum,
                },
            });
            let contactMethod = response.data.data.arApplicationForAid.weekdayContMethSw;
            let appOverview = AppContext.model.getValue('appOverview.conInfo')
                ? AppContext.model.getValue('appOverview.conInfo')
                : {};
            if (contactMethod === 'P') {
                //AppContext.model.setValue('appOverview.conInfo.preferredMethod', 'Primary');
                appOverview['preferredMethod'] = 'Primary';
            } else if (contactMethod === 'U') {
                //AppContext.model.setValue('appOverview.conInfo.preferredMethod', 'Work');
                appOverview['preferredMethod'] = 'Work';
            } else if (contactMethod === 'A') {
                //AppContext.model.setValue('appOverview.conInfo.preferredMethod', 'Alternative');
                appOverview['preferredMethod'] = 'Alternative';
            }
            let contactTime = response.data.data.arApplicationForAid.weekdayContTime;
            if (contactTime === 'EAM') {
                // AppContext.model.setValue('appOverview.conInfo.preferredTime', 'Early Morning');
                appOverview['preferredTime'] = 'Early Morning';
            } else if (contactTime === 'LAM') {
                //AppContext.model.setValue('appOverview.conInfo.preferredTime', 'Late Morning');
                appOverview['preferredTime'] = 'Late Morning';
            } else if (contactTime === 'EAA') {
                // AppContext.model.setValue('appOverview.conInfo.preferredTime', 'Early Afternoon');
                appOverview['preferredTime'] = 'Early Afternoon';
            } else if (contactTime === 'LUH') {
                // AppContext.model.setValue('appOverview.conInfo.preferredTime', 'Lunch Hour');
                appOverview['preferredTime'] = 'Lunch Hour';
            } else if (contactTime === 'LAA') {
                // AppContext.model.setValue('appOverview.conInfo.preferredTime', 'Late Afternoon');
                appOverview['preferredTime'] = 'Late Afternoon';
            }

            response.data.data.arPhoneList.map(phone => {
                if (phone.phnTypeCd === 'PRP') {
                    let finalStr = this.phonesData(phone.phnNum, phone);

                    // AppContext.model.setValue('appOverview.conInfo.primary', finalStr);
                    // AppContext.model.setValue('appOverview.conInfo.primary2', phone.phnComments);
                    appOverview['primary'] = finalStr;
                    appOverview['primary2'] = phone.phnComments;
                } else if (phone.phnTypeCd === 'WOP') {
                    let finalStr = this.phonesData(phone.phnNum, phone);

                    // AppContext.model.setValue('appOverview.conInfo.work', finalStr);
                    // AppContext.model.setValue('appOverview.conInfo.work2', phone.phnComments);
                    appOverview['work'] = finalStr;
                    appOverview['work2'] = phone.phnComments;
                    AppContext.model.setValue('appOverview.conInfo.primary', finalStr);
                    AppContext.model.setValue('appOverview.conInfo.primary2', phone.phnComments);
                    AppContext.model.setValue('appOverview.conInfo.primary2Check', 'true');
                } else if (phone.phnTypeCd === 'WOP') {
                    let finalStr = this.phonesData(phone.phnNum, phone);

                    AppContext.model.setValue('appOverview.conInfo.work', finalStr);
                    AppContext.model.setValue('appOverview.conInfo.work2', phone.phnComments);
                    AppContext.model.setValue('appOverview.conInfo.work2Check', 'true');
                }
                if (phone.phnTypeCd === 'ALP') {
                    let finalStr = this.phonesData(phone.phnNum, phone);

                    // AppContext.model.setValue('appOverview.conInfo.alternative', finalStr);
                    // AppContext.model.setValue(
                    //     'appOverview.conInfo.alternative2',
                    //     phone.phnComments,
                    // );
                    appOverview['alternative'] = finalStr;
                    appOverview['alternative2'] = phone.phnComments;
                    AppContext.model.setValue('appOverview.conInfo.alternative', finalStr);
                    AppContext.model.setValue(
                        'appOverview.conInfo.alternative2',
                        phone.phnComments,
                    );
                    AppContext.model.setValue('appOverview.conInfo.alternative2Check', 'true');
                }
            });
            let emailID = {
                email: '',
                comments: '',
            };
            let emailData = [];
            response.data.data.arEmailList.map(emailD => {
                emailID.email = emailD.email;
                emailID.comments = emailD.emailComments;
                // emailData.push(emailD.email + '-' + emailD.emailComments);
                emailData.push({ ...emailID });
            });
            let finalEmail = emailData.reverse();
            //AppContext.model.setValue('appOverview.conInfo.email', finalEmail);
            appOverview['email'] = finalEmail;

            let addressDetails = response.data.data.arAppAddrPA;
            if (addressDetails) {
                // AppContext.model.setValue(
                //     'appOverview.conInfo.resAddress',
                //     addressDetails.addrLine,
                // );
                // AppContext.model.setValue(
                //     'appOverview.conInfo.resAddress2',
                //     addressDetails.addrCity +
                //         ', ' +
                //         '\n' +
                //         addressDetails.addrStateCd +
                //         '\n' +
                //         addressDetails.addrZip5,
                // );
                appOverview['resAddress'] = addressDetails.addrLine;
                appOverview['resAddress2'] =
                    addressDetails.addrCity +
                    ', ' +
                    '\n' +
                    addressDetails.addrStateCd +
                    '\n' +
                    addressDetails.addrZip5;

                let county = Util.getRefTableDescriptionByCode(
                    'COUNTY',
                    addressDetails.addrCountyCd,
                );
                // AppContext.model.setValue('appOverview.conInfo.resAddress3', county);
                appOverview['resAddress3'] = county;
                AppContext.model.setValue('appOverview.conInfo.resAddress3', county);
                AppContext.model.setValue('appOverview.conInfo.resAddress2Check', 'true');
                AppContext.model.setValue('appOverview.conInfo.resAddress3Check', 'true');
            }
            let mailAddressDetails = response.data.data.arAppAddrMA;

            if (mailAddressDetails) {
                // AppContext.model.setValue(
                //     'appOverview.conInfo.mailAddress',
                //     mailAddressDetails.addrLine,
                // );
                // AppContext.model.setValue(
                //     'appOverview.conInfo.mailAddress2',
                //     mailAddressDetails.addrCity +
                //         ', ' +
                //         '\n' +
                //         mailAddressDetails.addrStateCd +
                //         '\n' +
                //         mailAddressDetails.addrZip5,
                // );

                let mailCounty = Util.getRefTableDescriptionByCode(
                    'COUNTY',
                    mailAddressDetails.addrCountyCd,
                );
                // AppContext.model.setValue('appOverview.conInfo.mailAddress3', mailCounty);
                appOverview['mailAddress'] = mailAddressDetails.addrLine;
                appOverview['mailAddress2'] =
                    mailAddressDetails.addrCity +
                    ', ' +
                    '\n' +
                    mailAddressDetails.addrStateCd +
                    '\n' +
                    mailAddressDetails.addrZip5;
                appOverview['mailAddress3'] = mailCounty;
                AppContext.model.setValue('appOverview.conInfo.mailAddress2Check', 'true');
                AppContext.model.setValue('appOverview.conInfo.mailAddress3Check', 'true');
            }
            AppContext.model.setValue('appOverview.conInfo', appOverview);
            return true;
        } catch (ex) {
            console.log(ex);
            return false;
        }
    }

    async fetchAuthRepFrSummary() {
        try {
            let appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
            // let caseNum = AppContext.model.getValue('caseAssociate.id')
            //     ? AppContext.model.getValue('caseAssociate.id')
            //     : '0';
            let caseNum = '0';
            let response = await basicAxiosInterceptor({
                method: 'POST',
                url: AppContext.config.findAuthRepDetailsForAppNum,
                data: {
                    appNum: appNum,
                    caseNum: caseNum,
                },
            });
            let appOverview = AppContext.model.getValue('appOverview.authRep')
                ? AppContext.model.getValue('appOverview.authRep')
                : {};
            // AppContext.model.setValue(
            //     'appOverview.authRep.useAuthRep',
            //     response.data.data.arApplicationForAid.authRepSw === 'Y' ? 'Yes' : 'No',
            // );
            appOverview['useAuthRep'] =
                response.data.data.arApplicationForAid.authRepSw === 'Y' ? 'Yes' : 'No';
            // AppContext.model.setValue(
            //     'appOverview.authRep.name',
            //     response.data.data.arApplicationForAid.authrepFirstName +
            //         ' ' +
            //         response.data.data.arApplicationForAid.authrepLastName,
            // );
            appOverview['name'] =
                response.data.data.arApplicationForAid.authrepFirstName +
                ' ' +
                response.data.data.arApplicationForAid.authrepLastName;

            let relationship = Util.getRefTableDescriptionByCode(
                'AUTHREPRELATIONTOINDIVIDUAL',
                response.data.data.arApplicationForAid.relCd,
            );

            // AppContext.model.setValue('appOverview.authRep.relationship', relationship);
            appOverview['relationship'] = relationship;

            let address = response.data.data.arAppAddrAA;
            if (address) {
                // AppContext.model.setValue('appOverview.authRep.address', address.addrLine);
                // AppContext.model.setValue(
                //     'appOverview.authRep.address2',
                //     address.addrCity
                //         ? address.addrCity
                //         : '' + ', ' + '\n' + address.addrStateCd
                //         ? address.addrStateCd
                //         : '' + '\n' + address.addrZip5
                //         ? address.addrZip5
                //         : '',
                // );
                appOverview['address'] = address.addrLine;
                appOverview['address2'] = address.addrCity
                    ? address.addrCity
                    : '' + ', ' + '\n' + address.addrStateCd
                    ? address.addrStateCd
                    : '' + '\n' + address.addrZip5
                    ? address.addrZip5
                    : '';
            }

            response.data.data.arPhoneList.map(phone => {
                if (phone.phnTypeCd === 'PRP') {
                    let finalStr = this.phonesData(phone.phnNum, phone);

                    // AppContext.model.setValue('appOverview.authRep.primary', finalStr);
                    // AppContext.model.setValue('appOverview.authRep.primary2', phone.phnComments);
                    appOverview['primary'] = finalStr;
                    appOverview['primary2'] = phone.phnComments;
                } else if (phone.phnTypeCd === 'WOP') {
                    let finalStr = this.phonesData(phone.phnNum, phone);

                    // AppContext.model.setValue('appOverview.authRep.work', finalStr);
                    // AppContext.model.setValue(
                    //     'appOverview.authRep.work2',
                    //     phone.phnComments ? phone.phnComments : '',
                    // );
                    appOverview['work'] = finalStr;
                    appOverview['work2'] = phone.phnComments;
                }
                if (phone.phnTypeCd === 'ALP') {
                    let finalStr = this.phonesData(phone.phnNum, phone);

                    // AppContext.model.setValue('appOverview.authRep.alternative', finalStr);
                    // AppContext.model.setValue(
                    //     'appOverview.authRep.alternative2',
                    //     phone.phnComments,
                    // );
                    appOverview['alternative'] = finalStr;
                    appOverview['alternative2'] = phone.phnComments;
                }
            });
            let emailData1 = [];
            let emailID = {
                email: '',
                comments: '',
            };
            response.data.data.arEmailList.map(emailD => {
                emailID.email = emailD.email;
                emailID.comments = emailD.emailComments;
                //emailData1.push(emailD.email + '-' + emailD.emailComments);
                emailData1.push({ ...emailID });
            });
            //let finalEmail1 = emailData1.reverse();
            //AppContext.model.setValue('appOverview.authRep.email', emailData1);
            appOverview['email'] = emailData1;
            AppContext.model.setValue('appOverview.authRep', appOverview);

            return true;
        } catch (ex) {
            console.log(ex);
            return false;
        }
    }

    renderComponent() {
        return Template.bind(this).apply(this);
    }
}

export default ProgramSelection;
