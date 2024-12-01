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
				<c:if test="${alert != null}">
					<h3 class="alert alertdanger">${alert}</h3>
				</c:if>
				<c:if test="${alert == null}">
					<h1>Shopping cart</h1>
					<div class="goods-page">
						<div class="goods-data clearfix">
							<div class="table-wrapper-responsive">
								<table summary="Shopping cart">
									<c:forEach var="cartItem" items="${listCartItem}">
										<tr>
											<th class="goods-page-image">Image</th>
											<th class="goods-page-name">Name</th>
											<th class="goods-page-description">Description</th>
											<th class="goods-page-quantity">Quantity</th>
											<th class="goods-page-price">Unit price</th>
											<th class="goods-page-total" colspan="2">Total</th>
										</tr>
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
											<td>${cartItem.product.name}</td>
											<td class="goods-page-description">
												<p>Material: ${cartItem.product.material}</p>
												<p>Color: ${cartItem.product.color}</p>
												<p>Size: height: ${cartItem.product.height} -
													length: ${cartItem.product.length} - width:
													${cartItem.product.width}</p>
											</td>
											<td class="goods-page-quantity">
												<div class="product-quantity">
													<input id="product-quantity" type="text"
														value="${cartItem.quantity}" readonly
														class="form-control input-sm">
												</div>
											</td>
											<td class="goods-page-price"><strong>${cartItem.product.price}</strong>
											</td>
											<td class="goods-page-total"><strong>${cartItem.product.price * cartItem.quantity}</strong>
											</td>
											<td class="del-goods-col"><a class="del-goods" href="${pageContext.request.contextPath}/cart/delete">&nbsp;</a>
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>

							<div class="shopping-total">
								<ul>
									<li><em>Total</em> <strong class="price">${total}</strong>
									</li>
								</ul>
							</div>
						</div>
						<button class="btn btn-default" onclick="continueShopping()" style="background:black; border:black;">
							Continue shopping <i class="fa fa-shopping-cart"></i>
						</button>
						<button class="btn btn-default" onclick="continueShopping()" style="margin-left: 15px; background:black; border:black;">
							Delete cart <i class="fa fa-shopping-cart"></i>
						</button>
						<button class="btn btn-primary" onclick="checkout()" style="background:black; border:black;">
							Checkout <i class="fa fa-check"></i>
						</button>
					</div>
				</c:if>
			</div>
			<!-- END CONTENT -->
		</div>
		<!-- END SIDEBAR & CONTENT -->
	</div>
</div>

<script>
    function continueShopping() {
        window.location.href = '${pageContext.request.contextPath}/#'; // Chuyển hướng đến trang product
    }
    function checkout() {
        window.location.href = '${pageContext.request.contextPath}/checkout'; // Chuyển hướng đến trang thanh toán
    }
</script>