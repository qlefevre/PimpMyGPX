package com.github.pimpmygpx;

import io.jenetics.jpx.GPX;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.TimeZone;
import java.util.function.Consumer;

public class AbstractUnitTest {

    public static final String GPX_LANDASIENNE = "/La_Landasienne_2023_5Km_20231015.gpx";

    public void testGpx(String ressourceFilename, Consumer<GPX> function) throws IOException {
        try(InputStream is = getClass().getResourceAsStream(ressourceFilename)) {
            GPX gpx = GPX.Reader.DEFAULT.read(is);
            function.accept(gpx);
        }
    }

    public void testMain(String ressourceFilename, String[] args, Consumer<GPX> function) throws Exception {
        Path tmpDir = Files.createTempDirectory("pmgpx");
        Path tmpInputFile = tmpDir.resolve(ressourceFilename.substring(1));
        try(InputStream is = getClass().getResourceAsStream(ressourceFilename)) {
            Files.copy(is,tmpInputFile);
        }
        String[] testArgs = Arrays.copyOf(args,args.length+1);
        testArgs[args.length] = tmpInputFile.toString();
        MainCli.main(testArgs);
        Path tmpOutputFile = tmpDir.resolve(ressourceFilename.substring(1).replace(".gpx",".out.gpx"));
        try(InputStream is = new FileInputStream(tmpOutputFile.toFile())) {
            GPX gpx = GPX.Reader.DEFAULT.read(is);
            function.accept(gpx);
        }
    }

}
