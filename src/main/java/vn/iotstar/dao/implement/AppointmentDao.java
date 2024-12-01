package vn.iotstar.dao.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IAppointmentDao;
import vn.iotstar.dao.IPromoteDao;
import vn.iotstar.entity.Appointment;
import vn.iotstar.entity.PaymentMethod;
import vn.iotstar.entity.Promote;
import vn.iotstar.entity.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class AppointmentDao implements IAppointmentDao {

    @Override
    public void insert(Appointment appointment) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(appointment);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public List<Appointment> getAppointmentsByCustomerId(int customerId) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT a FROM Appointment a WHERE a.customer.id = :customerId";
        TypedQuery<Appointment> query = enma.createQuery(jpql, Appointment.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

    @Override
    public Appointment findAppointmentById(int appointmentId) {
        EntityManager enma = JPAConfig.getEntityManager();
        Appointment appointment = enma.find(Appointment.class, appointmentId);
        return appointment;
    }

    @Override
    public void update(Appointment appointment) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.merge(appointment);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public List<Appointment> findALl() {
        EntityManager enma = JPAConfig.getEntityManager();
        String sql = "SELECT a FROM Appointment a";
        TypedQuery<Appointment> query = enma.createQuery(sql, Appointment.class);
        return query.getResultList();
    }

    @Override
    public List<Appointment> findALlByDate(Timestamp appointmentDate) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT a FROM Appointment a WHERE a.appointmentDate >= :startDate AND a.appointmentDate < :endDate";
        TypedQuery<Appointment> query = enma.createQuery(jpql, Appointment.class);
        LocalDateTime startDate = appointmentDate.toLocalDateTime().toLocalDate().atStartOfDay();
        LocalDateTime endDate = startDate.plusDays(1);

        query.setParameter("startDate", Timestamp.valueOf(startDate));
        query.setParameter("endDate", Timestamp.valueOf(endDate));

        return query.getResultList();
    }
}
