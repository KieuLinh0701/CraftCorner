package vn.iotstar.controllers.admin.promote;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;
import vn.iotstar.entity.PaymentMethod;
import vn.iotstar.entity.Promote;
import vn.iotstar.services.IPromoteService;
import vn.iotstar.services.implement.PromoteService;
import vn.iotstar.utils.Constant;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(urlPatterns = { "/admin/promote/edit"})
public class PromoteEditController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final IPromoteService promoteService = new PromoteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Promote promote = promoteService.findById(id);
        if (promote != null) {
            req.setAttribute("promote", promote);
            req.getRequestDispatcher(Constant.PROMOTE_EDIT).forward(req, resp);

        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Promote not found");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Promote promote = promoteService.findById(id);

        if (promote != null) {
            promote.setVoucherCode(req.getParameter("voucherCode"));
            promote.setStartDate(LocalDateTime.parse(req.getParameter("startDate")));
            promote.setEndDate(LocalDateTime.parse(req.getParameter("endDate")));
            promote.setMinOrderTotal(Double.parseDouble(req.getParameter("minOrderTotal")));
            promote.setQuantity(Integer.parseInt(req.getParameter("quantity")));
            promote.setDiscountPercent(Integer.parseInt(req.getParameter("discountPercent")));

            promoteService.update(promote);

            resp.sendRedirect(req.getContextPath() + "/admin/promote");

        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Promote not found");
        }
    }

}
