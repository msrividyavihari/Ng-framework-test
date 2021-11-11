import { Lift } from '@d-lift/core';
import CustomeDataTable from './CustomeDataTable';

const CustomeDataTableComponent = CustomeDataTable;
const UXExtension = {
    as: uxtype => {
        CustomeDataTableComponent.ComponentType = uxtype;
        return UXExtension;
    },
    initialize: () => Lift.Extensions.registerUXLib(CustomeDataTableComponent),
};

export default UXExtension;
