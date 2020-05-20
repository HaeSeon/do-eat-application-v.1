package com.example.do_eat_application_v1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {

    val realm = Realm.getDefaultInstance()  //Realm 객체 초기화

    override fun onCreate(savedInstanceState: Bundle?) {    //앱이 최초로 실행 됐을 떄 수행
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  //xml 화면 뷰를 연결한다.

        val realmResult = realm.where<Todo>()
            .findAll()
            .sort("date", Sort.DESCENDING)  //sort() 메서드를 이용하여 날짜순으로 내림차순 정렬

        //TodoListAdapter 클래스에 할 일 목록인 realmResult 를 전달하여 어댑터 인스턴스를 생성한다.
        val adapter = TodoListAdapter(realmResult) { id ->
            startActivity(Intent(this@MainActivity, EditActivity::class.java).putExtra("id", id))
        }

        //데이터가 변경되면 어댑터 적용, addChangeListner 으로 데이터가 변경될 때마다 어뎁터에 알려줌
        realmResult.addChangeListener { _ ->
            adapter.notifyDataSetChanged()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        addFab.setOnClickListener{startActivity<EditActivity>()}

    }



    override fun onDestroy() {
        super.onDestroy()
        realm.close()   //Realm 객체 해제
    }


}
