import { AppContext, Util } from '@d-lift/core';
class AdditionalInfoService {

    callInitializeService = async initializeData => {
        let initializeAdditionalInfoURL = AppContext.config.initializeAdditionalInfo;
        let initializeResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        const inputJSON = {
            docId: "FGGA0015",
            coReqSeq: initializeData.coReqSeq,
            parentPageId: initializeData.parentPageID
        };

        try {
            let response = await Util.HTTP.post(initializeAdditionalInfoURL, inputJSON, headers);
            console.log(response);
            if (response.data.message.code === 100) {
                initializeResponse = {
                    initializeData: initializeData,
                    foundRecords: response.data.data,
                    success: true,
                };
            } else {
                initializeResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            initializeResponse.success = false;
        }
        console.log('call Initialize Service Called');
        return initializeResponse;
    }
    
    saveDraftService = async initializeData => {
        let saveDraftURL = AppContext.config.saveDraftGenerateManual;
        let saveDraftResponse = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        const inputJSON = {
            docId : "FGGA0015",
          //  coReqSeq : "94249170",
            AppealId : initializeData.AppealId,
            pageId : initializeData.pageId,
            response : initializeData.response,
            preview : "false",
            parentPageId : initializeData.parentPageId,
            caseAppNumber : "122256186",
            coManualFieldsMap : initializeData.coManualFieldsMap,
            fwDataElementListMap : initializeData.fwDataElementListMap,
            request : initializeData.additionalInfoData,

        };
        try {
            let response = await Util.HTTP.post(saveDraftURL, inputJSON, headers);
            console.log(response);
            if (response.data.message.code === 100) {
                saveDraftResponse = {
                    foundRecords: response.data.data,
                    success: true,
                };
            } else {
                saveDraftResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            saveDraftResponse.success = false;
        }
        console.log('Save Draft Service Called');
        return saveDraftResponse;
    };

    previewService = async initializeData => {
        let previewURL = {};
        let previewResponse = {};
        let inputJSON = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        if (initializeData.parentPageId === 'COMCI'){
            console.log("initializeData: ",initializeData);
            // Generate Manual call
            previewURL = AppContext.config.previewGenerateManual;
            inputJSON = {

                action : "genPreview",
                fullName: initializeData.generateManualDetail.fullName,
                watermark  : "Y",
                securityFlag : "N",
                formVersion : "1",
                formTitle: initializeData.generateManualDetail.docName,
                preferredLanguage : "English",
                goGreen : "N",
                agencyCode:"MASSHEALTH",
                agencyName: "MASS HEALTH AGENCY",
                SSN : "XXXX-XX-5455",
                clientDOB : "1960-05-18",
                systemDate : new Date().toJSON().slice(0, 10),

                mailDate: "2021-10-05",
                templateId: "MA-APPDENY-001",
                formNo: "MA-APPDENY-001",
                documentId: "437291141",
                // salutation: "",
                noticeDate: new Date().toJSON().slice(0, 10),
                reciepientSeqNum: "458979843",
                caseNum: initializeData.generateManualDetail.caseNumber,

                MASSHealthMedicaid : {
                        "Individual" : [
                             {
                            "Name" : "John Lewin",
                            "MemberId" : "123",
                            "IndividualDOB" : "03-12-1980",
                            "Program" : "MSPA",
                            "EffectiveDate" : "01-01-2020",
                            "ClosureDate" : "06-30-2020",
                            "ProgramClosureReason" : "Age"
                        },
                         {
                            "Name" : "John Lewin",
                            "MemberId" : "123",
                            "IndividualDOB" : "03-12-1980",
                            "Program" : "MFPA",
                            "EffectiveDate" : "07-01-2020",
                            "ClosureDate" : "",
                            "ProgramClosureReason" : ""
                        } 
					],
					"Householdsize" : "",
					"HHFPLLimit" : ""
	            },


                // docId : initializeData.generateManualDetail.docId,
                docId : "MA-APPDENY-001",
               // coReqSeq : "94252814",
                indivId : initializeData.generateManualDetail.generateManual.clientId,
                AppealId : initializeData.AppealId,
                pageId : initializeData.pageId,
                response : initializeData.response,
                preview : "true",
                parentPageId : initializeData.parentPageId,
                caseAppNumber : initializeData.generateManualDetail.caseNumber,
                coManualFieldsMap : initializeData.coManualFieldsMap,
                fwDataElementListMap : initializeData.fwDataElementListMap,
                request : initializeData.additionalInfoData,
                mailingAdd:{
                    street1:initializeData.additionalInfoData.street1,
                    street2:initializeData.additionalInfoData.street2,
                    city:initializeData.additionalInfoData.city,
                    state:initializeData.additionalInfoData.state,
                    zip5:initializeData.additionalInfoData.zip5,
                    zip4:initializeData.additionalInfoData.zip4
                }
            }
        }
        else{
             // View Pending call
            previewURL = AppContext.config.previewViewPending;
            const generateManualDTO = {
                docId : initializeData.ViewPendingDetail.docId,
                coReqSeq : initializeData.ViewPendingDetail.coReqSeq,
                AppealId : initializeData.AppealId,
                pageId : initializeData.pageId,
                response : initializeData.response,
                parentPageId : initializeData.parentPageId,
                caseAppNumber : initializeData.ViewPendingDetail.caseAppNumber,
                coManualFieldsMap : initializeData.coManualFieldsMap,
                fwDataElementListMap : initializeData.fwDataElementListMap,
                request : initializeData.additionalInfoData,
            };
            inputJSON = {
                action : "viewPreview",
                fullName: initializeData.ViewPendingDetail.fullName,
                watermark  : "Y",
                securityFlag : "N",
                formVersion : "1",
                formTitle: initializeData.ViewPendingDetail.docName,
                preferredLanguage : "English",
                goGreen : "N",
                agencyCode:"MASSHEALTH",
                agencyName: "MASS HEALTH AGENCY",
                SSN : "XXXX-XX-5455",
                clientDOB : "1960-05-18",
                systemDate : "2021-02-28",
                MASSHealthMedicaid : {
                    "Individual" : [
                         {
                        "Name" : "John Lewin",
                        "MemberId" : "123",
                        "IndividualDOB" : "03-12-1980",
                        "Program" : "MSPA",
                        "EffectiveDate" : "01-01-2020",
                        "ClosureDate" : "06-30-2020",
                        "ProgramClosureReason" : "Age"
                    },
                     {
                        "Name" : "John Lewin",
                        "MemberId" : "123",
                        "IndividualDOB" : "03-12-1980",
                        "Program" : "MFPA",
                        "EffectiveDate" : "07-01-2020",
                        "ClosureDate" : "",
                        "ProgramClosureReason" : ""
                    } 
                ],
                "Householdsize" : "",
                "HHFPLLimit" : ""
                },
                previewVal : "true",
                searchCriteria  : initializeData.ViewPendingDetail.searchCriteria,
                caseNum : initializeData.ViewPendingDetail.caseAppNumber,
                appNum : initializeData.ViewPendingDetail.caseAppNumber,
                clientId : initializeData.ViewPendingDetail.clientId,
                workerName : initializeData.ViewPendingDetail.workerName,
                workerId : initializeData.ViewPendingDetail.workerId,
                ccCertStartDt : initializeData.ViewPendingDetail.ccCertStartDt,
                ccCertEndDt : initializeData.ViewPendingDetail.ccCertEndDt,
                coReqSeq : initializeData.ViewPendingDetail.coReqSeq,
                docId : initializeData.ViewPendingDetail.docId,
                opentextInd:  initializeData.ViewPendingDetail.opentextInd,
                generateManualDTO : generateManualDTO
            }     
        };
        try {
            let response = await Util.HTTP.post(previewURL, inputJSON, headers);
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
        console.log('preview Service Called');
        return previewResponse;
    };

    centralPrintService = async initializeData => {
        let centralPrintURL = {};
        let centralPrintResponse = {};
        let inputJSON = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        if (initializeData.parentPageId === 'COMCI'){
            // Generate Manual call
            centralPrintURL = AppContext.config.centralPrintGenerateManual;
            inputJSON = {
                action : "genCentralPrint",
                fullName: initializeData.generateManualDetail.fullName,
                watermark  : "Y",
                securityFlag : "N",
                formVersion : "1",
                formTitle: initializeData.generateManualDetail.docName,
                preferredLanguage : "English",
                goGreen : "N",
                agencyCode:"MASSHEALTH",
                agencyName: "MASS HEALTH AGENCY",
                SSN : "XXXX-XX-5455",
                clientDOB : "1960-05-18",
                systemDate : new Date().toJSON().slice(0, 10),

                mailDate: "2021-10-05",
                templateId: "MA-APPDENY-001",
                formNo: "MA-APPDENY-001",
                documentId: "437291141",
                // salutation: "",
                noticeDate: new Date().toJSON().slice(0, 10),
                reciepientSeqNum: "458979843",
                caseNum: initializeData.generateManualDetail.caseNumber,

                MASSHealthMedicaid : {
                    "Individual" : [
                         {
                        "Name" : "John Lewin",
                        "MemberId" : "123",
                        "IndividualDOB" : "03-12-1980",
                        "Program" : "MSPA",
                        "EffectiveDate" : "01-01-2020",
                        "ClosureDate" : "06-30-2020",
                        "ProgramClosureReason" : "Age"
                        },
                        {
                            "Name" : "John Lewin",
                            "MemberId" : "123",
                            "IndividualDOB" : "03-12-1980",
                            "Program" : "MFPA",
                            "EffectiveDate" : "07-01-2020",
                            "ClosureDate" : "",
                            "ProgramClosureReason" : ""
                        } 
                    ],
                    "Householdsize" : "",
                    "HHFPLLimit" : ""
                },

                // docId : initializeData.generateManualDetail.docId,
                docId : "MA-APPDENY-001",
                //coReqSeq : "94252814",
                indivId : initializeData.generateManualDetail.generateManual.clientId,
                AppealId : initializeData.AppealId,
                pageId : initializeData.pageId,
                response : initializeData.response,
                preview : "false",
                parentPageId : initializeData.parentPageId,
                caseAppNumber : initializeData.generateManualDetail.caseNumber,
                coManualFieldsMap : initializeData.coManualFieldsMap,
                fwDataElementListMap : initializeData.fwDataElementListMap,
                request : initializeData.additionalInfoData,
                mailingAdd:{
                    street1:initializeData.additionalInfoData.street1,
                    street2:initializeData.additionalInfoData.street2,
                    city:initializeData.additionalInfoData.city,
                    state:initializeData.additionalInfoData.state,
                    zip5:initializeData.additionalInfoData.zip5,
                    zip4:initializeData.additionalInfoData.zip4
                } 
            }
        }
        else{
             // View Pending call
             centralPrintURL = AppContext.config.centralPrintViewPending;
             const generateManualDTO = {
                docId : initializeData.ViewPendingDetail.docId,
                coReqSeq : initializeData.ViewPendingDetail.coReqSeq,
                AppealId : initializeData.AppealId,
                pageId : initializeData.pageId,
                response : initializeData.response,
                preview : "false",
                parentPageId : initializeData.parentPageId,
                caseAppNumber : initializeData.ViewPendingDetail.caseAppNumber,
                coManualFieldsMap : initializeData.coManualFieldsMap,
                fwDataElementListMap : initializeData.fwDataElementListMap,
                request : initializeData.additionalInfoData,
            };
            inputJSON = {
                action : "viewCentralPrint",
                fullName: initializeData.ViewPendingDetail.fullName,
                watermark  : "Y",
                securityFlag : "N",
                formVersion : "1",
                formTitle: initializeData.ViewPendingDetail.docName,
                preferredLanguage : "English",
                goGreen : "N",
                agencyCode:"MASSHEALTH",
                agencyName: "MASS HEALTH AGENCY",
                SSN : "XXXX-XX-5455",
                clientDOB : "1960-05-18",
                systemDate : "2021-02-28",
                MASSHealthMedicaid : {
                    "Individual" : [
                         {
                        "Name" : "John Lewin",
                        "MemberId" : "123",
                        "IndividualDOB" : "03-12-1980",
                        "Program" : "MSPA",
                        "EffectiveDate" : "01-01-2020",
                        "ClosureDate" : "06-30-2020",
                        "ProgramClosureReason" : "Age"
                        },
                        {
                            "Name" : "John Lewin",
                            "MemberId" : "123",
                            "IndividualDOB" : "03-12-1980",
                            "Program" : "MFPA",
                            "EffectiveDate" : "07-01-2020",
                            "ClosureDate" : "",
                            "ProgramClosureReason" : ""
                        } 
                    ],
                    "Householdsize" : "",
                    "HHFPLLimit" : ""
                },
                previewVal : "true",
                searchCriteria  : initializeData.ViewPendingDetail.searchCriteria,
                caseNum : initializeData.ViewPendingDetail.caseAppNumber,
                appNum : initializeData.ViewPendingDetail.caseAppNumber,
                clientId : initializeData.ViewPendingDetail.clientId,
                workerName : initializeData.ViewPendingDetail.workerName,
                workerId : initializeData.ViewPendingDetail.workerId,
                ccCertStartDt : initializeData.ViewPendingDetail.ccCertStartDt,
                ccCertEndDt : initializeData.ViewPendingDetail.ccCertEndDt,
                coReqSeq : initializeData.ViewPendingDetail.coReqSeq,
                docId : initializeData.ViewPendingDetail.docId,
                generateManualDTO : generateManualDTO
            }   
        };
        try {
            let response = await Util.HTTP.post(centralPrintURL, inputJSON, headers);
            if (response.status === 200) {              
                centralPrintResponse = {
                    foundRecords: response.data.data,
                    success: true,
                };
            } else {
                centralPrintResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            centralPrintResponse.success = false;
        }
        console.log('centralPrint Service Called');
        return centralPrintResponse;
    };

    localPrintService = async initializeData => {
        let localPrintURL = {};
        let localPrintResponse = {};
        let inputJSON = {};
        var headers = {
            'Content-Type': 'application/json',
        };
        if (initializeData.parentPageId === 'COMCI'){
            // Generate Manual call
            localPrintURL = AppContext.config.localPrintGenerateManual;
            inputJSON = {
                
                action : "genLocalPrint",
                fullName: initializeData.generateManualDetail.fullName,
                watermark  : "Y",
                securityFlag : "N",
                formVersion : "1",
                formTitle: initializeData.generateManualDetail.docName,
                preferredLanguage : "English",
                goGreen : "N",
                agencyCode:"MASSHEALTH",
                agencyName: "MASS HEALTH AGENCY",
                SSN : "XXXX-XX-5455",
                clientDOB : "1960-05-18",
                systemDate : new Date().toJSON().slice(0, 10),

                mailDate: "2021-10-05",
                templateId: "MA-APPDENY-001",
                formNo: "MA-APPDENY-001",
                documentId: "437291141",
                // salutation: "",
                noticeDate: new Date().toJSON().slice(0, 10),
                reciepientSeqNum: "458979843",
                caseNum: initializeData.generateManualDetail.caseNumber,

                MASSHealthMedicaid : {
                    "Individual" : [
                         {
                        "Name" : "John Lewin",
                        "MemberId" : "123",
                        "IndividualDOB" : "03-12-1980",
                        "Program" : "MSPA",
                        "EffectiveDate" : "01-01-2020",
                        "ClosureDate" : "06-30-2020",
                        "ProgramClosureReason" : "Age"
                        },
                        {
                            "Name" : "John Lewin",
                            "MemberId" : "123",
                            "IndividualDOB" : "03-12-1980",
                            "Program" : "MFPA",
                            "EffectiveDate" : "07-01-2020",
                            "ClosureDate" : "",
                            "ProgramClosureReason" : ""
                        } 
                    ],
                    "Householdsize" : "",
                    "HHFPLLimit" : ""
                },


                // docId : initializeData.generateManualDetail.docId,
                docId : "MA-APPDENY-001",
                //coReqSeq : "94252814",
                indivId : initializeData.generateManualDetail.generateManual.clientId,
                AppealId : initializeData.AppealId,
                pageId : initializeData.pageId,
                response : initializeData.response,
                preview : "false",
                parentPageId : initializeData.parentPageId,
                caseAppNumber : initializeData.generateManualDetail.caseNumber,
                coManualFieldsMap : initializeData.coManualFieldsMap,
                fwDataElementListMap : initializeData.fwDataElementListMap,
                request : initializeData.additionalInfoData,
                mailingAdd:{
                    street1:initializeData.additionalInfoData.street1,
                    street2:initializeData.additionalInfoData.street2,
                    city:initializeData.additionalInfoData.city,
                    state:initializeData.additionalInfoData.state,
                    zip5:initializeData.additionalInfoData.zip5,
                    zip4:initializeData.additionalInfoData.zip4
                } 
            }
        }
        else{
             // View Pending call
             localPrintURL = AppContext.config.localPrintViewPending;
             const generateManualDTO = {
                docId : initializeData.ViewPendingDetail.docId,
                coReqSeq : initializeData.ViewPendingDetail.coReqSeq,
                AppealId : initializeData.AppealId,
                pageId : initializeData.pageId,
                response : initializeData.response,
                preview : "false",
                parentPageId : initializeData.parentPageId,
                caseAppNumber : initializeData.ViewPendingDetail.caseAppNumber,
                coManualFieldsMap : initializeData.coManualFieldsMap,
                fwDataElementListMap : initializeData.fwDataElementListMap,
                request : initializeData.additionalInfoData,
            };
            inputJSON = {
                action : "viewLocalPrint",
                fullName: initializeData.ViewPendingDetail.fullName,
                watermark  : "Y",
                securityFlag : "N",
                formVersion : "1",
                formTitle: initializeData.ViewPendingDetail.docName,
                preferredLanguage : "English",
                goGreen : "N",
                agencyCode:"MASSHEALTH",
                agencyName: "MASS HEALTH AGENCY",
                SSN : "XXXX-XX-5455",
                clientDOB : "1960-05-18",
                systemDate : "2021-02-28",
                MASSHealthMedicaid : {
                    "Individual" : [
                         {
                        "Name" : "John Lewin",
                        "MemberId" : "123",
                        "IndividualDOB" : "03-12-1980",
                        "Program" : "MSPA",
                        "EffectiveDate" : "01-01-2020",
                        "ClosureDate" : "06-30-2020",
                        "ProgramClosureReason" : "Age"
                        },
                        {
                            "Name" : "John Lewin",
                            "MemberId" : "123",
                            "IndividualDOB" : "03-12-1980",
                            "Program" : "MFPA",
                            "EffectiveDate" : "07-01-2020",
                            "ClosureDate" : "",
                            "ProgramClosureReason" : ""
                        } 
                    ],
                    "Householdsize" : "",
                    "HHFPLLimit" : ""
                },
                previewVal : "true",
                opentextInd:  initializeData.ViewPendingDetail.opentextInd,
                searchCriteria  : initializeData.ViewPendingDetail.searchCriteria,
                caseNum : initializeData.ViewPendingDetail.caseAppNumber,
                appNum : initializeData.ViewPendingDetail.caseAppNumber,
                clientId : initializeData.ViewPendingDetail.clientId,
                workerName : initializeData.ViewPendingDetail.workerName,
                workerId : initializeData.ViewPendingDetail.workerId,
                ccCertStartDt : initializeData.ViewPendingDetail.ccCertStartDt,
                ccCertEndDt : initializeData.ViewPendingDetail.ccCertEndDt,
                coReqSeq : initializeData.ViewPendingDetail.coReqSeq,
                docId : initializeData.ViewPendingDetail.docId,
                generateManualDTO : generateManualDTO
            }  
        };
        try {
            let response = await Util.HTTP.post(localPrintURL, inputJSON, headers);
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
                localPrintResponse = {
                    success: true,
                };
            } else {
                localPrintResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            localPrintResponse.success = false;
        }
        console.log('localPrint Service Called');
        return localPrintResponse;
    };
}

const additionalInfoService = new AdditionalInfoService();
export default additionalInfoService;