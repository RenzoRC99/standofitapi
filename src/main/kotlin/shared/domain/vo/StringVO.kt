package shared.domain.vo

import shared.domain.voexceptions.string.StringVOException
import shared.domain.voexceptions.string.StringVOMessages


abstract class StringVO(
    value: String
) : BaseVO<String>(value) {

    protected fun ensureNotEmpty() {
        if (value.isEmpty()) {
            throw StringVOException(
                code = StringVOMessages.EMPTY.code,
                message = StringVOMessages.EMPTY.message
            )
        }
    }

    protected fun ensureNotBlank() {
        if (value.isBlank()) {
            throw StringVOException(
                code = StringVOMessages.BLANK.code,
                message = StringVOMessages.BLANK.message
            )
        }
    }

    protected fun ensureMinLength(min: Int) {
        if (value.length < min) {
            throw StringVOException(
                code = StringVOMessages.TOO_SHORT.code,
                message = StringVOMessages.TOO_SHORT.message
            )
        }
    }

    protected fun ensureMaxLength(max: Int) {
        if (value.length > max) {
            throw StringVOException(
                code = StringVOMessages.TOO_LONG.code,
                message = StringVOMessages.TOO_LONG.message
            )
        }
    }

    protected fun ensureAllowedCharacters(regex: Regex) {
        if (!regex.matches(value)) {
            throw StringVOException(
                code = StringVOMessages.INVALID_CHARACTERS.code,
                message = StringVOMessages.INVALID_CHARACTERS.message
            )
        }
    }

    protected fun ensureFormat(regex: Regex) {
        if (!regex.matches(value)) {
            throw StringVOException(
                code = StringVOMessages.INVALID_FORMAT.code,
                message = StringVOMessages.INVALID_FORMAT.message
            )
        }
    }

    protected fun ensureNoEmojis() {
        val emojiRegex = Regex("[\uD83C-\uDBFF\uDC00-\uDFFF]+")
        if (emojiRegex.containsMatchIn(value)) {
            throw StringVOException(
                code = StringVOMessages.INVALID_EMOJI.code,
                message = StringVOMessages.INVALID_EMOJI.message
            )
        }
    }

    protected fun ensurePrefix(prefix: String) {
        if (!value.startsWith(prefix)) {
            throw StringVOException(
                code = StringVOMessages.INVALID_PREFIX.code,
                message = StringVOMessages.INVALID_PREFIX.message
            )
        }
    }

    protected fun ensureSuffix(suffix: String) {
        if (!value.endsWith(suffix)) {
            throw StringVOException(
                code = StringVOMessages.INVALID_SUFFIX.code,
                message = StringVOMessages.INVALID_SUFFIX.message
            )
        }
    }

    protected fun ensureNotAllowed(values: List<String>) {
        if (values.contains(value)) {
            throw StringVOException(
                code = StringVOMessages.NOT_ALLOWED.code,
                message = StringVOMessages.NOT_ALLOWED.message
            )
        }
    }
}
