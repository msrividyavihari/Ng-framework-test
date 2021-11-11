import { BaseComponent, UX } from '@d-lift/uxcomponents';
import React from 'react';
import ContactInfoTemplate from './ContactInfo.rt';
import { Date } from 'sugar';
import errorMessages from '../../../../public/validation/commonValidation.json';
import { PageConfig, UXPage, AppContext, Lift } from '@d-lift/core';
import '../../../Validations/customValidations.js';
import basicAxiosInterceptor from '@/HttpInterceptors/BasicAxiosInterCeptor';
import _ from 'lodash';

import './ContactInfo.css';

class ContactInfo extends BaseComponent {
    cancelNoAddress = () => {
        AppContext.model.setValue('addressNotFound.popup', false);
    };

    cancelValidateReAddress = () => {
        AppContext.model.setValue('validateReAddress.popup', false);
    };

    confirmValidateReAddress = () => {
        AppContext.model.setValue('validateReAddress.popup', false);

        //Update model with new Web Address details
        AppContext.model.setValue(
            'contactInfo.addressDetails.addressInfo.RE.addrLine',
            AppContext.model.getValue('contactInfo.addressDetails.addressInfo.webRE.addrLine'),
        );
        AppContext.model.setValue(
            'contactInfo.addressDetails.addressInfo.RE.addrStateCd',
            AppContext.model.getValue('contactInfo.addressDetails.addressInfo.webRE.addrStateCd'),
        );
        AppContext.model.setValue(
            'contactInfo.addressDetails.addressInfo.RE.addrZip5',
            AppContext.model.getValue('contactInfo.addressDetails.addressInfo.webRE.addrZip5'),
        );
        AppContext.model.setValue(
            'contactInfo.addressDetails.addressInfo.RE.Zipcode4',
            AppContext.model.getValue('contactInfo.addressDetails.addressInfo.webRE.Zipcode4'),
        );
        AppContext.model.setValue(
            'contactInfo.addressDetails.addressInfo.RE.addrCity',
            AppContext.model.getValue('contactInfo.addressDetails.addressInfo.webRE.addrCity'),
        );
    };

    cancelValidateMaAddress = () => {
        AppContext.model.setValue('validateMaAddress.popup', false);
    };

    confirmValidateMaAddress = () => {
        //Update model with new Web Address details
        AppContext.model.setValue(
            'contactInfo.addressDetails.addressInfo.MA.addrLine',
            AppContext.model.getValue('contactInfo.addressDetails.addressInfo.webMA.addrLine'),
        );
        AppContext.model.setValue(
            'contactInfo.addressDetails.addressInfo.MA.addrStateCd',
            AppContext.model.getValue('contactInfo.addressDetails.addressInfo.webMA.addrStateCd'),
        );
        AppContext.model.setValue(
            'contactInfo.addressDetails.addressInfo.MA.addrZip5',
            AppContext.model.getValue('contactInfo.addressDetails.addressInfo.webMA.addrZip5'),
        );
        AppContext.model.setValue(
            'contactInfo.addressDetails.addressInfo.MA.Zipcode4',
            AppContext.model.getValue('contactInfo.addressDetails.addressInfo.webMA.Zipcode4'),
        );
        AppContext.model.setValue(
            'contactInfo.addressDetails.addressInfo.MA.addrCity',
            AppContext.model.getValue('contactInfo.addressDetails.addressInfo.webMA.addrCity'),
        );

        AppContext.model.setValue('validateMaAddress.popup', false);
    };

    async validate(id) {
        const node = document.getElementById(id);
        const childNodes = node.querySelectorAll('[model]');
        _.forEach(childNodes, function (childnode) {
            if (childnode.getAttribute('model') !== undefined) {
                if (
                    AppContext.model.getValue(childnode.getAttribute('model')) === undefined ||
                    AppContext.model.getValue(childnode.getAttribute('model')) === ''
                ) {
                    AppContext.pagedetails
                        .getPageContext()
                        .stateHandler(childnode.getAttribute('model'), undefined);
                }
            }
        });

        if (node.querySelectorAll("[is-error='true']").length === 0) {
            return true;
        }
        return false;
    }

