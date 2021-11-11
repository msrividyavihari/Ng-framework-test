import { Util, Lift, Navigate, AppContext } from '@d-lift/core';
import _ from 'lodash';
import NavigationUtills from '@/ScreenFlowUtils/NavigationUtills';
import * as navConstants from './ScreenflowConstants/NavigationUtillsConstants';
import pageUtills from './PageUtills';
import screenflow from '@/Services/screenFlow';
import context from './ScreenflowConstants/NavigationContext.json';

/**
 *@description Save Workflow object [It overwrites existing workflow object] and navigates the user to the first screen
 * @param {String} screenFlowName  Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
 */
export const navigateToFirstScreen = workflowName => {
    saveWorkflowData(workflowName)
        .then(response => {
            NavigationUtills.navigateToBaseScreenWorkflow(workflowName);
        })
        .catch(error => {
            AppContext.notification.error(
                'Error While Fetching data Redirecting to Login Page in 10 Seconds',
            );
            setTimeout(() => {
                AppContext.security.logout();
            }, 10000);
        });
    //console.log(Lift.Workflow.getCurrentScreen(screenFlowName)?.path);
};

/**
 *@description If the workflow already exists it navigates to the first screen of the workflow
 * @param {String} workflowName  Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
 */
export const navigateToScreenFlow = workflowName => {
    if (!Util.containsSessionData(`${workflowName}_screenflow`)) {
        navigateToFirstScreen(workflowName);
    } else NavigationUtills.navigateToBaseScreenWorkflow(workflowName);
};

/**
 * @description creates and saves the workflow based on the workflow name to session
 * @param {String} workflow
 */
const saveWorkflowData = async workflowName => {
    await getWorkFlowData(workflowName);
    const dataSub = JSON.parse(Util.getSessionData(workflowName + navConstants.METADATA));
    const roleBaseJson = JSON.parse(Util.getSessionData(workflowName + navConstants.NAVIGATION));
    //const dataTop = WorkFlowUtills.getBootStrapData(navConstants.NAV_PAGE_DETAILS_JSON);
    // const roleBaseJson = WorkFlowUtills.getBootStrapData(navConstants.ROLE_BASE_PAGE_JSON)[
    //     workflowName
    // ];
    console.log(roleBaseJson);
    if (!roleBaseJson) {
        Navigate.to('/');
        throw Error(`${workflowName} with this name dosent exist`);
    }
    const leftNav = generateWorkFlow(dataSub, roleBaseJson[workflowName], workflowName);

    // console.log(leftNav);
    Lift.Workflow.saveWorkflow(workflowName, [...leftNav]);
    pageUtills.createScreenDataWfCache(workflowName)
};

/**
 * @description returns json data of the given pageJsonwhich is bootystraped
 * @param {String} pageJsonName
 * @returns Json data from the session
 */
const getWorkFlowData = async workFlowName => {
    // if (Util.getSessionData(pageJsonName + navConstants.NAVIGATION)) {
    //     return JSON.parse(Util.getSessionData(pageJsonName + navConstants.NAVIGATION));
    // }
    let response;
    if (workFlowName === 'AREGA')
        response = await screenflow.getScreenFlowAREGA();
    else if (workFlowName === 'ARSEA')
        response = await screenflow.getScreenFlowARSEA();
    else if(workFlowName === 'COVPC')
        response = await screenflow.getScreenFlowCOVPC();

    Util.setSessionData(
        workFlowName + navConstants.NAVIGATION,
        JSON.stringify(response.data.navigation),
    );
    Util.setSessionData(
        workFlowName + navConstants.METADATA,
        JSON.stringify(response.data.metadata),
    );
};

/**
 * @description create a workflow object which has the details of the pages left navigation
 * @param {Array} dataSub
 * @param {Array} dataTop
 * @param {Array} roleBaseJson
 * @returns {JSON} workflowObject
 */
const generateWorkFlow = (dataSub, roleBaseJson, workflowName) => {
    // console.log(dataSub);
    // console.log(dataTop);
    // console.log(roleBaseJson);
    const workflowObject = [];
    const workflowContext = context[workflowName];
    const screenMap = {};
    _.forEach(roleBaseJson, (subNavIds, jKey) => {
        console.log(subNavIds, jKey);
        let screenData = dataSub[jKey];
        let subnav = [];
        _.forEach(subNavIds, pageId => {
            let subScrData = dataSub[pageId];
            screenMap[pageId] = jKey;
            subnav.push(
                Lift.Workflow.createScreen(
                    pageId,
                    subScrData.title,
                    workflowContext,
                    `/${workflowContext}/${pageId}`,
                ),
            );
        });
        screenMap[jKey] = jKey;
        workflowObject.push(
            Lift.Workflow.createScreen(jKey, screenData.title, workflowContext, subnav),
        );
    });
    Util.setSessionData(`${workflowName}_map`, JSON.stringify(screenMap));
    return [...workflowObject];
};

/**
 * @description Clears workflow object
 * @param {string} [workflowName] - Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
 */
export const clearWorkFlow = workflowName => {
    Lift.Workflow.clearWorkFlow(workflowName);
    pageUtills.clearPageMap(workflowName);
};

/**
 * @description function to bootstrap the json files and fetched when needed
 */
// export const bootStrepThePageJsonFIles = () => {
//     screenflow.getScreenFlowSub().then(response => {
//         Util.setSessionData(navConstants.SUB_PAGE_DETAILS_JSON, JSON.stringify(response));
//     });
//     // screenflow.getScreenFlowTop().then(response => {
//     //     Util.setSessionData(navConstants.NAV_PAGE_DETAILS_JSON, JSON.stringify(response));
//     // });
//     screenflow.getTestScreen().then(response => {
//         Util.setSessionData(navConstants.ROLE_BASE_PAGE_JSON, JSON.stringify(response));
//     });
// };

/** @description Workflow Helper classes to dynamically update workflow object */
export const WorkFlow = {
    navigateToScreenFlow: navigateToScreenFlow,
    navigateToFirstScreen: navigateToFirstScreen,
    //loadJsonData: bootStrepThePageJsonFIles,
    clearWorkFlow: clearWorkFlow,
};
