import { AppContext, Util } from '@d-lift/core';
class GenerateManualService {
    
    generateManualService = async generateManualData => {
        let generateManualURL = AppContext.config.caseSearch;
        let casesearchResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        const inputJSON = {
            caseNum: generateManualData.caseNumber,
        };
        try {
            let response = await Util.HTTP.post(generateManualURL, inputJSON, headers);
            console.log(response);
            if (response.data.message.code === 100) {
                casesearchResponse = {
                    foundRecords: response.data,
                    success: true,
                };
            } else {
                casesearchResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            casesearchResponse.success = false;
        }
        console.log('Generate Manual Service Called');
        return casesearchResponse;
    };

    callCoMasterService = async generateManualData => {
        let callCoMasterURL = AppContext.config.callCoMaster;
        let casesearchResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        try {
            let response = await Util.HTTP.get(callCoMasterURL, headers);
            console.log(response);
            if (response.data.message.code === 100) {
                casesearchResponse = {                    
                    foundRecords: response.data,
                    success: true,
                };
            } else {
                casesearchResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            casesearchResponse.success = false;
        }
        console.log('Call CoMaster Service Called');
        return casesearchResponse;
    };

}

const generateManualService = new GenerateManualService();
export default generateManualService;