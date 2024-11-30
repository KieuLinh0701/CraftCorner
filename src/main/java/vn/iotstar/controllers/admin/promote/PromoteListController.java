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

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/admin/promote"})
public class PromoteListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IPromoteService promoteService = new PromoteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Promote> promotes = promoteService.findAll();
        req.setAttribute("promotes", promotes);
        req.getRequestDispatcher(Constant.PROMOTE_LIST).forward(req, resp);
    }

}
