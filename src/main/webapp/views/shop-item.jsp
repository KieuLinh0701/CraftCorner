<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>


<!-- Body BEGIN -->
<body class="ecommerce">
    <!-- BEGIN STYLE CUSTOMIZER -->
    <div class="color-panel hidden-sm">
      <div class="color-mode-icons icon-color"></div>
      <div class="color-mode-icons icon-color-close"></div>
      <div class="color-mode">
        <p>THEME COLOR</p>
        <ul class="inline">
          <li class="color-red current color-default" data-style="red"></li>
          <li class="color-blue" data-style="blue"></li>
          <li class="color-green" data-style="green"></li>
          <li class="color-orange" data-style="orange"></li>
          <li class="color-gray" data-style="gray"></li>
          <li class="color-turquoise" data-style="turquoise"></li>
        </ul>
      </div>
    </div>
    <!-- END BEGIN STYLE CUSTOMIZER --> 

   
    <div class="main">
      <div class="container">
        <ul class="breadcrumb">
        </ul>
        <!-- BEGIN CONTENT -->
        <div class="row margin-bottom-40">
          

          <!-- BEGIN CONTENT -->
          <div class="col-md-9 col-sm-7">
            <div class="product-page">
              <div class="row">
                <div class="col-md-6 col-sm-6">
                  <div class="product-main-image">
    <div class="product-main-image">
    <c:choose>
        <c:when test="${not empty product.image}">
            <!-- Resolve the image path correctly using c:url -->
            <img src="${product.image}" class="img-responsive" alt="${product.name}" />
        </c:when>
        <c:otherwise>
            <!-- Default image if product has no image -->
            <img src="<c:url value='/assets/img/product/default-product.jpg' />" class="img-responsive" alt="Default Product Image" />
        </c:otherwise>
    </c:choose>
</div>

                            <h3><a href="shop-item.html">${product.name}</a></h3>
                            <div class="pi-price">${product.price}VND</div>
                            <a href="#" class="btn btn-default add2cart">Add to cart</a>
                        </div>
                    </div>
               
                  </div>
                </div>                                   
                    <div class="availability">
                      Availability: <strong>In Stock</strong>
                    </div>
                  </div>
                  <div class="description">
                   
                  </div>
                  <div class="product-page-options">
                    <div class="pull-left">
                     
                    </div>
                   
                  </div>
                  <div class="product-page-cart">
                    <div class="product-quantity">
                        <input id="product-quantity" type="text" value="1" readonly class="form-control input-sm">
                    </div>
                    <button class="btn btn-primary" type="submit">Add to cart</button>
                  </div>
                  

                <div class="product-page-content">
                  <ul id="myTab" class="nav nav-tabs">
                    <li><a href="#Description" data-toggle="tab">Description</a></li>
                    <li><a href="#Information" data-toggle="tab">Information</a></li>
                    <li class="active"><a href="#Reviews" data-toggle="tab">Reviews (2)</a></li>
                  </ul>
                  <div id="myTabContent" class="tab-content">
                    <div class="tab-pane fade" id="Description">
                       <p>${product.description}</p>
                    </div>
                    <div class="tab-pane fade" id="Information">
                      <table class="datasheet">
                        <tr>
                          <th colspan="2">Additional features</th>
                        </tr>
                        <tr>
                          <td class="datasheet-features-type">Color</td>
                          <td>${product.color}</td>
                        </tr>
                        <tr>
                          <td class="datasheet-features-type">Height</td>
                          <td>${product.height}</td>
                        </tr>
                        <tr>
                          <td class="datasheet-features-type">Length</td>
                          <td>${product.length}</td>
                        </tr>
                        <tr>
                          <td class="datasheet-features-type">Material</td>
                          <td>${product.material}</td>
                        </tr>
                        <tr>
                          <td class="datasheet-features-type">Stored Quantity</td>
                          <td>${product.quantity}</td>
                        </tr>
                        <tr>
                          <td class="datasheet-features-type">Width</td>
                          <td>${product.width}</td>
                        </tr>                     
                      </table>
                    </div>
                    <div class="tab-pane fade in active" id="Reviews">
                      <!--<p>There are no reviews for this product.</p>-->
                      <div class="review-item clearfix">
                        <div class="review-item-submitted">
                          <strong>Bob</strong>
                          <em>30/12/2013 - 07:37</em>
                          <div class="rateit" data-rateit-value="5" data-rateit-ispreset="true" data-rateit-readonly="true"></div>
                        </div>                                              
                        <div class="review-item-content">
                            <p>Sed velit quam, auctor id semper a, hendrerit eget justo. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Duis vel arcu pulvinar dolor tempus feugiat id in orci. Phasellus sed erat leo. Donec luctus, justo eget ultricies tristique, enim mauris bibendum orci, a sodales lectus purus ut lorem.</p>
                        </div>
                      </div>
                      <div class="review-item clearfix">
                        <div class="review-item-submitted">
                          <strong>Mary</strong>
                          <em>13/12/2013 - 17:49</em>
                          <div class="rateit" data-rateit-value="2.5" data-rateit-ispreset="true" data-rateit-readonly="true"></div>
                        </div>                                              
                        <div class="review-item-content">
                            <p>Sed velit quam, auctor id semper a, hendrerit eget justo. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Duis vel arcu pulvinar dolor tempus feugiat id in orci. Phasellus sed erat leo. Donec luctus, justo eget ultricies tristique, enim mauris bibendum orci, a sodales lectus purus ut lorem.</p>
                        </div>
                      </div>

