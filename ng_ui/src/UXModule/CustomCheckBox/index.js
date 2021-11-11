import { Lift } from '@d-lift/core';
import CustomCheckBox from './CustomCheckBox';

const CustomCheckBoxComponent = CustomCheckBox;
const UXExtension = {
    as: uxtype => {
        CustomCheckBoxComponent.ComponentType = uxtype;
        return UXExtension;
    },
    initialize: () => Lift.Extensions.registerUXLib(CustomCheckBoxComponent),
};

export default UXExtension;
