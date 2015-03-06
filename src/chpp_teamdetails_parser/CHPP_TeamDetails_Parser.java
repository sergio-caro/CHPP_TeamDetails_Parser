/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chpp_teamdetails_parser;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author sergio
 * @version 1.0
 */
public class CHPP_TeamDetails_Parser {
/**
     * Executes the XML parsing.<br/>
     * <b>NOTE</b>: This is Match Details <u>3.1 interface version</u>.
     *
     * @param input_file XML file as input
     * @return a HattrickData object with the parsed information, null if errors.
     * @throws java.lang.Exception Throws exception if something went wrong when parsing the file.
     * @see
     * <a href="http://www.hattrick.org/Community/CHPP/NewDocs/File.aspx?name=teamdetails">Go to Hattrick
     * CHPP webpage for more information</a>
     *
     */
    public static team_details_v3_1.HattrickData parse_match_details_v3_1(File input_file) throws Exception {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(team_details_v3_1.HattrickData.class);
            System.out.println("¿The file could be read? " + input_file.canRead());
            if (input_file.canRead()) {
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                team_details_v3_1.HattrickData ht_data = (team_details_v3_1.HattrickData) jaxbUnmarshaller.unmarshal(input_file);
                
                if(Float.toString(ht_data.getVersion()).equalsIgnoreCase("2.5")){
                    return ht_data;
                }else{
                    throw new Exception("XML had an unsupported version (found "+Float.toString(ht_data.getVersion())+"), but this method requieres 3.1 version.");
                }
            }
        } catch (JAXBException ex) {
            Logger.getLogger(CHPP_TeamDetails_Parser.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("The file could not be parsed.");
        }
        return null;
    }

    /**
     * Main class for testing the library
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
    }

}