    validateResInfo = async () => {
        try {
            //If validation errors are present, return
            let validResAddrStatus = await this.validate('contact_res_addr');
            if (!validResAddrStatus) {
                return;
            }

            if (
                !(
                    AppContext.model.getValue(
                        'contactInfo.addressDetails.addressInfo.RE.addrLine',
                    ) &&
                    AppContext.model.getValue(
                        'contactInfo.addressDetails.addressInfo.RE.addrCity',
                    ) &&
                    AppContext.model.getValue('contactInfo.addressDetails.addressInfo.RE.addrZip5')
                ) ||
                (AppContext.model.getValue(
                    'contactInfo.addressDetails.addressInfo.RE.addrFormatCd',
                ) === 'US' &&
                    !AppContext.model.getValue(
                        'contactInfo.addressDetails.addressInfo.RE.addrStateCd',
                    ))
            ) {
                AppContext.pagedetails
                    .getPageContext()
                    .stateHandler('msgsList', [errorMessages.GL162]);
                AppContext.pagedetails.getPageContext().stateHandler('errorMsg', true);
                window.scrollTo(0, 0);
            } else {
                AppContext.pagedetails.getPageContext().stateHandler('errorMsg', false);
            }

            //Get matching address from Web
            let street = AppContext.model.getValue(
                'contactInfo.addressDetails.addressInfo.RE.addrLine',
            );
            let city = AppContext.model.getValue(
                'contactInfo.addressDetails.addressInfo.RE.addrCity',
            );
            let state = AppContext.model.getValue(
                'contactInfo.addressDetails.addressInfo.RE.addrStateCd',
            );
            let zipCode = AppContext.model.getValue(
                'contactInfo.addressDetails.addressInfo.RE.addrZip5',
            );
            //let street2 = AppContext.model.getValue('contactInfo.addressDetails.addressInfo.RE.addrLine1');
            let street2 = 'NA';
            let urbanization = 'NA';

            (async () => {
                //await Util.HTTP.post(AppContext.config.associateCase + appNum + '/' + caseNum);
                const response = await basicAxiosInterceptor({
                    method: 'GET',
                    url:
                        AppContext.config.validateContactAddress +
                        '?Street=' +
                        street +
                        '&City=' +
                        city +
                        '&State=' +
                        state +
                        '&ZipCode=' +
                        zipCode +
                        '&Street2=' +
                        street2 +
                        '&Urbanization=' +
                        urbanization,
                });
                if (typeof response.data.AddrLine1 === 'undefined') {
                    console.log('No matching address found');
                    AppContext.model.setValue('addressNotFound.popup', true);
                } else {
                    //Update Model with new Web address from response
                    console.log(response.data);
                    AppContext.model.setValue(
                        'contactInfo.addressDetails.addressInfo.webRE.addrLine',
                        response.data.AddrLine1,
                    );
                    AppContext.model.setValue(
                        'contactInfo.addressDetails.addressInfo.webRE.addrStateCd',
                        response.data.State,
                    );
                    AppContext.model.setValue(
                        'contactInfo.addressDetails.addressInfo.webRE.addrZip5',
                        response.data.Zipcode5,
                    );
                    AppContext.model.setValue(
                        'contactInfo.addressDetails.addressInfo.webRE.Zipcode4',
                        response.data.Zipcode4,
                    );
                    AppContext.model.setValue(
                        'contactInfo.addressDetails.addressInfo.webRE.addrCity',
                        response.data.City,
                    );

                    AppContext.model.setValue('validateReAddress.popup', true);
                }
            })(console.log('Open next panel not needed'));
        } catch (ex) {
            console.log(ex);
        }
    };

    chgResCounty = (event) => {
        if (event.target.value !== 'GA') {
            AppContext.model.setValue(
                'contactInfo.addressDetails.addressInfo.RE.addrCountyCd',
                '999',
            );
        } else {
            AppContext.model.setValue('contactInfo.addressDetails.addressInfo.RE.addrCountyCd', '');
        }
    };

    chgMailCounty = (event) => {
        if (event.target.value !== 'GA') {
            AppContext.model.setValue(
                'contactInfo.addressDetails.addressInfo.MA.addrCountyCd',
                '999',
            );
        } else {
            AppContext.model.setValue('contactInfo.addressDetails.addressInfo.MA.addrCountyCd', '');
        }
    };

