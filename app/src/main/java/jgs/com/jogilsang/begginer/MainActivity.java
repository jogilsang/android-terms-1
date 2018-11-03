package jgs.com.jogilsang.begginer;

/**
 * Created by User on 2017-02-20.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    public LinearLayout termsPage;       // 이용약관 페이지
    public TextView termsDetail_1;       // 상세보기 -> href 웹뷰연결
    public RelativeLayout termsLayout_1; // 레이아웃 -> 동의시 파란색으로 채움
    public ImageView termsCheck_1;       // 체    크  -> 하얀색으로 변경
    public TextView termsAgree_1;        // 동    의  -> 하얀색으로 변경

    public TextView termsDetail_2;       // 상세보기 -> href 웹뷰연결
    public RelativeLayout termsLayout_2; // 레이아웃 -> 동의시 파란색으로 채움
    public ImageView termsCheck_2;       // 체    크  -> 하얀색으로 변경
    public TextView termsAgree_2;        // 동    의  -> 하얀색으로 변경

    public Button termsYes;              // 최종동의
    public Button termsNo;               // 취소

    // PUBLIC_VALUE
    public int TERMS_AGREE_1 = 0; // No Check = 0, CHeck = 1
    public int TERMS_AGREE_2 = 0; // No Check = 0, CHeck = 1


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE); //인트로화면이므로 타이틀바를 없앤다
        setContentView(R.layout.activity_main);

        initView();

        setAction();

    }

    public void initView(){

        // terms layout
        termsPage = (LinearLayout)findViewById(R.id.terms_page);           // 전체 페이지 다되면 GONE시켜야됨
        termsDetail_1 = (TextView) findViewById(R.id.terms_detail_1);      // 상세보기 -> href 웹뷰연결
        termsLayout_1 = (RelativeLayout)findViewById(R.id.terms_layout_1); // 레이아웃 -> 동의시 파란색으로 채움
        termsCheck_1  = (ImageView)findViewById(R.id.terms_check_1);       // 체    크  -> 하얀색으로 변경
        termsAgree_1  = (TextView)findViewById(R.id.terms_agree_1);        // 동    termsDetail_2;

        termsDetail_2 = (TextView) findViewById(R.id.terms_detail_2);      // 상세보기 -> href 웹뷰연결
        termsLayout_2 = (RelativeLayout)findViewById(R.id.terms_layout_2); // 레이아웃 -> 동의시 파란색으로 채움
        termsCheck_2  = (ImageView)findViewById(R.id.terms_check_2);       // 체    크  -> 하얀색으로 변경
        termsAgree_2  = (TextView)findViewById(R.id.terms_agree_2);        // 동    termsDetail_2;

        termsYes = (Button)findViewById(R.id.terms_yes);                   // 버튼 : yes
        termsNo = (Button)findViewById(R.id.terms_no);                     // 버튼 : no

        Linkify.TransformFilter mTransform = new Linkify.TransformFilter() {
            @Override
            public String transformUrl(Matcher match, String url) {
                return "";
            }
        };

        Pattern pattern = Pattern.compile(getString(R.string.terms_detail_button));

        Linkify.addLinks(termsDetail_1, pattern, getString(R.string.url_terms_1),null, mTransform);
        Linkify.addLinks(termsDetail_2, pattern, getString(R.string.url_terms_2) ,null, mTransform);
        //출처: http://gun0912.tistory.com/66 [박상권의 삽질블로그]

    }

    @Override
    public void onBackPressed() {
        actionExit();
    }

    public void setAction(){

        // 최종 약관 동의
        termsYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 첫번째 약관 체크여부
                if(TERMS_AGREE_1 == 1) {
                    // 두번쨰 약관 체크 여부
                    if(TERMS_AGREE_2 == 1) {

                        // 페이지 사라지기
//                        unCheckTermsAgreeOne();
//                        unCheckTermsAgreeTwo();
//                        termsPage.setVisibility(View.GONE);

                        // 페이저 인트로 액티비티로 고고
                        //startActivity(new Intent(MainActivity.this, RecomendActivity.class));

                    }
                    // 두번째 약관 체크안된경우 - 알람 메세지
                    else {
                        basicToast(getString(R.string.terms_agree_2_needed));

                        return ;
                    }

                }
                // 첫번째 약관 체크안된경우 - 알람 메세지
                else {
                    basicToast(getString(R.string.terms_agree_1_needed));
                    return;
                }

            }
        });

        // 최종 약관 거절
        termsNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actionExit();

            }
        });

        // 거절

        // 이용약관 레이아웃 클릭
        termsLayout_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch(TERMS_AGREE_1) {

                    // 체크가 안된상태 -> 따라서 체크된상태로 변경
                    case 0 :

                        // 레이아웃변경
                        checkTermsAgreeOne();

                        break;

                    // 체크가 된 상태 -> 따라서 체크 안된 상태로 변경
                    // 체크가 된 상태 -> 따라서 체크 안된 상태로 변경
                    case 1 :

                        unCheckTermsAgreeOne();

                        break;

                }

            }
        });



        // 이용약관 레이아웃 클릭
        termsLayout_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch(TERMS_AGREE_2) {

                    // 체크가 안된상태 -> 따라서 체크된상태로 변경
                    case 0 :

                        checkTermsAgreeTwo();

                        break;

                    // 체크가 된 상태 -> 따라서 체크 안된 상태로 변경
                    // 체크가 된 상태 -> 따라서 체크 안된 상태로 변경
                    case 1 :

                        unCheckTermsAgreeTwo();

                        break;

                }


            }
        });



    }

    public void actionExit() {

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        //dlg.setTitle("버튼 1개 대화상자"); // 제목
        dlg.setMessage(getString(R.string.action_exit_question)); // 내용
        //dlg.setIcon(R.drawable.ic_launcher); // 아이콘

        dlg.setNegativeButton(getString(R.string.message_yes), new  DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // onClick 메소드 매개변수가 DialogInterface 여야하네.
                //Toast.makeText(dlg.this, "good",0).show();


                moveTaskToBack(true);
                finish();

                //출처: http://whose.tistory.com/443 [Whose Tistory?!]
            }
        });

        dlg.setPositiveButton(getString(R.string.message_no), new  DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // onClick 메소드 매개변수가 DialogInterface 여야하네.
                //Toast.makeText(this, "종료",0).show();

            }
        });

        dlg.show(); // 보이다

    }

    public void checkTermsAgreeOne(){

        termsLayout_1.setBackgroundResource(R.drawable.rounded_border_main);
        termsCheck_1.setImageResource(R.drawable.ic_check_white_24dp);
        termsAgree_1.setTextColor(Color.WHITE);

        TERMS_AGREE_1 = 1;

    }

    public void unCheckTermsAgreeOne(){

        termsLayout_1.setBackgroundResource(R.drawable.rounded_border_edittext);
        termsCheck_1.setImageResource(R.drawable.ic_check_black_24dp);
        termsAgree_1.setTextColor(Color.BLACK);

        TERMS_AGREE_1 = 0;

    }

    public void checkTermsAgreeTwo(){

        termsLayout_2.setBackgroundResource(R.drawable.rounded_border_main);
        termsCheck_2.setImageResource(R.drawable.ic_check_white_24dp);
        termsAgree_2.setTextColor(Color.WHITE);

        TERMS_AGREE_2 = 1;

    }

    public void unCheckTermsAgreeTwo(){

        termsLayout_2.setBackgroundResource(R.drawable.rounded_border_edittext);
        termsCheck_2.setImageResource(R.drawable.ic_check_black_24dp);
        termsAgree_2.setTextColor(Color.BLACK);

        TERMS_AGREE_2 = 0;

    }

    public void basicToast(String message) {

        //Toast toast = Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.CENTER, 0, 0); -> 중앙에 보여주기
        //toast.show();
        // 메세지 보여주기

        //StyleableToast.makeText(TermsActivity.this, message,  Toast.LENGTH_SHORT, R.style.default_toast).show();

    }

}

