package com.example.unique.myfcmupstreammessageapplication;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;

/**
 * Created by Asma Shaikh on 23-Mar-18.
 */
    public class FCM_Callback extends FirebaseMessagingService {

        private final String TAG = "JSA-FCM";

        @Override
        public void onMessageSent(String msgId) {
            Log.e(TAG, "onMessageSent: " + msgId);
        }

        @Override
        public void onSendError(String msgId, Exception e) {
            Log.e(TAG, "onSendError: " + msgId);
            Log.e(TAG, "Exception: " + e);
        }
    }
