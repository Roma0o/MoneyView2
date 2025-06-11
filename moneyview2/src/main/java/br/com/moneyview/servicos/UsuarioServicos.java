package br.com.moneyview.servicos;

import br.com.moneyview.modelo.Usuario;
import br.com.moneyview.repositorio.UsuarioDAO;
import br.com.moneyview.util.CriptografiaUtils;

public class UsuarioServicos {
    private final UsuarioDAO usuarioDAO;
    
    public UsuarioServicos(){
        this.usuarioDAO = new UsuarioDAO();
    }
    
    public boolean registrarUsuario(String nome, String email, String senha) {
        if (nome.isBlank() || email.isBlank() || senha.isBlank()) {
            System.out.println("Todos os campos são obrigatórios.");
            return false;
        }
        if (!emailValido(email)) {
            System.out.println("Email invalido");
            return false;
        }
        if (usuarioDAO.emailJaExiste(email)) {
            System.out.println("Ja existe um usuario com esse email");
            return false;
        }
        
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(nome);
        novoUsuario.setEmail(email);
        novoUsuario.setSenha(CriptografiaUtils.encriptarMD5(senha));

        return usuarioDAO.salvar(novoUsuario);
    }

    public Usuario login(String email, String senha) {
        if (email.isBlank() || senha.isBlank()) {
            System.out.println("Email e senha são obrigatórios.");
            return null;
        }
        
        String senhaEncriptada = CriptografiaUtils.encriptarMD5(senha);
        return usuarioDAO.buscarPorEmailESenha(email, senhaEncriptada);
    }
    
    private boolean emailValido(String email) {
    String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
    return email.matches(regex);
}   
    
}
