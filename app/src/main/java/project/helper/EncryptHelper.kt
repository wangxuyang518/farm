package project.helper

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * 加解密
 */
public class EncryptHelper {
    /**
     * 加密方法
     * @param origin  要加密的数据
     * @return 加密的结果
     * @throws Exception
     */
    companion object {
        public fun encode(origin: String): String? {
            try {
                //"算法/模式/补码方式"NoPadding PkcsPadding
                val cipher = Cipher.getInstance("AES/CBC/NoPadding")
                val blockSize = cipher.blockSize

                val dataBytes = origin.toByteArray()
                var plaintextLength = dataBytes.size
                if (plaintextLength % blockSize != 0) {
                    plaintextLength = plaintextLength + (blockSize - plaintextLength % blockSize)
                }

                val plaintext = ByteArray(plaintextLength)
                System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.size)

                val keyspec = SecretKeySpec("1234567890123456".toByteArray(), "AES")
                val ivspec = IvParameterSpec("1234567890123456".toByteArray())

                cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec)
                val encrypted = cipher.doFinal(plaintext)

                return Base64.encodeToString(encrypted, 1)
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }

        }
    }
}
