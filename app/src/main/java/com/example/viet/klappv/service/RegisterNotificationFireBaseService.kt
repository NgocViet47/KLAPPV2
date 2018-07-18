package com.example.viet.klappv.service

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceIdService
import com.google.firebase.iid.FirebaseInstanceId
import okhttp3.*
import java.io.IOException


class RegisterNotificationFireBaseService:FirebaseInstanceIdService (){
    override fun onTokenRefresh() {
        super.onTokenRefresh()

        val refreshedToken = FirebaseInstanceId.getInstance().token
        if (refreshedToken != null) {
            sendRegistrationToServer(refreshedToken)
        }
    }

    private fun sendRegistrationToServer(refreshedToken: String) {
        Log.e("Token Notification", refreshedToken)

        val url = "https://fcm.googleapis.com/fcm/notification"
        val client = OkHttpClient()

        val jsonBody = """{
            "operation": "create",
            "notification_key_name": "KLAPPV_NOTIFI_1",
            "notification_key": "APA91bGmb67O8hs_3iAsH6EKIUZARkPjD28sUkB9ehYaQTBbYk9X_JAR_E2oacR_buQCLPb4uZdO2OHK2ceD5SUS3vlJjA1EHX15-x6RjD86icU7Awl7jCY",
            "registration_ids": ["""" + refreshedToken +""""]}
            """
        val JSON = MediaType.parse("application/json; charset=utf-8")
        val body = RequestBody.create(JSON, jsonBody)

        Log.e("JSON Body", jsonBody)
        val request = Request.Builder()
                .url(url)
                .addHeader("Content-Type","application/json")
                .addHeader("Authorization","key=AAAAOPWbwzU:APA91bGItoXUsrDFd4BQjywuHH-uA1jRlfe1jvY_1xEI0N1gLTGaeu-A5tkcET4vC13lPPtGctU5OkteW_B1EW62fXHH-52wRvdmcs22chu2KV08znXp-69GqgT1p-19np3-CetHElwub5zljHzipf6-Rre9keG1gw")
                .addHeader("project_id","244638794549")
                .post(body)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("error Create ",e.toString())
            }
            override fun onResponse(call: Call, response: Response){
//                = println(response.body()?.string())
                Log.e("response Create ",response.body()?.string())
            }
        })
    }
}