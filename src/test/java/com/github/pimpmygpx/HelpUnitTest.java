package com.github.pimpmygpx;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  Junit pour tester l'option --help : afficher l'aide
 */
public class HelpUnitTest extends AbstractUnitTest {

    private static final String EXPECTED_HELP = "PimpMyGPX 1.0 \n" +
            "usage: pmgpx [OPTION]... [FILE]\n" +
            "Do something useful with a GPX file\n" +
            " -s,  --start-time <hour>     Change start time in hh:mm or hh:mm:ss format\n" +
            " -f,  --finish-time <hour>    Change finish time in hh:mm or hh:mm:ss format\n" +
            " -d,  --date <date>           Change the date in yyyy-mm-dd format\n" +
            " -lat,--latitude <lat>        Change the latitude by adding <lat> to all waypoints\n" +
            " -lon,--longitude <lon>       Change the longitude by adding <lon> to all waypoints\n" +
            " -o,  --output <file>         Set output file\n" +
            " -i,  --info                  Display info about the given GPX\n" +
            " -h,  --help                  Display help\n" +
            "Please report issues at https://github.com/qlefevre/PimpMyGPX".replaceAll("\n",System.lineSeparator());

    @Test
    public void testMainOptionhelp() throws Exception {
        // Set the standard output to use newConsole.
        ByteArrayOutputStream newConsole = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newConsole));
        MainCli.main(new String[]{"-h"});
        assertEquals(EXPECTED_HELP, newConsole.toString().trim(),"Le message d'aide n'est pas correct.");
    }

}

