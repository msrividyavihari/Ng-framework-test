import Template from './FileClear.rt';
import { PageConfig, UXPage } from '@d-lift/core';

@PageConfig({
    ContextRoot: 'ApplicationRegistration',
    PageName: 'FileClear',
    Path: '/fileclear',
    Template: Template,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'File Clearence',
})
class FileClear extends UXPage {}

export default FileClear;
