package com.example.demoseminar.videocall;

import androidx.appcompat.app.AppCompatActivity;

//import com.stringee.call.StringeeCall;
//import com.stringee.common.StringeeAudioManager;
//import com.stringee.listener.StatusListener;

public class OutgoingCallActivity extends AppCompatActivity {//implements View.OnClickListener{
//    private FrameLayout mLocalViewContainer;
//    private FrameLayout mRemoteViewContainer;
//    private TextView tvTo;
//    private TextView tvState;
//    private ImageButton btnMute;
//    private ImageButton btnSpeaker;
//    private ImageButton btnVideo;
//    private ImageButton btnSwitch;
//
//    private StringeeCall mStringeeCall;
//    private StringeeAudioManager audioManager;
//    private String from;
//    private String to;
//    private boolean isVideoCall;
//    private boolean isMute = false;
//    private boolean isSpeaker = false;
//    private boolean isVideo = false;
//    // 0: back camera, 1: front camera
//    // When call starts, automatically use the front camera
//    private int cameraId = 1;
//
//    private StringeeCall.MediaState mMediaState;
//    private StringeeCall.SignalingState mSignalingState;
//
//    private KeyguardManager.KeyguardLock lock;
//
//    public static final int REQUEST_PERMISSION_CALL = 1;
//
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        //add Flag for show on lockScreen and disable keyguard
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
//                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
//                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
//                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
//
//        setContentView(R.layout.activity_outgoing_call);
//
//        lock = ((KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE)).newKeyguardLock(Context.KEYGUARD_SERVICE);
//        lock.disableKeyguard();
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
//            setShowWhenLocked(true);
//            setTurnScreenOn(true);
//        }
//
//        com.example.demoseminar.videocall.Common.isInCall = true;
//
//        from = getIntent().getStringExtra("from");
//        to = getIntent().getStringExtra("to");
//        isVideoCall = getIntent().getBooleanExtra("is_video_call", false);
//
//
//        mLocalViewContainer = findViewById(R.id.v_local);
//        mRemoteViewContainer = findViewById(R.id.v_remote);
//
//        tvTo = findViewById(R.id.tv_to);
//        tvTo.setText(to);
//        tvState = findViewById(R.id.tv_state);
//
//        btnMute = findViewById(R.id.btn_mute);
//        btnMute.setOnClickListener(this);
//        btnSpeaker = findViewById(R.id.btn_speaker);
//        btnSpeaker.setOnClickListener(this);
//        btnVideo = findViewById(R.id.btn_video);
//        btnVideo.setOnClickListener(this);
//        btnSwitch = findViewById(R.id.btn_switch);
//        btnSwitch.setOnClickListener(this);
//
//        isSpeaker = isVideoCall;
//        btnSpeaker.setBackgroundResource(isSpeaker ? R.drawable.mic_on_icon : R.drawable.mic_off_icon);
//
//        isVideo = isVideoCall;
//        btnVideo.setImageResource(isVideo ? R.drawable.videocam_on_icon : R.drawable.videocam_off_icon);
//
//        btnVideo.setVisibility(isVideo ? View.VISIBLE : View.GONE);
//        btnSwitch.setVisibility(isVideo ? View.VISIBLE : View.GONE);
//
//        ImageButton btnEnd = findViewById(R.id.btn_end);
//        btnEnd.setOnClickListener(this);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            List<String> lstPermissions = new ArrayList<>();
//
//            if (ContextCompat.checkSelfPermission(this,
//                    Manifest.permission.RECORD_AUDIO)
//                    != PackageManager.PERMISSION_GRANTED) {
//                lstPermissions.add(Manifest.permission.RECORD_AUDIO);
//            }
//
//            if (isVideoCall) {
//                if (ContextCompat.checkSelfPermission(this,
//                        Manifest.permission.CAMERA)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    lstPermissions.add(Manifest.permission.CAMERA);
//                }
//            }
//
//            if (lstPermissions.size() > 0) {
//                String[] permissions = new String[lstPermissions.size()];
//                for (int i = 0; i < lstPermissions.size(); i++) {
//                    permissions[i] = lstPermissions.get(i);
//                }
//                ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSION_CALL);
//                return;
//            }
//        }
//
//        //create audio manager to control audio device
//        audioManager = StringeeAudioManager.create(OutgoingCallActivity.this);
//        audioManager.start(new StringeeAudioManager.AudioManagerEvents() {
//            @Override
//            public void onAudioDeviceChanged(StringeeAudioManager.AudioDevice selectedAudioDevice, Set<StringeeAudioManager.AudioDevice> availableAudioDevices) {
//            }
//        });
//        audioManager.setSpeakerphoneOn(isVideoCall);
//        makeCall();
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        tvState.setText("Ended");
//        endCall();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        boolean isGranted = false;
//        if (grantResults.length > 0) {
//            for (int i = 0; i < grantResults.length; i++) {
//                if (grantResults[i] != android.content.pm.PackageManager.PERMISSION_GRANTED) {
//                    isGranted = false;
//                    break;
//                } else {
//                    isGranted = true;
//                }
//            }
//        }
//        if (requestCode == REQUEST_PERMISSION_CALL) {
//            if (!isGranted) {
//                finish();
//            } else {
//                makeCall();
//            }
//        }
//    }
//
//    private void makeCall() {
//        //make new call
//        mStringeeCall = new StringeeCall(Tab_1_Class_Test.client, from, to);
//        mStringeeCall.setVideoCall(isVideoCall);
//
//        mStringeeCall.setCallListener(new StringeeCall.StringeeCallListener() {
//            @Override
//            public void onSignalingStateChange(StringeeCall stringeeCall, final StringeeCall.SignalingState signalingState, String s, int i, String s1) {
//                Log.e("Stringee", "======== Custom data: " + stringeeCall.getCustomDataFromYourServer());
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mSignalingState = signalingState;
//                        switch (signalingState) {
//                            case CALLING:
//                                tvState.setText("Outgoing call");
//                                break;
//                            case RINGING:
//                                tvState.setText("Ringing");
//                                break;
//                            case ANSWERED:
//                                tvState.setText("Starting");
//                                if (mMediaState == StringeeCall.MediaState.CONNECTED) {
//                                    tvState.setText("Started");
//                                }
//                                break;
//                            case BUSY:
//                                tvState.setText("Busy");
//                                endCall();
//                            case ENDED:
//                                tvState.setText("Ended");
//                                endCall();
//                                break;
//                        }
//                    }
//                });
//            }
//
//            @Override
//            public void onError(StringeeCall stringeeCall, int i, String s) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Utils1vs1.reportMessage(OutgoingCallActivity.this, s);
//                    }
//                });
//            }
//
//            @Override
//            public void onHandledOnAnotherDevice(StringeeCall stringeeCall, StringeeCall.SignalingState signalingState, String s) {
//
//            }
//
//            @Override
//            public void onMediaStateChange(StringeeCall stringeeCall, final StringeeCall.MediaState mediaState) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mMediaState = mediaState;
//                        if (mediaState == StringeeCall.MediaState.CONNECTED) {
//                            if (mSignalingState == StringeeCall.SignalingState.ANSWERED) {
//                                tvState.setText("Started");
//                            }
//                        }
//                    }
//                });
//            }
//
//            @Override
//            public void onLocalStream(final StringeeCall stringeeCall) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (stringeeCall.isVideoCall()) {
//                            mLocalViewContainer.removeAllViews();
//                            mLocalViewContainer.addView(stringeeCall.getLocalView());
//                            stringeeCall.renderLocalView(true);
//                        }
//                    }
//                });
//            }
//
//            @Override
//            public void onRemoteStream(final StringeeCall stringeeCall) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (stringeeCall.isVideoCall()) {
//                            mRemoteViewContainer.removeAllViews();
//                            mRemoteViewContainer.addView(stringeeCall.getRemoteView());
//                            stringeeCall.renderRemoteView(false);
//                        }
//                    }
//                });
//            }
//
//            @Override
//            public void onCallInfo(StringeeCall stringeeCall, final JSONObject jsonObject) {
//            }
//        });
//
//        mStringeeCall.makeCall();
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_mute:
//                isMute = !isMute;
//                btnMute.setBackgroundResource(isMute ? R.drawable.mic_off_icon : R.drawable.mic_on_icon);
//                if (mStringeeCall != null) {
//                    mStringeeCall.mute(isMute);
//                }
//                break;
//            case R.id.btn_speaker:
//                isSpeaker = !isSpeaker;
//                btnSpeaker.setBackgroundResource(isSpeaker ? R.drawable.videocam_on_icon : R.drawable.videocam_off_icon);
//                if (audioManager != null) {
//                    audioManager.setSpeakerphoneOn(isSpeaker);
//                }
//                break;
//            case R.id.btn_end:
//                tvState.setText("Ended");
//                endCall();
//                break;
//            case R.id.btn_video:
//                isVideo = !isVideo;
//                btnVideo.setImageResource(isVideo ? R.drawable.videocam_on_icon : R.drawable.videocam_off_icon);
//                if (mStringeeCall != null) {
//                    mStringeeCall.enableVideo(isVideo);
//                }
//                break;
//            case R.id.btn_switch:
//                if (mStringeeCall != null) {
//                    mStringeeCall.switchCamera(new StatusListener() {
//                        @Override
//                        public void onSuccess() {
//                            cameraId = cameraId == 0 ? 1 : 0;
//                        }
//                    }, cameraId == 0 ? 1 : 0);
//                }
//                break;
//        }
//    }
//
//    private void endCall() {
//        mStringeeCall.hangup();
//        if (audioManager != null) {
//            audioManager.stop();
//            audioManager = null;
//        }
//        Utils1vs1.postDelay(new Runnable() {
//            @Override
//            public void run() {
//                Common.isInCall = false;
//                finish();
//            }
//        }, 1000);
//    }
}
