package vn.iotstar.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT ord FROM Order ord")
public class Order implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private int order_id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
	
	@Column(name="order_date")
	private Date order_date;
	
	@Column(name = "total_price", columnDefinition = "DECIMAL(10, 2) NOT NULL")
    private double total_price;
	
	@Column(name="note", columnDefinition = "NVARCHAR(500) NULL")
	private String note;
	
	@Column(name="status", columnDefinition = "NVARCHAR(100) NULL")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "payment_id", referencedColumnName = "id", nullable = false)
    private PaymentMethod payment;
	
	@ManyToOne
	@JoinColumn(name = "promote_id", referencedColumnName = "id", nullable = false)
    private Promote promote;
	
	@OneToMany(mappedBy="order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<OrderDetail> orderDetails;
	
	public Order() {
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PaymentMethod getPayment() {
		return payment;
	}

	public void setPayment(PaymentMethod payment) {
		this.payment = payment;
	}

	public Promote getPromote() {
		return promote;
	}

	public void setPromote(Promote promote) {
		this.promote = promote;
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
