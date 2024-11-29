package vn.iotstar.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "blogs")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private int blogId;

    @Column(name = "blog_title", columnDefinition = "NVARCHAR(255) NOT NULL")
    private String blogTitle;

    @Column(name = "content", columnDefinition = "TEXT NOT NULL")
    private String content;

    @Column(name = "image", columnDefinition = "NVARCHAR(500) NULL")
    private String image;

    // Quan hệ "Nhiều blog thuộc về một User" và user này là user tạo ra
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    // Ngày tạo (chỉ lưu ngày, tháng, năm)
    @Column(name = "created_at")
    private LocalDate createdAt;
    
 // Trạng thái (1 là đã xuất bản, 0 là chưa xuất bản, v.v.)
    @Column(name = "status", columnDefinition = "INT DEFAULT 1")
    private int status;

    // Constructors
    public Blog() {
    }

    public Blog(String blogTitle, String content, String image, User user, LocalDate createdAt,int status) {
        this.blogTitle = blogTitle;
        this.content = content;
        this.image = image;
        this.user = user;
        this.createdAt = createdAt;
        this.status = status;
    }

    // Getters and Setters
    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
