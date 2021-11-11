import Template from './SelectProg.rt';
import { PageConfig, UXPage, AppContext } from '@d-lift/core';
import navigationUtills from '@/ScreenFlowUtils/NavigationUtills';

@PageConfig({
    ContextRoot: 'ApplicationRegistration',
    PageName: 'SelectProg',
    Path: ['/selectprog', "/ARRPR"],
    Template: Template,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Register Program - Program',
})
class SelectProg extends UXPage {
    initializeModel() {
        // let appstruct = {
        //     prgmSource: '',
        //     prgmWaitlist: '',
        //     prgmSelected: '',
        //     prgmApplnRecvdDate: '',
        // };
        // AppContext.model.setValue('regProgram', appstruct);

        let regstrPrgmList = {
            prgmList: {
                prgmSelected: 'Food Stamps',
                prgmApplnRecvdDate: '08/07/2020',
            },
        };
        AppContext.model.setValue('regProgram1', regstrPrgmList);
    }

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

export default SelectProg;
