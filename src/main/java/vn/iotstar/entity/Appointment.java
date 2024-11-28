package vn.iotstar.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointments")
@NamedQuery(name = "Appointment.findAll", query = "SELECT c FROM Appointment c ")
public class Appointment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointmentId")
	private int appointmentId;
	
	@ManyToOne
	@JoinColumn(name = "customerId", nullable = false)
	private User customer;
	
	@ManyToOne
	@JoinColumn(name = "consultantId")
	private User consultant;
	
	@Column(name = "appointmentDate")
	private Timestamp appointmentDate;
	
	@Column(name = "status", columnDefinition = "NVARCHAR(255)")
	private String status;
	
	@Column(name = "createdAt")
	private Timestamp createdAt;
	
	@Column(name = "updateAt")
	private Timestamp updateAt;
	
	@Column(name = "description", columnDefinition = "NVARCHAR(MAX)")
	private String description;

	public Appointment() {
		super();
	}

	public Appointment(int appointmentId, User customer, User consultant, Timestamp appointmentDate, String status,
			Timestamp createdAt, Timestamp updateAt, String description) {
		super();
		this.appointmentId = appointmentId;
		this.customer = customer;
		this.consultant = consultant;
		this.appointmentDate = appointmentDate;
		this.status = status;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
		this.description = description;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public User getConsultant() {
		return consultant;
	}

	public void setConsultant(User consultant) {
		this.consultant = consultant;
	}

	public Timestamp getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Timestamp appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", customer=" + customer + ", consultant=" + consultant
				+ ", appointmentDate=" + appointmentDate + ", status=" + status + ", createdAt=" + createdAt
				+ ", updateAt=" + updateAt + ", description=" + description + "]";
	}
}
