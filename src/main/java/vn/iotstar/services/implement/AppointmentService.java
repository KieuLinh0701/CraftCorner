package vn.iotstar.services.implement;

import vn.iotstar.dao.IAppointmentDao;
import vn.iotstar.dao.IPromoteDao;
import vn.iotstar.dao.implement.AppointmentDao;
import vn.iotstar.dao.implement.PromoteDao;
import vn.iotstar.entity.Appointment;
import vn.iotstar.entity.Promote;
import vn.iotstar.services.IAppointmentService;
import vn.iotstar.services.IPromoteService;

import java.sql.Timestamp;
import java.util.List;

public class AppointmentService implements IAppointmentService {

    private final IAppointmentDao appointmentDao = new AppointmentDao();

    @Override
    public void insert(Appointment appointment) {
        appointmentDao.insert(appointment);
    }

    @Override
    public List<Appointment> getAppointmentsByCustomerId(int customerId) {
        return appointmentDao.getAppointmentsByCustomerId(customerId);
    }

    @Override
    public Appointment getAppointmentById(int id) {
        return appointmentDao.findAppointmentById(id);
    }

    @Override
    public void update(Appointment appointment) {
        appointmentDao.update(appointment);
    }

    @Override
    public List<Appointment> getAppointments() {
        return appointmentDao.findALl();
    }

    @Override
    public List<Appointment> getAppointmentsByDate(Timestamp date) {
        return appointmentDao.findALlByDate(date);
    }

}
