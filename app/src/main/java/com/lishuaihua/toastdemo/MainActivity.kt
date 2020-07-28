package com.lishuaihua.toastdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lishuaihua.toast.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt.setOnClickListener {
            ToastUtils.show("我是吐司")
        }
    }
}