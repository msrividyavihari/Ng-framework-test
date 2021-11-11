import React from 'react';
import PropTypes from 'prop-types';
import BootstrapTable from 'react-bootstrap-table-next';
import _ from 'lodash';
import paginationFactory, {
    PaginationProvider,
    PaginationListStandalone,
    SizePerPageDropdownStandalone,
} from 'react-bootstrap-table2-paginator';
import { Constants } from '@d-lift/core';
import { DefaultMessages as DefaultLabels } from '@d-lift/core';
import { Util } from '@d-lift/core';
import ToolkitProvider from 'react-bootstrap-table2-toolkit';
// import cellEditFactory from 'react-bootstrap-table2-editor';
import { BaseComponent, UX } from '@d-lift/uxcomponents';
import './CustomDataTable.css';

class DataTableWrap extends BaseComponent {
    dataTableOptions = {};
    columns = [];
    constructor(props) {
        super(props);
        this.constructOptions();
        if (this.props.hasOwnProperty('srotable-col-names')) {
            console.warn(
                "DEPRECATED PROP: 'srotable-col-names'. Please use 'sortable-col-names'. The deprecated prop will be removed in the v4.0 release.",
            );
        }
    }
    /**
     * Following function will read the Datatable related props from the UX tag like
     *  *col-header-keys* -- Related to Table column headers
     *  *col-default-headers* -- Column default header strings Either one has to present col-header-keys or col-header-keys
     *  *col-data-keys*  -- which holds the data in the collection
     *  *srotable-col-names* -- DEPRECATED PROP. Please use the correct spelling. This prop will be removed in v4.0. Contains the columnnames which requires sorting feature needs to enable
     *  *sortable-col-names* -- Contains the columnnames which requires sorting feature needs to enable
     *  *col-click-events* -- Contains the Columnnames and click event functions to be enabled
     */
    constructColumns = () => {
        let columns = [];
        let that = this;
        if (
            this.props.hasOwnProperty('col-header-keys') ||
            this.props.hasOwnProperty('col-default-headers')
        ) {
            let colHeaderKeys = '';
            // Reading the Column Header keys
            if (this.props.hasOwnProperty('col-header-keys'))
                colHeaderKeys = this.props['col-header-keys'];

            // Reading the Column default header strings Either one has to present col-header-keys or col-header-keys
            if (this.props.hasOwnProperty('col-default-headers'))
                colHeaderKeys = this.props['col-default-headers'];

            // Reading the data keys to which holds the data in the collection
            let colDataKeys = this.props['col-data-keys'];

            // Reading the sortable columns names
            let sortableColNames = '';
            if (this.props.hasOwnProperty('srotable-col-names')) {
                sortableColNames = this.props['srotable-col-names'];
            }
            if (this.props.hasOwnProperty('sortable-col-names')) {
                sortableColNames = this.props['sortable-col-names'];
            }

            //Splitting the column header keys,column data keys and sortable columns names
            colHeaderKeys = colHeaderKeys.split(',');
            colDataKeys = colDataKeys.split(',');
            sortableColNames = sortableColNames.toString().split(',');

            _.forEach(colHeaderKeys, function(value, index) {
                let columnJSON = {};
                //&& that.state.wcmKeys[value.toLowerCase()] !== undefined
                columnJSON.text = that.props.hasOwnProperty('col-header-keys')
                    ? Util.getWcmValue(value.toLowerCase(), that)
                    : value;
                //Reading the custom HTML of the columns it should be in JSON format
                if (
                    that.props.hasOwnProperty('header-formatter') &&
                    _.get(that.props['header-formatter'], value) !== undefined
                ) {
                    columnJSON.headerFormatter = _.get(that.props['header-formatter'], value);
                }
                if (index < colDataKeys.length) {
                    columnJSON.dataField = colDataKeys[index];
                }
                if (
                    sortableColNames !== undefined &&
                    (sortableColNames.indexOf(value) !== -1 ||
                        sortableColNames.indexOf('all') !== -1)
                )
                    columnJSON.sort = true;
                //Reading the Events of the columns it should be in JSON format
                if (
                    that.props.hasOwnProperty('col-click-events') &&
                    _.get(that.props['col-click-events'], value) !== undefined
                ) {
                    columnJSON.classes = Constants.cursorPointerClass;
                    columnJSON.events = {
                        onClick: (e, column, columnIndex, row, rowIndex) => {
                            _.get(that.props['col-click-events'], value)(
                                e,
                                column,
                                columnIndex,
                                row,
                                rowIndex,
                            );
                        },
                    };
                }
                //Reading the Events of the heders it should be in JSON format
                if (
                    that.props.hasOwnProperty('hdr-click') &&
                    _.get(that.props['hdr-click'], value) !== undefined
                ) {
                    columnJSON.classes = Constants.cursorPointerClass;
                    columnJSON.headerEvents = {
                        onClick: (e, column, columnIndex) => {
                            _.get(that.props['hdr-click'], value)(e, column, columnIndex);
                        },
                    };
                }
                //Reading the custom HTML of the columns it should be in JSON format
                if (
                    that.props.hasOwnProperty('customContent') &&
                    _.get(that.props['customContent'], value) !== undefined
                ) {
                    columnJSON.formatter = _.get(that.props['customContent'], value);
                }
                //Reading the css classes of the columns it should be in JSON format
                if (
                    that.props.hasOwnProperty('columnClasses') &&
                    _.get(that.props['columnClasses'], value) !== undefined
                ) {
                    columnJSON.classes = _.get(that.props['columnClasses'], value);
                }
                if (
                    that.props.hasOwnProperty('col-widths') &&
                    _.get(that.props['col-widths'], value) !== undefined
                ) {
                    columnJSON.headerClasses = 'ux-width-' + _.get(that.props['col-widths'], value);
                }
                if (
                    that.props.hasOwnProperty('commonheaderClass') &&
                    that.props['commonheaderClass'] !== undefined
                ) {
                    if (columnJSON.headerClasses !== undefined) {
                        columnJSON.headerClasses += ' ' + that.props.commonheaderClass;
                    } else {
                        columnJSON.headerClasses = that.props.commonheaderClass;
                    }
                }
                //Reading Column footer details it should be in JSON format
                if (
                    that.props.hasOwnProperty('columnWithFooterTitles') &&
                    that.props['columnWithFooterTitles'] !== undefined
                ) {
                    if (_.get(that.props['columnWithFooterTitles'], value) !== undefined) {
                        columnJSON.footer =
                            Util.getWcmValue(
                                _.get(that.props['columnWithFooterTitles'], value).toLowerCase(),
                                that,
                            ) !== ''
                                ? Util.getWcmValue(
                                      _.get(
                                          that.props['columnWithFooterTitles'],
                                          value,
                                      ).toLowerCase(),
                                      that,
                                  )
                                : _.get(that.props['columnWithFooterTitles'], value);
                    } else {
                        columnJSON.footer = '';
                    }
                }

                //Reading the custom HTML of the columns footers in JSON format
                if (that.props.hasOwnProperty('columnWithFooterFormaters')) {
                    if (_.get(that.props['columnWithFooterFormaters'], value) !== undefined) {
                        columnJSON.footerFormatter = _.get(
                            that.props['columnWithFooterFormaters'],
                            value,
                        );
                    }
                    if (columnJSON.footer === undefined) {
                        columnJSON.footer = '';
                    }
                }
                //Reading the column span Attributes in JOSN format
                if (
                    that.props.hasOwnProperty('colSpanColumns') &&
                    _.get(that.props['colSpanColumns'], value) !== undefined
                ) {
                    if (typeof _.get(that.props['colSpanColumns'], value) === 'function') {
                        columnJSON.attrs = _.get(that.props['colSpanColumns'], value);
                    }
                    if (_.get(that.props['colSpanColumns'], value) === 'Merge') {
                        columnJSON.attrs = { Merge: 'true' };
                    }
                }
                columns.push(columnJSON);
            });
            // Reading the action columns, it should be in JSON Object
            let children = React.Children.toArray(this.props.children);
            _.forEach(children, (elem, index) => {
                if (elem !== undefined && elem.props['type'] === Constants.actionColumnName) {
                    let actionColumn = {};
                    actionColumn.dataField = 'action_' + index;
                    if (elem.props.hasOwnProperty('width'))
                        actionColumn.headerClasses = 'ux-width-' + elem.props.width;
                    actionColumn.isDummyField = true;
                    actionColumn.text = elem.props.hasOwnProperty('labelkey')
                        ? Util.getWcmValue(elem.props['labelkey'].toLowerCase(), that)
                        : elem.props['labelkey'];
                    if (
                        elem.props.hasOwnProperty('dynamic-content') &&
                        !_.isUndefined(elem.props['dynamic-content']) &&
                        _.isFunction(elem.props['dynamic-content'])
                    ) {
                        actionColumn.formatter = elem.props['dynamic-content'];
                    } else {
                        actionColumn.formatter = (cellContent, row) => {
                            return React.Children.map(elem.props.children, child =>
                                React.cloneElement(child, { row: row, cellConcetent: cellContent }),
                            );
                        };
                    }
                    columns.push(actionColumn);
                }
            });
        }
        return columns;
    };
    componentDidUpdate() {
        if (this.props.hasOwnProperty('colSpanColumns')) {
            document.querySelectorAll("[merge*='true']").forEach(item => {
                item.remove();
            });
        }
    }
    constructOptions = () => {
        let _this = this;
        if (this.props.striped === 'true') this.dataTableOptions.striped = true;
        if (this.props.hover === 'true') this.dataTableOptions.hover = true;
        if (this.props.id) this.dataTableOptions.id = this.props.id;
        if (this.props.className) this.dataTableOptions.classes = this.props.className;
        if (this.props.bordered === 'false') this.dataTableOptions.bordered = false;
        if (this.props.responsive === 'true')
            this.dataTableOptions.classes =
                this.dataTableOptions.classes + ' ' + Constants.data_table_rsponsive_class;
        if (this.props.keyfield) this.dataTableOptions.keyField = this.props.keyfield;
        if (this.props.wrapperclasses)
            this.dataTableOptions.wrapperClasses = this.props.wrapperclasses;
        if (this.props.hasOwnProperty('rowSelection')) {
            if (this.props.rowSelection === 'single') {
                this.dataTableOptions.selectRow = { clickToSelect: true, mode: 'radio' };
            } else if (this.props.rowSelection === 'multi') {
                this.dataTableOptions.selectRow = { clickToSelect: true, mode: 'checkbox' };
            } else {
                this.dataTableOptions.selectRow = {
                    clickToSelect: true,
                    mode: 'checkbox',
                    hideSelectColumn: true,
                };
            }
            if (
                this.props.hasOwnProperty('rowClick') &&
                typeof this.props.rowClick === 'function'
            ) {
                this.dataTableOptions.selectRow['onSelect'] = (row, isSelect, rowIndex, e) => {
                    _this.props.rowClick(row, isSelect, rowIndex, e);
                };
            }
            if (
                this.props.hasOwnProperty('onSelectAll') &&
                typeof this.props.rowClick === 'function'
            ) {
                this.dataTableOptions.selectRow['onSelectAll'] = (isSelect, row, e) => {
                    _this.props.onSelectAll(isSelect, row, e);
                };
            }
        }
        if (this.props.hasOwnProperty('renderOnExpand')) {
            this.dataTableOptions.expandRow = {
                expandColumnPosition: _this.props.expandColumnPosition
                    ? _this.props.expandColumnPosition
                    : 'left',
                showExpandColumn: _this.props.showExpandColumn
                    ? _this.props.showExpandColumn
                    : true,
                expandHeaderColumnRenderer: ({ isAnyExpands }) =>
                    isAnyExpands ? (
                        <UX type="label"> More Details</UX>
                    ) : (
                        <UX type="label"> More Details</UX>
                    ),
                expandColumnRenderer: ({ expanded, rowKey, expandable }) =>
                    expanded ? (
                        // <UX type="icon" symbol={_this.props.columnCollapseIcon}></UX>
                        <UX className="underln" type="hyperLink">
                            Hide
                        </UX>
                    ) : (
                        // <UX type="icon" symbol={_this.props.columnExpandIcon}></UX>
                        <UX className="underln" type="hyperLink">
                            View
                        </UX>
                    ),
                parentClassName: 'bar',
                expandByColumnOnly: true,
                renderer: (row, rowIndexe) => {
                    return _this.props.renderOnExpand(row, rowIndexe);
                },
                onlyOneExpanding: !_this.props.expandMultiple,
            };
            if (this.props.hasOwnProperty('onExpand'))
                this.dataTableOptions.expandRow['onExpand'] = (row, isExanded, rowIndex, e) => {
                    _this.props.onExpand(row, isExanded, rowIndex, e);
                };
            if (this.props.hasOwnProperty('onExpandAll'))
                this.dataTableOptions.expandRow['onExpandAll'] = (row, isExanded, rowIndex, e) => {
                    _this.props.onExpandAll(row, isExanded, rowIndex, e);
                };
        }
        if (this.props.pagination === 'default') {
            let paginationOptions = {};
            if (
                this.props.hasOwnProperty('pagination-size-per-page') &&
                !_.isEmpty(this.props['pagination-size-per-page'])
            )
                paginationOptions.sizePerPage = parseInt(this.props['pagination-size-per-page']);
            if (this.props['pagination-show-total'] === 'true') paginationOptions.showTotal = true;
            paginationOptions.withFirstAndLast =
                this.props.withFirstAndLast === 'true' ? true : false;
            if (this.props.lastPageText) {
                paginationOptions.lastPageText = this.props.lastPageText;
            }
            if (this.props.firstPageText) {
                paginationOptions.firstPageText = this.props.firstPageText;
            }
            if (this.props.prePagetext) {
                paginationOptions.prePagetext = this.props.prePagetext;
            }
            if (this.props.nextPageText) {
                paginationOptions.nextPageText = this.props.nextPageText;
            }

            this.dataTableOptions.pagination = paginationFactory(paginationOptions);
        }
    };

