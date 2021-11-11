import './UXPopupComponent.css';
import React from 'react';
import { UX } from '@d-lift/uxcomponents'; 


const dataTable = (props) => {
    
    return (
        <UX type="section">
            <UX
                type="group"
                width="5">
            <UX
                id="application-id"
                labelText="Application Id"
                model={props.popupId}
                type="checkboxGroup"
                vertical-align="true"
            >
            <UX
                id="43784"
                text="43784"
                type="checkbox"
                value="43784"
                trueValue = "true"
                falseValue = "false"
            />
            <UX
                id="43784"
                text="574395"
                type="checkbox"
                value="574395"
                trueValue = "true"
                falseValue = "false"
            />
            </UX>
            </UX>
        </UX>
    )
}


export default dataTable;