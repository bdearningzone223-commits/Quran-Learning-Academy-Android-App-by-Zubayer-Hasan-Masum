package com.example.audio

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale
import java.util.UUID

data class AudioPlaybackState(
    val isReady: Boolean = false,
    val isSpeaking: Boolean = false,
    val activeTag: String? = null,
    val arabicLocaleSupported: Boolean = false,
    val slowMode: Boolean = false
)

class ArabicAudioEngine(context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = TextToSpeech(context.applicationContext, this)
    private val _state = MutableStateFlow(AudioPlaybackState())
    val state: StateFlow<AudioPlaybackState> = _state.asStateFlow()

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val candidateLocales = listOf(
                Locale.forLanguageTag("ar-SA"),
                Locale.forLanguageTag("ar-EG"),
                Locale.forLanguageTag("ar")
            )
            var arabicAvailable = false
            for (locale in candidateLocales) {
                val res = tts?.setLanguage(locale)
                if (res != TextToSpeech.LANG_MISSING_DATA && res != TextToSpeech.LANG_NOT_SUPPORTED) {
                    arabicAvailable = true
                    break
                }
            }
            if (!arabicAvailable) {
                tts?.setLanguage(Locale.forLanguageTag("ar"))
            }
            tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) {
                    _state.value = _state.value.copy(isSpeaking = true)
                }

                override fun onDone(utteranceId: String?) {
                    _state.value = _state.value.copy(isSpeaking = false, activeTag = null)
                }

                @Deprecated("Deprecated in Java")
                override fun onError(utteranceId: String?) {
                    _state.value = _state.value.copy(isSpeaking = false, activeTag = null)
                }
            })
            _state.value = _state.value.copy(
                isReady = true,
                arabicLocaleSupported = arabicAvailable
            )
        }
    }

    fun toggleSlowMode() {
        _state.value = _state.value.copy(slowMode = !_state.value.slowMode)
    }

    fun speak(
        arabicText: String,
        fallbackEnglishPhonetic: String? = null,
        tag: String = arabicText
    ) {
        val engine = tts ?: return
        val current = _state.value
        val rate = if (current.slowMode) 0.62f else 0.88f
        engine.setSpeechRate(rate)
        engine.setPitch(1.02f)

        val textToSpeak = arabicText.ifBlank { fallbackEnglishPhonetic ?: "" }

        _state.value = current.copy(isSpeaking = true, activeTag = tag)
        engine.speak(
            textToSpeak,
            TextToSpeech.QUEUE_FLUSH,
            null,
            UUID.randomUUID().toString()
        )
    }

    fun stop() {
        tts?.stop()
        _state.value = _state.value.copy(isSpeaking = false, activeTag = null)
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
    }
}
