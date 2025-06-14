package com.exemplo;

import javax.persistence.*;
import java.util.List;

public class CursoDAO {
    private EntityManagerFactory emf;

    public CursoDAO() {
        emf = Persistence.createEntityManagerFactory("cursoPU");
    }

    public void adicionarCurso(Curso curso) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(curso);
        em.getTransaction().commit();
        em.close();
    }

    public List<Curso> listarCursos() {
        EntityManager em = emf.createEntityManager();
        List<Curso> cursos = em.createQuery("FROM Curso", Curso.class).getResultList();
        em.close();
        return cursos;
    }

    public void atualizarCurso(Curso curso) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(curso);
        em.getTransaction().commit();
        em.close();
    }

    public void excluirCurso(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Curso curso = em.find(Curso.class, id);
        if (curso != null) {
            em.remove(curso);
        }
        em.getTransaction().commit();
        em.close();
    }
}