import Template from './AppRegistrationHome.rt';
import { PageConfig, UXPage, Navigate } from '@d-lift/core';
import { WorkFlow } from '@/ScreenFlowUtils/WorkFlow';
import './AppRegistrationHome.css';
@PageConfig({
    Path: '/AppRegistrationHome',
    Description: 'AppRegistrationHome Page',
    Template: Template,
    ContentManager: true,
})
class AppRegistrationHome extends UXPage {
    initializeModel() {
        return {
            AppRegistrationHome: 'AppRegistrationHome Page',
        };
    }

    toApplicationReg = () => {
        // Navigate.to('/ApplicationRegistration/Application', {
        //     workflow: 'ApplicationRegistration',
        // });
        this.props.location.state = undefined;
        Navigate.to('/ApplicationRegistrationNew/appRegNew');
    };
    toApplicationRegOld = () => {
        // Navigate.to('/AppRegistrationHome/Application', {
        //     workflow: 'AppRegistrationHome',
        // });
        WorkFlow.navigateToFirstScreen('AREGA');
    };

    toMaintainApp = () => {
        // Navigate.to('/MaintainApplicationSample/SearchApp', {
        //     workflow: 'MaintainApplicationSample',
        // });
        // WorkFlow.navigateToFirstScreen('ARSEA');
        Navigate.to('/ApplicationMaintainanceNew/SearchAppNew');
    };

    toMaintainAppOld = () => {
        // Navigate.to('/MaintainApplicationSample/SearchApp', {
        //     workflow: 'MaintainApplicationSample',
        // });
        WorkFlow.navigateToFirstScreen('ARSEA');
    };

    toLoginPage = async () => {
        Navigate.to('/Security/login');
    };
}

export default AppRegistrationHome;
