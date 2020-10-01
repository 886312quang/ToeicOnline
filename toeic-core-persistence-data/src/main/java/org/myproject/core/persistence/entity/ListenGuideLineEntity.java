package org.myproject.core.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "lisntenguideline")
public class ListenGuideLineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer listenGuideLineId;

    @Column(name = "title")
    private String title;
    @Column(name = "image")
    private String image;
    @Column(name = "content")
    private String content;
    @Column(name = "createdate")
    private Timestamp createDate;
    @Column(name = "modifieddate")
    private Timestamp modifiedDate;

    // List comment 1 - n
    @OneToMany(mappedBy = "listenGuideLineEntity", fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList;

    public Integer getListenGuideLineId() {
        return listenGuideLineId;
    }

    public void setListenGuideLineId(Integer listenGuideLineId) {
        this.listenGuideLineId = listenGuideLineId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public List<CommentEntity> getCommentEntityList() {
        return commentEntityList;
    }

    public void setCommentEntityList(List<CommentEntity> commentEntityList) {
        this.commentEntityList = commentEntityList;
    }
}
