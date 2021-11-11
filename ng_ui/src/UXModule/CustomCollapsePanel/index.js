import { Lift } from '@d-lift/core';
import CustomCollapsePanel from './CustomCollapsePanel';

const CustomCollapsePanelComponent = CustomCollapsePanel;
const UXExtension = {
    as: uxtype => {
        CustomCollapsePanelComponent.ComponentType = uxtype;
        return UXExtension;
    },
    initialize: () => Lift.Extensions.registerUXLib(CustomCollapsePanelComponent),
};

export default UXExtension;
