package examples.layout;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author blecherl
 */
public class Square extends JPanel implements ActionListener {

    int col;
    int row;
    private JButton jButton;

    public Square(int col, int row) {
        this.col = col;
        this.row = row;

        jButton = new JButton(row + ":" + col);
        jButton.addActionListener(this);
        this.add(jButton);
        this.setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        int result = JOptionPane.showConfirmDialog(this, "Want to press me?", "Pressed", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
//                showMessageDialog(this, "pressed on " + row + ":" + col);
//        if (result == JOptionPane.OK_OPTION){
            this.removeAll();
            JLabel jLabel = new JLabel("HOT SWAP");
            if (col == 5) {
                jLabel.setForeground(Color.RED);
            }
//            jLabel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                Square.this.removeAll();
//                Square.this.add(jButton);
//            }
//        });
            this.add(jLabel);
            this.invalidate();
            this.validate();
            this.repaint();
//            this.doLayout();
            }

//    }
}
