package com.github.pimpmygpx.cli;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomHelpFormatter extends HelpFormatter {

    private List<Option> optionsList;

    private int longOptCounter = 0;


    /**
     * Render the specified Options and return the rendered Options in a StringBuffer.
     *
     * @param sb The StringBuffer to place the rendered Options into.
     * @param width The number of characters to display per line
     * @param options The command line Options
     * @param leftPad the number of characters of padding to be prefixed to each line
     * @param descPad the number of characters of padding to be prefixed to each description line
     *
     * @return the StringBuffer with the rendered Options contents.
     */
    @Override
    protected StringBuffer renderOptions(final StringBuffer sb, final int width, final Options options, final int leftPad, final int descPad) {
        optionsList = new ArrayList<>(options.getOptions());
        if (getOptionComparator() != null) {
            Collections.sort(optionsList, getOptionComparator());
        }
        longOptCounter = 0;
        return super.renderOptions( sb,  width,  options,  leftPad,  descPad);
    }

    @Override
    public String getLongOptPrefix() {
        Option option = optionsList.get(longOptCounter);
        longOptCounter++;
        switch(option.getOpt().length()){
            case 1:
                return "  --";
            case 3:
            default:
                return "--";
        }
    }
}
