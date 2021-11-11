import React from 'react';
import { UX } from '@d-lift/uxcomponents';
import Template from './Application.rt';
import { PageConfig, UXPage, AppContext } from '@d-lift/core';
import navigationUtills from '@/ScreenFlowUtils/NavigationUtills';
import workflowUtills from '@/ScreenFlowUtils/WorkFlowUtills';
import {} from '@/Validations/customValidations.js';
import errorMessages from '../../../../public/validation/commonValidation.json';
import './Application.css';
import RefUtil from '../../../Utills/RefUtil';

@PageConfig({
    ContextRoot: 'ApplicationRegistration',
    PageName: 'Application',
    Path: ['/application', '/ARRAP'],
    Template: Template,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Register Application',
})
class Application extends UXPage {
    onPageLoad() {
        AppContext.model.setValue('appErrors', {
            errorField: [],
            isError: false,
        });

        AppContext.model.setValue('Dyna', {
            dynaElementPhoneList: [
                {
                    name: 'phnTypeCd',
                    label: 'phoneType',
                    type: 'select',
                    fieldRequired: true,
                    validation: null,
                    refTable: 'WEEKDAYCONTACTMETHOD',
                    defaultOption: 'true',
                    defaultOptionValue: "{''}",
                    labelRequiredClassIf: true,
                },
                {
                    name: 'phnNum',
                    label: 'phoneNumber',
                    type: 'text',
                    fieldRequired: true,
                    validation: null,
                    inputMode: 'numeric',
                    maxLength: '10',
                    labelRequiredClassIf: true,
                },
                {
                    name: 'phoneExtn',
                    label: 'extension',
                    type: 'text',
                    fieldRequired: false,
                    validation: null,
                    inputMode: 'numeric',
                    maxLength: '6',
                },
                {
                    name: 'phnComments',
                    label: 'comments',
                    type: 'text',
                    fieldRequired: false,
                    validation: null,
                },
            ],
            dynaElementContactList: [
                {
                    name: 'email',
                    label: 'email',
                    type: 'text',
                    fieldRequired: true,
                    validation: null,
                },
                {
                    name: 'emailComments',
                    label: 'comments',
                    type: 'text',
                    fieldRequired: false,
                    validation: null,
                },
            ],
        });

        AppContext.model.setValue('application', {
            selectedPrograms: [],
        });

        AppContext.model.setValue('ARRAP', {
            ArApplicationForAid: {
                dateTimeRegistered: new Date().toLocaleString().replace(',', ' '),
                appRecvdDt: '',
                appSignedSw: '',
                appModeCd: '',
                teleConvRefNum: '',
                ucmTranNum: '',
                authRepSw: '',
                weekdayContMethSw: '',
                weekdayContTime: '',
                wicDisclosureSw: '',
            },
            DcIndv: {
                firstName: '',
                midName: '',
                lastName: '',
                sufxName: '',
                organizationName: '',
                organizationId: '',
                primaryLang: '0',
                otherLanguage: '',
                disabilityAccom: '',
                communicationAsst: '',
                otherAccomodation: '',
                interpreterSw: '',
                authRepSw: '',
            },
            ArAppProgram: {
                programCd: [],
            },

            contactCollection: [
                {
                    email: 'xyz@test.com',
                    emailComments: 'test1',
                },
                {
                    email: 'abc@test.com',
                    emailComments: 'test2',
                },
            ],

            phoneCollection: [
                {
                    phnTypeCd: 'A',
                    phnNum: '8124244669',
                    phoneExtn: '12345',
                    phnComments: 'testabc',
                },
                {
                    phnTypeCd: 'P',
                    phnNum: '8200306652',
                    phoneExtn: '54354',
                    phnComments: 'testxyz',
                },
            ],
        });

        //  AppContext.model.setValue('application', applications);
        // AppContext.model.setValue('appErrors', appErrors);
    }

