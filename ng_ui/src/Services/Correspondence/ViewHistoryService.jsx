import { AppContext, Util } from '@d-lift/core';
class ViewHistoryService {
    viewHistoryService = async viewHistoryData => {
        let viewHistoryURL = AppContext.config.viewHistoryCO;
        let viewHistoryResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        const inputJSON = {
            searchCriteria: viewHistoryData.searchCriteria,
            caseNum: viewHistoryData.caseNumber,
            appNum: viewHistoryData.appNumber,
            clientId: viewHistoryData.clientId,
            workerName: viewHistoryData.workerName,
            workerId: viewHistoryData.workerId,
            reqDt: viewHistoryData.printStartDate,
            printDt: viewHistoryData.printEndDate,
            benefitMonStartDate: viewHistoryData.benefitMonStartDate,
            benefitMonEndDate: viewHistoryData.benefitMonEndDate,
            ccCertStartDt: viewHistoryData.ccCertStartDate,
            ccCertEndDt: viewHistoryData.ccCertEndDate,
            ccProviderId: viewHistoryData.ccProviderId,
            printMode: 'B',
            docId: 'FGG5460',
            programCd: 'TF'
        };
        try {
            let response = await Util.HTTP.post(viewHistoryURL, inputJSON, headers);
            console.log(response);
            if (response.data.message.code === 100) {
                viewHistoryResponse = {
                    foundRecords: response.data.data,
                    success: true,
                };
            } else {
                viewHistoryResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            viewHistoryResponse.success = false;
        }
        console.log('View History Service Called');
        return viewHistoryResponse;
    };

}

const viewHistoryService = new ViewHistoryService();
export default viewHistoryService;
