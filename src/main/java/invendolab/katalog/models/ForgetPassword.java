/*
 * Created by Batuhan Kök on 14/5/2018.
 * Copyright (c) D-Teknoloji 2018.
 */

package invendolab.katalog.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "forget_password")
public class ForgetPassword {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Column(name = "consumer_id")
    private long consumerId;

    @NotNull
    @Column(name = "key")
    private String key;

    @NotNull
    @Column(name = "is_active")
    private Boolean isActive;

    public ForgetPassword(){ }

    public ForgetPassword(long consumerId, String key, Boolean isActive) {
        this.consumerId = consumerId;
        this.key = key;
        this.isActive = isActive;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(long consumerId) {
        this.consumerId = consumerId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}