    checkSourceTanfFoodStamps(program, isError) {
        if (program === 'TF') {
            if (
                !this.state.model.appErrors.errorField.some(err => err === errorMessages.GANEW510)
            ) {
                this.state.model.appErrors.errorField.push(errorMessages.GANEW510);
            }
        } else if (program === 'FS') {
            if (
                !this.state.model.appErrors.errorField.some(err => err === errorMessages.GANEW511)
            ) {
                this.state.model.appErrors.errorField.push(errorMessages.GANEW511);
            }
        }
        if (!isError) {
            let errorField = [];
            if (program === 'TF') {
                errorField = this.state.model.appErrors.errorField.filter(err => {
                    return err !== errorMessages.GANEW510;
                });
            } else if (program === 'FS') {
                errorField = this.state.model.appErrors.errorField.filter(err => {
                    return err !== errorMessages.GANEW511;
                });
            }
            this.state.model.appErrors.errorField = errorField;
            if (this.state.model.appErrors.errorField.length !== 0) {
                isError = true;
            }
        }
        this.state.model.appErrors.isError = isError;
        if (this.state.model.appErrors.errorField.length !== 0) {
            AppContext.pagedetails
                .getPageContext()
                .stateHandler('msgsList', this.state.model.appErrors.errorField);
            AppContext.pagedetails
                .getPageContext()
                .stateHandler('errorMsg', this.state.model.appErrors.isError);
        } else {
            AppContext.pagedetails
                .getPageContext()
                .stateHandler('errorMsg', this.state.model.appErrors.isError);
        }
    }

    programCombination = isError => {
        if (!this.state.model.appErrors.errorField.some(err => err === errorMessages.GANEW600)) {
            this.state.model.appErrors.errorField.push(errorMessages.GANEW600);
        }
        if (!isError) {
            const errorField = this.state.model.appErrors.errorField.filter(err => {
                return err !== errorMessages.GANEW600;
            });
            this.state.model.appErrors.errorField = errorField;
            if (this.state.model.appErrors.errorField.length !== 0) {
                isError = true;
            }
        }
        this.state.model.appErrors.isError = isError;
        if (this.state.model.appErrors.errorField.length !== 0) {
            AppContext.pagedetails
                .getPageContext()
                .stateHandler('msgsList', this.state.model.appErrors.errorField);
            AppContext.pagedetails
                .getPageContext()
                .stateHandler('errorMsg', this.state.model.appErrors.isError);
        } else {
            AppContext.pagedetails
                .getPageContext()
                .stateHandler('errorMsg', this.state.model.appErrors.isError);
        }
    };

    incorrectProgramCombination = (isError, selectedPrograms, program) => {
        const incorrectCombinationMessage =
            'Cannot select ' +
            program.target.id +
            ' at the same time as ' +
            selectedPrograms.map(item => item) +
            '. Only Medicaid, SNAP, and TANF programs may be selected at the same time.';
        if (
            !this.state.model.appErrors.errorField.some(err =>
                err.includes(
                    '. Only Medicaid, SNAP, and TANF programs may be selected at the same time.',
                ),
            )
        ) {
            this.state.model.appErrors.errorField.push(incorrectCombinationMessage);
        } else {
            const errors = this.state.model.appErrors.errorField.filter(msg => {
                return !msg.includes(
                    '. Only Medicaid, SNAP, and TANF programs may be selected at the same time.',
                );
            });
            this.state.model.appErrors.errorField = errors;
            this.state.model.appErrors.errorField.push(incorrectCombinationMessage);
        }
        if (!isError) {
            const errorField = this.state.model.appErrors.errorField.filter(err => {
                return !err.includes(
                    '. Only Medicaid, SNAP, and TANF programs may be selected at the same time.',
                );
            });
            this.state.model.appErrors.errorField = errorField;
            if (this.state.model.appErrors.errorField.length !== 0) {
                isError = true;
            }
        }
        this.state.model.appErrors.isError = isError;
        if (this.state.model.appErrors.errorField.length !== 0) {
            AppContext.pagedetails
                .getPageContext()
                .stateHandler('msgsList', this.state.model.appErrors.errorField);
            AppContext.pagedetails
                .getPageContext()
                .stateHandler('errorMsg', this.state.model.appErrors.isError);
        } else {
            AppContext.pagedetails
                .getPageContext()
                .stateHandler('errorMsg', this.state.model.appErrors.isError);
        }
    };

