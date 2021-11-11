import { Lift } from '@d-lift/core';
import ContactInfo from './ContactInfo';

const ContactInfoComponent = ContactInfo;
const UXExtension = {
    as: uxtype => {
        ContactInfoComponent.ComponentType = uxtype;
        return UXExtension;
    },
    initialize: () => Lift.Extensions.registerUXLib(ContactInfoComponent),
};

export default UXExtension;
