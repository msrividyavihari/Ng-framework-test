import { Lift } from '@d-lift/core';
import CustomSelectableCards from './CustomSelectableCards';

const CustomSelectableCardsComponent = CustomSelectableCards;
const UXExtension = {
    as: uxtype => {
        CustomSelectableCardsComponent.ComponentType = uxtype;
        return UXExtension;
    },
    initialize: () => Lift.Extensions.registerUXLib(CustomSelectableCardsComponent),
};

export default UXExtension;
