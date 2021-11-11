import { AppContext, Util } from '@d-lift/core';
class DashboardService {

    dashboardStatusService = async noticestatusData => {
        let dashboardURL = AppContext.config.coDashboard;
        let dashboardResponse = {};
        const inputJSON = {
            
                agency: noticestatusData.agency,
                fromDate: noticestatusData.startDate,
                toDate: noticestatusData.endDate,
                channel: noticestatusData.channel,
                status:noticestatusData.status
            
        };
        try {
            let response = await Util.HTTP.post(dashboardURL, inputJSON);
            console.log(response);
            if (response.data.message.code === 100) {
                dashboardResponse = {
                    foundRecords: response.data.data,
                    success: true,
                };
            } else {
                dashboardResponse.success = false;
            }
        } catch (ex) {
            //On HTTP Status of service other than 200
            dashboardResponse.success = false;
        }
        console.log('Fetch latest Notice Status Service Called');
        return dashboardResponse;
    };

}

const dashboardService = new DashboardService();
export default dashboardService;