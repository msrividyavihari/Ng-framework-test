import { AppContext, Util } from '@d-lift/core';
class Login {
    loginService = async (username, password) => {
        let loginUrl = AppContext.config.loginservice;
        let loginResponse = {};

        try {
            let response = await Util.HTTP.post(loginUrl, {
                userName: username,
                password: password,
            });

            // let response = {
            //     "Data": {
            //         "userProfile": {
            //             "firstName": "Abdelrahman",
            //             "midname": null,
            //             "lastName": "Elbakry",
            //             "email": "abelbakry@deloitte.com"
            //         },
            //         "status": "Success"
            //     }
            // }

            if (response.data.Data.status === 'Success') {
                Util.setSessionData('userCounty', response.data.Data.userProfile.county);
                Util.setSessionData('jwtToken', response.headers.jwt);
                Util.setSessionData('isUserLoggedIn', true);
                loginResponse = {
                    userprofile: { ...response.data.Data.userprofile },
                    // XAuthToken: response.headers.XAuthToken,
                    // AutherizationToken: response.headers.AutherizationToken,
                    // SecurityHeaders: response.SecurityHeaders,
                    success: true,
                };
            } else {
                Util.setSessionData('isUserLoggedIn', false);
                loginResponse.success = false;
            }
        } catch (ex) {
            Util.setSessionData('isUserLoggedIn', false);
            //On HTTP Status of service other than 200
            loginResponse.success = false;
            // loginResponse.message = ex.response.data; //Copy Error message if required
            // loginResponse.status = ex.response.status; //Copy Status code (404, 500, ...)
        }
        console.log('Login Service Called');
        return loginResponse;
    };
}

const login = new Login();
export default login;
