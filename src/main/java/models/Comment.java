package models;

public class Comment {
    private int comment_id, comment_on_version;
    private String comment_on_proposal, comment_on_segment;
    private String comment_body, comment_author;

    public Comment(){}

    public void setCommentId(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getCommentId() {
        return comment_id;
    }

    public String getCommentAuthor() {
        return comment_author;
    }

    public void setCommentAuthor(String comment_author) {
        this.comment_author = comment_author;
    }

    public String getCommentOnSegment() {
        return comment_on_segment;
    }

    public void setCommentOnSegment(String comment_on_segment){
        this.comment_on_segment = comment_on_segment;
    }
    public int getCommentOnVersion() {
        return comment_on_version;
    }

    public String getCommentOnProposal() {
        return comment_on_proposal;
    }

    public String getCommentBody() {
        return comment_body;
    }

    public void setCommentBody(String comment_body) {
        this.comment_body = comment_body;
    }

    public void setCommentOnProposal(String comment_on_proposal) {
        this.comment_on_proposal = comment_on_proposal;
    }

    public void setCommentOnVersion(int comment_on_version) {
        this.comment_on_version = comment_on_version;
    }
}
