import { AppContext, Lift } from '@d-lift/core';
import SiteConfig from '@/SiteConfig.json';
import { RefrenceTableAdapter, ContentManagerAdapter, DataAdapter } from '@/Adapters';
import SessionTimeoutModal from '@/Security/SessionTimeoutModal.rt';
import NextGen360 from '@d-lift/theme-nextgen360';

global.appconfig = {};
global.appconfig.host = '';
global.appconfig.contextroot = '/';
window.scrollTo = jest.fn();
Lift.SiteConfig = SiteConfig;
Lift.AppConfig = global.appconfig;
Lift.DataAdapter = DataAdapter;
Lift.Themes = NextGen360;
Lift.RefrenceTableAdapter = RefrenceTableAdapter;
Lift.ContentManagerAdapter = ContentManagerAdapter;
Lift.SessionTimeoutModal = SessionTimeoutModal;
AppContext.setSiteConfig(SiteConfig);
