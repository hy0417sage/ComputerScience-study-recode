package com.hy0417sage.androidlifecycletest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public AlertDialog dialog;

    // onCreate : 액티비티를 생성할 때 실행
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);
        dialog = builder.create();
    }

    // onStart : 액티비티가 화면에 표시되기 직전에 호출, 화면에 진입할 때마다 실행
    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart()", Toast.LENGTH_SHORT).show();
        Log.d("(check) MainActivity", "onStart");
    }

    // onResume : 잠시 액티비티가 일시정지 되었다가 돌아오는 경우 호출, 액티비티가 재개되었을 때 실행
    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume()", Toast.LENGTH_SHORT).show();
        Log.d("(check) MainActivity", "onResume");
    }

    // onPause : 방해되는 이벤트가 발생하면 호출, 실행할 필요가 없는 기능들을 일시정지
    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause()", Toast.LENGTH_SHORT).show();
        Log.d("(check) MainActivity", "onPause");
    }

    // onStop : 액티비티가 사용자에게 더이상 보이지 않으면 호출
    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop()", Toast.LENGTH_SHORT).show();
        Log.d("(check) MainActivity", "onStop");
    }

    // onRestart : 홈으로 나갔다가 다시 돌아오거나 다른 액티비티로 갔다가 뒤로 가기 버튼을 통해서 돌아오는 경우 호출
    @Override
    public void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart()", Toast.LENGTH_SHORT).show();
        Log.d("(check) MainActivity", "onRestart");
    }

    // onDestroy : 앱을 종료하는 경우 호출
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show();
        Log.d("(check) MainActivity", "onDestroy");
    }

    public void dialog_button(View view) {
        dialog.show();
    }

    public void button_activity_change(View view) {
        Intent downloadIntent = new Intent(this, SecondActivity.class);
        startActivity(downloadIntent);
    }
}