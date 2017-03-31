package com.example.fang.adyuyinhe;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.tts.client.SpeechSynthesizer;

public class MainActivity extends AppCompatActivity {
    private EditText mInput;
    private SpeechSynthesizer mSpeechSynthesizer;
    private String name="kz";
    private static final int REQUEST_CODE = 0;
    private static final int REQUEST_CODES = 1;
    private String [] Permission={Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInput = (EditText) this.findViewById(R.id.mainactivity_et);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            //进入到这里代表没有权限.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
        } else {

        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //进入到这里代表没有权限.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODES);
        } else {

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults.length >0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //用户同意授权
                    Toast.makeText(MainActivity.this, "申请成功！！！", Toast.LENGTH_SHORT).show();
                }else{
                    //用户拒绝授权
                    Toast.makeText(MainActivity.this, "申请失败！！！", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODES:
                if(grantResults.length >0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //用户同意授权
                    Toast.makeText(MainActivity.this, "申请成功！！！", Toast.LENGTH_SHORT).show();
                }else{
                    //用户拒绝授权
                    Toast.makeText(MainActivity.this, "申请失败！！！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    //普通女声
    public void puTongWoman(View view) {
        speak(0);
    }

    //特殊女声
    public void qingGanWoman(View view) {
        speak(4);
    }

    //普通男声
    public void puTongMan(View view) {
        speak(3);
    }

    //特殊男声
    public void qingGanMan(View view) {
        speak(6);
    }

    private void speak(int speaker) {
        //若是每次都这样是不是会有内存问题呢？需要思考改进
        VoiceUtils utils = new VoiceUtils();
        utils.init(this, speaker);
        mSpeechSynthesizer = utils.getSyntheszer();
        String text = this.mInput.getText().toString();
        //需要合成的文本text的长度不能超过1024个GBK字节。
        if (TextUtils.isEmpty(mInput.getText())) {
            text = "帅镇！！！";
            mInput.setText(text);
        }
        this.mSpeechSynthesizer.speak(text);
    }

    //释放缓存
    @Override
    protected void onDestroy() {
        this.mSpeechSynthesizer.release();
        super.onDestroy();
    }
}