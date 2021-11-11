import React from 'react';
import TestRenderer from 'react-test-renderer';
import { mount } from 'enzyme';
import { AppContext } from '@d-lift/core';
import Home from 'Pages/Public/HomeNew/HomeNew';

describe('Home Page Test', () => {
    test('Renders Page Mount and Unmount Without Crashing', () => {
        const HomePage = TestRenderer.create(<Home />);
        // expect(HomePage).toMatchSnapshot();
        HomePage.unmount();
    });

    test('Home Page Model Initialization', () => {
        const HomePage = TestRenderer.create(<Home />);
        expect(typeof HomePage.getInstance().initializeModel).toBe('function');
        expect(HomePage.getInstance().state.model.home).toEqual('Home Page');
        HomePage.unmount();
    });
    test('Home Page Model Initialization using enzyme', () => {
        const TodoListPage = mount(<Home />);
        expect(AppContext.model.getValue('home')).toEqual('Home Page');
        expect(TodoListPage.state().model.home).toEqual('Home Page');
        TodoListPage.unmount();
    });
});
