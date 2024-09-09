/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.EstudianteCurso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author tomas
 */
@Local
public interface EstudianteCursoFacadeLocal {

    void create(EstudianteCurso estudianteCurso);

    void edit(EstudianteCurso estudianteCurso);

    void remove(EstudianteCurso estudianteCurso);

    EstudianteCurso find(Object id);

    List<EstudianteCurso> findAll();

    List<EstudianteCurso> findRange(int[] range);

    int count();
    
}
