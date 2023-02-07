package com.danphuong.utils.ext

import android.net.Uri
import java.security.MessageDigest
import java.text.Normalizer
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

const val full_width_regex = "/^[０-９]+\$/\n"
const val half_width_regex = "/^[0-9]+\$/\n"
val patternFullWidth : Pattern = Pattern.compile(full_width_regex, Pattern.UNICODE_CASE)
val patternHalfWidth : Pattern = Pattern.compile(half_width_regex, Pattern.UNICODE_CASE)

fun String.toCommasNumber() : String? {
    try {
        return NumberFormat.getNumberInstance(Locale.JAPAN).format(this.toFloat());
    } catch (e : Exception) {
        return "0"
    }
}

fun String.removeText() : String {
    return this.filter(Char::isDigit)
}

fun byteArrayToString(bytes : ByteArray) : String? {
    val buffer = StringBuilder()
    for (b in bytes) {
        buffer.append(java.lang.String.format(Locale.getDefault(), "%02x", b))
    }
    return buffer.toString()
}

fun String.toSha1() : String? {
    return try {
        val messageDigest : MessageDigest = MessageDigest.getInstance("SHA-1")
        messageDigest.update(this.toByteArray(charset("UTF-8")))
        byteArrayToString(messageDigest.digest())
    } catch (ignored : Exception) {
        ignored.printStackTrace()
        ""
    }
}

fun String.clearQuery() : String {
    val builtUri : Uri = Uri.parse(this).buildUpon().clearQuery().build()
    return builtUri.toString()
}

fun String.addParamUrl(param : String, value : String) : String {
    val builtUri : Uri = Uri.parse(this).buildUpon().appendQueryParameter(param, value).build()
    return builtUri.toString()
}

fun String.toFullWidth() : String {
    var str : String
    str = this.replace("A", "Ａ").replace("B", "Ｂ").replace("C", "Ｃ").replace("D", "Ｄ")
        .replace("E", "Ｅ").replace("F", "Ｆ").replace("G", "Ｇ").replace("H", "Ｈ").replace("I", "Ｉ")
        .replace("J", "Ｊ").replace("K", "Ｋ").replace("L", "Ｌ").replace("M", "Ｍ").replace("N", "Ｎ")
        .replace("O", "Ｏ").replace("P", "Ｐ").replace("Q", "Ｑ").replace("R", "Ｒ").replace("S", "Ｓ")
        .replace("T", "Ｔ").replace("U", "Ｕ").replace("V", "Ｖ").replace("W", "Ｗ").replace("X", "Ｘ")
        .replace("Y", "Ｙ").replace("Z", "Ｚ")
    str = str.replace("a", "ａ").replace("b", "ｂ").replace("c", "ｃ").replace("d", "ｄ")
        .replace("e", "ｅ").replace("f", "ｆ").replace("g", "ｇ").replace("h", "ｈ").replace("i", "ｉ")
        .replace("j", "ｊ").replace("k", "ｋ").replace("l", "ｌ").replace("m", "ｍ").replace("n", "ｎ")
        .replace("o", "ｏ").replace("p", "ｐ").replace("q", "ｑ").replace("r", "ｒ").replace("s", "ｓ")
        .replace("t", "ｔ").replace("u", "ｕ").replace("v", "ｖ").replace("w", "ｗ").replace("x", "ｘ")
        .replace("y", "ｙ").replace("z", "ｚ")
    str = str.replace("0", "０").replace("1", "１").replace("2", "２").replace("3", "３")
        .replace("4", "４").replace("5", "５").replace("6", "６").replace("7", "７").replace("8", "８")
        .replace("9", "９")
    str = str.replace("ｱ", "ア").replace("ｲ", "イ").replace("ｳ", "ウ").replace("ｴ", "エ")
        .replace("ｵ", "オ").replace("ｶ", "カ").replace("ｷ", "キ").replace("ｸ", "ク").replace("ｹ", "ケ")
        .replace("ｺ", "コ").replace("ｻ", "サ").replace("ｼ", "シ").replace("ｽ", "ス").replace("ｾ", "セ")
        .replace("ｿ", "ソ").replace("ﾀ", "タ").replace("ﾁ", "チ").replace("ﾂ", "ツ").replace("ﾃ", "テ")
        .replace("ﾄ", "ト").replace("ﾅ", "ナ").replace("ﾆ", "ニ").replace("ﾇ", "ヌ").replace("ﾈ", "ネ")
        .replace("ﾉ", "ノ").replace("ﾊ", "ハ").replace("ﾋ", "ヒ").replace("ﾌ", "フ").replace("ﾍ", "ヘ")
        .replace("ﾎ", "ホ").replace("ﾏ", "マ").replace("ﾐ", "ミ").replace("ﾑ", "ム").replace("ﾒ", "メ")
        .replace("ﾓ", "モ").replace("ﾔ", "ヤ").replace("ﾕ", "ユ").replace("ﾖ", "ヨ").replace("ﾗ", "ラ")
        .replace("ﾘ", "リ").replace("ﾙ", "ル").replace("ﾚ", "レ").replace("ﾛ", "ロ").replace("ﾜ", "ワ")
        .replace("ｦ", "ヲ").replace("ﾝ", "ン").replace("ｧ", "ァ").replace("ｨ", "ィ").replace("ｩ", "ゥ")
        .replace("ｪ", "ェ").replace("ｫ", "ォ").replace("ｶ", "ヵ").replace("ｹ", "ヶ").replace("ｯ", "ッ")
        .replace("ｬ", "ャ").replace("ｭ", "ュ").replace("ｮ", "ョ").replace("ﾜ", "ヮ").replace("ｳﾞ", "ヴ")
        .replace("ｶﾞ", "ガ").replace("ｷﾞ", "ギ").replace("ｸﾞ", "グ").replace("ｹﾞ", "ゲ")
        .replace("ｺﾞ", "ゴ").replace("ｻﾞ", "ザ").replace("ｼﾞ", "ジ").replace("ｽﾞ", "ズ")
        .replace("ｾﾞ", "ゼ").replace("ｿﾞ", "ゾ").replace("ﾀﾞ", "ダ").replace("ﾁﾞ", "ヂ")
        .replace("ﾂﾞ", "ヅ").replace("ﾃﾞ", "デ").replace("ﾄﾞ", "ド").replace("ﾊﾞ", "バ")
        .replace("ﾋﾞ", "ビ").replace("ﾌﾞ", "ブ").replace("ﾍﾞ", "ベ").replace("ﾎﾞ", "ボ")
        .replace("ﾊﾟ", "パ").replace("ﾋﾟ", "ピ").replace("ﾌﾟ", "プ").replace("ﾍﾟ", "ペ")
        .replace("ﾎﾟ", "ポ")
    str = str.replace("!", "！").replace("#", "＃").replace("$", "＄").replace("%", "％")
        .replace("&", "＆").replace("(", "（").replace(")", "）").replace("ｰ", "ー").replace("-", "－")
        .replace("=", "＝").replace("^", "＾").replace("~", "～").replace("|", "｜").replace("@", "＠")
        .replace("`", "‘").replace("｢", "「").replace("[", "［").replace("{", "｛").replace("+", "＋")
        .replace(":", "：").replace("*", "＊").replace("｣", "」").replace("]", "］").replace("}", "｝")
        .replace("､", "、").replace(",", "，").replace("<", "＜").replace("｡", "。").replace(".", "．")
        .replace(">", "＞").replace("･", "・").replace("/", "／").replace("?", "？").replace("_", "＿")
    return str
}

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}
