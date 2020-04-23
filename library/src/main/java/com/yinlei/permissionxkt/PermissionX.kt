package com.yinlei.permissionxkt

import androidx.fragment.app.FragmentActivity

// 对外接口部分
object PermissionX {

    private const val TAG = "InvisibleFragment"

    //FragmentActivity是AppCompatActivity的父类
    fun request(activity: FragmentActivity, vararg permissions: String, callback: PermissionCallback) {
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        fragment.requestNow(callback, *permissions)
    }

}