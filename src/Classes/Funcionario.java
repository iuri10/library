/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import ClassesDeEntidades.Livro;
import ClassesJPAController.FuncionarioJpaController;
import ClassesJPAController.LivroJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author Guilherme
 */
public class Funcionario {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("libraryPU");

    FuncionarioJpaController funcionariojpa;

    LivroJpaController livrojpa;

    private int idFuncionario;
    private String nome;
    private int cpf;
    private int idade;
    private int telefone;
    private String tipo;
    private String user;
    private String pass;
    private int idBiblioteca;

    public Funcionario(int idFuncionario, String nome, int cpf, int idade, int telefone, String tipo, String user, String pass, int idBiblioteca) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.telefone = telefone;
        this.tipo = tipo;
        this.user = user;
        this.pass = pass;
        this.idBiblioteca = idBiblioteca;
    }

    public Funcionario() {
        livrojpa = new LivroJpaController(emf);
        funcionariojpa = new FuncionarioJpaController(emf);

    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getIdBiblioteca() {
        return idBiblioteca;
    }

    public void setIdBiblioteca(int idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }

    public List<ClassesDeEntidades.Funcionario> login(String user, int pass) {
        List<ClassesDeEntidades.Funcionario> list = null;
        try {

            list = funcionariojpa.login(user, pass);

        } catch (Exception e) {

        }

        return list;
    }

    public void createlivro(Livro p) {
        try {
            livrojpa.create(p);
            JOptionPane.showMessageDialog(null, "Livro inserido com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir livro");
        }

    }

    public void deletelivro(int id) {
        try {
            livrojpa.destroy(id);
            JOptionPane.showMessageDialog(null, "Livro excluido com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro excluir livro");
        }
    }

    public List<Livro> listar(String editora) {
        List<Livro> list = null;
        try {
            list = livrojpa.buscar(editora);
        } catch (Exception e) {

        }

        return list;
    }

    public List<Livro> listartodos() {
        List<Livro> list = null;
        try {
            list = livrojpa.findLivroEntities();
        } catch (Exception e) {
        }
        return list;
    }

    public List<ClassesDeEntidades.Funcionario> listarFuncionarios(String nome) {
        List<ClassesDeEntidades.Funcionario> list = null;
        try {
            list = funcionariojpa.buscarFuncionarioPorNome(nome);
        } catch (Exception e) {
        }
        return list;
    }
    

    public List<ClassesDeEntidades.Funcionario> listartodosfuncionarios() {
        List<ClassesDeEntidades.Funcionario> list = null;
        try {
            list = funcionariojpa.findFuncionarioEntities();
        } catch (Exception e) {
        }
        return list;
    }

}
