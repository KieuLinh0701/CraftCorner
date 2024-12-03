package vn.iotstar.dao.implement;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IOrderDao;
import vn.iotstar.entity.Order;

public class OrderDao implements IOrderDao {

    @Override
    public Order insert(Order order) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(order);
            Order newOrder = enma.merge(order);
            trans.commit();
            return newOrder;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    public Long getTotalOrders(String periodType, String periodValue) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        Long result = 0L;
        try {
            trans.begin();

            // Khởi tạo câu truy vấn chung
            String queryStr = "SELECT COUNT(o.order_id) FROM Order o WHERE 1=1"; 

            if (periodType.equals("date")) {
                queryStr += " AND DATE(o.order_date) = :periodValue";
            } else if (periodType.equals("month")) {
                queryStr += " AND MONTH(o.order_date) = :month AND YEAR(o.order_date) = :year";
            } else if (periodType.equals("year")) {
                queryStr += " AND YEAR(o.order_date) = :year";
            }

            // Tạo truy vấn từ câu lệnh HQL
            Query query = enma.createQuery(queryStr);

            // Đặt tham số theo loại thời gian
            if ("date".equals(periodType)) {
                query.setParameter("periodValue", LocalDate.parse(periodValue));
            } else if ("month".equals(periodType)) {
                String[] monthYear = periodValue.split("-");
                query.setParameter("month", Integer.parseInt(monthYear[1]));  // Tháng
                query.setParameter("year", Integer.parseInt(monthYear[0]));   // Năm
            } else if ("year".equals(periodType)) {
                query.setParameter("year", Integer.parseInt(periodValue));
            }

            // Thực hiện truy vấn và trả kết quả
            result = (Long) query.getSingleResult();
            trans.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            if (trans.isActive()) {
                trans.rollback();
            }
            throw e;
        } finally {
            if (enma != null && enma.isOpen()) {
                enma.close();
            }
        }
    }


    public Double getTotalRevenue(String periodType, String periodValue) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            String queryStr = "SELECT SUM(o.total_price) FROM Order o WHERE 1=1"; // Đảm bảo có điều kiện "WHERE 1=1"

            // Xử lý theo từng kiểu thời gian
            if (periodType.equals("date")) {
                queryStr += " AND DATE(o.order_date) = :periodValue";
            } else if (periodType.equals("month")) {
                queryStr += " AND MONTH(o.order_date) = :month AND YEAR(o.order_date) = :year";
            } else if (periodType.equals("year")) {
                queryStr += " AND YEAR(o.order_date) = :year";
            }

            Query query = enma.createQuery(queryStr);

            // Thiết lập tham số cho câu truy vấn
            if (periodType.equals("date")) {
                query.setParameter("periodValue", periodValue);
            } else if (periodType.equals("month")) {
                query.setParameter("month", Integer.parseInt(periodValue.split("-")[1]));
                query.setParameter("year", Integer.parseInt(periodValue.split("-")[0]));
            } else if (periodType.equals("year")) {
                query.setParameter("year", Integer.parseInt(periodValue));
            }

            Double result = (Double) query.getSingleResult();
            trans.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    public String getHighestRevenueProduct(String periodType, String periodValue) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            String queryStr = "SELECT p.name FROM OrderDetail od JOIN od.product p WHERE 1=1";
            
            // Xử lý theo từng kiểu thời gian
            if (periodType.equals("date")) {
                queryStr += " AND DATE(od.order.order_date) = :periodValue GROUP BY p.name ORDER BY SUM(od.price * od.quantity) DESC";
            } else if (periodType.equals("month")) {
                queryStr += " AND MONTH(od.order.order_date) = :month AND YEAR(od.order.order_date) = :year GROUP BY p.name ORDER BY SUM(od.price * od.quantity) DESC";
            } else if (periodType.equals("year")) {
                queryStr += " AND YEAR(od.order.order_date) = :year GROUP BY p.name ORDER BY SUM(od.price * od.quantity) DESC";
            }
            
            Query query = enma.createQuery(queryStr).setMaxResults(1);
            
            // Thiết lập tham số cho câu truy vấn
            if (periodType.equals("date")) {
                query.setParameter("periodValue", periodValue);
            } else if (periodType.equals("month")) {
                query.setParameter("month", Integer.parseInt(periodValue.split("-")[1]));
                query.setParameter("year", Integer.parseInt(periodValue.split("-")[0]));
            } else if (periodType.equals("year")) {
                query.setParameter("year", Integer.parseInt(periodValue));
            }

            String result = (String) query.getSingleResult();
            trans.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    public String getLowestRevenueProduct(String periodType, String periodValue) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            String queryStr = "SELECT p.name FROM OrderDetail od JOIN od.product p WHERE 1=1";
            
            // Xử lý theo từng kiểu thời gian
            if (periodType.equals("date")) {
                queryStr += " AND DATE(od.order.order_date) = :periodValue GROUP BY p.name ORDER BY SUM(od.price * od.quantity) ASC";
            } else if (periodType.equals("month")) {
                queryStr += " AND MONTH(od.order.order_date) = :month AND YEAR(od.order.order_date) = :year GROUP BY p.name ORDER BY SUM(od.price * od.quantity) ASC";
            } else if (periodType.equals("year")) {
                queryStr += " AND YEAR(od.order.order_date) = :year GROUP BY p.name ORDER BY SUM(od.price * od.quantity) ASC";
            }
            
            Query query = enma.createQuery(queryStr).setMaxResults(1);
            
            // Thiết lập tham số cho câu truy vấn
            if (periodType.equals("date")) {
                query.setParameter("periodValue", periodValue);
            } else if (periodType.equals("month")) {
                query.setParameter("month", Integer.parseInt(periodValue.split("-")[1]));
                query.setParameter("year", Integer.parseInt(periodValue.split("-")[0]));
            } else if (periodType.equals("year")) {
                query.setParameter("year", Integer.parseInt(periodValue));
            }

            String result = (String) query.getSingleResult();
            trans.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }


    public String getMostPurchasedProduct(String periodType, String periodValue) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            String queryStr = "SELECT p.name FROM OrderDetail od JOIN od.product p WHERE 1=1";
            
            // Xử lý theo từng kiểu thời gian
            if (periodType.equals("date")) {
                queryStr += " AND DATE(od.order.order_date) = :periodValue GROUP BY p.name ORDER BY SUM(od.quantity) DESC";
            } else if (periodType.equals("month")) {
                queryStr += " AND MONTH(od.order.order_date) = :month AND YEAR(od.order.order_date) = :year GROUP BY p.name ORDER BY SUM(od.quantity) DESC";
            } else if (periodType.equals("year")) {
                queryStr += " AND YEAR(od.order.order_date) = :year GROUP BY p.name ORDER BY SUM(od.quantity) DESC";
            }
            
            Query query = enma.createQuery(queryStr).setMaxResults(1);
            
            // Thiết lập tham số cho câu truy vấn
            if (periodType.equals("date")) {
                query.setParameter("periodValue", periodValue);
            } else if (periodType.equals("month")) {
                query.setParameter("month", Integer.parseInt(periodValue.split("-")[1]));
                query.setParameter("year", Integer.parseInt(periodValue.split("-")[0]));
            } else if (periodType.equals("year")) {
                query.setParameter("year", Integer.parseInt(periodValue));
            }

            String result = (String) query.getSingleResult();
            trans.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }
    
    public String getLeastPurchasedProduct(String periodType, String periodValue) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            String queryStr = "SELECT p.name FROM OrderDetail od JOIN od.product p WHERE 1=1";
            
            // Xử lý theo từng kiểu thời gian
            if (periodType.equals("date")) {
                queryStr += " AND DATE(od.order.order_date) = :periodValue GROUP BY p.name ORDER BY SUM(od.quantity) ASC";
            } else if (periodType.equals("month")) {
                queryStr += " AND MONTH(od.order.order_date) = :month AND YEAR(od.order.order_date) = :year GROUP BY p.name ORDER BY SUM(od.quantity) ASC";
            } else if (periodType.equals("year")) {
                queryStr += " AND YEAR(od.order.order_date) = :year GROUP BY p.name ORDER BY SUM(od.quantity) ASC";
            }
            
            Query query = enma.createQuery(queryStr).setMaxResults(1);
            
            // Thiết lập tham số cho câu truy vấn
            if (periodType.equals("date")) {
                query.setParameter("periodValue", periodValue);
            } else if (periodType.equals("month")) {
                query.setParameter("month", Integer.parseInt(periodValue.split("-")[1]));
                query.setParameter("year", Integer.parseInt(periodValue.split("-")[0]));
            } else if (periodType.equals("year")) {
                query.setParameter("year", Integer.parseInt(periodValue));
            }

            String result = (String) query.getSingleResult();
            trans.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }
    
    public Object[] getOrderDetailsByPeriod(String periodType, String periodValue) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            
            // Câu truy vấn cho số lượng đơn hàng
            String orderQueryStr = "SELECT COUNT(o) FROM Order o WHERE 1=1";
            // Câu truy vấn cho tổng doanh thu
            String revenueQueryStr = "SELECT SUM(o.total_price) FROM Order o WHERE 1=1";

            // Xử lý theo từng loại thời gian
            if (periodType.equals("date")) {
                orderQueryStr += " AND DATE(o.order_date) = :periodValue";
                revenueQueryStr += " AND DATE(o.order_date) = :periodValue";
            } else if (periodType.equals("month")) {
                orderQueryStr += " AND MONTH(o.order_date) = :month AND YEAR(o.order_date) = :year";
                revenueQueryStr += " AND MONTH(o.order_date) = :month AND YEAR(o.order_date) = :year";
            } else if (periodType.equals("year")) {
                orderQueryStr += " AND YEAR(o.order_date) = :year";
                revenueQueryStr += " AND YEAR(o.order_date) = :year";
            } else {
                return new Object[] {new Long(0), 0.0}; // Trả về giá trị mặc định nếu periodType không hợp lệ
            }

            // Tạo và thiết lập câu truy vấn cho số lượng đơn hàng
            Query orderQuery = enma.createQuery(orderQueryStr);
            // Tạo và thiết lập câu truy vấn cho doanh thu
            Query revenueQuery = enma.createQuery(revenueQueryStr);

            // Thiết lập tham số cho câu truy vấn số lượng đơn hàng
            if (periodType.equals("date")) {
                orderQuery.setParameter("periodValue", periodValue);
                revenueQuery.setParameter("periodValue", periodValue);
            } else if (periodType.equals("month")) {
                String[] monthYear = periodValue.split("-");
                orderQuery.setParameter("month", Integer.parseInt(monthYear[1]));  // Tháng
                orderQuery.setParameter("year", Integer.parseInt(monthYear[0]));   // Năm
                revenueQuery.setParameter("month", Integer.parseInt(monthYear[1]));  // Tháng
                revenueQuery.setParameter("year", Integer.parseInt(monthYear[0]));   // Năm
            } else if (periodType.equals("year")) {
                orderQuery.setParameter("year", Integer.parseInt(periodValue));  // Năm
                revenueQuery.setParameter("year", Integer.parseInt(periodValue));  // Năm
            }

            // Thực thi câu truy vấn và lấy kết quả
            Long orderCount = (Long) orderQuery.getSingleResult();
            Double totalRevenue = (Double) revenueQuery.getSingleResult();

            // Trả về danh sách kết quả theo yêu cầu
            trans.commit();
            return new Object[] {orderCount, totalRevenue};
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

}
