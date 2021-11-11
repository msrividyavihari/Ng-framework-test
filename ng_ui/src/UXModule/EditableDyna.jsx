import React, { Component } from 'react';
import { UX } from '@d-lift/uxcomponents';
import { AppContext } from '@d-lift/core';
import _ from 'lodash';

export default class EditableDyna extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showEditForm: false,
            addToggle: true,
            dyna: { field: {} },
        };
    }

    cancelFunction = event => {
        var k = event.currentTarget.id;
        let obj = AppContext.model.getValue(k, {});
        obj.showEditForm = false;
        obj.addToggle = true;
        AppContext.model.setValue(k, obj);

        this.setState({ showEditForm: false });
        this.setState({ addToggle: true });
        AppContext.model.setValue('dyna.fields', {});
        document.getElementById(k + '_errorDiv').innerHTML = '';
    };

    addFunction = (columnData, rowData, a) => {
        var k = columnData.currentTarget.id;
        let obj = AppContext.model.getValue(k, {});
        obj.showEditForm = true;
        AppContext.model.setValue(k, obj);
        this.setState({ showEditForm: true });
    };

    editCreation = (columnData, rowData, a) => {
        let id = this.props.id;
        return (
            <UX
                type="icon"
                symbol="fa fa-pen"
                click={event => this.editRowContact(event, rowData, a, id)}></UX>
        );
    };

    editRowContact = (event, rowData, a, id) => {
        document.getElementById(id + '_errorDiv').innerHTML = '';
        let obj = AppContext.model.getValue(id, {});
        obj.showEditForm = true;
        obj.addToggle = false;
        AppContext.model.setValue(id, obj);
        this.setState({ showEditForm: true });

        this.setState({ addToggle: false });
        this.setState({ dyna: { fields: { ...rowData } } });
        AppContext.model.setValue('dyna.fields', { ...rowData });
        AppContext.model.setValue('dyna.editIndex', a);
        return true;
    };

    saveRow = event => {
        var k = event.currentTarget.id;
        let errors = this.validate(k);

        if (errors.length === 0) {
            let dynaArray = AppContext.model.getValue('dyna.fields', {});
            dynaArray.mode = 'C';
            let dataCollection = AppContext.model.getValue(this.props.datacollection);
            dataCollection.push(dynaArray);
            AppContext.model.setValue(this.props.datacollection, dataCollection);
            AppContext.model.setValue('dyna.fields', {});

            let obj = AppContext.model.getValue(k, {});
            obj.showEditForm = false;
            obj.addToggle = true;
            AppContext.model.setValue(k, obj);
            this.setState({ showEditForm: true });
        }
    };

    updateRow = event => {
        var k = event.currentTarget.id;
        let errors = this.validate(k);
        if (errors.length === 0) {
            let index = AppContext.model.getValue('dyna.editIndex', {});
            let dynaArray = AppContext.model.getValue('dyna.fields', {});
            if (dynaArray.mode && dynaArray.mode === 'C') {
                dynaArray.mode = 'C';
            } else {
                dynaArray.mode = 'U';
            }
            let dataCollection = AppContext.model.getValue(this.props.datacollection);
            dataCollection.splice(index, 1, dynaArray);
            AppContext.model.setValue(this.props.datacollection, dataCollection);
            AppContext.model.setValue('dyna.fields', {});
            this.setState({ showEditForm: false });
            this.setState({ addToggle: true });

            let obj = AppContext.model.getValue(k, {});
            obj.showEditForm = false;
            obj.addToggle = true;
            AppContext.model.setValue(k, obj);
            this.setState({ showEditForm: true });
        }
    };

    validate = id => {
        let dynaArray = AppContext.model.getValue('dyna.fields', {});
        if (!dynaArray) {
            dynaArray = [];
        }
        let msgs = this.props.validationFunctions(dynaArray);
        let html = '';
        if (Array.isArray(msgs) && msgs.length > 0) {
            msgs.map((ele, index) => {
                html = html + ele + '<br />';
            });
            document.getElementById(id + '_errorDiv').innerHTML = html;
        } else document.getElementById(id + '_errorDiv').innerHTML = '';
        return msgs;
    };

    deleteCreation = (columnData, rowData, a) => {
        return columnData ? null : (
            <UX
                type="icon"
                symbol="fa fa-trash"
                click={event => this.deleteRow(event, rowData, a)}></UX>
        );
    };

    deleteRow = (event, rowData, a) => {
        if (rowData.mode && rowData.mode === 'C') {
            let dynaCollection = [...AppContext.model.getValue(this.props.datacollection)];
            dynaCollection.remove(a);
            AppContext.model.setValue(this.props.datacollection, dynaCollection);
        } else {
            rowData.mode = 'D';
            let dataCollection = AppContext.model.getValue(this.props.datacollection);
            dataCollection.splice(a, 1, rowData);
            AppContext.model.setValue(this.props.datacollection, dataCollection);
            document.getElementById(event.currentTarget.id).closest('tr').style.display = 'none';
        }
    };

    createCustomContent = () => {
        let cc = { Delete: this.deleteCreation, Edit: this.editCreation };
        let ccDyna = this.props.customFunction;
        if (ccDyna) {
            return _.merge(cc, ccDyna());
        }
        return cc;
    };

    render() {
        const dynaTableList =
            AppContext.model.getValue(this.props.dynaTableList) !== undefined
                ? AppContext.model.getValue(this.props.dynaTableList)
                : [{}];

        let dynaId = this.props.id;
        if (AppContext.model.getValue(dynaId) === undefined)
            AppContext.model.setValue(dynaId, { showEditForm: false, addToggle: true });

        let addToggleModel = dynaId + '.addToggle';
        let showEditModel = dynaId + '.showEditForm';

        return (
            <UX type="card">
                <UX type="group" width="12">
                    <UX
                        type="dataTable"
                        col-default-headers={this.props.columnDefaultHeader}
                        col-data-keys={this.props.columnDataKeys}
                        sortable-col-names={this.props.sortableColumnNames}
                        customContent={this.createCustomContent()}
                        datacollection={this.props.datacollection}
                        keyfield={this.props.keyfield}
                        pagination={this.props.pagination}
                        hover={this.props.hover}
                        pagination-show-total={this.props.paginationShowTotal}
                        pagination-size-per-page={this.props.paginationSizePerPage}
                        striped={this.props.striped}></UX>
                </UX>

                <div className="error mt-3" id={dynaId + '_errorDiv'}></div>

                <div className="text-center">
                    <UX
                        type="button"
                        size="small"
                        labelKey={this.props.addText}
                        click={this.addFunction}
                        id={dynaId}
                        showIf={
                            !AppContext.model.getValue(showEditModel) &&
                            AppContext.model.getValue(addToggleModel)
                        }
                        className="align-self-center"></UX>
                </div>
                <UX
                    type="section"
                    className="mt-3"
                    showIf={AppContext.model.getValue(showEditModel)}>
                    <UX type="group" width="12">
                        {dynaTableList.map((item, key) => {
                            let tmp = 'dyna.fields.' + item['name'];

                            if (item != null && item['type'] === 'text') {
                                if (
                                    item != null &&
                                    item['type'] === 'text' &&
                                    item['inputMode'] === undefined &&
                                    item['maxLength'] === undefined
                                ) {
                                    return (
                                        <UX
                                            type="text"
                                            id={item['name']}
                                            labelKey={item['label']}
                                            model={tmp}
                                            labelRequiredClassIf={item['fieldRequired']}
                                        />
                                    );
                                } else if (
                                    item != null &&
                                    item['type'] === 'text' &&
                                    item['inputMode'] !== undefined &&
                                    item['inputMode'] === undefined
                                ) {
                                    return (
                                        <UX
                                            type="text"
                                            id={item['name']}
                                            labelKey={item['label']}
                                            model={tmp}
                                            labelRequiredClassIf={item['fieldRequired']}
                                            inputMode={item['inputMode']}
                                        />
                                    );
                                } else if (
                                    item != null &&
                                    item['type'] === 'text' &&
                                    item['inputMode'] === undefined &&
                                    item['maxLength'] !== undefined
                                ) {
                                    return (
                                        <UX
                                            type="text"
                                            id={item['name']}
                                            labelKey={item['label']}
                                            model={tmp}
                                            labelRequiredClassIf={item['fieldRequired']}
                                            maxLength={item['maxLength']}
                                        />
                                    );
                                } else if (
                                    item != null &&
                                    item['type'] === 'text' &&
                                    item['inputMode'] !== undefined &&
                                    item['maxLength'] !== undefined
                                ) {
                                    return (
                                        <UX
                                            type="text"
                                            id={item['name']}
                                            labelKey={item['label']}
                                            model={tmp}
                                            labelRequiredClassIf={item['fieldRequired']}
                                            maxLength={item['maxLength']}
                                            inputMode={item['inputMode']}
                                        />
                                    );
                                }
                            }

                            if (item != null && item['type'] === 'select')
                                return (
                                    <UX
                                        type="select"
                                        defaultOption={item['defaultOption']}
                                        defaultOptionValue={item['defaultOptionValue']}
                                        id={item['name']}
                                        labelKey={item['label']}
                                        ref-table={item['refTable']}
                                        model={tmp}
                                        labelRequiredClassIf={item['labelRequiredClassIf']}
                                    />
                                );
                        })}
                    </UX>
                </UX>
                <UX type="section" showIf={AppContext.model.getValue(showEditModel)}>
                    <UX
                        type="button"
                        size="medium"
                        mode="default"
                        labelKey="cancel"
                        click={this.cancelFunction}
                        id={dynaId}
                        className="align-self-start"></UX>

                    <UX
                        type="button"
                        size="medium"
                        mode="primary"
                        labelKey="save"
                        click={this.saveRow}
                        id={dynaId}
                        className="pull-right"
                        showIf={AppContext.model.getValue(addToggleModel)}></UX>

                    <UX
                        type="button"
                        size="medium"
                        mode="primary"
                        labelKey="update"
                        click={this.updateRow}
                        className="pull-right"
                        id={dynaId}
                        showIf={
                            AppContext.model.getValue(showEditModel) &&
                            !AppContext.model.getValue(addToggleModel)
                        }></UX>
                </UX>
            </UX>
        );
    }
}
