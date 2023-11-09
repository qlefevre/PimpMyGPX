package com.github.pimpmygpx;

import com.github.pimpmygpx.cli.CustomHelpFormatter;
import io.jenetics.jpx.GPX;
import io.jenetics.jpx.GPX.Writer;
import io.jenetics.jpx.GPX.Writer.Indent;
import org.apache.commons.cli.*;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

public class MainCli {
    public static final int DEFAULT_FRACTION_DIGITS = 7;
    public static final Indent DEFAULT_INDENT = new Indent(" ");

    public static void main(String[] args) throws Exception {

        // gui PimpMyGPX.exe
        // console pmgpx.exe

        Options options = getOptions();

        // Analyse
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if(cmd.hasOption("help") || args.length == 0) {
            String header = "Do something useful with a GPX file\n";
            String footer = "Please report issues at https://github.com/qlefevre/PimpMyGPX";

            System.out.println("PimpMyGPX 1.0 ");
            HelpFormatter formatter = new CustomHelpFormatter();
            formatter.setOptionComparator((o1, o2) -> 0);
            formatter.setWidth(90);
            formatter.setDescPadding(4);
            formatter.printHelp("pmgpx [OPTION]... [FILE]", header, options, footer);
        }else
        // A-t-ton bien un seul argument de fichier
        if(cmd.getArgList().size() == 1){

            // Fichier d'entrée
            Path inputFile = Paths.get(cmd.getArgList().get(0));
            GPX inputGpx = GPX.read(inputFile);

            // Supprime les <ele>
            inputGpx = GpxUtils.removeElevations(inputGpx);

            // start time
            if (cmd.hasOption("start-time")) {
                String startTime = cmd.getOptionValue("start-time");
                LocalTime localTime = LocalTime.parse(startTime, DateTimeFormatter.ISO_LOCAL_TIME);
                inputGpx = GpxUtils.changeStartTime(inputGpx,localTime);
            }

            // finish time
            if (cmd.hasOption("finish-time")) {
                String finishTime = cmd.getOptionValue("finish-time");
                LocalTime localTime = LocalTime.parse(finishTime, DateTimeFormatter.ISO_LOCAL_TIME);
                inputGpx = GpxUtils.changeFinishTime(inputGpx,localTime);
            }

            // date
            if (cmd.hasOption("date")) {
                String date = cmd.getOptionValue("date");
                LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
                inputGpx = GpxUtils.changeDate(inputGpx,localDate);
            }

            // lat / lon
            if (cmd.hasOption("lat") || cmd.hasOption("lon")){
                double lat = cmd.hasOption("lat") ? Double.parseDouble(cmd.getOptionValue("lat")) : 0;
                double lon =  cmd.hasOption("lon") ? Double.parseDouble(cmd.getOptionValue("lon")) : 0;
                inputGpx = GpxUtils.moveWayPoints(inputGpx,lat,lon);
            }

            // info
            if (cmd.hasOption("info")){
                System.out.println(GpxUtils.info(inputGpx));
            }

            // Mise à jour du temps
            inputGpx = GpxUtils.updateMetadata(inputGpx);

            // Output file
            Path outputFile;
            if(cmd.hasOption("output")){
                outputFile = Paths.get(cmd.getOptionValue("output"));
                // Si le chemin n'est pas absolu
                if(!outputFile.isAbsolute()){
                    outputFile = inputFile.getParent().resolve(outputFile);
                }
            }else {
                outputFile =Paths.get(inputFile.toString().replace(".gpx", ".out.gpx"));
            }
            Writer.of(DEFAULT_INDENT,DEFAULT_FRACTION_DIGITS).write(inputGpx, outputFile);
        }


    }

    private static Options getOptions() throws ParseException {
        // Options
        Options options = new Options();
        Option sOption = new Option("s", "start-time", true, "Change start time in hh:mm or hh:mm:ss format");
        sOption.setArgName("hour");
        options.addOption(sOption);
        Option fOption = new Option("f", "finish-time", true, "Change finish time in hh:mm or hh:mm:ss format");
        fOption.setArgName("hour");
        options.addOption(fOption);
        Option dOption = new Option("d", "date", true, "Change the date in yyyy-mm-dd format");
        dOption.setArgName("date");
        options.addOption(dOption);
        Option latOption = new Option("lat", "latitude", true, "Change the latitude by adding <lat> to all waypoints");
        latOption.setArgName("lat");
        options.addOption(latOption);
        Option lonOption = new Option("lon", "longitude", true, "Change the longitude by adding <lon> to all waypoints");
        lonOption.setArgName("lon");
        options.addOption(lonOption);
        Option oOption = new Option("o", "output", true, "Set output file");
        oOption.setArgName("file");
        options.addOption(oOption);
        options.addOption("i","info",false,"Display info about the given GPX");
        options.addOption("h","help",false,"Display help");

        return options;
    }

}

