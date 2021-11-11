import { Lift } from '@d-lift/core';
import AuthRep from './AuthRep';

const AuthRepComponent = AuthRep;
const UXExtension = {
    as: uxtype => {
        AuthRep.ComponentType = uxtype;
        return UXExtension;
    },
    initialize: () => Lift.Extensions.registerUXLib(AuthRepComponent),
};

export default UXExtension;
