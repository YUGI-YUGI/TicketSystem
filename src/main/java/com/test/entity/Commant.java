package com.test.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;
@Entity(name="commant1")
public class Commant {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@ManyToOne
	@JoinColumn(name="tid")
	private Ticket tId;
	
	@Column(nullable=false)
    private String comment;
    
//    @Fetch(FetchMode.JOIN)
//    @ManyToOne
//	@JoinColumn(name="commentBy")
//    private User commentBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date commentAt;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the commentAt
	 */
	public Date getCommentAt() {
		return commentAt;
	}
	/**
	 * @param commentAt the commentAt to set
	 */
	public void setCommentAt(Date commentAt) {
		this.commentAt = commentAt;
	}
	/**
	 * @return the tId
	 */
	public Ticket gettId() {
		return tId;
	}
	/**
	 * @param tId the tId to set
	 */
	public void settId(Ticket tId) {
		this.tId = tId;
	}

    
    
    
}
