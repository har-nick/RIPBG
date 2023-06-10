package domain.model

data class Torrent(val title: String, val magnet: String)

fun String.toTorrent(): Torrent {
    val delimiter = "&dn="
    val magnet = this.substringBefore(delimiter)
    val title = this.substringAfter(delimiter)

    return Torrent(title, magnet)
}
