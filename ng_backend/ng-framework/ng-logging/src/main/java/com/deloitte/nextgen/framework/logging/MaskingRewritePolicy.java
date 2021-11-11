package com.deloitte.nextgen.framework.logging;

import com.deloitte.nextgen.framework.logging.utils.MaskingUtils;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.rewrite.RewritePolicy;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.logging.slf4j.Log4jMarker;

/**
 * @author nishmehta on 22/10/2020 12:40 PM
 * @project ng-framework
 */

@Plugin(name = "MaskingRewritePolicy", category = Core.CATEGORY_NAME, elementType = "rewritePolicy", printObject = true)
public class MaskingRewritePolicy implements RewritePolicy {

    @PluginFactory
    public static MaskingRewritePolicy createPolicy() {
        return new MaskingRewritePolicy();
    }


    @Override
    public LogEvent rewrite(LogEvent source) {

        Marker sourceMarker = source.getMarker();
        if (sourceMarker == null) {
            return source;
        }

        final Message msg = source.getMessage();
        if (msg == null) {
            return source;
        }

        Object[] params = msg.getParameters();
        if (params == null || params.length == 0) {
            return source;
        }

        Log4jMarker eventMarker = new Log4jMarker(sourceMarker);

        for (int i = 0; i < params.length; i++) {

            if (params[i] == null) {
                continue;
            }


            if (eventMarker.contains(LogMarker.SECRET_LEVEL_TWO)) {

                params[i] = MaskingUtils.maskEverythingExceptLastFourChars(params[i].toString());

            } else if (eventMarker.contains(LogMarker.SECRET_LEVEL_ONE)) {

                params[i] = MaskingUtils.maskCharsInBetween(params[i].toString());

            } else if (eventMarker.contains(LogMarker.SECRET)) {

                params[i] = MaskingUtils.maskString(params[i].toString());

            } else {
                break;
            }

        }

        Message outMessage = new ParameterizedMessage(msg.getFormat(), params,
                msg.getThrowable());

        return new Log4jLogEvent
                .Builder(source)
                .setMessage(outMessage)
                .build();
    }
}