import { AppContext, Util } from '@d-lift/core';
class NoticeRequestService {
    
    logRequestService = async logRequestData => {
        let logRequestURL = AppContext.config.noticeLatestStatus;
        let logRequestResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        const inputJSON = {
            metaData : {
                logRequestId: logRequestData.logRequestId
            }
        };
        try {
            let response = await Util.HTTP.post(logRequestURL, inputJSON, headers);
            console.log(response);
            if (response.data.message.code === 100) {
                logRequestResponse = {
                    foundRecords: response.data.data,
                    success: true,
                };
            } else {
                logRequestResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            logRequestResponse.success = false;
        }
        console.log('Fetch Notice Status using logRequestId');
        return logRequestResponse;
    };

    noticeRequestService = async noticeRequestData => {
        let noticeRequestURL = AppContext.config.noticeLatestStatus;
        let noticeRequestResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        const inputJSON = {
            metaData : {
                caseNum: noticeRequestData.caseNumber,
                requestId: noticeRequestData.noticeId,
                startDt: noticeRequestData.startDate,
                endDt:noticeRequestData.endDate
            }
        };
        try {
            let response = await Util.HTTP.post(noticeRequestURL, inputJSON, headers);
            console.log(response);
            if (response.data.message.code === 100) {
                noticeRequestResponse = {
                    foundRecords: response.data.data,
                    success: true,
                };
            } else {
                noticeRequestResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            noticeRequestResponse.success = false;
        }
        console.log('Fetch latest Notice Status Service Called');
        return noticeRequestResponse;
    };

    noticeStatusUpdateService = async noticeStatusUpdateData => {
        let noticeStatusUpdateURL = AppContext.config.updateStatus;
        let noticeStatusUpdateResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        const inputJSON = {
            metaData : {
                caseNum: noticeStatusUpdateData.caseNum,
                requestId: noticeStatusUpdateData.noticeRequestId,
                templateId : noticeStatusUpdateData.templateId,
                agencyId : noticeStatusUpdateData.agencyId,
                requestDate : noticeStatusUpdateData.requestDate,
                agencyName : noticeStatusUpdateData.agencyName,
                agencyCode: noticeStatusUpdateData.agencyCode,
                addressUpdated : noticeStatusUpdateData.addressUpdated,
                clientName: noticeStatusUpdateData.clientName,
                watermark : noticeStatusUpdateData.waterMark,
                goGreen : noticeStatusUpdateData.goGreen,
                hohId : noticeStatusUpdateData.hohId ,
                preferredLanguage : noticeStatusUpdateData.language,
                securityFlag : noticeStatusUpdateData.securityFlag,
                formTitle : noticeStatusUpdateData.formTitle,
                status: noticeStatusUpdateData.status
            },
            requestJson : noticeStatusUpdateData.requestJson
        };
        try {
            let response = await Util.HTTP.post(noticeStatusUpdateURL, inputJSON, headers);
            console.log(response);
            if (response.data.message.code === 100) {
                noticeStatusUpdateResponse = {
                    foundRecords: response.data.data,
                    success: true,
                };
            } else {
                noticeStatusUpdateResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            noticeStatusUpdateResponse.success = false;
        }
        console.log('Notice Status Update Service Called');
        return noticeStatusUpdateResponse;
    };

    viewPendingPreviewService = async (previewData) => {
        let previewURL = AppContext.config.previewViewPending;
        let jsonData = {};
        let previewResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };

        jsonData = {
            "coReqSeq": previewData.logRequestId,
            "action": "viewPreview"
        };

        try {
            let response = await Util.HTTP.post(previewURL, jsonData, headers);
            console.log(response);
            if (response.status === 200) {
                let binaryString = window.atob(response.data);
                let binaryLen = binaryString.length;
                let bytes = new Uint8Array(binaryLen);
                for (let i = 0; i < binaryLen; i++) {
                    let ascii = binaryString.charCodeAt(i);
                    bytes[i] = ascii;
                }
                const file = new Blob([bytes],{type: 'application/pdf'});
                const fileURL = URL.createObjectURL(file);
                // //Open the URL on new Window
                window.open(fileURL);               
                previewResponse = {
                    success: true,
                };
            } else {
                previewResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            previewResponse.success = false;
        }
        return previewResponse;
    };

    viewPendingCentralPrintService = async (centralPrintData) => {
        let previewURL = AppContext.config.centralPrintViewPending;
        let jsonData = {};
        let previewResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };

        jsonData = {
            "coReqSeq": centralPrintData.logRequestId,
            "action": "viewCentralPrint"
        };
        
        try {
            let response = await Util.HTTP.post(previewURL, jsonData, headers);
            console.log(response);
            if (response.status === 200) {
                previewResponse= {
                foundRecords: response.data.data,
                success: true,
            };
        } else{
             previewResponse.success=false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            previewResponse.success = false;
        }
        return previewResponse;
    };

    viewPendinglocalPrintService = async (localPrintData) => {
        let previewURL = AppContext.config.localPrintViewPending;
        let jsonData = {};
        let previewResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };

        jsonData = {
            "coReqSeq": localPrintData.logRequestId,
            "action": "viewLocalPrint"
        };
        
        try {
            let response = await Util.HTTP.post(previewURL, jsonData, headers);
            console.log(response);
            if (response.status === 200) {
                let binaryString = window.atob(response.data);
                let binaryLen = binaryString.length;
                let bytes = new Uint8Array(binaryLen);
                for (let i = 0; i < binaryLen; i++) {
                    let ascii = binaryString.charCodeAt(i);
                    bytes[i] = ascii;
                }
                const file = new Blob([bytes],{type: 'application/pdf'});
                const fileURL = URL.createObjectURL(file);
                // //Open the URL on new Window
                window.open(fileURL);               
                previewResponse = {
                    success: true,
                };
            } else {
                previewResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            previewResponse.success = false;
        }
        return previewResponse;
    }

}

const noticeRequestService = new NoticeRequestService();
export default noticeRequestService;