<!-- BEGIN FORM -->
<form action="#" class="reviews-form" role="form">
  <h2>Write a review</h2>
  
  <div class="form-group">
    <label for="name">Name <span class="require">*</span></label>
    <input type="text" class="form-control" id="name" required>
  </div>
  
  <div class="form-group">
    <label for="email">Email</label>
    <input type="email" class="form-control" id="email">
  </div>
  
  <div class="form-group">
    <label for="review">Review <span class="require">*</span></label>
    <textarea class="form-control" rows="8" id="review" required></textarea>
  </div>
  
  <button type="submit" class="btn btn-primary">Send</button>
</form>
<!-- END FORM -->


            
              </div>
            </div>
         
          <!-- END CONTENT -->
     
        <!-- END CONTENT -->

        <!-- BEGIN SIMILAR PRODUCTS -->
        <%@ include file="/commons/web/similar-products.jsp"%>
        <!-- END SIMILAR PRODUCTS -->
   

   
   
    <!-- BEGIN STEPS -->
   <%@ include file="/commons/web/steps.jsp"%>
    <!-- END STEPS -->

   <!-- BEGIN fast view of a product -->
    <%@ include file="/commons/web/fast-view-products.jsp"%>
    <!-- END fast view of a product -->

    <!-- Load javascripts at bottom, this will reduce page load time -->
    <!-- BEGIN CORE PLUGINS(REQUIRED FOR ALL PAGES) -->
    <!--[if lt IE 9]>
    <script src="${URL}assets/global/plugins/respond.min.js"></script>  
    <![endif]-->  
    <script src="${URL}assets/global/plugins/jquery.min.js" type="text/javascript"></script>
    <script src="${URL}assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
    <script src="${URL}assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>      
    <script src="${URL}assets/frontend/layout/scripts/back-to-top.js" type="text/javascript"></script>
    <script src="${URL}assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
    <!-- END CORE PLUGINS -->

    <!-- BEGIN PAGE LEVEL JAVASCRIPTS (REQUIRED ONLY FOR CURRENT PAGE) -->
    <script src="${URL}assets/global/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script><!-- pop up -->
    <script src="${URL}assets/global/plugins/carousel-owl-carousel/owl-carousel/owl.carousel.min.js" type="text/javascript"></script><!-- slider for products -->
    <script src='${URL}assets/global/plugins/zoom/jquery.zoom.min.js' type="text/javascript"></script><!-- product zoom -->
    <script src="${URL}assets/global/plugins/bootstrap-touchspin/bootstrap.touchspin.js" type="text/javascript"></script><!-- Quantity -->
    <script src="${URL}assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
    <script src="${URL}assets/global/plugins/rateit/src/jquery.rateit.js" type="text/javascript"></script>

    <script src="${URL}assets/frontend/layout/scripts/layout.js" type="text/javascript"></script>
    <script type="text/javascript">
        jQuery(document).ready(function() {
            Layout.init();    
            Layout.initOWL();
            Layout.initTwitter();
            Layout.initImageZoom();
            Layout.initTouchspin();
            Layout.initUniform();
        });
    </script>
    <!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>