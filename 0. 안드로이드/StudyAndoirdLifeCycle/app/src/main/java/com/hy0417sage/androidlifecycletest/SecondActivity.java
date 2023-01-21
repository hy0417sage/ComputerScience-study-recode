package com.hy0417sage.androidlifecycletest;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    // onCreate : 액티비티를 생성할 때 실행
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d("(check) SecondActivity", "onCreate");
    }

    // onStart : 액티비티가 화면에 표시되기 직전에 호출, 화면에 진입할 때마다 실행
    @Override
    public void onStart() {
        super.onStart();
        Log.d("SecondActivity", "onStart()");
        Log.d("(check) SecondActivity", "onStart");
    }

    // onResume : 잠시 액티비티가 일시정지 되었다가 돌아오는 경우 호출, 액티비티가 재개되었을 때 실행
    @Override
    public void onResume() {
        super.onResume();
        Log.d("SecondActivity", "onResume()");
        Log.d("(check) SecondActivity", "onResume");
    }

    // onPause : 방해되는 이벤트가 발생하면 호출, 실행할 필요가 없는 기능들을 일시정지
    @Override
    public void onPause() {
        super.onPause();
        Log.d("SecondActivity", "onPause()");
        Log.d("(check) SecondActivity", "onPause");
    }

    // onStop : 액티비티가 사용자에게 더이상 보이지 않으면 호출
    @Override
    public void onStop() {
        super.onStop();
        Log.d("SecondActivity", "onStop()");
        Log.d("(check) SecondActivity", "onStop");
    }

    // onRestart : 홈으로 나갔다가 다시 돌아오거나 다른 액티비티로 갔다가 뒤로 가기 버튼을 통해서 돌아오는 경우 호출
    @Override
    public void onRestart() {
        super.onRestart();
        Log.d("SecondActivity", "onRestart()");
        Log.d("(check) SecondActivity", "onRestart");
    }

    // onDestroy : 앱을 종료하는 경우 호출
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("SecondActivity", "onDestroy()");
        Log.d("(check) SecondActivity", "onDestroy");
    }

}
