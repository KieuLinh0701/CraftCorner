<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Revenue Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        .dashboard-stat {
            border-radius: 5px;
            padding: 20px;
            color: white;
            margin-bottom: 20px; /* Khoảng cách giữa các ô */
        }
        .blue-madison {
            background-color: #1e88e5;
        }
        .yellow-gold {
            background-color: #ffb84d;
        }
        .visual {
            font-size: 40px;
        }
        .details {
            font-size: 16px;
        }
        .number {
            font-size: 30px;
            font-weight: bold;
        }
        .desc {
            font-size: 14px;
        }
        .dropdown-container {
            display: flex;
            align-items: center;
            margin-top: 30px; /* Khoảng cách giữa dropdown và các ô */
            margin-bottom: 30px; /* Khoảng cách dưới */
        }
        .btn-group {
            margin-right: 30px; /* Khoảng cách 30px giữa dropdown và ô nhập ngày */
        }

        /* Kiểu dáng cho button select */
        .btn-group button {
            padding: 8px 16px; /* Kích thước của ô select */
            font-size: 14px;
            border-radius: 5px;
            border: 1px solid #ccc; /* Viền ô select */
            background-color: #f0f0f0; /* Màu nền của ô select */
            color: #333;
            cursor: pointer;
            outline: none; /* Xóa viền focus */
        }

        /* Kiểu dáng cho ô nhập ngày */
        .date-input {
            width: 150px; /* Kích thước giống ô select */
            padding: 8px 16px; /* Padding giống ô select */
            font-size: 14px; /* Kích thước font giống ô select */
            border-radius: 5px;
            border: 1px solid #ccc; /* Viền giống ô select */
            background-color: #f0f0f0; /* Màu nền giống ô select */
            color: #333; /* Màu chữ giống ô select */
            outline: none; /* Xóa viền khi focus */
        }

        /* Hiệu ứng khi focus vào ô nhập ngày */
        .date-input:focus {
		    border: none; /* Loại bỏ viền */
		    outline: none; /* Loại bỏ outline */
		    box-shadow: none; /* Loại bỏ bóng */
		}

        .page-title {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
        }
        .row {
            margin-top: 30px; /* Khoảng cách giữa select và các ô Total Revenue, Total Orders */
        }
    </style>
</head>
<body>
    <h1 class="page-title">Revenue Dashboard</h1>

    <!-- Dropdown và ô nhập ngày -->
    <div class="dropdown-container">
        <!-- Select Date, Month, hoặc Year -->
        <div class="btn-group">
            <button id="dropdownButton" type="button" class="btn btn-fit-height grey-salt dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="1000" data-close-others="true">
                ${selectedPeriod} <i class="fa fa-calendar"></i>
            </button>
            <ul class="dropdown-menu pull-right" role="menu">
                <li><a href="#" onclick="changeSelection('Date')">Select Date</a></li>
                <li><a href="#" onclick="changeSelection('Month')">Select Month</a></li>
                <li><a href="#" onclick="changeSelection('Year')">Select Year</a></li>
            </ul>
        </div>

        <!-- Ô nhập ngày -->
        <input type="date" class="date-input" value="${selectedDate}" />
    </div>

    <!-- Revenue và Total Orders Dashboard -->
    <div class="row">
        <!-- Revenue -->
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
            <div class="dashboard-stat blue-madison">
                <div class="visual">
                    <i class="fa fa-dollar-sign fa-icon-medium"></i>
                </div>
                <div class="details">
                    <div class="number">
                        <c:choose>
                            <c:when test="${not empty totalRevenue}">
                                ${totalRevenue}
                            </c:when>
                            <c:otherwise>
                                N/A
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="desc">
                        Total Revenue
                    </div>
                </div>
            </div>
        </div>

        <!-- Total Orders -->
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
            <div class="dashboard-stat yellow-gold">
                <div class="visual">
                    <i class="fa fa-shopping-cart fa-icon-medium"></i>
                </div>
                <div class="details">
                    <div class="number">
                        <c:choose>
                            <c:when test="${not empty totalOrders}">
                                ${totalOrders}
                            </c:when>
                            <c:otherwise>
                                N/A
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="desc">
                        Total Orders
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Begin: life time stats -->
    <div class="portlet box red-sunglo">
        <div class="portlet-title">
            <div class="caption">
                <i class="fa fa-bar-chart-o"></i>Revenue
            </div>
        </div>
        <div class="portlet-body">
            <div class="tab-content">
                <div class="tab-pane active" id="portlet_tab1">
                    <div id="statistics_1" class="chart">
                        <!-- Placeholder for revenue chart -->
                    </div>
                </div>
                <div class="tab-pane" id="portlet_tab2">
                    <div id="statistics_2" class="chart">
                        <!-- Placeholder for order chart -->
                    </div>
                </div>
            </div>
            <div class="well no-margin no-border">
                <div class="row">
                    <div class="col-md-6">
                        <div class="well">
                            <h4>Most Purchased Product: </h4>
                            <p>${mostPurchasedProduct}</p>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="well">
                            <h4>Least Purchased Product: </h4>
                            <p>${leastPurchasedProduct}</p>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="well">
                            <h4>Highest Revenue Product: </h4>
                            <p>${highestRevenueProduct}</p>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="well">
                            <h4>Lowest Revenue Product: </h4>
                            <p>${lowestRevenueProduct}</p>
                        </div>
                    </div>                    
                </div>
            </div>
        </div>
    </div>

</body>
</html>
