package eu.kanade.tachiyomi.util.storage

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.ParcelFileDescriptor
import androidx.core.content.FileProvider
import eu.kanade.tachiyomi.BuildConfig
import java.io.File
import java.io.InputStream
import java.nio.channels.Channels
import java.nio.channels.FileChannel

/**
 * Returns the uri of a file
 *
 * @param context context of application
 */
fun File.getUriCompat(context: Context): Uri {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", this)
    } else {
        Uri.fromFile(this)
    }
}

fun File.openReadOnlyChannel(context: Context): FileChannel {
    return ParcelFileDescriptor.AutoCloseInputStream(context.contentResolver.openFileDescriptor(this.getUriCompat(context), "r")).channel
}

fun FileChannel.toInputStream(): InputStream {
    return Channels.newInputStream(this)
}
