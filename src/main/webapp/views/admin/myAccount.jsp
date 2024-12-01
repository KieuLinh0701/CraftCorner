<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Begin brands -->

<!-- BEGIN CONTENT -->
<div class="col-md-12 col-sm-12">
    <h1>My Account Page</h1>
    <div class="content-page">
        <div class="profile-content">
            <div class="row">
                <div class="col-md-12">
                    <div class="portlet light">
                        <div class="portlet-title tabbable-line">
                            <div class="caption caption-md">
                                <span class="caption-subject font-black-madison bold uppercase">Profile Account</span>
                            </div>
                            <ul class="nav nav-tabs">
                                <li class="active">
                                    <a href="#tab_1_1" data-toggle="tab">Personal Info</a>
                                </li>
                                <li>
                                    <a href="#tab_1_2" data-toggle="tab">Change Avatar</a>
                                </li>
                                <li>
                                    <a href="#tab_1_3" data-toggle="tab">Change Password</a>
                                </li>
                            </ul>
                        </div>
                        <div class="portlet-body">
                         <!-- Thêm thông báo alert -->
                            <% 
                                String alert = (String) request.getAttribute("alert"); 
                                if (alert != null) { 
                            %>
                                <div class="alert alert-info">
                                    <%= alert %>
                                </div>
                            <% 
                                } 
                            %>
                            <div class="tab-content">
                               <!-- PERSONAL INFO TAB -->
								<div class="tab-pane active" id="tab_1_1">
								    <form id="personalInfoForm" role="form" action="${pageContext.request.contextPath}/admin/myaccount" method="post">
								        <div class="form-group">
								            <label for="email">Email:</label>
								            <input type="email" id="email" name="email" class="form-control" value="${sessionScope.account.email}" readonly />
								        </div>
								        <div class="form-group">
								            <label class="control-label">Full Name</label>
								            <input type="text" name="fullname" value="${sessionScope.account.fullname}" class="form-control" id="fullname"/>
								        </div>
								        <div class="form-group">
								            <label class="control-label">Mobile Number</label>
								            <input type="text" name="phone" value="${sessionScope.account.phone}" class="form-control" id="phone"/>
								        </div>
								        <div class="form-group">
								            <label class="control-label">Address</label>
								            <br>
								            <label class="control-label">Ward</label>
								            <input type="text" name="ward" value="${sessionScope.account.address.ward}" class="form-control" id="ward"/>
								        </div>
								        <div class="form-group">
								            <label class="control-label">District</label>
								            <input type="text" name="district" value="${sessionScope.account.address.district}" class="form-control" id="district"/>
								        </div>
								        <div class="form-group">
								            <label class="control-label">City</label>
								            <input type="text" name="city" value="${sessionScope.account.address.city}" class="form-control" id="city"/>
								        </div>
								        <div class="form-group">
								            <label class="control-label">Detail</label>
								            <textarea name="detail" class="form-control" rows="3" placeholder="No description" id="detail">${sessionScope.account.address.detail}</textarea>
								        </div>
								        <div class="margiv-top-10">
								            <button type="submit" class="btn btn-primary" style="background-color: black">Save Changes</button>
								            <a href="#" class="btn default" id="cancelBtn">Cancel</a>
								        </div>
								    </form>
								</div>
								<!-- END PERSONAL INFO TAB -->

                                 <!-- CHANGE AVATAR TAB -->
                                <div class="tab-pane" id="tab_1_2">
                                    <form action="${pageContext.request.contextPath}/admin/uploadAvatar" method="post" enctype="multipart/form-data" role="form">
                                        <div class="form-group">
                                            <div class="fileinput fileinput-new" data-provides="fileinput">
                                                <div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">
                                                    <img id="avatarPreview" src="${pageContext.request.contextPath}/${sessionScope.account.image}" alt="User Avatar" />
                                                </div>
                                                <div>
                                                    <span class="btn default btn-file">
                                                        <span class="fileinput-new">Select image</span>
                                                        <span class="fileinput-exists">Change</span>
                                                        <input type="file" name="avatar" id="avatarInput" required>
                                                    </span>
                               
                                                </div>
                                            </div>
                                            <div class="clearfix margin-top-10">
                                                <span class="label label-danger">NOTE!</span>
                                                <span>Attached image thumbnail is supported in latest browsers.</span>
                                            </div>
                                        </div>
                                        <div class="margin-top-10">
                                            <button type="submit" class="btn btn-primary" style="background-color: black">Submit</button>
                                            <a href="#" class="btn default" id="cancelBtn2">Cancel</a>
                                        </div>
                                    </form>
                                </div>
                                <!-- END CHANGE AVATAR TAB -->
                                <!-- CHANGE PASSWORD TAB -->
								 <div class="tab-pane" id="tab_1_3">
                                    <form action="${pageContext.request.contextPath}/admin/changePassword" onsubmit="return validateForm()" method="post">
                                        <div class="form-group">
                                            <label class="control-label">Current Password</label>
                                            <input type="password" id="currentPassword" name="currentPassword" class="form-control"/>
                                            <span id="currentPasswordError" class="error-message"></span>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">New Password</label>
                                            <input type="password" id="newPassword" name="newPassword" class="form-control"/>
                                            <span id="newPasswordError" class="error-message"></span>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Re-type New Password</label>
                                            <input type="password" id="retypeNewPassword" name="retypeNewPassword" class="form-control"/>
                                            <span id="retypeNewPasswordError" class="error-message"></span>
                                        </div>
                                        <div class="margin-top-10">
                                            <button type="submit" class="btn btn-primary" style="background-color: black">Change Password</button>
                                           <a href="#" class="btn default" id="cancelBtn3">Cancel</a>
                                        </div>
                                    </form>
                                </div>
								<!-- END CHANGE PASSWORD TAB -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        <h3>My Orders</h3>
        <ul>
            <li><a href="#">View your order history</a></li>
            <li><a href="#">Downloads</a></li>
            <li><a href="#">Your Reward Points</a></li>
            <li><a href="#">View your return requests</a></li>
            <li><a href="#">Your Transactions</a></li>
        </ul>
    </div>
