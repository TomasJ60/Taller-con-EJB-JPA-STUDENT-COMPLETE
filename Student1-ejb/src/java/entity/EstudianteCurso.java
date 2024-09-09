/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tomas
 */
@Entity
@Table(name = "ESTUDIANTE_CURSO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstudianteCurso.findAll", query = "SELECT e FROM EstudianteCurso e"),
    @NamedQuery(name = "EstudianteCurso.findByEstudianteId", query = "SELECT e FROM EstudianteCurso e WHERE e.estudianteCursoPK.estudianteId = :estudianteId"),
    @NamedQuery(name = "EstudianteCurso.findByCursoId", query = "SELECT e FROM EstudianteCurso e WHERE e.estudianteCursoPK.cursoId = :cursoId")})
public class EstudianteCurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EstudianteCursoPK estudianteCursoPK;

    public EstudianteCurso() {
    }

    public EstudianteCurso(EstudianteCursoPK estudianteCursoPK) {
        this.estudianteCursoPK = estudianteCursoPK;
    }

    public EstudianteCurso(int estudianteId, int cursoId) {
        this.estudianteCursoPK = new EstudianteCursoPK(estudianteId, cursoId);
    }

    public EstudianteCursoPK getEstudianteCursoPK() {
        return estudianteCursoPK;
    }

    public void setEstudianteCursoPK(EstudianteCursoPK estudianteCursoPK) {
        this.estudianteCursoPK = estudianteCursoPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estudianteCursoPK != null ? estudianteCursoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudianteCurso)) {
            return false;
        }
        EstudianteCurso other = (EstudianteCurso) object;
        if ((this.estudianteCursoPK == null && other.estudianteCursoPK != null) || (this.estudianteCursoPK != null && !this.estudianteCursoPK.equals(other.estudianteCursoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EstudianteCurso[ estudianteCursoPK=" + estudianteCursoPK + " ]";
    }
    
}
