/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package torrehanoi;

/**
 *
 * @author XNA_3
 */
public class Disco extends javax.swing.JPanel {
    private int Tamanho;
    public Disco(int tam) {
        Tamanho = tam;
        setSize((int) (Tamanho*6 + 50), 9);
    }
    public int getTamanho() {
        return Tamanho;
    }
}
