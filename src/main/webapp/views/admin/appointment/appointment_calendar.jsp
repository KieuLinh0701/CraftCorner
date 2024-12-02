<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<!-- BEGIN CONTENT -->
<div class="col-md-12 col-sm-12">
    <div id="calendar"></div>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/global/plugins/fullcalendar/fullcalendar.css">
    <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/global/plugins/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/global/plugins/fullcalendar/fullcalendar.min.js"></script>

</div>



<script>
    $(document).ready(function() {
        $('#calendar').fullCalendar({
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek'
            },
            events: [
                <c:forEach var="event" items="${events}">
                    {
                        title: '${event.title}',
                        start: '${event.start}',
                        url: '${pageContext.request.contextPath}${event.url}',
                        allDay: ${event.allDay},
                    },
                </c:forEach>
            ],
            eventClick: function(event) {
                if (event.url) {
                    window.open(event.url, 'newtab');
                    return false;
                }
            }
        });
    });
</script>
