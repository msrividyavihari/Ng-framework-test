import { Util } from '@d-lift/core';
import { AbstractContentManagerAdapter } from '@d-lift/core';
import { AppContext } from '@d-lift/core';

/**
 * Content Manager Adapter to define and massage Content Manager Data
 */
class ContentManagerAdapter extends AbstractContentManagerAdapter {
    invoke = async locale => {
        let serviceResponse = await Util.HTTP.get(
            this.contentManagerEndpoint +
                AppContext.pagedetails.getRootContext() +
                '/' +
                AppContext.pagedetails.getPageName() +
                '/' +
                locale +
                '.json',
        );
        return serviceResponse;
    };
    getAppCommonContent = async locale => {
        let serviceResponse = await Util.HTTP.get(
            this.contentManagerEndpoint + 'Common/' + locale + '.json',
        );
        return serviceResponse;
    };
}

/**
 * Content Manager Adapter to define and massage Content Manager Data
 */
const contentManagerAdapter = new ContentManagerAdapter();
export default contentManagerAdapter;
