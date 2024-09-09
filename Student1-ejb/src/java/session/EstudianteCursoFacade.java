/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.EstudianteCurso;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tomas
 */
@Stateless
public class EstudianteCursoFacade extends AbstractFacade<EstudianteCurso> implements EstudianteCursoFacadeLocal {

    @PersistenceContext(unitName = "Student1-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteCursoFacade() {
        super(EstudianteCurso.class);
    }
    
}
