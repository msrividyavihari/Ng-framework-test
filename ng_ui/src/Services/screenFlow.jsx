import { ServiceConfig, GET } from '@d-lift/core';

@ServiceConfig({
    baseHrefKey: 'serviceEndpoint',
    secure: true,
    timeout: 3000,
})
class ScreenFLow {
    @GET({
        path: '/Screenflow/LeftNAvFlow/AREGA.json',
        secure: false,
    })
    async getScreenFlowAREGA(params, { success, data, status, headers }) {
        return data;
    }

    @GET({
        path: '/Screenflow/LeftNAvFlow/ARSEA.json',
        secure: false,
    })
    async getScreenFlowARSEA(params, { success, data, status, headers }) {
        return data;
    }

    @GET({
        path: '/Screenflow/LeftNAvFlow/COVPC.json',
        secure: false,
    })
    async getScreenFlowCOVPC(params, { success, data, status, headers }) {
        return data;
    }

    // @GET({
    //     path: '/Screenflow/LeftNAvFlow/LeftTopLevelNavDetails.json',
    //     secure: false,
    // })
    // async getScreenFlowTop(params, { success, data, status, headers }) {
    //     return data;
    // }

    @GET({
        path: '/Screenflow/testFlow.json',
        secure: false,
    })
    async getTestScreen(params, { success, data, status, headers }) {
        return data;
    }
}

const Service = new ScreenFLow();
export default Service;
