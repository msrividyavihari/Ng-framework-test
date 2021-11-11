import { AppContext, Util } from '@d-lift/core';
class SearchDocumentsService {
    searchDocumentsService = async searchDocumentsData => {
        let searchDocumentsURL = AppContext.config.searchDocumentsCO;
        let searchDocumentsResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        const inputJSON = {
            caseApp: searchDocumentsData.searchCriteria,
            caseAppNum: searchDocumentsData.caseNumber,
            clientId: searchDocumentsData.clientId,
            ssn: searchDocumentsData.ssnId,
            firstName: searchDocumentsData.firstName,
            lastName: searchDocumentsData.lastName,
            middleName: searchDocumentsData.middleName,
            refNumber: searchDocumentsData.refNumber,
            transactionNumber: searchDocumentsData.transactionNumber,
            docType: searchDocumentsData.docType,
            beginDate: searchDocumentsData.startDate,
            toDate: searchDocumentsData.endDate,
            empId:"773589"
        };
        try {
            let response = await Util.HTTP.post(searchDocumentsURL, inputJSON, headers);
            console.log(response);
            if (response.data.message.code === 100) {
                searchDocumentsResponse = {
                    foundRecords: response.data.data,
                    success: true,
                };
            } else {
                searchDocumentsResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            searchDocumentsResponse.success = false;
        }
        console.log('Search Documents Service Called');
        return searchDocumentsResponse;
    };

    getDocumentStreamService = async docuedgeDocumentId => {
        let viewDocumentURL = AppContext.config.viewDocumentCO;
        let viewDocumentResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        const inputJSON = {  
            docId: docuedgeDocumentId,
            action: "view"
        };
        try {
            let response = await Util.HTTP.post(viewDocumentURL, inputJSON, headers);
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
                viewDocumentResponse = {
                    success: true,
                };
            } else {
                viewDocumentResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            viewDocumentResponse.success = false;
        }
        console.log('Document Stream Service Called');
        return viewDocumentResponse;
    };

    metaDataChangeService = async metaData => {
        let metaDataURL = AppContext.config.metaDataCO;
        let metaDataResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        const inputJSON = {
            disDocMstrSeqNum: metaData.disDocMstrSeqNum,
            caseAppNum: metaData.caseNum,
            originalDocumentType: metaData.origDocType,
            modifiedDocumentType: metaData.modifiedDocumentType,
            actionType: metaData.actionType,
            referenceNo: metaData.disDocMstrSeqNum,
            indvId: metaData.indvId,
            originalSsn: metaData.originalSsn,
            modifiedSsn: metaData.modifiedSsn,
            docuedgeDocumentId: metaData.docuedgeDocumentId,

        };
        try {
            let response = await Util.HTTP.post(metaDataURL, inputJSON, headers);
            console.log(response);
            if (response.data.message.code === 100) {
                metaDataResponse = {
                    foundRecords: response.data.data,
                    success: true,
                };
            } else {
                metaDataResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            metaDataResponse.success = false;
        }
        console.log('Meta Data Service Called');
        return metaDataResponse;
    };

    linkDelinkService = async linkDelinkData => {
        let linkDelinkURL = AppContext.config.linkDelinkCO;
        let linkDelinkResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        const inputJSON = {
            docuedgeDocumentId: linkDelinkData.docuedgeDocumentId,
            pageId: linkDelinkData.pageId,
            referenceNo:linkDelinkData.disDocMstrSeqNum,
            documentTypeCd: linkDelinkData.docType,
            caseAppNum: linkDelinkData.caseNum,
            caseNo: linkDelinkData.caseNo,
            application : linkDelinkData.appNo,
            actionType: linkDelinkData.actionType,
            documentDescription: linkDelinkData.documentDescription,
            clientId: linkDelinkData.client,
            indvId: linkDelinkData.indvId
        };
        try {
            let response = await Util.HTTP.post(linkDelinkURL, inputJSON, headers);
            console.log(response);
            if (response.data.message.code === 100) {
                linkDelinkResponse = {
                    foundRecords: response.data.data.documentDynaCargos,
                    message:response.data.data.messageCodes,
                    success: true,
                };
            } else {
                linkDelinkResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            linkDelinkResponse.success = false;
        }
        console.log('Link Delink Service Called');
        return linkDelinkResponse;
    };

    retrieveService = async linkDelinkData => {
        let retrieveURL = AppContext.config.linkDelinkCO;
        let retrieveResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        let caseNum="";
        if(linkDelinkData.caseNo!=undefined){
            caseNum = linkDelinkData.caseNo;
        }else{
            caseNum = linkDelinkData.appNo;
        }
        const inputJSON = {
            docuedgeDocumentId: linkDelinkData.docuedgeDocumentId,
            pageId: 'DMVLD',
            documentTypeCd: linkDelinkData.docType,
            caseAppNum: caseNum,
            actionType: 'Retrieve'
        };
        try {
            let response = await Util.HTTP.post(retrieveURL, inputJSON, headers);
            console.log(response);
            if (response.data.message.code === 100) {
                retrieveResponse = {
                    foundRecords: response.data.data,
                    success: true
                };
            } else {
                retrieveResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            retrieveResponse.success = false;
        }
        console.log('Retrieve Service Called');
        return retrieveResponse;
    };
}

const searchDocumentsService = new SearchDocumentsService();
export default searchDocumentsService;