    checkSourceForAll = program => {
        const errorField = this.state.model.appErrors.errorField.filter(err => {
            return err !== errorMessages.GANEW510 || err !== errorMessages.GANEW511;
        });
        this.state.model.appErrors.errorField = errorField;
        this.checkSourceTanfFoodStamps(program.target.value, false);
        if (program.target.checked) {
            this.state.model.application.selectedPrograms.push(program.target.value);
        } else if (program.target.checked === false) {
            const sp = this.state.model.application.selectedPrograms.filter(
                v => v !== program.target.value,
            );
            this.state.model.application.selectedPrograms = sp;
        }
        if (
            program.target.checked &&
            this.state.model.application.selectedPrograms.some(
                p => p === 'TF' || p === 'SP' || p === 'MA',
            ) &&
            (this.state.model.application.selectedPrograms.indexOf('CC') >= 0 ||
                this.state.model.application.selectedPrograms.indexOf('FS') >= 0)
        ) {
            this.incorrectProgramCombination(
                true,
                this.state.model.application.selectedPrograms,
                program,
            );
        } else if (
            !program.target.checked &&
            this.state.model.application.selectedPrograms &&
            this.state.model.application.selectedPrograms.some(
                p => p === 'TF' || p === 'SP' || p === 'MA',
            ) &&
            this.state.model.application.selectedPrograms.indexOf('CC') < 0 &&
            this.state.model.application.selectedPrograms.indexOf('FS') < 0
        ) {
            this.incorrectProgramCombination(
                false,
                this.state.model.application.selectedPrograms,
                program,
            );
        }
        if (
            (AppContext.model.getValue('ARRAP.ArApplicationForAid.appModeCd') === 'FFM' ||
                AppContext.model.getValue('ARRAP.ArApplicationForAid.appModeCd') === 'LIS') &&
            program
        ) {
            if (
                (program.target.type === 'select-one' || program.target.checked) &&
                (program.target.value === 'TF' ||
                    program.target.value === 'CC' ||
                    program.target.value === 'WC' ||
                    program.target.value === 'SP')
            ) {
                this.programCombination(true);
            } else {
                this.programCombination(false);
            }
        } else if (
            AppContext.model.getValue('ARRAP.ArApplicationForAid.appModeCd') === 'DTF' &&
            program
        ) {
            if (
                (program.target.type === 'select-one' || program.target.checked) &&
                (program.target.value === 'TF' ||
                    program.target.value === 'CC' ||
                    program.target.value === 'WC' ||
                    program.target.value === 'MA')
            ) {
                this.programCombination(true);
            } else {
                this.programCombination(false);
            }
        } else if (
            AppContext.model.getValue('ARRAP.ArApplicationForAid.appModeCd') === 'INP' &&
            program
        ) {
            if (
                (program.target.type === 'select-one' || program.target.checked) &&
                (program.target.value === 'TF' ||
                    program.target.value === 'SP' ||
                    program.target.value === 'MA')
            ) {
                this.programCombination(true);
            } else {
                this.programCombination(false);
            }
        } else if (
            AppContext.model.getValue('ARRAP.ArApplicationForAid.appModeCd') === 'SHI' &&
            program
        ) {
            if (
                (program.target.type === 'select-one' || program.target.checked) &&
                (program.target.value === 'SP' || program.target.value === 'WC')
            ) {
                this.programCombination(true);
            } else {
                this.programCombination(false);
            }
        } else if (
            AppContext.model.getValue('ARRAP.ArApplicationForAid.appModeCd') === 'DTF' &&
            program
        ) {
            if (
                (program.target.type === 'select-one' || program.target.checked) &&
                (program.target.value === 'SP' ||
                    program.target.value === 'WC' ||
                    program.target.value === 'CC' ||
                    program.target.value === 'MA')
            ) {
                if (this.state.model.application.selectedPrograms.some(p => p !== 'TF')) {
                    this.checkSourceTanfFoodStamps(program.target.value, true);
                    return;
                } else {
                    this.checkSourceTanfFoodStamps(program.target.value, false);
                }
                this.programCombination(true);
            } else {
                if (
                    !AppContext.model
                        .getValue('ARRAP.ArAppProgram.programCd')
                        .some(err => err === 'TF')
                ) {
                    if (
                        !this.state.model.appErrors.errorField.some(
                            err => err === errorMessages.GANEW510,
                        )
                    ) {
                        this.state.model.appErrors.errorField.push(errorMessages.GANEW510);
                    }
                    if (
                        this.state.model.appErrors.errorField.some(
                            err => err === errorMessages.GANEW510,
                        )
                    ) {
                        let errs = this.state.model.appErrors.errorField;
                        errs = this.state.model.appErrors.errorField.filter(msg => {
                            return msg !== errorMessages.GANEW511;
                        });
                        this.state.model.appErrors.errorField = errs;
                    }
                }
            }
            if (program.target.checked && program.target.value === 'TF') {
                this.checkSourceTanfFoodStamps(program.target.value, false);
                return;
            } else if (!program.target.checked && program.target.value === 'TF') {
                this.checkSourceTanfFoodStamps(program.target.value, true);
                return;
            }
            this.programCombination(false);
        } else if (
            AppContext.model.getValue('ARRAP.ArApplicationForAid.appModeCd') === 'EXP' &&
            program
        ) {
            if (
                (program.target.type === 'select-one' || program.target.checked) &&
                (program.target.value === 'SP' ||
                    program.target.value === 'WC' ||
                    program.target.value === 'CC' ||
                    program.target.value === 'TF')
            ) {
                this.programCombination(true);
            } else {
                this.programCombination(false);
            }
        }
        // else if (
        //     AppContext.model.getValue('ARRAP.ArApplicationForAid.appModeCd') === 'FoodStamp' &&
        //     program
        // )
        // {
        //     if (
        //         !AppContext.model.getValue('application.programs.program').some(err => err === 'FS')
        //     ) {
        //         if (
        //             !this.state.model.appErrors.errorField.some(
        //                 err => err === errorMessages.GANEW511,
        //             )
        //         ) {
        //             this.state.model.appErrors.errorField.push(errorMessages.GANEW511);
        //         }
        //         if (
        //             this.state.model.appErrors.errorField.some(
        //                 err => err === errorMessages.GANEW510,
        //             )
        //         ) {
        //             let errs = this.state.model.appErrors.errorField;
        //             errs = this.state.model.appErrors.errorField.filter(msg => {
        //                 return msg !== errorMessages.GANEW510;
        //             });
        //             this.state.model.appErrors.errorField = errs;
        //         }
        //     }
        //     if (program.target.checked && program.target.value === 'FS') {
        //         this.checkSourceTanfFoodStamps(program.target.value, false);
        //         return;
        //     } else if (!program.target.checked && program.target.value === 'FS') {
        //         this.checkSourceTanfFoodStamps(program.target.value, true);
        //         return;
        //     }
        //     this.programCombination(false);
        // }
        else {
            this.programCombination(false);
        }
    };

