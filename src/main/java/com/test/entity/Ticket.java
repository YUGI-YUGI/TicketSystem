package com.test.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class Ticket {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	@Column(name="subject")
	private String subject;
	@Column(name="desprtion")
	private String desprtion;
	@Column(name="status1")
	private String status;
	

	@OneToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="assign_to")
	private User assignTO;
	
	@OneToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="customerId")
	private Customer customer;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date createdAt;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tId" )
	private List<Commant> comments;
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
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the desprtion
	 */
	public String getDesprtion() {
		return desprtion;
	}
	/**
	 * @param desprtion the desprtion to set
	 */
	public void setDesprtion(String desprtion) {
		this.desprtion = desprtion;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the customerId
	 */

	
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	/**
	 * @return the comments
	 */
	public List<Commant> getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(List<Commant> comments) {
		this.comments = comments;
	}
	/**
	 * @return the assignTO
	 */
	public User getAssignTO() {
		return assignTO;
	}
	/**
	 * @param assignTO the assignTO to set
	 */
	public void setAssignTO(User assignTO) {
		this.assignTO = assignTO;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ticket [id=").append(id).append(", subject=").append(subject).append(", desprtion=")
				.append(desprtion).append(", status=").append(status).append(", assignTO=").append(assignTO)
				.append(", customer=").append(customer).append(", createdAt=").append(createdAt).append(", comments=")
				.append(comments).append("]");
		return builder.toString();
	}

	
	
	
}
