// SPDX-License-Identifier: Apache-2.0 AND GPL-3.0-only
package helium314.keyboard.latin.utils

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

object LsposedUtils {
    private const val TAG = "LsposedUtils"

    fun fixScope(context: Context) {
        val packageName = context.packageName
        val checkCommand = "/data/adb/lspd/cli scope ls $packageName || /data/adb/modules/zygisk_vector/cli scope ls $packageName"
        val fullCheckCommand = "su -c '$checkCommand'"

        try {
            val process = Runtime.getRuntime().exec(arrayOf("sh", "-c", fullCheckCommand))
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val output = reader.readText()
            process.waitFor()

            val hasSystemServer = output.contains("system_server")
            val hasPicoExt = output.contains("com.picovr.systemext")

            if (!hasSystemServer || !hasPicoExt) {
                Log.i(TAG, "LSPosed scope missing targets, attempting fix")
                val fixCommands = listOf(
                    "su -c '/data/adb/lspd/cli modules enable $packageName || /data/adb/modules/zygisk_vector/cli modules enable $packageName'",
                    "su -c '/data/adb/lspd/cli scope add $packageName system_server || /data/adb/modules/zygisk_vector/cli scope add $packageName system_server'",
                    "su -c '/data/adb/lspd/cli scope add $packageName com.picovr.systemext || /data/adb/modules/zygisk_vector/cli scope add $packageName com.picovr.systemext'"
                )
                fixCommands.forEach { cmd ->
                    Runtime.getRuntime().exec(arrayOf("sh", "-c", cmd)).waitFor()
                }
            } else {
                Log.i(TAG, "LSPosed scope is correct")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to fix scope", e)
        }
    }
}
