window.publicURL = document.getElementById('publicUrl').getAttribute('content');
// eslint-disable-next-line no-unused-vars
var appconfig = {
    host: '',
    contextroot: window.publicURL,
    environment: 'SYSTEMS v1.0',
    contentManagerURL: window.publicURL + 'WcmKeys/',
    referenceTableURL: window.publicURL + 'RefTables/',
    refrenceTableEndpoint: 'http://localhost:8080/rtservice',
    //loginservice: 'http://usmumloaner9.us.deloitte.com:8083/security/validateUser',
    loginservice: 'http://localhost:8083/security/validateUser',
    searchAppService: 'http://localhost:8082/ar/maintain-application/v1/applications',
    deleteAppService: 'http://localhost:8082/ar/maintain-application/v1/application',
    contentManagerEndpoint: window.publicURL + 'WcmKeys/',
    serviceEndpoint: window.publicURL,
    restServiceHost: window.publicURL,

    //Case Association Sceen End Points
    //fetchCasesForApp: 'http://localhost:8082/ar/application-registration/v1/search-cases/appnum',
    fetchAsscCasesForIndv:
        'http://localhost:8082/ar/application-registration/v1/associated-cases/indvid/appnum',
    associateCase: 'http://localhost:8082/ar/application-registration/v1/associate-case/caseNum', //Revisit
    //fileClear: 'http://localhost:8082/ar/application-registration/fc/ssn',
    fileClearBySSN: 'http://localhost:8082/ar/application-registration/v1/fc/ssn',
    fileClearByDetails: 'http://localhost:8082/ar/application-registration/v1/fc/details',
    createApp: 'http://localhost:8082/ar/application-registration/v1/ArApplicationForAid', //Revisit
    insertArAppIndv: 'http://localhost:8082/ar/application-registration/v1/application-individuals', //Revisit
    findByAppNum: 'http://localhost:8082/ar/application-registration/v1/application-details/appNum', //Revisit
    fetchApplicantsInfo: 'http://localhost:8082/ar/application-registration/v1/applicants-details',
    insertUpdateApplicants: 'http://localhost:8082/ar/application-registration/v1/applicants',
    fetchApplicantsInMaintMode:
        'http://localhost:8082/ar/application-registration/v1/applicants-details/appNum',

    //Contact details Screen End Points
    findContactDetailsForAppNum:
        'http://localhost:8082/ar/application-registration/v1/contact-details/appNum/caseNum',
    createContact: 'http://localhost:8082/ar/application-registration/v1/contact-details',
    validateContactAddress: 'http://localhost:8082/ar/application-registration/v1/validateAddress',
    deassociateCase: 'http://localhost:8082/ar/application-registration/v1/deassociate',

    //Program Selection End Points
    insertArAppProgram: 'http://localhost:8082/ar/application-registration/v1/ArAppProgram',
    insertPriorityAppStatus:
        'http://localhost:8082/ar/application-registration/v1/PriorityAppStatus',
    insertArExpResp: 'http://localhost:8082/ar/application-registration/v1/ArExpScreenResp',
    fetchPrgByAppNum: 'http://localhost:8082/ar/application-registration/v1/ArAppProgram/appNum',
    fetchExpInfo: 'http://localhost:8082/ar/application-registration/v1/ArExpScreenResp/appNum',
    //findPrgByAppNum: 'http://localhost:8082/ar/application-registration/fetchByAppNum/',

    insertArAppProgramIndv: 'http://localhost:8082/ar/application-registration/v1/ArAppProgramIndv',
    fetchArAppProgramIndv:
        'http://localhost:8082/ar/application-registration/v1/fetchArAppProgramIndv/appNum',
    //Authrep details Screen End Points
    findAuthRepDetailsForAppNum:
        'http://localhost:8082/ar/application-registration/v1/authrep-details/appNum/caseNum',
    createAuthRep: 'http://localhost:8082/ar/application-registration/v1/authRep-details/',
    deleteAuthRep: 'http://localhost:8082/ar/application-registration/v1/authRep-details/appNum',

    //Correspondence End Points
    caseSearch: 'http://localhost:8086/co/api/correspondence/generateManual/caseSearch',
    callCoMaster: 'http://localhost:8086/co/api/correspondence/generateManual/getCOMaster',
    initializeAdditionalInfo:
        'http://localhost:8086/co/api/correspondence/generateManual/initialize',
    saveDraftGenerateManual:
        'http://localhost:8086/co/api/correspondence/generateManual/saveAsDraft',
    viewPendingCO: 'http://localhost:8086/co/api/correspondence/viewPendingCorrespondence',
    viewPendingDetailCO:
        'http://localhost:8086/co/api/correspondence/viewPendingCorrespondence/detail',

    previewGenerateManual: 'http://localhost:8086/co/api/correspondence/generateManual/preview',
    previewViewPending:
        'http://localhost:8086/co/api/correspondence/viewPendingCorrespondence/preview',
    centralPrintGenerateManual:
        'http://localhost:8086/co/api/correspondence/generateManual/centralPrint',
    centralPrintViewPending:
        'http://localhost:8086/co/api/correspondence/viewPendingCorrespondence/centralPrint',
    localPrintGenerateManual:
        'http://localhost:8086/co/api/correspondence/generateManual/localPrint',
    localPrintViewPending:
        'http://localhost:8086/co/api/correspondence/viewPendingCorrespondence/localPrint',

    massMailingSummaryCO: 'http://localhost:8086/co/api/correspondence/massMailing/summary',
    createMassMailingCO:
        'http://localhost:8086/co/api/correspondence/massMailing/createOrUpdateRequest',
    deleteMassMailingCO: 'http://localhost:8086/co/api/correspondence/massMailing/delete',
    findProgramIndividuals:
        'http://localhost:8082/ar/application-registration/v1/AppIndividual/appNum',
    searchDocumentsCO: 'http://localhost:8086/co/api/documentManagement/search',
    viewHistoryCO: 'http://localhost:8086/co/api/correspondence/viewHistory/searchHistory',
    viewHistoryDetailCO: 'http://localhost:8086/co/api/correspondence/historyDetail/retrieve',
    centralReprintCO: 'http://localhost:8086/co/api/correspondence/historyDetail/central-reprint',
    localReprintCO: 'http://localhost:8086/co/api/correspondence/historyDetail/local-reprint',
    previewCO: 'http://localhost:8086/co/api/correspondence/historyDetail/preview',
    massPreviewCO: 'http://localhost:8086/co/api/correspondence/massMailing/preview',
    metaDataCO: 'http://localhost:8086/co/api/correspondence/documentDetails/metaDataChange',
    linkDelinkCO: 'http://localhost:8086/co/api/correspondence/documentDetails/DelinkAndLink',
    viewDocumentCO: 'http://localhost:8086/co/api/documentHandler/getDocumentStream',

    //ProgressBar
    getProgress: 'http://localhost:8082/ar/application-registration/v1/application-progress/appNum',

    //SubmitApp
    submitApp: 'http://localhost:8082/ar/application-registration/v1/submitApplication/appNum', //Revisit

    // SSP Staging Apis For Application Regstration
    getAppRqst: 'http://localhost:8085/ar-ssp-api/ssp-staging-application/v1/app-request/appNum',
    getOneIndv: 'http://localhost:8085/ar-ssp-api/ssp-staging-individual/v1/indviduals/appNum',
    //searchSSPApps: 'http://localhost:8085/ar-ssp-api/appsearch/findApps',
    fetchConflictApplicants:
        'http://localhost:8082/ar/application-registration/v1/conflict-applicants/appNum',
    fetchConflictContacts:
        'http://localhost:8082/ar/application-registration/v1/conflict-contacts/appNum',
    fetchConflictAuthRep:
        'http://localhost:8082/ar/application-registration/v1/conflict-authRep/appNum',
    // MA Demo - Notice Request Status
    noticeLatestStatus: 'http://localhost:8086/co/api/correspondence/noticeRequest/latest-status',
    updateStatus: 'http://localhost:8086/co/api/correspondence/noticeRequest/status-update',
    coDashboard: 'http://localhost:8086/co/api/correspondence/noticeRequest/fetch-status',
    dashboardReports: 'http://localhost:8086/co/api/correspondence/noticeRequest/fetch-tracking-report'
};
