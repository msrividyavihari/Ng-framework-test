import React, { Component } from 'react';
import { UX } from '@d-lift/uxcomponents';
import './CustomHeader.css';


export default class CustomHeader extends Component {
    render() {
        return (
            <div className="card-allign">
                < UX type="group" >
                    <UX type="group" width="12">
                        <UX type="label" >Application No : </UX>
                    </UX>
                    <UX type="group" width="12">
                        <UX type="label" >Status : </UX>
                    </UX>
                    <UX type="group" width="12">
                        <UX type="label" >Primary Name : </UX>
                    </UX >
                    <span class="fa fa-angle-down padding90" onClick={this.toggle}></span>
                </UX >
                <div id="collapseSection" className="displayHide" >
                    <UX type="group" width="12">
                        <UX type="label" >Links : </UX>
                    </UX>
                </div>

            </div >
        );
    }
    toggle() {
        var x = document.getElementById('collapseSection');
        if (x.style.display === "none") {
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }
};

