package models;

public class Comment {
    private int id, proposalVersion;
    private String proposalId;
    private String text, author;

    public Comment(){}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getProposalVersion() {
        return proposalVersion;
    }

    public String getProposalId() {
        return proposalId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    public void setProposalVersion(int proposalVersion) {
        this.proposalVersion = proposalVersion;
    }
}
