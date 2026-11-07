package controlador;

import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ModeloCadastro;
import modelo.ModeloConsulta;
import view.TelaDeConsulta;

public class ControladorConsulta {

    private TelaDeConsulta tela;
    private ArrayList<ModeloCadastro> listaMovimentacoes;

    public ControladorConsulta(TelaDeConsulta tela,
            ArrayList<ModeloCadastro> listaMovimentacoes) {
        this.tela = tela;
        this.listaMovimentacoes = listaMovimentacoes;
        carregarTabela();
    }

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tela.getTabela().getModel();
        modelo.setRowCount(0);
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

        for (ModeloCadastro cadastro : listaMovimentacoes) {
            modelo.addRow(new Object[]{
                cadastro.getId(), cadastro.getTipo(), cadastro.getDescricao(),
                cadastro.getValor(), formatoData.format(cadastro.getData())
            });
        }
        calcularTotais();
    }

    private void calcularTotais() {
        float totalReceitas = 0;
        float totalDespesas = 0;

        for (ModeloCadastro cadastro : listaMovimentacoes) {
            if (cadastro.getTipo().equalsIgnoreCase("Receita")) {
                totalReceitas += cadastro.getValor();
            } else if (cadastro.getTipo().equalsIgnoreCase("Despesa")) {
                totalDespesas += cadastro.getValor();
            }
        }

        ModeloConsulta consulta = new ModeloConsulta(
                totalDespesas, totalReceitas, totalReceitas - totalDespesas);
        NumberFormat moeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        tela.getTotalDespesas().setText(moeda.format(consulta.getTotalDespesas()));
        tela.getTotalReceitas().setText(moeda.format(consulta.getTotalReceitas()));
        tela.getSaldo().setText(moeda.format(consulta.getSaldo()));
    }

    public ModeloCadastro obterSelecionado() {
        int linha = tela.getTabela().getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(tela, "Selecione uma movimentação na tabela!");
            return null;
        }

        int id = Integer.parseInt(tela.getTabela().getValueAt(linha, 0).toString());
        for (ModeloCadastro cadastro : listaMovimentacoes) {
            if (cadastro.getId() == id) {
                return cadastro;
            }
        }
        return null;
    }

    public void excluirSelecionado() {
        ModeloCadastro cadastro = obterSelecionado();
        if (cadastro == null) {
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(tela,
                "Deseja realmente excluir esta movimentação?",
                "Confirmar exclusão", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            listaMovimentacoes.remove(cadastro);
            carregarTabela();
            JOptionPane.showMessageDialog(tela, "Movimentação excluída com sucesso!");
        }
    }
}
