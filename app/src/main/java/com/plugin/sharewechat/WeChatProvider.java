package com.plugin.sharewechat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * 微信接口支持
 * Created by killer on 15/9/28..
 */
public class WeChatProvider {

    private static IWXAPI wxApi;

    public static void init(Activity activity) {
        if (wxApi != null) {
            return;
        }
        wxApi = WXAPIFactory.createWXAPI(activity.getApplicationContext(), "wxaa561e93e30b45ca");
        wxApi.registerApp("wxaa561e93e30b45ca");
    }

    public boolean handleIntent(Intent var1, IWXAPIEventHandler var2) {
        return wxApi != null && wxApi.handleIntent(var1, var2);
    }

    private void shareAppWeChat(Context context, int type) {

        WXWebpageObject webPage = new WXWebpageObject();

        WXMediaMessage msg = new WXMediaMessage();

        Bitmap bit = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        if (bit != null) {
            msg.setThumbImage(bit);
        }

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.scene = type;
        req.message = msg;

        wxApi.sendReq(req);
    }
}
