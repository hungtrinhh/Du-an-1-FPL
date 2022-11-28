
package com.example.myapplication.Firebase;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Model.Game;
import com.example.myapplication.Model.Hoadon;
import com.example.myapplication.Model.Hoadonchoigame;
import com.example.myapplication.Model.Hoadonnaptien;
import com.example.myapplication.Model.Notify;
import com.example.myapplication.Model.User;
import com.example.myapplication.Model.Voucher;
import com.example.myapplication.R;
import com.example.myapplication.Service.UpdateGameService;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FbDao {
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static final String TAG = "Firebase Dao";
    public static List<Game> listGame;
    public static List<User> listUser;
    public static List<Voucher> listVoucher;
    public static List<Notify> listNotify;
    public static List<Hoadon> hoadonList;
    public FirebaseStorage storageFireBase;
    public static StorageReference avatatRef;
    private int[]  imageAvatarGame = new int[]{ R.drawable.game_ghost_house,R.drawable.game_bounce_house,R.drawable.racingcar,R.drawable.gun, R.drawable.game_nhun_nhay,R.drawable.game_bao_nha , R.drawable.game_jumping_house, R.drawable.game_cau_truot, R.drawable.game_suc_cac, R.drawable.game_xich_du};


    private static List<Hoadonnaptien> hoadonnaptienList;
    private static List<Hoadonchoigame> hoadonchoigameList;


    public static List<Notify> getListNotify() {
        return listNotify;
    }

    public static List<Game> getListGame() {
        return listGame;
    }

    public static List<Voucher> getListVoucher() {
        return listVoucher;
    }

    public static java.util.List<User> getList() {
        return listUser;
    }

    public static Bitmap Avatar;
    public static User UserLogin;
    public static Activity activity;
    public static boolean Login = false;
    //trả về trạng thái khi load dữ liệu xong thằng nào đụng vào đấm chết
    public static boolean LoadedUser = false;
    public static boolean LoadedAvatar = false;
    public static boolean UpdatedUser = false;
    public static boolean UpLoadedAvatar = false;

    //hàm khởi tạo để trả về userId
    public FbDao(Activity context) {
        hoadonList = new ArrayList<>();
        activity = context;
        storageFireBase = FirebaseStorage.getInstance();
        avatatRef = storageFireBase.getReference().child("avatar");
        ReadUser();
        ReadVoucher();
        ReadGame();
        ReadNotify();


    }

    public FbDao() {
    }


    public static String getNameGameFromID(int id) {
        String tenGame = "";
        for (Game game : listGame) {
            if (game.getId() == id) {
                tenGame = game.getTenGame();
            }
        }
        return tenGame;
    }

    //hàm update avatatar cho user
    public void UpLoadavatar(ImageView imageView) {
        String id = UserLogin.getId();
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = avatatRef.child(id).putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e(TAG, "onFailure: to upload ", null);
                UpLoadedAvatar = true;
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.e(TAG, "onSuccess: to upload ", null);
                UpLoadedAvatar = true;
            }
        });
    }
    //hàm load avatar

    public static void Thanhtoantien(float money) {
        money = UserLogin.getSodu() - money;
        Map<String, Object> map = new HashMap<>();
        map.put("sodu", money);
        DatabaseReference userRef = database.getReference("Users").child(UserLogin.getId());
        userRef.updateChildren(map, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Log.d(TAG, "Thanh toán thành công");
            }
        });

    }

    public static void LoadAvatarFromID() {
        String id = UserLogin.getId();
        StorageReference avartar = avatatRef.child((id));
        final long ONE_MEGABYTE = 1024 * 1024;
        avartar.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Log.e(TAG, "onSuccess: ", null);
                Avatar = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                UserLogin.setAvatar(Avatar);
                LoadedAvatar = true;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e(TAG, "onFailure: ", null);
                LoadedAvatar = true;
            }
        });
    }

    //hàm này trả về ref của hoá đơn chơi game
    public String getReferenceToday() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
        String s = dateFormat.format(date);
        return s;
    }

    //hàm chơi game ko hiểu đừng đọc
    public void PlaygameGio(int minute, String idGame, float cost) {
        long milisecond = minute * 60 * 1000;
        Date curenTime = new Date();
        Date endTime = new Date(milisecond + curenTime.getTime());
        getReferenceToday();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String curenTimetoString = dateFormat.format(curenTime);
        String endTimetoString = dateFormat.format(endTime);
        DatabaseReference hoadonchoigameRef = database.getReference("Hoadonchoigame").child(getReferenceToday());
        Hoadonchoigame hoadon = new Hoadonchoigame();
        hoadon.setDateStart(curenTimetoString);
        hoadon.setUserid(UserLogin.getId());
        hoadon.setCost(cost);
        hoadon.setGameid(idGame);
        hoadon.setDateEnd(endTimetoString);
        hoadonchoigameRef.push().setValue(hoadon);
        Map<String, Object> map = new HashMap<>();
        map.put("trangThai", "Đang được chơi");
        database.getReference("Game").child(idGame).updateChildren(map, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(activity, "Chơi thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //hàm login và bắt data cho userLogin để userLogin thay đổi data theo thời gian thực
    public static void Login(String id) {
        DatabaseReference myRef = database.getReference("Users");
        DatabaseReference userRef = myRef.child(id);
        Bitmap avatar = UserLogin.getAvatar();
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserLogin = snapshot.getValue(User.class);
                UserLogin.setId(snapshot.getKey());
                UserLogin.setAvatar(avatar);
                ReadHistory();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "onCancelled: " + error, null);
            }
        });
    }

    //hàm đọc về dữ liệu game
    private void ReadGame() {
        listGame = new ArrayList<>();
        DatabaseReference myRef = database.getReference("Game");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listGame.clear();
                for (DataSnapshot dt : dataSnapshot.getChildren()) {
                    Game u = dt.getValue(Game.class);
                    if (u == null) {
                        continue;
                    }
                    listGame.add(u);
                }
                setImgGame();

                Log.d(TAG, "Đã nhận dữ liệu Game: ");
                activity.startService(new Intent(activity, UpdateGameService.class));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e(TAG, "DatabaseError: " + error.toString()
                );
            }
        });
    }
    private void setImgGame(){
        listGame = FbDao.getListGame();
        for (int i=1;i<listGame.size()+1;i++){
            Game game = listGame.get(i-1);
            if (game.getId()==i){
                game.setImgGame(imageAvatarGame[i-1]);
            }
        }
    }

    private static void ReadHistory() {
        hoadonchoigameList = new ArrayList<>();
        hoadonnaptienList = new ArrayList<>();

        DatabaseReference myRef = database.getReference("Hoadonchoigame");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                hoadonchoigameList.clear();
                for (DataSnapshot dt : dataSnapshot.getChildren()) {
                    for (DataSnapshot data : dt.getChildren()) {
                        for (DataSnapshot d : data.getChildren()
                        ) {
                            Hoadonchoigame u = d.getValue(Hoadonchoigame.class);
                            if (u == null) {
                                continue;
                            }
                            if (u.getUserid().equals(UserLogin.getId())) {
                                hoadonchoigameList.add(u);
                                Log.d(TAG, "onDataChange: " + u.toString());
                            }
                        }

                    }
                }
                hoadonList.clear();
                hoadonList.addAll(hoadonchoigameList);
                hoadonList.addAll(hoadonnaptienList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e(TAG, "DatabaseError: " + error.toString()
                );
            }
        });
        DatabaseReference myRef1 = database.getReference("HoaDonNapTien");
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                hoadonnaptienList.clear();
                for (DataSnapshot dt : dataSnapshot.getChildren()) {
                    Hoadonnaptien u = dt.getValue(Hoadonnaptien.class);
                    if (u == null) {
                        continue;
                    }
                    if (u.getUserId().equals(UserLogin.getId())) {
                        hoadonnaptienList.add(u);
                        Log.d(TAG, "onDataChange: " + u.toString());
                    }
                }
                hoadonList.clear();
                hoadonList.addAll(hoadonchoigameList);
                hoadonList.addAll(hoadonnaptienList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e(TAG, "DatabaseError: " + error.toString()
                );
            }
        });
    }


    //hàm đọc về dữ liệu voutcher
    private void ReadVoucher() {
        Log.d(TAG, "ReadVoucher: ");
        listVoucher = new ArrayList<>();
        DatabaseReference myRef = database.getReference("Voucher");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listVoucher.clear();
                for (DataSnapshot dt : dataSnapshot.getChildren()) {
                    Voucher u = dt.getValue(Voucher.class);
                    if (u == null) {
                        continue;
                    }
                    listVoucher.add(u);
                }
                Log.d(TAG, "Đã nhận dữ liệu voucher: ");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e(TAG, "DatabaseError: " + error.toString()
                );
            }
        });
    }

    private void ReadNotify() {
        Log.d(TAG, "ReadVoucher: ");
        listNotify = new ArrayList<>();
        DatabaseReference myRef = database.getReference("Notify");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listNotify.clear();
                for (DataSnapshot dt : dataSnapshot.getChildren()) {
                    Notify u = dt.getValue(Notify.class);
                    if (u == null) {
                        continue;
                    }
                    listNotify.add(u);
                }
                Log.d(TAG, "Đã nhận dữ liệu voucher: ");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e(TAG, "DatabaseError: " + error.toString()
                );
            }
        });
    }

    //hàm thêm user khi đăng kí
    public void AddUser(User user) {
        DatabaseReference myRef = database.getReference("Users");
        myRef.push().setValue(user);
    }

    public static void AddNotify(Notify notify) {
        DatabaseReference myRef = database.getReference();
        myRef.child("Notify").push().setValue(notify);
    }

    public static void AddHoaDonNap(Hoadonnaptien hoadonnaptien) {
        DatabaseReference myRef = database.getReference();
        myRef.child("HoaDonNapTien").push().setValue(hoadonnaptien);
    }

    //hàm cập nhạt lại user
    public void UpdateUser(User user1) {
        DatabaseReference myRef = database.getReference("Users").child(user1.getId());
        User user = user1;
        user.setAvatar(null);
        user.setId(null);
        myRef.setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                UpdatedUser = true;
            }
        });
    }

    //hàm đọc thông tin user
    public void ReadUser() {
        Log.d(TAG, "ReadUser: ");
        listUser = new ArrayList<>();
        DatabaseReference myRef = database.getReference("Users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dt : dataSnapshot.getChildren()) {
                    User u = dt.getValue(User.class);
                    if (u == null) {
                        continue;
                    }
                    u.setId(dt.getKey());
                    listUser.add(u);
                }
                LoadedUser = true;
                Log.d(TAG, "Đã nhận dữ liệu User");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e(TAG, "DatabaseError: " + error.toString()
                );
            }
        });
    }
    ///////////////////////////////////////////////////////////////////////////
}