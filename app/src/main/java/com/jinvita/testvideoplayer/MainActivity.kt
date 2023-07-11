package com.jinvita.testvideoplayer

import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.jinvita.testvideoplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val controller by lazy {
        object : MediaController(this) {
            override fun show() = super.show(0)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding) {
            videoView.setMediaController(controller)
            controller.setAnchorView(videoView)
            button1.setOnClickListener { playVideo(1) }
            button2.setOnClickListener { playVideo(2) }
            button3.setOnClickListener { playVideo(3) }
            button4.setOnClickListener { playVideo(4) }
            button5.setOnClickListener { playVideo(5) }
        }
    }

    private fun ActivityMainBinding.playVideo(index: Int) = with(videoView) {
        setVideoPath(
            "android.resource://$packageName/${
                when (index) {
                    1 -> R.raw.video1
                    2 -> R.raw.video2
                    3 -> R.raw.video3
                    4 -> R.raw.video4
                    5 -> R.raw.video5
                    else -> R.raw.video1
                }
            }"
        )
        setOnCompletionListener {
            controller.show(0)
            start()
        }
        setOnPreparedListener { controller.show(0) }
        start()

        textTitle.text = "비디오$index 재생 중"
    }
}