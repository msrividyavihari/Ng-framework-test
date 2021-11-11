package com.deloitte.nextgen.framework.automate;

/**
 * @author nishmehta
 * @since 22/07/2021 11:26 AM
 */


public class Options {

    private final boolean verbose;
    private final String generateMode;

    public Options(Boolean verbose, String generateMode) {
        this.verbose = verbose;
        this.generateMode = generateMode;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public String getGenerateMode() {
        return generateMode;
    }
}
