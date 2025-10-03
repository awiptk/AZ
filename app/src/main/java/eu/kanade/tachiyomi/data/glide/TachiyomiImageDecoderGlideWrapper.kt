package eu.kanade.tachiyomi.data.glide

import android.graphics.Bitmap
import android.os.Build
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapResource
import com.bumptech.glide.load.resource.bitmap.Downsampler
import eu.kanade.tachiyomi.util.system.ImageUtil
import tachiyomi.decoder.ImageDecoder
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.ByteBuffer

class TachiyomiImageDecoderGlideWrapper {
    class InputStreamDecoder(private val bitmapPool: BitmapPool) : ResourceDecoder<InputStream, Bitmap> {
        override fun handles(
            source: InputStream,
            options: Options
        ): Boolean {
            return when (ImageUtil.findImageType(source)) {
                ImageUtil.ImageType.JXL, ImageUtil.ImageType.AVIF -> true
                ImageUtil.ImageType.HEIF -> Build.VERSION.SDK_INT < Build.VERSION_CODES.O
                else -> false
            }
        }

        override fun decode(
            source: InputStream,
            width: Int,
            height: Int,
            options: Options
        ): Resource<Bitmap>? {
            val decoder = ImageDecoder.newInstance(source)

            if (decoder == null || decoder.width == 0 || decoder.height == 0) {
                return null
            }

            val rgb565 = options[Downsampler.DECODE_FORMAT] === DecodeFormat.PREFER_RGB_565
            val bitmap = decoder.decode(rgb565 = rgb565)
            decoder.recycle()

            if (bitmap == null) {
                return null
            }

            return BitmapResource.obtain(bitmap, bitmapPool)
        }
    }

    class ByteBufferDecoder(bitmapPool: BitmapPool) : ResourceDecoder<ByteBuffer, Bitmap> {
        private val streamDecoder = InputStreamDecoder(bitmapPool)

        override fun handles(
            source: ByteBuffer,
            options: Options
        ): Boolean {
            val sourceCopy = ByteArray(source.remaining())
            source.get(sourceCopy)
            return streamDecoder.handles(ByteArrayInputStream(sourceCopy), options)
        }

        override fun decode(
            source: ByteBuffer,
            width: Int,
            height: Int,
            options: Options
        ): Resource<Bitmap>? = streamDecoder.decode(ByteArrayInputStream(source.array()), width, height, options)
    }
}
