import { Lift, Util } from '@d-lift/core';
import _ from 'lodash';
import * as navConstants from './ScreenflowConstants/NavigationUtillsConstants';
import pageUtills from './PageUtills';
import context from './ScreenflowConstants/NavigationContext.json';

/**
 * @author spreddy
 * @class WorkFlowUtills
 * @description - It as Utility class for workflow creation and modifications and Navigations
 */
class WorkFlowUtills {
    /**
     * @description Update screenflow by inserting new screen after the screen with `id = screenId`
     * @param {string} workflowName - Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
     * @param {string} pageId - Unique screen id.to be added at next screen
     */
    addNextScreenTop = (pageId, workflowName = Lift.Workflow.getCurrentWorkflowName()) => {
        if (pageUtills.checkIfPageExists(pageId)) {
            return;
        }
        let subScreenId = Lift.Workflow.getCurrentScreen(workflowName)?.id;
        let screenId = pageUtills.getParentScreenId(subScreenId);
        let nextScreen = this.createScreenObject(pageId, workflowName);
        pageUtills.updatePageMap(workflowName, pageId, pageId);
        Lift.Workflow.addNewScreenAfter(workflowName, screenId, nextScreen);
    };
    /**
     * @description Update screenflow by inserting new screen before the screen with `id = screenId`
     * @param {string} workflowName - Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
     * @param {string} pageId - Unique screen id.to be added at previous screen
     */
    addScreenBeforeTop = (pageId, workflowName = Lift.Workflow.getCurrentWorkflowName()) => {
        if (pageUtills.checkIfPageExists(pageId)) {
            return;
        }
        let subScreenId = Lift.Workflow.getCurrentScreen(workflowName)?.id;
        let screenId = pageUtills.getParentScreenId(subScreenId);
        let previousScreen = this.createScreenObject(pageId, workflowName);
        pageUtills.updatePageMap(workflowName, pageId, pageId);
        Lift.Workflow.addNewScreenBefore(workflowName, screenId, previousScreen);
    };

    removeFromPageMap = (pageId, workflowName) => {
        if (!Util.getSessionData(`${workflowName}_map`)) {
            return;
        }
        const screenMap = {
            ...JSON.parse(Util.getSessionData(`${workflowName}_map`)),
        };
        if (pageUtills.getParentScreenId(pageId, workflowName) === pageId) {
            Object.keys(screenMap).forEach(key => {
                if (screenMap[key] === pageId) {
                    delete screenMap[key];
                }
            });
        } else {
            delete screenMap[pageId];
        }

        Util.setSessionData(`${workflowName}_map`, JSON.stringify(screenMap));
    };

    /**
     * @description Adds sub screen at the end
     * @param {*} pageId page that needs to be added at the end
     * @param {string} [workflowName] - Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
     */
    addSubScreensEnd = (pageId, workflowName = Lift.Workflow.getCurrentWorkflowName()) => {
        if (pageUtills.checkIfPageExists(pageId)) {
            return;
        }
        const subScreen = this.createScreenObject(pageId, workflowName);
        const subScreenId = Lift.Workflow.getCurrentScreen(workflowName)?.id;
        const parentId = pageUtills.getParentScreenId(subScreenId);
        console.log('Parent id', parentId);
        pageUtills.updatePageMap(workflowName, pageId, parentId);
        Lift.Workflow.addNewSubNavAtEnd(workflowName, parentId, subScreen);
    };

    /**
     * @description Adds sub screen at the end
     * @param {*} pageId page that needs to be added at the end
     * @param {string} [workflowName] - Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
     */
    addSubScreensBeg = (pageId, workflowName = Lift.Workflow.getCurrentWorkflowName()) => {
        if (pageUtills.checkIfPageExists(pageId)) {
            return;
        }
        const subScreen = this.createScreenObject(pageId, workflowName);
        const subScreenId = Lift.Workflow.getCurrentScreen(workflowName)?.id;
        const parentId = pageUtills.getParentScreenId(subScreenId);
        pageUtills.updatePageMap(workflowName, pageId, parentId);
        Lift.Workflow.addNewSubNavAtStart(workflowName, parentId, subScreen);
    };
    /**
     * @description page Id which will be added at after the page
     * @param {*} pageId page that needs to be added before current page
     * @param {string} [workflowName] - Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
     */
    addSubScreenAfterCurrentScreen = (
        pageId,
        workflowName = Lift.Workflow.getCurrentWorkflowName(),
    ) => {
        if (pageUtills.checkIfPageExists(pageId)) {
            return;
        }
        const subScreen = this.createScreenObject(pageId, workflowName);
        const workFlow = Lift.Workflow.getWorkflow(workflowName);
        const updatedWorkFlow = [];
        let parentPageId;
        _.forEach(workFlow, screen => {
            let subFlow = [];
            _.forEach(screen.subnav, screenSub => {
                subFlow.push(screenSub);
                if (screenSub.path === window.location.pathname) {
                    subFlow.push(subScreen);
                    parentPageId = screen.id;
                }
            });
            updatedWorkFlow.push({
                ...screen,
                subnav: subFlow,
            });
        });
        Lift.Workflow.saveWorkflow(workflowName, updatedWorkFlow);
        pageUtills.updatePageMap(workflowName, pageId, parentPageId);
    };
    /**
     * @description page Id which will be added at before the page
     * @param {*} pageId page that needs to be added before current page
     * @param {string} [workflowName] - Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
     */
    addSubScreenBeforeCurrentScreen = (
        pageId,
        workflowName = Lift.Workflow.getCurrentWorkflowName(),
    ) => {
        if (pageUtills.checkIfPageExists(pageId)) {
            return;
        }
        const subScreen = this.createScreenObject(pageId, workflowName);
        const workFlow = Lift.Workflow.getWorkflow(workflowName);
        const updatedWorkFlow = [];
        let parentPageId;
        _.forEach(workFlow, screen => {
            let subFlow = [];
            _.forEach(screen.subnav, screenSub => {
                if (screenSub.path === window.location.pathname) {
                    subFlow.push(subScreen);
                    parentPageId = screen.id;
                }
                subFlow.push(screenSub);
            });
            updatedWorkFlow.push({
                ...screen,
                subnav: subFlow,
            });
        });
        Lift.Workflow.saveWorkflow(workflowName, updatedWorkFlow);
        pageUtills.updatePageMap(workflowName, pageId, parentPageId);
    };

