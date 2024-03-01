package com.example.note.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "folders", schema = "note", catalog = "")
public class FoldersEntity {
    private int idFolders;

    private String name;
    private FoldersEntity foldersByIdParentFolder;
    private Collection<FoldersEntity> foldersByIdFolders;
    private Collection<NotesEntity> notesByIdFolders;

    public FoldersEntity() {
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idFolders", nullable = false)
    public int getIdFolders() {
        return idFolders;
    }

    public void setIdFolders(int idFolders) {
        this.idFolders = idFolders;
    }



    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoldersEntity that = (FoldersEntity) o;

        if (idFolders != that.idFolders) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idFolders;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_parent_folder", referencedColumnName = "idFolders", nullable = false)
    public FoldersEntity getFoldersByIdParentFolder() {
        return foldersByIdParentFolder;
    }

    public void setFoldersByIdParentFolder(FoldersEntity foldersByIdParentFolder) {
        this.foldersByIdParentFolder = foldersByIdParentFolder;
    }

    @OneToMany(mappedBy = "foldersByIdParentFolder")
    public Collection<FoldersEntity> getFoldersByIdFolders() {
        return foldersByIdFolders;
    }

    public void setFoldersByIdFolders(Collection<FoldersEntity> foldersByIdFolders) {
        this.foldersByIdFolders = foldersByIdFolders;
    }

    @OneToMany(mappedBy = "foldersByFolderId")
    public Collection<NotesEntity> getNotesByIdFolders() {
        return notesByIdFolders;
    }

    public void setNotesByIdFolders(Collection<NotesEntity> notesByIdFolders) {
        this.notesByIdFolders = notesByIdFolders;
    }
}
