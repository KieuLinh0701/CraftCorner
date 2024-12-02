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
    	String percentStr = req.getParameter("percent");
        List<Promote> promotes;

        if (percentStr != null && !percentStr.isEmpty()) {
            try {
                int percent = Integer.parseInt(percentStr);
                // Tìm kiếm các chương trình khuyến mãi theo phần trăm giảm giá
                promotes = promoteService.findByPercent(percent);
            } catch (NumberFormatException e) {
                // Xử lý lỗi nếu tham số không hợp lệ
                req.setAttribute("error", "Giá trị phần trăm không hợp lệ.");
                promotes = promoteService.findAll(); // Hiển thị tất cả nếu có lỗi
            }
        } else {
            // Nếu không có tham số, hiển thị tất cả chương trình khuyến mãi
            promotes = promoteService.findAll();
        }        req.setAttribute("promotes", promotes);
        req.getRequestDispatcher(Constant.PROMOTE_LIST).forward(req, resp);
    }

}
