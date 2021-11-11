import { BaseComponent } from '@d-lift/uxcomponents';
import { Navigate } from '@d-lift/core';
import RightNavTemplate from './RightNavigation.rt';
import './RightNavigation.css';

class RightNavigation extends BaseComponent {
    toApplicationReg = e => {
        let appNum = e.target.innerText;
        Navigate.to('/ApplicationRegistrationNew/appRegNew', {
            maintainMode: 'Y',
            appNumber: appNum,
        });
    };

    renderComponent() {
        return RightNavTemplate.bind(this).apply(this);
    }

    async loadDataFCSSP(ssn) {
        this.parent.props.fileClearSearch(ssn);
    }
}

export default RightNavigation;
