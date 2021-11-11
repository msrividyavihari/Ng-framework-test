import { BaseComponent, UX } from '@d-lift/uxcomponents';
import React from 'react';
import AuthRepTemplate from './AuthRep.rt';
import { AppContext } from '@d-lift/core';
import { Date } from 'sugar';
import errorMessages from '../../../../public/validation/commonValidation.json';
import basicAxiosInterceptor from '@/HttpInterceptors/BasicAxiosInterCeptor';
import _ from 'lodash';

import './AuthRep.css';

class AuthRep extends BaseComponent {
    addEmailDetails = () => {
        const appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
        console.log(AppContext.model.getValue('authRep.emailDetails'));
        //If undefined add a new null []
        if (
            typeof AppContext.model.getValue('authRep.emailDetails') === 'undefined' ||
            AppContext.model.getValue('authRep.emailDetails') === null
        ) {
            AppContext.model.setValue('authRep.emailDetails', []);
        }
        AppContext.model.setValue('authRep.emailDetails', [
            ...AppContext.model.getValue('authRep.emailDetails'),
            {
                email: '',
                emailComments: '',
                appNum: appNum,
            },
        ]);
    };

    renderComponent() {
        return AuthRepTemplate.bind(this).apply(this);
    }
}

export default AuthRep;
