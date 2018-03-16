
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package websfa.soap.client;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2018-03-09T14:39:16.056+02:00
 * Generated source version: 3.2.1
 * 
 */

@javax.jws.WebService(
                      serviceName = "ZTBL_WEBSERVICE",
                      portName = "ZTBL_WEBSERVICE_soap12",
                      targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style",
                      //wsdlLocation = "file:/D:/Aplicatii/Java/WebSFATest/WebSFATest/WebContent/resources/wsdl/sap_bg_test.wsdl",
                     wsdlLocation = "classPath:wsdl/sap_bg_test.wsdl",
                      endpointInterface = "websfa.soap.client.ZTBLWEBSERVICE")
                      
public class ZTBL_WEBSERVICE_Soap12Impl implements ZTBLWEBSERVICE {

    private static final Logger LOG = Logger.getLogger(ZTBL_WEBSERVICE_Soap12Impl.class.getName());

    /* (non-Javadoc)
     * @see websfa.soap.client.ZTBLWEBSERVICE#zcalcTrap(java.lang.String vCity, java.lang.String vKunnr, java.math.BigDecimal vNetwr, java.lang.String vRegio, java.lang.String vUl)*
     */
    public java.math.BigDecimal zcalcTrap(java.lang.String vCity, java.lang.String vKunnr, java.math.BigDecimal vNetwr, java.lang.String vRegio, java.lang.String vUl) { 
        LOG.info("Executing operation zcalcTrap");
        System.out.println(vCity);
        System.out.println(vKunnr);
        System.out.println(vNetwr);
        System.out.println(vRegio);
        System.out.println(vUl);
        try {
            java.math.BigDecimal _return = new java.math.BigDecimal("8580975759565577678.7199763256835103951");
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see websfa.soap.client.ZTBLWEBSERVICE#zactDiscMaxim(java.lang.String pattId)*
     */
    public java.lang.String zactDiscMaxim(java.lang.String pattId) { 
        LOG.info("Executing operation zactDiscMaxim");
        System.out.println(pattId);
        try {
            java.lang.String _return = "_return1802803292";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see websfa.soap.client.ZTBLWEBSERVICE#zcreazaComanda(java.lang.String canal, java.lang.String faraTransp, java.lang.String gvEvent, java.lang.String id, java.lang.String vMesaj, java.lang.String vOk, java.math.BigDecimal vTrans)*
     */
    public void zcreazaComanda(java.lang.String canal, java.lang.String faraTransp, java.lang.String gvEvent, java.lang.String id, javax.xml.ws.Holder<java.lang.String> vMesaj, javax.xml.ws.Holder<java.lang.String> vOk, javax.xml.ws.Holder<java.math.BigDecimal> vTrans) { 
        LOG.info("Executing operation zcreazaComanda");
        System.out.println(canal);
        System.out.println(faraTransp);
        System.out.println(gvEvent);
        System.out.println(id);
        try {
            java.lang.String vMesajValue = "vMesajValue-576038995";
            vMesaj.value = vMesajValue;
            java.lang.String vOkValue = "vOkValue1431644691";
            vOk.value = vOkValue;
            java.math.BigDecimal vTransValue = new java.math.BigDecimal("-5762071506418962396.8388825964667352153");
            vTrans.value = vTransValue;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see websfa.soap.client.ZTBLWEBSERVICE#zgetPrice(java.lang.String canal, java.lang.String city, java.lang.String dzterm, java.math.BigDecimal gvCant, java.lang.String gvKunnr, java.lang.String gvLgort, java.lang.String gvMatnr, java.lang.String gvSite, java.lang.String gvSpart, java.lang.String gvVrkme, java.lang.String gvWerks, java.lang.String mp, java.lang.String regio, java.lang.String tipPers, java.lang.String ulStoc, java.lang.Short errorCode, java.math.BigDecimal gvCantFree, java.lang.String gvCond, java.lang.String gvCurrency, java.lang.String gvMatnrFree, java.math.BigDecimal gvNetwr, java.math.BigDecimal gvNetwrFree, java.math.BigDecimal gvNetwrList, java.lang.String gvNoDisc, java.lang.String gvVrkmeFree, java.lang.String impachet, java.math.BigDecimal multiplu, java.math.BigDecimal outCantUmb, java.lang.String outUmb, java.math.BigDecimal procTrap, java.lang.String vMess, java.math.BigDecimal valTrap)*
     */
    public void zgetPrice(java.lang.String canal, java.lang.String city, java.lang.String dzterm, javax.xml.ws.Holder<java.math.BigDecimal> gvCant, java.lang.String gvKunnr, java.lang.String gvLgort, java.lang.String gvMatnr, java.lang.String gvSite, java.lang.String gvSpart, javax.xml.ws.Holder<java.lang.String> gvVrkme, java.lang.String gvWerks, java.lang.String mp, java.lang.String regio, java.lang.String tipPers, java.lang.String ulStoc, javax.xml.ws.Holder<java.lang.Short> errorCode, javax.xml.ws.Holder<java.math.BigDecimal> gvCantFree, javax.xml.ws.Holder<java.lang.String> gvCond, javax.xml.ws.Holder<java.lang.String> gvCurrency, javax.xml.ws.Holder<java.lang.String> gvMatnrFree, javax.xml.ws.Holder<java.math.BigDecimal> gvNetwr, javax.xml.ws.Holder<java.math.BigDecimal> gvNetwrFree, javax.xml.ws.Holder<java.math.BigDecimal> gvNetwrList, javax.xml.ws.Holder<java.lang.String> gvNoDisc, javax.xml.ws.Holder<java.lang.String> gvVrkmeFree, javax.xml.ws.Holder<java.lang.String> impachet, javax.xml.ws.Holder<java.math.BigDecimal> multiplu, javax.xml.ws.Holder<java.math.BigDecimal> outCantUmb, javax.xml.ws.Holder<java.lang.String> outUmb, javax.xml.ws.Holder<java.math.BigDecimal> procTrap, javax.xml.ws.Holder<java.lang.String> vMess, javax.xml.ws.Holder<java.math.BigDecimal> valTrap) { 
        LOG.info("Executing operation zgetPrice");
        System.out.println(canal);
        System.out.println(city);
        System.out.println(dzterm);
        System.out.println(gvCant.value);
        System.out.println(gvKunnr);
        System.out.println(gvLgort);
        System.out.println(gvMatnr);
        System.out.println(gvSite);
        System.out.println(gvSpart);
        System.out.println(gvVrkme.value);
        System.out.println(gvWerks);
        System.out.println(mp);
        System.out.println(regio);
        System.out.println(tipPers);
        System.out.println(ulStoc);
        try {
            short errorCodeValue = (short)192;
            errorCode.value = errorCodeValue;
            java.math.BigDecimal gvCantFreeValue = new java.math.BigDecimal("6722545735545029259.5716895872634112513");
            gvCantFree.value = gvCantFreeValue;
            java.lang.String gvCondValue = "gvCondValue2058760896";
            gvCond.value = gvCondValue;
            java.lang.String gvCurrencyValue = "gvCurrencyValue-2093234057";
            gvCurrency.value = gvCurrencyValue;
            java.lang.String gvMatnrFreeValue = "gvMatnrFreeValue-1186480159";
            gvMatnrFree.value = gvMatnrFreeValue;
            java.math.BigDecimal gvNetwrValue = new java.math.BigDecimal("-186983223298290628.782955446143440061");
            gvNetwr.value = gvNetwrValue;
            java.math.BigDecimal gvNetwrFreeValue = new java.math.BigDecimal("9047361456619895318.3820455301226176903");
            gvNetwrFree.value = gvNetwrFreeValue;
            java.math.BigDecimal gvNetwrListValue = new java.math.BigDecimal("1103886410264720332.1583990632847148684");
            gvNetwrList.value = gvNetwrListValue;
            java.lang.String gvNoDiscValue = "gvNoDiscValue1566850719";
            gvNoDisc.value = gvNoDiscValue;
            java.lang.String gvVrkmeFreeValue = "gvVrkmeFreeValue-294928288";
            gvVrkmeFree.value = gvVrkmeFreeValue;
            java.lang.String impachetValue = "impachetValue436165840";
            impachet.value = impachetValue;
            java.math.BigDecimal multipluValue = new java.math.BigDecimal("-7229138568621421939.3082960721011380670");
            multiplu.value = multipluValue;
            java.math.BigDecimal outCantUmbValue = new java.math.BigDecimal("-4708906442028849172.7161165721499150483");
            outCantUmb.value = outCantUmbValue;
            java.lang.String outUmbValue = "outUmbValue-412393813";
            outUmb.value = outUmbValue;
            java.math.BigDecimal procTrapValue = new java.math.BigDecimal("6405688149630318172.7926775874191040821");
            procTrap.value = procTrapValue;
            java.lang.String vMessValue = "vMessValue-1807699083";
            vMess.value = vMessValue;
            java.math.BigDecimal valTrapValue = new java.math.BigDecimal("6427878504254650442.5849705699628539534");
            valTrap.value = valTrapValue;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see websfa.soap.client.ZTBLWEBSERVICE#zstareComanda(java.lang.String nrCom, java.lang.String pernrCh, java.lang.String stare, java.lang.String vMess, java.lang.String vOk)*
     */
    public void zstareComanda(java.lang.String nrCom, java.lang.String pernrCh, java.lang.String stare, javax.xml.ws.Holder<java.lang.String> vMess, javax.xml.ws.Holder<java.lang.String> vOk) { 
        LOG.info("Executing operation zstareComanda");
        System.out.println(nrCom);
        System.out.println(pernrCh);
        System.out.println(stare);
        try {
            java.lang.String vMessValue = "vMessValue868230972";
            vMess.value = vMessValue;
            java.lang.String vOkValue = "vOkValue-854761616";
            vOk.value = vOkValue;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}