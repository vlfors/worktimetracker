package test.worktimetracker.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vlad
 */
@Entity
@Table(name = "worktime")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "WorktimeEntity.findAll", query = "SELECT w FROM WorktimeEntity w"),
        @NamedQuery(name = "WorktimeEntity.findById", query = "SELECT w FROM WorktimeEntity w WHERE w.id = :id"),
        @NamedQuery(name = "WorktimeEntity.findByWtBegin", query = "SELECT w FROM WorktimeEntity w WHERE w.wtBegin = :wtBegin"),
        @NamedQuery(name = "WorktimeEntity.findByWtEnd", query = "SELECT w FROM WorktimeEntity w WHERE w.wtEnd = :wtEnd"),
        @NamedQuery(name = "WorktimeEntity.getInfoAboutTasks", query = " SELECT w.tskId.tskIname,  SUM(w.wtEnd-w.wtBegin) as d FROM WorktimeEntity w where w.wtEnd!= null and w.usrId =:idUser group by w.tskId.tskIname")})
public class WorktimeEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "wt_begin")
    private BigInteger wtBegin;
    @Column(name = "wt_end")
    private BigInteger wtEnd;
    @JoinColumn(name = "usr_id", referencedColumnName = "usr_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private UserttEntity usrId;
    @JoinColumn(name = "tsk_id", referencedColumnName = "tsk_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TaskEntity tskId;

    public WorktimeEntity() {
    }

    public WorktimeEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getWtBegin() {
        return wtBegin;
    }

    public void setWtBegin(BigInteger wtBegin) {
        this.wtBegin = wtBegin;
    }

    public BigInteger getWtEnd() {
        return wtEnd;
    }

    public void setWtEnd(BigInteger wtEnd) {
        this.wtEnd = wtEnd;
    }

    public UserttEntity getUsrId() {
        return usrId;
    }

    public void setUsrId(UserttEntity usrId) {
        this.usrId = usrId;
    }

    public TaskEntity getTskId() {
        return tskId;
    }

    public void setTskId(TaskEntity tskId) {
        this.tskId = tskId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WorktimeEntity)) {
            return false;
        }
        WorktimeEntity other = (WorktimeEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WorktimeEntity[ id=" + id + " ]";
    }

}