    getCustomTotalChild(paginationProps) {
        if (paginationProps.totalSize < 10 || isNaN(paginationProps.totalSize)) {
            return null;
        }
        let totalPages = Math.round(paginationProps.dataSize / paginationProps.sizePerPage);
        let totalItems = totalPages * paginationProps.sizePerPage;
        let rangeStart =
            paginationProps.page - 1 > 0
                ? (paginationProps.page - 1) * paginationProps.sizePerPage + 1
                : 1;
        let rangeEnd = rangeStart + paginationProps.sizePerPage - 1;
        return (
            <React.Fragment>
                <span className="ux-pagination-range">
                    {rangeStart}-{rangeEnd}
                </span>
                <span className="ux-pagination-total"> out of {totalItems}</span>
            </React.Fragment>
        );
    }

    getPageDropDown(paginationProps) {
        if (paginationProps.totalSize < 10 || isNaN(paginationProps.totalSize)) {
            return null;
        }
        return (
            <React.Fragment>
                <SizePerPageDropdownStandalone
                    {...paginationProps}
                    className="ux-pagination-size-dropdown"
                />
                <span>&emsp;items per page</span>
            </React.Fragment>
        );
    }
    /**
     * Following function will be used to construct the empty message when ever the collection supplied to this datatable component is empty
     */
    constructWcmValues = () => {
        if (this.props.hasOwnProperty('emptymsg-key'))
            this.dataTableOptions.noDataIndication =
                Util.getWcmValue(this.props['emptymsg-key'].toLowerCase(), this) !== undefined
                    ? Util.getWcmValue(this.props['emptymsg-key'].toLowerCase(), this)
                    : DefaultLabels.tableDataEmptyMessage;
    };
    renderComponent() {
        this.constructWcmValues();
        if (this.props.customsearch) {
            let MySearch = this.props.customsearch;
            return (
                <ToolkitProvider
                    keyField={this.props.keyfield}
                    data={
                        !_.isEmpty(this.props.getStateValue(this.props.datacollection))
                            ? this.props.getStateValue(this.props.datacollection)
                            : []
                    }
                    columns={this.constructColumns()}
                    search>
                    {props => (
                        <React.Fragment>
                            <MySearch {...props.searchProps} />
                            <BootstrapTable
                                bootstrap4
                                {...props.baseProps}
                                {...this.dataTableOptions}
                            />
                        </React.Fragment>
                    )}
                </ToolkitProvider>
            );
        } else if (this.props.hasOwnProperty('pagination') && this.props.pagination === 'custom') {
            return (
                <PaginationProvider
                    pagination={paginationFactory({
                        custom: true,
                        totalSize: !_.isEmpty(this.props.getStateValue(this.props.datacollection))
                            ? this.props.getStateValue(this.props.datacollection).length
                            : 0,
                        sizePerPage:
                            this.props.hasOwnProperty('pagination-size-per-page') &&
                            !_.isEmpty(this.props['pagination-size-per-page'])
                                ? parseInt(this.props['pagination-size-per-page'])
                                : 15,
                    })}>
                    {({ paginationProps, paginationTableProps }) => (
                        <div>
                            <div
                                className={
                                    this.props['paginationJustify']
                                        ? `ux-row ux-custom-pagination ux-pagination-justify-${this.props['paginationJustify']}`
                                        : `ux-row ux-custom-pagination`
                                }>
                                <div className="col-md-12 ux-pagination-column">
                                    {paginationProps.totalSize > 0 && (
                                        <p className="mr-2">
                                            {paginationProps.page * paginationProps.sizePerPage -
                                                paginationProps.sizePerPage +
                                                1}{' '}
                                            -{' '}
                                            {paginationProps.page * paginationProps.sizePerPage >
                                            paginationProps.dataSize
                                                ? paginationProps.dataSize
                                                : paginationProps.page *
                                                  paginationProps.sizePerPage}{' '}
                                            of {paginationProps.totalSize} results
                                        </p>
                                    )}
                                    <PaginationListStandalone {...paginationProps} />
                                </div>
                            </div>
                            <BootstrapTable
                                ref={n => (this.table = n)}
                                bootstrap4
                                data={
                                    !_.isEmpty(this.props.getStateValue(this.props.datacollection))
                                        ? this.props.getStateValue(this.props.datacollection)
                                        : []
                                }
                                columns={this.constructColumns()}
                                {...paginationTableProps}
                                {...this.dataTableOptions}
                            />
                            <div
                                className={
                                    this.props['paginationJustify']
                                        ? `ux-row ux-custom-pagination ux-pagination-justify-${this.props['paginationJustify']}`
                                        : `ux-row ux-custom-pagination`
                                }>
                                <div className="col-md-12 ux-pagination-column">
                                    <PaginationListStandalone {...paginationProps} />
                                </div>
                            </div>
                        </div>
                    )}
                </PaginationProvider>
            );
        } else if (this.props.hasOwnProperty('pagination') && this.props.pagination === 'remote') {
            return (
                <PaginationProvider
                    pagination={paginationFactory({
                        custom: true,
                        page: this.props.page,
                        totalSize: this.props.totalSize ? this.props.totalSize : 0,
                        sizePerPage:
                            this.props.hasOwnProperty('pagination-size-per-page') &&
                            !_.isEmpty(this.props['pagination-size-per-page'])
                                ? parseInt(this.props['pagination-size-per-page'])
                                : 15,
                    })}>
                    {({ paginationProps, paginationTableProps }) => (
                        <div>
                            <div
                                className={
                                    this.props['paginationJustify']
                                        ? `ux-row ux-custom-pagination ux-pagination-justify-${this.props['paginationJustify']}`
                                        : `ux-row ux-custom-pagination`
                                }>
                                <div className="col-md-12 ux-pagination-column">
                                    {paginationProps.totalSize > 0 && (
                                        <p className="mr-2">
                                            {paginationProps.page * paginationProps.sizePerPage -
                                                paginationProps.sizePerPage +
                                                1}{' '}
                                            -{' '}
                                            {paginationProps.page * paginationProps.sizePerPage >
                                            paginationProps.dataSize
                                                ? paginationProps.dataSize
                                                : paginationProps.page *
                                                  paginationProps.sizePerPage}{' '}
                                            of {paginationProps.totalSize} results
                                        </p>
                                    )}
                                    <PaginationListStandalone {...paginationProps} />
                                </div>
                            </div>
                            <BootstrapTable
                                bootstrap4
                                remote
                                data={
                                    !_.isEmpty(this.props.getStateValue(this.props.datacollection))
                                        ? this.props.getStateValue(this.props.datacollection)
                                        : []
                                }
                                columns={this.constructColumns()}
                                {...paginationTableProps}
                                {...this.dataTableOptions}
                                onTableChange={this.props.contentLoader}
                            />
                            <div
                                className={
                                    this.props['paginationJustify']
                                        ? `ux-row ux-custom-pagination ux-pagination-justify-${this.props['paginationJustify']}`
                                        : `ux-row ux-custom-pagination`
                                }>
                                <div className="col-md-12 ux-pagination-column">
                                    <PaginationListStandalone {...paginationProps} />
                                </div>
                            </div>
                        </div>
                    )}
                </PaginationProvider>
            );
        } else if (this.props.hasOwnProperty('pagination') && this.props.pagination === 'nextgen') {
            let nextgenPaginationOptions = paginationFactory({
                custom: true,
                totalSize:
                    !_.isEmpty(this.props.getStateValue(this.props.datacollection)) &&
                    this.props.getStateValue(this.props.datacollection).length > 10
                        ? this.props.getStateValue(this.props.datacollection).length
                        : 0,
                ...this.dataTableOptions.pagination,
            });
            return (
                <PaginationProvider pagination={nextgenPaginationOptions}>
                    {({ paginationProps, paginationTableProps }) => (
                        <div className="ux-datatable">
                            <BootstrapTable
                                bootstrap4
                                data={
                                    !_.isEmpty(this.props.getStateValue(this.props.datacollection))
                                        ? this.props.getStateValue(this.props.datacollection)
                                        : []
                                }
                                columns={this.constructColumns()}
                                className="ux-datatable"
                                {...paginationTableProps}
                                {...this.dataTableOptions}></BootstrapTable>
                            <div className="ux-pagination-default">
                                <div className="ux-pagination-total">
                                    {this.getCustomTotalChild(paginationProps)}
                                </div>
                                <div className="ux-pagination-default-pagesize">
                                    {this.getPageDropDown(paginationProps)}
                                </div>

                                <PaginationListStandalone {...paginationProps} />
                            </div>
                        </div>
                    )}
                </PaginationProvider>
            );
        } else {
            return (
                <BootstrapTable
                    bootstrap4
                    data={
                        !_.isEmpty(this.props.getStateValue(this.props.datacollection))
                            ? this.props.getStateValue(this.props.datacollection)
                            : []
                    }
                    columns={this.constructColumns()}
                    {...this.dataTableOptions}></BootstrapTable>
            );
        }
    }
}
/** DataTable Component */
const CustomeDataTable = props => <DataTableWrap {...props} />;

