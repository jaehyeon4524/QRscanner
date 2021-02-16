// build.gradle, AndroidMainfest.xml 이 두파일에 추가해야 되는 내용 있습니다.
//QR 코드 스탠 하는 부분
package com.example.qrscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class scanner extends AppCompatActivity {
    Button a;
    private Button buttonScan;
    private IntentIntegrator qrScan;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        qrScan = new IntentIntegrator(this);

        textViewResult = (TextView)  findViewById(R.id.textViewResult);

        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        String url;
        String mainurl = "http://m.site.naver.com/0KPKk"; //정해놓은 QR코드 URL
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            try {
                //data를 json으로 변환
                JSONObject obj = new JSONObject(result.getContents());
            } catch (JSONException e) {
                e.printStackTrace();
                    url = result.getContents();
                    if(mainurl.equals(url)) { // url 결과 비교하여 성공시 인식 성공 출력
                        Toast.makeText(this, "인식 성공 ", Toast.LENGTH_LONG).show();
                        a = findViewById(R.id.button3);
                        a.setOnClickListener(new View.OnClickListener(){
                            public void onClick(View view){
                                Intent i= new Intent(scanner.this,review.class);//클래스 이름 변경시 변경해주어야함
                                startActivity(i);
                            }
                        });

                    }
                    else{//비교 결과가 다를 경우 인식 실패 출력
                        Toast.makeText(this, "인식 실패", Toast.LENGTH_LONG).show();
                        textViewResult.setText("인식 실패하여 리뷰를 작성할 수 없습니다.");
                    }
 /*               textViewResult.setText(result.getContents()); -> 넘어오는 정보 확이 하는 코드 */
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}