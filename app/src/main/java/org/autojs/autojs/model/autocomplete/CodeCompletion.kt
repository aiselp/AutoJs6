package org.autojs.autojs.model.autocomplete

/**
 * Created by Stardust on 2018/2/3.
 * Transformed by 抠脚本人 on Jul 11, 2023.
 */
class CodeCompletion {
    val hint: String
    val url: String?
    private val mInsertText: String?
    private val mInsertPos: Int

    constructor(hint: String, url: String?, insertPos: Int) {
        this.hint = hint
        this.url = url
        mInsertPos = insertPos
        mInsertText = null
    }

    constructor(hint: String, url: String, insertText: String?) {
        this.hint = hint
        this.url = url
        mInsertText = insertText
        mInsertPos = -1
    }

    val insertText: String
        get() {
            if (mInsertText != null) return mInsertText
            return if (mInsertPos == 0) {
                hint
            } else hint.substring(mInsertPos)
        }
}