import { AppContext } from '@d-lift/core';

export const selAssoCase = id => {
    if (id && id !== '0') {
        AppContext.model.setValue('caseAssociate.selected', true);
        if (
            document.getElementsByName(id + '_B') &&
            document.getElementsByName(id + '_B').length > 0
        ) {
            document.getElementsByName(id + '_B')[0].style.display = 'none';
        }
        if (
            document.getElementsByName(id + '_G') &&
            document.getElementsByName(id + '_G').length > 0
        ) {
            document.getElementsByName(id + '_G')[0].style.display = 'block';
        }
        if (document.getElementById(id + '_B')) {
            document.getElementById(
                id + '_B',
            ).parentElement.parentElement.parentElement.style.borderStyle = 'solid';
            document.getElementById(
                id + '_B',
            ).parentElement.parentElement.parentElement.style.borderColor = 'rgb(20, 241, 187)';
        }
    }
};

export const deselAssoCase = id => {
    if (!document.getElementById(id + '_G').disabled) {
        if (AppContext.model.getValue('caseAssociate.id') === id) {
            AppContext.model.setValue('caseAssociate.selected', false);
        }
        if (null !== document.getElementById(id + '_B')) {
            document.getElementById(
                id + '_B',
            ).parentElement.parentElement.parentElement.style.borderStyle = 'none';
        }

        /*  document.getElementById(
        id + '_B',
    ).parentElement.parentElement.parentElement.style.borderColor = 'rgb(20, 241, 187)'; */
        document.getElementsByName(id + '_G')[0].style.display = 'none';
        document.getElementsByName(id + '_B')[0].style.display = 'block';
    }
};

export const disableCaseAssociation = () => {
    var nodes = document
        .querySelector('#case_association')
        .querySelector('.react-bootstrap-table')
        .getElementsByTagName('button');
    console.log(nodes);
    for (var i = 0; i < nodes.length; i++) {
        nodes[i].disabled = 'true';
    }
};

export const CommonUtils = {
    selAssoCase: selAssoCase,
    deselAssoCase: deselAssoCase,
    disableCaseAssociation: disableCaseAssociation,
};
