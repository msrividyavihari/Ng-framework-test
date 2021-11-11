import { Navigate, Lift } from '@d-lift/core';
import pageUtills from './PageUtills';
/**
 * @author spreddy
 * @class NavigationUtills
 * @description - It as Utility class for workflow creation and modifications and Navigations
 */
class NavigationUtills {
    /**
     * @description Navigates to the first screen of the workflow
     * @param {String} workflowName Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
     */
    navigateToBaseScreenWorkflow = workflowName => {
        // let firstScreenPath = leftNav[0]?.path ? leftNav[0]?.path : leftNav[0]?.subnav[0]?.path;
        let firstScreenPath = pageUtills.getBasePageUri(workflowName);
        // console.log(firstScreenPath);
        Navigate.to(firstScreenPath, {
            workflow: workflowName,
        });
    };

    /**
     * @description Navigates to  Screen path from screen flow with respect to screenId porvided
     * @param {string} workflowName - Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
     * @param {String} screenId - Screen Id for which page is navigated to
     */
    toGivenPage = (screenId, workFlowName = Lift.Workflow.getCurrentWorkflowName()) => {
        Navigate.to(pageUtills.getPagePath(screenId, workFlowName));
    };

    /**
     * @description Navigates to Next Screen path from screen flow with respect to current location
     * @param {string} workflowName - Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
     */
    toNextPage = (data = {}, workFlowName = Lift.Workflow.getCurrentWorkflowName()) => {
        data = { ...data };
        Navigate.to(pageUtills.getNextPageUri(workFlowName), data);
    };
    /**
     * @description Navigates to Previous Screen path from screen flow with respect to current location
     * @param {string} workflowName - Workflow name. Inside workflow screens this can be accessed via `this.state.workflow`
     */
    toPreviousPage = (data = {}, workFlowName = Lift.Workflow.getCurrentWorkflowName()) => {
        Navigate.to(pageUtills.getPreviousPageUri(workFlowName), data);
    };

    /**
     * @description this is to refresh the same given page with given data
     */
    refreshCurrentScreen = (data = {}) => {
        Navigate.to(window.location.pathname, data);
    };

    /**
     * @description function used to navigate to home page of the application
     */
    goToHomePage = () => {
        Navigate.to('/Public/HomeNew');
    };
}
const navigationUtills = new NavigationUtills();
export default navigationUtills;
