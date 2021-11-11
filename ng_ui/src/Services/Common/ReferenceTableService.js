import { ServiceConfig, GET } from '@d-lift/core';

@ServiceConfig({
    baseHrefKey: 'refrenceTableEndpoint',
    secure: true,
    timeout: 3000,
})
class ReferenceTableService {
    @GET({
        path: '/',
        params: 'tableList',
    })
    async getReferenceTableData(requestBody, { success, data, status, headers }) {
        return data;
    }
}

const refrenceTableService = new ReferenceTableService();
export default refrenceTableService;
