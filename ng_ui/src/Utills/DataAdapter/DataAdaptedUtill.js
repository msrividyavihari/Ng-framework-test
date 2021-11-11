import { AppContext, Lift } from '@d-lift/core';
import _ from 'lodash';
import workflowUtills from '@/ScreenFlowUtils/WorkFlowUtills';

const handleMessages = messages => {
    if (!messages) return;

    messages.info
        ? AppContext.notification.info(messages.info)
        : AppContext.notification.clearInfo();
    messages.error
        ? AppContext.notification.error(messages.error)
        : AppContext.notification.clearError();
    messages.warning
        ? AppContext.notification.warning(messages.warning)
        : AppContext.notification.clearWarning();
    messages.success
        ? AppContext.notification.success(messages.success)
        : AppContext.notification.clearSuccess();
    window.scrollTo(0, 0);
};
const handleWorkflow = workflowList => {
    if (!workflowList) return;
    _.forEach(workflowList, workflow => {
        let workflowName = workflow.workFlowName
            ? workflow.workFlowName
            : Lift.Workflow.getCurrentWorkflowName();

        workflow.mode
            ? addPage(workflow, workflowName)
            : workflowUtills.removePage(workflow.pageId, workflowName);
    });
};

const handleExceptions = exceptionObject => {
    AppContext.notification.error(
        exceptionObject
            ? exceptionObject
            : 'Your session is timed out. You will be redirected to home page in 3 sec.',
    );
    setTimeout(() => {
        AppContext.security.logout();
    }, 3000);
    window.scrollTo(0, 0);
};

const handleResponse = response => {
    //console.log(response);
    handleMessages(response.messages);
    if (response.error) return;
    handleWorkflow(response.workflow);
    return response.data;
};

const addPage = (workflow, workflowName) => {
    workflow.refrencePageId
        ? workflow.position
            ? workflowUtills.addScreenAfterScreen(
                  workflow.refrencePageId,
                  workflow.pageId,
                  workflowName,
              )
            : workflowUtills.addScreenBeforeScreen(
                  workflow.refrencePageId,
                  workflow.pageId,
                  workflowName,
              )
        : workflow.position
        ? workflowUtills.addSubScreenAfterCurrentScreen(workflow.pageId, workflowName)
        : workflowUtills.addSubScreenBeforeCurrentScreen(workflow.pageId, workflowName);
};

export const ResponseHandlers = {
    handleResponse: handleResponse,
    handleExceptions: handleExceptions,
};
