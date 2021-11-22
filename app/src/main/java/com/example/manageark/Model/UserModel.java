package com.example.manageark.Model;

public class UserModel {
    private String uid;
    private String fullName;
    private String email;
    private String uniqueId;
    private String university;
    private String photoUrl;

    public UserModel(String uid, String fullName, String email, String uniqueId, String university, String photoUrl) {
        this.uid = uid;
        this.fullName = fullName;
        this.email = email;
        this.uniqueId = uniqueId;
        this.university = university;
        this.photoUrl = photoUrl;
    }

    public void setUID(String uid) { this.uid = uid; }
    public String getUID() { return this.uid; }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniqueId() {
        return uniqueId;
    }
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getUniversity() {
        return university;
    }
    public void setUniversity(String university) {
        this.university = university;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
