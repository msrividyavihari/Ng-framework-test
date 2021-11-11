import React from 'react';
import { UX } from '@d-lift/uxcomponents';
import Template from './Alias.rt';
import { PageConfig, UXPage, AppContext} from '@d-lift/core';
import navigationUtills from '@/ScreenFlowUtils/NavigationUtills';
import { } from '@/Validations/customValidations.js';
import errorMessages from '../../../../public/validation/commonValidation.json';

@PageConfig({
    ContextRoot: 'ApplicationRegistration',
    PageName: 'Alias',
    Path: ['/alias', "/ARRIO"],
    Template: Template,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Alias Details',
})
class Alias extends UXPage {
    onPageLoad(){
        AppContext.model.setValue("aliasCollection", [{
            aliasFirst: "John",
            aliasMiddle: "Cena",
            aliasLast: "Oliver",
            aliasSuffix: "1",
            aliasGender: "M"
        }, {
            aliasFirst: "Kim",
            aliasMiddle: "Rose",
            aliasLast: "Cathy",
            aliasSuffix: "2",
            aliasGender: "F",
        },]);
    }
    
    initializeModel() {
        return {
            alias: {
                aliasPrmy: {
                    prmyFirst: 'Amy',
                    prmyMiddle: 'Jeff',
                    prmyLast: 'Rose',
                    prmySuffix: 'M',
                    prmyGender: 'Female',
                    prmySSN: '987654321',
                    prmyDOB: '01/01/2001',
                },
            }
        };
    }

    editCreation = (columnData, rowData, a) => {
        return (
            <UX type="icon" symbol="fa fa-pen" click={event => this.editRowContact(event, rowData, a)}></UX>
        );
    };

    editRowContact = (event, rowData, a) => {

        AppContext.model.setValue('alias.DynaFlags.showForm', true);
        AppContext.model.setValue('alias.DynaFlags.toggleAdd', false);
        AppContext.model.setValue('alias.name', { ...rowData });
        AppContext.model.setValue('alias.dyna.editContactIndex', a);
        return true;
    };

    deleteCreation = (columnData, rowData, a) => {
        return columnData ? null : (
            <UX
                type="icon"
                symbol="fa fa-trash"
                click={event => this.deleteRowContact(event, rowData, a)}></UX>
        );
    };    

    deleteRowContact = (event, rowData, a) => {
        let aliasCollection = [...AppContext.model.getValue('aliasCollection')];
        aliasCollection.remove(a);
        AppContext.model.setValue('aliasCollection', aliasCollection);
    };

    showForm() {
        AppContext.model.setValue('alias.DynaFlags.showForm', true);
        AppContext.model.setValue('alias.DynaFlags.toggleAdd', true);
    }

    saveContact = () => {
        if (!this.ValidateAliasDyna()) {
            let aliasArray = AppContext.model.getValue('alias.name', {});
            let aliasCollection = AppContext.model.getValue('aliasCollection');
            aliasCollection.push(aliasArray)
            AppContext.model.setValue('aliasCollection', aliasCollection);
            AppContext.model.setValue('alias.name', {});
            AppContext.model.setValue('alias.DynaFlags.showForm', false);
        } 
    };

    cancel() {
        AppContext.model.setValue('alias.name', {});
        AppContext.model.setValue('alias.DynaFlags.showForm', false);
    }

    update = () => {
        if (!this.ValidateAliasDyna()) {
            let index = AppContext.model.getValue('alias.dyna.editContactIndex', {});
            let aliasArray = AppContext.model.getValue('alias.name', {});
            let aliasCollection = AppContext.model.getValue('aliasCollection');
            aliasCollection.splice(index, 1, aliasArray);
            AppContext.model.setValue('aliasCollection', aliasCollection);
            AppContext.model.setValue('alias.name', {});
            AppContext.model.setValue('alias.DynaFlags.showForm', false);
        }
    }

