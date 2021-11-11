import Template from './Address.rt';
import { PageConfig, UXPage, AppContext, Lift } from '@d-lift/core';
import navigationUtills from '@/ScreenFlowUtils/NavigationUtills';
import errorMessages from '../../../../public/validation/commonValidation.json';
import './Address.css';

@PageConfig({
    ContextRoot: 'ApplicationRegistration',
    PageName: 'Address',
    Path: ['/address', "/ARRAD"],
    Template: Template,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Addresses',
})
class Address extends UXPage {
    onPageLoad() {
        AppContext.model.setValue('ARRAD.ArAppAddrCargo[0].addrFormatCd', 'US');
        AppContext.model.setValue('ARRAD.ArAppAddrCargo[1].addrFormatCd', 'US');
        AppContext.model.setValue('ARRAD.ArAppAddrCargo[0].addrStNm', 'GA');
        let a = document.getElementById('section3');
        a.style.display = 'none';
    }

    initializeModel() {
        // let appstruct = {
        //     anon : '',
        //     noAddr: '',
        //     addrFormat1: '',
        //     careOf: '',
        //     addrLneRes1: '',
        //     addrLneRes2: '',
        //     addrLneRes3: '',
        //     apfo: '',
        //     aa: '',
        //     county: '',
        //     rcityName: '',
        //     rstateName: '',
        //     rzipCode: '',
        //     addrFormat2: '',
        //     mcareOf: '',
        //     maddrLne1: '',
        //     maddrLne2: '',
        //     maddrLne3: '',
        //     mcityName: '',
        //     mstateName: '',
        //     mAapfo: '',
        //     maa: '',
        //     mzipCode: '',
        // };
        // AppContext.model.setValue('addressReg', appstruct);

        let Validator = Lift.Validator;

        Validator.register('addressCheck', function (value, requirement) {
            var valFlag = true;
            var regex = /^[0-9]$/;
            var numCnt = 0;
            for (var i = 0, len = value.length; i < len; i++) {
                var substr = value.substring(i, i + 1);

                if (substr.match(regex)) {
                    numCnt++;
                    if (numCnt >= 9) {
                        valFlag = false;
                    }
                } else {
                    numCnt = 0;
                }
            }
            return valFlag;
        });

        Validator.register('splCharCheck', function (value, requirement) {
            var pattern = /^[a-zA-Z0-9- ]+$/g;
            return pattern.test(value)
        });

        Validator.register('cityCheck', function (value, requirement) {
            var pattern = /[A-Z]+$/i;
            return pattern.test(value);
        });

        Validator.register('zipCheck', function (value, requirement) {
            var pattern = /(\d{5})/;
            return pattern.test(value);
        });
    }

    chgResidCounty = event => {
        if (event.target.value !== 'GA') {
            AppContext.model.setValue('ARRAD.ArAppAddrCargo[0].addrCountyCd', '999');
        } else {
            AppContext.model.setValue('ARRAD.ArAppAddrCargo[0].addrCountyCd', '');
        }
    };

    validateResInfo = () => {
        if (
            !(
                AppContext.model.getValue('ARRAD.ArAppAddrCargo[0].addrLine1') &&
                AppContext.model.getValue('ARRAD.ArAppAddrCargo[0].addrCity') &&
                AppContext.model.getValue('ARRAD.ArAppAddrCargo[0].addrStNm') &&
                AppContext.model.getValue('ARRAD.ArAppAddrCargo[0].addrZip5')
            )
        ) {
            AppContext.pagedetails.getPageContext().stateHandler('msgsList', [errorMessages.GL162]);
            AppContext.pagedetails.getPageContext().stateHandler('errorMsg', true);
            window.scrollTo(0, 0);
        } else {
            AppContext.pagedetails.getPageContext().stateHandler('errorMsg', false);
        }
    };

    validateMailInfo = () => {
        if (
            !(
                AppContext.model.getValue('ARRAD.ArAppAddrCargo[1].addrLine1') &&
                AppContext.model.getValue('ARRAD.ArAppAddrCargo[1].addrCity') &&
                AppContext.model.getValue('ARRAD.ArAppAddrCargo[1].addrStNm') &&
                AppContext.model.getValue('ARRAD.ArAppAddrCargo[1].addrZip5')
            )
        ) {
            AppContext.pagedetails.getPageContext().stateHandler('msgsList', [errorMessages.GL162]);
            AppContext.pagedetails.getPageContext().stateHandler('errorMsg', true);
            window.scrollTo(0, 0);
        } else {
            AppContext.pagedetails.getPageContext().stateHandler('errorMsg', false);
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

    maildiff = () => {
        if (AppContext.model.getValue('ARRAD.anon') === 'true') {
            let x = document.getElementById('section3');
            x.style.display = 'none';
        } else {
            let y = document.getElementById('section3');
            y.style.display = 'block';
        }
    };
}

export default Address;
