import React from 'react';
import { BaseComponent } from '@d-lift/uxcomponents';
import { UX } from '@d-lift/uxcomponents';
import { AppContext, Util } from '@d-lift/core';
import Template from './ApplicationOverview.rt';
import _ from 'lodash';
import basicAxiosInterceptor from '@/HttpInterceptors/BasicAxiosInterCeptor';
import './ApplicationOverview.css';

class ApplicationOverview extends BaseComponent {
    // uxComponentDidMount = () => {
    //     AppContext.model.setValue('appOverview.priority_status', 'Pregnancy');
    //     AppContext.model.setValue('appOverview.expedited_snap', 'No');
    //     AppContext.model.setValue('appOverview.special_circumstances', 'No');
    //     AppContext.model.setValue('appOverview.programList', [
    //         {
    //             program: 'MA',
    //             applicantName: ['John Brisk', 'Two'],
    //         },
    //         {
    //             program: 'TF',
    //             applicantName: ['One', 'Two', 'Three'],
    //         },
    //         {
    //             program: 'FS',
    //             applicantName: ['One', 'Two', 'Three', 'Four'],
    //         },
    //     ]);
    // };

    renderComponent() {
        return Template.bind(this).apply(this);
    }
}
export default ApplicationOverview;
