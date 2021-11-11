import Template from './AppGrpIndiv.rt';
import { PageConfig, UXPage, AppContext } from '@d-lift/core';
import React from 'react';
import { UX } from '@d-lift/uxcomponents';
import _ from 'lodash';
import navigationUtills from '@/ScreenFlowUtils/NavigationUtills';

@PageConfig({
    ContextRoot: 'ApplicationRegistration',
    PageName: 'AppGrpIndiv',
    Path: ['/appgrpindiv', "/ARRIH"],
    Template: Template,
    WorkFlowNavigation: true,
    PageType: 'workflow',
    Description: 'Register Individual - Summary',
})
class AppGrpIndiv extends UXPage {
    initializeModel() {
        return {

            personCollection: [
                {
                    personId: '1',
                    name: 'Tim John',
                    aliasName: ['JO', 'Tee'],
                    dateOfBirth: '2019-08-01',
                    clinetId: '67255332',
                    SSN: '678-321-1221',
                    Gender: 'F',
                    isPI: true,
                },
                {
                    personId: '2',
                    name: 'John wick',
                    aliasName: ['Johny', 'Jeff', 'Weeck'],
                    dateOfBirth: '2019-08-01',
                    clinetId: '67255332',
                    SSN: '678-321-1221',
                    Gender: 'M',
                    isPI: false,
                },
                {
                    personId: '3',
                    name: 'John doe',
                    aliasName: ['UJohn doe', 'Do', 'Mic'],
                    dateOfBirth: '2019-08-01',
                    clinetId: '67255332',
                    SSN: '678-321-1221',
                    Gender: 'M',
                    isPI: false,
                },
                {
                    personId: '4',
                    name: 'Ram Singh',
                    aliasName: ['Singh Ram'],
                    dateOfBirth: '2019-08-01',
                    clinetId: '67255332',
                    SSN: '678-321-1221',
                    Gender: 'M',
                    isPI: false,
                },
                {
                    personId: '5',
                    name: 'Kachara',
                    aliasName: 'The spinner',
                    dateOfBirth: '2019-08-01',
                    clinetId: '67255332',
                    SSN: '678-321-1221',
                    Gender: 'F',
                    isPI: false,
                },
                {
                    personId: '6',
                    name: 'Kim John',
                    aliasName: ['Kim'],
                    dateOfBirth: '1996-08-01',
                    clinetId: '9328393',
                    SSN: '678-673-1221',
                    Gender: 'F',
                    isPI: false,
                },
                {
                    personId: '7',
                    name: 'Srinivas',
                    aliasName: ' Srinu',
                    dateOfBirth: '2019-08-01',
                    clinetId: '7832383',
                    SSN: '893-321-1221',
                    Gender: 'F',
                    isPI: false,
                },
            ]

        };



    }

    formetPeronHeader = () => <UX type="icon" symbol="fa fa-users"></UX>;

    updatePI = (event, rowData) => {
        let personCollection = [...AppContext.model.getValue('personCollection')];
        personCollection = _.flatMap(personCollection, data => {
            return { ...data, isPI: data.personId === rowData.personId };
        });
        AppContext.model.setValue('personCollection', personCollection);
    };

    piCustomeColumn = (columnData, rowData) => {
        return columnData ? (
            <UX type="icon" symbol="fa fa-user-check"></UX>
        ) : (
                <UX
                    type="icon"
                    click={event => this.updatePI(event, rowData)}
                    symbol="far fa-user"></UX>
            );
    };

    deleteCreation = (columnData, rowData) => {
        return columnData ? null : (
            <UX
                type="icon"
                symbol="fa fa-trash"
                click={event => this.deleteRow(event, rowData)}></UX>
        );
    };

    deleteRow = (event, rowData) => {
        let personCollection = [...AppContext.model.getValue('personCollection')];
        personCollection = _.remove(personCollection, data => {
            return data.personId !== rowData.personId;
        });
        AppContext.model.setValue('personCollection', personCollection);
    };

    editCreation = (columnData, rowData) => {
        return (
            <UX type="icon" symbol="fa fa-pen" click={event => this.editRow(event, rowData)}></UX>
        );
    };

    addIndividual = event => {
        navigationUtills.toNextPage();
    };
    editRow = (event, rowData) => {
        navigationUtills.toNextPage({ ...rowData });
    };

    goToProgramSummary = event => {
        navigationUtills.toGivenPage('progsummary');
    };

    goBack = event => {
        navigationUtills.toPreviousPage({ ...this.state.recieved });
    };

    goToHomePage = event => {
        navigationUtills.goToHomePage();
    };

    splitAliasNames = columnData => {
        console.log(columnData);

        return Array.isArray(columnData) ? _.join(columnData, ' , ') : columnData;
    };

    getGenderCustome = columnData => {
        return columnData === 'M' ? 'Male' : 'Female';
    };
}

export default AppGrpIndiv;
