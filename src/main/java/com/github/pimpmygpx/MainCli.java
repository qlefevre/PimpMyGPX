package com.github.pimpmygpx;

import io.jenetics.jpx.GPX;
import org.apache.commons.cli.*;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

public class MainCli {

    public static void main(String[] args) throws Exception {

        // gui PimpMyGPX.exe
        // console pmgpx.exe

        Options options = getOptions();

        // Analyse
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if(cmd.hasOption("help") || args.length == 0) {
            String header = "Do something useful with a GPX file\n";
            String footer = "Please report issues at http://example.com/issues";

            System.out.println("PimpMyGPX 1.0 ");
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("pmgpx [OPTION]... [FILE]", header, options, footer);
        }else
        // A-t-ton bien un seul argument de fichier
        if(cmd.getArgList().size() == 1){

            // Fichier d'entrée
            Path inputFile = Paths.get(cmd.getArgList().get(0));
            GPX inputGpx = GPX.read(inputFile);
            GPX outputGpx = inputGpx;

            // start time
            if (cmd.hasOption("start-time")) {
                String startTime = cmd.getOptionValue("start-time");
                LocalTime localTime = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm"));
                outputGpx = GpxUtils.changeStartTime(inputGpx,localTime);
            }

            // finish time
            if (cmd.hasOption("finish-time")) {
                String finishTime = cmd.getOptionValue("finish-time");
                LocalTime localTime = LocalTime.parse(finishTime, DateTimeFormatter.ofPattern("HH:mm"));
                outputGpx = GpxUtils.changeFinishTime(inputGpx,localTime);
            }

            // date
            if (cmd.hasOption("date")) {
                String date = cmd.getOptionValue("date");
                LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
                outputGpx = GpxUtils.changeDate(inputGpx,localDate);
            }

            // Output file
            Path outputFile = Paths.get(inputFile.toString().replace(".gpx",".out.gpx"));
            GPX.write(outputGpx, outputFile);
        }


    }

    private static Options getOptions() throws ParseException {
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
        Option oOption = new Option("o", "output", true, "Set output file");
        oOption.setArgName("file");
        options.addOption(oOption);
        options.addOption("h","help",false,"Display help");

        return options;
    }

}

