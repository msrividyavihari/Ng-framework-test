import Login from '@/Services/Account/Login';
import { Navigate, Util } from '@d-lift/core';

export default class SecurityController {
    /**
     * This method is called via Security Context for Logout Functionality
     * @returns {Promise}
     */
    logout = async () => {
        /** Application can call any Logout URL to perform logout on application server
         * and event use Navigate.redirect to load different URL like Application Home Page
         */
        Util.clearSessionData('jwtToken');
        Util.clearSessionData('isUserLoggedIn');
        Navigate.to('/');
    };

    /**
     * This method is called via Security Context for Login Functionality
     *
     * @param {String} username
     * @param {String} password
     * @returns {Promise}
     */
    login = async (username, password) => {
        let response = await Login.loginService(username, password);
        return response;
    };
}
