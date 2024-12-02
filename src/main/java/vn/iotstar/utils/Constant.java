package vn.iotstar.utils;

import java.nio.file.Paths;

public class Constant {

	public static final int PAGESIZE = 10;
	public static final int PAGESIZE_MIN = 4;

	public static final String SESSION_USERNAME = "email";
	public static final String COOKIE_REMEMBER = "email";

	// Email trang web
	public static final String FROM_EMAIL = "craftcornerfurniture@gmail.com";
	public static final String PASSWORD_EMAIL = "mhzh cegr tcgb onyh";

	// Địa chỉ file .jsp
	public static final String HOME = "/views/home.jsp";
	public static final String REGISTER = "/views/register.jsp";
	public static final String LOGIN = "/views/login.jsp";
	public static final String VERIFY_CODE_ACCOUNT = "/views/verifyCodeAccount.jsp";
	public static final String VERIFY_CODE_PASSWORD = "/views/verifyCodePassword.jsp";
	public static final String FORGOT_PASSWORD = "/views/forgotPassword.jsp";
	public static final String CREATE_PASSWORD = "/views/createPassword.jsp";
	public static final String CART = "/views/cart.jsp";
	public static final String ADMIN_CART = "/views/admin/cart.jsp";
	public static final String CHECKOUT = "/views/checkout.jsp";
	public static final String ADMIN_CHECKOUT = "/views/admin/checkout.jsp";
	public static final String ACCOUNT = "/views/myAccount.jsp";
	public static final String ADMIN_ACCOUNT = "/views/admin/myAccount.jsp";
	public static final String ORDER_SUCCESS = "/views/orderSuccess.jsp";

	public static final String ADMIN_HOME = "/views/admin/home.jsp";
	public static final String DESIGN_DETAIL_MANAGEMENT = "/views/admin/design-detail.jsp";
	public static final String DESIGN_ADD_STEP1 = "/views/admin/design-add-step1.jsp";
	public static final String DESIGN_ADD_STEP2 = "/views/admin/design-add-step2.jsp";
	public static final String DESIGN_EDIT_STEP1 = "/views/admin/design-edit-step1.jsp";
	public static final String DESIGN_EDIT_STEP2 = "/views/admin/design-edit-step2.jsp";
	public static final String DESIGN_MANAGEMENT = "/views/admin/design-management.jsp";
	public static final String PRODUCT_MANAGEMENT = "/views/admin/product-management.jsp";
	public static final String PRODUCT_ADD = "/views/admin/product-add.jsp";
	public static final String PRODUCT_EDIT = "/views/admin/product-edit.jsp";

	public static final String ADMIN_ORDER_SUCCESS = "/views/admin/orderSuccess.jsp";
	public static final String UPLOAD_DIRECTORY = "C:\\upload";
	public static final String DEFAULT_FILENAME = "default.file";

	public static final String MAKE_APPOINTMENT = "/views/makeAppointment.jsp";
	public static final String LIST_APPOINTMENT = "/views/listAppointment.jsp";

	public static final String ADMIN_APPOINTMENT_LIST = "/views/admin/appointment/appointment_list.jsp";
	public static final String ADMIN_APPOINTMENT_CALENDAR = "/views/admin/appointment/appointment_calendar.jsp";

	public static final String APPOINTMENT_PENDING = "PENDING";
	public static final String APPOINTMENT_ACCEPT = "ACCEPT";
	public static final String APPOINTMENT_CANCEL = "CANCEL";

	// Promote method
	public static final String PROMOTE_LIST = "/views/admin/promote/promote_list.jsp";
	public static final String PROMOTE_ADD = "/views/admin/promote/promote_add.jsp";
	public static final String PROMOTE_EDIT = "/views/admin/promote/promote_edit.jsp";

	// Payment method
	public static final String PAYMENT_METHOD_LIST = "/views/admin/payment_method/payment_method_list.jsp";
	public static final String PAYMENT_METHOD_ADD = "/views/admin/payment_method/payment_method_add.jsp";
	public static final String PAYMENT_METHOD_EDIT = "/views/admin/payment_method/payment_method_edit.jsp";
	public static final String PAYMENT_METHOD_DETAIL = "/views/admin/payment_method/payment_method_detail.jsp";

}
