jest.mock('@/PublicRouteConfig.json', () => ({ test: 'test' }), { virtual: true });
jest.mock('@/PrivateRouteConfig.json', () => ({ test: 'test' }), { virtual: true });

global.console = {
    log: console.log, // console.log are ignored in tests
    error: console.error, //console.error are ignored in tests
    warn: jest.fn(), //console.warn are ignored in tests
    info: console.info,
    debug: console.debug,
};

jest.mock(
    '@/SiteConfig.json',
    () => ({
        AppType: 'Default',
        PageTitle: 'LIFT App',
        PageTitleStrategy: 'append',
        PageMetaTags: {
            description: 'NextGen LIFT',
            'format-detection': 'telephone=yes',
        },
        ContextRoot: '/',
        ContentCache: true,
        AuthenticationType: 'Default',
        RefrenceTableCache: true,
        SessionTimeout: {
            enabled: true,
            timerResetEvents: [],
            warning: {
                enabled: true,
                displayInSeconds: 10,
            },
            timeoutInSeconds: 30,
        },
    }),
    { virtual: true },
);

jest.mock('react-modal');
jest.dontMock('validatorjs');
jest.dontMock('@d-lift/core');
jest.dontMock('@d-lift/uxcomponents');

beforeEach(() => {
    // console.log("Running before each...");
});
beforeAll(() => {
    // console.log("Running before all...");
});
