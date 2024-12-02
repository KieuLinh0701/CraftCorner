package vn.iotstar.controllers.admin.promote;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Promote;
import vn.iotstar.services.IPromoteService;
import vn.iotstar.services.implement.PromoteService;

import java.io.IOException;

@WebServlet(urlPatterns = { "/admin/promote/delete"})
public class PromoteDeleteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final IPromoteService promoteService = new PromoteService();

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Promote promote = promoteService.findById(id);

        if(promote != null) {
            promoteService.delete(promote);
            resp.sendRedirect(req.getContextPath() + "/admin/promote");
        }else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Promote not found");
        }
    }

}