    /**
     * @description - returns created screen object based on the pageId provided
     * @param {String} pageId screen object created of pageid
     * @returns {screenObject} created screen object based on the pageId
     */
    createScreenObject = (pageId, workflowName) => {
        let pagesDetails = JSON.parse(Util.getSessionData(workflowName + navConstants.METADATA));
        console.log(pagesDetails);
        return {
            ...pagesDetails[pageId],
            id: pageId,
            context: context[workflowName],
            path: `/${context[workflowName]}/${pageId}`,
        };
    };
    /**
     * @description removed the top pages by page id
     * @param {*} pageId page Id which needs to be removed
     * @returns updated workflow object
     */
    removePage = (pageId, workflow = Lift.Workflow.getCurrentWorkflow()) => {
        const updatedFLow = [];
        _.forEach(workflow, mainScreen => {
            if (mainScreen.id !== pageId) {
                let subNav = [];
                _.forEach(mainScreen.subnav, subScreen => {
                    if (subScreen.id !== pageId) {
                        subNav.push(subScreen);
                    }
                });

                updatedFLow.push({
                    ...mainScreen,
                    subnav: subNav,
                });
            }
        });
        Lift.Workflow.saveWorkflow(Lift.Workflow.getCurrentWorkflowName(), updatedFLow);
        this.removeFromPageMap(pageId, Lift.Workflow.getCurrentWorkflowName());
    };
    /**
     * @description create a sub screen(addPageId) after the given pageId(pageId)
     * @param {String} pageId screen to be created after pageId mandaroty field
     * @param {String} addPageId the which needs to be added checks if the page exists then then no page is added
     * @param {String} workflowName  Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
     */

    addScreenAfterScreen = (
        pageId,
        addPageId,
        workflowName = Lift.Workflow.getCurrentWorkflowName(),
    ) => {
        if (pageUtills.checkIfPageExists(addPageId) || !pageId) {
            return;
        }
        const subScreen = this.createScreenObject(addPageId, workflowName);
        const pageScreen = this.createScreenObject(pageId, workflowName);
        const workFlow = Lift.Workflow.getWorkflow(workflowName);
        const updatedWorkFlow = [];
        let parentPageId;
        _.forEach(workFlow, screen => {
            let subFlow = [];
            _.forEach(screen.subnav, screenSub => {
                subFlow.push(screenSub);
                if (screenSub.path === pageScreen.path) {
                    subFlow.push(subScreen);
                    parentPageId = screen.id;
                }
            });
            updatedWorkFlow.push({
                ...screen,
                subnav: subFlow,
            });
        });
        Lift.Workflow.saveWorkflow(workflowName, updatedWorkFlow);
        pageUtills.updatePageMap(workflowName, addPageId, parentPageId);
    };
    /**
     * @description create a sub screen(addPageId) before the given pageId(pageId)
     * @param {String} pageId screen to be created after pageId mandaroty field
     * @param {String} addPageId id pd page which needs to be added checks if the page exists then then no page is added
     * @param {String} workflowName  Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
     */

    addScreenBeforeScreen = (
        pageId,
        addPageId,
        workflowName = Lift.Workflow.getCurrentWorkflowName(),
    ) => {
        if (pageUtills.checkIfPageExists(addPageId) || !pageId) {
            return;
        }
        const pageScreen = this.createScreenObject(pageId, workflowName);
        const subScreen = this.createScreenObject(addPageId, workflowName);
        const workFlow = Lift.Workflow.getWorkflow(workflowName);
        const updatedWorkFlow = [];
        let parentPageId;
        _.forEach(workFlow, screen => {
            let subFlow = [];
            _.forEach(screen.subnav, screenSub => {
                if (screenSub.path === pageScreen.path) {
                    subFlow.push(subScreen);
                    parentPageId = screen.id;
                }
                subFlow.push(screenSub);
            });
            updatedWorkFlow.push({
                ...screen,
                subnav: subFlow,
            });
        });
        Lift.Workflow.saveWorkflow(workflowName, updatedWorkFlow);
        pageUtills.updatePageMap(workflowName, addPageId, parentPageId);
    };
}

const workFlowUtills = new WorkFlowUtills();
export default workFlowUtills;
