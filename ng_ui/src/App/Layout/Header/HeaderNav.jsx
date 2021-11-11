import { BaseNavComponent, Navigate, AppContext, Util } from '@d-lift/core';
import HeaderNavTemplate from './HeaderNav.rt';
import HeaderLabelKeys from './HeaderLabelKeys.json';
import { WorkFlow } from '@/ScreenFlowUtils/WorkFlow';
import './HeaderNav.css';

export default class HeaderNav extends BaseNavComponent {
    onInit() {
        this.setLabelKeys(HeaderLabelKeys);
        this.setTemplate(HeaderNavTemplate);
    }

    onComponentMount() {
        this.startClock();
    }

    onComponentUnmount() {
        this.stopClock();
    }

    onNavigationChange(obj) {}

    onLocaleChange(locale) {}

    updateLangauge(ev) {
        if (this.state.locale === 'en') {
            this.updateLocale('es');
        } else {
            this.updateLocale('en');
        }
    }

    logout = () => {
        Util.clearSessionData('jwtToken');
        Util.clearSessionData('isUserLoggedIn');
        Navigate.to('/');
    };

    toApplicationReg = () => {
        // Navigate.to('/ApplicationRegistration/Application', {
        //     workflow: 'ApplicationRegistration',
        // });
        //WorkFlow.navigateToFirstScreen('AREGA');
        Navigate.to('/ApplicationRegistrationNew/appRegNew');
    };

    toMaintainApp = () => {
        // Navigate.to('/MaintainApplicationSample/SearchApp', {
        //     workflow: 'MaintainApplicationSample',
        // });
        //  WorkFlow.navigateToFirstScreen('ARSEA');
        Navigate.to('/ApplicationMaintainanceNew/SearchAppNew');
    };

    toCorrespondence = () => {
        WorkFlow.navigateToFirstScreen('COVPC');
    };

    toHomePage = () => {
        Navigate.to('/Public/HomeNew');
    };
}
