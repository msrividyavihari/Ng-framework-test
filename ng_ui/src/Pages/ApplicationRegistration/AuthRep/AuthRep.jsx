import React from 'react';
import { UX } from '@d-lift/uxcomponents';
import Template from './AuthRep.rt';
import { PageConfig, UXPage, AppContext, Lift } from '@d-lift/core';
import navigationUtills from '@/ScreenFlowUtils/NavigationUtills';
import {} from '@/Validations/customValidations.js';
import errorMessages from '../../../../public/validation/commonValidation.json';

@PageConfig({
    ContextRoot: 'ApplicationRegistration',
    PageName: 'AuthRep',
    Path: ['/authrep', '/ARRAR'],
    Template: Template,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Application Registration',
})
class AuthRep extends UXPage {
    onPageLoad() {
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

        AppContext.model.setValue('ARRAR', {
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

        let Validator = Lift.Validator;
        Validator.register('charCheck', function(value, requirement) {
            var pattern = /^[0-9a-zA-Z'_ ]+$/g;
            return pattern.test(value);
        });

        Validator.register('splCharCheck', function(value, requirement) {
            var pattern = /^[a-zA-Z0-9- ]+$/g;
            return pattern.test(value);
        });

        Validator.register('cityCheck', function(value, requirement) {
            var pattern = /[A-Z]+$/gi;
            return pattern.test(value);
        });

        Validator.register('zipCheck', function(value, requirement) {
            var pattern = /(\d{5})/;
            return pattern.test(value);
        });

        Validator.register('minCharCheck', function(value, requirement) {
            var charFlag = false;
            var regex = /^[A-Z ]$/gi;
            var numCount = 0;
            for (var i = 0, len = value.length; i < len; i++) {
                var substr = value.substring(i, i + 1);

                if (substr.match(regex)) {
                    numCount++;
                    if (numCount >= 2) {
                        charFlag = true;
                    }
                } else {
                    numCount = 0;
                }
            }
            return charFlag;
        });

        Validator.register('splNumCheck', function(value, requirement) {
            var valFlag = true;
            var regex = /^[0-9]$/;
            var numCnt = 0;
            for (var i = 0, len = value.length; i < len; i++) {
                var substr = value.substring(i, i + 1);

                if (substr.match(regex)) {
                    numCnt++;
                    if (numCnt >= 9) {
                        valFlag = false;
                    }
                } else {
                    numCnt = 0;
                }
            }
            return valFlag;
        });
    }

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

    getphoneTypeDesc = columnData => {
        if (columnData === 'A') {
            return 'Alternative Phone';
        } else if (columnData === 'P') {
            return 'Primary Phone';
        } else if (columnData === 'U') {
            return 'Work Phone';
        }
    };

    createCustomContent = () => {
        let cc = { 'Phone Type': this.getphoneTypeDesc };
        return cc;
    };

    goBack = () => {
        navigationUtills.toPreviousPage();
    };

    goToHomePage = event => {
        navigationUtills.goToHomePage();
    };

    SubmitForm = () => {
        let validatedDom = AppContext.pagedetails.getPageContext().validate();
        if (!validatedDom.isError) {
            navigationUtills.toNextPage();
        } else {
            window.scrollTo(0, 0);
        }
    };
}

export default AuthRep;
