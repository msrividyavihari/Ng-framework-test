import Template from './ChildCareProGroup.rt';
import { PageConfig, UXPage, AppContext } from '@d-lift/core';
import navigationUtills from '@/ScreenFlowUtils/NavigationUtills';

@PageConfig({
    ContextRoot: 'ApplicationRegistration',
    PageName: 'ChildCareProGroup',
    Path: ['/childcareprogroup', "/ARCPG"],
    Template: Template,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Child Care Priority Groups',
})
class ChildCareProGroup extends UXPage {
    initializeModel() {
        // let appstruct = {
        //     applnPrtyGrp: '',
        //     dfcsStatus: '',
        //     withDisabl: '',
        //     protServ: '',
        //     gaPreK: '',
        //     fmlyLack: '',
        //     fmlyDomesViol: '',
        //     fmlyNtrlDistr: '',
        //     grndParntRaiseGrndChild: '',
        //     minParent: '',
        //     tanf: '',
        //     lowIncome: '',
        //     grntTranst: '',
        //     prtyGrp: '',
        //     tccCase: '',
        //     resrcExcess: '',
        // };
        // AppContext.model.setValue('childCare', appstruct);

    }

    chgApplnPrty = event => {
        if (event.target.value === 'N') {
            AppContext.model.setValue('childCare.dfcsStatus', 'NO');
            AppContext.model.setValue('childCare.withDisabl', 'N');
            AppContext.model.setValue('childCare.protServ', 'NO');
            AppContext.model.setValue('childCare.gaPreK', 'N');
            AppContext.model.setValue('childCare.fmlyLack', 'N');
            AppContext.model.setValue('childCare.fmlyDomesViol', 'NO');
            AppContext.model.setValue('childCare.fmlyNtrlDistr', 'NO');
            AppContext.model.setValue('childCare.grndParntRaiseGrndChild', 'NO');
            AppContext.model.setValue('childCare.minParent', 'NO');
            AppContext.model.setValue('childCare.tanf', 'NO');
            AppContext.model.setValue('childCare.lowIncome', 'No');
            AppContext.model.setValue('childCare.grntTranst', 'NO');
            AppContext.model.setValue('childCare.prtyGrp', 'NO');
            AppContext.model.setValue('childCare.tccCase', 'NO');
            AppContext.model.setValue('childCare.resrcExcess', 'N');
        }
    };

    chgAppln = event => {
        if (
            (event.target.value !== 'N' ||
                event.target.value !== 'NO' ||
                event.target.value !== 'No') &&
            event.target.value !== 'default'
        ) {
            AppContext.model.setValue('childCare.applnPrtyGrp', 'Y');
        }
    };

    goToNextPage = () => {
        let validatedDom = AppContext.pagedetails.getPageContext().validate();
        if (!validatedDom.isError) {
            navigationUtills.toNextPage();
        } else {
            window.scrollTo(0, 0);
        }
    };

    goBack = () => {
        navigationUtills.toPreviousPage();
    };
}

export default ChildCareProGroup;
