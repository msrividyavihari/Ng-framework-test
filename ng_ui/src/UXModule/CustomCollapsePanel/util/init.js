export function componentInit(fn) {
    try {
        fn();
    } catch (e) {
        if (process.env.NODE_ENV !== 'test') {
            console.error(`Lift Initialization Error: ${e}`);
        }
    }
}
