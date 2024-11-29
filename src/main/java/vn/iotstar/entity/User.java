package vn.iotstar.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT v FROM User v")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "fullname", columnDefinition = "NVARCHAR(50) NULL")
	private String fullname;

	@Column(name = "email", columnDefinition = "NVARCHAR(50) NOT NULL")
	private String email;
	
	@Column(name = "code", columnDefinition = "NVARCHAR(20) NULL")
	private String code;

	@Column(name = "image", columnDefinition = "NVARCHAR(500) NULL")
	private String image;
	
	@Column(name = "password", columnDefinition = "NVARCHAR(50) NULL")
	private String password;
	
	@Column(name = "phone", columnDefinition = "NVARCHAR(50) NULL")
	private String phone;
	
	@Column(name = "status")
	private int status;

	@Column(name = "createDate")
	private LocalDateTime createDate;

	// bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name = "roleId")
	private Role role;
	
	// CascadeType.ALL để tự động lưu Address khi cập nhật User
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false)
	private Address address;

	public User() {
	}
	
	public User(String fullname, String email, String code, String password,
			String phone, int status, LocalDateTime createDate, Role role, Address address) {
		super();
		this.fullname = fullname;
		this.email = email;
		this.code = code;
		this.password = password;
		this.phone = phone;
		this.status = status;
		this.createDate = createDate;
		this.role = role;
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
