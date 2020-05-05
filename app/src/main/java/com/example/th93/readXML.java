package com.example.th93;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class readXML {

    private static readXML xml = null;
    private ArrayList<Smartpost> posts = new ArrayList<Smartpost>();
    private ArrayList<String> names = new ArrayList<>();

    private readXML(){
    }

    public static readXML getInstance() {
        if (xml == null) {
            xml = new readXML();
        }
        return xml;
    }

    public ArrayList<String> getPosts(){
        DocumentBuilder builder = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlStringFin = " http://iseteenindus.smartpost.ee/api/?request=destinations&country=FI&type=APT";
            String urlStringEst = "http://iseteenindus.smartpost.ee/api/?request=destinations&country=EE&type=APT";
            Document docFin = builder.parse(urlStringFin);
            Document docEst = builder.parse(urlStringEst);
            docFin.getDocumentElement().normalize();
            docEst.getDocumentElement().normalize();
            names.add("Select SmartPost");

            //////Käydään läpi Suomen Smatrpostit///////
            NodeList nListFin = docFin.getDocumentElement().getElementsByTagName("item");

            for (int i=0; i<nListFin.getLength(); i++) {
                Node node = nListFin.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String nameF = element.getElementsByTagName("name").item(0).getTextContent();
                    String locationF = element.getElementsByTagName("country").item(0).getTextContent();
                    String addressF = element.getElementsByTagName("address").item(0).getTextContent();
                    String availabilityF = element.getElementsByTagName("availability").item(0).getTextContent();
                    posts.add(new Smartpost(nameF,locationF, addressF, availabilityF));
                    names.add(nameF);

                    String[] separatedFin = availabilityF.split(","); /*ma-pe 8.00 - 20.00, la 8.00 - 19.00, su 11.00 - 18.00*/
                    String days1 = separatedFin[0];
                    String[] separatedFin2 = days1.split(" ");
                    String day1 = separatedFin2[0];
                    String open1 = separatedFin2[1];
                    String close1 = separatedFin2[3];
                    System.out.println(close1);
                    if (separatedFin.length > 1){
                        String days2 = separatedFin[1];
                        String[] separatedFin3 = days2.split(" ");
                        String day2 = separatedFin3[0];
                        String open2 = separatedFin3[1];
                        String close2 = separatedFin3[3];
                    }
                    if (separatedFin.length > 2) {
                        String days3 = separatedFin[2];
                        String[] separatedFin4 = days3.split(" ");
                        String day3 = separatedFin4[0];
                        String open3 = separatedFin4[1];
                        String close3 = separatedFin4[3];
                    }

                }
            }
            //////Käydään läpi Viron Smatrpostit///////
            NodeList nListEst = docEst.getDocumentElement().getElementsByTagName("item");
            for (int i=0; i<nListEst.getLength(); i++) {
                Node node = nListEst.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String nameE = element.getElementsByTagName("name").item(0).getTextContent();
                    String locationE = element.getElementsByTagName("country").item(0).getTextContent();
                    String addressE = element.getElementsByTagName("address").item(0).getTextContent();
                    String availabilityE = element.getElementsByTagName("availability").item(0).getTextContent();
                    posts.add(new Smartpost(nameE,locationE, addressE, availabilityE));
                    names.add(nameE);

                    String[] separatedEst = availabilityE.split(";"); /*E-L 10.00-21.00; P 10.00-18.00*/
                    String Edays1 = separatedEst[0];
                    String[] separatedEst2 = Edays1.split(" ");
                    String Eday1 = separatedEst2[0];
                    String Eopen1 = separatedEst2[1];
                    String Eclose1 = separatedEst2[3];
                    if (separatedEst.length > 1){
                        String Edays2 = separatedEst[1];
                        String[] separatedEst3 = Edays2.split(" ");
                        String Eday2 = separatedEst3[0];
                        String Eopen2 = separatedEst3[1];
                        String Eclose2 = separatedEst3[3];
                    }
                    if (separatedEst.length > 2) {
                        String Edays3 = separatedEst[2];
                        String[] separatedEst4 = Edays3.split(" ");
                        String Eday3 = separatedEst4[0];
                        String Eopen3 = separatedEst4[1];
                        String Eclose3 = separatedEst4[3];
                    }


                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e){
            e.printStackTrace();
        } finally {
            System.out.println("############# DONE ##############");
            System.out.println(names.size());
            return names;
        }
    }

    public ArrayList<Smartpost> getInfo(){
        return posts;
    }

}
