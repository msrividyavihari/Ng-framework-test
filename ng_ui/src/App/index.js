import 'react-app-polyfill/ie11';
import 'react-app-polyfill/stable';
import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import RouteConfigJson from '@/PublicRouteConfig.json';
import SecureRouteConfigJson from '@/PrivateRouteConfig.json';
import { Lift } from '@d-lift/core';
import NextGen360 from '@d-lift/theme-nextgen360';
import '@d-lift/theme-nextgen360/dist/styles/NextGen360.css';
import CustomeDataTable from '@/UXModule/CustomeDataTable';
import CustomCollapsePanel from '@/UXModule/CustomCollapsePanel';
import CustomApplicantCards from '@/UXModule/CustomApplicantCards';
import CustomCheckBox from '@/UXModule/CustomCheckBox';
import CustomSelectableCards from '@/UXModule/CustomSelectableCards';

const initializeLift = () => {
    //Lift Initialization
    Lift.setRouterFile(RouteConfigJson, SecureRouteConfigJson);
    Lift.InstallExtension(CustomeDataTable.as('CustomeDataTable'));
    Lift.InstallExtension(CustomCollapsePanel.as('CustomCollapsePanel'));
    Lift.InstallExtension(CustomApplicantCards.as('CustomApplicantCards'));
    Lift.InstallExtension(CustomCheckBox.as('CustomCheckBox'));
    Lift.InstallExtension(CustomSelectableCards.as('CustomSelectableCards'));
    Lift.Themes = NextGen360;
    Lift.AppConfig = global.appconfig;
    //App rendering
    ReactDOM.render(<App />, document.getElementById('root'));
};

initializeLift();
