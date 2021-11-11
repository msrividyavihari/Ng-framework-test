import { Util, AppContext } from '@d-lift/core';
import _ from 'lodash';

const RefUtil = {
    getRefTblFldDescription: function(tableName, fielldtoCompare, valuetoCompare, basePage) {
        let description = undefined;

        if (!_.isEmpty(basePage.state.refTablesCache) && !_.isEmpty(valuetoCompare)) {
            if (fielldtoCompare === undefined) {
                fielldtoCompare = 'CODE';
            }
            let compareObj = {};
            _.set(compareObj, fielldtoCompare, valuetoCompare);
            description = _.filter(
                basePage.state.refTablesCache[Util.getLocale()][tableName],
                compareObj,
            );
            description = !_.isEmpty(description) ? description[0].DESCRIPTION : '';
        }
        return description;
    },

    hideDropDownOptionByValue(selectId, optionValue) {
        document.getElementById(selectId).childNodes.forEach(function(item) {
            if (item.value.toString() === optionValue.toString()) {
                item.style.display = 'none';
            }
        });
    },

    invokeAfterRef() {
        if (
            AppContext.pagedetails.getPageName() === 'ApplicationRegistration' &&
            AppContext.model.getValue('sspMode') !== 'Y'
        )
            this.hideDropDownOptionByValue('source', 'SS');
    },
};

export default RefUtil;
