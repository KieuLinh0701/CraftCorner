package vn.iotstar.dao.implement;

import vn.iotstar.entity.Blog;
import vn.iotstar.entity.User;
import vn.iotstar.utils.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BlogDAO {

	// Lấy tất cả blog
	public List<Blog> getAllBlogs() {
		List<Blog> blogs = new ArrayList<>();
		String query = "SELECT b.blog_id, b.blog_title, b.content, b.image, b.created_at, b.status,b.user_id "
				+ "FROM blogs b ORDER BY b.created_at DESC";

		try (Connection conn = new DBConnection().getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				
				Blog blog = mapResultSetToBlog(rs);
				// Lấy thông tin User
                int userId = rs.getInt("user_id");
                System.out.println(userId);

                User user = getUserById(userId, conn);
                blog.setUser(user);
                
                blogs.add(blog);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return blogs;
	}

	// Lấy blog theo ID
	public Blog getBlogById(int blogId) {
		Blog blog = null;
		String query = "SELECT b.blog_id, b.blog_title, b.content, b.image, b.created_at, b.status,b.user_id "
				+ "FROM blogs b WHERE b.blog_id = ?";

		try (Connection conn = new DBConnection().getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, blogId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					blog = mapResultSetToBlog(rs);
					
					// Lấy thông tin User
	                int userId = rs.getInt("user_id");
	                System.out.println(userId);

	                User user = getUserById(userId, conn);
	                blog.setUser(user);
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return blog;
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

	// Lấy blog theo phân trang
	public List<Blog> getBlogsByPage(int offset, int limit) {
		List<Blog> blogs = new ArrayList<>();
		String query = "SELECT b.blog_id, b.blog_title, b.content, b.image, b.created_at, b.status "
				+ "FROM blogs b ORDER BY b.created_at DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

		try (Connection conn = new DBConnection().getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, offset);
			stmt.setInt(2, limit);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					blogs.add(mapResultSetToBlog(rs));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return blogs;
	}

	// Lấy tổng số blog
	public int getTotalBlogs() {
		String query = "SELECT COUNT(*) AS total FROM blogs";
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

	// Chuyển đổi từ ResultSet sang Blog
	private Blog mapResultSetToBlog(ResultSet rs) throws SQLException {
		Blog blog = new Blog();
		blog.setBlogId(rs.getInt("blog_id"));
		blog.setBlogTitle(rs.getString("blog_title"));
		blog.setContent(rs.getString("content"));
		blog.setImage(rs.getString("image"));
		blog.setCreatedAt(rs.getDate("created_at").toLocalDate());
		blog.setStatus(rs.getInt("status"));
		return blog;
	}

	// Thêm một blog
	public void addBlog(Blog blog) {
		String query = "INSERT INTO blogs (blog_title, content, image, created_at, status) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = new DBConnection().getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, blog.getBlogTitle());
			stmt.setString(2, blog.getContent());
			stmt.setString(3, blog.getImage());
			stmt.setDate(4, Date.valueOf(blog.getCreatedAt()));
			stmt.setInt(5, blog.getStatus());

			stmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Cập nhật một blog
	public void updateBlog(Blog blog) {
		String query = "UPDATE blogs SET blog_title = ?, content = ?, image = ?, created_at = ?, status = ? WHERE blog_id = ?";
		try (Connection conn = new DBConnection().getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, blog.getBlogTitle());
			stmt.setString(2, blog.getContent());
			stmt.setString(3, blog.getImage());
			stmt.setDate(4, Date.valueOf(blog.getCreatedAt()));
			stmt.setInt(5, blog.getStatus());
			stmt.setInt(6, blog.getBlogId());

			stmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Xóa một blog
	public void deleteBlog(int blogId) {
		String query = "DELETE FROM blogs WHERE blog_id = ?";
		try (Connection conn = new DBConnection().getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, blogId);
			stmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Tìm blog theo từ khóa
	public List<Blog> getBlogsByQuery(String query) {
		List<Blog> blogs = new ArrayList<>();
		String sql = "SELECT b.blog_id, b.blog_title, b.content, b.image, b.created_at, b.status "
				+ "FROM blogs b WHERE b.blog_title LIKE ? OR b.content LIKE ? ORDER BY b.created_at DESC";

		try (Connection conn = new DBConnection().getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, "%" + query + "%");
			stmt.setString(2, "%" + query + "%");

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					blogs.add(mapResultSetToBlog(rs));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return blogs;
	}

	// Lấy blog theo khoảng ngày
	public List<Blog> getBlogsByDateRange(String minDate, String maxDate) {
		List<Blog> blogs = new ArrayList<>();
		String sql = "SELECT b.blog_id, b.blog_title, b.content, b.image, b.created_at, b.status "
				+ "FROM blogs b WHERE b.created_at BETWEEN ? AND ? ORDER BY b.created_at DESC";

		try (Connection conn = new DBConnection().getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setDate(1, Date.valueOf(minDate));
			stmt.setDate(2, Date.valueOf(maxDate));

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					blogs.add(mapResultSetToBlog(rs));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return blogs;
	}

	// Tìm blog theo từ khóa và khoảng ngày
	public List<Blog> getBlogsByQueryAndDate(String query, String minDate, String maxDate) {
		List<Blog> blogs = new ArrayList<>();
		String sql = "SELECT b.blog_id, b.blog_title, b.content, b.image, b.created_at, b.status "
				+ "FROM blogs b WHERE (b.blog_title LIKE ? OR b.content LIKE ?) "
				+ "AND b.created_at BETWEEN ? AND ? ORDER BY b.created_at DESC";

		try (Connection conn = new DBConnection().getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, "%" + query + "%");
			stmt.setString(2, "%" + query + "%");
			stmt.setDate(3, Date.valueOf(minDate));
			stmt.setDate(4, Date.valueOf(maxDate));

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					blogs.add(mapResultSetToBlog(rs));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return blogs;
	}

	// Tìm blog theo từ khóa, khoảng ngày, và phân trang
	public List<Blog> getBlogsByQueryAndDateWithPagination(String query, String minDate, String maxDate, int offset,
			int limit) {
		List<Blog> blogs = new ArrayList<>();
		String sql = "SELECT b.blog_id, b.blog_title, b.content, b.image, b.created_at, b.status "
				+ "FROM blogs b WHERE (b.blog_title LIKE ? OR b.content LIKE ?) "
				+ "AND b.created_at BETWEEN ? AND ? ORDER BY b.created_at DESC "
				+ "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

		try (Connection conn = new DBConnection().getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, "%" + query + "%");
			stmt.setString(2, "%" + query + "%");
			stmt.setDate(3, Date.valueOf(minDate));
			stmt.setDate(4, Date.valueOf(maxDate));
			stmt.setInt(5, offset);
			stmt.setInt(6, limit);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					blogs.add(mapResultSetToBlog(rs));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return blogs;
	}

	public List<Blog> getBlogsByQueryWithPagination(String query, int offset, int limit) {
		List<Blog> blogs = new ArrayList<>();
		String sql = "SELECT b.blog_id, b.blog_title, b.content, b.image, b.created_at, b.status "
				+ "FROM blogs b WHERE b.blog_title LIKE ? OR b.content LIKE ? "
				+ "ORDER BY b.created_at DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

		try (Connection conn = new DBConnection().getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, "%" + query + "%");
			stmt.setString(2, "%" + query + "%");
			stmt.setInt(3, offset);
			stmt.setInt(4, limit);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					blogs.add(mapResultSetToBlog(rs));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return blogs;
	}

	public int getTotalBlogsByQuery(String query) {
		String sql = "SELECT COUNT(*) AS total FROM blogs b WHERE b.blog_title LIKE ? OR b.content LIKE ?";
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

	public List<Blog> getBlogsByDateRangeWithPagination(String minDate, String maxDate, int offset, int limit) {
		List<Blog> blogs = new ArrayList<>();
		String sql = "SELECT b.blog_id, b.blog_title, b.content, b.image, b.created_at, b.status "
				+ "FROM blogs b WHERE b.created_at BETWEEN ? AND ? "
				+ "ORDER BY b.created_at DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

		try (Connection conn = new DBConnection().getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setDate(1, Date.valueOf(minDate)); // Convert String to SQL Date
			stmt.setDate(2, Date.valueOf(maxDate));
			stmt.setInt(3, offset);
			stmt.setInt(4, limit);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					blogs.add(mapResultSetToBlog(rs));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return blogs;
	}

	public int getTotalBlogsByDateRange(String minDate, String maxDate) {
		String sql = "SELECT COUNT(*) AS total FROM blogs b WHERE b.created_at BETWEEN ? AND ?";
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

	public int getTotalBlogsByQueryAndDate(String query, String minDate, String maxDate) {
		String sql = "SELECT COUNT(*) AS total FROM blogs b WHERE (b.blog_title LIKE ? OR b.content LIKE ?) "
				+ "AND b.created_at BETWEEN ? AND ?";
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

}
