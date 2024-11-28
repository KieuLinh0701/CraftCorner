package vn.iotstar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "promotes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Promote.findAll", query = "SELECT v FROM Promote v")
public class Promote implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "voucher_code", columnDefinition = "NVARCHAR(200)")
    private String voucherCode;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "discount_percent", nullable = false)
    private Integer discountPercent;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "quantity_used", columnDefinition= "INT DEFAULT 0")
    private Integer quantityUsed;

    @Column(name = "min_order_total", nullable = false)
    private Double minOrderTotal;
}