    validateMailInfo = async () => {
        try {
            //If validation errors are present, return
            let validMailAddrStatus = await this.validate('contact_mail_addr');
            if (!validMailAddrStatus) {
                return;
            }

            if (
                !(
                    AppContext.model.getValue(
                        'contactInfo.addressDetails.addressInfo.MA.addrLine',
                    ) &&
                    AppContext.model.getValue(
                        'contactInfo.addressDetails.addressInfo.MA.addrCity',
                    ) &&
                    AppContext.model.getValue('contactInfo.addressDetails.addressInfo.MA.addrZip5')
                ) ||
                (AppContext.model.getValue(
                    'contactInfo.addressDetails.addressInfo.MA.addrFormatCd',
                ) === 'US' &&
                    !AppContext.model.getValue(
                        'contactInfo.addressDetails.addressInfo.MA.addrStateCd',
                    ))
            ) {
                AppContext.pagedetails
                    .getPageContext()
                    .stateHandler('msgsList', [errorMessages.GL162]);
                AppContext.pagedetails.getPageContext().stateHandler('errorMsg', true);
                window.scrollTo(0, 0);
            } else {
                AppContext.pagedetails.getPageContext().stateHandler('errorMsg', false);
            }

            //Get matching address from Web
            let street = AppContext.model.getValue(
                'contactInfo.addressDetails.addressInfo.MA.addrLine',
            );
            let city = AppContext.model.getValue(
                'contactInfo.addressDetails.addressInfo.MA.addrCity',
            );
            let state = AppContext.model.getValue(
                'contactInfo.addressDetails.addressInfo.MA.addrStateCd',
            );
            let zipCode = AppContext.model.getValue(
                'contactInfo.addressDetails.addressInfo.MA.addrZip5',
            );
            //let street2 = AppContext.model.getValue('contactInfo.addressDetails.addressInfo.MA.addrLine1');
            let street2 = 'NA';
            let urbanization = 'NA';

            (async () => {
                //await Util.HTTP.post(AppContext.config.associateCase + appNum + '/' + caseNum);
                const response = await basicAxiosInterceptor({
                    method: 'GET',
                    url:
                        AppContext.config.validateContactAddress +
                        '?Street=' +
                        street +
                        '&City=' +
                        city +
                        '&State=' +
                        state +
                        '&ZipCode=' +
                        zipCode +
                        '&Street2=' +
                        street2 +
                        '&Urbanization=' +
                        urbanization,
                });
                if (typeof response.data.AddrLine1 === 'undefined') {
                    console.log('No matching address found');
                    AppContext.model.setValue('addressNotFound.popup', true);
                } else {
                    //Update Model with new Web address from response
                    console.log(response.data);
                    AppContext.model.setValue(
                        'contactInfo.addressDetails.addressInfo.webMA.addrLine',
                        response.data.AddrLine1,
                    );
                    AppContext.model.setValue(
                        'contactInfo.addressDetails.addressInfo.webMA.addrStateCd',
                        response.data.State,
                    );
                    AppContext.model.setValue(
                        'contactInfo.addressDetails.addressInfo.webMA.addrZip5',
                        response.data.Zipcode5,
                    );
                    AppContext.model.setValue(
                        'contactInfo.addressDetails.addressInfo.webMA.Zipcode4',
                        response.data.Zipcode4,
                    );
                    AppContext.model.setValue(
                        'contactInfo.addressDetails.addressInfo.webMA.addrCity',
                        response.data.City,
                    );

                    AppContext.model.setValue('validateMaAddress.popup', true);
                }
            })(console.log('Open next panel not needed'));
        } catch (ex) {
            console.log(ex);
        }
    };

    addEmailDetails = () => {
        const appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
        console.log(AppContext.model.getValue('contactInfo.emailDetails'));
        //If undefined add a new null []
        if (
            typeof AppContext.model.getValue('contactInfo.emailDetails') === 'undefined' ||
            AppContext.model.getValue('contactInfo.emailDetails') === null
        ) {
            AppContext.model.setValue('contactInfo.emailDetails', []);
        }
        AppContext.model.setValue('contactInfo.emailDetails', [
            ...AppContext.model.getValue('contactInfo.emailDetails'),
            {
                email: '',
                emailComments: '',
                appNum: appNum,
            },
        ]);
    };

    renderComponent() {
        return ContactInfoTemplate.bind(this).apply(this);
    }
}

export default ContactInfo;
