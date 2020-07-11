
import btl2.GUI.Home;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author DELL
 */
public class BTL2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Home.getInstance().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Home.getInstance().pack();
        Home.getInstance().setSize(1017,700);
        Home.getInstance().setLocationRelativeTo(null);
        Home.getInstance().setVisible(true);
    }
}
