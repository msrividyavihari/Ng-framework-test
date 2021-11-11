import CustomCollapsePanelTemplate from './CustomCollapsePanel.rt';
import _ from 'lodash';
import React from 'react';
import PropTypes from 'prop-types';
import sz from 'sizzle';
import { Components } from '@d-lift/uxcomponents';
import './CustomCollapsePanel.css';
import CustomCollapseNative from './CustomCollapseNative';

class CustomCollapsePanel extends Components.collapsePanel {
    constructor(props) {
        super(props);
        this.state.hasFieldError = false;
        this.collapseSectionsId = [];
        this.collapseSections = {};
        this.collapseSectionlength = null;
    }

    updateActiveClass(e) {
        let panelElements = sz('div.ux-custom-panel');
        panelElements.forEach(element => {
            element.classList.remove('ux-custom-panel-active');
            sz(`[data-toggle="collapse"]`, element)[0].classList.add('collapsed');
        });
        panelElements = sz('div.ux-custom-panel:has(.state-expanded)');
        panelElements.forEach(element => {
            element.classList.add('ux-custom-panel-active');
            sz(`[data-toggle="collapse"]`, element)[0].classList.remove('collapsed');
        });
    }

    uxComponentDidMount() {
        let collapseSectionsId = [];
        let sectionFlags = {};
        let children = this.props.children;
        if (!Array.isArray(children)) {
            children = [this.props.children];
        }
        React.Children.forEach(children, (value, index) => {
            let collapse = new CustomCollapseNative(
                `#${value.props.id}_panel [data-toggle="collapse"]`,
            );
            this.collapseSections[value.props.id] = collapse;
            if (
                (value.props.hasOwnProperty('component-type') ||
                    value.props.hasOwnProperty('type')) &&
                (value.props['component-type'] === 'collapseSection' ||
                    value.props.type === 'collapseSection') &&
                (value.props.showIf === undefined || value.props.showIf === true)
            ) {
                collapseSectionsId.push(value.props.id);
                sectionFlags[value.props.id] = {};
                if (index === 0) {
                    sectionFlags[value.props.id]['readonly'] = false;
                    sectionFlags[value.props.id]['visited'] = true;
                } else {
                    sectionFlags[value.props.id]['readonly'] = true;
                    sectionFlags[value.props.id]['visited'] = false;
                }
                sectionFlags[value.props.id]['disablePanel'] = value.props.disablePanel;

                sectionFlags[value.props.id]['ownBodyClass'] = value.props.ownBodyClass;
            }
        });
        this.collapseSectionsId = collapseSectionsId;

        _.forEach(this.collapseSectionsId, collapseSectionId => {
            if (sectionFlags[collapseSectionId]['disablePanel'] === 'false') {
                this.collapseSections[collapseSectionId].show();
            } else {
                this.collapseSections[collapseSectionId].hide();
            }
        });

        this.collapseSectionlength = collapseSectionsId.length;
        this.componentConfigHandler('sectionFlags.' + this.props.id, sectionFlags);
        this.editDoneMode =
            this.props.editDoneMode === undefined
                ? this.context?.ThemeContext?.options?.collapsePanel?.editDoneMode === undefined
                    ? true
                    : this.context?.ThemeContext?.options?.collapsePanel?.editDoneMode
                : this.props.editDoneMode;
        this.updateActiveClass();
        this.preserveReadOnlyStateOfElements(sz('[readonly]'));
    }

    componentDidUpdate() {
        if (
            (this.props.enableReadOnlyState !== undefined ||
                this.context?.ThemeContext?.options?.customCollapsePanel?.enableReadonly ===
                    true) &&
            this.editDoneMode === true
        ) {
            let children = this.props.children;
            if (!Array.isArray(children)) {
                children = [this.props.children];
            }
            let sectionFlags = {
                ...this.getComponentConfigValue('sectionFlags.' + this.props.id),
            };
            React.Children.forEach(children, (value, index) => {
                if (
                    (value.props.hasOwnProperty('component-type') ||
                        value.props.hasOwnProperty('type')) &&
                    (value.props['component-type'] === 'collapseSection' ||
                        value.props.type === 'collapseSection')
                ) {
                    if (
                        sectionFlags !== undefined &&
                        sectionFlags[value.props.id] !== undefined &&
                        sectionFlags[value.props.id].readonly === true
                    ) {
                        this.collapseSections[value.props.id].enableReadonly();
                    } else {
                        this.collapseSections[value.props.id].disableReadonly();
                    }
                }
            });
        }

        if (
            this.props.enableReadOnlyAllPanels !== undefined &&
            this.props.enableReadOnlyAllPanels === true
        ) {
            this.readOnly();
        }
        this.updateActiveClass();
    }

