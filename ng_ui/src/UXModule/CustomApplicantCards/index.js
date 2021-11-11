import { Lift } from '@d-lift/core';
import CustomApplicantCards from './CustomApplicantCards';

const CustomApplicantCardsComponent = CustomApplicantCards;
const UXExtension = {
    as: uxtype => {
        CustomApplicantCardsComponent.ComponentType = uxtype;
        return UXExtension;
    },
    initialize: () => Lift.Extensions.registerUXLib(CustomApplicantCardsComponent),
};

export default UXExtension;
