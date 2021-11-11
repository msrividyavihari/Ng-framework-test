import React, { Component } from 'react';
import { UX, BaseComponent } from '@d-lift/uxcomponents';
import { AppContext, Util } from '@d-lift/core';
import './ResultPanel.css';
import { PercentageIcon } from '../PercentageIcon/PercentageIcon';

class ResultPanel extends BaseComponent {
    selPrimaryIndvRp = id => {
        this.props.selFunction(id, true);
    };
    deselPrimaryIndvRp = id => {
        this.props.deselFunction(id, true);
    };

    renderComponent() {
        let collectionList = AppContext.model.getValue(this.props.collectionList)
            ? AppContext.model.getValue(this.props.collectionList)
            : [{}];
        const applicantType = this.props.applicantType === 'P' ? 'Primary' : 'Additional';
        let cardData = collectionList.map((item, i) => {
            return (
                <div className="main">
                    <div className="outer" id={item.indvId}>
                        <div class="header">
                            <span className="barRP">
                                <PercentageIcon
                                    color="#19E4D4"
                                    percentage={item.score}></PercentageIcon>
                                <span className="name">
                                    {item.firstName} {item.midName} {item.lastName} -
                                </span>
                                <span>
                                    {item.gender === 'M' ? ' Male' : ' Female'}, Age {item.age}
                                </span>
                            </span>
                            <span className="selBtnSpanRp">
                                <UX
                                    type="button"
                                    mode="primary"
                                    className="selbtnFc1"
                                    size="small"
                                    name={item.indvId + '_B'}
                                    click={event => this.selPrimaryIndvRp(item.indvId)}>
                                    Select as {applicantType} Applicant
                                </UX>
                                <UX
                                    type="button"
                                    mode="primary"
                                    className="selbtnFc2"
                                    size="small"
                                    name={item.indvId + '_G'}
                                    click={event => this.deselPrimaryIndvRp(item.indvId)}>
                                    <span>&#10003; &nbsp;</span> {applicantType} Applicant
                                </UX>
                            </span>
                        </div>

                        <div className="inner">
                            <table>
                                <tr>
                                    <th>SSN</th>
                                    <th>Individual ID</th>
                                    <th>Date of Birth</th>
                                    <th>Alias</th>
                                    <th>Race</th>
                                    <th>Ethnicity</th>
                                    <th>Household</th>
                                </tr>
                                <tr>
                                    <td>{item.ssn}</td>
                                    <td>{item.indvId}</td>
                                    <td>{item.dobDt}</td>
                                    <td>
                                        {item.alisName
                                            ? item.alisName.map(name => <div>{name}</div>)
                                            : ''}
                                    </td>
                                    <td>
                                        {Util.getRefTableDescriptionByCode('RACE', item.raceCd)}
                                    </td>
                                    <td>
                                        {Util.getRefTableDescriptionByCode(
                                            'ETHNICITY',
                                            item.ethnicityCd,
                                        )}
                                    </td>
                                    <td>
                                        {item.relationShips
                                            ? item.relationShips
                                                  .filter(relation =>
                                                      relation.name && relation.relation
                                                          ? true
                                                          : false,
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
                                </tr>
                            </table>
                        </div>
                    </div>
                    <UX type="lineBreak"></UX>
                </div>
            );
        });

        return cardData;
    }
}

export default ResultPanel;
