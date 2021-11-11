import Template from './NotFound.rt';
import { PageConfig, UXPage } from '@d-lift/core';
import NotFoundImage from './images/error.gif';
import './NotFound.scss';

@PageConfig({
    Template: Template,
    Path: '/notfound',
})
class NotFound extends UXPage {
    constructor(props) {
        super(props);
        this.notFoundImage = NotFoundImage;
    }
}

export default NotFound;
