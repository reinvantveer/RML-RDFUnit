package be.ugent.mmlab.rml.rmlvalidator.main;

import be.ugent.mmlab.rml.model.RMLMapping;
import be.ugent.mmlab.rml.rmlvalidator.RMLConfiguration;
import be.ugent.mmlab.rml.rmlvalidator.RMLMappingExtractor;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.antidot.semantic.rdf.rdb2rdf.r2rml.exception.InvalidR2RMLStructureException;
import net.antidot.semantic.rdf.rdb2rdf.r2rml.exception.InvalidR2RMLSyntaxException;
import net.antidot.semantic.rdf.rdb2rdf.r2rml.exception.R2RMLDataError;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFParseException;

/**
 *
 * @author andimou
 */
public class MainTest {
    
    // Log
	private static Log log = LogFactory.getLog(MainTest.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        
        BasicConfigurator.configure();
        CommandLine commandLine = RMLConfiguration.parseArguments(args);
        try {
            commandLine = RMLConfiguration.parseArguments(args);
            
            if (commandLine.hasOption("h")) 
                RMLConfiguration.displayHelp();
            
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("RML Validator");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("");
            System.out.println("Usage: mvn exec:java -Dexec.args=\"<mapping_file> \"");
            System.out.println("");
            System.out.println("With");
            System.out.println("    <mapping_file> = The RML mapping document conform with the RML specification (http://semweb.mmlab.be/rml/spec.html)");
            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------");

            //System.out.println("[RMLValidator:main] mapping document " + args[0]);
            
            //RMLMapping rmlMapping = RMLMappingFactory.extractRMLMapping("http://rml.io/rml/data/CD_EWI/Destelbergen/destelbergen_XML.rml.ttl");
            //RMLMapping rmlMapping = RMLMappingFactory.extractRMLMapping("http://rml.io/rml/data/drafts/offer.rml.ttl");
            //RMLMapping rmlMapping = RMLMappingFactory.extractRMLMapping("http://rml.io/rml/data/drafts/instrument_sample.rml.ttl");
            RMLMapping rmlMapping = RMLMappingExtractor.extractRMLMapping("/home/andimou/Desktop/offer.rml.ttl");
            
            if (rmlMapping == null) {
                log.error("[RMLEngine:runRMLMapping] " + "/home/andimou/Desktop/offer.rml.ttl" + "not found");
                throw new IllegalArgumentException(
                        "[RMLEngine:runRMLMapping] No RML Mapping object found.");
            }
            
            
        } catch (R2RMLDataError ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidR2RMLStructureException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidR2RMLSyntaxException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RepositoryException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RDFParseException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
