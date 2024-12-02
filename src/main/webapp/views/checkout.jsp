<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<div class="main">
	<div class="container">
		<!-- BEGIN SIDEBAR & CONTENT -->
		<div class="row margin-bottom-40">
			<!-- BEGIN CONTENT -->
			<div class="col-md-12 col-sm-12">
				<h1>Checkout</h1>
				<!-- BEGIN CHECKOUT PAGE -->
				<div class="panel-group checkout-page accordion scrollable"
					id="checkout-page">
					<div class="panel-body row">
						<div class="col-md-6 col-sm-6">
							<div class="goods-page">
								<div class="goods-data clearfix">
									<div class="table-wrapper-responsive">
										<h3>Your Personal Details</h3>
										<div class="form-group">
											<label for="fullname">Full Name: ${user.fullname}</label> <br>
											<label for="phone">Phone: ${user.phone}</label> <br> <label
												for="email">Email: ${user.email}</label> <br>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-sm-6">
							<div class="goods-page">
								<div class="goods-data clearfix">
									<div class="table-wrapper-responsive">
										<h3>Your Address</h3>
										<div id="myDiv1">
											<label for="address">${user.address.detail},
												${user.address.ward}, ${user.address.district},
												${user.address.city}</label> <br>
											<button class="btn btn-primary" onclick="modify()"
												style="background: black; border: black; margin-left: 0px;">
												Modify <i class="fa fa-check"></i>
											</button>
										</div>
										<form
											action="${pageContext.request.contextPath}/checkout/updateaddress"
											method="post">
											<div id="myDiv2" style="display: none;">
												<div class="form-group address-edit-form">
													<input type="hidden" name="address_id" id="address_id"
														value="${user.address.address_id}"> <label
														for="city">City</label> <input type="text" id="city"
														name="city" class="form-control"
														value="${user.address.city}"> <label
														for="district">District</label> <input type="text"
														id="district" name="district" class="form-control"
														value="${user.address.district}"> <label
														for="ward">Ward</label> <input type="text" id="ward"
														name="ward" class="form-control"
														value="${user.address.ward}"> <label for="detail">Detail</label>
													<input type="text" id="detail" name="detail"
														class="form-control" value="${user.address.detail}">
													<br>
													<button class="btn btn-primary" type="submit"
														style="background: black; border: 2px solid black; float: right;">
														Save <i class="fa fa-check"></i>
													</button>
													<button class="btn btn-primary" onclick="cancel()"
														style="background: white; border: 2px solid black; color: black; margin-right: 15px; float: right;">
														Cancel <i class="fa fa-check"></i>
													</button>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="panel-body row ">
						<div class="goods-page"
							style="padding-right: 15px; padding-left: 15px;">
							<div class="goods-data clearfix">
								<div class="table-wrapper-responsive">
									<h3>Products</h3>
									<table>
										<tr>
											<th class="goods-page-image">Image</th>
											<th class="goods-page-description">Description</th>
											<th class="goods-page-quantity">Quantity</th>
											<th class="goods-page-price">Unit price</th>
											<th class="goods-page-total">Total</th>
										</tr>
										<c:forEach var="cartItem" items="${listCartItem}">
											<tr>
												<td class="goods-page-image"><a href="#"> <c:if
															test="${cartItem.product.image.substring(0,5) != 'https'}">
															<c:url value="/image?fname=${cartItem.product.image}"
																var="imgUrl"></c:url>
														</c:if> <c:if
															test="${cartItem.product.image.substring(0,5) == 'https'}">
															<c:url value="${cartItem.product.image}" var="imgUrl"></c:url>
														</c:if> <img src="${imgUrl}" alt="${cartItem.product.name}">
												</a></td>
												<td class="goods-page-description">
													<p>Material: ${cartItem.product.material}</p>
													<p>Color: ${cartItem.product.color}</p>
													<p>Size: height: ${cartItem.product.height} - length:
														${cartItem.product.length} - width:
														${cartItem.product.width}</p>
												</td>
												<td class="goods-page-price"><p>${cartItem.quantity}</p></td>
												<td class="goods-page-price"><p>${cartItem.product.price}</p>
												</td>
												<td class="goods-page-total"><p>${cartItem.product.price * cartItem.quantity}</p>
												</td>
											</tr>
										</c:forEach>
									</table>
								</div>
							</div>
						</div>
						<form action="${pageContext.request.contextPath}/order"
							id="orderForm" method="post">
							<input type="hidden" name="user_id" id="user_id"
								value="${sessionScope.account.id}">
							<div class="panel-body row">
								<div class="col-md-6 col-sm-6">
									<div class="goods-page">
										<div class="goods-data clearfix">
											<div class="table-wrapper-responsive">
												<h3>Payment Methods</h3>
												<table summary="Shopping cart">
													<c:forEach var="payment" items="${listPayment}">
														<tr>
															<td class="goods-page-image"><c:if
																	test="${payment.image.substring(0,5) != 'https'}">
																	<c:url value="/image?fname=${payment.image}"
																		var="imgUrl"></c:url>
																</c:if> <c:if test="${payment.image.substring(0,5) == 'https'}">
																	<c:url value="${payment.image}" var="imgUrl"></c:url>
																</c:if> <img src="${imgUrl}" alt="${payment.bankName}"
																style="height: 40px; width: 40px; margin-left: 50px;"></td>
															<td>${payment.bankName}</td>
															<td><input type="radio" name="payment"
																value="${payment.id}" style="float: right;"></td>
														</tr>
														<div id="paymentError" class="error-message"
															style="color: red; font-style: italic; font-size: 12px;"></div>
													</c:forEach>
												</table>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-sm-6">
									<div class="goods-page">
										<div class="goods-data clearfix">
											<div class="table-wrapper-responsive">
												<h3>Platform Vouchers</h3>
												<c:if test="${empty listPromote}">
													<br>
													<p style="margin-left: 20px">No discount is available!</p>
												</c:if>
												<c:if test="${!empty listPromote}">
													<table summary="Shopping cart">
														<c:forEach var="promote" items="${listPromote}">
															<tr>
																<td>
																	<p>${promote.voucherCode}</p>
																</td>
																<td><input type="radio" name="promote"
																	value="${promote.id}"
																	onchange="clickDiscount(${promote.discountPercent})"
																	style="float: right;"></td>
															</tr>
														</c:forEach>
													</table>
												</c:if>
											</div>
										</div>
									</div>
								</div>
							</div>
					</div>

					<div class="panel-body row">
						<div class="col-md-12 col-sm-12">
							<div class="goods-page">
								<div class="goods-data clearfix">
									<div class="table-wrapper-responsive">
										<h3>Order notes</h3>
										<textarea type="text" id="noteOrder" name="noteOrder" rows="8"
											class="form-control"></textarea>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="checkout-total-block">
						<h3>Payment Details</h3>
						<ul>
							<li><em>Sub total</em> <strong class="price" name="subTotal"
								id="subTotal">${total}</strong></li>
							<li><em>Voucher</em> <strong class="price"
								id="priceDiscount" name="priceDiscount">- 0</strong></li>
							<li><em>Shipping cost</em> <strong class="price">0</strong>
							</li>
							<li class="checkout-total-price"><em>Total</em> <strong
								class="price" id="total" name="total">${total}</strong></li>
						</ul>
						<input type="hidden" name="total_price" id="total_price"
							value="${total}">
					</div>
					<div class="clearfix"></div>
					<button class="btn btn-primary pull-right" type="submit"
						id="button-confirm" style="margin-right: 0px; background: black;">Confirm
						Order</button>
					<button type="button" onclick="cancelOrder()"
						class="btn btn-default pull-right margin-right-20">Cancel</button>
					</form>
				</div>
				<!-- END CHECKOUT PAGE -->
			</div>
		</div>
		<!-- END CONTENT -->
	</div>
	<!-- END SIDEBAR & CONTENT -->
