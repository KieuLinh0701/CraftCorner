package vn.iotstar.controllers.admin.appointment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Appointment;
import vn.iotstar.entity.User;
import vn.iotstar.services.IAppointmentService;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.implement.AppointmentService;
import vn.iotstar.services.implement.UserService;
import vn.iotstar.utils.Constant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/admin/appointment-calendar"})
public class CalendarAppointmentController extends HttpServlet{

    private static final long serialVersionUID = 1L;


    private IAppointmentService appointmentService = new AppointmentService();

    private IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Appointment> appointments = appointmentService.getAppointments();

        Map<String, Integer> groupedAppointments = new HashMap<>();

        for (Appointment appointment : appointments) {
            String dateKey = appointment.getAppointmentDate().toLocalDateTime().toLocalDate().toString(); // Lấy ngày từ appointmentDate
            groupedAppointments.put(dateKey, groupedAppointments.getOrDefault(dateKey, 0) + 1); // Tăng số lượng cho ngày đó
        }

        List<Map<String, Object>> events = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : groupedAppointments.entrySet()) {
            String date = entry.getKey();
            int count = entry.getValue();
            String title = count + " người đặt lịch"; // Tạo tiêu đề

            String url = "/admin/appointment?date=" + date;

            // Tạo sự kiện
            Map<String, Object> event = new HashMap<>();
            event.put("title", title);
            event.put("start", date);
            event.put("url", url);
            event.put("allDay", true);
            event.put("rendering", "background");
            events.add(event);
        }
        

        req.setAttribute("events", events);
        req.getRequestDispatcher(Constant.ADMIN_APPOINTMENT_CALENDAR).forward(req, resp);
    }
}
