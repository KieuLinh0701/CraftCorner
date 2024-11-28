package vn.iotstar.entity;

import java.io.Serializable;
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
@Table(name = "design_items")
@NamedQuery(name = "DesignItem.findAll", query = "SELECT v FROM DesignItem v")
public class DesignItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "designItem_id")
	private int designItem_id;

	@ManyToOne
    @JoinColumn(name = "design_id")
    private Designs design;

	@ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    public DesignItem() {
    }

	public int getDesignItem_id() {
		return designItem_id;
	}

	public void setDesignItem_id(int designItem_id) {
		this.designItem_id = designItem_id;
	}

	public Designs getDesign() {
		return design;
	}

	public void setDesign(Designs design) {
		this.design = design;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
