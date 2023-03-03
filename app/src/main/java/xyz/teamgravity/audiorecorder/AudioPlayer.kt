package xyz.teamgravity.audiorecorder

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import androidx.core.net.toUri
import java.io.File

class AudioPlayer(
    private val context: Context,
) {

    private var player: MediaPlayer? = null

    private fun initializeMediaPlayer(data: Uri) {
        player = MediaPlayer.create(context, data)
    }

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun play(data: File) {
        initializeMediaPlayer(data.toUri())
        player?.start()
    }

    fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
}