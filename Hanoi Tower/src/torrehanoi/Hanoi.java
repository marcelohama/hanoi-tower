/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package torrehanoi;

import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;

/**
 *
 * @author XNA_3
 */
public class Hanoi extends Thread {
    
    private Stack<Disco> origem;
    private Stack<Disco> destino;
    private Stack<Disco> auxiliar;
    private JLabel iteracoes;
    private int movs = 0;
    private JSpinner spn;
    private JButton bt;
    private int velocidade = 1;

    public Hanoi(int n, Stack<Disco> origem, Stack<Disco> destino, Stack<Disco> auxiliar, JLabel iteracoes, JButton bt, JSpinner spn) {
        this.origem = origem;
        this.destino = destino;
        this.auxiliar = auxiliar;
        this.iteracoes = iteracoes;
        this.bt = bt;
        this.spn = spn;
    }
    public void resolver(int n, Stack<Disco> origem, Stack<Disco> destino, Stack<Disco> auxiliar) {
        if(n == 1) {
            moveDisco(origem.peek(),destino);
            destino.push(origem.pop());
            iteracoes.setText("ITERAÇÕES: "+String.valueOf(++movs));
        }
        else {
            resolver(n-1, origem, auxiliar, destino);
            moveDisco(origem.peek(),destino);
            destino.push(origem.pop());
            iteracoes.setText("ITERAÇÕES: "+String.valueOf(++movs));
            resolver(n-1, auxiliar, destino, origem);
        }
    }
    
    private void moveDisco(Disco d, Stack<Disco> dest) {
        try {
            int posx = d.getX();
            int posy = d.getY();
            // levanta o disco
            for(int i=posy;i>100;i--) {
                d.setLocation(posx, i);
                sleep(velocidade);
            }
            // move o disco para a posição de destino
            posx = d.getX();
            posy = d.getY();
            if(dest == this.auxiliar) {
                while(posx < 375-d.getTamanho()*3) {
                    d.setLocation(posx++, posy);
                    sleep(velocidade);
                }
            }
            else if(dest == this.destino) {
                if(posx > 230) {
                    while(posx != 230-d.getTamanho()*3) {
                        d.setLocation(posx--, posy);
                        sleep(velocidade);
                    }
                }
                else {
                    while(posx != 230-d.getTamanho()*3) {
                        d.setLocation(posx++, posy);
                        sleep(velocidade);
                    }
                }
            }
            else {
                while(posx > 85-d.getTamanho()*3) {
                    d.setLocation(posx--, posy);
                    sleep(velocidade);
                }
            }
            // abaixa o disco
            posx = d.getX();
            posy = d.getY();
            for(int i=100;i<=260-(dest.size())*10;i++) {
                d.setLocation(posx, i);
                sleep(velocidade);
            }
        }
        catch (InterruptedException e) {
        }
    }
    
    @Override
    public void run() {
        super.run();
        resolver(origem.size(), origem, destino, auxiliar);
        bt.setEnabled(true);
        spn.setEnabled(true);
    }
}
