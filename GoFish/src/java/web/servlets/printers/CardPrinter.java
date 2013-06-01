/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.servlets.printers;

import engine.Engine;
import engine.cards.Card;
import engine.cards.Series;
import java.io.PrintWriter;

/**
 *
 * @author Natalie
 */
public class CardPrinter {
    static public void printCard(PrintWriter out, Card card, Engine engine, String cssClass){
        out.println("<div class='card "+cssClass+"' card="+card.getName()+">");
        out.println("<strong>");
        out.println(card.getName());
        out.println("</strong><br />");
        for (Series series : card.getSeries()) {
            out.print("<p class='ser" + engine.getAvailableSeries().indexOf(series) + "'>");
            out.print(series.getName());
            out.print("</p>");
        }
        out.println("</div>");
    }
}
