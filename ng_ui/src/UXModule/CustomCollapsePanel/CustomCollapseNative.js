/**
 * Ref: https://github.com/thednp/bootstrap.native
 * Has been customized to cater Lift needs
 */

import hasClass from './shorter-js/src/class/hasClass';
import addClass from './shorter-js/src/class/addClass';
import removeClass from './shorter-js/src/class/removeClass';
import { bootstrapCustomEvent, dispatchCustomEvent } from './util/event.js';
import { componentInit } from './util/init';
import sz from 'sizzle';

// COLLAPSE DEFINITION
// ===================

export default function CustomCollapse(element, options) {
    // set options
    options = options || {};

    // bind
    let self = this;

    // target practice
    let accordion = null,
        collapse = null,
        activeCollapse,
        activeElement,
        // custom events
        showCustomEvent,
        shownCustomEvent,
        hideCustomEvent,
        hiddenCustomEvent;

    // private methods
    function queryElement(selector, parent) {
        var lookUp = parent && parent instanceof Element ? parent : document;
        return selector instanceof Element ? selector : lookUp.querySelector(selector);
    }
    function getElementTransitionDuration(element) {
        let computedStyle = getComputedStyle(element),
            property = computedStyle[transitionProperty],
            duration =
                supportTransition && property && property !== 'none'
                    ? parseFloat(computedStyle[transitionDuration])
                    : 0;

        return !isNaN(duration) ? duration * 1000 : 0;
    }

    function transitionProperty() {
        return 'webkitTransition' in document.head.style
            ? 'webkitTransitionProperty'
            : 'transitionProperty';
    }
    function transitionEndEvent() {
        return 'webkitTransition' in document.head.style ? 'webkitTransitionEnd' : 'transitionend';
    }
    function transitionDuration() {
        return 'webkitTransition' in document.head.style
            ? 'webkitTransitionDuration'
            : 'transitionDuration';
    }
    function supportTransition() {
        return 'webkitTransition' in document.head.style || 'transition' in document.head.style;
    }

    function emulateTransitionEnd(element, handler) {
        let called = 0,
            duration = getElementTransitionDuration(element);
        duration
            ? element.addEventListener(transitionEndEvent, function transitionEndWrapper(e) {
                  addEvent(e, called, handler);
                  element.removeEventListener(transitionEndEvent, transitionEndWrapper);
              })
            : setTimeout(timeOut(called, handler), 17);
    }

    function addEvent(e, called, handler) {
        return !called && handler(e), (called = 1);
    }
    function timeOut(called, handler) {
        return !called && handler(), (called = 1);
    }

    function openAction(collapseElement, toggle, fn) {
        dispatchCustomEvent.call(collapseElement, showCustomEvent);
        if (showCustomEvent.defaultPrevented) return;
        collapseElement.isAnimating = true;
        addClass(collapseElement, 'collapsing');
        addClass(collapseElement, 'state-expanded');
        removeClass(collapseElement, 'collapse');
        removeClass(collapseElement, 'state-collapsed');
        collapseElement.style.height = `${collapseElement.scrollHeight}px`;

        emulateTransitionEnd(collapseElement, () => {
            collapseElement.isAnimating = false;
            collapseElement.setAttribute('aria-expanded', 'true');
            toggle.setAttribute('aria-expanded', 'true');
            removeClass(collapseElement, 'collapsing');
            addClass(collapseElement, 'collapse');
            addClass(collapseElement, 'show');
            collapseElement.style.height = '';
            dispatchCustomEvent.call(collapseElement, shownCustomEvent);
            if (fn !== undefined && typeof fn === 'function') {
                fn();
            }
        });
    }
    function closeAction(collapseElement, toggle, fn) {
        dispatchCustomEvent.call(collapseElement, hideCustomEvent);
        if (hideCustomEvent.defaultPrevented) return;
        collapseElement.isAnimating = true;
        collapseElement.style.height = `${collapseElement.scrollHeight}px`; // set height first
        removeClass(collapseElement, 'collapse');
        removeClass(collapseElement, 'show');
        removeClass(collapseElement, 'state-expanded');
        addClass(collapseElement, 'collapsing');
        addClass(collapseElement, 'state-collapsed');
        //collapseElement.offsetWidth; // force reflow to enable transition
        collapseElement.style.height = '0px';

        emulateTransitionEnd(collapseElement, () => {
            collapseElement.isAnimating = false;
            collapseElement.setAttribute('aria-expanded', 'false');
            toggle.setAttribute('aria-expanded', 'false');
            removeClass(collapseElement, 'collapsing');
            addClass(collapseElement, 'collapse');
            collapseElement.style.height = '';
            dispatchCustomEvent.call(collapseElement, hiddenCustomEvent);
            if (fn !== undefined && typeof fn === 'function') {
                fn();
            }
        });
    }

    // public methods
    self.toggle = e => {
        if (e && e.target.tagName === 'A') {
            e.preventDefault();
        }
        if (!hasClass(collapse, 'show')) {
            self.show();
        } else {
            self.hide();
        }
    };
    self.readOnly = flag => {
        let elements = sz(
            'input,select,textarea,div.ux-radio,div.ux-radio-card,div.ux-radio-bar,.readonly-no-value,div.datepicker,div.ux-input-group,div.ux-checkbox,div.ux-switch,div.ux-cb-persona-wrapper,div.ux-toggle-card',
            collapse,
        );
        if (Array.isArray(elements)) {
            elements.forEach(el => {
                if (flag) {
                    if (el.getAttribute('readonly') === null) {
                        el.setAttribute('readonly', true);
                    }
                } else {
                    if (el.getAttribute('readonly') !== null && !el.hasAttribute('data-readonly')) {
                        el.removeAttribute('readonly');
                    }
                }
            });
        }
        sz('button:not([id$="_modify"]):not([id$="_done"])', collapse).forEach(el => {
            if (flag) {
                el.setAttribute('disabled', true);
            } else {
                el.removeAttribute('disabled');
            }
        });
    };
    self.enableReadonly = () => {
        self.readOnly(true);
    };
    self.disableReadonly = () => {
        self.readOnly(false);
    };
    self.hide = fn => {
        if (collapse !== null && collapse.isAnimating) return;
        closeAction(collapse, element, fn);
        addClass(element, 'collapsed');
    };
    self.show = fn => {
        if (accordion) {
            activeCollapse = accordion.getElementsByClassName(`collapse show`)[0];
            activeElement =
                activeCollapse &&
                (queryElement(`[data-target="#${activeCollapse.id}"]`, accordion) ||
                    queryElement(`[href="#${activeCollapse.id}"]`, accordion));
        }

        if (collapse !== null && !collapse.isAnimating) {
            if (activeElement && activeCollapse !== collapse) {
                closeAction(activeCollapse, activeElement);
                addClass(activeElement, 'collapsed');
            }
            openAction(collapse, element, fn);
            removeClass(element, 'collapsed');
        }
    };
    self.dispose = () => {
        options = options || false;
        element.removeEventListener('click', self.toggle, options);
        delete element.Collapse;
    };

    // init
    componentInit(() => {
        // initialization element
        element = queryElement(element);

        // reset on re-init
        element.Collapse && element.Collapse.dispose();

        // DATA API
        const accordionData = element.getAttribute('data-parent');

        // custom events
        showCustomEvent = bootstrapCustomEvent('show', 'collapse');
        shownCustomEvent = bootstrapCustomEvent('shown', 'collapse');
        hideCustomEvent = bootstrapCustomEvent('hide', 'collapse');
        hiddenCustomEvent = bootstrapCustomEvent('hidden', 'collapse');

        // determine targets
        collapse = queryElement(
            options.target || element.getAttribute('data-target') || element.getAttribute('href'),
        );

        // invalidate
        // if (!collapse) return;

        collapse.isAnimating = false;
        accordion = element.closest(options.parent || accordionData);

        // associations
        collapse && (self.collapse = collapse);
        // accordion && ((self.options = {}), (self.options.parent = accordion));
        // prevent adding event handlers twice
        if (!element.Collapse) {
            options = options || false;
            element.addEventListener('click', self.toggle, options);
        }

        // associate target to init object
        self.element = element;
        element.Collapse = self;
    });
}