    ValidateAliasDyna = () => {
        AppContext.model.setValue('alias.name.validationMessage', []);
        let errorFlag = false;
        var reg = /^[a-zA-Z0-9- ]+$/g;
        let errorMessage = AppContext.model.getValue('alias.name.validationMessage');
        if (!(AppContext.model.getValue('alias.name.aliasFirst'))) {
            errorFlag = true;
            errorMessage.push(errorMessages.GL003.replace('~', 'First'))
        }
        else {
            let aliasFirst = AppContext.model.getValue('alias.name.aliasFirst');
            if (!reg.test(aliasFirst)) {
                errorFlag = true;
                errorMessage.push(errorMessages.GL016.replace('~','First Name'))
            }
        }

        AppContext.model.setValue('alias.name.validationMessage', []);
        let errorFlag1 = false;
        var reg1 = /^[a-zA-Z0-9- ]+$/g;
        if ((AppContext.model.getValue('alias.name.aliasMiddle'))) {
            let aliasMiddle = AppContext.model.getValue('alias.name.aliasMiddle');
            if (!reg1.test(aliasMiddle)) {
                errorFlag1 = true;
                errorMessage.push(errorMessages.GL016.replace('~','Middle Name'))
            }
        }   

        AppContext.model.setValue('alias.name.validationMessage', []);
        let errorFlag2 = false;
        var reg2 = /^[a-zA-Z0-9- ]+$/g;
        if (!(AppContext.model.getValue('alias.name.aliasLast'))) {
            errorFlag2 = true;
            errorMessage.push(errorMessages.GL003.replace('~', 'Last'))
        } else {
            let aliasLast = AppContext.model.getValue('alias.name.aliasLast');
            if (!reg2.test(aliasLast)) {
                errorFlag2 = true;
                errorMessage.push(errorMessages.GL016.replace('~','Last Name'))
            } 
        }

        AppContext.model.setValue('alias.name.validationMessage', []);
        let errorFlag3 = false;
        if (!(AppContext.model.getValue('alias.name.aliasGender'))) {
            errorFlag3 = true;
            errorMessage.push(errorMessages.GL003.replace('~', 'Gender'))
        }

        AppContext.model.setValue('alias.name.validationMessage', []);
        let errorFlag4 = false;
        var regex4 = /^[A-Z]$/gi;
        var numCount = 0;
        let aliasLast = AppContext.model.getValue('alias.name.aliasLast');
        if ((AppContext.model.getValue('alias.name.aliasLast'))) {
                for (var i = 0, len = aliasLast.length; i < len; i++) {
                    var substr = aliasLast.substring(i, i + 1);
                    if (substr.match(regex4)) {
                        numCount++;
                    }
                }
                if (numCount < 2) {
                    errorFlag4 = true;
                    errorMessage.push(errorMessages.DC4106)
                } else {
                    errorFlag4 = false;
                }
        };

        if (errorFlag === true || errorFlag1 === true || errorFlag2 === true || errorFlag3 === true || 
            errorFlag4 === true) {
                errorFlag = true;
                AppContext.model.setValue('alias.name.ValidateContact', true);
                AppContext.model.setValue('alias.name.validationMessage', errorMessage);
        } else {
            errorFlag = false;
            AppContext.model.setValue('alias.name.ValidateContact', false);
            AppContext.model.setValue('alias.name.validationMessage', []);
        }
        return errorFlag;
    }

    getAliasSuffix = columnData => {
        if (columnData === '1') {
            return 'Jr';
        } else if (columnData === '2') {
            return 'Sr';
        } else if (columnData === '3') {
            return 'I';
        } else if (columnData === '4') {
            return 'II';
        } else if (columnData === '5') {
            return 'III';
        } else if (columnData === '6') {
            return 'IV';
        } else if (columnData === '1003') {
            return 'V';
        } else if (columnData === '7') {
            return 'VI';
        } else if (columnData === '8') {
            return 'VII';
        } else if (columnData === '9') {
            return 'VIII';
        } else if (columnData === '10') {
            return 'IX';
        } else if (columnData === '11') {
            return 'X';
        } else if (columnData === '12') {
            return 'XI';
        } else if (columnData === '13') {
            return 'XX';
        }   
    };

    getAliasGender = columnData => {
        if (columnData === 'M') {
            return 'Male';
        } else if (columnData === 'F') {
            return 'Female';
        } else if (columnData === 'U') {
            return 'Unknown';
        }
    };

    goBack = () => {
        navigationUtills.toPreviousPage();
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

export default Alias;
