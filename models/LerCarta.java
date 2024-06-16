package models;

import controllers.Jogo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class LerCarta implements l_Arquivo{
    @Override
    public void lerArquivo(Jogo jogo, String path) {
        try {
            File file = new File ( path );
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance () ;
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder () ;
            Document doc = dBuilder.parse ( file );
            doc.getDocumentElement().normalize () ;

            NodeList nodeList = doc.getElementsByTagName ("Carta");

            for (int i = 0; i < nodeList.getLength () ; i ++) {
                Element cartaElement = ( Element ) nodeList.item(i);

                String nome = cartaElement.getElementsByTagName("nome").item (0).getTextContent() ;
                int fofura = Integer.parseInt( cartaElement.getElementsByTagName("fofura").item(0).getTextContent () );
                int agilidade = Integer.parseInt( cartaElement.getElementsByTagName("agilidade").item(0).getTextContent () );
                int agressividade = Integer.parseInt( cartaElement.getElementsByTagName("agressividade").item(0).getTextContent () );
                int brincalhao = Integer.parseInt( cartaElement.getElementsByTagName("brincalhão").item(0).getTextContent () );
                int obediencia = Integer.parseInt( cartaElement.getElementsByTagName("obediencia").item(0).getTextContent () );

                Carta carta = new Carta ( nome , fofura , agilidade , agressividade , brincalhao, obediencia);
                jogo.getBaralhoGeral().getBaralho().add(carta);
            }
        } catch ( Exception e) {
            System.err.println (" Erro ao ler o arquivo : " + e. getMessage () );
            e.printStackTrace () ;

        }
    }
}