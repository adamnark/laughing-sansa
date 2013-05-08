/*
 */
package swing.utils.playeritem;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author adam
 */
public class PlayerItemRenderer extends DefaultListCellRenderer{ 
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); //To change body of generated methods, choose Tools | Templates.
        
        if(value instanceof PlayerItem){
            PlayerItem playerItem = (PlayerItem) value;
            this.setText(playerItem.getName());
            this.setIcon(playerItem.getPlayerType().getImageIcon());
        }
        
        
        
        return this;
    }
    
}
