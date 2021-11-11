import React from 'react';
// import TestRenderer from 'react-test-renderer';
import {mount} from 'enzyme';
import { MemoryRouter as Router } from 'react-router-dom';

const mountWithRouter = node => mount(<Router>{node}</Router>);
    
export {
    mountWithRouter
};