import Template from './HomeNew.rt';
import { PageConfig, UXPage, Navigate } from '@d-lift/core';
import { WorkFlow } from '@/ScreenFlowUtils/WorkFlow';
import './HomeNew.css';
@PageConfig({
    Path: '/HomeNew',
    Description: 'HomeNew Page',
    Template: Template,
    ContentManager: true,
})
class HomeNew extends UXPage {
    initializeModel() {
        return {
            HomeNew: 'HomeNew Page',
        };
    }

    toCoViewPending = () => {
        WorkFlow.navigateToFirstScreen('COVPC');
    };

    toLoginPage = async () => {
        Navigate.to('/Security/login');
    };

    toAppReg = () => {
        // Navigate.to('/AppRegistrationHome', {
        //     workflow: 'AppRegistrationHome',
        // });
        Navigate.to('/Public/AppRegistrationHome');
    };
}

export default HomeNew;
