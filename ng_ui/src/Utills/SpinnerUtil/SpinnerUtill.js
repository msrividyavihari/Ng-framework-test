import { Lift } from '@d-lift/core';

class SpinnerUtil {
    show = () => {
        Lift.spinner.show();
    };

    hide = () => {
        Lift.spinner.hide();
    };
}
const Spinner = new SpinnerUtil();
export default Spinner;