    showDisclosure(program) {
        if (program) {
            for (let i = 0; i < program.length; i++) {
                if (program[i] === 'WC') {
                    return true;
                }
            }
        }
    }

    showOtherAccomodation(accomodationTypes) {
        if (accomodationTypes) {
            for (let i = 0; i < accomodationTypes.length; i++) {
                if (accomodationTypes[i].value === 'OTH') {
                    return true;
                }
            }
        }
    }

    validateWic = (program, isWic) => {
        if (
            isWic ||
            (program.target &&
                program.target.value === 'WC' &&
                AppContext.model.getValue('ARRAP.ArApplicationForAid.appRecvdDt'))
        ) {
            const selectedDate = new Date(
                AppContext.model.getValue('ARRAP.ArApplicationForAid.appRecvdDt'),
            );
            const selectedDateStr =
                selectedDate.getDate() +
                '-' +
                selectedDate.getMonth() +
                '-' +
                selectedDate.getFullYear();
            const currentDate =
                new Date().getDate() + '-' + new Date().getMonth() + '-' + new Date().getFullYear();
            if (
                currentDate !== selectedDateStr &&
                ((program.target && program.target.value === 'WC' && program.target.checked) ||
                    isWic)
            ) {
                this.state.model.appErrors.isError = true;
                if (
                    !this.state.model.appErrors.errorField.some(
                        msg => msg === errorMessages.GANEW500,
                    )
                ) {
                    this.state.model.appErrors.errorField.push(errorMessages.GANEW500);
                    this.state.model.appErrors.isError = true;
                }
                if (this.state.model.appErrors.isError) {
                    AppContext.pagedetails
                        .getPageContext()
                        .stateHandler('msgsList', this.state.model.appErrors.errorField);
                    AppContext.pagedetails
                        .getPageContext()
                        .stateHandler('errorMsg', this.state.model.appErrors.isError);
                }
            } else {
                const errorField = this.state.model.appErrors.errorField.filter(err => {
                    return err !== errorMessages.GANEW500;
                });
                this.state.model.appErrors.errorField = errorField;
                if (this.state.model.appErrors.errorField.length === 0) {
                    this.state.model.appErrors.isError = false;
                }
                AppContext.pagedetails.getPageContext().stateHandler('msgsList', errorField);
                if (!this.state.model.appErrors.isError) {
                    AppContext.pagedetails
                        .getPageContext()
                        .stateHandler('errorMsg', this.state.model.appErrors.isError);
                }
            }
        } else {
            const errorField = this.state.model.appErrors.errorField.filter(err => {
                return err !== errorMessages.GANEW500;
            });
            this.state.model.appErrors.errorField = errorField;
        }
    };
    validateRecievedDate = event => {
        if (event._sugar_utc === false) {
            const currentMonth = new Date();
            const selectedMonth = new Date(
                AppContext.model.getValue('ARRAP.ArApplicationForAid.appRecvdDt'),
            );
            const isPriorMonth = currentMonth.getMonth() > selectedMonth.getMonth();
            const monthDiff = this.getMonthDiff(selectedMonth, currentMonth);
            if (!monthDiff && isPriorMonth) {
                this.state.model.appErrors.isError = monthDiff;
                AppContext.pagedetails
                    .getPageContext()
                    .stateHandler('warningMsgsList', [errorMessages.GANEW550]);
                AppContext.pagedetails.getPageContext().stateHandler('warningMsg', isPriorMonth);
            } else if (monthDiff) {
                if (
                    !this.state.model.appErrors.errorField.some(
                        err => err === errorMessages.GANEW551,
                    )
                ) {
                    this.state.model.appErrors.errorField.push(errorMessages.GANEW551);
                }
                this.state.model.appErrors.isError = monthDiff;
                AppContext.pagedetails
                    .getPageContext()
                    .stateHandler('msgsList', this.state.model.appErrors.errorField);
                AppContext.pagedetails
                    .getPageContext()
                    .stateHandler('errorMsg', this.state.model.appErrors.isError);
            } else {
                const errorField = this.state.model.appErrors.errorField.filter(err => {
                    return err !== errorMessages.GANEW551;
                });
                this.state.model.appErrors.errorField = errorField;
                this.state.model.appErrors.isError =
                    this.state.model.appErrors.errorField.length !== 0;
                AppContext.pagedetails.getPageContext().stateHandler('warningMsg', false);
                AppContext.pagedetails
                    .getPageContext()
                    .stateHandler('msgsList', this.state.model.appErrors.errorField);
                AppContext.pagedetails
                    .getPageContext()
                    .stateHandler('errorMsg', this.state.model.appErrors.isError);
            }
            if (
                AppContext.model.getValue('ARRAP.ArAppProgram.programCd').some(wic => wic === 'WC')
            ) {
                this.validateWic(event, true);
            } else {
                this.validateWic(event, false);
            }
        }
    };

