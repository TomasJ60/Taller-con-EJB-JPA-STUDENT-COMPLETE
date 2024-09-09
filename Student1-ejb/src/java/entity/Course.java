/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tomas
 */
@Entity
@Table(name = "COURSE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
    @NamedQuery(name = "Course.findByCoursecode", query = "SELECT c FROM Course c WHERE c.coursecode = :coursecode"),
    @NamedQuery(name = "Course.findByNamecourse", query = "SELECT c FROM Course c WHERE c.namecourse = :namecourse"),
    @NamedQuery(name = "Course.findByCredits", query = "SELECT c FROM Course c WHERE c.credits = :credits"),
    @NamedQuery(name = "Course.findBySemestre", query = "SELECT c FROM Course c WHERE c.semestre = :semestre"),
    @NamedQuery(name = "Course.findByAdmitted", query = "SELECT c FROM Course c WHERE c.admitted = :admitted")})
public class Course implements Serializable {

    @ManyToMany(mappedBy = "courseCollection")
    private Collection<Student> studentCollection;

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COURSECODE")
    private Integer coursecode;

    @Size(max = 50)
    @Column(name = "NAMECOURSE")
    private String namecourse;

    @Size(max = 50)
    @Column(name = "CREDITS")
    private String credits;

    @Size(max = 50)
    @Column(name = "SEMESTRE")
    private String semestre;

    @Column(name = "ADMITTED")
    private Integer admitted;

    public Course() {
    }

    public Course(Integer coursecode, String namecourse, String credits, String semestre, Integer admitted) {
        this.coursecode = coursecode;
        this.namecourse = namecourse;
        this.credits = credits;
        this.semestre = semestre;
        this.admitted = admitted;
    }

    public Course(Integer coursecode) {
        this.coursecode = coursecode;
    }

    public Integer getCoursecode() {
        return coursecode;
    }

    public void setCoursecode(Integer coursecode) {
        this.coursecode = coursecode;
    }

    public String getNamecourse() {
        return namecourse;
    }

    public void setNamecourse(String namecourse) {
        this.namecourse = namecourse;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public Integer getAdmitted() {
        return admitted;
    }

    public void setAdmitted(Integer admitted) {
        this.admitted = admitted;
    }

    @XmlTransient
    public Collection<Student> getStudentCollection() {
        return studentCollection;
    }

    public void setStudentCollection(Collection<Student> studentCollection) {
        this.studentCollection = studentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coursecode != null ? coursecode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.coursecode == null && other.coursecode != null) || (this.coursecode != null && !this.coursecode.equals(other.coursecode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Course[ coursecode=" + coursecode + " ]";
    }
}
