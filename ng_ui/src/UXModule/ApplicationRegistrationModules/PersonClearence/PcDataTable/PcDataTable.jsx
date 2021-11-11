import { BaseComponent, UX } from '@d-lift/uxcomponents';
import React from 'react';
import { PercentageIcon } from '@/UXModule/PercentageIcon/PercentageIcon';
import Template from './PcDataTable.rt';
import { AppContext, Util } from '@d-lift/core';
class PcDataTable extends BaseComponent {
    showPercentage = (row, data, index) => {
        return <PercentageIcon color="yellow" percentage={data.score}></PercentageIcon>;
    };

    showGender = (row, data, index) => {
        const searchData = AppContext.model.getValue('ar.pc.search');
        return this.returnRow(row === 'M' ? 'Male' : 'Female', searchData.gender !== row);
    };
    renderOnExpand = {
        expandRow: {
            renderer: item => {
                return (
                    <table className="w-75">
                        <tr>
                            <th className="font-weight-bold">Household</th>
                            <th className="font-weight-bold">Race</th>
                            <th className="font-weight-bold">Ethenticity</th>
                            <th className="font-weight-bold">Alias</th>
                        </tr>
                        <tr>
                            <td>
                                {item.relationShips
                                    ? item.relationShips
                                          .filter(relation =>
                                              relation.name && relation.relation ? true : false,
                                          )
                                          .map(relation => (
                                              <div>{`${
                                                  relation.name
                                              } - ${Util.getRefTableDescriptionByCode(
                                                  'DCRELATIONSHIP',
                                                  relation.relation,
                                              )}`}</div>
                                          ))
                                    : ''}
                            </td>
                            <td>{Util.getRefTableDescriptionByCode('RACE', item.raceCd)}</td>
                            <td>
                                {Util.getRefTableDescriptionByCode('ETHNICITY', item.ethnicityCd)}
                            </td>
                            <td>
                                {item.alisName ? item.alisName.map(name => <div>{name}</div>) : ''}
                            </td>
                        </tr>
                    </table>
                );
            },
            showExpandColumn: true,
            expandColumnPosition: 'right',
            onlyOneExpanding: true,
            expandHeaderColumnRenderer: ({ isAnyExpands }) =>
                isAnyExpands ? (
                    <UX type="label"> More Details</UX>
                ) : (
                    <UX type="label"> More Details</UX>
                ),
            expandColumnRenderer: ({ expanded, rowKey, expandable }) =>
                expanded ? (
                    // <UX type="icon" symbol={_this.props.columnCollapseIcon}></UX>
                    <UX className="underln" type="hyperLink">
                        Hide
                    </UX>
                ) : (
                    // <UX type="icon" symbol={_this.props.columnExpandIcon}></UX>
                    <UX className="underln" type="hyperLink">
                        View
                    </UX>
                ),
        },
    };

    contentFirstName = (row, data, index) => {
        const searchData = AppContext.model.getValue('ar.pc.search');
        return this.returnRow(row, searchData.firstName !== row);
    };

    contentLastName = (row, data, index) => {
        const searchData = AppContext.model.getValue('ar.pc.search');
        return this.returnRow(row, searchData.lastName !== row);
    };

    contentDob = (row, data, index) => {
        const searchData = AppContext.model.getValue('ar.pc.search');
        return this.returnRow(row, searchData.dobDt !== row);
    };

    returnRow = (row, diff) => {
        return (
            <UX type="para" className={diff ? 'ux-color-danger' : ''}>
                {row}
            </UX>
        );
    };

    selPrimIndvPc = id => {
        this.props.selFunction(id, false);
    };
    deselPrimIndvPc = id => {
        this.props.deselFunction(id, false);
    };

    piCustomeColumn = (columnData, rowData) => {
        const applicantType = this.props.applicantType === 'P' ? 'Primary' : 'Additional';
        return (
            <>
                <UX
                    type="button"
                    mode="primary"
                    className="selbtnFc1"
                    size="small"
                    name={rowData.indvId + '_B'}
                    click={event => this.selPrimIndvPc(rowData.indvId)}>
                    Select
                </UX>
                <UX
                    type="button"
                    mode="primary"
                    className="selbtnFc2"
                    size="small"
                    name={rowData.indvId + '_G'}
                    click={event => this.deselPrimIndvPc(rowData.indvId)}>
                    {applicantType} Applicant
                </UX>
            </>
        );
    };

    renderComponent() {
        return Template.bind(this).apply(this);
    }
}

export default PcDataTable;