</div>
<!-- END CONTENT -->

<script>
	// Khi người dùng chọn ảnh mới, cập nhật ảnh preview
	document.getElementById('avatarInput').addEventListener('change', function(event) {
	    var reader = new FileReader();
	    reader.onload = function(e) {
	        document.getElementById('avatarPreview').src = e.target.result;  // Hiển thị ảnh đã chọn
	    };
	    reader.readAsDataURL(this.files[0]);
	});
    // Khi nhấn "Cancel", reset input file
   document.getElementById('cancelBtn').addEventListener('click', function(event) {
    event.preventDefault();   
    // Lấy lại các giá trị mặc định từ sessionScope
    document.getElementById('fullname').value = "${sessionScope.account.fullname}";
    document.getElementById('phone').value = "${sessionScope.account.phone}";
    document.getElementById('ward').value = "${sessionScope.account.address.ward}";
    document.getElementById('district').value = "${sessionScope.account.address.district}";
    document.getElementById('city').value = "${sessionScope.account.address.city}";
    document.getElementById('detail').value = "${sessionScope.account.address.detail}";
    document.getElementById('newPassword').value = '';
    document.getElementById('retypeNewPassword').value = '';
});

// Khi nhấn "Cancel", reset input file
   document.getElementById('cancelBtn2').addEventListener('click', function(event) {
    event.preventDefault();   
    document.getElementById('avatarPreview').src = "${pageContext.request.contextPath}/${sessionScope.account.image}";  // Hiển thị ảnh mặc định
    document.getElementById('avatarInput').value = '';  // Reset input file

});

// Khi nhấn "Cancel", reset input file
   document.getElementById('cancelBtn3').addEventListener('click', function(event) {
    event.preventDefault();
    document.getElementById('currentPassword').value = '';
    document.getElementById('newPassword').value = '';
    document.getElementById('retypeNewPassword').value = '';
    setError("currentPasswordError", "");
    setError("newPasswordError", "");
    setError("retypeNewPasswordError", "");

});
    function validateForm() {
        const currentPassword = document.getElementById("currentPassword").value;
        const newPassword = document.getElementById("newPassword").value;
        const retypeNewPassword = document.getElementById("retypeNewPassword").value;

        let hasError = false;

        // Kiểm tra Current Password
        if (!currentPassword) {
            setError("currentPasswordError", "Please enter your current password!");
            hasError = true;
        } else {
            setError("currentPasswordError", "");
        }

        // Kiểm tra New Password
        if (!newPassword) {
            setError("newPasswordError", "Please enter your new password!");
            hasError = true;
        } else if (!validatePasswordStrength(newPassword)) {
            setError("newPasswordError", "Password must be at least 8 characters and include uppercase, lowercase, a number, and a special character.");
            hasError = true;
        } else {
            setError("newPasswordError", "");
        }

        // Kiểm tra Re-type New Password
        if (!retypeNewPassword) {
            setError("retypeNewPasswordError", "Please retype your new password!");
            hasError = true;
        } else if (retypeNewPassword !== newPassword) {
            setError("retypeNewPasswordError", "Passwords do not match!");
            hasError = true;
        } else {
            setError("retypeNewPasswordError", "");
        }

        return !hasError;
    }

 		// Hàm setError để thay đổi thông báo lỗi và màu sắc
    function setError(elementId, message) {
        const errorElement = document.getElementById(elementId);
        errorElement.textContent = message;
        errorElement.style.color = "red";  // Đảm bảo thông báo lỗi có màu đỏ
    }

    // Hàm kiểm tra độ mạnh của mật khẩu
    function validatePasswordStrength(password) {
        const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?_&])[A-Za-z\d@$!%*?_&]{8,}$/;
        return passwordRegex.test(password);
    }
    
</script>