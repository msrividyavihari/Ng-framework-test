import { BaseComponent } from '@d-lift/uxcomponents';
import CustomCheckBoxTemplate from './CustomCheckBox.rt';
import React from 'react';
import PropTypes from 'prop-types';
import _ from 'lodash';
import './CustomCheckBox.css';

class CustomCheckBoxWrap extends BaseComponent {
    constructor(props) {
        super(props);
        this.state[props.model] = this.props.getStateValue(this.props.model);
    }

    renderComponent() {
        return CustomCheckBoxTemplate.bind(this).apply(this);
    }
}

const CustomCheckBox = props => <CustomCheckBoxWrap {...props} />;

CustomCheckBox.propTypes = {
    model: PropTypes.string,
    /* Applies CustomCheckBox styling based on value, inherits from CheckBoxGroup when refTables or lists are used */
    checkStyle: PropTypes.oneOf(['customcheckbox']),
    /** List of CSS classnames to apply on this component at div level */
    className: PropTypes.string,
    /** List of CSS classnames to apply on this component at checkbox level */
    checkboxClassName: PropTypes.string,
    /** List of CSS classnames to apply on this component at label level */
    labelClassName: PropTypes.string,
    /** Set truevalue prop as either string or bool, default is true*/
    trueValue: PropTypes.oneOfType([PropTypes.string, PropTypes.bool]),
    /** Set falsevalue prop as either string or bool, default is false*/
    falseValue: PropTypes.oneOfType([PropTypes.string, PropTypes.bool]),
    /** Disabled the CustomCheckBox  */
    disabled: PropTypes.bool,
    checked: PropTypes.bool,
};

CustomCheckBox.defaultProps = {
    trueValue: true,
    falseValue: false,
};

export default CustomCheckBox;
