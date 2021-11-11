import React from 'react';
import { UX } from '@d-lift/uxcomponents';
import PropTypes from 'prop-types';

export const NPCButtons = props => {
    return (
        <UX type="section" body-class="p-0 m-0">
            <UX
                type="buttonGroup"
                groupAlign="right"
                className="align-self-center container-md mb-4">
                <UX
                    key="prev"
                    id={props.previd}
                    type={props.prevtype}
                    mode={props.prevmode}
                    size={props.prevsize}
                    inline={props.previnline}
                    click={props.prevclick}
                    blur={props.prevblue}
                    name={props.prevname}
                    buttonType={props.prevbuttonType}
                    align={props.prevalign}
                    iconClass={props.previconClass}
                    labelKey={props.prevlabelKey}
                    arialabel={props.prevarialabel}
                    showIf={props.prevshowif}
                    arialabelledby={props.prevarialabelledby}
                    ariadescribedby={props.prevariadescribedby}
                    disabled={props.prevdisabled}
                    preIconClass={props.prevpreIconClass}
                    postIconClass={props.prevpostIconClass}
                    headerKey={props.prevheaderKey}
                    subheaderKey={props.prevsubheaderKey}
                    imagePath={props.previmagePath}
                    imageAlt={props.previmageAlt}
                    ux-class={props.prevuxCalss}></UX>
                <UX
                    key="cancel"
                    id={props.cancelid}
                    type={props.canceltype}
                    mode={props.cancelmode}
                    size={props.cancelsize}
                    inline={props.cancelinline}
                    click={props.cancelclick}
                    blur={props.cancelblue}
                    name={props.cancelname}
                    showIf={props.cancelshowif}
                    buttonType={props.cancelbuttonType}
                    align={props.cancelalign}
                    iconClass={props.canceliconClass}
                    labelKey={props.cancellabelKey}
                    arialabel={props.cancelarialabel}
                    arialabelledby={props.cancelarialabelledby}
                    ariadescribedby={props.cancelariadescribedby}
                    disabled={props.canceldisabled}
                    preIconClass={props.cancelpreIconClass}
                    postIconClass={props.cancelpostIconClass}
                    headerKey={props.cancelheaderKey}
                    subheaderKey={props.cancelsubheaderKey}
                    imagePath={props.cancelimagePath}
                    imageAlt={props.cancelimageAlt}
                    ux-class={props.canceluxCalss}></UX>
                <UX
                    key="next"
                    id={props.nextid}
                    type={props.nexttype}
                    mode={props.nextmode}
                    size={props.nextsize}
                    inline={props.nextinline}
                    click={props.nextclick}
                    blur={props.nextblue}
                    name={props.nextname}
                    showIf={props.nextshowif}
                    buttonType={props.nextbuttonType}
                    align={props.nextalign}
                    iconClass={props.nexticonClass}
                    labelKey={props.nextlabelKey}
                    arialabel={props.nextarialabel}
                    arialabelledby={props.nextarialabelledby}
                    ariadescribedby={props.nextariadescribedby}
                    disabled={props.nextdisabled}
                    preIconClass={props.nextpreIconClass}
                    postIconClass={props.nextpostIconClass}
                    headerKey={props.nextheaderKey}
                    subheaderKey={props.nextsubheaderKey}
                    imagePath={props.nextimagePath}
                    imageAlt={props.nextimageAlt}
                    ux-class={props.nextuxCalss}></UX>
            </UX>
        </UX>
    );
};

