import { AppContext, Util } from '@d-lift/core';
class MassMailingService {
    massMailingSummaryService = async massSummaryData => {
        let massMailingSummaryURL = AppContext.config.massMailingSummaryCO;
        let massMailingSummaryResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        try {
            let response = await Util.HTTP.post(massMailingSummaryURL,massSummaryData, headers);
            console.log(response);
            if (response.data.message.code === 100) {
                massMailingSummaryResponse = {
                    foundRecords: response.data.data,
                    success: true,
                };
            } else {
                massMailingSummaryResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            massMailingSummaryResponse.success = false;
        }
        console.log('Mass Mailing Summary Service Called');
        return massMailingSummaryResponse;
    };

    deleteMassMailingService = async massMailingData => {
        let deleteMassMailingURL = AppContext.config.deleteMassMailingCO;
        let deleteMassMailingResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        const inputJSON = {  
            massMailingSeqNum : massMailingData.massMailingSeqNum
        }
        try {
            let response = await Util.HTTP.post(deleteMassMailingURL, inputJSON, headers);
            console.log(response);
            if (response.data.message.code === 100) {
                deleteMassMailingResponse = {
                    foundRecords: response.data.data,
                    success: true,
                };
            } else {
                deleteMassMailingResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            deleteMassMailingResponse.success = false;
        }
        console.log('Delete selected Mass Mailing Service Called');
        return deleteMassMailingResponse;
    };

    createMassMailingService = async massMailingData => {
        let createMassMailingURL = AppContext.config.createMassMailingCO;
        let createMassMailingResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        let countyCd = [];
        for(let i=0;i<massMailingData.countyCd.length;i++){
            countyCd[i] = massMailingData.countyCd[i].value;
        }
        const inputJSON = {  
            massMailingSeqNum : massMailingData.massMailingSeqNum,
            recptPop : massMailingData.recptPop,
            scheduledDt : massMailingData.scheduledDt,
            noticeTitle : massMailingData.noticeTitle,
            noticeTxt : massMailingData.noticeText,
            coPolicyManualReference : massMailingData.policyManualRef,
            programLst : massMailingData.programLst,
            jobProcessedSw : 'N',
            countySelect : countyCd
        };
        try {
            let response = await Util.HTTP.post(createMassMailingURL, inputJSON, headers);
            console.log(response);
            if (response.data.message.code === 100) {
                createMassMailingResponse = {
                    foundRecords: response.data.data,
                    success: true,
                };
            } else {
                createMassMailingResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            createMassMailingResponse.success = false;
        }
        console.log('Create Mass Mailing Service Called');
        return createMassMailingResponse;
    };

    previewService = async massMailingData => {
        let previewURL = AppContext.config.massPreviewCO;
        let previewResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        const inputJSON = {  
            massMailingSeqNum: massMailingData.massMailingSeqNum,
            recptPop : massMailingData.recptPop,
            scheduledDt : massMailingData.scheduledDt,
            noticeTitle : massMailingData.noticeTitle,
            noticeTxt : massMailingData.noticeText,
            coPolicyManualReference : massMailingData.policyManualRef,
            programLst : massMailingData.programLst,
            jobProcessedSw : 'N',
        };
        try {
            let response = await Util.HTTP.post(previewURL, inputJSON, headers);
            console.log(response);
            if (response.status === 200) {
                console.log(response.data);
                let binaryString = window.atob(response.data);
                let binaryLen = binaryString.length;
                let bytes = new Uint8Array(binaryLen);
                for (let i = 0; i < binaryLen; i++) {
                    let ascii = binaryString.charCodeAt(i);
                    bytes[i] = ascii;
                }
                const fileNew = new Blob([bytes], {
                    type: 'application/pdf',
                });    
                const fileURL = URL.createObjectURL(fileNew);
                window.open(fileURL, "_blank");
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
        console.log('Preview Service Called');
        return previewResponse;
    };

}

const massMailingService = new MassMailingService();
export default massMailingService;
