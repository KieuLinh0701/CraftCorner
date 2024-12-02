<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<!-- BEGIN CONTENT -->
<div class="col-md-12 col-sm-12">
    <div class="content-form-page">
        <div class="row">
            <h3 class="text-center">DANH SÁCH ĐẶT LỊCH</h3>
            <a href="${pageContext.request.contextPath}/make-appointment" class="btn btn-success" style="margin-bottom: 10px; color: white">Đặt lịch</a>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Thời gian hẹn</th>
                        <th>Nội dung</th>
                        <th>Người tư vấn</th>
                        <th>Trạng thái</th>
                        <th>Ngày tạo</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="appointment" items="${appointments}">
                        <tr>
                            <td>${appointment.appointmentId}</td>
                            <td>${appointment.appointmentDate}</td>
                            <td>${appointment.description}</td>
                            <td>
                                <c:if test="${not empty appointment.consultant}">
                                    ${appointment.consultant.fullname}
                                </c:if>
                                <c:if test="${empty appointment.consultant}">
                                    -
                                </c:if>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${appointment.status == 'PENDING'}">
                                        <span class="label label-primary">Đang chờ xác nhận</span>
                                    </c:when>
                                    <c:when test="${appointment.status == 'ACCEPT'}">
                                         <span class="label label-success">Đã xác nhận</span>
                                    </c:when>
                                    <c:when test="${appointment.status == 'CANCEL'}">
                                        <span class="label label-danger"> Đã huỷ</span>
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>${appointment.createdAt}</td>
                            <td>
                                <c:if test="${appointment.status == 'PENDING'}">
                                    <a href="appointment/customer-cancel?appointmentId=${appointment.appointmentId}"
                                       class="btn btn-warning"
                                       onclick="return confirm('Bạn có chắc muốn huỷ lịch?')">
                                        Huỷ lịch hẹn
                                    </a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
        </div>
    </div>
</div>
<!-- END CONTENT -->
