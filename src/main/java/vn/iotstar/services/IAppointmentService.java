package vn.iotstar.services;


import vn.iotstar.entity.Appointment;
import vn.iotstar.entity.Promote;

import java.sql.Timestamp;
import java.util.List;

public interface IAppointmentService {

    void insert(Appointment appointment);

    List<Appointment> getAppointmentsByCustomerId(int customerId);

    Appointment getAppointmentById(int id);

    void update(Appointment appointment);

    List<Appointment> getAppointments();

    List<Appointment> getAppointmentsByDate(Timestamp date);
}
