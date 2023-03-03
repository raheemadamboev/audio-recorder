package xyz.teamgravity.audiorecorder

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import xyz.teamgravity.audiorecorder.ui.theme.AudioRecorderTheme
import java.io.File

class MainActivity : ComponentActivity() {

    private val recorder: AudioRecorder by lazy { AudioRecorder(applicationContext) }
    private val player: AudioPlayer by lazy { AudioPlayer(applicationContext) }

    private var audio: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissions(arrayOf(Manifest.permission.RECORD_AUDIO), 0)
        setContent {
            AudioRecorderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Button(
                            onClick = {
                                audio = File(cacheDir, "audio.mp3")
                                recorder.start(audio ?: return@Button)
                            }
                        ) {
                            Text(text = stringResource(id = R.string.start_recording))
                        }
                        Button(
                            onClick = {
                                recorder.stop()
                            }
                        ) {
                            Text(text = stringResource(id = R.string.stop_recording))
                        }
                        Button(
                            onClick = {
                                player.play(audio ?: return@Button)
                            }
                        ) {
                            Text(text = stringResource(id = R.string.play))
                        }
                        Button(
                            onClick = {
                                player.stop()
                            }
                        ) {
                            Text(text = stringResource(id = R.string.stop))
                        }
                    }
                }
            }
        }
    }
}