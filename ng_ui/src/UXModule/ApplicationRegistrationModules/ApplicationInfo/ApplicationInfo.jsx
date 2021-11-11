import { BaseComponent } from '@d-lift/uxcomponents';
import ApplicationInfoTemplate from './ApplicationInfo.rt';
import { AppContext } from '@d-lift/core';
import errorMessages from '../../../../public/validation/commonValidation.json';
import { Date } from 'sugar';
import './ApplicationInfo.css';

class ApplicationInfo extends BaseComponent {
    uxComponentDidMount = () => {
        AppContext.model.setValue('appRegNew', {
            ArApplicationForAid: {
                dateTimeRegistered: new Date().toLocaleString().raw.replace(/,/g, ''),
                time: new Date().toLocaleTimeString().slice(-2) == 'AM' ? 'AM' : 'PM',
                appRegDt: new Date()
                    .toLocaleTimeString()
                    .replace(/.M/gm, '')
                    .raw.replace(/,/g, ''),
            },
        });
    };

    validateMonthDiffRecievedDate = event => {
        if (event !== null) {
            console.log(event._sugar_utc);
            if (event._sugar_utc === false) {
                const currentMonth = new Date();
                const selectedMonth = new Date(
                    AppContext.model.getValue('appRegNew.ArApplicationForAid.appRecvdDt'),
                );
                //const isPriorMonth = currentMonth.getMonth() > selectedMonth.getMonth();
                const monthDiff = this.getMonthDiff(selectedMonth, currentMonth);
                if (monthDiff) {
                    AppContext.model.setValue('appInfoError', true);
                    AppContext.model.setValue('appInfoErrorMsg', errorMessages.GANEW551);
                } else {
                    AppContext.model.setValue('appInfoError', false);
                    AppContext.model.setValue('appInfoErrorMsg', '');
                }
            }
        }
    };

    validateRegisteredDate = event => {
        if (event._sugar_utc === false) {
            const currentMonth = new Date();
            const selectedMonth = new Date(
                AppContext.model.getValue('appRegNew.ArApplicationForAid.appRegDt'),
            );
            //const isPriorMonth = currentMonth.getMonth() > selectedMonth.getMonth();
            const monthDiff = this.getMonthDiff(selectedMonth, currentMonth);
            if (monthDiff) {
                AppContext.model.setValue('appInfoError', true);
                AppContext.model.setValue('appInfoErrorMsg', [errorMessages.GANEW551]);
            } else {
                AppContext.model.setValue('appInfoError', false);
                AppContext.model.setValue('appInfoErrorMsg', '');
            }
        }
    };

    getMonthDiff = (d1, d2) => {
        var months;
        months = (d2.getFullYear() - d1.getFullYear()) * 12;
        months -= d1.getMonth() + 1;
        months += d2.getMonth();
        if (d2.getDate() >= d1.getDate()) months++;

        if (months > 13) {
            return true;
        } else {
            return false;
        }
    };

    renderComponent() {
        return ApplicationInfoTemplate.bind(this).apply(this);
    }
}

export default ApplicationInfo;
