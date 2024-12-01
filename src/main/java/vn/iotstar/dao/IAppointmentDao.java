package vn.iotstar.dao;

import vn.iotstar.entity.Appointment;

import java.sql.Timestamp;
import java.util.List;

public interface IAppointmentDao {

    void insert(Appointment appointment);

    List<Appointment> getAppointmentsByCustomerId(int customerId);

    Appointment findAppointmentById(int appointmentId);

    void update(Appointment appointment);

    List<Appointment> findALl();

    List<Appointment> findALlByDate(Timestamp appointmentDate);
}
