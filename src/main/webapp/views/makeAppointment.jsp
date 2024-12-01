<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<!-- BEGIN CONTENT -->
<div class="col-md-12 col-sm-12">
    <div class="content-form-page">
        <div class="row">
            <h3 class="text-center">ĐẶT LỊCH TƯ VẤN</h3>
                <form action="${pageContext.request.contextPath}/make-appointment"
                      method="post" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="appointmentDate" class="col-lg-4 control-label"> Thời gian đặt lịch
                            <span class="require">*</span>
                        </label>
                        <div class="col-lg-8">
                            <input type="datetime-local" class="form-control" id="appointmentDate" name="appointmentDate">
<%--                            <span id="emailError" class="error-message"--%>
<%--                                  style="color: #E02222; font-size: 12px; font-style: italic;"></span>--%>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="description" class="col-lg-4 control-label"> Mô tả mong muốn
                            <span class="require">*</span>
                        </label>
                        <div class="col-lg-8">
                            <textarea rows="15" class="form-control" id="description" name="description"></textarea>
<%--                            <span id="emailError" class="error-message"--%>
<%--                                  style="color: #E02222; font-size: 12px; font-style: italic;"></span>--%>
                        </div>
                        <div class="row">
                            <div
                                    class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-20">
                                <button type="submit" class="registerbtn"
                                        style="background-color: black; height: 40px; width: 180px; color: white; font-size: 15px">ĐẶT LỊCH NGAY</button>
                            </div>
                        </div>
                    </div>

                </form>
        </div>
    </div>
</div>
<!-- END CONTENT -->