NPCButtons.propTypes = {
    /** PROP TYPE DEF FOR PREVIOS BUTTON  */
    /** Define a unique id for the component */
    previd: PropTypes.string,
    /**
     * Sets the button mode
     */
    prevmode: PropTypes.oneOf([
        'default',
        'primary',
        'secondary',
        'success',
        'warning',
        'danger',
        'info',
        'link',
        'card',
    ]).isRequired,
    /** Button Size */
    prevsize: PropTypes.oneOf(['small', 'medium', 'large']),
    /** Inline buttons are displayed usually with the form context where action is optional. For example, inside the accordian component */
    previnline: PropTypes.bool,
    /** if the button needs to be displayed*/
    prevshowif: PropTypes.bool,
    /** An event-handler function usually described as: (e) => { [Your business logic] }, where 'e' is the click event and 'e.target' is the button component which triggers the event */
    prevclick: PropTypes.func,
    /** An event-handler function usually described as: (e) => { [Your business logic] }, where 'e' is the blur event and 'e.target' is the button component which triggers the event */
    prevblur: PropTypes.func,
    /** Defines to the 'name' attribute of an HTML <button> tag */
    prevname: PropTypes.string,
    /** Defines the 'type' attribute of an HTML <button> tag */
    prevbuttonType: PropTypes.oneOf(['button', 'submit']),
    /** Sets the alignment of the button to left or right. Will inherit if not defined  */
    prevalign: PropTypes.oneOf(['right', 'left']),
    /** Prepends an icon, such as those from Font Awesome, to the button's text. Uses CSS class names to determine the icon (e.g "fa fa-icon-name") */
    previconClass: PropTypes.string,
    /** Applies the value of a key from the Content Manager to the button's label/text */
    prevlabelKey: PropTypes.string.isRequired,
    /** Attribute is used to define a string that labels the current element. Use it in cases where a text label is not visible on the screen  */
    prevarialabel: PropTypes.string,
    /** Attribute establishes relationships between objects and their label(s), and its value should be one or more element IDs */
    prevarialabelledby: PropTypes.string,
    /** Attribute is used to indicate the IDs of the elements that describe the object. */
    prevariadescribedby: PropTypes.string,
    /** Disables the button */
    prevdisabled: PropTypes.oneOf(['true', 'false']),
    /** Icon class to be applied to a span element before the button label */
    prevpreIconClass: PropTypes.string,
    /** Icon class to be applied to a span element after the button label */
    prevpostIconClass: PropTypes.string,
    /** Applies the value of a key from the Content Manager to the button's header text (NOTE: 'card' mode only) */
    prevheaderKey: PropTypes.string,
    /** Applies the value of a key from the Content Manager to the button's subheader text (NOTE: 'card' mode only) */
    prevsubheaderKey: PropTypes.string,
    /** Path of image to display on button (NOTE: 'card' mode only) */
    previmagePath: PropTypes.string,
    /** Alternative text of image being displayed (NOTE: 'card' mode only) */
    previmageAlt: PropTypes.string,
    /**ux caleesd which needs to be useed */
    prevuxCalss: PropTypes.string,
    /** PROP TYPE DEF FOR NEXT BUTTON  */
    /** Define a unique id for the component */
    nextid: PropTypes.string,
    /**
     * Sets the button mode
     */
    nextmode: PropTypes.oneOf([
        'default',
        'primary',
        'secondary',
        'success',
        'warning',
        'danger',
        'info',
        'link',
        'card',
    ]).isRequired,
    /** Button Size */
    nextsize: PropTypes.oneOf(['small', 'medium', 'large']),
    /** Inline buttons are displayed usually with the form context where action is optional. For example, inside the accordian component */
    nextinline: PropTypes.bool,
    /** if the button needs to be displayed*/
    nextshowif: PropTypes.bool,
    /** An event-handler function usually described as: (e) => { [Your business logic] }, where 'e' is the click event and 'e.target' is the button component which triggers the event */
    nextclick: PropTypes.func,
    /** An event-handler function usually described as: (e) => { [Your business logic] }, where 'e' is the blur event and 'e.target' is the button component which triggers the event */
    nextblur: PropTypes.func,
    /** Defines to the 'name' attribute of an HTML <button> tag */
    nextname: PropTypes.string,
    /** Defines the 'type' attribute of an HTML <button> tag */
    nextbuttonType: PropTypes.oneOf(['button', 'submit']),
    /** Sets the alignment of the button to left or right. Will inherit if not defined  */
    nextalign: PropTypes.oneOf(['right', 'left']),
    /** Prepends an icon, such as those from Font Awesome, to the button's text. Uses CSS class names to determine the icon (e.g "fa fa-icon-name") */
    nexticonClass: PropTypes.string,
    /** Applies the value of a key from the Content Manager to the button's label/text */
    nextlabelKey: PropTypes.string.isRequired,
    /** Attribute is used to define a string that labels the current element. Use it in cases where a text label is not visible on the screen  */
    nextarialabel: PropTypes.string,
    /** Attribute establishes relationships between objects and their label(s), and its value should be one or more element IDs */
    nextarialabelledby: PropTypes.string,
    /** Attribute is used to indicate the IDs of the elements that describe the object. */
    nextariadescribedby: PropTypes.string,
    /** Disables the button */
    nextdisabled: PropTypes.oneOf(['true', 'false']),
    /** Icon class to be applied to a span element before the button label */
    nextpreIconClass: PropTypes.string,
    /** Icon class to be applied to a span element after the button label */
    nextpostIconClass: PropTypes.string,
    /** Applies the value of a key from the Content Manager to the button's header text (NOTE: 'card' mode only) */
    nextheaderKey: PropTypes.string,
    /** Applies the value of a key from the Content Manager to the button's subheader text (NOTE: 'card' mode only) */
    nextsubheaderKey: PropTypes.string,
    /** Path of image to display on button (NOTE: 'card' mode only) */
    nextimagePath: PropTypes.string,
    /** Alternative text of image being displayed (NOTE: 'card' mode only) */
    nextimageAlt: PropTypes.string,
    /**ux caleesd which needs to be useed */
    nextuxCalss: PropTypes.string,

    /** PROP TYPE DEF FOR CANCEL BUTTON  */
    /** Define a unique id for the component */
    cancelid: PropTypes.string,
    /**
     * Sets the button mode
     */
    cancelmode: PropTypes.oneOf([
        'default',
        'primary',
        'secondary',
        'success',
        'warning',
        'danger',
        'info',
        'link',
        'card',
    ]).isRequired,
    /** Button Size */
    cancelsize: PropTypes.oneOf(['small', 'medium', 'large']),
    /** Inline buttons are displayed usually with the form context where action is optional. For example, inside the accordian component */
    cancelinline: PropTypes.bool,
    /** if the button needs to be displayed*/
    cancelshowif: PropTypes.bool,
    /** An event-handler function usually described as: (e) => { [Your business logic] }, where 'e' is the click event and 'e.target' is the button component which triggers the event */
    cancelclick: PropTypes.func,
    /** An event-handler function usually described as: (e) => { [Your business logic] }, where 'e' is the blur event and 'e.target' is the button component which triggers the event */
    cancelblur: PropTypes.func,
    /** Defines to the 'name' attribute of an HTML <button> tag */
    cancelname: PropTypes.string,
    /** Defines the 'type' attribute of an HTML <button> tag */
    cancelbuttonType: PropTypes.oneOf(['button', 'submit']),
    /** Sets the alignment of the button to left or right. Will inherit if not defined  */
    cancelalign: PropTypes.oneOf(['right', 'left']),
    /** Prepends an icon, such as those from Font Awesome, to the button's text. Uses CSS class names to determine the icon (e.g "fa fa-icon-name") */
    canceliconClass: PropTypes.string,
    /** Applies the value of a key from the Content Manager to the button's label/text */
    cancellabelKey: PropTypes.string.isRequired,
    /** Attribute is used to define a string that labels the current element. Use it in cases where a text label is not visible on the screen  */
    cancelarialabel: PropTypes.string,
    /** Attribute establishes relationships between objects and their label(s), and its value should be one or more element IDs */
    cancelarialabelledby: PropTypes.string,
    /** Attribute is used to indicate the IDs of the elements that describe the object. */
    cancelariadescribedby: PropTypes.string,
    /** Disables the button */
    canceldisabled: PropTypes.oneOf(['true', 'false']),
    /** Icon class to be applied to a span element before the button label */
    cancelpreIconClass: PropTypes.string,
    /** Icon class to be applied to a span element after the button label */
    cancelpostIconClass: PropTypes.string,
    /** Applies the value of a key from the Content Manager to the button's header text (NOTE: 'card' mode only) */
    cancelheaderKey: PropTypes.string,
    /** Applies the value of a key from the Content Manager to the button's subheader text (NOTE: 'card' mode only) */
    cancelsubheaderKey: PropTypes.string,
    /** Path of image to display on button (NOTE: 'card' mode only) */
    cancelimagePath: PropTypes.string,
    /** Alternative text of image being displayed (NOTE: 'card' mode only) */
    cancelimageAlt: PropTypes.string,
    /**ux caleesd which needs to be useed */
    canceluxCalss: PropTypes.string,
    /**The following pro types should never be modified by the developer which aregiven below */
    prevtype: PropTypes.oneOf(['button']),
    nexttype: PropTypes.oneOf(['button']),
    canceltype: PropTypes.oneOf(['button']),
};

NPCButtons.defaultProps = {
    prevmode: 'default',
    prevtype: 'button',
    prevpreIconClass: 'fa fa-arrow-left',
    prevshowif: true,
    nextshowif: true,
    cancelshowif: true,
    prevuxCalss: 'mr-auto',
    cancelmode: 'default',
    canceltype: 'button',
    cancelpostIconClass: 'fa fa-ban',
    nextmode: 'primary',
    nexttype: 'button',
    nextpostIconClass: 'fa fa-arrow-right',
};
