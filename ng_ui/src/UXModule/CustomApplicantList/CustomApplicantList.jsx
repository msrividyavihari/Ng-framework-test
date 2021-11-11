import React, { Component } from 'react';
import { UX, BaseComponent } from '@d-lift/uxcomponents';
import { AppContext, Util } from '@d-lift/core';
import './CustomApplicantList.css';
import { PercentageIcon } from '../PercentageIcon/PercentageIcon';

class CustomApplicantList extends BaseComponent {
    renderComponent() {
        var healthCareImage = document.createElement('img');
        healthCareImage.setAttribute('src', '/icons/Healthcare.png');
        healthCareImage.setAttribute(
            'style',
            'background:white;margin-left: 4%;margin-right: 3%;height: 27px;width: 51px;position: absolute;top: 10px;left: 0.5%',
        );
        healthCareImage.setAttribute('height', '38');

        let programList = AppContext.model.getValue('appOverview.programList')
            ? AppContext.model.getValue('appOverview.programList')
            : [{}];

        return (
            <div className="cardData">
                <UX type="grid">
                    <UX type="row">
                        {programList.map((item, i) => (
                            <UX type="column">
                                <div className="main">
                                    <div className="outer_summary" id={item.program}>
                                        <UX type="grid">
                                            <UX type="row">
                                                <UX type="column">
                                                    <div class="header_summary">
                                                        {item.program === 'MA' ? (
                                                            <span className="bar">
                                                                <span className="medicaid"></span>
                                                                <UX
                                                                    type="header"
                                                                    className="headers"
                                                                    size="5">
                                                                    Applying for Healthcare
                                                                </UX>
                                                            </span>
                                                        ) : item.program === 'FS' ? (
                                                            <span className="bar">
                                                                <span className="snap"></span>
                                                                <UX
                                                                    type="header"
                                                                    className="headers"
                                                                    size="5">
                                                                    Applying for SNAP
                                                                </UX>
                                                            </span>
                                                        ) : (
                                                            <span className="bar">
                                                                <span className="tanf"></span>
                                                                <UX
                                                                    type="header"
                                                                    className="headers"
                                                                    size="5">
                                                                    Applying for TANF
                                                                </UX>
                                                            </span>
                                                        )}
                                                    </div>
                                                </UX>
                                            </UX>
                                        </UX>
                                        <div className="inner_summary">
                                            <UX type="grid">
                                                <UX type="row">
                                                    {item.applicantName
                                                        ? item.applicantName.map((name, i) => (
                                                              <UX type="column">
                                                                  <UX type="grid">
                                                                      <UX type="row">
                                                                          {item.applicantAge[i] >=
                                                                          18 ? (
                                                                              <div className="indv1"></div>
                                                                          ) : (
                                                                              <div className="indv2"></div>
                                                                          )}
                                                                      </UX>
                                                                      <UX type="row">
                                                                          <div className="indvName">
                                                                              {name}
                                                                          </div>
                                                                      </UX>
                                                                  </UX>
                                                              </UX>
                                                          ))
                                                        : ''}
                                                </UX>
                                                {/* <tr>
                                                    {item.applicantName
                                                        ? item.applicantName.map(name => (
                                                              <td>
                                                                  <div>{name}</div>
                                                              </td>
                                                          ))
                                                        : ''}
                                                </tr> */}
                                            </UX>
                                        </div>
                                    </div>
                                </div>
                            </UX>
                        ))}
                    </UX>
                </UX>
            </div>
        );
    }
}

export default CustomApplicantList;
