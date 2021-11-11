import { Lift, Util, AppContext } from '@d-lift/core';
import basicAxiosInterceptor from '@/HttpInterceptors/BasicAxiosInterCeptor';
import _ from 'lodash';
import { Date } from 'sugar';

class PageUtills {
    associateCase = async (caseNum) => {
        try {
            let appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
            await basicAxiosInterceptor({
                method: 'POST',
                url: AppContext.config.associateCase,
                data: {
                    appNum: appNum,
                    caseNum: caseNum,
                },
            });
            return true;
        } catch (ex) {
            console.log(ex);
        }
    };

    setContactModelDetails = async (responseData, appNum, caseNum) => {
        let contactAR = {
            preferredContactMethod: responseData.arApplicationForAid.weekdayContMethSw
                ? responseData.arApplicationForAid.weekdayContMethSw
                : '',
            preferredContactTime: responseData.arApplicationForAid.weekdayContTime
                ? responseData.arApplicationForAid.weekdayContTime
                : '',
        };

        AppContext.model.setValue('contactInfo.arApplicationForAid', contactAR);

        //Populate Phone details
        let contactPhnDetails = AppContext.model.getValue('contactInfo.phoneDetails')
            ? AppContext.model.getValue('contactInfo.phoneDetails')
            : {};
        if (!_.isEmpty(responseData.arPhoneList)) {
            responseData.arPhoneList.map((phn, i) => {
                phn.appNum = AppContext.model.getValue('appRegNew.ArApplicationForAid.appNum');
                if (phn.phnTypeCd === 'PRP') {
                    contactPhnDetails['PRP'] = phn;
                } else if (phn.phnTypeCd === 'WOP') {
                    contactPhnDetails['WOP'] = phn;
                } else if (phn.phnTypeCd === 'ALP') {
                    contactPhnDetails['ALP'] = phn;
                }
            });
            AppContext.model.setValue('contactInfo.phoneDetails', contactPhnDetails);
        }

        //Populate Email details Array
        if (!_.isEmpty(responseData.arEmailList)) {
            AppContext.model.setValue('contactInfo.emailDetails', responseData.arEmailList);
        }

        let addressDetails = AppContext.model.getValue('contactInfo.addressDetails')
            ? AppContext.model.getValue('contactInfo.addressDetails')
            : { addressInfo: {} };
        //addressDetails['addressInfo'] = {};
        //Populate Residence Address details
        if (!_.isEmpty(responseData.arAppAddrPA)) {
            addressDetails['resAddrSw'] = responseData.arAppAddrPA.resAddrSw;
            if (!responseData.arAppAddrPA.addrFormatCd) {
                responseData.arAppAddrPA.addrFormatCd = 'US';
            }
            addressDetails['addressInfo']['RE'] = responseData.arAppAddrPA;
            addressDetails['addressInfo']['RE']['addrCountyCd'] =
                responseData.arAppAddrPA.addrStateCd === 'GA'
                    ? responseData.arAppAddrPA.addrCountyCd
                    : '999';
        }

        //Is Homeless switch
        if (!_.isEmpty(responseData.arAppAddrPA)) {
            addressDetails['homelessSw'] = responseData.arAppAddrPA.resAddrSw;
        }

        //Is Mailing different switch - derive by comparing PA and MA address
        if (!_.isEmpty(responseData.arAppAddrPA) && !_.isEmpty(responseData.arAppAddrMA)) {
            //Compare US Residence and Mailing address
            if (
                responseData.arAppAddrPA.addrFormatCd === responseData.arAppAddrMA.addrFormatCd &&
                responseData.arAppAddrPA.addrFormatCd === 'US'
            ) {
                if (
                    responseData.arAppAddrPA.addrLine === responseData.arAppAddrMA.addrLine &&
                    responseData.arAppAddrPA.addrLine1 === responseData.arAppAddrMA.addrLine1 &&
                    responseData.arAppAddrPA.addrCity === responseData.arAppAddrMA.addrCity &&
                    responseData.arAppAddrPA.addrStateCd === responseData.arAppAddrMA.addrStateCd &&
                    responseData.arAppAddrPA.addrZip5 === responseData.arAppAddrMA.addrZip5
                ) {
                    // AppContext.model.setValue('contactInfo.addressDetails.livingResSw', 'N');
                    addressDetails['livingResSw'] = 'N';
                } else {
                    // AppContext.model.setValue('contactInfo.addressDetails.livingResSw', 'Y');
                    addressDetails['livingResSw'] = 'Y';
                }
            }

            //Compare Military Residence and Mailing address
            if (
                responseData.arAppAddrPA.addrFormatCd === responseData.arAppAddrMA.addrFormatCd &&
                responseData.arAppAddrPA.addrFormatCd === 'MI'
            ) {
                if (
                    responseData.arAppAddrPA.addrLine === responseData.arAppAddrMA.addrLine &&
                    responseData.arAppAddrPA.addrLine1 === responseData.arAppAddrMA.addrLine1 &&
                    responseData.arAppAddrPA.addrLine3 === responseData.arAppAddrMA.addrLine3 &&
                    responseData.arAppAddrPA.apoFpoAddr === responseData.arAppAddrMA.apoFpoAddr &&
                    responseData.arAppAddrPA.addrMilitary ===
                        responseData.arAppAddrMA.addrMilitary &&
                    responseData.arAppAddrPA.addrZip5 === responseData.arAppAddrMA.addrZip5
                ) {
                    addressDetails['livingResSw'] = 'N';
                } else {
                    addressDetails['livingResSw'] = 'Y';
                }
            }

            //If one is PA and other address is MA, default switch to Y
            if (responseData.arAppAddrPA.addrFormatCd !== responseData.arAppAddrMA.addrFormatCd) {
                addressDetails['livingResSw'] = 'Y';
            }
        }
        //Populate Mailing Address details
        if (!_.isEmpty(responseData.arAppAddrMA)) {
            addressDetails['resAddrSw'] = responseData.arAppAddrMA.resAddrSw;
            addressDetails['addressInfo']['MA'] = responseData.arAppAddrMA;
            addressDetails['addressInfo']['MA']['addrCountyCd'] =
                responseData.arAppAddrPA.addrStateCd === 'GA'
                    ? responseData.arAppAddrPA.addrCountyCd
                    : '999';
        } else {
            responseData['arAppAddrMA'] = {};
            responseData['arAppAddrMA']['addrFormatCd'] = 'US';
            addressDetails['addressInfo']['MA'] = responseData.arAppAddrMA;
            addressDetails['livingResSw'] = 'N';
        }
        AppContext.model.setValue('contactInfo.addressDetails', addressDetails);
    };

