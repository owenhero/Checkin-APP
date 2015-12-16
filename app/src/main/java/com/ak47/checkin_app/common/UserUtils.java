package com.ak47.checkin_app.common;

import android.content.Context;
import android.text.TextUtils;

import com.ak47.checkin_app.Constants;
import com.ak47.checkin_app.MyUser;
import com.alibaba.fastjson.JSON;

public class UserUtils {
	/**
	 * 获取用户信息
	 * 
	 * @param context
	 * @return
	 */
	public static MyUser getUserModel(Context context) {
		MyUser user = null;
		String jsondata = Utils.getValue(context, Constants.UserInfo);
		// Log.e("", jsondata);
		if (!TextUtils.isEmpty(jsondata))
			user = JSON.parseObject(jsondata, MyUser.class);
		return user;
	}

	/**
	 * 获取用户ID
	 * 
	 * @param context
	 * @return
	 */
	public static String getUserID(Context context) {
		MyUser user = getUserModel(context);
		if (user != null)
			return user.getTelephone();
		else
			return "";
	}

	/**
	 * 获取用户名字
	 * 
	 * @param context
	 * @return
	 */
	public static String getUserName(Context context) {
		MyUser user = getUserModel(context);
		if (user != null)
			return user.getUserName();
		else
			return "";
	}
//
//	/**
//	 * 获取用户
//	 *
//	 * @param context
//	 * @return
//	 */
//	public static String getUserPwd(Context context) {
//		MyUser user = getUserModel(context);
//		if (user != null)
//			return user.getPassword();
//		else
//			return "";
//	}

//	public static void getLogout(Context context) {
//		EMChatManager.getInstance().logout();// 退出环信聊天
//		Utils.RemoveValue(context, Constants.LoginState);
//		Utils.RemoveValue(context, Constants.UserInfo);
//		App.getInstance2().exit();
//	}
//
//	public static void initUserInfo(final Context context,
//			final String telphone, final ImageView img_avar,
//			final TextView txt_name) {
//		NetClient netClient = new NetClient(context);
//		RequestParams params = new RequestParams();
//		params.put("telphone", telphone);
//		netClient.post(Constants.getUserInfoURL, params, new BaseJsonRes() {
//
//			@Override
//			public void onMySuccess(String data) {
//				User user = JSON.parseObject(data, User.class);
//				if (user != null) {
//					if (user.getUserName() != null) {
//						txt_name.setText(user.getUserName());
//					}
//					if (user.getHeadUrl() != null) {
//						NetClient.getIconBitmap(img_avar, user.getHeadUrl());
//					}
//					FinalDb db = FinalDb.create(context, Constants.DB_NAME,
//							false);
//					if (db.findById(user.getId(), User.class) != null)
//						db.deleteById(User.class, user.getId());
//					db.save(user);
//					GloableParams.UserInfos.add(user);
//					GloableParams.Users.put(user.getTelephone(), user);
//				}
//			}
//
//			@Override
//			public void onMyFailure() {
//
//			}
//		});
//	}

}
