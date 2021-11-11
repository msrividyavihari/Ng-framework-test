import { Lift } from '@d-lift/core';
import { AppContext } from '@d-lift/core';

let Validator = Lift.Validator;
Validator.register('numericCheck2', function(value, requirement) {
    var pattern = /^[0-9]+$/;
    return pattern.test(value);
    // custom validation logic, return true or false
});
Validator.register('alphaCheck', function(value, requirement) {
    var pattern = /^[A-Za-z-' ]+$/;
    return pattern.test(value);
});
Validator.register('alphaNumericCheck', function(value, requirement) {
    var pattern = /^[A-Za-z0-9-' ]+$/;
    return pattern.test(value);
});
Validator.register('alphaNumericCheck2', function(value, requirement) {
    var pattern = /^[A-Za-z0-9]+$/;
    return pattern.test(value);
});
Validator.register('alphaNumericCheckWithoutSpace', function(value, requirement) {
    var pattern = /^[A-Za-z0-9-']+$/;
    return pattern.test(value);
});

Validator.register('nonNegetive', function(value, requirement) {
    return value >= 0;
});

Validator.register('zipCodeCheck', function(value, requirement) {
    var pattern = /^\d{5}$/;
    return pattern.test(value);
});

Validator.register('addressCheck', function(value, requirement) {
    var valFlag = true;
    var regex = /^[0-9]$/;
    var numCnt = 0;
    for (var i = 0, len = value.length; i < len; i++) {
        var substr = value.substring(i, i + 1);

        if (substr.match(regex)) {
            numCnt++;
            if (numCnt >= 9) {
                valFlag = false;
            }
        } else {
            numCnt = 0;
        }
    }
    return valFlag;
});

Validator.register('splCharCheck', function(value, requirement) {
    var pattern = /^[a-zA-Z0-9- ]+$/g;
    return pattern.test(value);
});

Validator.register('cityCheck', function(value, requirement) {
    var pattern = /[A-Z]+$/i;
    return pattern.test(value);
});

Validator.register('zipCheck', function(value, requirement) {
    var pattern = /(\d{5})/;
    return pattern.test(value);
});

Validator.register('phoneCheck', function(value, requirement) {
    var pattern = /(\d{10})/;
    return pattern.test(value);
});

Validator.register('emailCheck', function(value, requirement) {
    var pattern = /\S+@\S+\.\S+/;
    return pattern.test(value);
});

Validator.register('validateRecievedDate', function(value, requirement) {
    let maxDate = new Date('2099-12-31');
    let selectedDate = new Date(value);
    let minDate = new Date('1990-01-01');
    if (selectedDate >= maxDate) {
        return false;
    }

    if (selectedDate <= minDate) {
        return false;
    }
    return true;
});

Validator.register('validateSSN', function(value, requirement) {
    let regexp = /^(?!(000|666|9))\d{3}-?(?!(00))\d{2}-?(?!(0000))\d{4}$/;
    if (regexp.test(value)) {
        return true;
    } else {
        return false;
    }
});

Validator.register('fieldCheck', function(value, requirement) {
    return AppContext.model.getValue('ar.pc.search.firstName') === undefined ? false : true;
});
// model="appRegNew.ArApplicationForAid.dateTimeRegistered"

Validator.register('recivedAndregDiffDate', function(value, requirement) {
    let selectedDate = new Date(value);

    let regDate = new Date(
        AppContext.model
            .getValue('appRegNew.ArApplicationForAid.dateTimeRegistered')
            ?.split(' ')[0],
    );
    let maxDate = new Date('2099-12-31');
    regDate = regDate.setHours(selectedDate.getHours());
    regDate = new Date(regDate).setMinutes(selectedDate.getMinutes());

    regDate = new Date(regDate);
    if (selectedDate > regDate && selectedDate < maxDate) {
        return false;
    }

    return true;
});