    getContactDetails = async (appNum, caseNum) => {
        console.log('Case Association Start Time : ' + new Date().toLocaleString());
        try {
            let caseNumber = caseNum ? caseNum : '0';
            let response = await basicAxiosInterceptor({
                method: 'POST',
                url: AppContext.config.findContactDetailsForAppNum,
                data: {
                    appNum: appNum,
                    caseNum: caseNumber,
                },
            });
            AppContext.model.setValue('contactConflictsInput', response.data.data);
            await this.setContactModelDetails(response.data.data, appNum, caseNum);
            return true;
        } catch (ex) {
            console.log(ex);
        } finally {
            console.log('Case Association End Time : ' + new Date().toLocaleString());
        }
    };

    /**
     * @description updated workflowMap with the page _id given so it cannot be duplicated
     * @param {*} pageId gets Parent Screen ID based on sub screen ID
     * @param {*} workflowName Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
     */
    updatePageMap = (workflowName, pageId, parentId) => {
        if (!Util.getSessionData(`${workflowName}_map`)) {
            return;
        }
        const screenMap = {
            ...JSON.parse(Util.getSessionData(`${workflowName}_map`)),
            [pageId]: parentId,
        };
        Util.setSessionData(`${workflowName}_map`, JSON.stringify(screenMap));
    };

