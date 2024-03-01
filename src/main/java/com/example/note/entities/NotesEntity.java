package com.example.note.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "notes", schema = "note", catalog = "")
public class NotesEntity {
    private int idnotes;
    private String topic;
    private String text;
    private FoldersEntity foldersByFolderId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idnotes", nullable = false)
    public int getIdnotes() {
        return idnotes;
    }

    public void setIdnotes(int idnotes) {
        this.idnotes = idnotes;
    }

    public NotesEntity() {
    }


    @Basic
    @Column(name = "topic", nullable = false, length = 45)
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Basic
    @Column(name = "text", nullable = false, length = 255)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @ManyToOne
    @JoinColumn(name = "folder_id", referencedColumnName = "idFolders", nullable = false)
    public FoldersEntity getFoldersByFolderId() {
        return foldersByFolderId;
    }

    public void setFoldersByFolderId(FoldersEntity foldersByFolderId) {
        this.foldersByFolderId = foldersByFolderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotesEntity that = (NotesEntity) o;

        if (idnotes != that.idnotes) return false;

        if (topic != null ? !topic.equals(that.topic) : that.topic != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idnotes;

        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }


}
