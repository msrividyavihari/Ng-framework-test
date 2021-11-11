package com.deloitte.nextgen.util;

import com.sun.xml.bind.marshaller.CharacterEscapeHandler;
import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;
import java.io.Writer;

public class JaxbCharacterEscapeHandler implements CharacterEscapeHandler {
    /**
     * @param buf       The array of characters.
     * @param start    The starting position.
     * @param len   The number of characters to use.
     * @param isAttValue true if this is an attribute value literal.
     * @param out  Writer
     */
    @Override
    public void escape(char[] buf, int start, int len, boolean isAttValue, Writer out) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < start + len; i++) {
            sb.append(buf[i]);
        }
        out.write(StringEscapeUtils.unescapeHtml4(sb.toString().trim()));
    }
}