</div>

<script>

document.getElementById("orderForm").addEventListener("submit", function (event) {
    const paymentMethods = document.getElementsByName("payment");
    let isChecked = false;

    // Kiểm tra xem có radio nào được chọn hay không
    for (let i = 0; i < paymentMethods.length; i++) {
        if (paymentMethods[i].checked) {
            isChecked = true;
            break;
        }
    }

    const errorDiv = document.getElementById("paymentError");
    if (!isChecked) {
        event.preventDefault();
        errorDiv.textContent = "Please select a payment method!";
    } else {
        errorDiv.textContent = "";
    }
});

	function modify() {
		var div1 = document.getElementById("myDiv1");
		var div2 = document.getElementById("myDiv2");
		if (div1.style.display === "none") {
			div1.style.display = "block";
			div2.style.display = "none";
		} else {
			div1.style.display = "none";
			div2.style.display = "block";
		}
	}

	function cancel() {
		var div1 = document.getElementById("myDiv1");
		var div2 = document.getElementById("myDiv2");
		if (div2.style.display === "none") {
			div2.style.display = "block";
			div1.style.display = "none";
		} else {
			div2.style.display = "none";
			div1.style.display = "block";
		}
	}
	function clickDiscount(discountPercent) {
	    const subTotalElement = document.getElementById("subTotal");
	    const subTotalString = subTotalElement.textContent.trim();
	    const subTotal = parseInt(subTotalString);
	    
	    const percentage = parseInt(discountPercent);
	    const discountAmount = subTotal * (percentage / 100);
	    const total = subTotal - discountAmount;
	    
	    updatePrice(total, discountAmount);
	}

	function updatePrice(total, discountAmount) {
	    let totalString = String(total);
	    let discountString = "- " + String(discountAmount);

	    let totalElement1 = document.getElementById("total");
	    let totalElement2 = document.getElementById("total_price");
	    let priceDiscountElement = document.getElementById("priceDiscount");
		
	    totalElement1.textContent = totalString;
	    totalElement2.value = totalString;
	    priceDiscountElement.textContent = discountString;
	}

	function cancelOrder() {
		window.location.href = "${pageContext.request.contextPath}/cart";
	}
</script>