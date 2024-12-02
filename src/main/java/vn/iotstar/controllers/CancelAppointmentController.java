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
import java.util.Objects;

@WebServlet(urlPatterns = {"/appointment/customer-cancel"})
public class CancelAppointmentController extends HttpServlet{

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

        int appointmentId = Integer.parseInt(req.getParameter("appointmentId"));

        Appointment appointment = appointmentService.getAppointmentById(appointmentId);

        if(appointment == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Appointment not found");
            return;
        }

        if(!Objects.equals(appointment.getStatus(), Constant.APPOINTMENT_PENDING)){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Appointment can not be cancelled");
            return;
        }

        if(appointment.getCustomer().getId() != user.getId()){
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Request Forbidden");
            return;
        }

        appointment.setStatus(Constant.APPOINTMENT_CANCEL);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        appointment.setUpdateAt(Timestamp.valueOf(now.format(formatter)));
        appointmentService.update(appointment);

        resp.sendRedirect(req.getContextPath() + "/appointment");
    }

}
