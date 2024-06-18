package com.tondz.funquiz.Database;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.tondz.funquiz.MainActivity;
import com.tondz.funquiz.Model.NguoiDung;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaiKhoanDBContext {
    public FirebaseDatabase mDatabase;
    public DatabaseReference mRef;
    public List<NguoiDung> NguoiDungList;
    public FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    public TaiKhoanDBContext(){
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("nguoidung");
        NguoiDungList = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
    }
    public void dangKyTaiKhoan(NguoiDung nguoiDung, Context context,Activity activity){
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("Đang đăng nhập");
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(nguoiDung.getEmail(),nguoiDung.getMatkhau()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                nguoiDung.setId(firebaseUser.getUid());
                mRef.child(nguoiDung.getId()).setValue(nguoiDung).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(context, "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(context, "Đăng ký tài khoản thất bại, có thể email đã được sử dụng", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    public void getListNguoiDung(){
        NguoiDungList = new ArrayList<>();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()
                ) {
                    NguoiDungList.add(dataSnapshot.getValue(NguoiDung.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void dangNhap(String email, String matkhau, Context context, Activity activity){
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("Đang đăng nhập");
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email,matkhau).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    activity.finish();
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(context,"Emai hoặc mật khẩu không chính xác",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void deleteNguoiDung(String id){
        mRef.child(id).removeValue();
    }
}
