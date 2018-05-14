/*
 * Created by Batuhan Kök on 14/5/2018.
 * Copyright (c) D-Teknoloji 2018.
 */

package invendolab.katalog.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LoginResponse {

    private Long id;
    private String userName;
    @JsonIgnore
    private String password;
    private String email;
    private String jobTitle;
    private String bio;
    private String profileUrl;
    private String companyName;
    private String fullName;
    private boolean isActive;
    private String[] roles;

    public LoginResponse(Long id, String userName, String password, String email, String jobTitle, String bio, String profileUrl, String companyName, String fullName, boolean isActive, String[] roles) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.jobTitle = jobTitle;
        this.bio = bio;
        this.profileUrl = profileUrl;
        this.companyName = companyName;
        this.fullName = fullName;
        this.isActive = isActive;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