    getMonthDiff = (d1, d2) => {
        var months;
        months = (d2.getFullYear() - d1.getFullYear()) * 12;
        months -= d1.getMonth() + 1;
        months += d2.getMonth();
        if (d2.getDate() >= d1.getDate()) months++;

        if (months > 13) {
            return true;
        } else {
            return false;
        }
    };
    validateTanfFoodStamps = program => {
        if (program === 'TF') {
            AppContext.model.setValue('application.programs.tanfProgram', 'show');
            AppContext.model.setValue('application.programs.foodStampProgram', 'hide');
            return;
        } else if (program === 'FS') {
            AppContext.model.setValue('application.programs.foodStampProgram', 'show');
            AppContext.model.setValue('application.programs.tanfProgram', 'hide');
            return;
        }
    };

    ValidatePhoneDyna = val => {
        var pattern = /^\d{10}$/;

        let errorMessage = [];
        if (!(val.phnTypeCd || val.phnTypeCd === "{''}")) {
            errorMessage.push(errorMessages.GL003.replace('~', 'Phone Type'));
        }
        if (!val.phnNum) {
            errorMessage.push(errorMessages.GL003.replace('~', 'Phone Number'));
        } else {
            if (!pattern.test(val.phnNum)) {
                errorMessage.push(errorMessages.GL109.replace('~', 'Phone Type'));
            }
        }
        return errorMessage;
    };

