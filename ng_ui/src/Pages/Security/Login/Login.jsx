import Template from './Login.rt';
import { PageConfig, UXPage, AppContext } from '@d-lift/core';
import navigationUtills from '@/ScreenFlowUtils/NavigationUtills';
import _ from 'lodash';

@PageConfig({
    ContextRoot: 'Security',
    PageName: 'Login',
    Path: '/',
    Template: Template,
})
class Login extends UXPage {
    loginAction = async event => {
        event.preventDefault();

        let username = AppContext.model.getValue('userID');
        let password = AppContext.model.getValue('password');
        let response = await AppContext.security.login(username, password);
        console.log(response);

        if (response.success) {
        AppContext.model.setValue('isValidUser', false);
        navigationUtills.goToHomePage();
        } else {
           AppContext.model.setValue('isValidUser', true);
        }
    };

    // updateScreenFlow({ contactLog }) {
    //     let totalIndvs = Number.parseInt(contactLog.noOfIndividual);
    //     if (!Number.isNaN(totalIndvs)) {
    //         let indvScreens = [];
    //         for (let i = 0; i < totalIndvs; i++) {
    //             let newIndvScreen = Lift.Workflow.createScreen(
    //                 `Person${i + 1}`,
    //                 {
    //                     en: `Person ${i + 1}`,
    //                     es: `Person ${i + 1}`,
    //                 },
    //                 'SampleFlow',
    //                 `/SampleFlow/PersonInfo/${i + 1}`,
    //             );
    //             indvScreens.push(newIndvScreen);
    //         }
    //         let indvParentNode = Lift.Workflow.getScreen(this.state.workflow, 'PersonInfo');
    //         indvParentNode.subnav = indvScreens;
    //         delete indvParentNode.path;
    //         Lift.Workflow.replaceScreen(this.state.workflow, 'PersonInfo', indvParentNode);
    //         Lift.Workflow.removeScreen(this.state.workflow, 'Pregnancy');
    //     }
    //     if (AppContext?.Workflow?.SampleFlow?.Person !== undefined) {
    //         for (let [personId, personObj] of Object.entries(
    //             AppContext.Workflow.SampleFlow.Person,
    //         )) {
    //             let id = Number.parseInt(personObj.id);
    //             if (!Number.isNaN(id) && id > totalIndvs) {
    //                 delete AppContext.Workflow.SampleFlow.Person[personId];
    //             }
    //         }
    //     }
    // }

    forgotAction(e) {
        e.preventDefault();
    }

    forgotUsername(e) {
        e.preventDefault();
    }

    isValidUser(e) {
        let inValidUser = false;
        if (_.has(this.state.recieved, 'invaliduser')) {
            inValidUser = this.state.recieved.invaliduser;
        }
        console.log('invalidUser' + inValidUser);
        return inValidUser;
    }
}

export default Login;
