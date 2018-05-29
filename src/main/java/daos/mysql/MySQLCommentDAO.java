package daos.mysql;

import daos.CommentDAO;
import models.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLCommentDAO extends CommentDAO {

    private static final MySQLConnectionPool MY_SQL_CONNECTION_POOL = new MySQLConnectionPool();

    public MySQLCommentDAO(){

    }

    // Submit a new comment; return true on success
    public boolean submitNewComment(Comment comment) throws SQLException {
        // Establish our connection and build our query
        Connection con = MY_SQL_CONNECTION_POOL.getConnection();
        String query = "INSERT INTO `prd`.`SIXTH_proposal_comments` (" +
                "`comment_on_proposal`," +
                "`comment_on_version`," +
                "`comment_on_segment`," +
                "`comment_author`," +
                "`comment_body`) VALUES (" +
                "?,?,?,?,?);";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, comment.getCommentOnProposal());
        ps.setInt(2,comment.getCommentOnVersion());
        ps.setString(3, comment.getCommentOnSegment());
        ps.setString(4,comment.getCommentAuthor());
        ps.setString(5,comment.getCommentBody());

        // Do the thing
        int rowsAffected = ps.executeUpdate();

        // Close our connections
        ps.close();
        con.close();

        return (rowsAffected > 0);
    }

    public List<Comment> getCommentsOnVersion(String proposalId, int proposalVersion) throws SQLException{
        List<Comment> comments = new ArrayList<>();
        Connection con = MY_SQL_CONNECTION_POOL.getConnection();
        String query = "SELECT * FROM (" +
                "SELECT * FROM `prd`.`SIXTH_proposal_comments` WHERE `comment_on_proposal` = ?) pvc " +
                "WHERE pvc.`comment_on_version` = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,proposalId);
        ps.setInt(2, proposalVersion);

        // Do the thing
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            // Create and build out a temporary Comment
            Comment c = new Comment();
            c.setCommentOnProposal(rs.getString("comment_on_proposal"));
            c.setCommentOnVersion(rs.getInt("comment_on_version"));
            c.setCommentOnSegment(rs.getString("comment_on_segment"));
            c.setCommentBody(rs.getString("comment_body"));
            c.setCommentAuthor(rs.getString("comment_author"));

            // Add the new Comment to our list
            comments.add(c);
        }

        // Close our connections
        rs.close();
        ps.close();
        con.close();
        return comments;
    }

    public List<Comment> getCommentsOnProposal(String proposalId) throws SQLException{
        List<Comment> comments = new ArrayList<>();
        Connection con = MY_SQL_CONNECTION_POOL.getConnection();
        String query = "SELECT * FROM `prd`.`SIXTH_proposal_comments` WHERE `comment_on_proposal` = ?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1,proposalId);
        // Do the thing
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            // Create and build out a temporary Comment
            Comment c = new Comment();
            c.setCommentOnProposal(rs.getString("comment_on_proposal"));
            c.setCommentOnVersion(rs.getInt("comment_on_version"));
            c.setCommentOnSegment(rs.getString("comment_on_segment"));
            c.setCommentBody(rs.getString("comment_body"));
            c.setCommentAuthor(rs.getString("comment_author"));

            // Add the new Comment to our list
            comments.add(c);
        }

        // Close our connections
        rs.close();
        ps.close();
        con.close();

        return comments;
    }
}
