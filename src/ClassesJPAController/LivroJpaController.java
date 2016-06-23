/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesJPAController;

import Classes.GerarGrafico;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ClassesDeEntidades.Biblioteca;
import ClassesDeEntidades.Funcionario;
import ClassesDeEntidades.Livro;
import ClassesJPAController.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

/**
 *
 * @author Guilherme
 */
public class LivroJpaController implements Serializable {

    public LivroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Livro livro) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Biblioteca idBiblioteca = livro.getIdBiblioteca();
            if (idBiblioteca != null) {
                idBiblioteca = em.getReference(idBiblioteca.getClass(), idBiblioteca.getIdBiblioteca());
                livro.setIdBiblioteca(idBiblioteca);
            }
            em.persist(livro);
            if (idBiblioteca != null) {
                idBiblioteca.getLivroList().add(livro);
                idBiblioteca = em.merge(idBiblioteca);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Livro livro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Livro persistentLivro = em.find(Livro.class, livro.getIdLivro());
            Biblioteca idBibliotecaOld = persistentLivro.getIdBiblioteca();
            Biblioteca idBibliotecaNew = livro.getIdBiblioteca();
            if (idBibliotecaNew != null) {
                idBibliotecaNew = em.getReference(idBibliotecaNew.getClass(), idBibliotecaNew.getIdBiblioteca());
                livro.setIdBiblioteca(idBibliotecaNew);
            }
            livro = em.merge(livro);
            if (idBibliotecaOld != null && !idBibliotecaOld.equals(idBibliotecaNew)) {
                idBibliotecaOld.getLivroList().remove(livro);
                idBibliotecaOld = em.merge(idBibliotecaOld);
            }
            if (idBibliotecaNew != null && !idBibliotecaNew.equals(idBibliotecaOld)) {
                idBibliotecaNew.getLivroList().add(livro);
                idBibliotecaNew = em.merge(idBibliotecaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = livro.getIdLivro();
                if (findLivro(id) == null) {
                    throw new NonexistentEntityException("The livro with id " + id + " no longer exists.");
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
            Livro livro;
            try {
                livro = em.getReference(Livro.class, id);
                livro.getIdLivro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The livro with id " + id + " no longer exists.", enfe);
            }
            Biblioteca idBiblioteca = livro.getIdBiblioteca();
            if (idBiblioteca != null) {
                idBiblioteca.getLivroList().remove(livro);
                idBiblioteca = em.merge(idBiblioteca);
            }
            em.remove(livro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Livro> findLivroEntities() {
        return findLivroEntities(true, -1, -1);
    }

    public List<Livro> findLivroEntities(int maxResults, int firstResult) {
        return findLivroEntities(false, maxResults, firstResult);
    }

    private List<Livro> findLivroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Livro.class));
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

    public Livro findLivro(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Livro.class, id);
        } finally {
            em.close();
        }
    }

    public int getLivroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Livro> rt = cq.from(Livro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Livro> buscar(String editora) {
        EntityManager em = getEntityManager();
        List<Livro> resultlist = null;
        try {
            String query = "select c from Livro c where c.editora LIKE :editora";
            TypedQuery<Livro> list = em.createQuery(query, Livro.class);
            list.setParameter("editora", "%"+editora+"%");
            resultlist = list.getResultList();
            em.close();

        } catch (Exception e) {
            System.err.println("Erro ao buscar");
        }
        return resultlist;
    }
    
    public static List<GerarGrafico> grafico(List<Livro> lista) { 
        List<GerarGrafico> graf = null;
        graf = new ArrayList<>();
        int cont = 0;
        String[] area = new String[20];
        for (Livro l : lista) { // adiciona os generos dos filmes
            if (cont == 0){
                area[cont] = l.getArea();
                cont++;
            } else {
                int kk = 0;
                for (int i = 0; i < cont; i++) {
                    if (area[i].equalsIgnoreCase(l.getArea())){
                        i = cont;
                        kk = 1;
                    }
                }
                if (kk == 0) {
                    area[cont] = l.getArea();
                    cont++;
                }
            }
        }
        for (int i = 0; i < cont; i++) { // faz a contagem de cada genero
            GerarGrafico gr = new GerarGrafico();
            gr.setArea(area[i]);
            int qtd = 0;
            for (Livro l : lista){
                if (area[i].equalsIgnoreCase(l.getArea())){
                    qtd ++;
                }
            }
            gr.setQtd(qtd);
            graf.add(gr);
        }
        return graf;
    }

}
