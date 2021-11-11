import axios from 'axios';
import { AppContext, Util } from '@d-lift/core';

const defaultOptions = {
    headers: {
        Accept: '*/*',
        'Cache-Control': 'no-cache',
        Pragma: 'no-cache',
    },
};

const basicAxiosInterceptor = axios.create(defaultOptions);

// Add a request interceptor
basicAxiosInterceptor.interceptors.request.use(
    function(config) {
        // Do something before request is sent header can be sent
        const jwtToken = Util.getSessionData('jwtToken');

        config.headers.Authorization = jwtToken ? `Bearer ` + jwtToken : '';

        return config;
    },
    function(error) {
        // Do something with request error
        return Promise.reject(error);
    },
);

// Add a response interceptor
basicAxiosInterceptor.interceptors.response.use(
    function(response) {
        // Do something with response data
        // console.log('Clearing the error notifications');
        AppContext.notification.clearAll();

        return response;
    },
    function(error) {
        // Do something with response error
        console.log(error);
        console.log(error.response);
        // console.log(error.response.data.message.show);
        if (
            error &&
            error.response &&
            error.response.data.message &&
            error.response.data.message.show === false
        ) {
            AppContext.notification.error(error.response.data.data);
            window.scrollTo(0, 0);
        }

        return Promise.reject(error);
    },
);

export default basicAxiosInterceptor;
