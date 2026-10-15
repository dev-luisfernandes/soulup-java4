package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.entities.Usuario;
import br.com.fiap.excecoes.ExcecoesConexao;
import br.com.fiap.excecoes.ExcecoesRegraNegocio;

import java.util.ArrayList;

public class UsuarioBo {

    private UsuarioDao usuarioDao;

    // Selecionar todos os usuários
    public ArrayList<Usuario> selecionarBo() throws ExcecoesConexao {
        try {
            usuarioDao = new UsuarioDao();
            // Regra de negocios
            return usuarioDao.selecionar();
        } catch (Exception e) {
            throw new ExcecoesConexao(e);
        }
    }

    // Buscar usuário por CPF
    public Usuario buscarPorCpfBo(String cpf) throws ExcecoesConexao, ExcecoesRegraNegocio {
        // Regra de negocios: o CPF não pode ser vazio
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new ExcecoesRegraNegocio("O CPF não pode ser vazio.");
        }

        try {
            usuarioDao = new UsuarioDao();
            return usuarioDao.buscarPorCpf(cpf);
        } catch (Exception e) {
            throw new ExcecoesConexao(e);
        }
    }

    // Inserir usuário
    public void inserirBo(Usuario usuario) throws ExcecoesConexao, ExcecoesRegraNegocio {
        // Regra de negocios: validar os dados antes de ir ao banco
        validarUsuario(usuario);

        try {
            usuarioDao = new UsuarioDao();

            // Regra de negocios: não pode ter dois usuários com o mesmo CPF
            if (usuarioDao.buscarPorCpf(usuario.getCPF()) != null) {
                throw new ExcecoesRegraNegocio("Já existe um usuário cadastrado com este CPF.");
            }

            // atualiza o tier baseado nos pontos antes de inserir
            usuario.atualizarTier();

            usuarioDao.inserir(usuario);
        } catch (ExcecoesRegraNegocio e) {
            throw e;
        } catch (Exception e) {
            throw new ExcecoesConexao(e);
        }
    }

    // Atualizar usuário
    public void atualizarBo(Usuario usuario) throws ExcecoesConexao, ExcecoesRegraNegocio {
        validarUsuario(usuario);

        try {
            usuarioDao = new UsuarioDao();

            // Regra de negocios: só atualiza quem existe
            if (usuarioDao.buscarPorCpf(usuario.getCPF()) == null) {
                throw new ExcecoesRegraNegocio("Usuário não encontrado para o CPF informado.");
            }

            // recalcula o tier caso os pontos tenham mudado
            usuario.atualizarTier();

            usuarioDao.atualizar(usuario);
        } catch (ExcecoesRegraNegocio e) {
            throw e;
        } catch (Exception e) {
            throw new ExcecoesConexao(e);
        }
    }

    // Deletar usuário por CPF
    public void deletarBo(String cpf) throws ExcecoesConexao, ExcecoesRegraNegocio {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new ExcecoesRegraNegocio("O CPF não pode ser vazio.");
        }

        try {
            usuarioDao = new UsuarioDao();

            // Regra de negocios: só deleta quem existe
            if (usuarioDao.buscarPorCpf(cpf) == null) {
                throw new ExcecoesRegraNegocio("Usuário não encontrado para o CPF informado.");
            }

            usuarioDao.deletar(cpf);
        } catch (ExcecoesRegraNegocio e) {
            throw e;
        } catch (Exception e) {
            throw new ExcecoesConexao(e);
        }
    }

    // Validações dos dados do usuário
    private void validarUsuario(Usuario usuario) throws ExcecoesRegraNegocio {
        if (usuario == null) {
            throw new ExcecoesRegraNegocio("Os dados do usuário não foram informados.");
        }

        if (usuario.getCPF() == null || usuario.getCPF().length() != 11) {
            throw new ExcecoesRegraNegocio("O CPF deve ter 11 números.");
        }

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new ExcecoesRegraNegocio("O nome é obrigatório.");
        }

        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
            throw new ExcecoesRegraNegocio("E-mail inválido.");
        }

        if (usuario.getCEP() <= 0 || usuario.getCEP() > 99999999) {
            throw new ExcecoesRegraNegocio("CEP inválido.");
        }

        if (usuario.getPontos() < 0) {
            throw new ExcecoesRegraNegocio("Os pontos não podem ser negativos.");
        }
    }
}
