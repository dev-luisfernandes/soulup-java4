package br.com.fiap.entities;

public class Usuario {

    private int usuarioId;
    private String CPF;
    private String nome;
    private int CEP;
    private double pontos;
    private String celular;
    private String email;
    private String dataNascimento;
    private String situacao;
    private String pais;
    private String nomeUsuario;
    private String tier;

    public Usuario() {
        atualizarTier();
    }

    public Usuario(int usuarioId, String CPF, String nome, int CEP, double pontos, String celular, String email, String dataNascimento, String situacao, String pais, String nomeUsuario, String tier) {
        this.usuarioId = usuarioId;
        this.CPF = CPF;
        this.nome = nome;
        this.CEP = CEP;
        this.pontos = pontos;
        this.celular = celular;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.situacao = situacao;
        this.pais = pais;
        this.nomeUsuario = nomeUsuario;
        this.tier = tier;
    }

    // DEFINIÇÃO DE QUANTOS PONTOS É CADA TIER
    public void atualizarTier() {
        if (this.pontos >= 5550) {
            this.tier = "Ouro";
        } else if (this.pontos >= 3000) {
            this.tier = "Prata";
        } else {
            this.tier = "Verde";
        }
    }


    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCEP() {
        return CEP;
    }

    public void setCEP(int CEP) {
        this.CEP = CEP;
    }

    public double getPontos() {
        return pontos;
    }

    public void setPontos(double pontos) {
        this.pontos = pontos;
        atualizarTier();
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuarioId=" + usuarioId +
                ", CPF='" + CPF + '\'' +
                ", nome='" + nome + '\'' +
                ", pontos=" + pontos +
                ", celular='" + celular + '\'' +
                ", email='" + email + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", situacao='" + situacao + '\'' +
                ", pais='" + pais + '\'' +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", tier='" + tier + '\'' +
                '}';
    }
}