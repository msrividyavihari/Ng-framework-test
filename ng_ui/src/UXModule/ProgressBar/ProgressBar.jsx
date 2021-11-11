import React, { Component } from 'react';
import { UX } from '@d-lift/uxcomponents';
import './ProgressBar.css';

export default class ProgressBar extends Component {
    render() {
        return (
            <UX type="group">
                <UX percent={this.props.progress} type="progressbar" />
                <UX type="label" className="pbar" showIf ={this.props.progress !== ''}>
                    {this.props.progress}
                </UX>
            </UX>
        );
    }
}
