<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- BEGIN CONTENT -->
<div class="col-md-12 col-sm-12">
	<h1>My Account Page</h1>
	<div class="content-page">
		<!-- BEGIN PROFILE CONTENT -->
<div class="profile-content">
				<div class="row">
					<div class="col-md-12">
						<div class="portlet light">
							<div class="portlet-title tabbable-line">
								<div class="caption caption-md">
									<i class="icon-globe theme-font hide"></i>
									<span class="caption-subject font-blue-madison bold uppercase">Profile Account</span>
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
									<li>
										<a href="#tab_1_4" data-toggle="tab">Delete Account</a>
									</li>
								</ul>
							</div>
							<div class="portlet-body">
								<div class="tab-content">
									<!-- PERSONAL INFO TAB -->
									<div class="tab-pane active" id="tab_1_1">
										<form role="form" action="#">
											<div class="form-group">
												<label class="control-label">Email</label>
												<input type="text" placeholder="duong@gmail.com" class="form-control"/>
											</div>
											<div class="form-group">
												<label class="control-label">Full Name</label>
												<input type="text" placeholder="NgocDuongP" class="form-control"/>
											</div>
											<div class="form-group">
												<label class="control-label">Mobile Number</label>
												<input type="text" placeholder="+1 646 580 DEMO (6284)" class="form-control"/>
											</div>
											<div class="form-group">
												<label class="control-label">Address</label>
												<label class="control-label">Ward</label>
												<input type="text" placeholder="Tang Nhon Phu A" class="form-control"/>
											</div>
											<div class="form-group">
												<label class="control-label">District</label>
												<input type="text" placeholder="Thu Duc" class="form-control"/>
											</div>
											<div class="form-group">
												<label class="control-label">City</label>
												<input type="text" placeholder="Thanh Pho Ho Chi Minh" class="form-control"/>
											</div>
											<div class="form-group">
												<label class="control-label">Detail</label>
												<textarea class="form-control" rows="3" placeholder="No disciption"></textarea>
											</div>
											<div class="margiv-top-10">
												<a href="#" class="btn green-haze">
												Save Changes </a>
												<a href="#" class="btn default">
												Cancel </a>
											</div>
										</form>
									</div>
									<!-- END PERSONAL INFO TAB -->
									<!-- CHANGE AVATAR TAB -->
									<div class="tab-pane" id="tab_1_2">
										<p>
											 Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod.
										</p>
										<form action="#" role="form">
											<div class="form-group">
												<div class="fileinput fileinput-new" data-provides="fileinput">
													<div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">
														<img src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" alt=""/>
													</div>
													<div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;">
													</div>
													<div>
														<span class="btn default btn-file">
														<span class="fileinput-new">
														Select image </span>
														<span class="fileinput-exists">
														Change </span>
														<input type="file" name="...">
														</span>
														<a href="#" class="btn default fileinput-exists" data-dismiss="fileinput">
														Remove </a>
													</div>
												</div>
												<div class="clearfix margin-top-10">
													<span class="label label-danger">NOTE! </span>
													<span>Attached image thumbnail is supported in Latest Firefox, Chrome, Opera, Safari and Internet Explorer 10 only </span>
												</div>
											</div>
											<div class="margin-top-10">
												<a href="#" class="btn green-haze">
												Submit </a>
												<a href="#" class="btn default">
												Cancel </a>
											</div>
										</form>
									</div>
									<!-- END CHANGE AVATAR TAB -->
									<!-- CHANGE PASSWORD TAB -->
									<div class="tab-pane" id="tab_1_3">
										<form action="#">
											<div class="form-group">
												<label class="control-label">Current Password</label>
												<input type="password" class="form-control"/>
											</div>
											<div class="form-group">
												<label class="control-label">New Password</label>
												<input type="password" class="form-control"/>
											</div>
											<div class="form-group">
												<label class="control-label">Re-type New Password</label>
												<input type="password" class="form-control"/>
											</div>
											<div class="margin-top-10">
												<a href="#" class="btn green-haze">
												Change Password </a>
												<a href="#" class="btn default">
												Cancel </a>
											</div>
										</form>
									</div>
									<!-- END CHANGE PASSWORD TAB -->
									<!-- PRIVACY SETTINGS TAB -->
									<div class="tab-pane" id="tab_1_4">
										<form action="#">
											<table class="table table-light table-hover">
											<tr>
												<td>
													 We're sorry you're leaving Cozy Corner, but please note that deleted accounts will not be reopened.
												</td>
												<td>
													<label class="uniform-inline">
													<input type="radio" name="optionsRadios1" value="option1"/>
													Yes </label>
													<label class="uniform-inline">
													<input type="radio" name="optionsRadios1" value="option2" checked/>
													No </label>
												</td>
											</tr>
											</table>
											<!--end profile-settings-->
											<div class="margin-top-10">
												<a href="#" class="btn green-haze">
												Save Changes </a>
												<a href="#" class="btn default">
												Cancel </a>
											</div>
										</form>
									</div>
									<!-- END PRIVACY SETTINGS TAB -->
								</div>
							</div>
						</div>
					</div>
				</div>
</div>
<!-- END PROFILE CONTENT -->
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