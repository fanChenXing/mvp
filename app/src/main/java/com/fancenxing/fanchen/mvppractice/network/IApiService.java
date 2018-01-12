package com.fancenxing.fanchen.mvppractice.network;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;


/**
 * 类功能描述：
 * Created by 孙中宛 on 2017/8/11.
 */

public interface IApiService {

    //获取验证码
    @FormUrlEncoded
    @POST("User/send_sms_reg_code")
    Observable<ResponseModel> getVerifyCode(@Field("mobile") String phone);

    //提交注册
    @FormUrlEncoded
    @POST("User/regL")
    Observable<ResponseModel> register(@Field("mobile") String mobile, @Field("password1") String psw1,
                                       @Field("password2") String psw2, @Field("code") String code);

    //微信注册
    @FormUrlEncoded
    @POST("User/regWxTea")
    Observable<ResponseModel> wxRegister(@Field("mobile") String mobile, @Field("password1") String psw1,
                                         @Field("password2") String psw2, @Field("code") String code, @Field("openid") String openId);

    //登录
    @FormUrlEncoded
    @POST("User/loginTeach")
    Observable<ResponseModel> login(@Field("mobile") String mobile, @Field("password") String password);

    //微信登录
    @FormUrlEncoded
    @POST("User/thirdLoginTea")
    Observable<ResponseModel> wxLogin(@Field("code") String code, @Field("from") String from, @Field("openid") String openid);

    //首页轮播图
    @POST("Ad/news_carousel")
    Observable<ResponseModel> getBanners();

    //获取讲师列表
    @FormUrlEncoded
    @POST("Project/Search")
    Observable<ResponseModel> getFilterTeacher(@Field("page") int page, @Field("is_hot") String isHot,
                                               @Field("prices_a") String lowPrice, @Field("prices_b") String highPrice,
                                               @Field("courses_cat_id") String type, @Field("input_name") String inputName,
                                               @Field("year") String year, @Field("month") String month,
                                               @Field("day") String day, @Field("morning") String am,
                                               @Field("afternoon") String pm, @Field("evening") String night);

    //获取课题分类
    @POST("Project/getType")
    Observable<ResponseModel> getAllSubjects();

    //获取讲师详情
    @FormUrlEncoded
    @POST("Teacher/teaDetail")
    Observable<ResponseModel> getTeacherDetail(@Field("u_id") String teacherId);

    //获取讲师评论
    @FormUrlEncoded
    @POST("Project/jComTeach")
    Observable<ResponseModel> getTeacherComments(@Field("page") int currentPage, @Field("teacher_id") String teacherId);

    //讲师的日程
    @FormUrlEncoded
    @POST("NewInfo/teaCalendarTwo")
    Observable<ResponseModel> getTeacherSchedule(@Field("day_s") long startTime, @Field("day_e") long endTime,
                                                 @Field("tea_id") String teacherId);

    //获取个人信息
    @POST("MyInfo/MyInfoTeach")
    Observable<ResponseModel> getSelfInfo(@Header("token") String token);

    //上传个人头像
    @Multipart
    @POST("Uploadify/imageUpTeach")
    Observable<ResponseModel> uploadImage(@Header("token") String token, @Part MultipartBody.Part photo, @Part("description") RequestBody description);

    //保存个人信息
    @FormUrlEncoded
    @POST("MyInfo/EditMyInfoTeach")
    Observable<ResponseModel> saveSelfInfos(@Header("token") String token, @Field("head_pic") String head_path,
                                            @Field("nickname") String nickName, @Field("sex") String sex,
                                            @Field("birthday") String birthday, @Field("mobile") String mobile,
                                            @Field("email") String email, @Field("company") String company,
                                            @Field("professor") String professor, @Field("province") String province,
                                            @Field("address") String address);

    //获取积分记录
    @FormUrlEncoded
    @POST("NewInfo/pointHistoryTeach")
    Observable<ResponseModel> getNumberHistory(@Header("token") String token, @Field("page") int page);

    //修改密码
    @FormUrlEncoded
    @POST("User/edit_teach_pass")
    Observable<ResponseModel> modifyPassword(@Header("token") String token, @Field("old_password") String oldPsw,
                                             @Field("new_password1") String newPsw, @Field("new_password2") String surePsw);

