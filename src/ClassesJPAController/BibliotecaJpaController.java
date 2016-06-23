/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesJPAController;

import ClassesDeEntidades.Biblioteca;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ClassesDeEntidades.Livro;
import java.util.ArrayList;
import java.util.List;
import ClassesDeEntidades.Funcionario;
import ClassesJPAController.exceptions.IllegalOrphanException;
import ClassesJPAController.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

/**
 *
 * @author Guilherme
 */
public class BibliotecaJpaController implements Serializable {

    public BibliotecaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Biblioteca biblioteca) {
        if (biblioteca.getLivroList() == null) {
            biblioteca.setLivroList(new ArrayList<Livro>());
        }
        if (biblioteca.getFuncionarioList() == null) {
            biblioteca.setFuncionarioList(new ArrayList<Funcionario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Livro> attachedLivroList = new ArrayList<Livro>();
            for (Livro livroListLivroToAttach : biblioteca.getLivroList()) {
                livroListLivroToAttach = em.getReference(livroListLivroToAttach.getClass(), livroListLivroToAttach.getIdLivro());
                attachedLivroList.add(livroListLivroToAttach);
            }
            biblioteca.setLivroList(attachedLivroList);
            List<Funcionario> attachedFuncionarioList = new ArrayList<Funcionario>();
            for (Funcionario funcionarioListFuncionarioToAttach : biblioteca.getFuncionarioList()) {
                funcionarioListFuncionarioToAttach = em.getReference(funcionarioListFuncionarioToAttach.getClass(), funcionarioListFuncionarioToAttach.getIdFuncionario());
                attachedFuncionarioList.add(funcionarioListFuncionarioToAttach);
            }
            biblioteca.setFuncionarioList(attachedFuncionarioList);
            em.persist(biblioteca);
            for (Livro livroListLivro : biblioteca.getLivroList()) {
                Biblioteca oldIdBibliotecaOfLivroListLivro = livroListLivro.getIdBiblioteca();
                livroListLivro.setIdBiblioteca(biblioteca);
                livroListLivro = em.merge(livroListLivro);
                if (oldIdBibliotecaOfLivroListLivro != null) {
                    oldIdBibliotecaOfLivroListLivro.getLivroList().remove(livroListLivro);
                    oldIdBibliotecaOfLivroListLivro = em.merge(oldIdBibliotecaOfLivroListLivro);
                }
            }
            for (Funcionario funcionarioListFuncionario : biblioteca.getFuncionarioList()) {
                Biblioteca oldIdBibliotecaOfFuncionarioListFuncionario = funcionarioListFuncionario.getIdBiblioteca();
                funcionarioListFuncionario.setIdBiblioteca(biblioteca);
                funcionarioListFuncionario = em.merge(funcionarioListFuncionario);
                if (oldIdBibliotecaOfFuncionarioListFuncionario != null) {
                    oldIdBibliotecaOfFuncionarioListFuncionario.getFuncionarioList().remove(funcionarioListFuncionario);
                    oldIdBibliotecaOfFuncionarioListFuncionario = em.merge(oldIdBibliotecaOfFuncionarioListFuncionario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Biblioteca biblioteca) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Biblioteca persistentBiblioteca = em.find(Biblioteca.class, biblioteca.getIdBiblioteca());
            List<Livro> livroListOld = persistentBiblioteca.getLivroList();
            List<Livro> livroListNew = biblioteca.getLivroList();
            List<Funcionario> funcionarioListOld = persistentBiblioteca.getFuncionarioList();
            List<Funcionario> funcionarioListNew = biblioteca.getFuncionarioList();
            List<String> illegalOrphanMessages = null;
            for (Livro livroListOldLivro : livroListOld) {
                if (!livroListNew.contains(livroListOldLivro)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Livro " + livroListOldLivro + " since its idBiblioteca field is not nullable.");
                }
            }
            for (Funcionario funcionarioListOldFuncionario : funcionarioListOld) {
                if (!funcionarioListNew.contains(funcionarioListOldFuncionario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Funcionario " + funcionarioListOldFuncionario + " since its idBiblioteca field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Livro> attachedLivroListNew = new ArrayList<Livro>();
            for (Livro livroListNewLivroToAttach : livroListNew) {
                livroListNewLivroToAttach = em.getReference(livroListNewLivroToAttach.getClass(), livroListNewLivroToAttach.getIdLivro());
                attachedLivroListNew.add(livroListNewLivroToAttach);
            }
            livroListNew = attachedLivroListNew;
            biblioteca.setLivroList(livroListNew);
            List<Funcionario> attachedFuncionarioListNew = new ArrayList<Funcionario>();
            for (Funcionario funcionarioListNewFuncionarioToAttach : funcionarioListNew) {
                funcionarioListNewFuncionarioToAttach = em.getReference(funcionarioListNewFuncionarioToAttach.getClass(), funcionarioListNewFuncionarioToAttach.getIdFuncionario());
                attachedFuncionarioListNew.add(funcionarioListNewFuncionarioToAttach);
            }
            funcionarioListNew = attachedFuncionarioListNew;
            biblioteca.setFuncionarioList(funcionarioListNew);
            biblioteca = em.merge(biblioteca);
            for (Livro livroListNewLivro : livroListNew) {
                if (!livroListOld.contains(livroListNewLivro)) {
                    Biblioteca oldIdBibliotecaOfLivroListNewLivro = livroListNewLivro.getIdBiblioteca();
                    livroListNewLivro.setIdBiblioteca(biblioteca);
                    livroListNewLivro = em.merge(livroListNewLivro);
                    if (oldIdBibliotecaOfLivroListNewLivro != null && !oldIdBibliotecaOfLivroListNewLivro.equals(biblioteca)) {
                        oldIdBibliotecaOfLivroListNewLivro.getLivroList().remove(livroListNewLivro);
                        oldIdBibliotecaOfLivroListNewLivro = em.merge(oldIdBibliotecaOfLivroListNewLivro);
                    }
                }
            }
            for (Funcionario funcionarioListNewFuncionario : funcionarioListNew) {
                if (!funcionarioListOld.contains(funcionarioListNewFuncionario)) {
                    Biblioteca oldIdBibliotecaOfFuncionarioListNewFuncionario = funcionarioListNewFuncionario.getIdBiblioteca();
                    funcionarioListNewFuncionario.setIdBiblioteca(biblioteca);
                    funcionarioListNewFuncionario = em.merge(funcionarioListNewFuncionario);
                    if (oldIdBibliotecaOfFuncionarioListNewFuncionario != null && !oldIdBibliotecaOfFuncionarioListNewFuncionario.equals(biblioteca)) {
                        oldIdBibliotecaOfFuncionarioListNewFuncionario.getFuncionarioList().remove(funcionarioListNewFuncionario);
                        oldIdBibliotecaOfFuncionarioListNewFuncionario = em.merge(oldIdBibliotecaOfFuncionarioListNewFuncionario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = biblioteca.getIdBiblioteca();
                if (findBiblioteca(id) == null) {
                    throw new NonexistentEntityException("The biblioteca with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Biblioteca biblioteca;
            try {
                biblioteca = em.getReference(Biblioteca.class, id);
                biblioteca.getIdBiblioteca();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The biblioteca with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Livro> livroListOrphanCheck = biblioteca.getLivroList();
            for (Livro livroListOrphanCheckLivro : livroListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Biblioteca (" + biblioteca + ") cannot be destroyed since the Livro " + livroListOrphanCheckLivro + " in its livroList field has a non-nullable idBiblioteca field.");
            }
            List<Funcionario> funcionarioListOrphanCheck = biblioteca.getFuncionarioList();
            for (Funcionario funcionarioListOrphanCheckFuncionario : funcionarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Biblioteca (" + biblioteca + ") cannot be destroyed since the Funcionario " + funcionarioListOrphanCheckFuncionario + " in its funcionarioList field has a non-nullable idBiblioteca field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(biblioteca);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Biblioteca> findBibliotecaEntities() {
        return findBibliotecaEntities(true, -1, -1);
    }

    public List<Biblioteca> findBibliotecaEntities(int maxResults, int firstResult) {
        return findBibliotecaEntities(false, maxResults, firstResult);
    }

    private List<Biblioteca> findBibliotecaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Biblioteca.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Biblioteca findBiblioteca(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Biblioteca.class, id);
        } finally {
            em.close();
        }
    }

    public int getBibliotecaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Biblioteca> rt = cq.from(Biblioteca.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Biblioteca> buscaID(String nome) {
        EntityManager em = getEntityManager();
        List<Biblioteca> resultlist = null;
        try {
            String query = "select c from Biblioteca c where c.nome = :nome";
            TypedQuery<Biblioteca> list = em.createQuery(query, Biblioteca.class);
            list.setParameter("nome", nome);
            resultlist = list.getResultList();
            em.close();
            if (resultlist.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Biblioteca não cadastrada");
            }

        } catch (Exception e) {
            System.err.println("Erro ao buscar id");
        }
        return resultlist;
    }
    
}
