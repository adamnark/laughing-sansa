/*
 */
package web.servlets.printers;

import engine.factory.PlayerItem;

/**
 *
 * @author adam
 */
public class PlayerItemPrinter {
    public static String makeImgTag(PlayerItem pi){
        String path = pi.isHuman() ? "img/human.png" : "img/computer.png";
        return "<img src='" + path + "'>";
    }

}
