package com.tondz.funquiz.Database;

import com.tondz.funquiz.Model.CauHoi;
import com.tondz.funquiz.Model.Choi;
import com.tondz.funquiz.Model.NguoiDung;
import com.tondz.funquiz.R;

import java.util.ArrayList;

public class Common {
    public static Choi CHOI;
    public static NguoiDung NGUOI_DUNG;
    public static CauHoi CAU_HOI;
    public static int vitri=0;
    public static int SO_LAN_CHOI = 3;
    public static int CHECK_SOUND = 1;
    public static ArrayList<CauHoi>cauHoiArrayList = new ArrayList<>();
    public static String[]loiKhia ={"Bình tĩnh bạn ","SSai rồi kìa","Cố lên, cố lên","Có nên dừng cuộc chơi không bạn??","Ơ kìa, sai rồi","Chọn lại đê bạn","Bạn cần tập trung hơn"};
    public static String[]nhanXet = {"Cũng thường thôi bạn!!","Chiến tiếp không bạn ei","Quẩy tiếp đi xem nào","Nhây đó bạn","Hay lắm bạn, làm ván nữa","Xoắn não chưa bạn"};
    public static ArrayList<Integer>dsHinhAnh(){
        ArrayList<Integer>drawableArrayList = new ArrayList<>();
        drawableArrayList.add(R.drawable.troll_face0);
        drawableArrayList.add(R.drawable.troll_face1);
        drawableArrayList.add(R.drawable.troll_face2);
        drawableArrayList.add(R.drawable.troll_face3);
        drawableArrayList.add(R.drawable.troll_face4);
        drawableArrayList.add(R.drawable.troll_face5);
        drawableArrayList.add(R.drawable.troll_face6);
        drawableArrayList.add(R.drawable.troll_face7);
        drawableArrayList.add(R.drawable.troll_face0);
        drawableArrayList.add(R.drawable.troll_face9);
        drawableArrayList.add(R.drawable.troll_face4);
        drawableArrayList.add(R.drawable.troll_face4);
        drawableArrayList.add(R.drawable.troll_face2);
        drawableArrayList.add(R.drawable.troll_face7);
        return drawableArrayList;
    }
}
