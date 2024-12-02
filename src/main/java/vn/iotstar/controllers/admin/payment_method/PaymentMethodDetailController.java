package vn.iotstar.controllers.admin.payment_method;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.entity.PaymentMethod;
import vn.iotstar.services.IPaymentService;
import vn.iotstar.services.implement.PaymentService;
import vn.iotstar.utils.Constant;

import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/payment-method/detail"})
public class PaymentMethodDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IPaymentService paymentMethodService = new PaymentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Lấy ID từ request parameter
            String idParam = req.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                resp.sendRedirect(req.getContextPath() + "/admin/payment-method"); // Nếu không có ID, quay lại danh sách
                return;
            }

            // Chuyển đổi ID sang kiểu int
            int id = Integer.parseInt(idParam);

            // Tìm phương thức thanh toán theo ID
            PaymentMethod paymentMethod = paymentMethodService.findById(id);

            if (paymentMethod == null) {
                resp.sendRedirect(req.getContextPath() + "/admin/payment-method"); // Nếu không tìm thấy, quay lại danh sách
                return;
            }

            // Đặt đối tượng PaymentMethod vào request attribute
            req.setAttribute("paymentMethod", paymentMethod);

            // Forward đến trang hiển thị chi tiết
            req.getRequestDispatcher(Constant.PAYMENT_METHOD_DETAIL).forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/payment-method"); // Xử lý trường hợp ID không hợp lệ
        }
    }
}
