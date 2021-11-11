declare module "*.rt" {
    const content:Function;
    export default content;
}

declare module "*.json" {
    const value: any;
    export default value;
}

declare module "*.json5" {
    const value: any;
    export default value;
}


// declare module "@lift/core" {
//     /**
//      * UXPage extends React Component
//      */
//     declare class UXPage extends React.Component<P, S, SS> {
//         initializeModel(): JSON;
//         onPageLoad(): void;
//         pageDidMount(): void;
//         getModelValue(s:String): any;
//         setModelValue(S:String, V: {});
//         validateAndUpdateModel(S: String): Boolean;
//         updateModel(S: {}): Boolean;
//         goBack(): void;
//     }
//     export {UXPage};
    
//     type PageConfigParam = {
//         Path: String | Array<String>,
//         ContextRoot: String,
//         Description: String,
//         PageName: String,
//         Template: any
//     }
//     declare function PageConfig<T extends UXPage>(P: PageConfigParam): UXPage;

//     export {PageConfig};

//     interface Lift {
//         setRouterFile(RouteConfigJson: {}, SecureRouteConfigJson: {}): void;
//         AppConfig: {};
//     }
//     export {Lift}       
// }