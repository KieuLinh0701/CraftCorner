package vn.iotstar.controllers.admin.promote;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Promote;
import vn.iotstar.services.IPromoteService;
import vn.iotstar.services.implement.PromoteService;
import vn.iotstar.utils.Constant;

import java.io.*;
import java.time.LocalDateTime;

@WebServlet(urlPatterns = { "/admin/promote/add"})
public class PromoteAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IPromoteService promoteService = new PromoteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Constant.PROMOTE_ADD).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Promote promote = new Promote();
        promote.setVoucherCode(req.getParameter("voucherCode"));
        promote.setStartDate(LocalDateTime.parse(req.getParameter("startDate")));
        promote.setEndDate(LocalDateTime.parse(req.getParameter("endDate")));
        promote.setQuantity(Integer.parseInt(req.getParameter("quantity")));
        promote.setMinOrderTotal(Double.parseDouble(req.getParameter("minOrderTotal")));
        promote.setDiscountPercent(Integer.parseInt(req.getParameter("discountPercent")));
        promote.setQuantityUsed(0);

        promoteService.insert(promote);
        resp.sendRedirect(req.getContextPath() + "/admin/promote");
    }
}
