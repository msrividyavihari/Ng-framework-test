import { AppContext, Util } from '@d-lift/core';
class ViewHistoryDetailService {
    viewHistoryDetailService = async viewHistoryData => {
        let viewHistoryDetailURL = AppContext.config.viewHistoryDetailCO;
        let viewHistoryDetailResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        const inputJSON = {  
            t2CoReqSeq : viewHistoryData.coReqSeq,
            coDetSeq : viewHistoryData.coDetSeq,
            rptSeq : viewHistoryData.coRptSeq
        };
        try {
            let response = await Util.HTTP.post(viewHistoryDetailURL, inputJSON, headers);
            console.log(response);
            if (response.data.message.code === 100) {
                viewHistoryDetailResponse = {
                    foundRecords: response.data.data,
                    success: true,
                };
            } else {
                viewHistoryDetailResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            viewHistoryDetailResponse.success = false;
        }
        console.log('View History Detail Service Called');
        return viewHistoryDetailResponse;
    };

    centralReprintService = async viewHistoryDet => {
        let centralReprintURL = AppContext.config.centralReprintCO;
        let centralReprintResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        const inputJSON = {  
            t2CoReqSeq : viewHistoryDet.t2CoReqSeq,
            coDetSeq : viewHistoryDet.coDetSeq,
            rptSeq : viewHistoryDet.coRptSeq,
            disDocMstrSeqNum : viewHistoryDet.disDocMstrSeqNum
        };
        try {
            let response = await Util.HTTP.post(centralReprintURL, inputJSON, headers);
            console.log(response);
            if (response.data.message.code === 100) {
                centralReprintResponse = {
                    foundRecords: response.data.data,
                    success: true,
                };
            } else {
                centralReprintResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            centralReprintResponse.success = false;
        }
        console.log('Central reprint Service Called');
        return centralReprintResponse;
    };

    localReprintService = async viewHistoryDet => {
        let localReprintURL = AppContext.config.localReprintCO;
        let localReprintResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        const inputJSON = {  
            t2CoReqSeq : viewHistoryDet.t2CoReqSeq,
            coDetSeq : viewHistoryDet.coDetSeq,
            coRptSeq : viewHistoryDet.coRptSeq,
            disDocMstrSeqNum :  viewHistoryDet.disDocMstrSeqNum
        };
        try {
            let response = await Util.HTTP.post(localReprintURL, inputJSON, headers);
//            console.log(response);
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
                localReprintResponse = {
                    success: true,
                };
            } else {
                localReprintResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            localReprintResponse.success = false;
        }
        console.log('Local reprint Service Called');
        return localReprintResponse;
    };

    previewService = async viewHistoryDet => {
        let previewURL = AppContext.config.previewCO;
        let previewResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        const inputJSON = {  
            t2CoReqSeq : viewHistoryDet.t2CoReqSeq,
            coDetSeq : viewHistoryDet.coDetSeq,
            coRptSeq : viewHistoryDet.coRptSeq,
            disDocMstrSeqNum :  viewHistoryDet.disDocMstrSeqNum
        };
        try {
            let response = await Util.HTTP.post(previewURL, inputJSON, headers);
            //console.log(response);
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

const viewHistoryDetailService = new ViewHistoryDetailService();
export default viewHistoryDetailService;