    ValidateContactDyna = val => {
        var reg = /\S+@\S+\.\S+/;
        let errorMessage = [];
        if (!val.email) {
            errorMessage.push(errorMessages.GL003.replace('~', 'Email'));
        } else {
            let email = val.email;
            if (!reg.test(email)) {
                errorMessage.push(errorMessages.GL002);
            }
        }
        return errorMessage;
    };

    createCustomContent = () => {
        let cc = { 'Phone Type': this.getphoneTypeDesc };
        return cc;
    };

    getphoneTypeDesc = columnData => {
        // console.log(Util.getRefTblFldDescription('WEEKDAYCONTACTMETHOD', 'CODE', columnData.trim(), this));
        // return RefUtil.getRefTblFldDescription('WEEKDAYCONTACTMETHOD', 'CODE', columnData, this);
        if (columnData === 'A') {
            return 'Alternative Phone';
        } else if (columnData === 'P') {
            return 'Primary Phone';
        } else if (columnData === 'U') {
            return 'Work Phone';
        }
    };

    navigateToNextPage = () => {
        let validatedDom = AppContext.pagedetails.getPageContext().validate();
        if (
            AppContext.model.getValue('application.programs.foodStampProgram') === 'show' ||
            AppContext.model.getValue('application.programs.tanfProgram') === 'show'
        )
            return;
        if (!validatedDom.isError && !this.state.model.appErrors.isError) {
            if (this.state.model.application.selectedPrograms.some(p => p === 'CC')) {
                workflowUtills.addScreenAfterScreen('ARRAD', 'ARCPG');
            } else {
                workflowUtills.removePage('ARCPG');
            }
            if (AppContext.model.getValue('ARRAP.DcIndv.authRepSw') === 'Y') {
                workflowUtills.addSubScreenAfterCurrentScreen('ARRAR');
            } else {
                workflowUtills.removePage('ARRAR');
            }

            navigationUtills.toNextPage();
        } else {
            window.scrollTo(0, 0);
        }
    };
}

export default Application;
