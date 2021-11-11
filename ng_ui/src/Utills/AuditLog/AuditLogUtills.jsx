import { AppContext } from '@d-lift/core';

let auditLogTransSactions = [];
export const setAuditLogTransactions = auditAction => {
    auditLogTransSactions = [
        ...auditLogTransSactions,
        {
            pageId: AppContext.pagedetails.getPageId(),
            pageName: AppContext.pagedetails.getPageName(),
            action: auditAction,
            auditDate: new Date(),
        },
    ];
};

const sendTransactions = () => {
    // console.log(
    //     '%c USER AUDIT SENT!',
    //     'font-weight: bold; font-size: 50px;color: red; text-shadow: 3px 3px 0 rgb(217,31,38) , 6px 6px 0 rgb(226,91,14) , 9px 9px 0 rgb(245,221,8) , 12px 12px 0 rgb(5,148,68) , 15px 15px 0 rgb(2,135,206) , 18px 18px 0 rgb(4,77,145) , 21px 21px 0 rgb(42,21,113)',
    // );
    var style = 'color: tomato; background:#eee; -webkit-text-stroke: 1px black; font-size:30px;';
    console.log('%cAudit Log Sent', style);
    console.log({
        auditLogTransSactions,
        user: AppContext.security.getProfile(),
    });
    auditLogTransSactions = [];
};
export const sendAuditTransactionAtLimit = limiter => {
    setInterval(() => {
        sendTransactions();
    }, limiter);
    window.onbeforeunload = confirmExit;

    function confirmExit() {
        sendTransactions();
    }
};
