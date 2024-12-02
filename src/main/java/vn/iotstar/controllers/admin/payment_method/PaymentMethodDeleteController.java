package vn.iotstar.controllers.admin.payment_method;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.PaymentMethod;
import vn.iotstar.services.IPaymentService;
import vn.iotstar.services.implement.PaymentService;

import java.io.IOException;

@WebServlet(urlPatterns = { "/admin/payment-method/delete"})
public class PaymentMethodDeleteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final IPaymentService paymentMethodService = new PaymentService();

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        PaymentMethod paymentMethod = paymentMethodService.findById(id);

        if(paymentMethod != null) {
            paymentMethodService.delete(paymentMethod);
            resp.sendRedirect(req.getContextPath() + "/admin/payment-method");
        }else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Payment method not found");
        }
    }

}
