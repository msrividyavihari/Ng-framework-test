// import { Util } from '@d-lift/core';
import {
    AppContext,
    Lift,
    RefrenceTables,
    Util,
    refTables as refTablesSubject,
} from '@d-lift/core';

import { AbstractRefrenceTableAdapter } from '@d-lift/core';
import RefUtil from '@/Utills/RefUtil';
import _ from 'lodash';

/**
 * Refrence Table Adapter to define and massage Refrence Table Data
 */

const keyPrefix = 'lift-reftable-';
class RefrenceTableAdapter extends AbstractRefrenceTableAdapter {
    constructor() {
        super(AbstractRefrenceTableAdapter, ['invoke']);
        this.referenceTableEndpoint =
            AppContext.config === undefined ? '/' : AppContext.config.referenceTableEndpoint;
        this.refdata = {
            referenceTables: [],
        };
    }
    /**
     * How description key should be identified for each locale in refrence table data
     *
     * @param {{locale : "Locale Name", tableName: "Refrence Table Name"}} refTableObj
     */
    defineDescriptionKey(refTableObj) {
        // let localeSuffix = 'ENGLISH';
        // switch (refTableObj.locale) {
        //     case Constants.LOCALE.ESPANOL.CODE:
        //         localeSuffix = 'SPANISH';
        //         break;
        //     case Constants.LOCALE.ENGLISH.CODE:
        //     default:
        //         localeSuffix = 'ENGLISH';
        //         break;
        // }
        // return 'DESC' + localeSuffix;
        return 'DESCRIPTION';
    }

    /**
     * How refrence table code key should be identified for each locale in refrence table data
     *
     * @param {{locale : "Locale Name", tableName: "Refrence Table Name"}} refTableObj
     */
    defineCodeKey(refTableObj) {
        return 'CODE';
    }

    /**
     * Asynchronous method to invoke refrence table service and massage response and return a JSON object 
     * with following structure.
     * For more info: http://localhost:1800/#/Adapter?id=refrence-table-adapter
    ```json
    {
        "TABLE_NAME_1" : [
            {
                "Description Key for Locale 1" : "Description",
                "Description Key for Locale 2" : "Description", 
                "Code Key" : "Code"
            }
        ],
        "TABLE_NAME_2" : [
            {
                "Description Key for Locale 1" : "Description",
                "Description Key for Locale 2" : "Description", 
                "Code Key" : "Code"
            }
        ] 
    }
    ```
     * @param {[]} reftableList
     * @summary Define Refrence Table Invoke call. It must be async nd return promise. All HTTP Calls in this 
     * method should define with await.
     * @tutorial  http://localhost:1800/#/Adapter?id=refrence-table-adapter 
     */
    invoke = async reftableList => {
        //let refTableResponse = {};
        let refTableResponse = await Util.HTTP.post(
            AppContext.config.refrenceTableEndpoint + '/getReferenceTableData',
            reftableList,
        );
        // for await (const refTable of reftableList) {
        //let refTablePayload = { tableName: refTable };
        //Lift.logger.debug(refTablePayload);

        // let responseData = await Util.HTTP.get(
        //     AppContext.config.refrenceTableEndpoint + 'ReferenceResponse.json',
        // );

        // Lift.logger.debug(refTable);
        // refTableResponse[refTable] = responseData.data[refTable];
        //    }
        //return { data: refTableResponse };
        return { data: { ...refTableResponse.data } };
        // let refTableServiceResponse = await Util.HTTP.get(
        //     this.refrenceTableEndpoint + AppContext.pagedetails.getPageName() + '.json',
        // );
        // console.log('Refrence table list: ', reftableList);
        // return refTableServiceResponse;
    };

    loadRefrenceTable = async (locale, callee) => {
        if (RefrenceTables.getRefTableList().length <= 0) return null;
        const storageLookupKey = keyPrefix + AppContext.pagedetails.getPageName() + '_Ref';
        let refPayload;
        if (Util.getSessionData(storageLookupKey) != null && Lift.SiteConfig.RefrenceTableCache) {
            refPayload = {
                ...JSON.parse(Util.getSessionData(storageLookupKey)),
            };
        } else {
            let response = await this.invoke(RefrenceTables.getRefTableList());
            refPayload = response.data;
            Util.setSessionData(storageLookupKey, JSON.stringify(refPayload));
        }
        let refTableStruct = {};
        _.forOwn(refPayload, (value, key) => {
            let refTableArray = [];
            let refTableName = key;
            _.forEach(value, (refArray, key) => {
                let refNode = {
                    DESCRIPTION: _.get(
                        refArray,
                        this.defineDescriptionKey({
                            tableName: refTableName,
                            locale: locale,
                        }),
                    ),
                    CODE: _.get(
                        refArray,
                        this.defineCodeKey({
                            tableName: refTableName,
                            locale: locale,
                        }),
                    ),
                };
                refTableArray.push(refNode);
            });
            refTableStruct[refTableName] = refTableArray;
        });
        callee.refTablesCache[locale] = refTableStruct;
        await refTablesSubject.next(refTableStruct);
        RefUtil.invokeAfterRef();
    };
}

/**
 * Refrence Table Adapter to define and massage Refrence Table Data
 */
const refTableAdapter = new RefrenceTableAdapter();
export default refTableAdapter;
