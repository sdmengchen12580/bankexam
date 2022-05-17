package com.udit.bankexam.ui.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.AddressBean;
import com.udit.bankexam.bean.NewUserBean;
import com.udit.bankexam.bean.UpImgBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.presenter.user.UserInfoPresenter;
import com.udit.bankexam.ui.exam.ExamActivity;
import com.udit.bankexam.ui.home.HomeActivity;
import com.udit.bankexam.utils.BitmapUtils;
import com.udit.bankexam.utils.ExMultipartBody;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.bankexam.utils.PhotoUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.utils.SpUtil;
import com.udit.bankexam.utils.ToSystemSetting;
import com.udit.bankexam.view.CameraPop;
import com.udit.bankexam.view.user.UserInfoView;
import com.udit.frame.common.circleImageView.CircleImageView;
import com.udit.frame.common.dialog.CustomDialog;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.activity.BaseApplication;
import com.udit.frame.freamwork.http.HttpTask;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.freamwork.http.RequestObject;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zb on 2017/5/4.
 */

public class UserInfoActivity extends BaseActivity<UserInfoPresenter> implements UserInfoView.View, View.OnClickListener {

    public static void startUserInfoActivity(BaseActivity<?> mActivity) {
        mActivity.startActivity(new Intent(mActivity, UserInfoActivity.class));

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_userinfo);
    }

    private Uri imageUri1;
    public Uri cropImageUri;
    private String imgTakeAddress1;
    private Bitmap bitmap;
    private ImageView img_top_return;
    private TextView text_top_centent;
    private TextView tv_name;
    private TextView tv_phone;
    private CircleImageView img_user;
    private LinearLayout ll_modifty_name, ll_modifty_pwd, ll_phone;
    private AddressBean bean_address;
    private UserBean bean_user;
    private Call call;

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_centent.setText("账号信息");
        //头像展示
        String str = (String) SpUtil.get(this, "imgurl", "");
        if (!str.equals("")) {
            ImageLoader.getInstance().displayImage(str, this.img_user, BaseApplication.list_options);
        }
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);
        ll_modifty_name.setOnClickListener(this);
        ll_modifty_pwd.setOnClickListener(this);
        //上传头像
        img_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FastClickUtils.isFastClick()) {
                    return;
                }
                if (Build.VERSION.SDK_INT >= 23 && !permissionCheck(1)) {
                    ActivityCompat.requestPermissions(UserInfoActivity.this, permissionManifestCamera, PERMISSION_REQUEST_CAMEAR_CODE3);
                } else {
                    showalertdialog();
                }
            }
        });
    }

    @Override
    public void initData() {
        mPresenter = new UserInfoPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bean_user = SaveUtils.getUser(this);
        //昵称
        if (!MyCheckUtils.isEmpty(bean_user.getPet())) {
            tv_name.setText(bean_user.getPet());
        } else {
            tv_name.setText("");
        }
        //手机号
        if (this.bean_user.getMobile() != null && !this.bean_user.getMobile().equals(""))
            this.tv_phone.setText(this.bean_user.getMobile());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                this.onLowMemory();
                finish();
                break;
            case R.id.ll_modifty_pwd:
                //修改密码
                ModiftyPwdActivity.startModiftyPwdActivity(this);
                break;
            case R.id.ll_modifty_name:
                //修改昵称
                ModiftyNameActivity.startModiftyNameActivity(this);
                break;
//            case R.id.ll_shouhuo:
//                shouhuoActivity.startshouhuoActivity(this, bean_address);
//                break;
//            case R.id.text_new_shouhuo:
//                shouhuoActivity.startshouhuoActivity(this, null);
//                break;
//            case R.id.ll_quit:
//                //退出登录
//                CustomDialog.Builder builder = new CustomDialog.Builder(this);
//                builder.setMessage("您确定要退出么？");
//                builder.setTitle("退出");
//                builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        SaveUtils.deleteUser(UserInfoActivity.this);
//                        LoginActivity.startLoginActivity(UserInfoActivity.this);
//                        HomeActivity.HOMEACTIVITY.finish();
//                        finish();
//
//
//                    }
//                });
//                builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                builder.create().show();
//
//                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPresenter.doGetAddr(this, bean_user.getUid());
    }

    @Override
    public void getAddr(AddressBean bean) {
        if (bean != null) {
            bean_address = bean;
//            if (!MyCheckUtils.isEmpty(bean.getName())) {
//                text_address_username.setText(bean.getName());
//            } else
//                text_address_username.setText("");
//
//            if (!MyCheckUtils.isEmpty(bean.getAddr())) {
//                text_address.setText(bean.getAddr());
//            } else {
//                text_address.setText("");
//            }
//
//            if (!MyCheckUtils.isEmpty(bean.getTel())) {
//                text_phone.setText(bean.getTel());
//            } else {
//                text_phone.setText("");
//            }
//            text_new_shouhuo.setVisibility(View.GONE);
//            ll_shouhuo.setVisibility(View.VISIBLE);
//        } else {
//            text_new_shouhuo.setVisibility(View.VISIBLE);
//            ll_shouhuo.setVisibility(View.GONE);
        }
    }

    //--------------------------------拍照--------------------------------
    private void showalertdialog() {
        CameraPop cameraPop = new CameraPop(UserInfoActivity.this);
        cameraPop.showPop(new CameraPop.ClickCallback() {
            @Override
            public void clickType(int type) {
                switch (type) {
                    case 1:
                        if (PhotoUtils.hasSdcard()) {
                            imgTakeAddress1 = Environment.getExternalStorageDirectory().getPath() + "/" + PhotoUtils.getNetTimeC() + ".jpg";//网络时间
                            imageUri1 = Uri.fromFile(new File(imgTakeAddress1));
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                imageUri1 = FileProvider.getUriForFile(UserInfoActivity.this,
                                        "com.udit.bankexam.fileProvider", new File(imgTakeAddress1));//PhotoUtils.TakePic.fileUri
                            }
                            PhotoUtils.takePicture(UserInfoActivity.this, imageUri1, PhotoUtils.TakePic.CODE_CAMERA_REQUEST);
                        } else {
                            Toast.makeText(UserInfoActivity.this, "设备没有SD卡！", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case 2:
                        PhotoUtils.openPic(UserInfoActivity.this, PhotoUtils.TakePic.CODE_GALLERY_REQUEST);
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            cropImageUri = Uri.fromFile(PhotoUtils.TakePic.fileCropUri);
            switch (requestCode) {
                //拍照完成回调
                case PhotoUtils.TakePic.CODE_CAMERA_REQUEST:
                    PhotoUtils.cropImageUri(this, imageUri1, cropImageUri,
                            1, 1, PhotoUtils.TakePic.OUTPUT_X, PhotoUtils.TakePic.OUTPUT_Y, PhotoUtils.TakePic.CODE_RESULT_REQUEST);
                    break;

                //访问相册完成回调
                case PhotoUtils.TakePic.CODE_GALLERY_REQUEST:
                    if (PhotoUtils.hasSdcard()) {
                        Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            newUri = FileProvider.getUriForFile(this, "com.udit.bankexam.fileProvider", new File(newUri.getPath()));
                        }
                        PhotoUtils.cropImageUri(this, newUri, cropImageUri, 1, 1, PhotoUtils.TakePic.OUTPUT_X,
                                PhotoUtils.TakePic.OUTPUT_Y, PhotoUtils.TakePic.CODE_RESULT_REQUEST);
                    } else {
                        Toast.makeText(this, "设备没有SD卡！", Toast.LENGTH_SHORT).show();
                    }
                    break;

                //裁剪
                case PhotoUtils.TakePic.CODE_RESULT_REQUEST:
                    bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, this);
                    showLoadingDialog("请稍等...");
                    bitmap = makeRoundCorner(bitmap);
                    upImg();
                    break;
                default:
            }
        }
    }

    public static Bitmap makeRoundCorner(Bitmap bitmap)
    {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int left = 0, top = 0, right = width, bottom = height;
        float roundPx = height/2;
        if (width > height) {
            left = (width - height)/2;
            top = 0;
            right = left + height;
            bottom = height;
        } else if (height > width) {
            left = 0;
            top = (height - width)/2;
            right = width;
            bottom = top + width;
            roundPx = width/2;
        }
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        int color = 0xff424242;
        Paint paint = new Paint();
        Rect rect = new Rect(left, top, right, bottom);
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }


    @Override
    public void onRequestPermissionsResult(int permsRequestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (permsRequestCode) {
            case PERMISSION_REQUEST_CAMEAR_CODE3: {
//                if (!shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
//                    showToast("请在应用管理里把拍照权限打开");
//                }
                //grantResults判断会失效,都返回PERMISSION_GRANTED,用PermissionChecker.checkSelfPermission检测
                if (permissionCheck(1)) {
                    showalertdialog();
                } else {
                    Toast.makeText(this, "需要相关的权限去上传图片。", Toast.LENGTH_SHORT).show();
                    ToSystemSetting.change2AppSetting(UserInfoActivity.this);
                }
                return;
            }
        }
    }

    //---------------------------------------上传头像---------------------------------------
    private void upImg() {
        if (bitmap == null) {
            hideLoadingDialog();
            Toast.makeText(this, "图片上传失败,请重试", Toast.LENGTH_SHORT).show();
            return;
        }
        String path = BitmapUtils.saveBitmapToFile(UserInfoActivity.this, bitmap, PhotoUtils.getNetTimeC());
        final OkHttpClient client = new OkHttpClient();
        //文件
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (!path.equals("")) {
            RequestBody body = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), new File(path));
            requestBody.addFormDataPart("file", PhotoUtils.getNetTimeC() + ".jpg", body);
        }
        //参数
//        requestBody.addFormDataPart("sign", sign);
        //进度处理
        ExMultipartBody exMultipartBody = new ExMultipartBody(requestBody.build()
                , new ExMultipartBody.UploadProgressListener() {
            @Override
            public void onProgress(long total, long current) {
                int percent = (int) (current * 100 / total);
                Log.e("测试上传", "进度total=" + total + "   current" + current + "    percent=" + percent);
            }
        });
        //开始上传
        String uri = IHTTP.UP_IMG_NATIVE;
        final Request request = new Request.Builder().url(uri)
                .post(exMultipartBody)
                .build();
        call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                hideLoadingDialogDelay();
                Log.e("测试上传", "文件上传onFailure: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String dateJson = response.body().string();
                Log.e("测试上传", "文件上传onResponse: " + dateJson);
                final UpImgBean bean = new Gson().fromJson(dateJson, UpImgBean.class);
                if (bean.getCode() == 200) {
                    setAvatar(bean.getData().getResponse().getUrl());
                    return;
                }
                hideLoadingDialogDelay();
                Toast.makeText(UserInfoActivity.this, "头像上传失败,请重试", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //上传头像
    public void setAvatar(final String str) {
        try {
            HashMap<String, String> map_params = new HashMap<>();
            String sessionKey = (String) SpUtil.get(this, "sessionKey", "");
            map_params.put("appId", IHTTP.APP_ID);
            map_params.put("avatarUrl", str);
            map_params.put("sessionKey", sessionKey);
            setHttp(map_params, IHTTP.UP_IMG, new IHttpResponseListener() {

                @Override
                public void onError(String errStr) {
                    hideLoadingDialogDelay();
                }

                @Override
                public void doHttpResponse(String json) {
                    MyLogUtils.e("测试新接口上传头像", "json=" + json);
                    img_user.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(UserInfoActivity.this, "头像上传成功", Toast.LENGTH_SHORT).show();
                            ImageLoader.getInstance().displayImage(str, img_user);
                            SpUtil.put(UserInfoActivity.this, "imgurl", str);
                        }
                    });
                    hideLoadingDialogDelay();
                }
            });
        } catch (Exception e) {
        }
    }

    protected void setHttp(HashMap<String, String> map, String address, IHttpResponseListener listener)
            throws Exception {
        try {
            RequestObject object = new RequestObject(address, map);
            new HttpTask().start(object, HttpTask.REQUEST_TYPE_POST, listener);
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
