import { AbstractDataAdapter } from '@d-lift/core';
import { Util } from '@d-lift/core';
import _ from 'lodash';
import { AppContext } from '@d-lift/core';
import { ResponseHandlers } from '@/Utills/DataAdapter/DataAdaptedUtill';
import basicAxiosInterceptor from '@/HttpInterceptors/BasicAxiosInterCeptor';
import spinnerUtil from '@/Utills/SpinnerUtil/SpinnerUtill';

/**
 * Data Adapter to define and massage Data on Load and Presist
 */
class DataAdapter extends AbstractDataAdapter {
    invokeLoad = async (data, loadActionUrl) => {
        let serviceResponse = await Util.HTTP.post(this.serviceEndpoint + loadActionUrl, data);
        if (serviceResponse !== undefined && serviceResponse.data !== undefined) {
            AppContext.pagedetails
                .getPageContext()
                .stateHandler(
                    AppContext.pagedetails.getPageContext().saveModelString !== null
                        ? AppContext.pagedetails.getPageContext().saveModelString + '_pristine'
                        : AppContext.pagedetails.getPageContext().pageConfigJson.pageId +
                              '_pristine',
                    serviceResponse.data,
                );
        }
        return serviceResponse;
    };

    invokePresist = async (data, presistActionUrl) => {
        let previousPageId = data.currentPageID;
        let serviceResponse = await Util.HTTP.post(this.serviceEndpoint + presistActionUrl, data);
        if (serviceResponse !== undefined && serviceResponse.data !== undefined) {
            if (
                !_.isUndefined(serviceResponse.data.validationMessages) &&
                !_.isNull(serviceResponse.data.validationMessages)
            ) {
                AppContext.pagedetails
                    .getPageContext()
                    .stateHandler(
                        'msgsList',
                        Util.buildErrorMessage(serviceResponse.data.validationMessages),
                    );
                AppContext.pagedetails.getPageContext().stateHandler('errorMsg', true);
                window.scrollTo(0, 0);
            } else {
                if (serviceResponse.data.nextPageID === previousPageId) {
                    AppContext.pagedetails
                        .getPageContext()
                        .stateHandler(
                            AppContext.pagedetails.getPageContext().saveModelString !== null
                                ? AppContext.pagedetails.getPageContext().saveModelString
                                : AppContext.pagedetails.getPageContext().pageConfigJson.pageId,
                            serviceResponse.data,
                        );
                    AppContext.pagedetails.getPageContext().stateHandler('errorMsg', false);
                } else {
                    AppContext.pagedetails.getPageContext().props.history.push({
                        pathname:
                            '/' +
                            AppContext.pagedetails.getRootContext() +
                            '/' +
                            serviceResponse.data.nextPageID,
                        state: {
                            model: _.set({}, serviceResponse.data.nextPageID, serviceResponse.data),
                        },
                    });
                }
            }
        }
        return serviceResponse;
    };

    invokeHtttpCall = async (type, data, basePath, pathUri) => {
        spinnerUtil.show();
        try {
            let response = await basicAxiosInterceptor({
                method: type,
                url: `${AppContext.config[basePath]}${pathUri}`,
                data: {
                    ...data,
                },
            });
            spinnerUtil.hide();
            return ResponseHandlers.handleResponse({ ...response.data });
        } catch (error) {
            spinnerUtil.hide();
            ResponseHandlers.handleExceptions(error.response.data.message);
        }
    };
}

/**
 * Data Adapter to define and massage Data on Load and Presist
 */
const dataAdapter = new DataAdapter();
export default dataAdapter;