CustomeDataTable.propTypes = {
    /** Determines if the component is hidden or shown */
    showIf: PropTypes.bool,
    /** Applies color to every other row of the table */
    striped: PropTypes.string,
    /** Applies borders to a table and table cells. */
    bordered: PropTypes.string,
    /** Applies the provided classes on the column headers commonly */
    commonheaderClass: PropTypes.string,
    /** Holds the custom search html  */
    customsearch: PropTypes.func,
    /** Applies a grey background color on mouseover event to table rows */
    hover: PropTypes.string,
    /** Customized classes applied to the table element */
    class: PropTypes.string,
    /** Applies the values of keys from the Content Manager to Column Headers */
    'col-header-keys': PropTypes.string,
    /** Data keys used to get the values from collection object */
    'col-data-keys': PropTypes.string,
    /** rowClick will be used to execute a function */
    rowClick: PropTypes.func,
    /** onSelectAll will be used to execute a function on select of all rows */
    onSelectAll: PropTypes.func,
    /** Customized classes on the outer element which wrap the table element */
    wraperclasses: PropTypes.string,
    /** Customized classes on the columns */
    columnClasses: PropTypes.object,
    /** Controls if a pagination panel is rendered below the table */
    pagination: PropTypes.oneOf(['default', 'custom', 'remote']),
    /** Controls the number rows to be displayed per page */
    'pagination-size-per-page': PropTypes.string,
    /** Controls if the total number of records in the table is displayed. Ex: Showing rows 1 to 5 of 16 */
    'pagination-show-total': PropTypes.string,
    /* Pagination will be visible only if list of pages is greater than one */
    'pagination-hidePageListOnlyOnePage': PropTypes.string,
    /** DEPRECATED PROP. Please use the correct spelling. This prop will be removed in the v4.0 release. Option allowing columns to be sorted by column name. */
    'srotable-col-names': PropTypes.string,
    /** Option allowing columns to be sorted by column name. Must enter the matching col-default-headers entry as string. */
    'sortable-col-names': PropTypes.string,
    /** In pagination is remote then it totalSize will be used to calculate the pages */
    totalSize: PropTypes.number,
    /** Holds the list of columns as keys and click events as values
     * **Example**
     *   In the below example *MyApplications_table_submittted* is an column name and *this.firstColumnClick* is an event handler
     * ```js
     * "col-click-events={{"MyApplications_table_submittted":this.firstColumnClick}}"
     * ```
     */
    'col-click-events': PropTypes.object,
    /** Holds the list of columns as keys and click events as values
     * **Example**
     *   In the below example *MyApplications_table_submittted* is an column name and *this.firstColumnClick* is an event handler
     * ```js
     * "hdr-click={{"MyApplications_table_submittted":this.firstColumnClick}}"
     * ``
     */
    'hdr-click': PropTypes.object,
    /** Holds the list of columns as keys and customcontet events as values
     * **Example**
     *   In the below example *MyApplications_table_submittted* is an column name and *this.customContent* is an event handler
     * ```js
     * "customContent={{"MyApplications_table_submittted":this.customContent}}"
     * ```
     */

    customContent: PropTypes.object,

    /** Holds the list of columns as keys and customcontet events as values
     * **Example**
     *   In the below example *MyApplications_table_submittted* is an column header and *this.customContent* is an event handler
     * ```js
     * "header-formatter={{"MyApplications_table_submittted":this.customContent}}"
     * ```
     */

    'header-formatter': PropTypes.object,
    /** Holds the list of columns as keys and footer content as values
     * **Example**
     *   In the below example *MyApplications_table_submittted* is an column header and *Subtotal* is an footer content
     * ```js
     * "columnWithFooterTitles={{"MyApplications_table_submittted":"Subtotal"}}"
     * ```
     */

    columnWithFooterTitles: PropTypes.object,

    /** Holds the list of columns as keys and customcontet events as values
     * **Example**
     *   In the below example *MyApplications_table_submittted* is an column header and *this.customContent* is an event handler where it should
     *   retruns the custom content
     * ```js
     * "columnWithFooterFormaters={{"MyApplications_table_submittted":this.customContent}}"
     * ```
     */

    columnWithFooterFormaters: PropTypes.object,

    /** Holds the list of columns as keys and colSpan events as values
     * **Example**
     *   In the below example *MyApplications_table_submittted* is an column header and *this.colspan* is an event handler where it should
     *   retruns attribute {colSpan:<number>}
     * ```js
     * "columnWithFooterFormaters={{"MyApplications_table_submittted":this.colspan}}"
     * ```
     */
    colSpanColumns: PropTypes.object,

    /** Holds the widths of different columns. Column headers are held as keys and widths as numerical values (valid widths: 1-12)
     * **Example**
     * In the below example *MyApplications_table_submittted* is a column header and 2 is a width value
     * ```js
     *  col-widths={{"MyApplications_table_submittted":2}}
     * ```
     */
    'col-widths': PropTypes.object,
    /** Controls if the data table is responsive */
    responsive: PropTypes.string,
    /** Callback function to load the data in pagination if 'remote' */
    contentLoader: PropTypes.func,
    /** Controls the horizontal justification of pagination */
    paginationJustify: PropTypes.oneOf(['left', 'center', 'right']),
    /** Enables the row seletion capability */
    rowSelection: PropTypes.oneOf(['single', 'multi', 'none']),
    /** Apply 'go to first page' and 'go to last page' buttons when using default pagination */
    withFirstAndLast: PropTypes.oneOf(['true', 'false']),
    /** Set pagination 'go to last page' button text */
    lastPageText: PropTypes.string,
    /** Set pagination 'go to first page' button text */
    firstPageText: PropTypes.string,
    /** Set pagination 'next page' button text */
    nextPageText: PropTypes.string,
    /** Set pagination 'previous page' button text */
    prePageText: PropTypes.string,
    /** renderOnExpand function eturns jsx code  */
    renderOnExpand: PropTypes.func,
    /** has boolean valut for expand or collapse section */
    expandMultiple: PropTypes.bool,
    /** determines position of the expand collapse column */
    expandColumnPosition: PropTypes.oneOf(['left', 'right']),
    /** functionn gets called onExpand of a rwow  */
    onExpand: PropTypes.func,
    /** functionn gets called onExpand of all rwow  */
    onExpandAll: PropTypes.func,
    /** if the expand collapse column needs to be shown */
    showExpandColumn: PropTypes.bool,
    /** header collapse icon */
    headerCollapseIcon: PropTypes.string,
    /** header expand icon*/
    headerExpandIcon: PropTypes.string,
    /** column expand icon*/
    columnExpandIcon: PropTypes.string,
    /** column collapse icon*/
    columnCollapseIcon: PropTypes.string,
};
CustomeDataTable.defaultProps = {
    striped: 'false',
    hover: 'false',
    'pagination-size-per-page': '10',
    'pagination-show-total': 'false',
    'pagination-hidePageListOnlyOnePage': 'false',
    rowSelection: 'none',
    expandMultiple: 'false',
    headerCollapseIcon: 'fa fa-angle-double-up',
    headerExpandIcon: 'fa fa-angle-double-down',
    columnExpandIcon: 'fa fa-angle-down',
    columnCollapseIcon: 'fa fa-angle-up',
};

export default CustomeDataTable;
