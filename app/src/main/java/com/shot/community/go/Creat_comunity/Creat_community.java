package com.shot.community.go.Creat_comunity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shot.community.go.R;

/**
 * Created by god on 2017/11/16.
 */


public class Creat_community extends AppCompatActivity {
    EditText editText1 , editText2 ,editText3 ,editText4 , editText5;
    Button button;
    int nn = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creat_community);
        editText1 = (EditText)findViewById(R.id.et_name);
        editText2 = (EditText)findViewById(R.id.et_cellphone);
        editText3 = (EditText)findViewById(R.id.et_address);
        editText4 = (EditText)findViewById(R.id.et_email);
        editText5 = (EditText)findViewById(R.id.et_comname);
        button = (Button)findViewById(R.id.creat_com_button);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int g = 0;g<Forget_check.C_name.size();g++){
                    if (editText5.getText().toString().equals(Forget_check.C_name.get(g))){
                        nn=1;
                        break;
                    }
                    else
                    {
                        nn=0;
                    }
                }
                final  AlertDialog.Builder real_delete = new AlertDialog.Builder(Creat_community.this,R.style.MyAlertDialogStyle);
                real_delete.setTitle("確定創建社區 ? ");
                real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(editText1.getText().length()>0 && editText2.getText().length()>0 &&editText3.getText().length()>0 &&
                                editText4.getText().length()>0 &&editText5.getText().length()>0 ){

                            if (nn==1){
                                final  AlertDialog.Builder real_delete1 = new AlertDialog.Builder(Creat_community.this,R.style.MyAlertDialogStyle);
                                real_delete1.setTitle("該社區名稱已經被使用過 ");
                                real_delete1.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                real_delete1.show();
                            }
                            else{
                                Creat_comunity_http creat_comunity_http = new Creat_comunity_http(Creat_community.this , 0);
                                creat_comunity_http.execute("http://140.136.155.79/community/register_finish.php" , editText1.getText().toString(),
                                        editText2.getText().toString() , editText3.getText().toString() , editText4.getText().toString() , editText5.getText().toString() , randomString(20));
                                final  AlertDialog.Builder real_delete = new AlertDialog.Builder(Creat_community.this,R.style.MyAlertDialogStyle);
                                real_delete.setTitle("創建成功 ! 請至您的信箱領取啟動碼 ! ");
                                real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                        dialog.dismiss();
                                    }
                                });
                                real_delete.show();
                            }



                        }
                        else
                        {
                            final  AlertDialog.Builder real_delete = new AlertDialog.Builder(Creat_community.this,R.style.MyAlertDialogStyle);
                            real_delete.setTitle("創建失敗 請重新輸入!");
                            real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();
                                }
                            });
                            real_delete.show();
                            editText1.setText(null);
                            editText2.setText(null);
                            editText3.setText(null);
                            editText4.setText(null);
                            editText5.setText(null);
                        }

                        dialog.dismiss();
                    }
                });
                real_delete.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                real_delete.show();

            }
        });



    }



    @NonNull
    public static String randomString(int len) {
        String str = "0123456789abcdefghijklmnopqrstuvwxyz";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            int idx = (int)(Math.random() * str.length());
            sb.append(str.charAt(idx));
        }
        return sb.toString();
    }
}
