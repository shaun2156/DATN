package edu.ptit.quynhhtn.entity;

import javax.persistence.*;

@Entity
public class CommentEditing extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentEditingId;

    private Long commentId;

    @Column(columnDefinition="bit default 0")
    private boolean oldDisabled;

    @Column(columnDefinition="bit default 0")
    private boolean deleted;

    @Column(length = 2000)
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "commentId", referencedColumnName = "commentId", insertable = false, updatable = false)
    private Comment comment;

    public CommentEditing() {
    }

    public Long getCommentEditingId() {
        return commentEditingId;
    }

    public void setCommentEditingId(Long commentEditingId) {
        this.commentEditingId = commentEditingId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        if(comment != null){
            setCommentId(comment.getCommentId());
        }
        this.comment = comment;
    }

    public boolean isOldDisabled() {
        return oldDisabled;
    }

    public void setOldDisabled(boolean oldDisabled) {
        this.oldDisabled = oldDisabled;
    }
}
