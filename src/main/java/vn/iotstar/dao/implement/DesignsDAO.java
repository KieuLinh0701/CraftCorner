	package vn.iotstar.dao.implement;
	
	import vn.iotstar.entity.Designs;
	import vn.iotstar.entity.DesignItem;
	import vn.iotstar.entity.Product;
	import vn.iotstar.entity.User;
	
	import java.util.Set;
	import java.util.HashSet;
	
	import vn.iotstar.utils.DBConnection;
	
	import java.sql.*;
	import java.time.LocalDate;
	import java.util.ArrayList;
	import java.util.List;
	
	public class DesignsDAO {
	
		// Lấy tất cả thiết kế
		public List<Designs> getAllDesigns() {
			List<Designs> designs = new ArrayList<>();
			String query = "SELECT d.design_id, d.title, d.content, d.image, d.create_date, d.status "
					+ "FROM designs d ORDER BY d.create_date DESC";
	
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(query);
					ResultSet rs = stmt.executeQuery()) {
	
				while (rs.next()) {
					designs.add(mapResultSetToDesigns(rs));
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return designs;
		}
	
		// Lấy thiết kế theo ID
		public Designs getDesignById(int designId) {
		    Designs design = null;
		    String designQuery = "SELECT d.design_id, d.title, d.content, d.image, d.create_date, d.status, d.user_id "
		                       + "FROM designs d WHERE d.design_id = ?";
		    try (Connection conn = new DBConnection().getConnection();
		         PreparedStatement designStmt = conn.prepareStatement(designQuery)) {
		        designStmt.setInt(1, designId);
		        try (ResultSet rs = designStmt.executeQuery()) {
		            if (rs.next()) {
		                design = mapResultSetToDesigns(rs);
		                
		             // Lấy thông tin User
		                int userId = rs.getInt("user_id");
		                System.out.println(userId);

		                User user = getUserById(userId, conn);
		                design.setUser(user);
		               

		                System.out.println(user.getId());
		                // Lấy danh sách DesignItems và Products liên quan
		                Set<DesignItem> designItems = getDesignItemsByDesignId(designId, conn);
		                design.setDesignItems(designItems);
		            }
		        }
		    } catch (SQLException | ClassNotFoundException e) {
		        e.printStackTrace();
		    }
		    return design;
		}
		
		
		// Lấy thông tin User theo ID
		private User getUserById(int userId, Connection conn) throws SQLException {
		    String userQuery = "SELECT fullname FROM users WHERE id = ?";
		    try (PreparedStatement userStmt = conn.prepareStatement(userQuery)) {
		        userStmt.setInt(1, userId);
		        try (ResultSet rs = userStmt.executeQuery()) {
		            if (rs.next()) {
		                User user = new User();
		                user.setFullname(rs.getString("fullname"));
		                return user;
		            }
		        }
		    }
		    return null; // Nếu không tìm thấy user
		}

	
		
		// Lấy danh sách DesignItems và Products liên quan theo ID thiết kế
		public Set<DesignItem> getDesignItemsByDesignId(int designId, Connection conn) throws SQLException {
		    Set<DesignItem> designItems = new HashSet<>();
		    String sql = "SELECT di.designItem_id, di.design_id, di.product_id, p.name, p.description, p.quantity, "
		               + "p.price, p.image, p.color, p.material, p.height, p.length, p.width, p.status, p.createDate "
		               + "FROM design_items di "
		               + "JOIN products p ON di.product_id = p.product_id "
		               + "WHERE di.design_id = ?";
		    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setInt(1, designId);
		        try (ResultSet rs = stmt.executeQuery()) {
		            while (rs.next()) {
		                DesignItem designItem = new DesignItem();
		                designItem.setDesignItem_id(rs.getInt("designItem_id"));
	
		                // Lấy thông tin sản phẩm liên quan
		                Product product = new Product();
		                product.setProduct_id(rs.getInt("product_id"));
		                product.setName(rs.getString("name"));
		                product.setDescription(rs.getString("description"));
		                product.setQuantity(rs.getInt("quantity"));
		                product.setPrice(rs.getInt("price"));
		                product.setImage(rs.getString("image"));
		                product.setColor(rs.getString("color"));
		                product.setMaterial(rs.getString("material"));
		                product.setHeight(rs.getFloat("height"));
		                product.setLength(rs.getFloat("length"));
		                product.setWidth(rs.getFloat("width"));
		                product.setStatus(rs.getInt("status"));
		                product.setCreateDate(rs.getTimestamp("createDate").toLocalDateTime());
	
		                designItem.setProduct(product);
		                designItems.add(designItem);
		            }
		        }
		    }
		    return designItems;
		}
		
		
	
	
	
		// Lấy thiết kế theo phân trang
		public List<Designs> getDesignsByPage(int offset, int limit) {
			List<Designs> designs = new ArrayList<>();
			String query = "SELECT d.design_id, d.title, d.content, d.image, d.create_date, d.status "
					+ "FROM designs d ORDER BY d.create_date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
	
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(query)) {
	
				stmt.setInt(1, offset);
				stmt.setInt(2, limit);
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						designs.add(mapResultSetToDesigns(rs));
					}
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return designs;
		}
	
		// Lấy tổng số thiết kế
		public int getTotalDesigns() {
			String query = "SELECT COUNT(*) AS total FROM designs";
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(query);
					ResultSet rs = stmt.executeQuery()) {
	
				if (rs.next()) {
					return rs.getInt("total");
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return 0;
		}
	
		// Chuyển đổi từ ResultSet sang Designs
		private Designs mapResultSetToDesigns(ResultSet rs) throws SQLException {
			Designs design = new Designs();
			design.setDesignId(rs.getInt("design_id"));
			design.setTitle(rs.getString("title"));
			design.setContent(rs.getString("content"));
			design.setImage(rs.getString("image"));
			design.setCreateDate(rs.getDate("create_date").toLocalDate());
			design.setStatus(rs.getInt("status"));
			return design;
		}
	
		// Thêm một thiết kế
		public void addDesign(Designs design) {
			String query = "INSERT INTO designs (title, content, image, create_date, status) VALUES (?, ?, ?, ?, ?)";
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(query)) {
	
				stmt.setString(1, design.getTitle());
				stmt.setString(2, design.getContent());
				stmt.setString(3, design.getImage());
				stmt.setDate(4, Date.valueOf(design.getCreateDate())); // Convert LocalDate to SQL Date
				stmt.setInt(5, design.getStatus());
	
				stmt.executeUpdate();
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	
		// Cập nhật một thiết kế
		public void updateDesign(Designs design) {
			String query = "UPDATE designs SET title = ?, content = ?, image = ?, create_date = ?, status = ? WHERE design_id = ?";
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(query)) {
	
				stmt.setString(1, design.getTitle());
				stmt.setString(2, design.getContent());
				stmt.setString(3, design.getImage());
				stmt.setDate(4, Date.valueOf(design.getCreateDate())); // Convert LocalDate to SQL Date
				stmt.setInt(5, design.getStatus());
				stmt.setInt(6, design.getDesignId());
	
				stmt.executeUpdate();
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	
		// Xóa một thiết kế
		public void deleteDesign(int designId) {
			String query = "DELETE FROM designs WHERE design_id = ?";
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(query)) {
	
				stmt.setInt(1, designId);
				stmt.executeUpdate();
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	
		public List<Designs> getDesignsByQuery(String query) {
			List<Designs> designs = new ArrayList<>();
			String sql = "SELECT d.design_id, d.title, d.content, d.image, d.create_date, d.status "
					+ "FROM designs d WHERE d.title LIKE ? OR d.content LIKE ? ORDER BY d.create_date DESC";
	
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql)) {
	
				stmt.setString(1, "%" + query + "%");
				stmt.setString(2, "%" + query + "%");
	
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						designs.add(mapResultSetToDesigns(rs));
					}
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return designs;
		}
	
		public List<Designs> getDesignsByDateRange(String minDate, String maxDate) {
			List<Designs> designs = new ArrayList<>();
			String sql = "SELECT d.design_id, d.title, d.content, d.image, d.create_date, d.status "
					+ "FROM designs d WHERE d.create_date BETWEEN ? AND ? ORDER BY d.create_date DESC";
	
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql)) {
	
				stmt.setDate(1, Date.valueOf(minDate)); // Convert String to SQL Date
				stmt.setDate(2, Date.valueOf(maxDate));
	
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						designs.add(mapResultSetToDesigns(rs));
					}
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return designs;
		}
	
		public List<Designs> getDesignsByQueryAndDate(String query, String minDate, String maxDate) {
			List<Designs> designs = new ArrayList<>();
			String sql = "SELECT d.design_id, d.title, d.content, d.image, d.create_date, d.status "
					+ "FROM designs d WHERE (d.title LIKE ? OR d.content LIKE ?) "
					+ "AND d.create_date BETWEEN ? AND ? ORDER BY d.create_date DESC";
	
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql)) {
	
				stmt.setString(1, "%" + query + "%");
				stmt.setString(2, "%" + query + "%");
				stmt.setDate(3, Date.valueOf(minDate)); // Convert String to SQL Date
				stmt.setDate(4, Date.valueOf(maxDate));
	
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						designs.add(mapResultSetToDesigns(rs));
					}
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return designs;
		}
	
		public List<Designs> getDesignsByQueryAndDateWithPagination(String query, String minDate, String maxDate,
				int offset, int limit) {
			List<Designs> designs = new ArrayList<>();
			String sql = "SELECT d.design_id, d.title, d.content, d.image, d.create_date, d.status "
					+ "FROM designs d WHERE (d.title LIKE ? OR d.content LIKE ?) "
					+ "AND d.create_date BETWEEN ? AND ? ORDER BY d.create_date DESC "
					+ "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
	
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql)) {
	
				stmt.setString(1, "%" + query + "%");
				stmt.setString(2, "%" + query + "%");
				stmt.setDate(3, Date.valueOf(minDate)); // Convert String to SQL Date
				stmt.setDate(4, Date.valueOf(maxDate));
				stmt.setInt(5, offset);
				stmt.setInt(6, limit);
	
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						designs.add(mapResultSetToDesigns(rs));
					}
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return designs;
		}
	
		public List<Designs> getDesignsByQueryWithPagination(String query, int offset, int limit) {
			List<Designs> designs = new ArrayList<>();
			String sql = "SELECT d.design_id, d.title, d.content, d.image, d.create_date, d.status "
					+ "FROM designs d WHERE d.title LIKE ? OR d.content LIKE ? "
					+ "ORDER BY d.create_date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
	
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql)) {
	
				stmt.setString(1, "%" + query + "%");
				stmt.setString(2, "%" + query + "%");
				stmt.setInt(3, offset);
				stmt.setInt(4, limit);
	
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						designs.add(mapResultSetToDesigns(rs));
					}
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return designs;
		}
	
		public int getTotalDesignsByQuery(String query) {
			String sql = "SELECT COUNT(*) AS total FROM designs d WHERE d.title LIKE ? OR d.content LIKE ?";
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql)) {
	
				stmt.setString(1, "%" + query + "%");
				stmt.setString(2, "%" + query + "%");
	
				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						return rs.getInt("total");
					}
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return 0;
		}
	
		public List<Designs> getDesignsByDateRangeWithPagination(String minDate, String maxDate, int offset, int limit) {
			List<Designs> designs = new ArrayList<>();
			String sql = "SELECT d.design_id, d.title, d.content, d.image, d.create_date, d.status "
					+ "FROM designs d WHERE d.create_date BETWEEN ? AND ? "
					+ "ORDER BY d.create_date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
	
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql)) {
	
				stmt.setDate(1, Date.valueOf(minDate)); // Convert String to SQL Date
				stmt.setDate(2, Date.valueOf(maxDate));
				stmt.setInt(3, offset);
				stmt.setInt(4, limit);
	
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						designs.add(mapResultSetToDesigns(rs));
					}
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return designs;
		}
	
		public int getTotalDesignsByDateRange(String minDate, String maxDate) {
			String sql = "SELECT COUNT(*) AS total FROM designs d WHERE d.create_date BETWEEN ? AND ?";
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql)) {
	
				stmt.setDate(1, Date.valueOf(minDate)); // Convert String to SQL Date
				stmt.setDate(2, Date.valueOf(maxDate));
	
				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						return rs.getInt("total");
					}
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return 0;
		}
	
		public int getTotalDesignsByQueryAndDate(String query, String minDate, String maxDate) {
			String sql = "SELECT COUNT(*) AS total FROM designs d WHERE (d.title LIKE ? OR d.content LIKE ?) "
					+ "AND d.create_date BETWEEN ? AND ?";
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql)) {
	
				stmt.setString(1, "%" + query + "%");
				stmt.setString(2, "%" + query + "%");
				stmt.setDate(3, Date.valueOf(minDate)); // Convert String to SQL Date
				stmt.setDate(4, Date.valueOf(maxDate));
	
				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						return rs.getInt("total");
					}
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return 0;
		}
	
		public List<Designs> getDesignsByProductNameWithPagination(String productName, int offset, int limit) {
			List<Designs> designs = new ArrayList<>();
			String sql = "SELECT DISTINCT d.design_id, d.title, d.content, d.image, d.create_date, d.status "
					+ "FROM designs d " + "JOIN design_items di ON d.design_id = di.design_id "
					+ "JOIN products p ON di.product_id = p.product_id " + "WHERE p.name LIKE ? "
					+ "ORDER BY d.create_date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
	
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql)) {
	
				stmt.setString(1, "%" + productName + "%");
				stmt.setInt(2, offset);
				stmt.setInt(3, limit);
	
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						designs.add(mapResultSetToDesigns(rs));
					}
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return designs;
		}
	
		public int getTotalDesignsByProductName(String productName) {
			String sql = "SELECT COUNT(DISTINCT d.design_id) AS total " + "FROM designs d "
					+ "JOIN design_items di ON d.design_id = di.design_id "
					+ "JOIN products p ON di.product_id = p.product_id " + "WHERE p.name LIKE ?";
	
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql)) {
	
				stmt.setString(1, "%" + productName + "%");
	
				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						return rs.getInt("total");
					}
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return 0;
		}
	
		public List<Designs> getDesignsByProductAndQueryWithPagination(String productName, String query, int offset,
				int limit) {
			List<Designs> designs = new ArrayList<>();
			String sql = "SELECT DISTINCT d.design_id, d.title, d.content, d.image, d.create_date, d.status "
					+ "FROM designs d " + "JOIN design_items di ON d.design_id = di.design_id "
					+ "JOIN products p ON di.product_id = p.product_id "
					+ "WHERE p.name LIKE ? AND (d.title LIKE ? OR d.content LIKE ?) "
					+ "ORDER BY d.create_date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
	
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql)) {
	
				stmt.setString(1, "%" + productName + "%");
				stmt.setString(2, "%" + query + "%");
				stmt.setString(3, "%" + query + "%");
				stmt.setInt(4, offset);
				stmt.setInt(5, limit);
	
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						designs.add(mapResultSetToDesigns(rs));
					}
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return designs;
		}
	
		public int getTotalDesignsByProductAndQuery(String productName, String query) {
			String sql = "SELECT COUNT(DISTINCT d.design_id) AS total " + "FROM designs d "
					+ "JOIN design_items di ON d.design_id = di.design_id "
					+ "JOIN products p ON di.product_id = p.product_id "
					+ "WHERE p.name LIKE ? AND (d.title LIKE ? OR d.content LIKE ?)";
	
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql)) {
	
				stmt.setString(1, "%" + productName + "%");
				stmt.setString(2, "%" + query + "%");
				stmt.setString(3, "%" + query + "%");
	
				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						return rs.getInt("total");
					}
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return 0;
		}
	
		public List<Designs> getDesignsByProductQueryAndDateWithPagination(String productName, String query, String minDate,
				String maxDate, int offset, int limit) {
			List<Designs> designs = new ArrayList<>();
			String sql = "SELECT DISTINCT d.design_id, d.title, d.content, d.image, d.create_date, d.status "
					+ "FROM designs d " + "JOIN design_items di ON d.design_id = di.design_id "
					+ "JOIN products p ON di.product_id = p.product_id "
					+ "WHERE p.name LIKE ? AND (d.title LIKE ? OR d.content LIKE ?) " + "AND d.create_date BETWEEN ? AND ? "
					+ "ORDER BY d.create_date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
	
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql)) {
	
				stmt.setString(1, "%" + productName + "%");
				stmt.setString(2, "%" + query + "%");
				stmt.setString(3, "%" + query + "%");
				stmt.setDate(4, Date.valueOf(minDate));
				stmt.setDate(5, Date.valueOf(maxDate));
				stmt.setInt(6, offset);
				stmt.setInt(7, limit);
	
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						designs.add(mapResultSetToDesigns(rs));
					}
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return designs;
		}
	
		public int getTotalDesignsByProductQueryAndDate(String productName, String query, String minDate, String maxDate) {
			String sql = "SELECT COUNT(DISTINCT d.design_id) AS total " + "FROM designs d "
					+ "JOIN design_items di ON d.design_id = di.design_id "
					+ "JOIN products p ON di.product_id = p.product_id "
					+ "WHERE p.name LIKE ? AND (d.title LIKE ? OR d.content LIKE ?) " + "AND d.create_date BETWEEN ? AND ?";
	
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql)) {
	
				stmt.setString(1, "%" + productName + "%");
				stmt.setString(2, "%" + query + "%");
				stmt.setString(3, "%" + query + "%");
				stmt.setDate(4, Date.valueOf(minDate));
				stmt.setDate(5, Date.valueOf(maxDate));
	
				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						return rs.getInt("total");
					}
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return 0;
		}
	
		public List<Designs> getDesignsByProductAndDateWithPagination(String productName, String minDate, String maxDate,
				int offset, int limit) {
			List<Designs> designs = new ArrayList<>();
			String sql = "SELECT DISTINCT d.design_id, d.title, d.content, d.image, d.create_date, d.status "
					+ "FROM designs d " + "JOIN design_items di ON d.design_id = di.design_id "
					+ "JOIN products p ON di.product_id = p.product_id "
					+ "WHERE p.name LIKE ? AND d.create_date BETWEEN ? AND ? "
					+ "ORDER BY d.create_date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
	
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql)) {
	
				stmt.setString(1, "%" + productName + "%");
				stmt.setDate(2, Date.valueOf(minDate));
				stmt.setDate(3, Date.valueOf(maxDate));
				stmt.setInt(4, offset);
				stmt.setInt(5, limit);
	
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						designs.add(mapResultSetToDesigns(rs));
					}
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return designs;
		}
	
		public int getTotalDesignsByProductAndDate(String productName, String minDate, String maxDate) {
			String sql = "SELECT COUNT(DISTINCT d.design_id) AS total " + "FROM designs d "
					+ "JOIN design_items di ON d.design_id = di.design_id "
					+ "JOIN products p ON di.product_id = p.product_id "
					+ "WHERE p.name LIKE ? AND d.create_date BETWEEN ? AND ?";
	
			try (Connection conn = new DBConnection().getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql)) {
	
				stmt.setString(1, "%" + productName + "%");
				stmt.setDate(2, Date.valueOf(minDate));
				stmt.setDate(3, Date.valueOf(maxDate));
	
				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						return rs.getInt("total");
					}
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return 0;
		}
	
	}
