import CustomSelectableCardsTemplate from './CustomSelectableCards.rt';
import { BaseComponent } from '@d-lift/uxcomponents';
import { AppContext } from '@d-lift/core';
import PropTypes from 'prop-types';
import './CustomSelectableCards.css';

class CustomSelectableCards extends BaseComponent {
    set;
    constructor(props) {
        super(props);
        this.state[props.model] = this.props.getStateValue(this.props.model);
        this.set = new Set();
    }

    onSelectCard = async event => {
        event.preventDefault();
        console.log('onSelectCard');
        console.log(this.props.id);
        var x = document.getElementById(this.props.id);
        if (x.className === 'card custom-selectable-card') {
            x.className = 'card custom-card-selected';
        } else {
            x.className = 'card custom-selectable-card';
        }

        if (typeof this.props.SSN !== undefined && typeof this.props.KEY !== undefined) {
            console.log(this.props.KEY + '_label');
            var content = document.getElementById(this.props.id + '_label')?.textContent;
            console.log(content);
            var data = AppContext.model.getValue(
                'appRegNew.ConflictApplicant.ssn' + this.props.SSN.toString(),
            );

            //  var data = AppContext.model.getValue( 'appRegNew.ConflictApplicant.ssn')
            console.log(data);
            let tempData = { ...data };
            var ssn = AppContext.model.getValue('appRegNew.Conflits.ssn');
            if (!Array.isArray(ssn)) {
                ssn !== undefined ? (ssn = [ssn]) : (ssn = []);
            }

            if (!ssn?.includes(this.props.SSN)) {
                ssn.push(this.props.SSN);
            }

            console.log(tempData);
            let json = { ...tempData[this.props.SSN] };
            //

            if (json[this.props.KEY] !== content) {
                json[this.props.KEY] = content;
            } else {
                delete json[this.props.KEY];
            }

            tempData[this.props.SSN] = json;
            let key = 'appRegNew.ConflictApplicant.ssn' + this.props.SSN;
            console.log(ssn);

            AppContext.model.setValue('appRegNew.Conflits.ssn', ssn);
            AppContext.model.setValue(key, tempData);
        }
    };

    renderComponent() {
        return CustomSelectableCardsTemplate.bind(this).apply(this);
    }
}

CustomSelectableCards.propTypes = {
    mode: PropTypes.string,
};

CustomSelectableCards.defaultProps = {
    mode: 'read',
};

export function hello() {
    console.log(this.props.SSN);
    return 'Hello!';
}

export default CustomSelectableCards;
