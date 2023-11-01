package com.github.pimpmygpx;

import org.apache.commons.cli.*;

import java.util.Arrays;

public class MainCli {

    public static void main(String[] args) throws Exception {

        // gui PimpMyGPX.exe
        // console pmgpx.exe

        // Options
        Options options = new Options();
        Option sOption = new Option("s", "start-time", true, "Change start time in hh:mm format");
        sOption.setArgName("hour");
        options.addOption(sOption);
        Option fOption = new Option("f", "finish-time", true, "Change finish time in hh:mm format");
        fOption.setArgName("hour");
        options.addOption(fOption);
        Option dOption = new Option("d", "date", true, "Change the date");
        dOption.setArgName("date");
        options.addOption(dOption);
        options.addOption("h","help",false,"Display help");

        // Analyse
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        // start time
        if (cmd.hasOption("start-time")) {
            String argStartTime = cmd.getOptionValue("start-time");
            System.out.println(argStartTime);
        }
        if(cmd.hasOption("help") || args.length == 0) {
            String header = "Do something useful with a GPX file\n\n";
            String footer = "\nPlease report issues at http://example.com/issues";

            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("pimpmygpx", header, options, footer, true);
        }
    }

}

