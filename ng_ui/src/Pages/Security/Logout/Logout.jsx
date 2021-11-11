
import Template from './Logout.rt';
import { PageConfig, UXPage} from '@d-lift/core';

@PageConfig({
    ContextRoot: 'Security',
    PageName: 'Logout',
    Path: '/logout',
    Template: Template,
    
})
class Logout extends UXPage { }


export default Logout;
