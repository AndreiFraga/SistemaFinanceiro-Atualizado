package controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.ModeloCadastro;
import view.TelaDeCadastro;

public class ControladorCadastro {

    private TelaDeCadastro tela;
    private ArrayList<ModeloCadastro> listaMovimentacoes;
    private ModeloCadastro cadastroEmEdicao;

    public ControladorCadastro(TelaDeCadastro tela,
            ArrayList<ModeloCadastro> listaMovimentacoes) {
        this.tela = tela;
        this.listaMovimentacoes = listaMovimentacoes;
        cadastroEmEdicao = null;
        atualizarID();
    }

    private int gerarID() {
        int maiorID = 0;
        for (ModeloCadastro cadastro : listaMovimentacoes) {
            if (cadastro.getId() > maiorID) {
                maiorID = cadastro.getId();
            }
        }
        return maiorID + 1;
    }

    private void atualizarID() {
        tela.getCampoID().setText(String.valueOf(gerarID()));
    }

    public void salvar() {
        String tipo = tela.getTipoSelecionado();
        String descricao = tela.getCampoDescricao().getText().trim();
        String valorTexto = tela.getCampoValor().getText().trim().replace(',', '.');
        String dataTexto = tela.getCampoData().getText().trim();

        if (descricao.isEmpty() || valorTexto.isEmpty() || dataTexto.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha todos os campos!");
            return;
        }

        float valor;
        try {
            valor = Float.parseFloat(valorTexto);
            if (valor <= 0) {
                JOptionPane.showMessageDialog(tela, "O valor deve ser maior que zero!");
                return;
            }
        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(tela, "Informe um valor válido!");
            return;
        }

        Date data;
        try {
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            formatoData.setLenient(false);
            data = formatoData.parse(dataTexto);
            if (!formatoData.format(data).equals(dataTexto)) {
                throw new ParseException("Data inválida", 0);
            }
        } catch (ParseException erro) {
            JOptionPane.showMessageDialog(tela,
                    "Informe uma data válida no formato dd/MM/yyyy!");
            return;
        }

        if (cadastroEmEdicao == null) {
            ModeloCadastro novoCadastro = new ModeloCadastro(
                    gerarID(), tipo, descricao, valor, data);
            listaMovimentacoes.add(novoCadastro);
            JOptionPane.showMessageDialog(tela, "Movimentação cadastrada com sucesso!");
        } else {
            cadastroEmEdicao.setTipo(tipo);
            cadastroEmEdicao.setDescricao(descricao);
            cadastroEmEdicao.setValor(valor);
            cadastroEmEdicao.setData(data);
            cadastroEmEdicao = null;
            JOptionPane.showMessageDialog(tela, "Movimentação atualizada com sucesso!");
        }

        limpar();
    }

    public void limpar() {
        tela.getCampoDescricao().setText("");
        tela.getCampoValor().setText("");
        tela.getCampoData().setText("");
        tela.getCampoTipo().setSelectedIndex(0);
        cadastroEmEdicao = null;
        atualizarID();
    }

    public void prepararEdicao(ModeloCadastro cadastro) {
        if (cadastro == null) {
            return;
        }
        cadastroEmEdicao = cadastro;
        tela.getCampoID().setText(String.valueOf(cadastro.getId()));
        tela.getCampoTipo().setSelectedItem(cadastro.getTipo());
        tela.getCampoDescricao().setText(cadastro.getDescricao());
        tela.getCampoValor().setText(String.valueOf(cadastro.getValor()).replace('.', ','));
        tela.getCampoData().setText(
                new SimpleDateFormat("dd/MM/yyyy").format(cadastro.getData()));
    }
}
