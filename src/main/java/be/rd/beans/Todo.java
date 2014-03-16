package be.rd.beans;

import javax.persistence.*;

/**
 * Created by ruben on 3/13/14.
 */
@Entity
@Table
public class Todo {
    private Long id;
    private int version;
    private String description;
    private boolean done;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Version
    @Column(name = "VERSION")
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "DONE")
    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
