/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesJPAController;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ClassesDeEntidades.Biblioteca;
import ClassesDeEntidades.Funcionario;
import ClassesJPAController.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

/**
 *
 * @author Guilherme
 */
public class FuncionarioJpaController implements Serializable {

    public FuncionarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Funcionario funcionario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Biblioteca idBiblioteca = funcionario.getIdBiblioteca();
            if (idBiblioteca != null) {
                idBiblioteca = em.getReference(idBiblioteca.getClass(), idBiblioteca.getIdBiblioteca());
                funcionario.setIdBiblioteca(idBiblioteca);
            }
            em.persist(funcionario);
            if (idBiblioteca != null) {
                idBiblioteca.getFuncionarioList().add(funcionario);
                idBiblioteca = em.merge(idBiblioteca);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Funcionario funcionario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionario persistentFuncionario = em.find(Funcionario.class, funcionario.getIdFuncionario());
            Biblioteca idBibliotecaOld = persistentFuncionario.getIdBiblioteca();
            Biblioteca idBibliotecaNew = funcionario.getIdBiblioteca();
            if (idBibliotecaNew != null) {
                idBibliotecaNew = em.getReference(idBibliotecaNew.getClass(), idBibliotecaNew.getIdBiblioteca());
                funcionario.setIdBiblioteca(idBibliotecaNew);
            }
            funcionario = em.merge(funcionario);
            if (idBibliotecaOld != null && !idBibliotecaOld.equals(idBibliotecaNew)) {
                idBibliotecaOld.getFuncionarioList().remove(funcionario);
                idBibliotecaOld = em.merge(idBibliotecaOld);
            }
            if (idBibliotecaNew != null && !idBibliotecaNew.equals(idBibliotecaOld)) {
                idBibliotecaNew.getFuncionarioList().add(funcionario);
                idBibliotecaNew = em.merge(idBibliotecaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = funcionario.getIdFuncionario();
                if (findFuncionario(id) == null) {
                    throw new NonexistentEntityException("The funcionario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionario funcionario;
            try {
                funcionario = em.getReference(Funcionario.class, id);
                funcionario.getIdFuncionario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The funcionario with id " + id + " no longer exists.", enfe);
            }
            Biblioteca idBiblioteca = funcionario.getIdBiblioteca();
            if (idBiblioteca != null) {
                idBiblioteca.getFuncionarioList().remove(funcionario);
                idBiblioteca = em.merge(idBiblioteca);
            }
            em.remove(funcionario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Funcionario> findFuncionarioEntities() {
        return findFuncionarioEntities(true, -1, -1);
    }

    public List<Funcionario> findFuncionarioEntities(int maxResults, int firstResult) {
        return findFuncionarioEntities(false, maxResults, firstResult);
    }

    private List<Funcionario> findFuncionarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Funcionario.class));
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

    public Funcionario findFuncionario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Funcionario.class, id);
        } finally {
            em.close();
        }
    }

    public int getFuncionarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Funcionario> rt = cq.from(Funcionario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Funcionario> login(String user, int pass) {
        EntityManager em = getEntityManager();
        List<Funcionario> resultlist = null;
        try {
            String query = "select c from Funcionario c where c.user = :user and c.pass = :pass";
            TypedQuery<Funcionario> list = em.createQuery(query, Funcionario.class);
            list.setParameter("user", user);
            list.setParameter("pass", pass);
            resultlist = list.getResultList();
            em.close();
            if (resultlist.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Funcionário não cadastrado");
            }

        } catch (Exception e) {
            System.err.println("Erro login");
        }
        return resultlist;
    }
    
    public List<Funcionario> buscarFuncionarioPorNome(String nome) {
        EntityManager em = getEntityManager();
        List<Funcionario> resultlist = null;
        try {
            String query = "select c from Funcionario c where c.nome LIKE :nome";
            TypedQuery<Funcionario> list = em.createQuery(query, Funcionario.class);
            list.setParameter("nome", "%"+nome+"%");
            resultlist = list.getResultList();
            em.close();

        } catch (Exception e) {
            System.err.println("Erro ao buscar");
        }
        return resultlist;
    }

}
