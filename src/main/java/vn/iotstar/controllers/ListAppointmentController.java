package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.Appointment;
import vn.iotstar.entity.User;
import vn.iotstar.services.IAppointmentService;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.implement.AppointmentService;
import vn.iotstar.services.implement.UserService;
import vn.iotstar.utils.Constant;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(urlPatterns = {"/appointment"})
public class ListAppointmentController extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public IUserService userService = new UserService();

    private IAppointmentService appointmentService = new AppointmentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("account");

        if (user == null){
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        List<Appointment> appointments = appointmentService.getAppointmentsByCustomerId(user.getId());
        req.setAttribute("appointments", appointments);

        req.getRequestDispatcher(Constant.LIST_APPOINTMENT).forward(req, resp);
    }
}
