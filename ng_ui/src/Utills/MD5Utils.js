import { AppContext } from '@d-lift/core';
const md5 = require('md5');

let checkMD5Hash = id => {
    let mReturn = false;

    switch (id) {
        case 'application_info': {
            let data = {
                dateTimeRegistered: AppContext.model.getValue(
                    'appRegNew.ArApplicationForAid.dateTimeRegistered',
                ),
                appRegDt: AppContext.model.getValue('appRegNew.ArApplicationForAid.appRegDt'),
                time: AppContext.model.getValue('appRegNew.ArApplicationForAid.time'),
                appRecvdDt: AppContext.model.getValue('appRegNew.ArApplicationForAid.appRecvdDt'),
                appModeCd: AppContext.model.getValue('appRegNew.ArApplicationForAid.appModeCd'),
                appSignedSw: AppContext.model.getValue('appRegNew.ArApplicationForAid.appSignedSw'),
            };
            mReturn = _mCalculateHashAndCheckForChange(
                'appreg.panel.' + id + '_md5',
                JSON.stringify(data),
            );
            break;
        }

        case 'contact_info': {
            let data = { contact_info: AppContext.model.getValue('appreg.panal.contact_info') };

            mReturn = _mCalculateHashAndCheckForChange(
                'appreg.panel.' + id + '_md5',
                JSON.stringify(data),
            );
            break;
        }

        case 'authorised_representative': {
            let data = {
                authorised_representative: AppContext.model.getValue(
                    'appreg.panal.authorised_representative',
                ),
            };
            mReturn = _mCalculateHashAndCheckForChange(
                'appreg.panel.' + id + '_md5',
                JSON.stringify(data),
            );
            break;
        }

        case 'file_clearance': {
            let data = { fileClearStatus: AppContext.model.getValue('fileClearStatus') };
            mReturn = _mCalculateHashAndCheckForChange(
                'appreg.panel.' + id + '_md5',
                JSON.stringify(data),
            );
            break;
        }
        case 'program_selection': {
            let data = {
                program_selection: AppContext.model.getValue('appreg.panel.program_selection'),
            };
            mReturn = _mCalculateHashAndCheckForChange(
                'appreg.panel.' + id + '_md5',
                JSON.stringify(data),
            );
            break;
        }

        case 'psel_json': {
            let data = {
                program_selection: AppContext.model.getValue('appreg.panel.psel_json'),
            };
            mReturn = _mCalculateHashAndCheckForChange(
                'appreg.panel.' + id + '_md5',
                JSON.stringify(data),
            );
            break;
        }

        default:
            mReturn = false;
            break;
    }
    return mReturn;
};

let _mCalculateHashAndCheckForChange = (id, data) => {
    if (data !== undefined) {
        if (AppContext.model.getValue(id) === undefined || AppContext.model.getValue(id) == null) {
            AppContext.model.setValue(id, md5(data));
            return true;
        } else if (AppContext.model.getValue(id) === md5(data)) {
            return false;
        } else if (AppContext.model.getValue(id) !== md5(data)) {
            AppContext.model.setValue(id, md5(data));
            return true;
        }
    }
    return true;
};
export default checkMD5Hash;
