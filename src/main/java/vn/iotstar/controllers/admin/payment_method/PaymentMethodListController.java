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
import java.util.List;

@WebServlet(urlPatterns = { "/admin/payment-method"})
public class PaymentMethodListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IPaymentService paymentMethodService = new PaymentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PaymentMethod> paymentMethods = paymentMethodService.findAll();
        req.setAttribute("paymentMethods", paymentMethods);
        req.getRequestDispatcher(Constant.PAYMENT_METHOD_LIST).forward(req, resp);
    }

}
