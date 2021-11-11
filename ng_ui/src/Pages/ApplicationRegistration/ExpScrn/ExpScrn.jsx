import Template from './ExpScrn.rt';
import { PageConfig, UXPage, AppContext } from '@d-lift/core';
import navigationUtills from '@/ScreenFlowUtils/NavigationUtills';
import { } from '@/Validations/customValidations.js';

@PageConfig({
    ContextRoot: 'ApplicationRegistration',
    PageName: 'ExpScrn',
    Path: ['/expscrn', '/ARRES'],
    Template: Template,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Expedited Screening',
})
class ExpScrn extends UXPage {
    onPageLoad() {
        AppContext.model.setValue('valueN', 'N');
        AppContext.model.setValue('expScreen', {
            applicantgroupHasGrossIncome: 'N',
            doMonthlyIncomeAppply: 'N',
            mograntLess100: 'N',
            thereAMigrant: 'N',
            expeditedSw: 'N',
        });
    }
    data = e => {
        console.log('ABCD');
        return false;
    };
    goToNextPage = () => { };
    goBack = () => {
        navigationUtills.toPreviousPage();
    };
    returnTrue = () => {
        return true;
    };

    handleExpeditedEvents = expScreen => {
        if (expScreen.totalIncBeforeDed < 150 && expScreen.toalLiquidres <= 100) {
            expScreen.applicantgroupHasGrossIncome = 'Y';
            expScreen.rentOrMod = '';
        } else {
            expScreen.applicantgroupHasGrossIncome = 'N';
            if (
                parseFloat(expScreen.totalIncBeforeDed) < parseFloat(expScreen.rentOrMod) &&
                parseFloat(expScreen.toalLiquidres) <= parseFloat(expScreen.rentOrMod)
            ) {
                expScreen.doMonthlyIncomeAppply = 'Y';
                expScreen.mograntLess100 = 'Y';
                expScreen.thereAMigrant = 'Y';
            } else {
                expScreen.doMonthlyIncomeAppply = 'N';
                expScreen.mograntLess100 = 'N';
                expScreen.thereAMigrant = 'N';
            }
        }
        if (
            (expScreen.totalIncBeforeDed < 150 ||
                ('Y' === expScreen.thereAMigrant &&
                    expScreen.totalIncBeforeDed < expScreen.rentOrMod &&
                    expScreen.toalLiquidres <= expScreen.rentOrMod)) &&
            expScreen.toalLiquidres <= 100
        ) {
            expScreen.expeditedSw = 'Y';
        } else {
            expScreen.expeditedSw = 'N';
        }
    };

    changeEventTotalIncBeforeDed = event => {
        if (event) return;
        let expScreen = {
            ...AppContext.model.getValue('expScreen'),
            totalIncBeforeDed: event.target.value,
        };
        this.handleExpeditedEvents({ ...expScreen });
        this.setModelValue('expScreen', { ...expScreen });
    };

    changeEventToalLiquidres = event => {
        if (event) return;
        let expScreen = {
            ...AppContext.model.getValue('expScreen'),
            toalLiquidres: event.target.value,
        };
        this.handleExpeditedEvents({ ...expScreen });
        this.setModelValue('expScreen', { ...expScreen });
    };
    changeEventRentOrMod = event => {
        if (event) return;
        let expScreen = {
            ...AppContext.model.getValue('expScreen'),
            rentOrMod: event.target.value,
        };
        this.handleExpeditedEvents({ ...expScreen });
        this.setModelValue('expScreen', { ...expScreen });
    };

    changeMigrantWorker = event => {
        let expScreen = { ...AppContext.model.getValue('expScreen') };
        if (
            (expScreen.totalIncBeforeDed < 150 ||
                (event.target.value === 'Y' &&
                    expScreen.totalIncBeforeDed < expScreen.rentOrMod &&
                    expScreen.toalLiquidres <= expScreen.rentOrMod)) &&
            expScreen.toalLiquidres <= 100
        ) {
            expScreen.expeditedSw = 'Y';
        } else {
            expScreen.expeditedSw = 'N';
        }

        this.setModelValue('expScreen', { ...expScreen });
    };

    toNextPage = () => {
        let expScreen = { ...AppContext.model.getValue('expScreen') };
        if (!AppContext.pagedetails.getPageContext().validate().isError) {
            navigationUtills.toNextPage({ ...expScreen });
        }
    };
}

export default ExpScrn;
