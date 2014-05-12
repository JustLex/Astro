package by.zhakov.astro;

import by.zhakov.astro.xml.XMLController;
import org.apache.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;


public class Run{
    private static Logger log = Logger.getRootLogger();
    /**
     * This method runs main controller of application
     * @param args First element of args array is the path to source file
     *             second element is the path to destination file. If there's no
     *             such destination file, it will be created automatically
     */
	public static void main(String[] args){
        log.debug("starting program");

        if(args.length >= 2){
            log.debug("initializing restructuring");
            XMLController.restructureXML(args[0], args[1]);
        }
        log.debug("exiting program");
	}
}