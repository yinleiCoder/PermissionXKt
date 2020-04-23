# PermissionXKt

简化Android运行时权限的用法

```groovy
    implementation 'com.yinlei.permissionxkt:permissionxkt:1.0.0'
```

example:
1. 代码
````kotlin
   // 权限申请
        PermissionX.request(this, Manifest.permission.CALL_PHONE, Manifest.permission.READ_CONTACTS) { allGranted, deniedList ->
            if (allGranted) {
                Toast.makeText(this,"All permissions are granted!" ,Toast.LENGTH_SHORT ).show()
            } else {
                Toast.makeText(this,"You denied $deniedList" ,Toast.LENGTH_SHORT ).show()
            }
        }
```
2. AndroidManifest.xml:

    <uses-permission android:name="android.permission.CALL_PHONE"/>
    <uses-permission android:name="android.permission.READ_CONTACTS"/>