    readOnly() {
        let elements = sz(
            'input,select,textarea,div.ux-radio,div.ux-radio-card,div.ux-radio-bar,.readonly-no-value,div.datepicker,div.ux-input-group,div.ux-checkbox,div.ux-switch,div.ux-cb-persona-wrapper,div.ux-toggle-card',
        );
        if (Array.isArray(elements)) {
            elements.forEach(el => {
                el.setAttribute('readonly', true);
            });
        }
        sz('button:not([id$="appRegNext"])').forEach(el => {
            el.setAttribute('disabled', true);
        });

        sz('input.custom-control-input').forEach(el => {
            el.setAttribute('disabled', true);
        });
    }

    renderComponent() {
        this.disableNumber =
            this.props['disable-number'] === undefined
                ? this.context?.ThemeContext?.options?.customCollapsePanel?.disableNumber
                : this.props['disable-number'];
        this.disableNumber = this.disableNumber === undefined ? false : this.disableNumber;

        this.disableIcon =
            this.props['disable-icon'] === undefined
                ? this.context?.ThemeContext?.options?.customCollapsePanel?.disableIcon
                : this.props['disable-icon'];
        this.disableIcon = this.disableIcon === undefined ? false : this.disableIcon;

        return CustomCollapsePanelTemplate.bind(this).apply(this);
    }
}

CustomCollapsePanel.propTypes = {
    /**  */
    labelKey: PropTypes.string,
    /** List CSS classnames developer wants to apply on this component */
    class: PropTypes.string,
    /** Show If Condition to make decision when to show this component
     * Example "showIf": "(e) => { <Your business logic> }"
     */
    showIf: PropTypes.bool,
    /** Define unique id for the component */
    id: PropTypes.string,
    /** collapsesection :  Title Key to integrate with Content Manager. */
    titleKey: PropTypes.string,
    /** CollapseSection : Title key developer can provide direct text  */
    titleText: PropTypes.string,
    /** CollapseSection : key to get text of modifybutton from Content Manager */
    modifyButtonKey: PropTypes.string,
    /** CollapseSection : developer can provide direct text of modifybutton */
    modifyButtonText: PropTypes.string,
    /** CollapseSection : key to get text of donebutton from Content Manager */
    doneButtonKey: PropTypes.string,
    /** CollapseSection : developer can provide direct text of donebutton */
    doneButtonText: PropTypes.string,
    /** CollapseSection : defines is this section is last section or not */
    'last-section-if': PropTypes.bool,
    /** enableReadOnlyState: If this prop is defined as true, it will make all input elements as readonly when edit button is displayed */
    enableReadOnlyState: PropTypes.bool,
    /** Enables/Disabled Edit Done mode */
    editDoneMode: PropTypes.bool,
    /** hide panel when done action is invoked */
    hideOnDone: PropTypes.bool,
    /** collapsesection :  Label Key to integrate with Content Manager. */
    headerLabelKey: PropTypes.string,
    /** CollapseSection : Label key developer can provide direct text  */
    headerLabelText: PropTypes.string,
    /** Override original panel enabling behavior */
    disablePanel: PropTypes.bool,
    /** Set different panel body class when required */
    ownBodyClass: PropTypes.string,
    /** enableReadOnlyState: If this prop is defined as true, it will make all the panels readonly */
    enableReadOnlyAllPanels: PropTypes.bool,
};

CustomCollapsePanel.defaultProps = {
    numbered: true,
    editDoneMode: true,
    hideOnDone: true,
    ownBodyClass: 'ux-custom-panel-body',
};
export default CustomCollapsePanel;
