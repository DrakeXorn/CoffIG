package userInterface.frames;

import javax.swing.*;

public class AboutFrame extends JFrame {
    private JLabel infoLabel;

    public AboutFrame() {
        setSize(800, 500);

        String info = "<html>\n" +
                "<head>\n" +
                "\t<meta charset=\"utf-8\">\n" +
                "\t<style type=\"text/css\">\n" +
                "\t\tbody {\n" +
                "\t\t\tbackground-color: #292929;\n" +
                "\t\t\tfont-family: Verdana;\n" +
                "\t\t\ttext-align: center;\n" +
                "\t\t\tcolor: lightgray;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\th1 {\n" +
                "\t\t\ttext-align: center;\n" +
                "\t\t\tfont-size: 150%;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\ttable {\n" +
                "\t\t\tmargin: auto;\n" +
                "\t\t\twidth: 1000px;\n" +
                "\t\t\ttext-align: center;\n" +
                "\t\t\tborder-collapse: collapse;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\ttr {\n" +
                "\t\t\tborder: 1px solid lightgray;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\t.smallCaps {\n" +
                "\t\t\tfont-variant: small-caps;\n" +
                "\t\t}\n" +
                "\t</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<h1>À propos</h1>\n" +
                "\t<table>\n" +
                "\t\t<caption>Les développeurs de cette application</caption>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th class=\"smallCaps\">Aurélie Bodart</th>\n" +
                "\t\t\t<th class=\"smallCaps\">Christophe Bernard</th>\n" +
                "\t\t\t<th class=\"smallCaps\">Maxime Forain</th>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td><img src=\"Aurélie.png\"></td>\n" +
                "\t\t\t<td><img src=\"../../../resources/Christophe.png\"></td>\n" +
                "\t\t\t<td><img src=\"Maxime.png\"></td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td>Celle qui embrouille l'esprit des gens</td>\n" +
                "\t\t\t<td>L'insomniaque qui code</td>\n" +
                "\t\t\t<td>L'artiste</td>\n" +
                "\t\t</tr>\n" +
                "\t</table>\n" +
                "\t<p>Fait avec ❤ sur JetBrains IntelliJ IDEA 2020.1 avec le JDK 14.</p>\n" +
                "</body>\n" +
                "</html>";

        infoLabel = new JLabel(info);
        add(infoLabel);
        setVisible(true);
    }
}
