package com.example.note.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "authorities", schema = "note", catalog = "")
public class AuthoritiesEntity {
    private int idAuthorities;
    private String authority;

    private UsersEntity usersByUser;

    public AuthoritiesEntity() {
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_authorities", nullable = false)
    public int getIdAuthorities() {
        return idAuthorities;
    }

    public void setIdAuthorities(int idAuthorities) {
        this.idAuthorities = idAuthorities;
    }

    @Basic
    @Column(name = "authority", nullable = false, length = 45)
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthoritiesEntity that = (AuthoritiesEntity) o;

        if (idAuthorities != that.idAuthorities) return false;
        if (authority != null ? !authority.equals(that.authority) : that.authority != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAuthorities;
        result = 31 * result + (authority != null ? authority.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id_user", nullable = false)
    public UsersEntity getUsersByUser() {
        return usersByUser;
    }

    public void setUsersByUser(UsersEntity usersByUser) {
        this.usersByUser = usersByUser;
    }
}
