package vn.iotstar.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment_methods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "PaymentMethod.findAll", query = "SELECT v FROM PaymentMethod v")
public class PaymentMethod implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "bank_name", columnDefinition = "NVARCHAR(255) NOT NULL")
    private String bankName;

    @Column(name = "account_number", columnDefinition = "NVARCHAR(255) NOT NULL")
    private String accountNumber;

	@Column(name = "account_owner", columnDefinition = "NVARCHAR(255) NOT NULL")
	private String accountOwner;
    
    @Column(name = "image", columnDefinition = "NVARCHAR(255) NULL")
    private String image;

    @Column(name = "status", columnDefinition = "INT")
    private int status;
}