    /**
     * @description gives the basePage Uri of workflow provided
     * @param {String} workflowName Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
     * @returns
     */
    getBasePageUri = (workflowName) => {
        const leftNavigation = Lift.Workflow.getWorkflow(workflowName);
        return leftNavigation[0]?.path
            ? leftNavigation[0]?.path
            : leftNavigation[0]?.subnav[0]?.path;
    };

    /**
     * @description Get Next Screen path from screen flow with respect to current location
     * @param {string} workflowName - Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
     *  * @returns {string}
     */
    getNextPageUri = (workFlowName = Lift.Workflow.getCurrentWorkflowName()) => {
        return Lift.Workflow.getNextScreenPath(workFlowName);
    };

    /**
     * @description Get Previous Screen path from screen flow with respect to current location
     * @param {string} workflowName - Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
     *  * @returns {string}
     */
    getPreviousPageUri = (workFlowName = Lift.Workflow.getCurrentWorkflowName()) => {
        return Lift.Workflow.getPreviousScreenPath(workFlowName);
    };

    /**
     * @description retruns parent to page of given sub page
     * @param {*} subScreenId gets Parent Screen ID based on sub screen ID
     * @param {*} workflowName Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
     */
    getParentScreenId = (subPageId, workflowName = Lift.Workflow.getCurrentWorkflowName()) => {
        return JSON.parse(Util.getSessionData(`${workflowName}_map`))
            ? JSON.parse(Util.getSessionData(`${workflowName}_map`))[subPageId]
            : this.getParentIdFromFlow(workflowName, subPageId);
    };

    /**
     * @description when normal flow object is created with help of .json file then thsi method is used to fetch parent ID
     * @param {String} subScreenId gets Parent Screen ID based on sub screen ID
     * @param {String} workflowName Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
     *
     * @returns {String} returns parent page id
     */
    getParentIdFromFlow = (workflowName, subPageId) => {
        const workFlow = { ...Lift.Workflow.getWorkflow(workflowName) };
        let parentId;
        if (workFlow) {
            _.forEach(workFlow, (topSceeen) => {
                if (topSceeen.id === subPageId) {
                    return subPageId;
                }
                _.forEach(topSceeen.subnav, (subScr) => {
                    if (subScr.id === subPageId) {
                        parentId = topSceeen.id;
                    }
                });
            });
        }
        return parentId;
    };

    /**
     * @description Get  Screen data from screen flow
     * @param {string} workflowName - Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
     * @param {String} screenId - Screen Id for which data is fetched
     *  * @returns {string}
     */
    getPageDetails = (pageId, workFlowName = Lift.Workflow.getCurrentWorkflowName()) => {
        let parentPageId = this.getParentScreenId(pageId);
        return parentPageId === pageId
            ? Lift.Workflow.getScreen(workFlowName, parentPageId)
            : Lift.Workflow.getScreen(workFlowName, parentPageId, pageId);
    };

    /**
     * @description Get  Screen path from screen flow based on ID
     * @param {string} workflowName - Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
     * @param {String} screenId - Screen Id for which data is fetched
     *  * @returns {string}
     */
    getPagePath = (pageId, workFlowName = Lift.Workflow.getCurrentWorkflowName()) => {
        return this.getPageDetails(pageId, workFlowName)?.path;
    };

    /**
     * @description Clears pages Map of taht workflow object
     * @param {string} [workflowName] - Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
     */
    clearPageMap = (workflowName = Lift.Workflow.getCurrentWorkflowName()) => {
        Util.clearSessionData(`${workflowName}_map`);
    };
    /**
     * @description checks if page is in the workflow
     * @param {*} subScreenId gets Parent Screen ID based on sub screen ID
     * @param {*} workflowName Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
     */
    checkIfPageExists = (subScreenId, workflowName = Lift.Workflow.getCurrentWorkflowName()) => {
        return JSON.parse(Util.getSessionData(`${workflowName}_map`))
            ? JSON.parse(Util.getSessionData(`${workflowName}_map`))[subScreenId]
            : this.getParentIdFromFlow(workflowName, subScreenId);
    };

