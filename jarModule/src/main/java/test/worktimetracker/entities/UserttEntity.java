/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.worktimetracker.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vlad
 */
@Entity
@Table(name = "usertt", schema = "public")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "UserttEntity.findAll", query = "SELECT u FROM UserttEntity u"),
        @NamedQuery(name = "UserttEntity.findByUsrId", query = "SELECT u FROM UserttEntity u WHERE u.usrId = :usrId"),
        @NamedQuery(name = "UserttEntity.findByUsrName", query = "SELECT u FROM UserttEntity u WHERE UPPER( u.usrName) = UPPER(:usrName)"),
        @NamedQuery(name = "UserttEntity.findByUsrEmail", query = "SELECT u FROM UserttEntity u WHERE u.usrEmail = :usrEmail")})
public class UserttEntity implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usrId",fetch= FetchType.EAGER)
    private List<WorktimeEntity> worktimeCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usr_id")
    private Integer usrId;
    @Size(max = 50)
    @Column(name = "usr_name")
    private String usrName;
    @Size(max = 50)
    @Column(name = "usr_email")
    private String usrEmail;

    public UserttEntity() {
    }

    public UserttEntity(String usrName) {
        this.usrName = usrName;
    }

    public Integer getUsrId() {
        return usrId;
    }

    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usrId != null ? usrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserttEntity)) {
            return false;
        }
        UserttEntity other = (UserttEntity) object;
        if ((this.usrId == null && other.usrId != null) || (this.usrId != null && !this.usrId.equals(other.usrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "h.UserttEntity[ usrId=" + usrId + " ]";
    }

    @XmlTransient
    public List<WorktimeEntity> getWorktimeCollection() {
        return worktimeCollection;
    }

    public void setWorktimeCollection(List<WorktimeEntity> worktimeCollection) {
        this.worktimeCollection = worktimeCollection;
    }

}
