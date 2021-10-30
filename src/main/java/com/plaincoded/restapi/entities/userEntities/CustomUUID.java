package com.plaincoded.restapi.entities.userEntities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CustomUUID  {//Universally unique identifier for email confirmation purposes

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private long tokenid;

    @Column(name="confirmation_token")
    private String UUID;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public CustomUUID() {
    }

    public CustomUUID(User user) {
        this.user = user;
        createdDate = new Date();
        UUID = java.util.UUID.randomUUID().toString();
    }

    public long getTokenid() {
        return tokenid;
    }

    public void setTokenid(long tokenid) {
        this.tokenid = tokenid;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String confirmationToken) {
        this.UUID = confirmationToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // getters and setters
}