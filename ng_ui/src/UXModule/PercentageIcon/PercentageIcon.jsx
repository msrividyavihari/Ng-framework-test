import React from 'react';
import './PercentageIcon.css';

export const PercentageIcon = props => {
    let classNae = `match ${props.color}`;
    return <div className={classNae}>{props.percentage}</div>;
};