    cachePageModel = (pageID) => {
        let data = {};
        data[pageID] = AppContext.model.getValue(pageID);
        let workflowName = Lift.Workflow.getCurrentWorkflowName();
        let screenData = JSON.parse(Util.getSessionData('screenData'));
        // this.upsert(screenData[workflowName], data);
        screenData[workflowName][pageID] = data[pageID];
        Util.setSessionData('screenData', JSON.stringify(screenData));
    };

    createScreenDataWfCache = (workflowName) => {
        let data = {};
        data[workflowName] = {};
        Util.setSessionData('screenData', JSON.stringify(data));
    };

    readModelfromCachebyPageID = (pageID) => {
        let screenData = JSON.parse(Util.getSessionData('screenData'));
        let workflowName = Lift.Workflow.getCurrentWorkflowName();
        return screenData[workflowName][pageID];
    };
    compareModelwithCache = (pageID) => {
        let clonemodel = this.readModelfromCachebyPageID(pageID);
        let pagemodel = AppContext.model.getValue(pageID);
        if (!_.isEqual(clonemodel, pagemodel)) {
            this.cachePageModel(pageID);
            return true;
        }
        return false;
    };

    closeSSPApplicants = () => {
        if (document.getElementById('mySidepanelApplicants')) {
            document.getElementById('mySidepanelApplicants').style.display = 'none';
        }
        if (document.getElementById('sspIndvs')) {
            document.getElementById('sspIndvs').style.display = 'none';
        }
        AppContext.model.setValue('fileClearStatus', 'Cleared');
        document.getElementById(`file_clearance_exclamation`).classList.add('success');
    };

    loadDataintoFileClearanceSSP = async (ssn) => {
        AppContext.model.setValue('conflictPanel.sspApplicant.open', true);
        try {
            let sspApplicantList = AppContext.model.getValue('sspApplicants');
            let sspPayLoad = {};
            sspApplicantList.map((item) => {
                let dobFormatted =
                    new Date(item.dob).getFullYear().raw +
                    '-' +
                    parseInt(new Date(item.dob).getMonth() + 1) +
                    '-' +
                    new Date(item.dob).getDate().raw;
                if (item.primaryApplicantSw === 'Yes' || item.ssn === ssn) {
                    sspPayLoad = {
                        indvid: '',
                        ssn: item.ssn,
                        dobDt: dobFormatted,
                        age: '',
                        lastName: item.lastName,
                        firstName: item.firstName,
                        sufxName: '',
                        midName: '',
                        raceCd: '',
                        ethnicityCd: '',
                        gender: item.gender,
                        alisName: [''],
                        relationShips: [{}],
                        score: '',
                    };
                }
            });
            let sspDCResponse = [];
            if (sspPayLoad.ssn) {
                AppContext.model.setValue('ar.pc.search.sspssn', sspPayLoad.ssn);
                AppContext.model.setValue('ar.pc.search.ssn', sspPayLoad.ssn);
                sspDCResponse = await basicAxiosInterceptor({
                    method: 'POST',
                    url: AppContext.config.fileClearBySSN,
                    data: {
                        ssn: sspPayLoad.ssn,
                    },
                });

                AppContext.model.setValue('FileClearMatchSize', [...sspDCResponse.data].length);

                AppContext.model.setValue('ar.pc.search.collection.above85', [
                    ...sspDCResponse.data,
                ]);
                if (AppContext.model.getValue('ar.pc.search.collection.above85').length === 0) {
                    AppContext.model.setValue('ARRAP.RN.rNavCond', 'fileClrNoMatch');
                    AppContext.model.setValue('sspDcMatch', false);
                    AppContext.model.setValue('fileClearStatus', 'No Match from Data Collection');
                } else {
                    this.FCPanelMatchFound(sspPayLoad.ssn);
                    AppContext.model.setValue('FCMatch', 'SSN');
                    AppContext.model.setValue('ARRAP.RN.rNavCond', 'fileClrOneMatch1');
                    AppContext.model.setValue('sspDcMatch', true);
                    AppContext.model.getValue('ar.pc.search.collection.above85').length === 1
                        ? AppContext.model.setValue(
                              'fileClearStatus',
                              '1 Match from Data Collection',
                          )
                        : AppContext.model.setValue(
                              'fileClearStatus',
                              AppContext.model.getValue('ar.pc.search.collection.above85').length +
                                  ' Matches from Data Collection',
                          );
                }
            }
            if (!sspPayLoad.ssn || sspDCResponse.data.length === 0) {
                let sspDCResponse = await basicAxiosInterceptor({
                    method: 'POST',
                    url: AppContext.config.fileClearByDetails,
                    data: sspPayLoad,
                });
                let resplength = [...sspDCResponse.data].length;
                AppContext.model.setValue('FileClearMatchSize', resplength);

                AppContext.model.setValue(
                    'ar.pc.search.collection.above85',
                    sspDCResponse.data.filter((data) => {
                        return data.score > 84;
                    }),
                );

                AppContext.model.setValue(
                    'ar.pc.search.collection.below85',
                    sspDCResponse.data.filter((data) => {
                        return data.score < 85;
                    }),
                );

                if (resplength === 0) {
                    this.FCPanelNoMatchFound(sspPayLoad.ssn);
                    AppContext.model.setValue('ARRAP.RN.rNavCond', 'fileClrNoMatch');
                    AppContext.model.setValue('sspDcMatch', false);
                    AppContext.model.setValue('FCMatch', 'NoMatch');
                } else {
                    this.FCPanelMatchFound(sspPayLoad.ssn);
                    AppContext.model.setValue('ARRAP.RN.rNavCond', 'fileClrOneMatch1');
                    AppContext.model.setValue('sspDcMatch', true);
                    AppContext.model.setValue('FCMatch', 'SSNAdd');
                }
            }
            AppContext.model.setValue('ar.pc.search.ssnln', true);
            AppContext.model.setValue('disableFrSSP', 'true');
            return true;
        } catch (ex) {
            console.log(ex);
            return false;
        }
    };

