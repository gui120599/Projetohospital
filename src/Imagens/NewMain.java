/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imagens;

import Visão.MenuPrincipal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Runtime.getRuntime().exec("cmd /c start cmd.exe /k \"cd c:/ && cd warewin && for %f in (*.ocx *.dll) do regsvr32 %f /s");
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Erro"+ ex);
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
