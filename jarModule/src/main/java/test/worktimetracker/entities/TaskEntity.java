/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.worktimetracker.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vlad
 */
@Entity
@Table(name = "task", schema = "public")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TaskEntity.findAll", query = "SELECT t FROM TaskEntity t"),
        @NamedQuery(name = "TaskEntity.findByTskId", query = "SELECT t FROM TaskEntity t WHERE t.tskId = :tskId"),
        @NamedQuery(name = "TaskEntity.findByTskIname", query = "SELECT t FROM TaskEntity t WHERE UPPER(t.tskIname) =UPPER(:tskIname)")//,
        /*@NamedQuery(name = "TaskEntity.findRelByName", query = "SELECT t FROM TaskEntity t,WorktimeEntity w WHERE t.tskId = w.tskId and  t.tskIname = :tskIname")*/})
public class TaskEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tsk_id")
    private Integer tskId;
    @Size(max = 50)
    @Column(name = "tsk_iname")
    private String tskIname;
    @OneToMany(cascade = CascadeType.ALL,fetch= FetchType.EAGER, mappedBy = "tskId")
    private Collection<WorktimeEntity> worktimeCollection;

    public TaskEntity() {
    }

    public TaskEntity(Integer tskId) {
        this.tskId = tskId;
    }

    public Integer getTskId() {
        return tskId;
    }

    public void setTskId(Integer tskId) {
        this.tskId = tskId;
    }

    public String getTskIname() {
        return tskIname;
    }

    public void setTskIname(String tskIname) {
        this.tskIname = tskIname;
    }

    @XmlTransient
    public Collection<WorktimeEntity> getWorktimeCollection() {
        return worktimeCollection;
    }

    public void setWorktimeCollection(Collection<WorktimeEntity> worktimeCollection) {
        this.worktimeCollection = worktimeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tskId != null ? tskId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaskEntity)) {
            return false;
        }
        TaskEntity other = (TaskEntity) object;
        if ((this.tskId == null && other.tskId != null) || (this.tskId != null && !this.tskId.equals(other.tskId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "h.Task[ tskId=" + tskId + " ]";
    }

}
