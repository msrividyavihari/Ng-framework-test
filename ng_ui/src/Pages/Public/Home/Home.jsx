import Template from './Home.rt';
import { PageConfig, UXPage, Navigate } from '@d-lift/core';
import { WorkFlow } from '@/ScreenFlowUtils/WorkFlow';
@PageConfig({
    Path: '/Home',
    Description: 'Home Page',
    Template: Template,
    ContentManager: true,
})
class Home extends UXPage {
    initializeModel() {
        return {
            home: 'Home Page',
        };
    }

    toApplicationReg = () => {
        // Navigate.to('/ApplicationRegistration/Application', {
        //     workflow: 'ApplicationRegistration',
        // });
        Navigate.to('/ApplicationRegistrationNew/appRegNew');
    };

    toApplicationRegOld = () => {
        // Navigate.to('/ApplicationRegistration/Application', {
        //     workflow: 'ApplicationRegistration',
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

    toCoViewPending = () => {
        WorkFlow.navigateToFirstScreen('COVPC');
    };

    toLoginPage = async () => {
        Navigate.to('/Security/login');
    };
}

export default Home;