    //忘记密码
    @FormUrlEncoded
    @POST("User/forgot_pass_tea")
    Observable<ResponseModel> forgetPassword(@Field("mobile") String phone, @Field("code") String code,
                                             @Field("new_password") String psw, @Field("new_password_two") String surePsw);

    //意见反馈
    @FormUrlEncoded
    @POST("FeedBack/feedback")
    Observable<ResponseModel> submitOpinion(@Header("token") String token, @Field("msg_content") String opinion);

    //我的约课
    @FormUrlEncoded
    @POST("Project/myOrderTeach")
    Observable<ResponseModel> getCourseRecords(@Header("token") String token, @Field("type") String type, @Field("page") int page);


    //我的约课-统计
    @FormUrlEncoded
    @POST("Statis/myOrder")
    Observable<ResponseModel> getStatisticsRecords(@Header("token") String token, @Field("type") String status, @Field("page") int page,
                                                   @Field("t_name") String searchName, @Field("start_time") String start, @Field("end_time") String end);

    //待回复-取消
    @FormUrlEncoded
    @POST("Project/refuse")
    Observable<ResponseModel> waitReplyCancel(@Header("token") String token, @Field("order_id") String orderId);

    //待回复-拒绝改签
    @FormUrlEncoded
    @POST("Teacher/noEdit")
    Observable<ResponseModel> disagreeChangeOrder(@Field("order_id") String orderId);

    //待上课-取消
    @FormUrlEncoded
    @POST("Project/refuse_two")
    Observable<ResponseModel> waitAttendCancel(@Header("token") String token, @Field("order_id") String orderId);

    //待回复-确认上课
    @FormUrlEncoded
    @POST("Project/confirmReply")
    Observable<ResponseModel> attendConfirm(@Header("token") String token, @Field("order_id") String orderId);

    //待回复-同意改签
    @FormUrlEncoded
    @POST("Teacher/okEdit")
    Observable<ResponseModel> agreeChangeOrder(@Field("order_id") String orderId);

    //评价教务
    @FormUrlEncoded
    @POST("Project/comJiao")
    Observable<ResponseModel> commentJiaoWu(@Header("token") String token, @Field("order_id") String oderId,
                                            @Field("grade_j") String grade, @Field("comment_j") String comment,
                                            @Field("j_label_id") String label, @Field("j_anoy") String anonymous);

    //课程详情
    @FormUrlEncoded
    @POST("Project/class_detail_tea")
    Observable<ResponseModel> getCourseDetail(@Field("order_id") String orderId);

    //教务约课
    @FormUrlEncoded
    @POST("Project/orderClass")
    Observable<ResponseModel> commitCourseInfo(@Header("token") String token, @Field("u_id") String teacherId,
                                               @Field("class_name") String courseName, @Field("class_name_b") String className,
                                               @Field("address") String address, @Field("notes") String remark,
                                               @Field("year") String year, @Field("month") String month,
                                               @Field("day") String day, @Field("stage") String stage);

    //申请添加好友
    @FormUrlEncoded
    @POST("NewInfo/addFriendTea")
    Observable<ResponseModel> applyAddFriend(@Header("token") String token, @Field("user_id") String jiaoId,
                                             @Field("notes") String notes);

    //好友申请列表
    @POST("NewInfo/selectNewFriendTea")
    Observable<ResponseModel> getApplyList(@Header("token") String token);

    //接受好友请求
    @FormUrlEncoded
    @POST("NewInfo/likeFriend")
    Observable<ResponseModel> acceptFriendApply(@Header("token") String token, @Field("f_id") String addId);

    //通讯录好友列表
    @POST("NewInfo/listNewFriendTea")
    Observable<ResponseModel> getContactList(@Header("token") String token);

    //删除好友
    @FormUrlEncoded
    @POST("NewInfo/deleteFriend")
    Observable<ResponseModel> deleteFriend(@Header("token") String token, @Field("f_id") String friendId);

