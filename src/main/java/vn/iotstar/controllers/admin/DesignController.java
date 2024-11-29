package vn.iotstar.controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Designs;
import vn.iotstar.services.IDesignService;
import vn.iotstar.services.implement.DesignService;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = { "/admin/designs", "/admin/designdetail" })
public class DesignController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public IDesignService designService = new DesignService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("designs")) {
			List<Designs> listDesign = designService.findAll();
			if (listDesign.size() == 0) {
				req.setAttribute("alert", "No design templates are currently available for display");
			} else {
				req.setAttribute("listDesign", listDesign);
			}
			req.getRequestDispatcher(Constant.DESIGN_MANAGEMENT).forward(req, resp);
		} else if (url.contains("designdetail")) {
			int designId = Integer.parseInt(req.getParameter("id"));
			Designs design = designService.findById(designId);
			req.setAttribute("design", design);
			req.getRequestDispatcher(Constant.DESIGN_DETAIL_MANAGEMENT).forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
	}

}
