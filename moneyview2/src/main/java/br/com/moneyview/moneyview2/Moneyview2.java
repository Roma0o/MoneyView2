package br.com.moneyview.moneyview2;

import br.com.moneyview.gui.TelaLogin;
import java.time.LocalDate;
import java.util.List;
import br.com.moneyview.modelo.Categoria;
import br.com.moneyview.modelo.Transacao;
import br.com.moneyview.modelo.Usuario;
import br.com.moneyview.servicos.CategoriaServicos;
import br.com.moneyview.servicos.TransacaoServicos;
import br.com.moneyview.servicos.UsuarioServicos;

public class Moneyview2 {

    public static void main(String[] args) {
        TelaLogin login = new TelaLogin();
        login.setVisible(true);
        }
    //Metodo de testes pra testar as funcoes aqui
    public static void testeFuncoes(){
        UsuarioServicos usuarioServicos = new UsuarioServicos();
        CategoriaServicos categoriaServicos = new CategoriaServicos();
        TransacaoServicos transacaoServicos = new TransacaoServicos();
        
        String nome = "João da Silva";
        String email = "joao@email.com";
        String senha = "1234";
        
        System.out.println("== REGISTRO DE USUÁRIO ==");
        boolean registrado = usuarioServicos.registrarUsuario(nome, email, senha);
        
        if (!registrado) {
            System.out.println("Registro falhou ou usuario já existe");
        }else{
            System.out.println("Usuario registrado com sucesso");
        }
        
        System.out.println("\n== LOGIN ==");
        Usuario usuario = usuarioServicos.login(email, senha);
        
        if (usuario == null) {
            System.out.println("Login falhou. Encerrando...");
            return;
        } else {
            System.out.println("Login realizado com sucesso. Bem-vindo, " + usuario.getNome());
        }
        
        System.out.println("\n== CATEGORIAS ==");
        categoriaServicos.adicionarCategoria("Alimentação");
        categoriaServicos.adicionarCategoria("Salário");
        
        List<Categoria> categorias = categoriaServicos.listarCategorias();
        Categoria catAlimentacao = categorias.stream()
                .filter(c -> c.getNome().equals("Alimentação"))
                .findFirst().orElse(null);
        Categoria catSalario = categorias.stream()
                .filter(c -> c.getNome().equals("Salário"))
                .findFirst().orElse(null);
        
        if (catAlimentacao == null || catSalario == null) {
            System.out.println("Erro ao recuperar categorias. Encerrando...");
            return;
        }
        
         System.out.println("Categorias registradas com sucesso.");

        System.out.println("\n== TRANSAÇÕES ==");
        Transacao t1 = new Transacao(); //("gasto", 50.00, "Pizza com amigos", LocalDate.now(), catAlimentacao, usuario);
        t1.setTipo("Despesa");
        t1.setValor(50.00);
        t1.setDescricao("Pizza com amigos");
        t1.setData(LocalDate.now());
        t1.setCategoria(catAlimentacao);
        t1.setUsuario(usuario);
        
        Transacao t2 = new Transacao(); //("ganho", 3200.00, "Salário mensal", LocalDate.now(), catSalario, usuario);
        t2.setTipo("Ganho");
        t2.setValor(3200.00);
        t2.setDescricao("Salário mensal");
        t2.setData(LocalDate.now());
        t2.setCategoria(catSalario);
        t2.setUsuario(usuario);
        
        transacaoServicos.registrarTransacao(t1);
        transacaoServicos.registrarTransacao(t2);
        System.out.println("Transações registradas.");
        
        System.out.println("\n== LISTANDO TRANSACOES DO USUÁRIO ==");
        List<Transacao> transacoes = transacaoServicos.listarTransacoesDoUsuario(usuario.getId());
        
        for (Transacao t : transacoes) {
            System.out.printf("[%s] R$ %.2f - %s (%s) em %s%n",
                    t.getTipo().toUpperCase(),
                    t.getValor(),
                    t.getDescricao(),
                    t.getCategoria().getNome(),
                    t.getData());
    }
    
    }

}
