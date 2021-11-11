package com.deloitte.nextgen.framework.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.slf4j.Log4jMarker;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nishmehta on 22/10/2020 3:27 PM
 * @project ng-logging
 */

@Plugin(name = "LogMarkerFilter", category = Node.CATEGORY, elementType = Filter.ELEMENT_TYPE, printObject = true)
public class RestrictedDataMarkerFilter extends AbstractFilter {

    protected static final List<org.slf4j.Marker> markersToMatch = new ArrayList<>(3);

    static {
        markersToMatch.add(LogMarker.SECRET);
        markersToMatch.add(LogMarker.SECRET_LEVEL_ONE);
        markersToMatch.add(LogMarker.SECRET_LEVEL_TWO);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object... params) {
        return filter(marker);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, Object msg,
                         Throwable t) {
        return filter(marker);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker,
                         Message msg, Throwable t) {
        return filter(marker);
    }

    @Override
    public Result filter(LogEvent event) {
        // make sure the event has a marker
        org.apache.logging.log4j.Marker eventMarker = event.getMarker();
        if (eventMarker == null) {
            return Result.DENY;
        }

        return filter(eventMarker);
    }

    private Result filter(Marker marker) {
        if (!isStarted()) {
            return Result.NEUTRAL;
        }

        org.apache.logging.slf4j.Log4jMarker slf4jMarker = new Log4jMarker(marker);
        for (org.slf4j.Marker matcher : markersToMatch) {
            if (slf4jMarker.contains(matcher.getName())) {
                return Result.ACCEPT;
            }
        }

        return Result.DENY;
    }

    @PluginFactory
    public static RestrictedDataMarkerFilter createFilter(
            @PluginAttribute(value = "acceptAll", defaultBoolean = false) boolean acceptAll) {
        return new RestrictedDataMarkerFilter();
    }
}
