package com.yinlei.permissionxkt

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
 * 运行时权限的封装需要特定的上下文依赖。
 * 一般需要在activity中接收onRequestPermissionsResult()回调才行。所以不能简单的将整个操作封装到一个独立的类中
 * 特别的解决方案：封装到BaseActivity或提供一个透明的Activity来处理运行时权限。
 * 普遍的技巧：提供一个隐藏的Fragment，不用担心性能影响，非常轻量级
 */

typealias PermissionCallback = (Boolean, List<String>) -> Unit

class InvisibleFragment : Fragment(){

    // 运行时权限申请结果的回调通知方式
    private var callback: PermissionCallback? = null

    // 申请权限
    fun requestNow(cb: PermissionCallback, vararg permissions: String) {
        callback = cb
        requestPermissions(permissions, 1)
    }

    // 处理运行时权限的申请结果
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            // 所有被用户拒绝的权限
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            val allGranted = deniedList.isEmpty()
            callback?.let {
                it(allGranted, deniedList)
            }
        }
    }

}