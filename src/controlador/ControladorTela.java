package controlador;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import modelo.ModeloCadastro;
import view.TelaDeCadastro;
import view.TelaDeConsulta;

public class ControladorTela {

    private JDesktopPane jDesktop;
    private ArrayList<ModeloCadastro> listaMovimentacoes;

    public ControladorTela(JDesktopPane jDesktop) {
        this.jDesktop = jDesktop;
        listaMovimentacoes = new ArrayList<>();
    }

    public void abrirTelaDeCadastro() {
        jDesktop.removeAll();
        Dimension resolucao = jDesktop.getSize();
        TelaDeCadastro tela = new TelaDeCadastro(listaMovimentacoes);
        tela.setSize(resolucao);
        tela.setLocation(0, 0);
        jDesktop.add(tela);
        tela.setVisible(true);
        jDesktop.revalidate();
        jDesktop.repaint();
    }

    public void abrirTelaDeConsulta() {
        jDesktop.removeAll();
        Dimension resolucao = jDesktop.getSize();
        TelaDeConsulta tela = new TelaDeConsulta(listaMovimentacoes);
        tela.setSize(resolucao);
        tela.setLocation(0, 0);
        jDesktop.add(tela);
        tela.setVisible(true);
        jDesktop.revalidate();
        jDesktop.repaint();
    }
}