    FCPanelMatchFound = (ssn) => {
        if (
            document.getElementById('sspPanelBadge' + ssn) &&
            document.getElementById('sspPanelBadge' + ssn).children[0] &&
            document.getElementById('sspPanelBadge' + ssn).children[0].children[0]
        ) {
            document.getElementById('sspPanelBadge' + ssn).children[0].children[0].innerHTML =
                'Match found for this individual. Review the individuals on the left and add to the application if applicable.';
        }
    };

    FCPanelNoMatchFound = (ssn) => {
        if (
            document.getElementById('sspPanelBadge' + ssn) &&
            document.getElementById('sspPanelBadge' + ssn).children[0] &&
            document.getElementById('sspPanelBadge' + ssn).children[0].children[0]
        ) {
            document.getElementById('sspPanelBadge' + ssn).children[0].children[0].innerHTML =
                'Click on Establish New Person in File Clearance Panel to Proceed.';
        }
    };

    isSSPPanelGreenNotchResolved = () => {
        if (
            document.getElementById('mySidenavApplicants') &&
            document.getElementById('mySidenavApplicants').children[0] &&
            document.getElementById('mySidenavApplicants').children[0].textContent ===
                'SSP Applicants'
        ) {
            return false;
        }
        return true;
    };
    continueSession = async (props) => {
        // console.log('inside function cont');
        // let logedInUser = AppContext.security.getProfile()._id;
        // console.log(logedInUser);
        // let anonymousUser = false;
        // if (_.isUndefined(logedInUser) || logedInUser === null) {
        //     anonymousUser = true;
        // }

        // if (anonymousUser) {
        //     console.log('anonymous user');

        //     //let serviceResponse = await this.refreshAnonymousToken();
        //     // if (serviceResponse.nonAFB) {
        //     //     props.resetTimeout();
        //     // } else if (serviceResponse.status === 200) {
        //     //     AppContext.tokenA = serviceResponse.data.pageCollection.tokenA[0];
        //     //     props.resetTimeout();
        //     // }
        // } else {
        props.resetTimeout();
        // }
    };
}
const pageUtills = new PageUtills();
export default pageUtills;
