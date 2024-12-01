package vn.iotstar.controllers.admin.appointment;

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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/admin/appointment"})
public class ListAppointmentController extends HttpServlet{

    private static final long serialVersionUID = 1L;


    private IAppointmentService appointmentService = new AppointmentService();

    private IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dateParam = req.getParameter("date");

        List<Appointment> appointments = null; 
        
         if (dateParam != null && !dateParam.isEmpty()) {
            LocalDate date = LocalDate.parse(dateParam); 
            appointments = appointmentService.getAppointmentsByDate(Timestamp.valueOf(date.atStartOfDay()));
        } else {
            appointments = appointmentService.getAppointments();
        }

        List<User> consultants = userService.findAll();

        List<User> admins = consultants.stream()
                .filter(user -> "ADMIN".equalsIgnoreCase(user.getRole().getName()))
                .collect(Collectors.toList());

        req.setAttribute("appointments", appointments);

        req.setAttribute("consultants", admins);

        req.getRequestDispatcher(Constant.ADMIN_APPOINTMENT_LIST).forward(req, resp);
    }
}
