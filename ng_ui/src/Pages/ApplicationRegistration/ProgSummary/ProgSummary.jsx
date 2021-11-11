import Template from './ProgSummary.rt';
import { PageConfig, UXPage, AppContext } from '@d-lift/core';
import { UX } from '@d-lift/uxcomponents';
import React from 'react';
import _ from 'lodash';
import navigationUtills from '@/ScreenFlowUtils/NavigationUtills';

@PageConfig({
    ContextRoot: 'ApplicationRegistration',
    PageName: 'ProgSummary',
    Path: ['/progsummary', "/ARRPI"],
    Template: Template,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Register Program - Summary',
})
class ProgSummary extends UXPage {
    initializeModel() {

        return {
            programCollection: [
                {
                    programId: '0',
                    program: 'Food Stamps',
                    expedited: 'No',
                    persons: 'Will John M 21',
                    coveReqDate: '06/24/2020',
                },
                {
                    programId: '1',
                    program: 'TANF',
                    expedited: 'No',
                    persons: 'Eric Bischoff M 44',
                    coveReqDate: '05/23/2020',
                },
            ]

        }

    }

    deleteCreation = (columnData, rowData) => {
        return columnData ? null : (
            <UX
                type="icon"
                symbol="fa fa-trash"
                click={event => this.deleteRow(event, rowData)}></UX>
        );
    };

    deleteRow = (event, rowData) => {
        let programCollection = [...AppContext.model.getValue('programCollection')];
        programCollection = _.remove(programCollection, data => {
            return data.programId !== rowData.programId;
        });
        AppContext.model.setValue('programCollection', programCollection);
    };

    editCreation = (columnData, rowData) => {
        return (
            <UX type="icon" symbol="fa fa-pen" click={event => this.editRow(event, rowData)}></UX>
        );
    };

    editRow = (event, rowData) => {
        navigationUtills.toNextPage({ ...rowData });
    };

    goToNextPage = event => {
        navigationUtills.toGivenPage('progsummary');
    };

    goBack = event => {
        navigationUtills.toPreviousPage({ ...this.state.recieved });
    };

    goToHomePage = event => {
        navigationUtills.goToHomePage();
    };
}

export default ProgSummary;
