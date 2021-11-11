import { AppContext, Util } from '@d-lift/core';
import basicAxiosInterceptor from '@/HttpInterceptors/BasicAxiosInterCeptor';

class ViewPendingService {
    viewPendingService = async viewPendingData => {
        let viewPendingURL = AppContext.config.viewPendingCO;
        let viewPendingResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        const inputJSON = {
            searchCriteria: viewPendingData.searchCriteria,
            caseNum: viewPendingData.caseNumber,
            appNum: viewPendingData.appNumber,
            clientId: viewPendingData.clientId,
            workerName: viewPendingData.workerName,
            workerId: viewPendingData.workerId,
            ccCertStartDt: viewPendingData.ccCertStartDt,
            ccCertEndDt: viewPendingData.ccCertEndDt,
            coReqSeq: viewPendingData.coReqSeq,
            docId: viewPendingData.docId,
            ccProviderId: viewPendingData.ccProviderId,
            opentextInd: viewPendingData.viewPendingData
        };
        try {
            let response = await Util.HTTP.post(viewPendingURL, inputJSON, headers);
            console.log(response);
            if (response.data.message.code === 100) {
                viewPendingResponse = {
                    foundRecords: response.data.data,
                    success: true,
                };
            } else {
                viewPendingResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            viewPendingResponse.success = false;
        }
        console.log('View Pending Service Called');
        return viewPendingResponse;
    };

    viewPendingDetailService = async appNum => {
        let deleteAppResponse = {};
        try {
            let response = await basicAxiosInterceptor({
                method: 'POST',
                url: AppContext.config.deleteAppService,
                data: {
                    appNum: appNum,
                },
            });
            console.log(response);
            if (response.data.message.code === 100) {
                deleteAppResponse = {
                    responseType: { ...response.data.data },
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

const viewPendingService = new ViewPendingService();
export default viewPendingService;