    //系统消息
    @FormUrlEncoded
    @POST("Message/teaMsgList")
    Observable<ResponseModel> getSystemMsgs(@Header("token") String token, @Field("page") int currentPage);

    //个人简介
    @FormUrlEncoded
    @POST("MyInfo/EditIntro")
    Observable<ResponseModel> saveIntroduce(@Header("token") String token, @Field("introduce") String introduce);

    //保存填写的课酬
    @FormUrlEncoded
    @POST("MyInfo/EditPrices")
    Observable<ResponseModel> saveCoursePay(@Header("token") String token, @Field("prices") String localPrice,
                                            @Field("w_prices") String otherPrice, @Field("other_prices") String customPrice);

    //设置授课分类
    @FormUrlEncoded
    @POST("MyInfo/EditCat")
    Observable<ResponseModel> saveCourseType(@Header("token") String token, @Field("courses_cat_id") String types);

    //设置授课课题
    @FormUrlEncoded
    @POST("MyInfo/EditTopic")
    Observable<ResponseModel> saveSubjects(@Header("token") String token, @Field("project_name") String subjects);

    //上传个人形象照
    @Multipart
    @POST("Uploadify/teachPic")
    Observable<ResponseModel> uploadOfficialImage(@Header("token") String token, @Part MultipartBody.Part photo, @Part("description") RequestBody description);

    //教务详情
    @FormUrlEncoded
    @POST("Teacher/jiaoDetail")
    Observable<ResponseModel> getPurchaserInfo(@Header("token") String token, @Field("user_id") String purchaserId);

    //获取对教务的评价
    @FormUrlEncoded
    @POST("Project/jiaoComTeach")
    Observable<ResponseModel> getPurchaserComment(@Field("page") int currentPage, @Field("user_id") String purchaserId);

    //我的日程
    @FormUrlEncoded
    @POST("NewInfo/myCalendar")
    Observable<ResponseModel> getMySchedule(@Header("token") String token, @Field("day_s") long startTime,
                                            @Field("day_e") long endTime);

    //操作（开启／关闭）单日某个时间段日程
    @FormUrlEncoded
    @POST("NewInfo/addCalendar")
    Observable<ResponseModel> operateSingleSchedule(@Header("token") String token, @Field("year") int year,
                                                    @Field("month") int month, @Field("day") int day,
                                                    @Field("morning") String am, @Field("m_status") String amStatus,
                                                    @Field("afternoon") String pm, @Field("a_status") String pmStatus,
                                                    @Field("evening") String night, @Field("e_status") String nightStatus);

    //关闭整周日程
    @POST("Teacher/closeWeek")
    Observable<ResponseModel> closeWeekSchedule(@Header("token") String token, @Body RequestBody body);

    //开启整周日程
    @POST("Teacher/openWeek")
    Observable<ResponseModel> openWeekSchedule(@Header("token") String token, @Body RequestBody body);

    //获取最新版本信息
    @POST("Case/updateTea")
    Observable<ResponseModel> getLatestVersion();

    //下载app
    @GET
    Observable<ResponseBody> downloadFile(@Url String url);

    //收到的评价
    @FormUrlEncoded
    @POST("Project/myComTeach")
    Observable<ResponseModel> getReceivedComments(@Header("token") String token, @Field("page") int currentPage);

    //获取信用历史
    @FormUrlEncoded
    @POST("NewInfo/creditHistoryTeach")
    Observable<ResponseModel> getCreditHistory(@Header("token") String token, @Field("page") int currentPage);

    //更改手机号
    @FormUrlEncoded
    @POST("User/changeMobile")
    Observable<ResponseModel> changePhone(@Header("token") String token, @Field("password") String password, @Field("mobile") String phone, @Field("code") String verifyCode);

    //实名认证
    @Multipart
    @POST("Uploadify/uploadTeaCard")
    Observable<ResponseModel> submitAuthenticationInfos(@Header("token") String token, @PartMap Map<String, RequestBody> params, @Part List<MultipartBody.Part> list);

    //教育资讯
    @FormUrlEncoded
    @POST("Ad/newsList")
    Observable<ResponseModel> getEducationInfoList(@Field("page") int page);

}
