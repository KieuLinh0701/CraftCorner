<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<!-- BEGIN CONTENT -->
<div class="col-md-12 col-sm-12">
	<div class="content-form-page">
		<div class="row">
			<h3 class="text-center">CUSTOMER APPOINTMENT LIST</h3>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Customer</th>
						<th>Appointment Time</th>
						<th>Description</th>
						<th>Consultant</th>
						<th>Status</th>
						<th>Created Date</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="appointment" items="${appointments}">
						<tr>
							<td>${appointment.appointmentId}</td>
							<td>${appointment.customer.fullname}-
								${appointment.customer.email}</td>
							<td>${appointment.appointmentDate}</td>
							<td>${appointment.description}</td>
							<td><c:if test="${not empty appointment.consultant}">
                                    ${appointment.consultant.fullname}
                                </c:if> <c:if
									test="${empty appointment.consultant}">
                                    -
                                </c:if></td>
							<td><c:choose>
									<c:when test="${appointment.status == 'PENDING'}">
										<span class="label label-primary">Pending Confirmation</span>
									</c:when>
									<c:when test="${appointment.status == 'ACCEPT'}">
										<span class="label label-success">Confirmed</span>
									</c:when>
									<c:when test="${appointment.status == 'CANCEL'}">
										<span class="label label-danger">Cancelled</span>
									</c:when>
								</c:choose></td>
							<td>${appointment.createdAt}</td>
							<td><c:if test="${appointment.status == 'PENDING'}">
									<button style="color: white"
										data-id="${appointment.appointmentId}"
										class="btn btn-success confirm" data-toggle="modal"
										data-target="#exampleModalCenter">Confirm</button>
								</c:if> <c:if test="${appointment.status == 'PENDING'}">
									<a style="color: white"
										href="${pageContext.request.contextPath}/admin/appointment/cancel?appointmentId=${appointment.appointmentId}"
										class="btn btn-warning"
										onclick="return confirm('Are you sure you want to cancel the appointment?')">
										Cancel </a>
								</c:if> <!-- Modal -->
								<div class="modal fade" id="exampleModalCenter" tabindex="-1"
									role="dialog" aria-labelledby="exampleModalCenterTitle"
									aria-hidden="true">
									<div class="modal-dialog modal-dialog-centered" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title">Confirm Appointment</h5>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<form
												action="${pageContext.request.contextPath}/admin/appointment/confirm"
												method="post">
												<input type="hidden" name="appointmentId" id="appointmentId">
												<div class="modal-body">
													<div class="form-group">
														<label for="consultant" class="col-lg-4 control-label">
															Select Consultant <span class="require">*</span>
														</label>
														<div class="col-lg-8">
															<select class="form-control" id="consultant"
																name="consultant">
																<c:forEach var="consultant" items="${consultants}">
																	<option value="${consultant.id}">${consultant.fullname}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<br>

												</div>
												<div class="modal-footer">
													<button type="submit" class="btn btn-success">Save</button>
												</div>
											</form>
										</div>
									</div>
								</div></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- END CONTENT -->
<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalCenterTitle"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">...</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
			</div>
		</div>
	</div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const confirmButtons = document.querySelectorAll('.confirm');

        confirmButtons.forEach(button => {
            button.addEventListener('click', function() {
                const appointmentId = this.getAttribute('data-id');
                document.getElementById('appointmentId').value = appointmentId;
            });
        });
    });
</script>