import { AppContext, Util } from '@d-lift/core';
import basicAxiosInterceptor from '@/HttpInterceptors/BasicAxiosInterCeptor';
class AppSearch {
    searchAppService = async (appNum, ssn, firstName, lastName) => {
        let searchAppResponse = {};

        const inputJSON = {
            appNum: appNum,
            applicantSSN: ssn,
            applicantFirstName: firstName,
            applicantLastName: lastName,
        };
        try {
            let applicantResp = await basicAxiosInterceptor({
                method: 'POST',
                url: AppContext.config.searchAppService,
                data: { ...inputJSON },
            });
            if (applicantResp.data.message.code === 100) {
                searchAppResponse = {
                    foundApps: applicantResp.data.data,
                    success: true,
                };
            } else {
                searchAppResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            searchAppResponse.success = false;
        }
        console.log('Application Search Service Called');
        return searchAppResponse;
    };

    deleteAppService = async appNum => {
        let deleteAppResponse = {};
        try {
            //let response = await Util.HTTP.get(deleteAppUrl);

            let applicantResp = await basicAxiosInterceptor({
                method: 'PUT',
                url: AppContext.config.deleteAppService,
                data: {
                    appNum: appNum,
                },
            });
            console.log(applicantResp);
            if (applicantResp.data.message.code === 100) {
                deleteAppResponse = {
                    responseType: { ...applicantResp.data.data },
                    success: true,
                };
            } else {
                deleteAppResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            deleteAppResponse.success = false;
        }
        console.log('Delete Application Service Called');
        return deleteAppResponse;
    };
}

const appSearch = new AppSearch();
export default appSearch;
