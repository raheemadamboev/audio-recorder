package xyz.teamgravity.audiorecorder

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import java.io.File
import java.io.FileOutputStream

class AudioRecorder(
    private val context: Context,
) {

    private var recorder: MediaRecorder? = null

    @Suppress("DEPRECATION")
    private fun initializeMediaRecorder() {
        recorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) MediaRecorder(context)
        else MediaRecorder()
    }

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun start(destination: File) {
        initializeMediaRecorder()
        recorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        recorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        recorder?.setOutputFile(FileOutputStream(destination).fd)
        recorder?.prepare()
        recorder?.start()
    }

    fun stop() {
        recorder?.stop()
        recorder?.reset()
        recorder = null
    }
}