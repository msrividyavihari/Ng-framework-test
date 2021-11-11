import { configure } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
import 'babel-polyfill';
global.appconfig = {};
global.appconfig.host = '';
global.appconfig.contextroot = '/';
configure({ adapter: new Adapter() });
