@file:Depends("mirai/broadcastToGroup", "shout")

package mirai

import cf.wayzer.placehold.PlaceHoldApi
import net.mamoe.mirai.message.data.MessageSource.Key.quote
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

val address by config.key("mdtbaohe.work:24212", "服务器地址,为空则不显示")

globalEventChannel().subscribeMessages {
    case("服务器状态") {
        @Suppress("UNCHECKED_CAST")
        val getMapSnap = PlaceHoldApi.GlobalContext.getVar("wayzer.ext.mapSnap._get") as? () -> BufferedImage
        val addressInfo = if (address.isEmpty()) "" else "服务器地址: $address "
        var msg = this.message.quote() + """
                {addressInfo}版本 {game.version}
                火力版本：3.1.5
                当前战局为: [{map.id}]{map.name} 
                已抵挡反扑次数: {state.wave} 持续战斗时间: {state.gameTime:分钟}
                服务器TPS: {tps} 内存占用(MB) {heapUse}
                当前人数: {state.playerSize} 总单位数: {state.allUnit}
            """.trimIndent().with("addressInfo" to addressInfo, "receiver" to this.sender).toString()
        if (getMapSnap != null) {
            launch(Dispatchers.IO) {
                val file = File.createTempFile("status_Image", ".png")
                try {
                    ImageIO.write(getMapSnap(), "png", file)
                    file.toExternalResource("png").use {
                        withTimeoutOrNull(3000) {
                            msg += subject.uploadImage(it)
                        } ?: { msg += "上传图片超时" }
                    }
                } finally {
                    file.delete()
                }
                subject.sendMessage(msg)
            }
        } else subject.sendMessage(msg)
    }
}