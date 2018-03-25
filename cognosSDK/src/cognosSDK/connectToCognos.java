package cognosSDK;

import java.net.URL;
import javax.xml.namespace.QName;
import org.apache.axis.client.Stub;
import org.apache.axis.message.SOAPHeaderElement;
// import com.cognos.developer.schemas.bibus._3.BaseClass;
import com.cognos.developer.schemas.bibus._3.BiBusHeader;
import com.cognos.developer.schemas.bibus._3.ContentManagerService_PortType;
import com.cognos.developer.schemas.bibus._3.ContentManagerService_ServiceLocator;
// import com.cognos.developer.schemas.bibus._3.PropEnum;
// import com.cognos.developer.schemas.bibus._3.QueryOptions;
// import com.cognos.developer.schemas.bibus._3.SearchPathMultipleObject;
//import com.cognos.developer.schemas.bibus._3.Sort;
import com.cognos.developer.schemas.bibus._3.XmlEncodedXML;
 
public class connectToCognos {
private static String dispatcherURL = "***";
private static String nameSpaceID = "***";
private static String userName = "***";
private static String password = "***";
private ContentManagerService_PortType cmService=null;
 
public static void main(String args[]) {
	connectToCognos mainClass = new connectToCognos(); // instantiate the class
 
    // Connect to the Cognos services
    mainClass.connectToCognosService (dispatcherURL); 
 
    // Logon to Cognos 
    mainClass.logonToCognos(nameSpaceID, userName, password); 
 
    // Logoff from Cognos 
    mainClass.logoffFromCognos(); 
}
 
// Connect to the Cognos services
private void connectToCognosService(String dispatcherURL) {
    ContentManagerService_ServiceLocator cmServiceLocator =
        new ContentManagerService_ServiceLocator();
 
    try {
        URL url = new URL(dispatcherURL);
        cmService = cmServiceLocator.getcontentManagerService(url);
        System.out.println("connect to webservice");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
 
// Logon to Cognos 
private void logonToCognos(String nsID, String user, String pswd) {
    StringBuffer credentialXML = new StringBuffer();
    credentialXML.append("<credential>");
    credentialXML.append("<namespace>").append(nsID).append("</namespace>");
    credentialXML.append("<username>").append(user).append("</username>");
    credentialXML.append("<password>").append(pswd).append("</password>");
    credentialXML.append("</credential>");
 
    String encodedCredentials = credentialXML.toString();
    XmlEncodedXML xmlCredentials = new XmlEncodedXML();
    xmlCredentials.set_value(encodedCredentials);
 
    try {
        cmService.logon(xmlCredentials, null);
        SOAPHeaderElement temp = ((Stub)cmService).getResponseHeader(
            "http://developer.cognos.com/schemas/bibus/3/", "biBusHeader");
        BiBusHeader CMbibus = (BiBusHeader)temp.getValueAsType(
            new QName ("http://developer.cognos.com/schemas/bibus/3/",
                "biBusHeader"));
        ((Stub)cmService).setHeader(
            "http://developer.cognos.com/schemas/bibus/3/", 
            "biBusHeader", CMbibus);
        System.out.println("logged in");
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}
 
// Method to Logoff from Cognos 
private void logoffFromCognos() {
    try {
        cmService.logoff();
        System.out.println("logged off");
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}

}

function logon(aNamespace, aUserid, aPassword){ // build the xml credentials element 
	var xmlData = "<credentials>" +
}
												// "<credentialElements><name>CAMNamespace</name><label>Namespace:</label>"
												// + "<value><actualValue>" + aNamespace + "</actualValue></value>" +
												// "</credentialElements><credentialElements><name>CAMUsername</name><label>User
												// ID:</label>" + "<value><actualValue>" + aUserid +
												// "</actualValue></value>" +
												// "</credentialElements><credentialElements><name>CAMPassword</name><label>Password:</label>"
												// + "<value><actualValue>" + aPassword + "</actualValue></value>" +
												// "</credentialElements></credentials>"; //authentication resource var
												// rdsLogonUrl = cognosUrl + '/rds/auth/logon'; var request = new
												// Ajax.Request(rdsLogonUrl,{ asynchronous : false, method : 'get',
												// parameters :{ xmlData : xmlData }, onSuccess : function(aTransport) {
												// return aTransport.responseText; }, onFailure : function(aTransport) {
												// var regex = /(RDS-ERR-)(\d*)/; var err =
												// aTransport.responseText.match(regex); if (err != null) {
												// alert(aTransport.responseText); } }); }